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

import com.vaadin.addon.charts.model.style.Style;

/**
 * Text labels for the plot bands
 */
public class PlotBandLabel extends AbstractConfigurationObject {
    private HorizontalAlign align;
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

    /**
     * @see #setAlign(HorizontalAlign)
     */
    public HorizontalAlign getAlign() {
        return align;
    }

    /**
     * Sets horizontal alignment of the label. Can be one of LEFT, CENTER or
     * RIGHT. Defaults to center.
     * 
     * @param align
     */
    public void setAlign(HorizontalAlign align) {
        this.align = align;
    }
}
