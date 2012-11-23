package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Client side ChartSelectionEvent
 */
public class ChartSelectionEvent extends JavaScriptObject {
    protected ChartSelectionEvent() {
    }

    public native final double getSelectionStart()
    /*-{
        return this.xAxis[0].min;
    }-*/;

    public native final double getSelectionEnd()
    /*-{
        return this.xAxis[0].max;
    }-*/;

    public native final void preventDefault()
    /*-{
        this.preventDefault();
    }-*/;
}
