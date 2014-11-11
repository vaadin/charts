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
 * Abstract base for Title classes (namely Title and Subtitle)
 */
public abstract class AbstractTitle extends AbstractConfigurationObject {
    private Boolean floating;
    private VerticalAlign verticalAlign;
    private HorizontalAlign align;
    private String text;
    private Style style;
    private Number x;
    private Number y;

    public AbstractTitle() {
    }

    /**
     * Constructs a title with the given text
     * 
     * @param text
     */
    public AbstractTitle(String text) {
        this.text = text;
    }

    /**
     * @see #setFloating(Boolean)
     * 
     * @return whether the title is floating or not, false if undefined.
     */
    public boolean isFloating() {
        return floating == null ? false : floating;
    }

    /**
     * When the title is floating, the plot area will not move to make space for
     * it. Defaults to false.
     * 
     * @param floating
     *            the floating to set
     */
    public void setFloating(Boolean floating) {
        this.floating = floating;
    }

    /**
     * @see #setVerticalAlign(VerticalAlign)
     * @return the verticalAlign
     */
    public VerticalAlign getVerticalAlign() {
        return verticalAlign;
    }

    /**
     * Sets the vertical alignment of the title. Can be one of
     * {@link VerticalAlign#TOP}, {@link VerticalAlign#MIDDLE} and
     * {@link VerticalAlign#BOTTOM}. Defaults to {@link VerticalAlign#TOP}.
     * 
     * @param verticalAlign
     *            the align to set
     */
    public void setVerticalAlign(VerticalAlign verticalAlign) {
        this.verticalAlign = verticalAlign;
    }

    /**
     * @see #setHorizontalAlign(HorizontalAlign)
     * @return the horizontal alignment
     */
    public HorizontalAlign getHorizontalAlign() {
        return align;
    }

    /**
     * The horizontal alignment of the title. Can be one of
     * {@link HorizontalAlign#LEFT}, {@link HorizontalAlign#CENTER} and
     * {@link HorizontalAlign#RIGHT}. Defaults to {@link HorizontalAlign#CENTER}
     * .
     * 
     * @param horizontalAlign
     *            the alignment to set
     */
    public void setHorizontalAlign(HorizontalAlign horizontalAlign) {
        align = horizontalAlign;
    }

    /**
     * @see #setText(String)
     * 
     * @return the text of the title
     */
    public String getText() {
        return text;
    }

    /**
     * Set the text of the title for the chart. To disable the title, set the
     * text to null. Defaults to "Chart title".
     * 
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @see #setStyle(Style)
     * @return the style of the title
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Sets the CSS styles for the title. Use this for font styling, but use
     * {@link #setHorizontalAlign(HorizontalAlign)},
     * {@link #setVerticalAlign(VerticalAlign)}, {@link #setX(Number)} and
     * {@link #setY(Number)} for text alignment.
     * 
     * @param style
     *            the style to set
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * @see #setX(Number)
     * @return the X position of the title
     */
    public Number getX() {
        return x;
    }

    /**
     * The X position of the title relative to the alignment within
     * chart.spacingLeft and chart.spacingRight. Defaults to 0.
     * 
     * @param x
     *            the X position to set
     */
    public void setX(Number x) {
        this.x = x;
    }

    /**
     * 
     * @see #setY(Number)
     * @return the Y position of the title
     */
    public Number getY() {
        return y;
    }

    /**
     * The Y position of the title relative to the alignment within
     * chart.spacingTop and chart.spacingBottom. Defaults to 15.
     * 
     * @param y
     *            the Y position to set
     */
    public void setY(Number y) {
        this.y = y;
    }

}
