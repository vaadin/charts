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
/**
 * An array defining zones within a series. Zones can be applied to the X axis,
 * Y axis or Z axis for bubbles, according to the <code>zoneAxis</code> option.
 */
public class Zones extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Color color;
	private DashStyle dashStyle;
	private Color fillColor;
	private Number value;

	public Zones() {
	}

	/**
	 * @see #setColor(Color)
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Defines the color of the series.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @see #setDashStyle(DashStyle)
	 */
	public DashStyle getDashStyle() {
		return dashStyle;
	}

	/**
	 * A name for the dash style to use for the graph.
	 */
	public void setDashStyle(DashStyle dashStyle) {
		this.dashStyle = dashStyle;
	}

	/**
	 * @see #setFillColor(Color)
	 */
	public Color getFillColor() {
		return fillColor;
	}

	/**
	 * Defines the fill color for the series (in area type series)
	 * <p>
	 * Defaults to:
	 */
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	/**
	 * @see #setValue(Number)
	 */
	public Number getValue() {
		return value;
	}

	/**
	 * The value up to where the zone extends, if undefined the zones stretches
	 * to the last value in the series.
	 * <p>
	 * Defaults to: undefined
	 */
	public void setValue(Number value) {
		this.value = value;
	}
}