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

import com.vaadin.addon.charts.model.style.Style;

/**
 * Text labels for the plot bands
 */
public class PlotBandLabel extends AbstractConfigurationObject {
    private String text;
    private Style style;

    /**
     * Constructs a PlotBandLabel using the given text
     * 
     * @param text
     */
    public PlotBandLabel(String text) {
        this.text = text;
    }

    /**
     * Sets the text string itself. A subset of HTML is supported.
     * 
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @see #setText(String)
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the CSS styles for the text label.
     * 
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * @see #setStyle(LabelStyle)
     */
    public Style getStyle() {
        return style;
    }
}
