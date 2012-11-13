package com.vaadin.addon.charts.client.ui;

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

    public native final String getSeriesName()
    /*-{
        return this.point.series.name;
    }-*/;
}
