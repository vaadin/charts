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
}
