package com.vaadin.addon.charts.model;
public class Bottom extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object color;
	private Number size;

	public Bottom() {
	}

	public Object getColor() {
		return color;
	}

	public void setColor(Object color) {
		this.color = color;
	}

	public Number getSize() {
		return size;
	}

	public void setSize(Number size) {
		this.size = size;
	}
}