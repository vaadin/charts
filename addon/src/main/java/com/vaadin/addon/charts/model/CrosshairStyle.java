package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.SolidColor;

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

/**
 * A wrapper for styles of the crosshairs that appear on options.
 * 
 * @since 2.0
 *
 */
public class CrosshairStyle extends AbstractConfigurationObject {

    private static final long serialVersionUID = 20141112;

    private Number width;
    private String color;
    private DashStyle dashStyle;
    private Number zIndex;

    /**
     * Constructs the style with given parameters.
     * 
     * @param width
     *            Width of the crosshair.
     * @param color
     *            Color of the crosshair.
     * @param dashStyle
     *            Dash style.
     * @param zIndex
     *            Z-index of the crosshair.
     */
    public CrosshairStyle(Number width, String color, DashStyle dashStyle,
            Number zIndex) {
        setWidth(width);
        this.setColor(color);
        setDashStyle(dashStyle);
        setzIndex(zIndex);
    }

    /**
     * Constructs the style with given parameters.
     * 
     * @param width
     *            Width of the crosshair.
     * @param color
     *            Color of the crosshair.
     * @param dashStyle
     *            Dash style.
     * @param zIndex
     *            Z-index of the crosshair.
     */
    public CrosshairStyle(Number width, SolidColor color, DashStyle dashStyle,
            Number zIndex) {
        setWidth(width);
        this.setColor(color);
        setDashStyle(dashStyle);
        setzIndex(zIndex);
    }

    /**
     * Returns current crosshair width.
     * 
     * @return Crosshair width.
     */
    public Number getWidth() {
        return width;
    }

    /**
     * Sets the new crosshair width.
     * 
     * @param width
     *            Crosshair width.
     */
    public void setWidth(Number width) {
        this.width = width;
    }

    /**
     * Returns current crosshair color.
     * 
     * @return Crosshair color.
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the crosshair color.
     * 
     * @param color
     *            Color.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Sets the crosshair color.
     * 
     * @param color
     *            Color.
     */
    public void setColor(SolidColor color) {
        this.color = color.toString();
    }

    /**
     * Returns current dash style of the crosshair.
     * 
     * @return Dash style.
     */
    public DashStyle getDashStyle() {
        return dashStyle;
    }

    /**
     * Sets the dash style of the crosshair.
     * 
     * @param dashStyle
     *            Dash style.
     */
    public void setDashStyle(DashStyle dashStyle) {
        this.dashStyle = dashStyle;
    }

    /**
     * Returns the Z-index of the crosshair.
     * 
     * @return Z-index of the crosshair.
     */
    public Number getzIndex() {
        return zIndex;
    }

    /**
     * Sets the Z-index of the crosshair.
     * 
     * @param zIndex
     *            New Z-index of the crosshair.
     */
    public void setzIndex(Number zIndex) {
        this.zIndex = zIndex;
    }

}
