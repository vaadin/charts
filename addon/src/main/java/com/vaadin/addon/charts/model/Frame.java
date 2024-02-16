/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model;

/**
 * Provides the option to draw a frame around the charts by defining a bottom,
 * front and back panel.
 */
public class Frame extends AbstractConfigurationObject {

	private Back back;
	private Bottom bottom;
	private Side side;

	public Frame() {
	}

	/**
	 * @see #setBack(Back)
	 */
	public Back getBack() {
		if (back == null) {
			back = new Back();
		}
		return back;
	}

	/**
	 * Defines the back panel of the frame around 3D charts.
	 */
	public void setBack(Back back) {
		this.back = back;
	}

	/**
	 * @see #setBottom(Bottom)
	 */
	public Bottom getBottom() {
		if (bottom == null) {
			bottom = new Bottom();
		}
		return bottom;
	}

	/**
	 * The bottom of the frame around a 3D chart.
	 */
	public void setBottom(Bottom bottom) {
		this.bottom = bottom;
	}

	/**
	 * @see #setSide(Side)
	 */
	public Side getSide() {
		if (side == null) {
			side = new Side();
		}
		return side;
	}

	/**
	 * The side for the frame around a 3D chart.
	 */
	public void setSide(Side side) {
		this.side = side;
	}
}
