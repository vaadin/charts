package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
public class Dial extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
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

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBaseLength() {
		return baseLength;
	}

	public void setBaseLength(String baseLength) {
		this.baseLength = baseLength;
	}

	public Number getBaseWidth() {
		return baseWidth;
	}

	public void setBaseWidth(Number baseWidth) {
		this.baseWidth = baseWidth;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Number getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getRearLength() {
		return rearLength;
	}

	public void setRearLength(String rearLength) {
		this.rearLength = rearLength;
	}

	public Number getTopWidth() {
		return topWidth;
	}

	public void setTopWidth(Number topWidth) {
		this.topWidth = topWidth;
	}
}