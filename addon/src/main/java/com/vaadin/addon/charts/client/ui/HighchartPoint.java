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

public class HighchartPoint extends JavaScriptObject {

    protected HighchartPoint() {
    }

    public native final String getId()
    /*-{
        return this.id;
    }-*/;

    public native final int getIndex()
    /*-{
        return this.index;
    }-*/;

    public native final HighchartSeries getSeries()
    /*-{
        return this.series;
    }-*/;

    public native final void update(String json)
    /*-{
        var newPointData = $wnd.eval('('+json+')');
        this.update(newPointData);
    }-*/;

    public native final void update(double newValue)
    /*-{
        this.update(newValue);
    }-*/;

    public native final void slice(boolean sliced, boolean redraw,
            boolean animation)
    /*-{
        this.slice(sliced, redraw, animation);
    }-*/;

}
