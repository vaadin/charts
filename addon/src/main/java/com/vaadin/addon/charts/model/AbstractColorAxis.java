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

import com.vaadin.addon.charts.model.style.SolidColor;

/**
 * Support for colours in axes that need it.
 * 
 * @since 2.0
 *
 */
public abstract class AbstractColorAxis extends Axis {

    private static final long serialVersionUID = 20141112;

    private String minColor;
    private String maxColor;

    /**
     * Returns current minimum color.
     * 
     * @return Minimum color.
     */
    public String getMinColor() {
        return minColor;
    }

    /**
     * Sets the minimum color.
     * 
     * @param minColor
     *            Minimum color.
     */
    public void setMinColor(String minColor) {
        this.minColor = minColor;
    }

    /**
     * Sets the minimum color.
     * 
     * @param color
     *            Minimum color.
     */
    public void setMinColor(SolidColor color) {
        this.minColor = color.toString();
    }

    /**
     * Returns current maximum color.
     * 
     * @return Maximum color.
     */
    public String getMaxColor() {
        return maxColor;
    }

    /**
     * Sets the new maximum color.
     * 
     * @param maxColor
     *            Maximum color.
     */
    public void setMaxColor(String maxColor) {
        this.maxColor = maxColor;
    }

    /**
     * Sets the new maximum color.
     * 
     * @param color
     *            Maximum color.
     */
    public void setMaxColor(SolidColor color) {
        this.maxColor = color.toString();
    }

}
