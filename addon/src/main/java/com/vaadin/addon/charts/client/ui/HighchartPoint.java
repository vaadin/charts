package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

public class HighchartPoint extends JavaScriptObject {

    protected HighchartPoint() {
    }
    
    public native final HighchartSeries getSeries()
    /*-{
        return this.series;
    }-*/;

}
