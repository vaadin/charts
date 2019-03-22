package com.vaadin.addon.charts.client.ui;

/*-
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2019 Vaadin Ltd
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
