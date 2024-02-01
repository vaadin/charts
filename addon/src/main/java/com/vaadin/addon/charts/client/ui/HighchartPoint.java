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
package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

public class HighchartPoint extends JavaScriptObject {

    protected HighchartPoint() {
    }

    public native final String getId()
    /*-{
        return this.id;
    }-*/;

    public native final int getIndex()
    /*-{
        return this.index;
    }-*/;

    public native final HighchartSeries getSeries()
    /*-{
        return this.series;
    }-*/;

    public native final void update(String json)
    /*-{
        var newPointData = $wnd.eval('('+json+')');
        this.update(newPointData);
    }-*/;

    public native final void update(double newValue)
    /*-{
        this.update(newValue);
    }-*/;

    public native final void slice(boolean sliced, boolean redraw,
            boolean animation)
    /*-{
        this.slice(sliced, redraw, animation);
    }-*/;

}
