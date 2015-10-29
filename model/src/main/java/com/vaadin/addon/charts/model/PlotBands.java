package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
public class PlotBands extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object borderColor;
	private Number borderWidth;
	private Object color;
	private Number from;
	private String id;
	private Object innerRadius;
	private Label label;
	private Object outerRadius;
	private Object thickness;
	private Number to;
	private Number zIndex;

	public PlotBands() {
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

	public Object getColor() {
		return color;
	}

	public void setColor(Object color) {
		this.color = color;
	}

	public Number getFrom() {
		return from;
	}

	public void setFrom(Number from) {
		this.from = from;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(Object innerRadius) {
		this.innerRadius = innerRadius;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Object getOuterRadius() {
		return outerRadius;
	}

	public void setOuterRadius(Object outerRadius) {
		this.outerRadius = outerRadius;
	}

	public Object getThickness() {
		return thickness;
	}

	public void setThickness(Object thickness) {
		this.thickness = thickness;
	}

	public Number getTo() {
		return to;
	}

	public void setTo(Number to) {
		this.to = to;
	}

	public Number getZIndex() {
		return zIndex;
	}

	public void setZIndex(Number zIndex) {
		this.zIndex = zIndex;
	}
}