package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JavaScriptObject;

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
            int seriesIndex, boolean redraw, boolean shift, boolean animation)
    /*-{
        this.series[seriesIndex].addPoint([xValue, yValue],redraw,shift,animation);
    }-*/;

    /**
     * Updates the value of the specific point of given series
     * 
     * @param seriesIndex
     * @param pointIndex
     * @param newValue
     */
    public native final void updatePointValue(int seriesIndex, int pointIndex,
            double newValue, boolean redraw, boolean animation)
    /*-{
        this.series[seriesIndex].data[pointIndex].update(newValue,redraw,animation);
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

    /**
     * Doesn't work?
     */
    public native final void redraw()
    /*-{
        this.redraw();
    }-*/;
}
