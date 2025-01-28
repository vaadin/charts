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

/**
 * Abstract class with common attributes for click events
 *
 */
public class AbstractClickEvent extends JavaScriptObject {
    protected AbstractClickEvent() {
    }

    public native final boolean isAltKey()
    /*-{
        return this.altKey;
    }-*/;

    public native final boolean isCtrlKey()
    /*-{
        return this.ctrlKey;
    }-*/;

    public native final boolean isShiftKey()
    /*-{
        return this.shiftKey;
    }-*/;

    public native final boolean isMetaKey()
    /*-{
        return this.metaKey;
    }-*/;

    public native final int getButton()
    /*-{
        return this.button;
    }-*/;

    public native final int getChartX()
    /*-{
        if (this.chartX) {
          return this.chartX;
        } else {
          return 0;
        }
    }-*/;

    public native final int getChartY()
    /*-{
        if (this.chartY) {
          return this.chartY;
        } else {
          return 0;
        }
    }-*/;

}
