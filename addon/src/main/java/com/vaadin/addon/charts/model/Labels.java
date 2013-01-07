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

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.Style;

/**
 * The axis labels show the numeric value or category name for each tick.
 */
@SuppressWarnings("serial")
public class Labels extends AbstractConfigurationObject {

    private HorizontalAlign align;
    private Boolean enabled;
    private Object rotation;
    private Number staggerLines;
    private Number step;
    private Style style;
    private Number x;
    private Number y;
    private String _fn_formatter;
    private Color color;
    private Color connectorColor;
    private Number distance;
    private Color backgroundColor;

    public Labels() {
    }

    /**
     * Creates a new enabled or disabled label
     * 
     * @param enabled
     *            true to enable, false to disable
     */
    public Labels(boolean enabled) {
        setEnabled(enabled);
    }

    /**
     * @see #setAlign(HorizontalAlign)
     * @return The horizontal alignment relative to the position of a label.
     */
    public HorizontalAlign getAlign() {
        return align;
    }

    /**
     * Sets which part of the string the given position is anchored to. In
     * inverted charts, X-axis label alignment and Y-axis alignment are swapped.
     * Defaults to {@link HorizontalAlign#CENTER}.
     * 
     * @param align
     *            the align to set
     */
    public void setAlign(HorizontalAlign align) {
        this.align = align;
    }

    /**
     * @see #setEnabled(Boolean)
     */
    public boolean getEnabled() {
        return enabled == null ? true : enabled;
    }

    /**
     * Enable or disable the axis labels. Defaults to true.
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @see #setRotation(Number)
     * @return The rotation of the labels in degrees.
     */
    public Object getRotation() {
        return rotation;
    }

    /**
     * Sets the rotation of the labels in degrees. Defaults to 0.
     * 
     * @see #setRotation(String)
     * 
     * @param rotation
     *            the rotation in degrees
     */
    public void setRotation(Number rotation) {
        this.rotation = rotation;
    }

    /**
     * Sets the rotation of the labels in degrees. Defaults to 0. Can also be
     * set to "auto".
     * 
     * @param rotation
     *            in String format (e.g. "auto") the rotation to set
     */
    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    /**
     * @see #setStaggerLines(Number)
     */
    public Number getStaggerLines() {
        return staggerLines;
    }

    /**
     * The number of lines to spread the labels over to make room or tighter
     * labels. Defaults to null.
     * 
     * <em>Note</em> This applies to horizontal axes only.
     * 
     * @param staggerLines
     *            the staggerLines to set
     */
    public void setStaggerLines(Number staggerLines) {
        this.staggerLines = staggerLines;
    }

    /**
     * @see #setStep(Number)
     */
    public Number getStep() {
        return step;
    }

    /**
     * Sets whether to show only every n'th label on the axis. E.g. setting the
     * step to 2 shows every other label. Defaults to null.
     * 
     * @param step
     *            the step to set
     */
    public void setStep(Number step) {
        this.step = step;
    }

    /**
     * @see #setStyle(Style)
     */
    public Style getStyle() {
        if (style == null) {
            style = new Style();
        }
        return style;
    }

    /**
     * Sets the CSS styles for the labels. Defaults to:
     * 
     * style: { color: '#6D869F', fontWeight: 'bold' }
     * 
     * @param style
     *            the style to set
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * @see #setX(Number)
     */
    public Number getX() {
        return x;
    }

    /**
     * Sets the X position offset of the labels relative to the tick position on
     * the axis. Defaults to 0.
     * 
     * @param x
     */
    public void setX(Number x) {
        this.x = x;
    }

    /**
     * @see #setY(Number)
     */
    public Number getY() {
        return y;
    }

    /**
     * The Y position offset of the label relative to the tick position on the
     * axis. Defaults to 0.
     * 
     * @param y
     */
    public void setY(Number y) {
        this.y = y;
    }

    /**
     * @see #setFormatter(String)
     */
    public String getFormatter() {
        return _fn_formatter;

    }

    /**
     * Sets a JavaScript function to format the label. The value is given by
     * this.value. Additional properties for this are axis, chart, isFirst and
     * isLast. Defaults to:
     * 
     * <code>
     * function(){ return this.value; }
     * </code>
     * 
     * If the function is a simple one liner, it can be given in a shortened
     * form, e.g.: <code>this.value</code>
     * 
     * @param formatter
     */
    public void setFormatter(String formatter) {
        _fn_formatter = formatter;
    }

    /**
     * @see #setColor(Color)
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the text color for the data labels. Defaults to null.
     * 
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @see #setConnectorColor(Color)
     * @return The connector color or null if not defined
     */
    public Color getConnectorColor() {
        return connectorColor;
    }

    /**
     * Sets the color of the line connecting the data label to the pie slice.
     * The default color is the same as the point's color.
     * 
     * @param connectorColor
     */
    public void setConnectorColor(Color connectorColor) {
        this.connectorColor = connectorColor;
    }

    /**
     * Sets the distance of the data label from the pie's edge. Negative numbers
     * put the data label on top of the pie slices. Connectors are only shown
     * for data labels outside the pie. Defaults to 30.
     * 
     * @param distance
     */
    public void setDistance(Number distance) {
        this.distance = distance;
    }

    /**
     * @see #setDistance(Number)
     */
    public Number getDistance() {
        return distance;
    }

    /**
     * Sets the background color or gradient for the data label. Defaults to
     * undefined.
     * 
     * @param backgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @see #setBackgroundColor(Color)
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

}
