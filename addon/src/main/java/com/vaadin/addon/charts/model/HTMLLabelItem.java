package com.vaadin.addon.charts.model;

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
