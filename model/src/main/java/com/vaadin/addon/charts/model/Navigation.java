package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.Style;
/**
 * Options for the paging or navigation appearing when the legend is overflown.
 */
public class Navigation extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Color activeColor;
	private Object animation;
	private Number arrowSize;
	private Color inactiveColor;
	private Style style;
	private ButtonOptions buttonOptions;
	private Style menuItemHoverStyle;
	private Style menuItemStyle;
	private Style menuStyle;

	public Navigation() {
	}

	/**
	 * @see #setActiveColor(Color)
	 */
	public Color getActiveColor() {
		return activeColor;
	}

	/**
	 * The color for the active up or down arrow in the legend page navigation.
	 * <p>
	 * Defaults to: #3E576F
	 */
	public void setActiveColor(Color activeColor) {
		this.activeColor = activeColor;
	}

	/**
	 * @see #setAnimation(Object)
	 */
	public Object getAnimation() {
		return animation;
	}

	/**
	 * How to animate the pages when navigating up or down. A value of
	 * <code>true</code> applies the default navigation given in the
	 * chart.animation option. Additional options can be given as an object
	 * containing values for easing and duration. .
	 * <p>
	 * Defaults to: true
	 */
	public void setAnimation(Object animation) {
		this.animation = animation;
	}

	/**
	 * @see #setArrowSize(Number)
	 */
	public Number getArrowSize() {
		return arrowSize;
	}

	/**
	 * The pixel size of the up and down arrows in the legend paging navigation.
	 * .
	 * <p>
	 * Defaults to: 12
	 */
	public void setArrowSize(Number arrowSize) {
		this.arrowSize = arrowSize;
	}

	/**
	 * @see #setInactiveColor(Color)
	 */
	public Color getInactiveColor() {
		return inactiveColor;
	}

	/**
	 * The color of the inactive up or down arrow in the legend page navigation.
	 * .
	 * <p>
	 * Defaults to: #CCC
	 */
	public void setInactiveColor(Color inactiveColor) {
		this.inactiveColor = inactiveColor;
	}

	/**
	 * @see #setStyle(Style)
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * Text styles for the legend page navigation.
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	/**
	 * @see #setButtonOptions(ButtonOptions)
	 */
	public ButtonOptions getButtonOptions() {
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