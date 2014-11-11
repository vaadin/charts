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

import com.vaadin.addon.charts.model.style.Color;

public abstract class AbstractAreaPlotOptions extends AbstractLinePlotOptions {

    private Number fillOpacity;
    private Color fillColor;
    private Color lineColor;

    /**
     * @see #setLineColor(Color)
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * Sets a separate color for the graph line. By default the line takes the
     * color of the series, but the lineColor setting allows setting a separate
     * color for the line without altering the fillColor. Defaults to null
     * 
     * @param lineColor
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * @see #setFillColor(Color)
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Sets the fill color or gradient for the area. When null, the series'
     * color is used with the series' fillOpacity. Defaults to null.
     * 
     * @param fillColor
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * @see #setFillOpacity(Number)
     * @return Fill opacity or null if not defined
     */
    public Number getFillOpacity() {
        return fillOpacity;
    }

    /**
     * Sets the fill opacity for the area. Defaults to .75.
     * 
     * @param fillOpacity
     */
    public void setFillOpacity(Number fillOpacity) {
        this.fillOpacity = fillOpacity;
    }

}
