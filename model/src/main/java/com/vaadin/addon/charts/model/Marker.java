package com.vaadin.addon.charts.model;
public class Marker extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Boolean enabled;
	private Object fillColor;
	private Number height;
	private Object lineColor;
	private Number lineWidth;
	private Number radius;
	private States states;
	private String symbol;
	private Number width;

	public Marker() {
	}

	public Marker(Boolean enabled) {
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

	public Number getHeight() {
		return height;
	}

	public void setHeight(Number height) {
		this.height = height;
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

	public States getStates() {
		return states;
	}

	public void setStates(States states) {
		this.states = states;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Number getWidth() {
		return width;
	}

	public void setWidth(Number width) {
		this.width = width;
	}
}