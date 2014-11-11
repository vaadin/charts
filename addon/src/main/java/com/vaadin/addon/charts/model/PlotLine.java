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
import com.vaadin.addon.charts.model.style.SolidColor;

/**
 * A line stretching across the plot area, marking a specific value on one of
 * the axes.
 */
@SuppressWarnings("serial")
public class PlotLine extends AbstractConfigurationObject {
    private Number value;
    private Number width;
    private Color color;
    private PlotBandLabel label;

    public PlotLine() {
        super();
    }

    /**
     * Constructs a PlotLine with the given value, width and color
     * 
     * @param value
     * @param width
     * @param color
     */
    public PlotLine(Number value, Number width, SolidColor color) {
        super();
        this.value = value;
        this.width = width;
        this.color = color;
    }

    /**
     * @see #setValue(Number)
     * @return The position of the plot line.
     */
    public Number getValue() {
        return value;
    }

    /**
     * Sets the position of the line in axis units. Defaults to null.
     * 
     * @param value
     */
    public void setValue(Number value) {
        this.value = value;
    }

    /**
     * @see #setWidth(Number)
     */
    public Number getWidth() {
        return width;
    }

    /**
     * Sets the width or thickness of the plot line. Defaults to null.
     * 
     * @param width
     */
    public void setWidth(Number width) {
        this.width = width;
    }

    /**
     * @see #setColor(SolidColor)
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the line. Defaults to null.
     * 
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * see {@link #setLabel(PlotBandLabel)}
     * 
     * @return
     */
    public PlotBandLabel getLabel() {
        return label;
    }

    /**
     * Sets the label displayed for plot line. Default is null.
     * 
     * @param label
     */
    public void setLabel(PlotBandLabel label) {
        this.label = label;
    }

}
