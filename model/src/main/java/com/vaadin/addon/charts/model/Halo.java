package com.vaadin.addon.charts.model;
public class Halo extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object attributes;
	private Number opacity;
	private Number size;

	public Halo() {
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public Number getOpacity() {
		return opacity;
	}

	public void setOpacity(Number opacity) {
		this.opacity = opacity;
	}

	public Number getSize() {
		return size;
	}

	public void setSize(Number size) {
		this.size = size;
	}
}