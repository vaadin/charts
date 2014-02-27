package com.vaadin.addon.charts.model;

public abstract class AbstractLinePlotOptions extends AbstractPointPlotOptions {

    private Number lineWidth;
    private Marker marker;
    private DashStyle dashStyle;

    /**
     * @see #setMarker(Marker)
     */
    public Marker getMarker() {
        return marker;
    }

    /**
     * Sets the marker used for the plot point items (points/bars/columns)
     * 
     * @param marker
     */
    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    /**
     * @see #setLineWidth(Number)
     * @return Line width or null if undefined
     */
    public Number getLineWidth() {
        return lineWidth;
    }

    /**
     * Sets the with of the graph line in pixels. Defaults to 2.
     * 
     * @param lineWidth
     */
    public void setLineWidth(Number lineWidth) {
        this.lineWidth = lineWidth;
    }

    public DashStyle getDashStyle() {
        return dashStyle;
    }

    /**
     * Sets the dash style used to render lines.
     * 
     * @see DashStyle
     * 
     * @param dashStyle
     *            the dash style
     */
    public void setDashStyle(DashStyle dashStyle) {
        this.dashStyle = dashStyle;
    }

}
