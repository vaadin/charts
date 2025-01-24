/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

public class ValueAxisPair extends JavaScriptObject {
    protected ValueAxisPair() {
    }

    public native final double getValue()
    /*-{
        if (this.value) {
          return this.value;
        } else {
          return 0;
        }
    }-*/;

    public native final HighchartAxis getAxis()
    /*-{
        return this.axis;
    }-*/;
}
