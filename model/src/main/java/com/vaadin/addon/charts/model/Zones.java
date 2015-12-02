package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
public class Zones extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Color color;
	private String dashStyle;
	private Color fillColor;
	private Number value;

	public Zones() {
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getDashStyle() {
		return dashStyle;
	}

	public void setDashStyle(String dashStyle) {
		this.dashStyle = dashStyle;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}
}