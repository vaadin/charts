/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;

/**
 * A collection of options for buttons and menus appearing in the exporting
 * module.
 */
public class Navigation extends AbstractConfigurationObject {

	private ButtonOptions buttonOptions;
	private Style menuItemHoverStyle;
	private Style menuItemStyle;
	private Style menuStyle;

	public Navigation() {
	}

	/**
	 * @see #setButtonOptions(ButtonOptions)
	 */
	public ButtonOptions getButtonOptions() {
		if (buttonOptions == null) {
			buttonOptions = new ButtonOptions();
		}
		return buttonOptions;
	}

	/**
	 * A collection of options for buttons appearing in the exporting module.
	 */
	public void setButtonOptions(ButtonOptions buttonOptions) {
		this.buttonOptions = buttonOptions;
	}

	/**
	 * @see #setMenuItemHoverStyle(Style)
	 */
	public Style getMenuItemHoverStyle() {
		if (menuItemHoverStyle == null) {
			menuItemHoverStyle = new Style();
		}
		return menuItemHoverStyle;
	}

	/**
	 * CSS styles for the hover state of the individual items within the popup
	 * menu appearing by default when the export icon is clicked. The menu items
	 * are rendered in HTML. Defaults to
	 * 
	 * <pre>
	 * menuItemHoverStyle: {
	 * 		background: '#4572A5',
	 * 		color: '#FFFFFF'
	 * 	}
	 * </pre>
	 */
	public void setMenuItemHoverStyle(Style menuItemHoverStyle) {
		this.menuItemHoverStyle = menuItemHoverStyle;
	}

	/**
	 * @see #setMenuItemStyle(Style)
	 */
	public Style getMenuItemStyle() {
		if (menuItemStyle == null) {
			menuItemStyle = new Style();
		}
		return menuItemStyle;
	}

	/**
	 * CSS styles for the individual items within the popup menu appearing by
	 * default when the export icon is clicked. The menu items are rendered in
	 * HTML. Defaults to
	 * 
	 * <pre>
	 * menuItemStyle: {
	 * 		padding: '0 5px',
	 * 		background: NONE,
	 * 		color: '#303030'
	 * 	}
	 * </pre>
	 */
	public void setMenuItemStyle(Style menuItemStyle) {
		this.menuItemStyle = menuItemStyle;
	}

	/**
	 * @see #setMenuStyle(Style)
	 */
	public Style getMenuStyle() {
		if (menuStyle == null) {
			menuStyle = new Style();
		}
		return menuStyle;
	}

	/**
	 * CSS styles for the popup menu appearing by default when the export icon
	 * is clicked. This menu is rendered in HTML. Defaults to
	 * 
	 * <pre>
	 * menuStyle: {
	 * 		border: '1px solid #A0A0A0',
	 * 		background: '#FFFFFF'
	 * 	}
	 * </pre>
	 */
	public void setMenuStyle(Style menuStyle) {
		this.menuStyle = menuStyle;
	}
}
