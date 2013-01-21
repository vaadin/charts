package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

public class HighchartSeries extends JavaScriptObject {

    protected HighchartSeries() {
    }
    
    public native final String getName()
    /*-{
        return this.name;
    }-*/;


}
