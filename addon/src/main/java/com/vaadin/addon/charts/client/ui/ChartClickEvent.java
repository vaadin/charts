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
