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
 * The stack labels show the total value for each bar in a stacked column or bar
 * chart. The label will be placed on top of positive columns and below negative
 * columns. In case of an inverted column chart or a bar chart the label is
 * placed to the right of positive bars and to the left of negative bars.
 */
@SuppressWarnings("serial")
public class StackLabels extends AbstractConfigurationObject {

    private HorizontalAlign align;
    private Boolean enabled;
    private String _fn_formatter;
    private Number rotation;
    private Style style;
    private HorizontalAlign textAlign;
    private VerticalAlign verticalAlign;
    private Number x;
    private Number y;

    /**
     * Constructs a disabled StackLabels.
     */
    public StackLabels() {
    }

    /**
     * Constructs a StackLabels that is either enabled or disabled.
     * 
     * @param enabled
     *            true to enable, false to disable.
     */
    public StackLabels(boolean enabled) {
        setEnabled(enabled);
    }

    /**
     * @see #setAlign(HorizontalAlign)
     * @return The horizontal alignment or null if not defined
     */
    public HorizontalAlign getAlign() {
        return align;
    }

    /**
     * Defines the horizontal alignment of the stack total label. The default
     * value is calculated at runtime and depends on the orientation and whether
     * the stack is positive or negative.
     * 
     * @param align
     */
    public void setAlign(HorizontalAlign align) {
        this.align = align;
    }

    /**
     * @see #setEnabled(Boolean)
     */
    public boolean isEnabled() {
        return enabled == null ? false : enabled;
    }

    /**
     * Enables or disables the stack total labels. Defaults to disabled.
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @see #setFormatter(String)
     * @return The JavaScript function to format the label.
     */
    public String getFormatter() {
        return _fn_formatter;
    }

    /**
     * Sets the JavaScript function to format the label.
     * 
     * @param formatter
     */
    public void setFormatter(String formatter) {
        _fn_formatter = formatter;
    }

    /**
     * @see #setRotation(Number)
     * @return The rotation of the labels or null if not defined
     */
    public Number getRotation() {
        return rotation;
    }

    /**
     * Sets the rotation of the labels in degrees. Defaults to 0.
     * 
     * @param rotation
     */
    public void setRotation(Number rotation) {
        this.rotation = rotation;
    }

    /**
     * @see #setStyle(Style)
     * @return The style of the labels or null if not defined
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Sets the style of the labels.
     * 
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * @see #setTextAlign(HorizontalAlign)
     * @return The alignment of the text or null if not defined
     */
    public HorizontalAlign getTextAlign() {
        return textAlign;
    }

    /**
     * Sets the text alignment for the label. While
     * {@link #setAlign(HorizontalAlign)} determines where the texts anchor
     * point is placed with regards to the stack,
     * {@link #setTextAlign(HorizontalAlign)} determines how the text is aligned
     * against its anchor point. The default value is calculated at runtime and
     * depends on orientation and whether the stack is positive or negative.
     * 
     * @param textAlign
     */
    public void setTextAlign(HorizontalAlign textAlign) {
        this.textAlign = textAlign;
    }

    /**
     * @see #setVerticalAlign(VerticalAlign)
     * @return The vertical alignment or null if not defined
     */
    public VerticalAlign getVerticalAlign() {
        return verticalAlign;
    }

    /**
     * Defines the vertical alignment of the stack total label. The default
     * value is calculated at runtime and depends on orientation and whether the
     * stack is positive or negative.
     * 
     * @param verticalAlign
     */
    public void setVerticalAlign(VerticalAlign verticalAlign) {
        if (verticalAlign != null) {
            if (verticalAlign == VerticalAlign.HIGH) {
                verticalAlign = VerticalAlign.TOP;
            } else if (verticalAlign == VerticalAlign.LOW) {
                verticalAlign = VerticalAlign.BOTTOM;
            }
        }
        this.verticalAlign = verticalAlign;
    }

    /**
     * @see #setX(Number)
     * @return The X position offset of the labels or null if not defined
     */
    public Number getX() {
        return x;
    }

    /**
     * Sets the X position offset of the labels relative to the left of the
     * stacked bar. The default value is calculated at runtime and depends on
     * orientation and whether the stack is positive or negative.
     * 
     * @param x
     */
    public void setX(Number x) {
        this.x = x;
    }

    /**
     * @see #setY(Number)
     * @return The Y position offset of the labels or null if not defined
     */
    public Number getY() {
        return y;
    }

    /**
     * Sets the Y position offset of the labels relative to the tick position on
     * the axis. The default value is calculated at runtime and depends on
     * orientation and whether the stack is positive or negative.
     * 
     * @param y
     */
    public void setY(Number y) {
        this.y = y;
    }

}
