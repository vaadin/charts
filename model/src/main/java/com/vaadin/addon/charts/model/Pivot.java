package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
public class Pivot extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Color backgroundColor;
	private Color borderColor;
	private Number borderWidth;
	private Number radius;

	public Pivot() {
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
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

	public Number getRadius() {
		return radius;
	}

	public void setRadius(Number radius) {
		this.radius = radius;
	}
}