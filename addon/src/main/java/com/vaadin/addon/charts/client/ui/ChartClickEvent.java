package com.vaadin.addon.charts.client.ui;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
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

import com.google.gwt.core.client.JsArray;

/**
 * Client side ChartClickEvent
 */
public class ChartClickEvent extends AbstractClickEvent {
    protected ChartClickEvent() {
    }

    public native final JsArray<ValueAxisPair> getXPairs()
    /*-{
        return this.xAxis;
    }-*/;

    public native final JsArray<ValueAxisPair> getYPairs()
    /*-{
        return this.yAxis;
    }-*/;

}
