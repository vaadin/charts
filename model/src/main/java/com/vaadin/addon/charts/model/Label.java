package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2016 Vaadin Ltd
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
import com.vaadin.addon.charts.model.style.Color;
/**
 * Text labels for the plot bands
 */
public class Label extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private HorizontalAlign align;
	private Number rotation;
	private Style style;
	private String text;
	private String textAlign;
	private Boolean useHTML;
	private VerticalAlign verticalAlign;
	private Number x;
	private Number y;
	private Color backgroundColor;
	private Color borderColor;
	private Number borderRadius;
	private Number borderWidth;
	private String format;
	private String _fn_formatter;
	private Number padding;
	private Shape shape;

	public Label() {
	}

	/**
	 * @see #setAlign(HorizontalAlign)
	 */
	public HorizontalAlign getAlign() {
		return align;
	}

	/**
	 * Horizontal alignment of the label. Can be one of "left", "center" or
	 * "right".
	 * <p>
	 * Defaults to: left
	 */
	public void setAlign(HorizontalAlign align) {
		this.align = align;
	}

	/**
	 * @see #setRotation(Number)
	 */
	public Number getRotation() {
		return rotation;
	}

	/**
	 * Rotation of the text label in degrees. Defaults to 0 for horizontal plot
	 * lines and 90 for vertical lines.
	 */
	public void setRotation(Number rotation) {
		this.rotation = rotation;
	}

	/**
	 * @see #setStyle(Style)
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * CSS styles for the text label.
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	public Label(String text) {
		this.text = text;
	}

	/**
	 * @see #setText(String)
	 */
	public String getText() {
		return text;
	}

	/**
	 * The text itself. A subset of HTML is supported.
	 * <p>
	 * Defaults to:
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @see #setTextAlign(String)
	 */
	public String getTextAlign() {
		return textAlign;
	}

	/**
	 * The text alignment for the label. While <code>align</code> determines
	 * where the texts anchor point is placed within the plot band,
	 * <code>textAlign</code> determines how the text is aligned against its
	 * anchor point. Possible values are "left", "center" and "right". Defaults
	 * to the same as the <code>align</code> option.
	 */
	public void setTextAlign(String textAlign) {
		this.textAlign = textAlign;
	}

	/**
	 * @see #setUseHTML(Boolean)
	 */
	public Boolean getUseHTML() {
		return useHTML;
	}

	/**
	 * <p>
	 * Whether to <a href="http://docs.highcharts.com/#formatting$html">use
	 * HTML</a> to render the labels.
	 * <p>
	 * Defaults to: false
	 */
	public void setUseHTML(Boolean useHTML) {
		this.useHTML = useHTML;
	}

	/**
	 * @see #setVerticalAlign(VerticalAlign)
	 */
	public VerticalAlign getVerticalAlign() {
		return verticalAlign;
	}

	/**
	 * Vertical alignment of the label relative to the plot band. Can be one of
	 * "top", "middle" or "bottom".
	 * <p>
	 * Defaults to: top
	 */
	public void setVerticalAlign(VerticalAlign verticalAlign) {
		this.verticalAlign = verticalAlign;
	}

	/**
	 * @see #setX(Number)
	 */
	public Number getX() {
		return x;
	}

	/**
	 * Horizontal position relative the alignment. Default varies by
	 * orientation.
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
	 * Vertical position of the text baseline relative to the alignment. Default
	 * varies by orientation.
	 */
	public void setY(Number y) {
		this.y = y;
	}

	/**
	 * @see #setBackgroundColor(Color)
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * The background color for the label. Defaults to the related series color,
	 * or <code>gray</code> if that is not available.
	 * <p>
	 * Defaults to:
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @see #setBorderColor(Color)
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * The border color for the crosshair label
	 * <p>
	 * Defaults to:
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * @see #setBorderRadius(Number)
	 */
	public Number getBorderRadius() {
		return borderRadius;
	}

	/**
	 * The border corner radius of the crosshair label.
	 * <p>
	 * Defaults to: 3
	 */
	public void setBorderRadius(Number borderRadius) {
		this.borderRadius = borderRadius;
	}

	/**
	 * @see #setBorderWidth(Number)
	 */
	public Number getBorderWidth() {
		return borderWidth;
	}

	/**
	 * The border width for the crosshair label.
	 * <p>
	 * Defaults to: 0
	 */
	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
	}

	/**
	 * @see #setFormat(String)
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * A format string for the crosshair label. Defaults to <code>{value}</code>
	 * for numeric axes and <code>{value:%b %d, %Y}</code> for datetime axes.
	 * <p>
	 * Defaults to:
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	public String getFormatter() {
		return _fn_formatter;
	}

	public void setFormatter(String _fn_formatter) {
		this._fn_formatter = _fn_formatter;
	}

	/**
	 * @see #setPadding(Number)
	 */
	public Number getPadding() {
		return padding;
	}

	/**
	 * Padding inside the crosshair label.
	 * <p>
	 * Defaults to: 8
	 */
	public void setPadding(Number padding) {
		this.padding = padding;
	}

	/**
	 * @see #setShape(Shape)
	 */
	public Shape getShape() {
		return shape;
	}

	/**
	 * The shape to use for the label box.
	 * <p>
	 * Defaults to: callout
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}
}