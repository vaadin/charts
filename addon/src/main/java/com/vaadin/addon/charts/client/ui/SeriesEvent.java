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
import com.google.gwt.dom.client.NativeEvent;

public class SeriesEvent extends JavaScriptObject {

    protected SeriesEvent() {

    }

    public native final void preventDefault()
    /*-{
        this.preventDefault();
    }-*/;

    public native final HighchartSeries getSeries()
    /*-{
         return this.target.series || this.target;
    }-*/;

    /**
     * Returns the series item index for the legend item that was clicked if
     * possible.
     * 
     * @return Series item index.
     */
    public native final int getSeriesItemIndex()
    /*-{   
        if(typeof this.target.x === "undefined") {
          if(typeof this.target.index === "undefined") {
            return -1;
          }
          return this.target.index;
        }
        return this.target.x;
    }-*/;

    /**
     * Returns the native (actual event) that triggered this SeriesEvent
     * @return the native browser event for this SeriesEvent
     */
    public native final NativeEvent getBrowserEvent()
    /*-{
        return this.browserEvent;
    }-*/;

}
