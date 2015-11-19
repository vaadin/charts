package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;
public class Navigation extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object activeColor;
	private Object animation;
	private Number arrowSize;
	private Object inactiveColor;
	private Style style;
	private ButtonOptions buttonOptions;
	private Style menuItemHoverStyle;
	private Style menuItemStyle;
	private Style menuStyle;

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

	public ButtonOptions getButtonOptions() {
		return buttonOptions;
	}

	public void setButtonOptions(ButtonOptions buttonOptions) {
		this.buttonOptions = buttonOptions;
	}

	public Style getMenuItemHoverStyle() {
		return menuItemHoverStyle;
	}

	public void setMenuItemHoverStyle(Style menuItemHoverStyle) {
		this.menuItemHoverStyle = menuItemHoverStyle;
	}

	public Style getMenuItemStyle() {
		return menuItemStyle;
	}

	public void setMenuItemStyle(Style menuItemStyle) {
		this.menuItemStyle = menuItemStyle;
	}

	public Style getMenuStyle() {
		return menuStyle;
	}

	public void setMenuStyle(Style menuStyle) {
		this.menuStyle = menuStyle;
	}
}