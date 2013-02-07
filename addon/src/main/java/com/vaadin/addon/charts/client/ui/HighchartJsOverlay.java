package com.vaadin.addon.charts.client.ui;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
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
     * This is currently overly simplified (only y value possible), just a
     * prototype.
     * 
     * @param doubleAttribute
     */
    public native final void addPoint(double xValue, double yValue,
            int seriesIndex, boolean redraw, boolean shift)
    /*-{
        this.series[seriesIndex].addPoint([xValue, yValue],redraw,shift);
    }-*/;

    public native final void removePoint(double xValue, double yValue,
            double threshold)
    /*-{
        for (var i=0; i<this.series[0].data.length; i++) {
            if (this.series[0].data[i].x && this.series[0].data[i].y) {
                if (Math.abs(this.series[0].data[i].x-xValue)<threshold && Math.abs(this.series[0].data[i].y-yValue)<threshold) {
                    this.series[0].data[i].remove();
                    break;
                }
            }
        }
    }-*/;

    public native final void removePoint(double xValue, double yValue,
            int seriesIndex, double threshold)
    /*-{
        for (var i=0; i<this.series[seriesIndex].data.length; i++) {
            if (this.series[seriesIndex].data[i].x && this.series[seriesIndex].data[i].y) {
                if (Math.abs(this.series[seriesIndex].data[i].x-xValue)<threshold && Math.abs(this.series[seriesIndex].data[i].y-yValue)<threshold) {
                    this.series[seriesIndex].data[i].remove();
                    break;
                }
            }
        }
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

    public final native void setAnimation(boolean animation) 
    /*-{
        this.animation = animation;
    }-*/;

    public final native void setSize(int offsetWidth, int offsetHeight, boolean animate, boolean clearUserSize) 
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

}
