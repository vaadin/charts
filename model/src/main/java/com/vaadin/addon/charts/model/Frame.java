package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
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
/**
 * Provides the option to draw a frame around the charts by defining a bottom,
 * front and back panel.
 */
public class Frame extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Back back;
	private Bottom bottom;
	private Side side;

	public Frame() {
	}

	/**
	 * @see #setBack(Back)
	 */
	public Back getBack() {
		return back;
	}

	/**
	 * Defines the back panel of the frame around 3D charts.
	 * <p>
	 * Defaults to:
	 */
	public void setBack(Back back) {
		this.back = back;
	}

	/**
	 * @see #setBottom(Bottom)
	 */
	public Bottom getBottom() {
		return bottom;
	}

	/**
	 * The bottom of the frame around a 3D chart.
	 * <p>
	 * Defaults to:
	 */
	public void setBottom(Bottom bottom) {
		this.bottom = bottom;
	}

	/**
	 * @see #setSide(Side)
	 */
	public Side getSide() {
		return side;
	}

	/**
	 * The side for the frame around a 3D chart.
	 * <p>
	 * Defaults to:
	 */
	public void setSide(Side side) {
		this.side = side;
	}
}