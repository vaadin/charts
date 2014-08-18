package com.vaadin.addon.charts.client.ui;

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

import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.charts.client.HighchartsScriptLoader;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.VConsole;
import com.vaadin.terminal.gwt.client.ui.VLazyExecutor;

public class HighchartWidget extends Widget implements Paintable,
        ChartClickHandler, PointClickHandler, LegendItemClickHandler,
        ChartSelectionHandler {

    private HighchartJsOverlay jsOverlay;
    private ApplicationConnection client;
    private String paintableId;
    public static final String CHART_CLICK_EVENT_ID = "cl";
    public static final String CHART_SELECTION_EVENT_ID = "cs";
    public static final String LEGENDITEM_CLICK_EVENT_ID = "lic";
    public static final String POINT_CLICK_EVENT_ID = "pcl";

    public HighchartWidget() {
        HighchartsScriptLoader.ensureInjected();
        setElement(Document.get().createDivElement());
        getElement().setInnerHTML("Loading chart...");
    }

    public void init(HighchartConfig config) {
        HighchartJsOverlay old = jsOverlay;
        if (old != null) {
            old.destroy();
        }
        jsOverlay = config.renderTo(getElement());
    }

    public void addPoint(String pointJson, int seriesIndex, boolean shift) {
        jsOverlay.addPoint(pointJson, seriesIndex, true, shift);
    }

    public void updatePointValue(int seriesIndex, int pointIndex,
            double newValue) {
        HighchartSeries highchartSeries = jsOverlay.getSeries()
                .get(seriesIndex);
        HighchartPoint highchartPoint = highchartSeries.getData().get(
                pointIndex);
        highchartPoint.update(newValue);
    }

    public void updatePointValue(int seriesIndex, int pointIndex, String json) {
        HighchartSeries highchartSeries = jsOverlay.getSeries()
                .get(seriesIndex);
        HighchartPoint highchartPoint = highchartSeries.getData().get(
                pointIndex);
        highchartPoint.update(json);
    }

    public void removePoint(int pointIndex, int seriesIndex) {
        jsOverlay.removePoint(pointIndex, seriesIndex);
    }

    public void setSeriesEnabled(int seriesIndex, boolean enabled) {
        jsOverlay.setSeriesEnabled(seriesIndex, enabled);
    }

    public int getSeriesIndex(HighchartSeries series) {
        JsArray<HighchartSeries> serA = jsOverlay.getSeries();
        for (int i = 0; i < serA.length(); i++) {
            if (serA.get(i) == series) {
                return i;
            }
        }
        return -1;
    }

    public void setAnimation(boolean animation) {
        jsOverlay.setAnimation(animation);
    }

    public void updateSize() {
        if (jsOverlay != null) {
            jsOverlay.setSize(getOffsetWidth(), getOffsetHeight(), false, true);
        }
    }

    public void destroy() {
        jsOverlay.destroy();
    }

    @Override
    public void updateFromUIDL(UIDL mainUidl, ApplicationConnection client) {
        if (this.client == null) {
            this.client = client;
            this.paintableId = mainUidl.getId();
        }
        if (!client.updateComponent(this, mainUidl, true)) {
            if (mainUidl.hasAttribute("confState")
                    || mainUidl.hasAttribute("jsonState")) {
                VConsole.error("full paint");
                final HighchartConfig cfg = HighchartConfig
                        .createFromServerSideString(
                                mainUidl.getStringAttribute("confState"),
                                mainUidl.getStringAttribute("jsonState"));

                if (client.hasEventListeners(this, CHART_CLICK_EVENT_ID)) {
                    cfg.setClickHandler((ChartClickHandler) this);
                }
                if (client.hasEventListeners(this, POINT_CLICK_EVENT_ID)) {
                    cfg.setSeriesPointClickHandler((PointClickHandler) this);
                }
                if (client.hasEventListeners(this, LEGENDITEM_CLICK_EVENT_ID)) {
                    cfg.setLegendItemClickHandler(this);
                }
                if (client.hasEventListeners(this, CHART_SELECTION_EVENT_ID)) {
                    cfg.setChartSelectionHandler(this);
                }

                init(cfg);
            } else {
                int childCount = mainUidl.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    UIDL uidl = mainUidl.getChildUIDL(i);
                    // updates
                    if (uidl.hasAttribute("addedPoint")) {
                        String pointJson = uidl
                                .getStringAttribute("addedPoint");
                        int seriesIndex = uidl.getIntAttribute("seriesIndex");
                        boolean shift = uidl.getBooleanAttribute("shift");
                        addPoint(pointJson, seriesIndex, shift);
                    } else if (uidl.hasAttribute("removedPointIndex")) {
                        int seriesIndex = uidl.getIntAttribute("seriesIndex");
                        int pointIndex = uidl
                                .getIntAttribute("removedPointIndex");
                        removePoint(pointIndex, seriesIndex);
                    } else if (uidl.hasAttribute("updatedPoint")) {
                        String json = uidl.getStringAttribute("updatedPoint");
                        int seriesIndex = uidl.getIntAttribute("seriesIndex");
                        int pointIndex = uidl.getIntAttribute("pointIndex");
                        updatePointValue(seriesIndex, pointIndex, json);
                    } else if (uidl.hasAttribute("enabledSeriesIndex")) {
                        int seriesIndex = uidl
                                .getIntAttribute("enabledSeriesIndex");
                        boolean enabled = uidl.getBooleanAttribute("enabled");
                        setSeriesEnabled(seriesIndex, enabled);
                    } else if (uidl.hasAttribute("animationEnabled")) {
                        setAnimation(uidl
                                .getBooleanAttribute("animationEnabled"));
                    } else {
                        VConsole.error("Unhandled partial changes");
                    }
                }
            }
        }

    }

    @Override
    public void setWidth(String width) {
        super.setWidth(width);
        resize.trigger();
    }

    @Override
    public void setHeight(String height) {
        super.setHeight(height);
        resize.trigger();
    }

    VLazyExecutor resize = new VLazyExecutor(500, new ScheduledCommand() {
        @Override
        public void execute() {
            updateSize();
        }
    });

    VLazyExecutor cleanup = new VLazyExecutor(1000, new ScheduledCommand() {
        @Override
        public void execute() {
            if (!isAttached()) {
                destroy();
            }
        }
    });

    @Override
    protected void onDetach() {
        super.onDetach();
        cleanup.trigger();
    }

    @Override
    public void onClick(ChartClickEvent event) {
        client.updateVariable(paintableId, "clickX", event.getX(), false);
        client.updateVariable(paintableId, "clickY", event.getY(), true);
    }

    @Override
    public void onClick(PointClickEvent event) {
        client.updateVariable(paintableId, "pointClickIndex", event.getPoint()
                .getSeries().indexOf(event.getPoint()), false);
        client.updateVariable(paintableId, "pointClickX", event.getX(), false);
        client.updateVariable(paintableId, "pointClickY", event.getY(), false);
        client.updateVariable(paintableId, "pointClickSeriesIndex",
                getSeriesIndex(event.getPoint().getSeries()), false);
        client.updateVariable(paintableId, "pointClickCategory",
                event.getCategory(), true);
    }

    @Override
    public void onClick(LegendItemClickEvent event) {
        int seriesIndex = getSeriesIndex(event.getSeries());
        client.updateVariable(paintableId, "seriesItemIndex",
                event.getSeriesItemIndex(), false);
        client.updateVariable(paintableId, "legendClick", seriesIndex, true);
        event.preventDefault();
    }

    @Override
    public void onSelection(ChartSelectionEvent event) {
        client.updateVariable(paintableId, "selectionStart",
                event.getSelectionStart(), false);
        client.updateVariable(paintableId, "selectionValueStart",
                event.getValueStart(), false);
        client.updateVariable(paintableId, "selectionEnd",
                event.getSelectionEnd(), false);
        client.updateVariable(paintableId, "selectionValueEnd",
                event.getValueEnd(), true);
        event.preventDefault();
    }

}
