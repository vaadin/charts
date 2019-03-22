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

public class ChartDrilldownEvent extends JavaScriptObject {
    protected ChartDrilldownEvent() {
    }

    public native final boolean isCategory()
    /*-{
        return this.points && this.points.length > 1;
    }-*/;

    public native final boolean hasDrilldownSeries()
    /*-{
        return this.seriesOptions;
    }-*/;

    public native final HighchartPoint getPoint()
    /*-{
        return this.point;
    }-*/;

}
