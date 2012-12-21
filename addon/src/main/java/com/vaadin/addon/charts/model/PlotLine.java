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
import com.vaadin.addon.charts.model.style.SolidColor;

/**
 * A line stretching across the plot area, marking a specific value on one of
 * the axes.
 */
public class PlotLine extends AbstractConfigurationObject {
    private Number value;
    private Number width;
    private Color color;

    /**
     * Default constructor
     */
    public PlotLine() {
        super();
    }

    /**
     * Construct a PloLine with given value, width and color
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
     * @return
     */
    public Number getValue() {
        return value;
    }

    /**
     * The position of the line in axis units. Defaults to null.
     * 
     * @param value
     */
    public void setValue(Number value) {
        this.value = value;
    }

    /**
     * @see #setWidth(Number)
     * @return
     */
    public Number getWidth() {
        return width;
    }

    /**
     * The width or thickness of the plot line. Defaults to null.
     * 
     * @param width
     */
    public void setWidth(Number width) {
        this.width = width;
    }

    /**
     * @see #setColor(SolidColor)
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * The color of the line. Defaults to null.
     * 
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

}
