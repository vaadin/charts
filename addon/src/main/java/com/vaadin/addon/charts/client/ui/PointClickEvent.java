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

import com.google.gwt.core.client.JavaScriptObject;

public class PointClickEvent extends JavaScriptObject {
    protected PointClickEvent() {
    }

    public native final double getX()
    /*-{
         return this.point.x;
    }-*/;

    public native final double getY()
    /*-{
        return this.point.y;
    }-*/;

    public native final String getCategory()
    /*-{
        return "" + this.point.category;
    }-*/;

    public native final String getName()
    /*-{
        return this.point.name;
    }-*/;

    public native final String getId()
    /*-{
        return this.point.id;
    }-*/;

    public native final HighchartPoint getPoint() 
    /*-{
        return this.point;
    }-*/;
    
    public native final HighchartAxis getXAxis()
    /*-{
    	return this.point.series.xAxis;
    }-*/;
    
    public native final HighchartAxis getYAxis()
    /*-{
    	return this.point.series.yAxis;
    }-*/;

}
