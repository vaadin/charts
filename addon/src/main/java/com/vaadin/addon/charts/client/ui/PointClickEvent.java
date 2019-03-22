package com.vaadin.addon.charts.client.ui;

/*-
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2019 Vaadin Ltd
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
