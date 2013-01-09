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

import java.util.Date;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;

/**
 * A colored band stretching across the plot area marking an interval on the
 * axis.
 */
public class PlotBand extends AbstractConfigurationObject {
    private Number from;
    private Number to;
    private Color color;
    private Number zIndex;
    private PlotBandLabel label;
    private String innerRadius;
    private String outerRadius;

    public PlotBand() {
    }

    /**
     * Constructs a new PlotBand with a starting position, an ending position
     * and a color.
     * 
     * @param from
     *            The start position of the plot band in axis units.
     * @param to
     *            The end position of the plot band in axis units.
     * @param color
     *            The color of the plot band.
     */
    public PlotBand(Number from, Number to, SolidColor color) {
        setFrom(from);
        setTo(to);
        setColor(color);
    }

    /**
     * @see #setFrom(Number)
     * @return The starting position of the plot band
     */
    public Number getFrom() {
        return from;
    }

    /**
     * Sets the start position of the plot band in axis units. Defaults to null.
     * 
     * @param from
     */
    public void setFrom(Number from) {
        this.from = from;
    }

    /**
     * @see #setTo(Number)
     * @return The ending position of the plot band
     */
    public Number getTo() {
        return to;
    }

    /**
     * Sets the end position of the plot band in axis units. Defaults to null.
     * 
     * @param to
     */
    public void setTo(Number to) {
        this.to = to;
    }

    /**
     * @see #setColor(Color)
     * @return The color of the plot band
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the plot band. Defaults to null.
     * 
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @see #setzIndex(Number)
     * @return The Z index
     */
    public Number getzIndex() {
        return zIndex;
    }

    /**
     * Sets the z index of the plot band within the chart. Defaults to null.
     * 
     * @param zIndex
     */
    public void setzIndex(Number zIndex) {
        this.zIndex = zIndex;
    }

    /**
     * Sets a text label for the plot band
     * 
     * @param label
     */
    public void setLabel(PlotBandLabel label) {
        this.label = label;
    }

    /**
     * @see #setLabel(PlotBandLabel)
     * @return The text label for the plot band
     */
    public PlotBandLabel getLabel() {
        return label;
    }

    /**
     * Sets the start position of the plot band in axis units. Defaults to null.
     * 
     * @param date
     */
    public void setFrom(Date date) {
        from = date.getTime();
    }

    /**
     * Sets the end position of the plot band in axis units. Defaults to null.
     * 
     * @return To value
     */
    public void setTo(Date date) {
        to = date.getTime();
    }

    /**
     * Sets the inner radius of the plot band on the gauge.
     * 
     * <em>Note<em>: Only applicable for {@link ChartType#GAUGE}
     * 
     * @param innerRadius
     */
    public void setInnerRadius(String innerRadius) {
        this.innerRadius = innerRadius;
    }

    /**
     * @see #setInnerRadius(String)
     */
    public String getInnerRadius() {
        return innerRadius;
    }

    /**
     * Sets the outer radius of the plot band on the gauge.
     * 
     * <em>Note<em>: Only applicable for {@link ChartType#GAUGE}
     * 
     * @param outerRadius
     */
    public void setOuterRadius(String outerRadius) {
        this.outerRadius = outerRadius;
    }

    /**
     * @see #setOuterRadius(String)
     */
    public String getOuterRadius() {
        return outerRadius;
    }
}
