package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.vaadin.addon.charts.Chart;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@SuppressWarnings("serial")
@Connect(Chart.class)
public class HighchartConnector extends AbstractComponentConnector {

    HighchartRpc rpc = RpcProxy.create(HighchartRpc.class, this);

    public HighchartConnector() {
        registerRpc(HighchartClientRpc.class, new HighchartClientRpc() {
            @Override
            public void addPoint(double x, double y, int seriesIndex) {
                getWidget().addPoint(x, y, seriesIndex);
            }

            @Override
            public void removePoint(double x, double y) {
                getWidget().removePoint(x, y);
            }

            @Override
            public void updatePointValue(int seriesIndex, int pointIndex,
                    double newValue) {
                getWidget().updatePointValue(seriesIndex, pointIndex, newValue);
            }

            @Override
            public void setRedrawAfterUpdate(boolean redraw) {
                getWidget().setRedrawAfterUpdate(redraw);
            }

            @Override
            public void setAnimationAfterUpdate(boolean animationAfterUpdate) {
                getWidget().setAnimationAfterUpdate(animationAfterUpdate);

            }

            @Override
            public void setShiftAfterUpdate(boolean shiftAfterUpdate) {
                getWidget().setShiftAfterUpdate(shiftAfterUpdate);
            }
        });
    }

    @Override
    public VHighchart getWidget() {
        return (VHighchart) super.getWidget();
    }

    @Override
    public HighchartComponentState getState() {
        return (HighchartComponentState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        final HighchartConfig cfg = HighchartConfig
                .createFromServerSideString(getState().jsonState);
        if (getState().registeredEventListeners != null
                && getState().registeredEventListeners
                        .contains(VHighchart.CHART_CLICK_EVENT_ID)) {
            cfg.setClickHandler(new ChartClickHandler() {
                @Override
                public void onClick(ChartClickEvent event) {
                    rpc.onChartClick(event.getX(), event.getY());
                }
            });
        }
        if (getState().registeredEventListeners != null
                && getState().registeredEventListeners
                        .contains(VHighchart.POINT_CLICK_EVENT_ID)) {
            cfg.setSeriesPointClickHandler(new PointClickHandler() {

                @Override
                public void onClick(PointClickEvent event) {
                    rpc.onPointClick(event.getX(), event.getY(),
                            event.getSeriesName(), event.getCategory());
                }
            });
        }

        if (getState().registeredEventListeners != null
                && getState().registeredEventListeners
                        .contains(VHighchart.COLUMN_CLICK_EVENT_ID)) {
            cfg.setSeriesPointClickHandler(new PointClickHandler() {

                @Override
                public void onClick(PointClickEvent event) {
                    rpc.onPointClick(event.getX(), event.getY(),
                            event.getSeriesName(), event.getCategory());
                }
            });
        }

        if (getState().registeredEventListeners != null
                && getState().registeredEventListeners
                        .contains(VHighchart.CHART_SELECTION_EVENT_ID)) {
            cfg.setChartSelectionHandler(new ChartSelectionHandler() {

                @Override
                public void onSelection(ChartSelectionEvent event) {
                    rpc.onSelection(event.getSelectionStart(),
                            event.getSelectionEnd());
                    event.preventDefault();
                }
            });
        }

        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                getWidget().init(cfg);
            }
        });
    }
}
