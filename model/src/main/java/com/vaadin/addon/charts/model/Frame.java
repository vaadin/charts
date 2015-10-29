package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
public class Frame extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Back back;
	private Bottom bottom;
	private Side side;

	public Frame() {
	}

	public Back getBack() {
		return back;
	}

	public void setBack(Back back) {
		this.back = back;
	}

	public Bottom getBottom() {
		return bottom;
	}

	public void setBottom(Bottom bottom) {
		this.bottom = bottom;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}
}