package com.vaadin.addon.charts.model;

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

import com.vaadin.addon.charts.model.style.Color;

/**
 * Marker (symbol) for series points
 */
public class Marker extends AbstractConfigurationObject {

    private Boolean enabled;
    private MarkerSymbol symbol;
    private Number radius;
    private MarkerStates states;
    private Number lineWidth;
    private Color lineColor;
    private Color fillColor;

    public Marker() {
    }

    /**
     * Constructs a marker which is either enabled or disabled.
     * 
     * @param enabled
     *            true to enable, false to disable
     */
    public Marker(Boolean enabled) {
        setEnabled(enabled);
    }

    /**
     * @see Marker#setEnabled(Boolean)
     */
    public boolean isEnabled() {
        return enabled == null ? true : enabled;
    }

    /**
     * Enables or disables the point marker. Defaults to enabled (true).
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @see #setRadius(Number)
     */
    public Number getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the point marker. Defaults to 0.
     * 
     * @param radius
     */
    public void setRadius(Number radius) {
        this.radius = radius;
    }

    /**
     * @see #setStates(MarkerStates)
     */
    public MarkerStates getStates() {
        return states;
    }

    /**
     * Sets the marker state for hover and select events
     * 
     * @param states
     */
    public void setStates(MarkerStates states) {
        this.states = states;
    }

    /**
     * @see #setLineWidth(Number)
     */
    public Number getLineWidth() {
        return lineWidth;
    }

    /**
     * Sets the width of the point marker's outline. Defaults to 0.
     * 
     * @param lineWidth
     */
    public void setLineWidth(Number lineWidth) {
        this.lineWidth = lineWidth;
    }

    /**
     * @see #setLineColor(Color)
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * Sets the color of the point marker's outline. When null, the series' or
     * point's color is used. Defaults to "#FFFFFF".
     * 
     * @param lineColor
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * Sets a predefined shape or symbol for the marker. When null, the symbol
     * is pulled from options.symbols. Defaults to null.
     * 
     * @see MarkerSymbolEnum
     * @see MarkerSymbolUrl
     * 
     * @param symbol
     */
    public void setSymbol(MarkerSymbol symbol) {
        this.symbol = symbol;
    }

    /**
     * @see #setSymbol(MarkerSymbol)
     */
    public MarkerSymbol getSymbol() {
        return symbol;
    }

    /**
     * @see #setFillColor(Color)
     * @return The fill color or null if not defined
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Sets the fill color of the point marker. When null, the series' or
     * point's color is used. Defaults to null.
     * 
     * @param fillColor
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

}
