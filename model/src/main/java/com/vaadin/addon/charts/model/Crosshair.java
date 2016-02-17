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
 * Configure a crosshair that follows either the mouse pointer or the hovered
 * point. By default, the crosshair is enabled on the X axis and disabled on Y
 * axis.
 */
public class Crosshair extends AbstractConfigurationObject {

	private Color color;
	private DashStyle dashStyle;
	private Boolean snap;
	private Number width;
	private Number zIndex;
	private CrosshairLabel label;

	public Crosshair() {
	}

	/**
	 * @see #setColor(Color)
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * The color of the crosshair. Defaults to <code>#C0C0C0</code> for numeric
	 * and datetime axes, and <code>rgba(155,200,255,0.2)</code> for category
	 * axes, where the crosshair by default highlights the whole category.
	 * <p>
	 * Defaults to:
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
	 * The dash style for the crosshair. See <a
	 * href="#plotOptions.series.dashStyle">series.dashStyle</a> for possible
	 * values.
	 * <p>
	 * Defaults to: Solid
	 */
	public void setDashStyle(DashStyle dashStyle) {
		this.dashStyle = dashStyle;
	}

	/**
	 * @see #setSnap(Boolean)
	 */
	public Boolean getSnap() {
		return snap;
	}

	/**
	 * Whether the crosshair should snap to the point or follow the pointer
	 * independent of points.
	 * <p>
	 * Defaults to: true
	 */
	public void setSnap(Boolean snap) {
		this.snap = snap;
	}

	/**
	 * @see #setWidth(Number)
	 */
	public Number getWidth() {
		return width;
	}

	/**
	 * The pixel width of the crosshair. Defaults to 1 for numeric or datetime
	 * axes, and for one category width for category axes.
	 * <p>
	 * Defaults to:
	 */
	public void setWidth(Number width) {
		this.width = width;
	}

	/**
	 * @see #setZIndex(Number)
	 */
	public Number getZIndex() {
		return zIndex;
	}

	/**
	 * The Z index of the crosshair. Higher Z indices allow drawing the
	 * crosshair on top of the series or behind the grid lines.
	 * <p>
	 * Defaults to: 2
	 */
	public void setZIndex(Number zIndex) {
		this.zIndex = zIndex;
	}

	/**
	 * @see #setLabel(CrosshairLabel)
	 */
	public CrosshairLabel getLabel() {
		if (label == null) {
			label = new CrosshairLabel();
		}
		return label;
	}

	/**
	 * A label on the axis next to the crosshair.
	 */
	public void setLabel(CrosshairLabel label) {
		this.label = label;
	}
}