package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.Style;

/**
 * The axis labels show the number or category for each tick.
 * 
 */
public class Labels extends AbstractConfigurationObject {

    private String align;
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

    /**
     * Default constructor
     */
    public Labels() {

    }

    /**
     * Create new label with enabled state
     * 
     * @param enabled
     *            Enabled state
     */
    public Labels(boolean enabled) {
        setEnabled(enabled);
    }

    /**
     * @return the align
     */
    public String getAlign() {
        return align;
    }

    /**
     * @param align
     *            the align to set
     */
    public void setAlign(String align) {
        this.align = align;
    }

    /**
     * @return the enabled
     */
    public boolean getEnabled() {
    	return enabled == null ? true : enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * The rotation is typically numeric like 90 but could be also String like
     * "auto"
     * 
     * @return the rotation
     */
    public Object getRotation() {
        return rotation;
    }

    /**
     * @param rotation
     *            the rotation in Number format
     */
    public void setRotation(Number rotation) {
        this.rotation = rotation;
    }

    /**
     * @param rotation
     *            in String format (e.g. "auto") the rotation to set
     */
    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    /**
     * @return the staggerLines
     */
    public Number getStaggerLines() {
        return staggerLines;
    }

    /**
     * @param staggerLines
     *            the staggerLines to set
     */
    public void setStaggerLines(Number staggerLines) {
        this.staggerLines = staggerLines;
    }

    /**
     * @return the step
     */
    public Number getStep() {
        return step;
    }

    /**
     * @param step
     *            the step to set
     */
    public void setStep(Number step) {
        this.step = step;
    }

    /**
     * @return the style
     */
    public Style getStyle() {
        if (style == null) {
            style = new Style();
        }
        return style;
    }

    /**
     * @param style
     *            the style to set
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * @return the x
     */
    public Number getX() {
        return x;
    }

    /**
     * @param x
     *            the x to set
     */
    public void setX(Number x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public Number getY() {
        return y;
    }

    /**
     * @param y
     *            the y to set
     */
    public void setY(Number y) {
        this.y = y;
    }

    /**
     * @see #setFormatter(String)
     * @return
     */
    public String getFormatter() {
        return _fn_formatter;

    }

    /**
     * JavaScript function to format the label. The value is given by
     * this.value. Additional properties for this are axis, chart, isFirst and
     * isLast. Defaults to:
     * <p>
     * <code>
     * function(){ return this.value; }
     * </code>
     * <p>
     * If function is simpler one liner, it can be given in a shortened form. E.g. the default formatter could be simply:
     * <p>
     * <code>this.value</code>
     * 
     * @param formatter
     */
    public void setFormatter(String formatter) {
        _fn_formatter = formatter;
    }

    /**
     * The text color for the data labels. Defaults to null. Defaults to null.
     * 
     * @return Text color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @see #getColor()
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * The color of the line connecting the data label to the pie slice. The
     * default color is the same as the point's color. Defaults to
     * {point.color}.
     * 
     * @return Connector color or null if not defined
     */
    public Color getConnectorColor() {
        return connectorColor;
    }

    /**
     * @see #getConnectorColor()
     * @param connectorColor
     */
    public void setConnectorColor(Color connectorColor) {
        this.connectorColor = connectorColor;
    }

    /**
     * The distance of the data label from the pie's edge. Negative numbers put
     * the data label on top of the pie slices. Connectors are only shown for
     * data labels outside the pie. Defaults to 30.
     * 
     * @param distance
     */
    public void setDistance(Number distance) {
        this.distance = distance;
    }

    /**
     * @see #setDistance(Number)
     * @return
     */
    public Number getDistance() {
        return distance;
    }

    /**
     * The background color or gradient for the data label. Defaults to
     * undefined. Defaults to undefined.
     * 
     * @param backgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @see #setBackgroundColor(Color)
     * @return
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

}
