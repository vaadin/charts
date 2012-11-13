package com.vaadin.addon.charts.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

public class VHighchart extends Widget {

    public static final String CHART_CLICK_EVENT_ID = "cl";
    public static final String CHART_SELECTION_EVENT_ID = "cs";
    public static final String POINT_CLICK_EVENT_ID = "pcl";
    public static final String COLUMN_CLICK_EVENT_ID = "ccl";

    private HighchartJsOverlay jsOverlay;

    private boolean redraw = true;
    private boolean shift = false;
    private boolean animation = true;

    public VHighchart() {
        setElement(Document.get().createDivElement());
        getElement().setInnerHTML("Loading chart...");
    }

    public void init(HighchartConfig config) {
        jsOverlay = config.renderTo(getElement());
    }

    public void addPoint(double x, double y, int seriesIndex) {
        jsOverlay.addPoint(x, y, seriesIndex, redraw, shift, animation);
    }

    public void removePoint(double x, double y) {
        jsOverlay.removePoint(x, y, 0.01);
    }

    public void updatePointValue(int seriesIndex, int pointIndex,
            double newValue) {
        jsOverlay.updatePointValue(seriesIndex, pointIndex, newValue, redraw,
                animation);

    }

    public void removePoint(double x, double y, int seriesIndex) {
        jsOverlay.removePoint(x, y, seriesIndex, 0.01);
    }

    public void setRedrawAfterUpdate(boolean redraw) {
        this.redraw = redraw;
    }

    public void setAnimationAfterUpdate(boolean animationAfterUpdate) {
        animation = animationAfterUpdate;
    }

    public void setShiftAfterUpdate(boolean shift) {
        this.shift = shift;
    }
}
