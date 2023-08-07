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

public class PointClickEvent extends AbstractClickEvent {
    protected PointClickEvent() {
    }

    public native final double getX()
    /*-{
        if(this.point.x){
          return this.point.x;
        } else{
          return 0;
        }
    }-*/;

    public native final double getY()
    /*-{
        if(this.point.y){
          return this.point.y;
        } else{
          return 0;
        }
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
