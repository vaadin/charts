package com.vaadin.addon.charts.model;
public class Crosshair extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object color;
	private String dashStyle;
	private Boolean snap;
	private Number width;
	private Number zIndex;

	public Crosshair() {
	}

	public Object getColor() {
		return color;
	}

	public void setColor(Object color) {
		this.color = color;
	}

	public String getDashStyle() {
		return dashStyle;
	}

	public void setDashStyle(String dashStyle) {
		this.dashStyle = dashStyle;
	}

	public Boolean getSnap() {
		return snap;
	}

	public void setSnap(Boolean snap) {
		this.snap = snap;
	}

	public Number getWidth() {
		return width;
	}

	public void setWidth(Number width) {
		this.width = width;
	}

	public Number getZIndex() {
		return zIndex;
	}

	public void setZIndex(Number zIndex) {
		this.zIndex = zIndex;
	}
}