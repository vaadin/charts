package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2016 Vaadin Ltd
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

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.Style;
/**
 * Options for the paging or navigation appearing when the legend is overflown.
 */
public class LegendNavigation extends AbstractConfigurationObject {

	private Color activeColor;
	private Boolean animation;
	private Number arrowSize;
	private Color inactiveColor;
	private Style style;

	public LegendNavigation() {
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
	 * @see #setAnimation(Boolean)
	 */
	public Boolean getAnimation() {
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
	public void setAnimation(Boolean animation) {
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
		if (style == null) {
			style = new Style();
		}
		return style;
	}

	/**
	 * Text styles for the legend page navigation.
	 */
	public void setStyle(Style style) {
		this.style = style;
	}
}