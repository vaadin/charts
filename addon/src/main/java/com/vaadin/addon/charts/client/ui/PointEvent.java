package com.vaadin.addon.charts.client.ui;

/*
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

import com.google.gwt.core.client.JavaScriptObject;


public class PointEvent extends JavaScriptObject {

    protected PointEvent() {

    }

    public native final double getX()
    /*-{
        return this.target.x;
    }-*/;

    public native final double getY()
    /*-{
        return this.target.y;
    }-*/;

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
