package com.vaadin.addon.charts;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

import com.vaadin.addon.charts.client.ui.ChartClientRpc;
import com.vaadin.addon.charts.client.ui.ChartConnector;
import com.vaadin.addon.charts.client.ui.ChartServerRpc;
import com.vaadin.addon.charts.client.ui.ChartState;
import com.vaadin.addon.charts.client.ui.MouseEventDetails;
import com.vaadin.addon.charts.events.AbstractSeriesEvent;
import com.vaadin.addon.charts.events.AxisRescaledEvent;
import com.vaadin.addon.charts.events.ConfigurationChangeListener;
import com.vaadin.addon.charts.events.DataAddedEvent;
import com.vaadin.addon.charts.events.DataRemovedEvent;
import com.vaadin.addon.charts.events.DataUpdatedEvent;
import com.vaadin.addon.charts.events.ItemSlicedEvent;
import com.vaadin.addon.charts.events.SeriesStateEvent;
import com.vaadin.addon.charts.model.AbstractSeries;
import com.vaadin.addon.charts.model.ChartModel;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
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
@SuppressWarnings("serial")
public class Chart extends AbstractComponent {

    /**
     * Forwards changes broadcasted by configurations into client RPC method
     * calls.
     * 
     * @since 2.0
     *
     */
    private static class ProxyChangeForwarder implements
            ConfigurationChangeListener {

        private final Chart chart;

        public ProxyChangeForwarder(Chart chart) {
            this.chart = chart;
        }

        @Override
        public void dataAdded(DataAddedEvent event) {
            if (event.getItem() != null) {
                if (event.getItem().getX() != null) {
                    // x,y type data
                    chart.getRpcProxy(ChartClientRpc.class).addPoint(
                            event.getItem().toString(), getSeriesIndex(event),
                            event.isShift());
                }
            }
        }

        private int getSeriesIndex(AbstractSeriesEvent event) {
            return chart.getConfiguration().getSeries()
                    .indexOf(event.getSeries());
        }

        @Override
        public void dataRemoved(DataRemovedEvent event) {
            chart.getRpcProxy(ChartClientRpc.class).removePoint(
                    event.getIndex(), getSeriesIndex(event));
        }

        @Override
        public void dataUpdated(DataUpdatedEvent event) {
            if (event.getValue() != null) {
                chart.getRpcProxy(ChartClientRpc.class).updatePointValue(
                        getSeriesIndex(event), event.getPointIndex(),
                        event.getValue().doubleValue());
            } else {
                chart.getRpcProxy(ChartClientRpc.class).updatePoint(
                        getSeriesIndex(event), event.getPointIndex(),
                        event.getItem().toString());
            }
        }

        @Override
        public void seriesStateChanged(SeriesStateEvent event) {
            chart.getRpcProxy(ChartClientRpc.class).setSeriesEnabled(
                    getSeriesIndex(event), event.isEnabled());
        }

        @Override
        public void animationChanged(boolean animation) {
            chart.getRpcProxy(ChartClientRpc.class).setAnimationEnabled(
                    animation);
        }

        @Override
        public void axisRescaled(AxisRescaledEvent event) {
            chart.getRpcProxy(ChartClientRpc.class).rescaleAxis(
                    event.getAxis(), event.getAxisIndex(),
                    event.getMinimum().doubleValue(),
                    event.getMaximum().doubleValue(),
                    event.isRedrawingNeeded(), event.isAnimated());
        }

        @Override
        public void itemSliced(ItemSlicedEvent event) {
            chart.getRpcProxy(ChartClientRpc.class).sliceItem(
                    getSeriesIndex(event), event.getIndex(), event.isSliced(),
                    event.isRedraw(), event.isAnimation());
        }

    }

    private final class ChartServerRpcImplementation implements ChartServerRpc {
        @Override
        public void onChartClick(MouseEventDetails details) {
            final ChartClickEvent chartClickEvent = new ChartClickEvent(
                    Chart.this, details);
            fireEvent(chartClickEvent);
        }

        @Override
        public void onPointClick(MouseEventDetails details,
                final int seriesIndex, final String category,
                final int pointIndex) {

            final Series series = getSeriesBasedOnIndex(seriesIndex);
            final PointClickEvent pointClickEvent = new PointClickEvent(
                    Chart.this, details, series, category, pointIndex);
            fireEvent(pointClickEvent);
        }

        private Series getSeriesBasedOnIndex(final int seriesIndex) {
            final Series series = getConfiguration().getSeries().get(
                    seriesIndex);
            return series;
        }

        @Override
        public void onSelection(final double selectionStart,
                final double selectionEnd, final double valueStart,
                final double valueEnd) {
            final ChartSelectionEvent selectionEvent = new ChartSelectionEvent(
                    Chart.this, selectionStart, selectionEnd, valueStart,
                    valueEnd);
            fireEvent(selectionEvent);
        }

        @Override
        public void onLegendItemClick(final int seriesIndex, int seriesItemIndex) {
            final LegendItemClickEvent itemClickEvent = new LegendItemClickEvent(
                    Chart.this, getSeriesBasedOnIndex(seriesIndex),
                    seriesItemIndex);
            fireEvent(itemClickEvent);

        }
    }

    /**
     * Listens to events on the series attached to the chart and redraws as
     * necessary.
     */
    private final ConfigurationChangeListener changeListener = new ProxyChangeForwarder(
            this);

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

        registerRpc(new ChartServerRpcImplementation(), ChartServerRpc.class);
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
            getState().confState = configuration == null ? null : configuration
                    .toString();
            getState().jsonState = jsonConfig;
            stateDirty = false;
        }
        if (configuration != null) {
            // Start listening to data series events once the chart has been
            // drawn.
            configuration.addChangeListener(changeListener);
        }
    }

    @Override
    protected ChartState getState() {
        return (ChartState) super.getState();
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
        forceStateChange();
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
        forceStateChange();
    }

    private void forceStateChange() {
        getState().paintCount++;
        markAsDirty();
        // Remove all pending RPC calls as they are now irrelevant
        // and possibly corrupted as well
        retrievePendingRpcCalls();
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
     * points, bars or columns in the chart
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
    public void removePointClickListener(PointClickListener listener) {
        this.removeListener(ChartConnector.POINT_CLICK_EVENT_ID,
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

    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
        if (getUI() != null) {
            configuration.addChangeListener(changeListener);
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
        stateDirty = true;
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
            this.configuration.removeChangeListener(changeListener);
        }
        this.configuration = configuration;
        stateDirty = true;
    }
}
