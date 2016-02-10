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

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.util.Stack;

import com.vaadin.addon.charts.declarative.ChartDesignWriter;
import com.vaadin.addon.charts.declarative.ChartDesignReader;
import com.vaadin.addon.charts.events.AbstractSeriesEvent;
import com.vaadin.addon.charts.events.AxisRescaledEvent;
import com.vaadin.addon.charts.events.ConfigurationChangeListener;
import com.vaadin.addon.charts.events.DataAddedEvent;
import com.vaadin.addon.charts.events.DataRemovedEvent;
import com.vaadin.addon.charts.events.DataUpdatedEvent;
import com.vaadin.addon.charts.events.ItemSlicedEvent;
import com.vaadin.addon.charts.events.SeriesStateEvent;
import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.AbstractSeries;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.shared.ChartClientRpc;
import com.vaadin.addon.charts.shared.ChartConnector;
import com.vaadin.addon.charts.shared.ChartServerRpc;
import com.vaadin.addon.charts.shared.ChartState;
import com.vaadin.addon.charts.shared.DrilldownEventDetails;
import com.vaadin.addon.charts.shared.MouseEventDetails;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.declarative.DesignContext;
import com.vaadin.util.ReflectTools;

import org.jsoup.nodes.Element;

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
                            toJSON(event.getItem()), getSeriesIndex(event),
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
                        toJSON(event.getItem()));
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

        @Override
        public void drilldownAdded(int seriesIndex, int pointIndex,
                Series series) {
            chart.getRpcProxy(ChartClientRpc.class).addDrilldown(
                    toJSON((AbstractConfigurationObject) series), seriesIndex,
                    pointIndex);

        }

    }

    private final class ChartServerRpcImplementation implements ChartServerRpc {

        @Override
        public void onChartClick(MouseEventDetails details) {
            final ChartClickEvent chartClickEvent = new ChartClickEvent(
                    Chart.this, details);
            fireEvent(chartClickEvent);
        }

        private Stack<Series> drilldownStack = new Stack<Series>();

        @Override
        public void onChartDrilldown(DrilldownEventDetails details) {
            final int seriesIndex = details.getPoint().getSeriesIndex();
            final int pointIndex = details.getPoint().getIndex();
            Series series = resolveSeriesFor(seriesIndex);
            DataSeriesItem item = null;
            if (series instanceof DataSeries) {
                DataSeries dataSeries = (DataSeries) series;
                item = dataSeries.get(pointIndex);
            }
            final DrilldownEvent chartDrilldownEvent = new DrilldownEvent(
                    Chart.this, series, item, pointIndex);

            if (getDrilldownCallback() != null) {
                Series drilldownSeries = getDrilldownCallback()
                        .handleDrilldown(chartDrilldownEvent);
                if (drilldownSeries != null) {
                    drilldownStack.push(drilldownSeries);
                    getRpcProxy(ChartClientRpc.class)
                            .addDrilldown(
                                    toJSON((AbstractConfigurationObject) drilldownSeries),
                                    seriesIndex, pointIndex);
                }
            }
        }

        @Override
        public void onChartDrillup() {
            if (!drilldownStack.isEmpty()) {
                drilldownStack.pop();
            }
            fireEvent(new ChartDrillupEvent(Chart.this));

        }

        @Override
        public void onPointClick(MouseEventDetails details,
                final int seriesIndex, final String category,
                final int pointIndex) {
            Series series = resolveSeriesFor(seriesIndex);
            final PointClickEvent pointClickEvent = new PointClickEvent(
                    Chart.this, details, series, category, pointIndex);
            fireEvent(pointClickEvent);
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
            Series series = resolveSeriesFor(seriesIndex);
            final LegendItemClickEvent legendItemClickEvent = new LegendItemClickEvent(
                    Chart.this, series, seriesItemIndex);
            fireEvent(legendItemClickEvent);
        }

        @Override
        public void onCheckboxClick(boolean isChecked, final int seriesIndex,
                int seriesItemIndex) {
            Series series = resolveSeriesFor(seriesIndex);
            CheckboxClickEvent checkboxClickEvent = new CheckboxClickEvent(
                    Chart.this, isChecked, series, seriesItemIndex);
            fireEvent(checkboxClickEvent);
        }

        @Override
        public void onSeriesHide(int seriesIndex, int seriesItemIndex) {
            Series series = resolveSeriesFor(seriesIndex);
            SeriesHideEvent seriesHideEvent = new SeriesHideEvent(Chart.this,
                    series, seriesItemIndex);
            fireEvent(seriesHideEvent);
        }

        @Override
        public void onSeriesShow(int seriesIndex, int seriesItemIndex) {
            Series series = resolveSeriesFor(seriesIndex);
            SeriesShowEvent seriesShowEvent = new SeriesShowEvent(Chart.this,
                    series, seriesItemIndex);
            fireEvent(seriesShowEvent);
        }

        @Override
        public void onXAxesExtremesChange(int axisIndex, double minimum,
                double maximum) {
            XAxis axis = getConfiguration().getxAxis(axisIndex);
            XAxesExtremesChangeEvent event = new XAxesExtremesChangeEvent(
                    Chart.this, axis, minimum, maximum);
            fireEvent(event);
        }

        @Override
        public void onYAxesExtremesChange(int axisIndex, double minimum,
                double maximum) {
            YAxis axis = getConfiguration().getyAxis(axisIndex);
            YAxesExtremesChangeEvent event = new YAxesExtremesChangeEvent(
                    Chart.this, axis, minimum, maximum);
            fireEvent(event);
        }

        @Override
        public void onPointSelect(int seriesIndex, String category,
                int pointIndex) {
            Series series = resolveSeriesFor(seriesIndex);
            PointSelectEvent event = new PointSelectEvent(Chart.this, series,
                    category, pointIndex);
            fireEvent(event);
        }

        @Override
        public void onPointUnselect(int seriesIndex, String category,
                int pointIndex) {
            Series series = resolveSeriesFor(seriesIndex);
            PointUnselectEvent event = new PointUnselectEvent(Chart.this,
                    series, category, pointIndex);
            fireEvent(event);
        }

        private Series resolveSeriesFor(int seriesIndex) {
            Series series;
            if (drilldownStack.isEmpty()) {
                series = getConfiguration().getSeries().get(seriesIndex);
            } else {
                series = drilldownStack.peek();
            }
            return series;
        }

    }

    private final static Method chartClickMethod = ReflectTools.findMethod(
            ChartClickListener.class, "onClick", ChartClickEvent.class);
    private final static Method chartDrillupMethod = ReflectTools.findMethod(
            ChartDrillupListener.class, "onDrillup", ChartDrillupEvent.class);
    private final static Method pointClickMethod = ReflectTools.findMethod(
            PointClickListener.class, "onClick", PointClickEvent.class);
    private final static Method chartSelectionMethod = ReflectTools.findMethod(
            ChartSelectionListener.class, "onSelection",
            ChartSelectionEvent.class);
    private final static Method legendItemClickMethod = ReflectTools
            .findMethod(LegendItemClickListener.class, "onClick",
                    LegendItemClickEvent.class);
    private final static Method checkboxClickMethod = ReflectTools.findMethod(
            CheckboxClickListener.class, "onClick", CheckboxClickEvent.class);
    private final static Method showSeriesMethod = ReflectTools.findMethod(
            SeriesShowListener.class, "onShow", SeriesShowEvent.class);
    private final static Method hideSeriesMethod = ReflectTools.findMethod(
            SeriesHideListener.class, "onHide", SeriesHideEvent.class);
    private final static Method xAxesExtremesChangeMethod = ReflectTools
            .findMethod(XAxesExtremesChangeListener.class,
                    "onXAxesExtremesChange", XAxesExtremesChangeEvent.class);
    private final static Method yAxesExtremesChangeMethod = ReflectTools
            .findMethod(YAxesExtremesChangeListener.class,
                    "onYAxesExtremesChange", YAxesExtremesChangeEvent.class);
    private final static Method pointSelectMethod = ReflectTools.findMethod(
            PointSelectListener.class, "onSelect", PointSelectEvent.class);
    private final static Method pointUnselectMethod = ReflectTools
            .findMethod(PointUnselectListener.class, "onUnselect",
                    PointUnselectEvent.class);
    /**
     * Listens to events on the series attached to the chart and redraws as
     * necessary.
     */
    private final ConfigurationChangeListener changeListener = new ProxyChangeForwarder(
            this);

    private DrilldownCallback drilldownCallback;

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
        com.vaadin.addon.charts.model.Chart chart = new com.vaadin.addon.charts.model.Chart();
        chart.setType(type);
        configuration.setChart(chart);
    }

    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial);
        if (initial || stateDirty) {
            getState().confState = configuration == null ? null
                    : toJSON(configuration);
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
     * Sets the jsonConfig used to render this chart.
     * <p>
     * Note, that calling this method on already displayed component don't
     * necessary update it. Developer should call {@link #drawChart()} or
     * {@link #drawChart(String)} method to force re draw.
     *
     * @see #drawChart(String)
     * @param jsonConf
     */
    public void setJsonConfig(String jsonConf) {
        jsonConfig = jsonConf;
        stateDirty = true;
    }

    /**
     * @return the chart configuration that is used for this chart
     * @see #drawChart(Configuration)
     */
    public Configuration getConfiguration() {
        return configuration;
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

    /**
     * Draws a chart with the given configuration as a starting point.
     * <p>
     * Note that if you modify the underlying {@link Series} directly the chart
     * will automatically be updated to reflect this unless explicitly told not
     * to. The methods listed below can be used as an example.
     *
     * @see DataSeries#add(DataSeriesItem)
     * @see DataSeries#addData(DataSeriesItem, boolean)
     * @see DataSeries#removeData(DataSeriesItem)
     * @see DataSeries#updateData(DataSeriesItem)
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
     * Adds chart drillup listener, which will be notified of clicks on the
     * 'Back to previous series' button.
     *
     * @param listener
     */
    public void addChartDrillupListener(ChartDrillupListener listener) {
        this.addListener(ChartConnector.CHART_DRILLUP_EVENT_ID,
                ChartDrillupEvent.class, listener, chartDrillupMethod);
    }

    /**
     * Removes a chart drillup listener.
     *
     * @see #addChartDrillupListener(ChartDrillupListener)
     * @param listener
     */
    public void removeChartDrillupListener(ChartDrillupListener listener) {
        this.removeListener(ChartConnector.CHART_DRILLUP_EVENT_ID,
                ChartDrillupEvent.class, listener);
    }

    /**
     * Adds checkbox click listener, which will be notified when user has
     * clicked a checkbox in the legend
     *
     * @param listener
     */
    public void addCheckBoxClickListener(CheckboxClickListener listener) {
        this.addListener(ChartConnector.CHECKBOX_CLICK_EVENT_ID,
                CheckboxClickEvent.class, listener, checkboxClickMethod);
    }

    /**
     * Removes a checkbox click listener
     *
     * @see #addCheckBoxClickListener(CheckboxClickListener)
     * @param listener
     */
    public void removeCheckBoxClickListener(CheckboxClickListener listener) {
        this.removeListener(ChartConnector.CHECKBOX_CLICK_EVENT_ID,
                CheckboxClickEvent.class, listener);
    }

    /**
     * @return drilldownCallbackHandler
     * @see #setDrilldownCallback(DrilldownCallback)
     */
    public DrilldownCallback getDrilldownCallback() {
        return drilldownCallback;
    }

    /**
     * Sets the Chart drilldown handler that's responsible for returning the
     * drilldown series for each drilldown callback when doing async drilldown
     *
     * @see DataSeries#addItemWithDrilldown(com.vaadin.addon.charts.model.series.DataSeriesItem)
     *      addItemWithDrilldown to find out how to enable async drilldown
     *
     * @param drilldownCallback
     */
    public void setDrilldownCallback(DrilldownCallback drilldownCallback) {
        this.drilldownCallback = drilldownCallback;
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
     * <p>
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
     * <p>
     * Note that adding a legend item click listener also disabled the default
     * behaviour to toggle series visibility. If that is not desired, you can
     * enable it again by calling setSeriesVisibilityTogglingDisabled(
     * <code>true</code>)
     *
     * @param listener
     */
    public void addLegendItemClickListener(LegendItemClickListener listener) {
        this.addListener(ChartConnector.LEGENDITEM_CLICK_EVENT_ID,
                LegendItemClickEvent.class, listener, legendItemClickMethod);
        setSeriesVisibilityTogglingDisabled(true);
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
     * Adds a series hide listener, which will be notified when a series is
     * hidden
     *
     * @param listener
     */
    public void addSeriesHideListener(SeriesHideListener listener) {
        this.addListener(ChartConnector.HIDE_SERIES_EVENT_ID,
                SeriesHideEvent.class, listener, hideSeriesMethod);
    }

    /**
     * Removes a series hide listener.
     *
     * @see #addSeriesHideListener(SeriesHideListener)
     * @param listener
     */
    public void removeSeriesHideListener(SeriesHideListener listener) {
        this.removeListener(ChartConnector.HIDE_SERIES_EVENT_ID,
                SeriesHideEvent.class, listener);
    }

    /**
     * Adds a series show listener, which will be notified when a series is
     * shown
     *
     * @param listener
     */
    public void addSeriesShowListener(SeriesShowListener listener) {
        this.addListener(ChartConnector.SHOW_SERIES_EVENT_ID,
                SeriesShowEvent.class, listener, showSeriesMethod);
    }

    /**
     * Removes a series show listener.
     *
     * @see #addSeriesShowListener(SeriesShowListener)
     * @param listener
     */
    public void removeSeriesShowListener(SeriesShowListener listener) {
        this.removeListener(ChartConnector.SHOW_SERIES_EVENT_ID,
                SeriesShowEvent.class, listener);
    }

    /**
     * Adds a x axes extremes change listener, which will be notified when an x
     * axis extremes are changed.
     *
     * @param listener
     */
    public void addXAxesExtremesChangeListener(
            XAxesExtremesChangeListener listener) {
        this.addListener(ChartConnector.X_AXES_EXTREMES_CHANGE_EVENT_ID,
                XAxesExtremesChangeEvent.class, listener,
                xAxesExtremesChangeMethod);
    }

    /**
     * Removes a x axes extremes change listener.
     *
     * @see #addSeriesShowListener(SeriesShowListener)
     * @param listener
     */
    public void removeXAxesExtremesChangeListener(
            XAxesExtremesChangeListener listener) {
        this.removeListener(ChartConnector.X_AXES_EXTREMES_CHANGE_EVENT_ID,
                XAxesExtremesChangeEvent.class, listener);
    }

    /**
     * Adds a y axes extremes change listener, which will be notified when an y
     * axis extremes are changed.
     *
     * @param listener
     */
    public void addYAxesExtremesChangeListener(
            YAxesExtremesChangeListener listener) {
        this.addListener(ChartConnector.Y_AXES_EXTREMES_CHANGE_EVENT_ID,
                YAxesExtremesChangeEvent.class, listener,
                yAxesExtremesChangeMethod);
    }

    /**
     * Removes a y axes extremes change listener.
     *
     * @see #addSeriesShowListener(SeriesShowListener)
     * @param listener
     */
    public void removeYAxesExtremesChangeListener(
            YAxesExtremesChangeListener listener) {
        this.removeListener(ChartConnector.Y_AXES_EXTREMES_CHANGE_EVENT_ID,
                YAxesExtremesChangeEvent.class, listener);
    }

    /**
     * Adds a point select listener, which will be notified when an data point
     * is selected.
     *
     * @param listener
     */
    public void addPointSelectListener(PointSelectListener listener) {
        this.addListener(ChartConnector.POINT_SELECT_EVENT_ID,
                PointSelectEvent.class, listener, pointSelectMethod);
    }

    /**
     * Removes a point select listener.
     *
     * @see #addPointSelectListener(PointSelectListener)
     * @param listener
     */
    public void removePointSelectListener(PointSelectListener listener) {
        this.removeListener(ChartConnector.POINT_SELECT_EVENT_ID,
                PointSelectEvent.class, listener);
    }

    /**
     * Adds a point unselect listener, which will be notified when an data point
     * is unselected.
     *
     * @param listener
     */
    public void addPointUnselectListener(PointUnselectListener listener) {
        this.addListener(ChartConnector.POINT_UNSELECT_EVENT_ID,
                PointUnselectEvent.class, listener, pointUnselectMethod);
    }

    /**
     * Removes a point unselect listener.
     *
     * @see #addPointUnselectListener(PointUnselectListener)
     * @param listener
     */
    public void removePointUnselectListener(PointUnselectListener listener) {
        this.removeListener(ChartConnector.POINT_UNSELECT_EVENT_ID,
                PointUnselectEvent.class, listener);
    }

    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
        if (getUI() != null) {
            configuration.addChangeListener(changeListener);
        }
    }

    /**
     * The series visibility is toggled by default if user clicks the series
     * item in the legend. Calling setSeriesVisibilityTogglingDisabled(
     * <code>true</code>) will disable this behaviour.
     *
     * @param disabled
     */
    public void setSeriesVisibilityTogglingDisabled(boolean disabled) {
        getState().seriesVisibilityTogglingDisabled = disabled;
    }

    public void setTimeline(boolean timeline) {
        getState().timeline = timeline;
    }

    public boolean isTimeline() {
        return getState().timeline;
    }

    @Override
    public void readDesign(Element design, DesignContext designContext) {
        super.readDesign(design, designContext);

        Configuration configuration = getConfiguration();
        if(configuration == null) {
            configuration = new Configuration();
        }
        ChartDesignReader.readConfigurationFromElements(
            design.children(), configuration);

        setConfiguration(configuration);
    }



    @Override
    public void writeDesign(Element design, DesignContext designContext) {
        super.writeDesign(design, designContext);

        Configuration configuration = getConfiguration();
        if(configuration != null) {
            ChartDesignWriter.writeConfigurationToElement(
                configuration, design);
        }
    }
}
