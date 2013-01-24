package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

public class HighchartPoint extends JavaScriptObject {

    protected HighchartPoint() {
    }
    
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

}
