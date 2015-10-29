package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.style.Style;
public class Navigation extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object activeColor;
	private Object animation;
	private Number arrowSize;
	private Object inactiveColor;
	private Style style;

	public Navigation() {
	}

	public Object getActiveColor() {
		return activeColor;
	}

	public void setActiveColor(Object activeColor) {
		this.activeColor = activeColor;
	}

	public Object getAnimation() {
		return animation;
	}

	public void setAnimation(Object animation) {
		this.animation = animation;
	}

	public Number getArrowSize() {
		return arrowSize;
	}

	public void setArrowSize(Number arrowSize) {
		this.arrowSize = arrowSize;
	}

	public Object getInactiveColor() {
		return inactiveColor;
	}

	public void setInactiveColor(Object inactiveColor) {
		this.inactiveColor = inactiveColor;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}
}