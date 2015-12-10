package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
public class Marker extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Boolean enabled;
	private Color fillColor;
	private Number height;
	private Color lineColor;
	private Number lineWidth;
	private Number radius;
	private States states;
	private Number width;
	private MarkerSymbol symbol;

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

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public Number getHeight() {
		return height;
	}

	public void setHeight(Number height) {
		this.height = height;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
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

	public Number getWidth() {
		return width;
	}

	public void setWidth(Number width) {
		this.width = width;
	}

	public MarkerSymbol getSymbol() {
		return symbol;
	}

	public void setSymbol(MarkerSymbol symbol) {
		this.symbol = symbol;
	}
}