/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
/**
 * Options for the dial or arrow pointer of the gauge.
 */
public class Dial extends AbstractConfigurationObject {

	private Color backgroundColor;
	private String baseLength;
	private Number baseWidth;
	private Color borderColor;
	private Number borderWidth;
	private String radius;
	private String rearLength;
	private Number topWidth;

	public Dial() {
	}

	/**
	 * @see #setBackgroundColor(Color)
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * The background or fill color of the gauge's dial.
	 * <p>
	 * Defaults to: black
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @see #setBaseLength(String)
	 */
	public String getBaseLength() {
		return baseLength;
	}

	/**
	 * The length of the dial's base part, relative to the total radius or
	 * length of the dial.
	 * <p>
	 * Defaults to: 70%
	 */
	public void setBaseLength(String baseLength) {
		this.baseLength = baseLength;
	}

	/**
	 * @see #setBaseWidth(Number)
	 */
	public Number getBaseWidth() {
		return baseWidth;
	}

	/**
	 * The pixel width of the base of the gauge dial. The base is the part
	 * closest to the pivot, defined by baseLength.
	 * <p>
	 * Defaults to: 3
	 */
	public void setBaseWidth(Number baseWidth) {
		this.baseWidth = baseWidth;
	}

	/**
	 * @see #setBorderColor(Color)
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * The border color or stroke of the gauge's dial. By default, the
	 * borderWidth is 0, so this must be set in addition to a custom border
	 * color.
	 * <p>
	 * Defaults to: silver
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * @see #setBorderWidth(Number)
	 */
	public Number getBorderWidth() {
		return borderWidth;
	}

	/**
	 * The width of the gauge dial border in pixels.
	 * <p>
	 * Defaults to: 0
	 */
	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
	}

	/**
	 * @see #setRadius(String)
	 */
	public String getRadius() {
		return radius;
	}

	/**
	 * The radius or length of the dial, in percentages relative to the radius
	 * of the gauge itself.
	 * <p>
	 * Defaults to: 80%
	 */
	public void setRadius(String radius) {
		this.radius = radius;
	}

	/**
	 * @see #setRearLength(String)
	 */
	public String getRearLength() {
		return rearLength;
	}

	/**
	 * The length of the dial's rear end, the part that extends out on the other
	 * side of the pivot. Relative to the dial's length.
	 * <p>
	 * Defaults to: 10%
	 */
	public void setRearLength(String rearLength) {
		this.rearLength = rearLength;
	}

	/**
	 * @see #setTopWidth(Number)
	 */
	public Number getTopWidth() {
		return topWidth;
	}

	/**
	 * The width of the top of the dial, closest to the perimeter. The pivot
	 * narrows in from the base to the top.
	 * <p>
	 * Defaults to: 1
	 */
	public void setTopWidth(Number topWidth) {
		this.topWidth = topWidth;
	}
}