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

public class ChartDrilldownEvent extends JavaScriptObject {
    protected ChartDrilldownEvent() {
    }

    public native final boolean isCategory()
    /*-{
        return this.points && this.points.length > 1;
    }-*/;

    public native final boolean hasDrilldownSeries()
    /*-{
        return this.seriesOptions;
    }-*/;

    public native final HighchartPoint getPoint()
    /*-{
        return this.point;
    }-*/;

}
