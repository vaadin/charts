package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2018 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

import javax.annotation.Generated;
import com.vaadin.addon.charts.model.style.Style;
/**
 * A collection of options for buttons and menus appearing in the exporting
 * module.
 */
@Generated(value = "This class is generated and shouldn't be modified", comments = "Incorrect and missing API should be reported to https://github.com/vaadin/vaadin-charts-flow/issues/new")
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