/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

public class PointEvent extends JavaScriptObject {

    protected PointEvent() {

    }

    public native final String getCategory()
    /*-{
        return "" + this.target.category;
    }-*/;

    public native final String getName()
    /*-{
        return this.target.name;
    }-*/;

    public native final String getId()
    /*-{
        return this.target.id;
    }-*/;

    public native final HighchartPoint getPoint()
    /*-{
        return this.target;
    }-*/;

    public native final HighchartAxis getXAxis()
    /*-{
        return this.target.series.xAxis;
    }-*/;

    public native final HighchartAxis getYAxis()
    /*-{
        return this.target.series.yAxis;
    }-*/;
}
