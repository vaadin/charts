package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;

/**
 * The stack labels show the total value for each bar in a stacked column or bar
 * chart. The label will be placed on top of positive columns and below negative
 * columns. In case of an inverted column chart or a bar chart the label is
 * placed to the right of positive bars and to the left of negative bars.
 */
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
     * Default constructor
     */
    public StackLabels() {

    }

    /**
     * Create new StackLabels with enabled state
     * 
     * @param enabled
     *            true to enable
     */
    public StackLabels(boolean enabled) {
        setEnabled(enabled);
    }

    /**
     * Defines the horizontal alignment of the stack total label. Can be one of
     * "left", "center" or "right". The default value is calculated at runtime
     * and depends on orientation and whether the stack is positive or negative.
     * 
     * @return Align or null if not defined
     */
    public HorizontalAlign getAlign() {
        return align;
    }

    /**
     * @see #getAlign()
     * @param align
     */
    public void setAlign(HorizontalAlign align) {
        this.align = align;
    }

    /**
     * Enable or disable the stack total labels. Defaults to false.
     * 
     * @return
     */
    public boolean isEnabled() {
        return enabled == null ? false : enabled;
    }

    /**
     * @see #isEnabled()
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @see #setFormatter(String)
     * @return
     */
    public String getFormatter() {
        return _fn_formatter;
    }

    /**
     * JavaScript function to format the label.
     * 
     * @param formatter
     */
    public void setFormatter(String formatter) {
        _fn_formatter = formatter;
    }

    /**
     * Rotation of the labels in degrees. Defaults to 0.
     * 
     * @return Rotation or null if not defined
     */
    public Number getRotation() {
        return rotation;
    }

    /**
     * @see #getRotation()
     * @param rotation
     */
    public void setRotation(Number rotation) {
        this.rotation = rotation;
    }

    /**
     * Style object
     * 
     * @return Style object or null if not defined
     */
    public Style getStyle() {
        return style;
    }

    /**
     * @see #getStyle()
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * The text alignment for the label. While align determines where the texts
     * anchor point is placed with regards to the stack, textAlign determines
     * how the text is aligned against its anchor point. Possible values are
     * "left", "center" and "right". The default value is calculated at runtime
     * and depends on orientation and whether the stack is positive or negative.
     * 
     * @return Text align or null if not defined
     */
    public HorizontalAlign getTextAlign() {
        return textAlign;
    }

    /**
     * @see #getTextAlign()
     * @param textAlign
     */
    public void setTextAlign(HorizontalAlign textAlign) {
        this.textAlign = textAlign;
    }

    /**
     * Defines the vertical alignment of the stack total label. Can be one of
     * "top", "middle" or "bottom". The default value is calculated at runtime
     * and depends on orientation and whether the stack is positive or negative.
     * 
     * @return Vertical align or null if not defined
     */
    public VerticalAlign getVerticalAlign() {
        return verticalAlign;
    }

    /**
     * @see #getVerticalAlign()
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
     * The x position offset of the label relative to the left of the stacked
     * bar. The default value is calculated at runtime and depends on
     * orientation and whether the stack is positive or negative.
     * 
     * @return X value or null if not defined
     */
    public Number getX() {
        return x;
    }

    /**
     * @see #getX()
     * @param x
     */
    public void setX(Number x) {
        this.x = x;
    }

    /**
     * The y position offset of the label relative to the tick position on the
     * axis. The default value is calculated at runtime and depends on
     * orientation and whether the stack is positive or negative.
     * 
     * @return Y value or null if not defined
     */
    public Number getY() {
        return y;
    }

    /**
     * @see #getY()
     * @param y
     */
    public void setY(Number y) {
        this.y = y;
    }

}
