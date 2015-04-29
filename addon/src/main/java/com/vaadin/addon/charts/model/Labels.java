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

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.Style;

/**
 * The axis labels show the numeric value or category name for each tick.
 */
@SuppressWarnings("serial")
public class Labels extends AbstractConfigurationObject {

    private HorizontalAlign align;
    private VerticalAlign verticalAlign;
    private Boolean enabled;
    private Boolean inside;
    private Object rotation;
    private Object autoRotation;
    private Number autoRotationLimit;
    private Number staggerLines;
    private Number step;
    private Style style;
    private Number x;
    private Number y;
    private String _fn_formatter;
    private String format;
    private Color color;
    private Color connectorColor;
    private Boolean softConnector;
    private Number distance;
    private Color backgroundColor;
    private Number borderWidth;
    private Boolean useHTML;
    private Shape shape;

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
     * Returns current autoRotation settings. Can be a Boolean if
     * {@link #disableAutoRotation()} was called, <code>null</code> if
     * {@link #resetAutoRotation()} was called, or an Number[] if
     * {@link #setAutoRotation(Number[])} was used.
     * 
     * @see #setAutoRotation(Number[])
     * @see #disableAutoRotation()
     * @see #resetAutoRotation()
     * @return Current autoRotation settings.
     */

    public Object getAutoRotation() {
        return autoRotation;
    }

    /**
     * For axes the allowed degrees of label rotation to prevent overlapping
     * labels. If there is enough space, labels are not rotated. As the chart
     * gets narrower, it will start rotating the labels -45 degrees, then remove
     * every second label and try again with rotations 0 and -45 etc. Defaults
     * to [-45].
     * 
     * @see #disableAutoRotation() to disable autoRotation
     * @see #resetAutoRotation() to reset autoRotation configuration
     * 
     * @param autoRotation
     */
    public void setAutoRotation(Number... autoRotation) {
        this.autoRotation = autoRotation;
    }

    /**
     * Disables autoRotation by setting value to <code>false</code>, which will
     * cause the labels to word-wrap if possible.
     */
    public void disableAutoRotation() {
        autoRotation = false;
    }

    /**
     * Resets the previous autoRotation configuration enabling the default value
     * of [-45] to be used.
     */
    public void resetAutoRotation() {
        autoRotation = null;
    }

    /**
     * @see #setAutoRotationLimit(Number)
     * @return autoRotationLimit
     */
    public Number getAutoRotationLimit() {
        return autoRotationLimit;
    }

    /**
     * When each category width is more than this many pixels, we don't apply
     * auto rotation. Instead, we lay out the axis label with word wrap. A lower
     * limit makes sense when the label contains multiple short words that don't
     * extend the available horizontal space for each label. Defaults to
     * <code>80</code>.
     * 
     * @param autoRotationLimit
     */
    public void setAutoRotationLimit(Number autoRotationLimit) {
        this.autoRotationLimit = autoRotationLimit;
    }

    /**
     * Sets the rotation of the labels perpendicular to the axis. This is for
     * GAUGE type charts.
     * 
     */
    public void setRotationPerpendicular() {
        rotation = "auto";
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

    /**
     * @see #setFormat(String)
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * A format string for the data label. Available variables are the same as
     * for formatter. Defaults to {y}.
     * 
     * @see #setFormatter(String)
     * 
     * @param format
     *            the format to set
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @see #setSoftConnector(Boolean)
     * 
     * @return false if connectors don't use soft arc
     */
    public Boolean getSoftConnector() {
        return softConnector;
    }

    /**
     * Sets whether to render the connector as a soft arc or a line with sharp
     * break. Defaults to true.
     * 
     * @param softConnector
     *            the softConnector flag to set
     */
    public void setSoftConnector(Boolean softConnector) {
        this.softConnector = softConnector;
    }

    /**
     * @see #setVerticalAlign(VerticalAlign)
     * @return the verticalAlign
     */
    public VerticalAlign getVerticalAlign() {
        return verticalAlign;
    }

    /**
     * Sets the vertical alignment of a data label. Can be one of
     * {@link VerticalAlign#TOP}, {@link VerticalAlign#MIDDLE} or
     * {@link VerticalAlign#BOTTOM}. The default value depends on the data, for
     * instance in a column chart, the label is above positive values and below
     * negative values.
     * 
     * @param verticalAlign
     *            the verticalAlign to set
     */
    public void setVerticalAlign(VerticalAlign verticalAlign) {
        this.verticalAlign = verticalAlign;
    }

    /**
     * @return the inside
     * @see #setInside(Boolean)
     */
    public Boolean getInside() {
        return inside;
    }

    /**
     * For points with an extent, like columns, inside property defines whether
     * to align the data label inside the box or to the actual value point.
     * Defaults to false in most cases, true in stacked columns.
     * 
     * @param inside
     *            the explicit inside value to set
     */
    public void setInside(Boolean inside) {
        this.inside = inside;
    }

    /**
     * Returns current border width.
     * 
     * @return Border width.
     */
    public Number getBorderWidth() {
        return borderWidth;
    }

    /**
     * Sets new border width.
     * 
     * @param borderWidth
     *            New border width.
     */
    public void setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * Checks whether or not HTML is allowed.
     * 
     * @return <b>true</b> when HTML is allowed, <b>false</b> otherwise.
     */
    public Boolean getUseHTML() {
        return useHTML;
    }

    /**
     * Sets whether or not to allow using HTML.
     * 
     * @param useHTML
     *            Whether or not to allow using HTML.
     */
    public void setUseHTML(Boolean useHTML) {
        this.useHTML = useHTML;
    }

    /**
     * @see #setShape(Shape)
     * @return shape
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * The shape to use for the border around the label. Defaults to
     * {@link Shape#SQUARE}
     *
     * @param shape
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

}
