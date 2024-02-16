/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

public class SetExtremesEvent extends JavaScriptObject {

    protected SetExtremesEvent() {

    }

    public native final HighchartAxis getAxis()
    /*-{
         return this.target;
    }-*/;

    public native final double getMin()
    /*-{
        if (this.min) {
          return this.min;
        } else {
          return 0;
        }
    }-*/;

    public native final double getMax()
    /*-{
        if (this.max) {
          return this.max;
        } else {
          return 0;
        }
    }-*/;

}
