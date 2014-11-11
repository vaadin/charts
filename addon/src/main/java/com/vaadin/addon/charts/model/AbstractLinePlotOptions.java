package com.vaadin.addon.charts.model;

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
