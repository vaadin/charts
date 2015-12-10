package com.vaadin.addon.charts.model;
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