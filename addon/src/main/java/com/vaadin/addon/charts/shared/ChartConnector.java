/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.shared;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Timer;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.client.ui.AfterSetExtremeHandler;
import com.vaadin.addon.charts.client.ui.ChartClickEvent;
import com.vaadin.addon.charts.client.ui.ChartClickHandler;
import com.vaadin.addon.charts.client.ui.ChartDrilldownEvent;
import com.vaadin.addon.charts.client.ui.ChartDrilldownHandler;
import com.vaadin.addon.charts.client.ui.ChartSelectionEvent;
import com.vaadin.addon.charts.client.ui.ChartSelectionHandler;
import com.vaadin.addon.charts.client.ui.CheckboxClickEvent;
import com.vaadin.addon.charts.client.ui.CheckboxClickHandler;
import com.vaadin.addon.charts.client.ui.DrilldownEventDetailsBuilder;
import com.vaadin.addon.charts.client.ui.HighchartConfig;
import com.vaadin.addon.charts.client.ui.HighchartPoint;
import com.vaadin.addon.charts.client.ui.HighchartSeries;
import com.vaadin.addon.charts.client.ui.HighchartWidget;
import com.vaadin.addon.charts.client.ui.LegendItemClickHandler;
import com.vaadin.addon.charts.client.ui.MouseEventDetailsBuilder;
import com.vaadin.addon.charts.client.ui.PointClickEvent;
import com.vaadin.addon.charts.client.ui.PointClickHandler;
import com.vaadin.addon.charts.client.ui.PointEvent;
import com.vaadin.addon.charts.client.ui.PointSelectHandler;
import com.vaadin.addon.charts.client.ui.PointUnselectHandler;
import com.vaadin.addon.charts.client.ui.SeriesEvent;
import com.vaadin.addon.charts.client.ui.SeriesHideHandler;
import com.vaadin.addon.charts.client.ui.SeriesShowHandler;
import com.vaadin.addon.charts.client.ui.SetExtremesEvent;
import com.vaadin.client.BrowserInfo;
import com.vaadin.client.DeferredWorker;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.client.ui.VWindow;
import com.vaadin.client.ui.layout.ElementResizeEvent;
import com.vaadin.client.ui.layout.ElementResizeListener;
import com.vaadin.client.ui.window.WindowConnector;
import com.vaadin.shared.ui.Connect;

@SuppressWarnings("serial")
@Connect(Chart.class)
public class ChartConnector extends AbstractComponentConnector implements DeferredWorker {

    ChartServerRpc rpc = RpcProxy.create(ChartServerRpc.class, this);
    protected ElementResizeListener resizeListener;
    public static final String POINT_CLICK_EVENT_ID = "pcl";
    public static final String LEGENDITEM_CLICK_EVENT_ID = "lic";
    public static final String CHART_SELECTION_EVENT_ID = "cs";
    public static final String CHART_CLICK_EVENT_ID = "cl";
    public static final String CHART_DRILLUP_EVENT_ID = "du";
    public static final String CHECKBOX_CLICK_EVENT_ID = "cbc";
    public static final String HIDE_SERIES_EVENT_ID = "hs";
    public static final String SHOW_SERIES_EVENT_ID = "ss";
    public static final String X_AXES_EXTREMES_CHANGE_EVENT_ID = "xae";
    public static final String Y_AXES_EXTREMES_CHANGE_EVENT_ID = "yae";
    public static final String POINT_SELECT_EVENT_ID = "pse";
    public static final String POINT_UNSELECT_EVENT_ID = "pus";

    private ChartsRenderingObserver chartsRenderingObserver;

    public ChartConnector() {
        chartsRenderingObserver = new ChartsRenderingObserver();

        registerRpc(ChartClientRpc.class, new ChartClientRpc() {
            @Override
            public void addPoint(final String pointJson, final int seriesIndex,
                    final boolean shift) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {

                        getWidget().addPoint(pointJson, seriesIndex, shift);
                    }
                });
            }

