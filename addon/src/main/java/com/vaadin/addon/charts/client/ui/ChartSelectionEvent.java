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

/**
 * Client side ChartSelectionEvent
 */
public class ChartSelectionEvent extends JavaScriptObject {
    protected ChartSelectionEvent() {
    }

    public native final double getSelectionStart()
    /*-{
        return this.xAxis[0].min;
    }-*/;

    public native final double getSelectionEnd()
    /*-{
        return this.xAxis[0].max;
    }-*/;
    public native final double getValueStart()
    /*-{
        return this.yAxis[0].min;
    }-*/;
    public native final double getValueEnd()
    /*-{
        return this.yAxis[0].max;
    }-*/;

    public native final void preventDefault()
    /*-{
        this.preventDefault();
    }-*/;
}
