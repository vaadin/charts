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
 * A HTML label that can be positioined anywhere in the chart area.
 * 
 */
public class HTMLLabelItem extends AbstractConfigurationObject {
    private String html;
    private Style style;

    /**
     * Constructor with given html content of the label
     * 
     * @param html
     */
    public HTMLLabelItem(String html) {
        setHtml(html);
    }

    /**
     * Constructor with given html content and Style of the label
     * 
     * @param html
     */
    public HTMLLabelItem(String html, Style style) {
        this(html);
        setStyle(style);
    }

    /**
     * @see #setHtml(String)
     * @return
     */
    public String getHtml() {
        return html;
    }

    /**
     * Inner HTML or text for the label. Defaults to "".
     * 
     * @param html
     */
    public void setHtml(String html) {
        this.html = html;
    }

    /**
     * @see #setStyle(Style)
     * @return
     */
    public Style getStyle() {
        return style;
    }

    /**
     * CSS styles for each label. To position the label, use left and top.
     * 
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }
}
