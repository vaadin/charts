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

/**
 * An object, or array of objects, for backgrounds. Sub options include
 * backgroundColor (which can be solid or gradient), innerWidth, outerWidth,
 * borderWidth, borderColor.
 * <p>
 * <b>These configuration options apply only to polar and angular gauges trough
 * the Pane-configuration object.</b>
 */
public class Background extends AbstractConfigurationObject {
    private Color backgroundColor;
    private Color borderColor;
    private Number borderWidth;
    private String outerRadius;
    private String innerRadius;
    private String shape;

    /**
     * @see #setBackgroundColor(Color)
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the background color
     * 
     * @param backgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @see #setBorderColor(Color)
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * Sets the border color
     * 
     * @param borderColor
     */
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * @see #setBorderWidth(Number)
     */
    public Number getBorderWidth() {
        return borderWidth;
    }

    /**
     * Sets the width of the border
     * 
     * @param borderWidth
     */
    public void setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * Sets the outer radius of the circular shaped background using a string
     * representation of a percentage, e.g. "110%". The percentage is relative
     * to the radius of the chart.
     * 
     * <em>This is applicable only to circular {@link ChartType#GAUGE} type charts.</em>
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

    /**
     * Sets the inner radius of the circular shaped background using a string
     * representation of a percentage, e.g. "110%". The percentage is relative
     * to the radius of the chart.
     * 
     * <em>This is applicable only to circular {@link ChartType#GAUGE} type charts.</em>
     * 
     * @param outerRadius
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
     * Returns current shape of the background.
     * 
     * @return Current shape.
     */
    public String getShape() {
        return shape;
    }

    /**
     * Sets the current shape of the background.
     * 
     * @param shape
     *            New shape.
     */
    public void setShape(String shape) {
        this.shape = shape;
    }

}
