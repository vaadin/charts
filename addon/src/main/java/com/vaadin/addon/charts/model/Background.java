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
 * An object, or array of objects, for backgrounds. Sub options include
 * backgroundColor (which can be solid or gradient), innerWidth, outerWidth,
 * borderWidth, borderColor.
 */
public class Background extends AbstractConfigurationObject {
    private Color backgroundColor;
    private Color borderColor;
    private Number innerWidth;
    private Number outerWidth;
    private Number borderWidth;
    private String outerRadius;
    private String innerRadius;

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
     * @see #setInnerWidth(Number)
     */
    public Number getInnerWidth() {
        return innerWidth;
    }

    /**
     * Sets the inner width
     * 
     * @param innerWidth
     */
    public void setInnerWidth(Number innerWidth) {
        this.innerWidth = innerWidth;
    }

    /**
     * @see #setOuterWidth(Number)
     */
    public Number getOuterWidth() {
        return outerWidth;
    }

    /**
     * Sets the outer width
     * 
     * @param outerWidth
     */
    public void setOuterWidth(Number outerWidth) {
        this.outerWidth = outerWidth;
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
     * Sets the outer radius using a string representation of a percentage, e.g.
     * "110%"
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
     * Sets the inner radius using a string representation of a percentage, e.g.
     * "110%"
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

}
