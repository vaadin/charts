package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

public class ChartClickEvent extends JavaScriptObject {
    protected ChartClickEvent() {
    }

    public native final double getX()
    /*-{
        return this.xAxis[0].value;
    }-*/;

    public native final double getY()
    /*-{
        return this.yAxis[0].value;
    }-*/;
}
