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

import java.lang.reflect.Method;

import com.vaadin.addon.charts.client.ui.ChartClientRpc;
import com.vaadin.addon.charts.client.ui.ChartServerRpc;
import com.vaadin.addon.charts.client.ui.ChartState;
import com.vaadin.addon.charts.client.ui.HighchartWidget;
import com.vaadin.addon.charts.model.ChartModel;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeriesEventListener;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.util.ReflectTools;

public class Chart extends AbstractComponent {

    private String jsonConfig;

    private Configuration configuration;

    private boolean stateDirty = false;

    /**
     * Default constructor, constructs 100% width and 400px height chart
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
            public void onPointClick(double x, double y, String seriesName,
                    String category) {
                PointClickEvent pointClickEvent = new PointClickEvent(
                        Chart.this, x, y, seriesName, category);
                fireEvent(pointClickEvent);
            }

            @Override
            public void onSelection(double selectionStart, double selectionEnd) {
                ChartSelectionEvent selectionEvent = new ChartSelectionEvent(
                        Chart.this, selectionStart, selectionEnd);
                fireEvent(selectionEvent);
            }
        });
    }

    /**
     * Constructs chart with given ChartType
     * 
     * @see #Chart()
     * @param type
     */
    public Chart(ChartType type) {
        this();
        configuration.setChart(new ChartModel(type));
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
            configuration.setDataSeriesEventListener(dataSeriesEventListener);
        }
    }

    @Override
    protected ChartState getState() {
        return (ChartState) super.getState();
    }

    private String getChartConfig() {
        if (jsonConfig != null) {
            return jsonConfig;
        }
        return configuration.toString();
    }

    /**
     * Draws chart with given configuration as a starting point. The
     * configuration is given as "native Highcarts config" in JSON.
     * <p>
     * Javascript is now allowed in the configuration. An exception is formatter
     * functions. Those must be given with special "_fn_" prefix to property
     * name. Client side will evaluate these into functions, otherwise the
     * configuration is evaluated safely with JSON parser.
     * <p>
     * Note, that if further modifications are done to configuration, the method
     * must be called again to redraw the UI.
     * 
     * @param jsonConfig
     *            the chart configuration as json string
     */
    public void drawChart(String jsonConfig) {
        this.jsonConfig = jsonConfig;
        configuration.setDataSeriesEventListener(null);
        configuration = null;
        stateDirty = true;
    }

    /**
     * @see #drawChart(String)
     * @return
     */
    public String getJsonConfig() {
        return jsonConfig;
    }

    /**
     * @return the chart configuration that is used for this chart component
     * @see #drawChart(Configuration)
     */
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Draws chart with given configuration as a starting point.
     * <p>
     * Note, that if further modifications are done directly to the
     * configuration, the method must be called again to redraw the UI.
     * 
     * <p>
     * If developers wishes to make more efficient "partial updates" to chart,
     * one should use mutator methods listed below.
     * 
     * @see #addPoint(Number, Number, Integer)
     * @see #removePoint(Number, Number)
     * @see #updatePoint(int, int, Number)
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
     * Completely redraw the chart based on its current config state.
     * 
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

    /**
     * Add chart click listener for listening clicks on the chart area
     * 
     * @param listener
     */
    public void addChartClickListener(ChartClickListener listener) {
        this.addListener(HighchartWidget.CHART_CLICK_EVENT_ID,
                ChartClickEvent.class, listener, chartClickMethod);
    }

    /**
     * @see #addChartClickListener(ChartClickListener)
     * @param listener
     */
    public void removeChartClickListener(ChartClickListener listener) {
        this.removeListener(HighchartWidget.CHART_CLICK_EVENT_ID,
                ChartClickEvent.class, listener);
    }

    /**
     * Add a click listener for listening clicks on the chart points
     * 
     * @param listener
     */
    public void addPointClickListener(PointClickListener listener) {
        this.addListener(HighchartWidget.POINT_CLICK_EVENT_ID,
                PointClickEvent.class, listener, pointClickMethod);
    }

    /**
     * @see #addPointClickListener(PointClickListener)
     * @param listener
     */
    public void removePointClickListener(ChartClickListener listener) {
        this.removeListener(HighchartWidget.POINT_CLICK_EVENT_ID,
                PointClickEvent.class, listener);
    }

    /**
     * Add a click listener for listening clicks on the column chart's columns
     * 
     * @param listener
     */
    public void addColumnClickListener(PointClickListener listener) {
        this.addListener(HighchartWidget.COLUMN_CLICK_EVENT_ID,
                PointClickEvent.class, listener, pointClickMethod);
    }

    /**
     * @see #addColumnClickListener(PointClickListener)
     * @param listener
     */
    public void removeColumnClickListener(ChartClickListener listener) {
        this.removeListener(HighchartWidget.COLUMN_CLICK_EVENT_ID,
                PointClickEvent.class, listener);
    }

    /**
     * Add a chart drag-selection listener<br />
     * <br />
     * 
     * Note, if a chart selection listener is set, default action for selection
     * is prevented. Most commonly this means that client side zoom don't work
     * and user is expected to set zoom or what ever wished in the listener.
     * 
     * @param listener
     */
    public void addChartSelectionListener(ChartSelectionListener listener) {
        this.addListener(HighchartWidget.CHART_SELECTION_EVENT_ID,
                ChartSelectionEvent.class, listener, chartSelectionMethod);
    }

    /**
     * @see #addChartSelectionListener(ChartSelectionListener)
     * @param listener
     */
    public void removeChartSelectionListener(ChartSelectionListener listener) {
        this.removeListener(HighchartWidget.CHART_SELECTION_EVENT_ID,
                ChartSelectionEvent.class, listener);
    }

    /**
     * Set client side chart rendering parameters
     * 
     * @param redrawAfterUpdate
     *            Defaults to true. Whether to redraw the chart after the point
     *            is added/removed/altered. When adding more than one point, it
     *            is highly recommended that the redraw option be set to false,
     *            and instead chart.redraw() is explicitly called after the
     *            adding of points is finished.
     * @param shiftAfterUpdate
     *            Defaults to false. When shift is true, one point is shifted
     *            off the start of the series as one is appended to the end. Use
     *            this option for live charts monitoring a value over time.
     * @param animationAfterUpdate
     *            Defaults to true. When true, the graph will be animated with
     *            default animation options. The animation can also be a
     *            configuration object with properties duration and easing. <br />
     *            <b>Only boolean value is currently supported by Vaadin</b>
     * 
     * <br />
     * <br />
     *            TODO: Add animation easing support
     */
    public void setClientSideRenderingParams(boolean redrawAfterUpdate,
            boolean shiftAfterUpdate, boolean animationAfterUpdate) {
        getRpcProxy(ChartClientRpc.class).setRedrawAfterUpdate(
                redrawAfterUpdate);
        getRpcProxy(ChartClientRpc.class).setShiftAfterUpdate(
                shiftAfterUpdate);
        getRpcProxy(ChartClientRpc.class).setAnimationAfterUpdate(
                animationAfterUpdate);
    }

    /**
     * Listens to events on the data series attached to the chart and redraws as
     * necessary.
     */
    private final DataSeriesEventListener dataSeriesEventListener = new DataSeriesEventListener() {
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
                                    event.getSeries()));
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

    };
}
