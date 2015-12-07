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
import com.google.gwt.core.client.JsArray;

/**
 * JS overlay for Chart chart. Needed for dynamic modifications, e.g.
 * adding/removing points.
 */
public class HighchartJsOverlay extends JavaScriptObject {

    protected HighchartJsOverlay() {
    }

    /**
     * 
     * @param doubleAttribute
     */
    public native final void addPoint(String pointJson, int seriesIndex,
            boolean redraw, boolean shift)
    /*-{
        var newPointData = $wnd.eval('('+pointJson+')');
        this.series[seriesIndex].addPoint(newPointData,redraw,shift);
    }-*/;

    public native final void removePoint(int pointIndex, int seriesIndex)
    /*-{
        this.series[seriesIndex].data[pointIndex].remove();
    }-*/;

    public native final void setSeriesEnabled(int index, boolean enabled)
    /*-{
        var ser = this.series[index];
        if(enabled) {
            ser.show();
        } else {
            ser.hide();
        }
    }-*/;

    public final native JsArray<HighchartSeries> getSeries()
    /*-{
        return this.series;
    }-*/;

    public final native JsArray<HighchartAxis> getxAxes()
    /*-{
         return this.xAxis;
    }-*/;

    public final native JsArray<HighchartAxis> getyAxes()
    /*-{
         return this.yAxis;
    }-*/;

    public final native JsArray<HighchartAxis> getzAxes()
    /*-{
         return this.zAxis;
    }-*/;

    public final native JsArray<HighchartAxis> getColorAxes()
    /*-{
         return this.colorAxis;
    }-*/;

    public final native void setAnimation(boolean animation)
    /*-{
        this.animation = animation;
    }-*/;

    public final native void setSize(int offsetWidth, int offsetHeight,
            boolean animate, boolean clearUserSize)
    /*-{
        this.setSize(offsetWidth,offsetHeight,animate);
        if(clearUserSize) {
            this.hasUserSize = null;
        }
    }-*/;

    public final native void destroy()
    /*-{
        this.destroy();
    }-*/;

    public final native void addDrilldown(String series, int seriesIndex,
            int pointIndex)
    /*-{
        var newDrilldownData = $wnd.eval('('+series+')');
        var point = this.series[seriesIndex].data[pointIndex];
        this.addSeriesAsDrilldown(point,newDrilldownData);
    }-*/;

}
