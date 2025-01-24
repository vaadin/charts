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

public class HighchartAxis extends JavaScriptObject {

    protected HighchartAxis() {
    }

    public native final double toPixels(double axisValue,
            boolean paneCoordinates)
    /*-{
            try {
    	        return this.toPixels(axisValue, paneCoordinates);
            } catch (e) {
                return -1;
            }
    }-*/;

    public native final void setExtremes(double min, double max,
            boolean redraw, boolean animation)
    /*-{
        this.setExtremes(min, max, redraw, animation);
     }-*/;

    public native final void resetZoom(boolean redraw, boolean animation)
    /*-{
        this.setExtremes(null, null, redraw, animation);
     }-*/;
}
