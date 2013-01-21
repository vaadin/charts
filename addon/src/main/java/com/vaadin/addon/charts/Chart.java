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

import com.vaadin.addon.charts.client.ui.ChartClientRpc;
import com.vaadin.addon.charts.client.ui.ChartConnector;
import com.vaadin.addon.charts.client.ui.ChartServerRpc;
import com.vaadin.addon.charts.client.ui.ChartState;
import com.vaadin.addon.charts.model.AbstractSeries;
import com.vaadin.addon.charts.model.ChartModel;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ConfigurationMutationListener;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.util.ReflectTools;

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
public class Chart extends AbstractComponent {

    static {
        LicenseChecker.nag();
    }

    private String jsonConfig;

    private Configuration configuration;

    private boolean stateDirty = false;

    /**
     * Constructs a chart component with default settings.
     * <p>
     * Default dimensions are 100% width and 400px height.
     * 
     * @See {@link Chart}
     */
    public Chart() {
        setWidth(100, Unit.PERCENTAGE);
        setHeight(400, Unit.PIXELS);
        configuration = new Configuration();

        registerRpc(new ChartServerRpc() {

            @Override
            public void onChartClick(double x, double y) {
                ChartClickEvent chartClickEvent = new ChartClickEvent(
                        Chart.this, x, y);
                fireEvent(chartClickEvent);
            }

            @Override
            public void onPointClick(double x, double y, int seriesIndex,
                    String category) {

                Series series = getSeriesBasedOnIndex(seriesIndex);
                PointClickEvent pointClickEvent = new PointClickEvent(
                        Chart.this, x, y, series, category);
                fireEvent(pointClickEvent);
            }

            private Series getSeriesBasedOnIndex(int seriesIndex) {
                Series series = getConfiguration().getSeries().get(seriesIndex);
                return series;
            }

            @Override
            public void onSelection(double selectionStart, double selectionEnd) {
                ChartSelectionEvent selectionEvent = new ChartSelectionEvent(
                        Chart.this, selectionStart, selectionEnd);
                fireEvent(selectionEvent);
            }

            @Override
            public void onLegendItemClick(int seriesIndex) {
                LegendItemClickEvent itemClickEvent = new LegendItemClickEvent(
                        Chart.this, getSeriesBasedOnIndex(seriesIndex));
                fireEvent(itemClickEvent);

            }
        });
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

    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial);
        if (initial || stateDirty) {
            getState().jsonState = getChartConfig();
            stateDirty = false;
        }
        if (initial && configuration != null) {
            // Start listening to data series events once the chart has been
            // drawn.
            configuration.setMutationListener(dataSeriesEventListener);
        }
    }

    @Override
    protected ChartState getState() {
        return (ChartState) super.getState();
    }

    /**
     * @return the configuration for the chart in Highcharts compatible JSON
     *         format.
     */
    private String getChartConfig() {
        if (jsonConfig != null) {
            return jsonConfig;
        }
        return configuration.toString();
    }

    /**
     * Draws chart with the given configuration as a starting point. The
     * configuration is given in Highcharts configuration format (JSON).
     * <p>
     * JavaScript is not allowed in the configuration. An exception is formatter
     * functions. Those must be given by prepending the special
     * <code>_fn_</code> prefix to the property name. The client side will
     * evaluate these into functions. Otherwise the configuration is evaluated
     * safely with a JSON parser.
     * <p>
     * Note, that if further modifications are done to the configuration, the
     * method must be called again to redraw the UI.
     * 
     * @param jsonConfig
     *            the chart configuration as a JSON string
     */
    public void drawChart(String jsonConfig) {
        this.jsonConfig = jsonConfig;
        configuration.setMutationListener(null);
        configuration = null;
        stateDirty = true;
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
        this.configuration = configuration;
        jsonConfig = null;
        stateDirty = true;
        forceStateChange();
    }

    private void forceStateChange() {
        getState().paintCount++;
        markAsDirty();
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
        this.addListener(ChartConnector.CHART_CLICK_EVENT_ID,
                ChartClickEvent.class, listener, chartClickMethod);
    }

    /**
     * Removes a chart click listener.
     * 
     * @see #addChartClickListener(ChartClickListener)
     * @param listener
     */
    public void removeChartClickListener(ChartClickListener listener) {
        this.removeListener(ChartConnector.CHART_CLICK_EVENT_ID,
                ChartClickEvent.class, listener);
    }

    /**
     * Adds a point click listener, which will be notified of clicks on the
     * points in the chart
     * 
     * @param listener
     */
    public void addPointClickListener(PointClickListener listener) {
        this.addListener(ChartConnector.POINT_CLICK_EVENT_ID,
                PointClickEvent.class, listener, pointClickMethod);
    }

    /**
     * Removes a point click listener.
     * 
     * @see #addPointClickListener(PointClickListener)
     * @param listener
     */
    public void removePointClickListener(ChartClickListener listener) {
        this.removeListener(ChartConnector.POINT_CLICK_EVENT_ID,
                PointClickEvent.class, listener);
    }

    /**
     * Adds a column click listener, which will be notified of clicks on the
     * chart's columns
     * 
     * @param listener
     */
    public void addColumnClickListener(PointClickListener listener) {
        this.addListener(ChartConnector.COLUMN_CLICK_EVENT_ID,
                PointClickEvent.class, listener, pointClickMethod);
    }

    /**
     * Removes a column click listener.
     * 
     * @see #addColumnClickListener(PointClickListener)
     * @param listener
     */
    public void removeColumnClickListener(ChartClickListener listener) {
        this.removeListener(ChartConnector.COLUMN_CLICK_EVENT_ID,
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
        this.addListener(ChartConnector.CHART_SELECTION_EVENT_ID,
                ChartSelectionEvent.class, listener, chartSelectionMethod);
    }

    /**
     * Removes a chart selection listener.
     * 
     * @see #addChartSelectionListener(ChartSelectionListener)
     * @param listener
     */
    public void removeChartSelectionListener(ChartSelectionListener listener) {
        this.removeListener(ChartConnector.CHART_SELECTION_EVENT_ID,
                ChartSelectionEvent.class, listener);
    }

    /**
     * Adds a legend item click listener, which will be notified of clicks on
     * the legend's items
     * 
     * @param listener
     */
    public void addLegendItemClickListener(LegendItemClickListener listener) {
        this.addListener(ChartConnector.LEGENDITEM_CLICK_EVENT_ID,
                LegendItemClickEvent.class, listener, legendItemClickMethod);
    }

    /**
     * Removes a legend item click listener.
     * 
     * @see #addLegendItemClickListener(LegendItemClickListener)
     * @param listener
     */
    public void removeLegendItemClickListener(LegendItemClickListener listener) {
        this.removeListener(ChartConnector.LEGENDITEM_CLICK_EVENT_ID,
                LegendItemClickEvent.class, listener);
    }

    /**
     * Listens to events on the series attached to the chart and redraws as
     * necessary.
     */
    private final ConfigurationMutationListener dataSeriesEventListener = new ConfigurationMutationListener() {
        // TODO: add support for DataSeriesItems with name,y pairs

        @Override
        public void dataAdded(DataAddedEvent event) {
            if (event.getItem() != null) {
                if (event.getItem().getX() != null) {
                    // x,y type data
                    getRpcProxy(ChartClientRpc.class).addPoint(
                            event.getItem().getX().doubleValue(),
                            event.getItem().getY().doubleValue(),
                            getConfiguration().getSeries().indexOf(
                                    event.getSeries()), event.isShift());
                }
            }
        }

        @Override
        public void dataRemoved(DataRemovedEvent event) {
            if (event.getItem() != null) {
                if (event.getItem().getX() != null) {
                    // x,y type data
                    getRpcProxy(ChartClientRpc.class).removePoint(
                            event.getItem().getX().doubleValue(),
                            event.getItem().getY().doubleValue());
                }
            }
        }

        @Override
        public void dataUpdated(DataUpdatedEvent event) {
            if (event.getValue() != null) {
                getRpcProxy(ChartClientRpc.class).updatePointValue(
                        getConfiguration().getSeries().indexOf(
                                event.getSeries()), event.getPointIndex(),
                        event.getValue().doubleValue());
            }
        }

        @Override
        public void seriesEnablation(SeriesEnablationEvent event) {
            int seriesIndex = getConfiguration().getSeries().indexOf(
                    event.getSeries());
            getRpcProxy(ChartClientRpc.class).setSeriesEnabled(seriesIndex,
                    event.isEnabled());
        }

        @Override
        public void animationChanged(boolean animation) {
            getRpcProxy(ChartClientRpc.class).setAnimationEnabled(animation);
        }

    };
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if(getUI() != null) {
            configuration.setMutationListener(dataSeriesEventListener);
        }
    }
}
