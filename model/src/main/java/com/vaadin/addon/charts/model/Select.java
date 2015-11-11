package com.vaadin.addon.charts.model;
public class Select extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Boolean enabled;
	private Object fillColor;
	private Object lineColor;
	private Number lineWidth;
	private Number radius;

	public Select() {
	}

	public Select(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Object getFillColor() {
		return fillColor;
	}

	public void setFillColor(Object fillColor) {
		this.fillColor = fillColor;
	}

	public Object getLineColor() {
		return lineColor;
	}

	public void setLineColor(Object lineColor) {
		this.lineColor = lineColor;
	}

	public Number getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(Number lineWidth) {
		this.lineWidth = lineWidth;
	}

	public Number getRadius() {
		return radius;
	}

	public void setRadius(Number radius) {
		this.radius = radius;
	}
}