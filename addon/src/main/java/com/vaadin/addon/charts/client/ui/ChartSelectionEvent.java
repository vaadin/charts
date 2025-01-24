/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Client side ChartSelectionEvent
 * 
 * xAxis and yAxis will be available depending on the zoomType that was
 * configured for the chart.
 * e.g. zoomType = 'x' will have xAxis defined and yAxis undefined
 */
public class ChartSelectionEvent extends JavaScriptObject {
    protected ChartSelectionEvent() {
    }

    public native final double getSelectionStart()
    /*-{
        if(this.xAxis && this.xAxis[0]){
            return this.xAxis[0].min;
        }else{
            return 0;
        }
    }-*/;

    public native final double getSelectionEnd()
    /*-{
        if(this.xAxis && this.xAxis[0]){
            return this.xAxis[0].max;
        }else{
            return 0;
        }
    }-*/;

    public native final double getValueStart()
    /*-{
        if(this.yAxis && this.yAxis[0]){
            return this.yAxis[0].min;
        }else{
            return 0;
        }
    }-*/;

    public native final double getValueEnd()
    /*-{
        if(this.yAxis && this.yAxis[0]){
            return this.yAxis[0].max;
        }else{
            return 0;
        }
    }-*/;

    public native final void preventDefault()
    /*-{
        this.preventDefault();
    }-*/;
}
