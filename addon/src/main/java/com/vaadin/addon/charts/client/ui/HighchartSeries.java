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
import com.google.gwt.core.client.JsArray;

public class HighchartSeries extends JavaScriptObject {

    protected HighchartSeries() {
    }

    public native final String getName()
    /*-{
        return this.name;
    }-*/;

    public final int indexOf(HighchartPoint point) {
        JsArray<HighchartPoint> data = getData();
        for (int i = 0; i < data.length(); i++) {
            HighchartPoint p = data.get(i);
            if (p == point) {
                return i;
            }
        }
        return -1;
    }

    public final native JsArray<HighchartPoint> getData()
    /*-{
        return this.data;
    }-*/;

}
