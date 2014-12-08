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
    private Number rotation;
    private HorizontalAlign textAlign;
    private Boolean useHTML;
    private VerticalAlign verticalAlign;
    private Number x;
    private Number y;

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
     * Sets horizontal alignment of the label. Can be one of
     * {@link HorizontalAlign#LEFT}, {@link HorizontalAlign#CENTER} and
     * {@link HorizontalAlign#RIGHT}. Defaults to center.
     * 
     * @param align
     */
    public void setAlign(HorizontalAlign align) {
        this.align = align;
    }

    /**
     * @see #setRotation(Number)
     * @return
     */
    public Number getRotation() {
        return rotation;
    }

    /**
     * Rotation of the text label in degrees . Defaults to 0.
     * 
     * @param rotation
     */
    public void setRotation(Number rotation) {
        this.rotation = rotation;
    }

    /**
     * @see #setTextAlign(HorizontalAlign)
     * @return textAlign
     */
    public HorizontalAlign getTextAlign() {
        return textAlign;
    }

    /**
     * The text alignment for the label. While align determines where the texts
     * anchor point is placed within the plot band, textAlign determines how the
     * text is aligned against its anchor point. Possible values are
     * {@link HorizontalAlign#LEFT}, {@link HorizontalAlign#CENTER} and
     * {@link HorizontalAlign#RIGHT}". Defaults to the same as the align option.
     * 
     * @param textAlign
     */
    public void setTextAlign(HorizontalAlign textAlign) {
        this.textAlign = textAlign;
    }

    /**
     * @see #setUseHTML(Boolean)
     * @return
     */
    public Boolean getUseHTML() {
        return useHTML;
    }

    /**
     * Whether to use HTML to render the labels. Defaults to false.
     * 
     * @param useHTML
     */
    public void setUseHTML(Boolean useHTML) {
        this.useHTML = useHTML;
    }

    /**
     * @see #setVerticalAlign(VerticalAlign)
     * @return
     */
    public VerticalAlign getVerticalAlign() {
        return verticalAlign;
    }

    /**
     * Vertical alignment of the label relative to the plot band. Can be one of
     * {@link VerticalAlign#TOP}, {@link VerticalAlign#MIDDLE} or
     * {@link VerticalAlign#BOTTOM}. Defaults to {@link VerticalAlign#TOP}.
     * 
     * @param verticalAlign
     */
    public void setVerticalAlign(VerticalAlign verticalAlign) {
        this.verticalAlign = verticalAlign;
    }

    /**
     * @see #setX(Number)
     * @return x
     */
    public Number getX() {
        return x;
    }

    /**
     * Horizontal position relative the alignment. Default varies by
     * orientation.
     * 
     * @param x
     */
    public void setX(Number x) {
        this.x = x;
    }

    /**
     * @see #setY(Number)
     * @return y
     */
    public Number getY() {
        return y;
    }

    /**
     * Vertical position of the text baseline relative to the alignment. Default
     * varies by orientation.
     * 
     * @param y
     */
    public void setY(Number y) {
        this.y = y;
    }
}
