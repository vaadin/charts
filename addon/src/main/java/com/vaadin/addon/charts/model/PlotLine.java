package com.vaadin.addon.charts.model;

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
