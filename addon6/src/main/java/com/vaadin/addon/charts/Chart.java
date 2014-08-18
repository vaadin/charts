package com.vaadin.addon.charts;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.vaadin.addon.charts.client.ui.HighchartWidget;
import com.vaadin.addon.charts.model.AbstractSeries;
import com.vaadin.addon.charts.model.ChartModel;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ConfigurationMutationListener;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.tools.ReflectTools;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ClientWidget;

/**
 * Chart is a Vaadin component that is used to visualize data.
 * <p>
 * All configuration and data is given through a {@link Configuration} object.
 * Here is a simple usage example:
 * <p>
 * <code><pre>
 * Chart chart = new Chart(ChartType.COLUMN);
 * Configuration conf = chart.getConfiguration();
 * conf.setTitle("My data visualized");
 * ListSeries series = new ListSeries("Diameters");
 * series.setData(4900,  12100,  12800,
 *                6800,  143000, 125000,
 *                51100, 49500);
 * conf.addSeries(series);
 * </pre></code>
 * <p>
 * Unless otherwise documented, dynamic changes to the chart configuration are
 * not immediately reflected to an already drawn component. To redraw the chart
 * you should call {@link #drawChart()} or {@link #drawChart(Configuration)}.
 * <p>
 * The implementation relies on the <a
 * href="http://www.highcharts.com/">HighCharts JS</a> library. Developers can
 * also use its raw JS API via the {@link #drawChart(String)} method.
 * <p>
 * See more examples in Book of Vaadin or the online demos.
 * 
 * @see <a href="http://vaadin.com/book">Book of Vaadin</a>
 * @see <a href="https://vaadin.com/add-ons/charts">Vaadin Charts</a>
 */
@SuppressWarnings("deprecation")
@ClientWidget(HighchartWidget.class)
public class Chart extends AbstractComponent {

