package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
public class Pivot extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object backgroundColor;
	private Object borderColor;
	private Number borderWidth;
	private Number radius;

	public Pivot() {
	}

	public Object getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Object backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Object getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Object borderColor) {
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