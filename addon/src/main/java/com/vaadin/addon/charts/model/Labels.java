package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.Style;

/**
 * The axis labels show the number or category for each tick.
 * 
 */
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
     * @see #setAlign(HorizontalAlign)
     * @return the align
     */
    public HorizontalAlign getAlign() {
        return align;
    }

    /**
     * What part of the string the given position is anchored to. Can be one of
     * "left", "center" or "right". In inverted charts, x axis label alignment
     * and y axis alignment are swapped. Defaults to "center".
     * 
     * @param align
     *            the align to set
     */
    public void setAlign(HorizontalAlign align) {
        this.align = align;
    }

    /**
     * @see #setEnabled(Boolean)
     * @return the enabled
     */
    public boolean getEnabled() {
        return enabled == null ? true : enabled;
    }

    /**
     * Enable or disable the axis labels. Defaults to true.
     * 
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @see #setRotation(Number)
     * @return the rotation
     */
    public Object getRotation() {
        return rotation;
    }

    /**
     * Rotation of the labels in degrees. Defaults to 0. The rotation is
     * typically numeric like 90 but could be also String like "auto"
     * 
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
     * @see #setStaggerLines(Number)
     * @return the staggerLines
     */
    public Number getStaggerLines() {
        return staggerLines;
    }

    /**
     * Horizontal axes only. The number of lines to spread the labels over to
     * make room or tighter labels. . Defaults to null.
     * 
     * @param staggerLines
     *            the staggerLines to set
     */
    public void setStaggerLines(Number staggerLines) {
        this.staggerLines = staggerLines;
    }

    /**
     * @see #setStep(Number)
     * @return the step
     */
    public Number getStep() {
        return step;
    }

    /**
     * To show only every n'th label on the axis, set the step to n. Setting the
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
     * @return the style
     */
    public Style getStyle() {
        if (style == null) {
            style = new Style();
        }
        return style;
    }

    /**
     * CSS styles for the label. Defaults to:
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
     * @return the x
     */
    public Number getX() {
        return x;
    }

    /**
     * The x position offset of the label relative to the tick position on the
     * axis. Defaults to 0.
     * 
     * @param x
     *            the x to set
     */
    public void setX(Number x) {
        this.x = x;
    }

    /**
     * @see #setY(Number)
     * @return the y
     */
    public Number getY() {
        return y;
    }

    /**
     * The y position offset of the label relative to the tick position on the
     * axis. Defaults to 0.
     * 
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
     * If function is simpler one liner, it can be given in a shortened form.
     * E.g. the default formatter could be simply:
     * <p>
     * <code>this.value</code>
     * 
     * @param formatter
     */
    public void setFormatter(String formatter) {
        _fn_formatter = formatter;
    }

    /**
     * @see #setColor(Color)
     * @return Text color
     */
    public Color getColor() {
        return color;
    }

    /**
     * The text color for the data labels. Defaults to null. Defaults to null.
     * 
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @see #setConnectorColor(Color)
     * @return Connector color or null if not defined
     */
    public Color getConnectorColor() {
        return connectorColor;
    }

    /**
     * The color of the line connecting the data label to the pie slice. The
     * default color is the same as the point's color. Defaults to
     * {point.color}.
     * 
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