    private static final int DIRTYFLAG_DYNCHANGE = 1;
    private List<PartialChange> dynChanges = new LinkedList<PartialChange>();
    private PartialPaintChecker partialPainter = new PartialPaintChecker(this);

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        if (partialPainter.isPartialRepaint()) {
            if (partialPainter.isDirty(DIRTYFLAG_DYNCHANGE)) {
                for (PartialChange pc : dynChanges) {
                    target.startTag("pc");
                    pc.paint(this, target);
                    target.endTag("pc");
                }
                dynChanges.clear();
            }
        } else {
            configuration.setMutationListener(dataSeriesEventListener);
            if (configuration != null) {
                target.addAttribute("confState", configuration.toString());
            }
            if (jsonConfig != null) {
                target.addAttribute("jsonState", jsonConfig);
            }
        }
        partialPainter.paintContentPerformed();
    }

    public void addPartialChange(PartialChange partialChange) {
        dynChanges.add(partialChange);
        partialPainter.setDirty(DIRTYFLAG_DYNCHANGE);
    }

    @Override
    public void changeVariables(Object source, Map<String, Object> variables) {
        if (variables.containsKey("clickX")) {
            Double x = (Double) variables.get("clickX");
            Double y = (Double) variables.get("clickY");
            final ChartClickEvent chartClickEvent = new ChartClickEvent(
                    Chart.this, x, y);
            fireEvent(chartClickEvent);
        } else if (variables.containsKey("pointClickIndex")) {
            final Series series = getSeriesBasedOnIndex(((Integer) variables
                    .get("pointClickSeriesIndex")));
            double x = (Double) variables.get("pointClickX");
            double y = (Double) variables.get("pointClickY");
            String category = (String) variables.get("pointClickCategory");
            int pointIndex = (Integer) variables.get("pointClickIndex");
            final PointClickEvent pointClickEvent = new PointClickEvent(
                    Chart.this, x, y, series, category, pointIndex);
            fireEvent(pointClickEvent);
        } else if (variables.containsKey("legendClick")) {
            final Series series = getSeriesBasedOnIndex(((Integer) variables
                    .get("legendClick")));
            int seriesItemIndex = (Integer) variables.get("seriesItemIndex");
            LegendItemClickEvent legendItemClickEvent = new LegendItemClickEvent(
                    this, series, seriesItemIndex);
            fireEvent(legendItemClickEvent);

        } else if (variables.containsKey("selectionStart")) {

            final double selectionStart = (Double) variables
                    .get("selectionStart");
            final double selectionEnd = (Double) variables.get("selectionEnd");
            final double valueStart = (Double) variables
                    .get("selectionValueStart");
            final double valueEnd = (Double) variables.get("selectionValueEnd");
            final ChartSelectionEvent selectionEvent = new ChartSelectionEvent(
                    Chart.this, selectionStart, selectionEnd, valueStart,
                    valueEnd);
            fireEvent(selectionEvent);
        }
    }

    @Override
    public void requestRepaint() {
        partialPainter.checkBeforeRequestRepaint();
        super.requestRepaint();
    }

    private Series getSeriesBasedOnIndex(final int seriesIndex) {
        final Series series = getConfiguration().getSeries().get(seriesIndex);
        return series;
    }

    static {
        LicenseChecker.nag();
    }

    private String jsonConfig;

    private Configuration configuration;

    /**
     * Constructs a chart component with default settings.
     * <p>
     * Default dimensions are 100% width and 400px height.
     * 
     * @See {@link Chart}
     */
    public Chart() {
        setWidth(100, UNITS_PERCENTAGE);
        setHeight(400, UNITS_PIXELS);
        setConfiguration(new Configuration());
    }

    /**
     * Constructs a chart with the specified default chart type.
     * <p>
     * In charts with multiple series, the type can also be defined by setting
     * series specific plot options.
     * 
     * @see #Chart()
     * @see AbstractSeries#setPlotOptions(com.vaadin.addon.charts.model.AbstractPlotOptions)
     * @param type
     *            the {@link ChartType}
     */
    public Chart(ChartType type) {
        this();
        configuration.setChart(new ChartModel(configuration, type));
    }

    /**
     * Draws chart with the given configuration. The configuration is given in
     * Highcharts configuration format (JavaScript) and it overrides settings
     * done via the standard {@link Configuration} object.
     * <p>
     * Although using strictly typed Java API (via {@link #getConfiguration()}
     * is highly encouraged, using the low level Highcharts configuration may be
     * handy in some occasions.
     * <p>
     * Note, that this configuration is evaluated as JavaScript and the config
     * may contain JavaScript functions. Thus developers should pay attention to
     * data that is passed to this function.
     * <p>
     * Note, that if further modifications are done to the configuration, the
     * method must be called again to redraw the UI.
     * 
     * @see #getConfiguration()
     * @see #drawChart(Configuration)
     * 
     * @param jsonConfig
     *            the chart configuration as a JSON string
     */
    public void drawChart(String jsonConfig) {
        setJsonConfig(jsonConfig);
        requestRepaint();
    }

    /**
     * @see #drawChart(String)
     * @return the Highcharts compatible JSON configuration of the chart.
     */
    public String getJsonConfig() {
        return jsonConfig;
    }

    /**
     * @return the chart configuration that is used for this chart
     * @see #drawChart(Configuration)
     */
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Draws a chart with the given configuration as a starting point.
     * <p>
     * Note that if you modify the underlying {@link Series} directly the chart
     * will automatically be updated to reflect this unless explicitly told not
     * to. The methods listed below can be used as an example.
     * 
     * @see DataSeries#add(com.vaadin.addon.charts.model.DataSeriesItem)
     * @see DataSeries#addData(com.vaadin.addon.charts.model.DataSeriesItem,
     *      boolean)
     * @see DataSeries#removeData(com.vaadin.addon.charts.model.DataSeriesItem)
     * @see DataSeries#updateData(com.vaadin.addon.charts.model.DataSeriesItem)
     * 
     * @param configuration
     */
    public void drawChart(Configuration configuration) {
        setConfiguration(configuration);
        requestRepaint();
    }

    /**
     * Draws the chart using the current configuration.
     * 
     * @see #getConfiguration()
     * @see #drawChart(Configuration)
     */
    public void drawChart() {
        drawChart(getConfiguration());
    }

    private final static Method chartClickMethod = ReflectTools.findMethod(
            ChartClickListener.class, "onClick", ChartClickEvent.class);

    private final static Method pointClickMethod = ReflectTools.findMethod(
            PointClickListener.class, "onClick", PointClickEvent.class);

    private final static Method chartSelectionMethod = ReflectTools.findMethod(
            ChartSelectionListener.class, "onSelection",
            ChartSelectionEvent.class);

    private final static Method legendItemClickMethod = ReflectTools
            .findMethod(LegendItemClickListener.class, "onClick",
                    LegendItemClickEvent.class);

    /**
     * Adds chart click listener, which will be notified of clicks on the chart
     * area
     * 
     * @param listener
     */
    public void addChartClickListener(ChartClickListener listener) {
        this.addListener(HighchartWidget.CHART_CLICK_EVENT_ID,
                ChartClickEvent.class, listener, chartClickMethod);
    }

    /**
     * Removes a chart click listener.
     * 
     * @see #addChartClickListener(ChartClickListener)
     * @param listener
     */
    public void removeChartClickListener(ChartClickListener listener) {
        this.removeListener(HighchartWidget.CHART_CLICK_EVENT_ID,
                ChartClickEvent.class, listener);
    }

    /**
     * Adds a point click listener, which will be notified of clicks on the
     * points, bars or columns in the chart
     * 
     * @param listener
     */
    public void addPointClickListener(PointClickListener listener) {
        this.addListener(HighchartWidget.POINT_CLICK_EVENT_ID,
                PointClickEvent.class, listener, pointClickMethod);
    }

    /**
     * Removes a point click listener.
     * 
     * @see #addPointClickListener(PointClickListener)
     * @param listener
     */
    public void removePointClickListener(PointClickListener listener) {
        this.removeListener(HighchartWidget.POINT_CLICK_EVENT_ID,
                PointClickEvent.class, listener);
    }

    /**
     * Adds a chart selection listener<br />
     * <br />
     * 
     * Note that if a chart selection listener is set, default action for
     * selection is prevented. Most commonly this means that client side zoom
     * doesn't work and you are responsible for setting the zoom, etc in the
     * listener implementation.
     * 
     * @param listener
     */
    public void addChartSelectionListener(ChartSelectionListener listener) {
        this.addListener(HighchartWidget.CHART_SELECTION_EVENT_ID,
                ChartSelectionEvent.class, listener, chartSelectionMethod);
    }

    /**
     * Removes a chart selection listener.
     * 
     * @see #addChartSelectionListener(ChartSelectionListener)
     * @param listener
     */
    public void removeChartSelectionListener(ChartSelectionListener listener) {
        this.removeListener(HighchartWidget.CHART_SELECTION_EVENT_ID,
                ChartSelectionEvent.class, listener);
    }

    /**
     * Adds a legend item click listener, which will be notified of clicks on
     * the legend's items
     * 
     * @param listener
     */
    public void addLegendItemClickListener(LegendItemClickListener listener) {
        this.addListener(HighchartWidget.LEGENDITEM_CLICK_EVENT_ID,
                LegendItemClickEvent.class, listener, legendItemClickMethod);
    }

    /**
     * Removes a legend item click listener.
     * 
     * @see #addLegendItemClickListener(LegendItemClickListener)
     * @param listener
     */
    public void removeLegendItemClickListener(LegendItemClickListener listener) {
        this.removeListener(HighchartWidget.LEGENDITEM_CLICK_EVENT_ID,
                LegendItemClickEvent.class, listener);
    }

    /**
     * Listens to events on the series attached to the chart and redraws as
     * necessary.
     */
    private final ConfigurationMutationListener dataSeriesEventListener = new ConfigurationMutationListener() {

        @Override
        public void dataAdded(DataAddedEvent event) {
            if (event.getItem() != null) {
                if (event.getItem().getX() != null) {
                    PointAddition pointAddition = new PointAddition(event
                            .getItem().toString(), getSeriesIndex(event),
                            event.isShift());
                    dynChanges.add(pointAddition);
                    partialPainter.setDirty(DIRTYFLAG_DYNCHANGE);
                }
            }
        }

        private int getSeriesIndex(SeriesEvent event) {
            return getConfiguration().getSeries().indexOf(event.getSeries());
        }

        @Override
        public void dataRemoved(DataRemovedEvent event) {
            PointRemoval pr = new PointRemoval(event.getIndex(),
                    getSeriesIndex(event));
            dynChanges.add(pr);
            partialPainter.setDirty(DIRTYFLAG_DYNCHANGE);
        }

        @Override
        public void dataUpdated(DataUpdatedEvent event) {
            PointUpdate pointUpdate;

            if (event.getValue() != null) {
                pointUpdate = new PointUpdate(event.getValue(),
                        event.getPointIndex(), getSeriesIndex(event));
            } else {
                pointUpdate = new PointUpdate(event.getItem().toString(),
                        event.getPointIndex(), getSeriesIndex(event));
            }
            dynChanges.add(pointUpdate);
            partialPainter.setDirty(DIRTYFLAG_DYNCHANGE);
        }

        @Override
        public void seriesEnablation(SeriesEnablationEvent event) {
            SeriesEnabled seriesEnabled = new SeriesEnabled(
                    getSeriesIndex(event), event.isEnabled());
            dynChanges.add(seriesEnabled);
            partialPainter.setDirty(DIRTYFLAG_DYNCHANGE);
        }

        @Override
        public void animationChanged(boolean animation) {
            dynChanges.add(new AnimationEnabled(animation));
            partialPainter.setDirty(DIRTYFLAG_DYNCHANGE);
        }

    };

    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
        if (getApplication() != null) {
            configuration.setMutationListener(dataSeriesEventListener);
        }
    }

    /**
     * Sets the jsonConfig used to render this chart.
     * <p>
     * Note, that calling this method on already displayed component don't
     * necessary update it. Developer should call {@link #drawChart()} or
     * {@link #drawChart(String)} method to force re draw.
     * 
     * @see #drawChart(String)
     * 
     * @param jsonConf
     */
    public void setJsonConfig(String jsonConf) {
        jsonConfig = jsonConf;
        requestRepaint();
    }

    /**
     * Sets the configuration object used to render this chart.
     * <p>
     * Note, that calling this method on already displayed component don't
     * necessary update it. Developer should call {@link #drawChart()} or
     * {@link #drawChart(Configuration)} method to force re draw.
     * 
     * @param configuration
     */
    public void setConfiguration(Configuration configuration) {
        if (this.configuration != null) {
            // unbound old configuration
            this.configuration.setMutationListener(null);
        }
        this.configuration = configuration;
        requestRepaint();
    }
}
