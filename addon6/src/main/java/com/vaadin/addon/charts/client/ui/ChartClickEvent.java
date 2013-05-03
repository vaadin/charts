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
 * Client side ChartClickEvent
 */
public class ChartClickEvent extends JavaScriptObject {
    protected ChartClickEvent() {
    }

    public native final double getX()
    /*-{
        return this.xAxis[0].value;
    }-*/;

    public native final double getY()
    /*-{
        return this.yAxis[0].value;
    }-*/;
}