            @Override
            public void removePoint(final int pointIndex, final int seriesIndex) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {

                        getWidget().removePoint(pointIndex, seriesIndex);
                    }
                });
            }

            @Override
            public void updatePointValue(final int seriesIndex,
                    final int pointIndex, final double newValue) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {

                        getWidget().updatePointValue(seriesIndex, pointIndex,
                                newValue);
                    }
                });
            }

            @Override
            public void setSeriesEnabled(final int seriesIndex,
                    final boolean enabled) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {

                        getWidget().setSeriesEnabled(seriesIndex, enabled);
                    }
                });
            }

            @Override
            public void setAnimationEnabled(final boolean animation) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {

                        getWidget().setAnimation(animation);
                    }
                });
            }

            @Override
            public void updatePoint(final int seriesIndex,
                    final int pointIndex, final String json) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {

                        getWidget().updatePointValue(seriesIndex, pointIndex,
                                json);
                    }
                });
            }

            @Override
            public void updateSeries(int seriesIndex, String seriesJson) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        getWidget().updateSeries(seriesIndex, seriesJson);
                    }
                });
            }

            @Override
            public void rescaleAxis(final int axisCategory,
                    final int axisIndex, final double minimum,
                    final double maximum, final boolean redraw,
                    final boolean animate) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {

                        switch (axisCategory) {
                        case X_AXIS:
                            getWidget().updatexAxis(axisIndex, minimum,
                                    maximum, redraw, animate);
                            break;
                        case Y_AXIS:
                            getWidget().updateyAxis(axisIndex, minimum,
                                    maximum, redraw, animate);
                            break;
                        case Z_AXIS:
                            getWidget().updatezAxis(axisIndex, minimum,
                                    maximum, redraw, animate);
                            break;
                        case COLOR_AXIS:
                            getWidget().updateColorAxis(axisIndex, minimum,
                                    maximum, redraw, animate);
                            break;
                        default:
                            getWidget().updateyAxis(axisIndex, minimum,
                                    maximum, redraw, animate);
                            break;
                        }
                    }
                });
            }

            @Override
            public void sliceItem(final int seriesIndex, final int pointIndex,
                    final boolean sliced, final boolean redraw,
                    final boolean animation) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {

                        getWidget().slicePoint(seriesIndex, pointIndex, sliced,
                                redraw, animation);
                    }
                });
            }

            @Override
            public void resetZoom(final boolean redraw, final boolean animate) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        getWidget().resetZoom(redraw, animate);
                    }
                });
            }

            @Override
            public void addDrilldown(final String series,
                    final int seriesIndex, final int pointIndex) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        getWidget().addDrilldown(series, seriesIndex,
                                pointIndex);
                    }
                });
            }

        });
    }

    @Override
    public HighchartWidget getWidget() {
        return (HighchartWidget) super.getWidget();
    }

    @Override
    public ChartState getState() {
        return (ChartState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        final HighchartConfig cfg = HighchartConfig.createFromServerSideString(
                getState().confState, getState().jsonState);

        chartsRenderingObserver.startRendering();
        cfg.setChartsRenderingObserver(chartsRenderingObserver);

        if (hasEventListener(CHART_CLICK_EVENT_ID)) {
            cfg.setClickHandler(new ChartClickHandler() {
                @Override
                public void onClick(ChartClickEvent event) {
                    MouseEventDetails details = MouseEventDetailsBuilder
                            .buildMouseEventDetails(event, getWidget());
                    rpc.onChartClick(details);
                }
            });
        }
        cfg.setDrilldownHandler(new ChartDrilldownHandler() {

            @Override
            public void onDrilldown(ChartDrilldownEvent event) {
                if (!event.isCategory() && !event.hasDrilldownSeries()) {
                    DrilldownEventDetails details = DrilldownEventDetailsBuilder
                            .buildDrilldownEventDetails(event, getWidget());

                    rpc.onChartDrilldown(details);
                }
            }

            @Override
            public void onDrillup() {
                rpc.onChartDrillup();
            }
        });
        if (hasEventListener(POINT_CLICK_EVENT_ID)) {
            cfg.setSeriesPointClickHandler(new PointClickHandler() {

                @Override
                public void onClick(PointClickEvent event) {
                    MouseEventDetails details = MouseEventDetailsBuilder
                            .buildMouseEventDetails(event, getWidget());

                    HighchartPoint point = event.getPoint();
                    HighchartSeries series = point.getSeries();
                    int seriesIndex = getWidget().getSeriesIndex(series);
                    int pointIndex = series.indexOf(point);

                    rpc.onPointClick(details, seriesIndex, event.getCategory(),
                            pointIndex);
                }
            });
        }

        if (hasEventListener(CHART_SELECTION_EVENT_ID)) {
            cfg.setChartSelectionHandler(new ChartSelectionHandler() {

                @Override
                public void onSelection(ChartSelectionEvent event) {
                    rpc.onSelection(event.getSelectionStart(),
                            event.getSelectionEnd(), event.getValueStart(),
                            event.getValueEnd());
                    event.preventDefault();
                }
            });
        }

        // The legendItemClickHandler is always set so that series visibility
        // toggling can be disabled
        cfg.setLegendItemClickHandler(new LegendItemClickHandler() {

            @Override
            public boolean onClick(SeriesEvent event) {
                if (hasEventListener(LEGENDITEM_CLICK_EVENT_ID)) {
                    int seriesIndex = getWidget().getSeriesIndex(
                            event.getSeries());

                    MouseEventDetails mouseEventDetails = MouseEventDetailsBuilder.buildMouseEventDetails(event.getBrowserEvent(), getWidget());
                    rpc.onLegendItemClick(seriesIndex,
                        event.getSeriesItemIndex(), mouseEventDetails);
                }
                return !getState().seriesVisibilityTogglingDisabled;
            }
        });

        if (hasEventListener(CHECKBOX_CLICK_EVENT_ID)) {
            cfg.setCheckboxClickHandler(new CheckboxClickHandler() {
                @Override public void onClick(CheckboxClickEvent event) {
                    int seriesIndex = getWidget().getSeriesIndex(
                        event.getSeries());
                    rpc.onCheckboxClick(event.isChecked(), seriesIndex, event.getSeriesItemIndex());
                }
            });
        }

        if (hasEventListener(HIDE_SERIES_EVENT_ID)) {
            cfg.setSeriesHideHandler(new SeriesHideHandler() {
                @Override
                public void onHide(SeriesEvent event) {
                    int seriesIndex = getWidget().getSeriesIndex(
                        event.getSeries());
                    rpc.onSeriesHide(seriesIndex, event.getSeriesItemIndex());
                }
            });
        }

        if (hasEventListener(SHOW_SERIES_EVENT_ID)) {
            cfg.setSeriesShowHandler(new SeriesShowHandler() {
                @Override
                public void onShow(SeriesEvent event) {
                    int seriesIndex = getWidget()
                        .getSeriesIndex(event.getSeries());
                    rpc.onSeriesShow(seriesIndex, event.getSeriesItemIndex());
                }
            });
        }

        if (hasEventListener(X_AXES_EXTREMES_CHANGE_EVENT_ID)) {
            cfg.setXAxesAfterSetExtremeHandler(new AfterSetExtremeHandler() {
                @Override
                public void afterSetExtreme(SetExtremesEvent event) {
                    int axisIndex = getWidget().getXAxisIndex(event.getAxis());
                    double min = event.getMin();
                    double max = event.getMax();
                    rpc.onXAxesExtremesChange(axisIndex, min, max);
                }
            });
        }

        if (hasEventListener(Y_AXES_EXTREMES_CHANGE_EVENT_ID)) {
            cfg.setYAxesAfterSetExtremeHandler(new AfterSetExtremeHandler() {
                @Override
                public void afterSetExtreme(SetExtremesEvent event) {
                    int axisIndex = getWidget().getYAxisIndex(event.getAxis());
                    double min = event.getMin();
                    double max = event.getMax();
                    rpc.onYAxesExtremesChange(axisIndex, min, max);
                }
            });
        }

        if (hasEventListener(POINT_SELECT_EVENT_ID)) {
            cfg.setPointSelectHandler(new PointSelectHandler(){
                @Override
                public void onSelect(PointEvent event) {
                    HighchartPoint point = event.getPoint();
                    HighchartSeries series = point.getSeries();
                    int seriesIndex = getWidget().getSeriesIndex(series);
                    int pointIndex = series.indexOf(point);

                    rpc.onPointSelect(seriesIndex, event.getCategory(),
                        pointIndex);
                }
            });
        }

        if (hasEventListener(POINT_UNSELECT_EVENT_ID)) {
            cfg.setPointUnselectHandler(new PointUnselectHandler() {

                @Override
                public void onUnselect(PointEvent event) {
                    HighchartPoint point = event.getPoint();
                    HighchartSeries series = point.getSeries();
                    int seriesIndex = getWidget().getSeriesIndex(series);
                    int pointIndex = series.indexOf(point);

                    rpc.onPointUnselect(seriesIndex, event.getCategory(),
                        pointIndex);
                }

            });
        }

        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                                             @Override
                                             public void execute() {
                                                 getWidget().init(cfg, getState().timeline);
                                                 int numberOfSeries = getWidget().getNumberOfSeries();
                                                 chartsRenderingObserver.setNumberOfSeries(
                                                     numberOfSeries);
                                                 // Add resize listener lazily here. If done in init like in
                                                 // examples it will be called
                                                 // way too early, like before the widget is not even rendered
                                                 // yet
                                                 if (resizeListener == null) {
                                                     resizeListener = new ElementResizeListener() {

                                                         @Override
                                                         public void onElementResize(ElementResizeEvent e) {
                                                             getWidget().updateSize();
                                                         }
                                                     };

                                                     getLayoutManager().addElementResizeListener(
                                                         getWidget().getElement(), resizeListener);
                                                 }

                                                 if (BrowserInfo.get().isIE()) {
                                                     // Workaround for Vaadin bug in IE (?), scrollbars...
                                                     ServerConnector parent2 = getParent();
                                                     if (parent2 instanceof WindowConnector) {
                                                         final WindowConnector w = (WindowConnector) parent2;
                                                         new Timer() {

                                                             @Override
                                                             public void run() {
                                                                 VWindow widget2 = w.getWidget();
                                                                 widget2.setWidth(widget2.getOffsetWidth()
                                                                         + "px");
                                                                 widget2.setHeight(widget2.getOffsetHeight()
                                                                         + "px");
                                                             }
                                                         }.schedule(10);
                                                     }
                                                 }
                                             }


        });
    }

    @Override
    public void onUnregister() {
        getWidget().destroy();
        if (resizeListener != null) {
            getLayoutManager().removeElementResizeListener(
                    getWidget().getElement(), resizeListener);
        }
        super.onUnregister();
    }

    public static class ChartsRenderingObserver {

        private int numberOfSeries;
        private int numberOfRenderedSeries;
        private boolean loaded;
        private boolean seriesAndDataLabelsRendered;

        public void startRendering() {
            numberOfSeries = 0;
            numberOfRenderedSeries = 0;
            loaded = false;
            seriesAndDataLabelsRendered = false;
        }

        public void onLoad() {
            loaded = true;
        }


        public void onAfterSeriesAnimate() {
            ++numberOfRenderedSeries;
            if(numberOfRenderedSeries >= numberOfSeries) {
                // After the series are rendered, the datalabels are rendered
                // with 200ms animation duration. There is no event at the moment to catch
                // when rendering of datalabels are done, so we add 500ms delay
                // before assuming the series and data labels are rendered.
                Timer timer = new Timer() {
                    @Override
                    public void run() {
                        seriesAndDataLabelsRendered = true;
                    }
                };
                timer.schedule(500);
            }
        }

        public boolean isWorkPending() {
            return !loaded || !seriesAndDataLabelsRendered;
        }

        public void setNumberOfSeries(int numberOfSeries) {
            this.numberOfSeries = numberOfSeries;
            if (numberOfSeries == 0) {
                seriesAndDataLabelsRendered = true;
            }
        }
    }

    @Override
    public boolean isWorkPending() {
        return chartsRenderingObserver.isWorkPending();
    }
}
