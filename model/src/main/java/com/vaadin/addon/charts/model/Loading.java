package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.style.Style;
public class Loading extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Number hideDuration;
	private Style labelStyle;
	private Number showDuration;
	private Style style;

	public Loading() {
	}

	public Number getHideDuration() {
		return hideDuration;
	}

	public void setHideDuration(Number hideDuration) {
		this.hideDuration = hideDuration;
	}

	public Style getLabelStyle() {
		return labelStyle;
	}

	public void setLabelStyle(Style labelStyle) {
		this.labelStyle = labelStyle;
	}

	public Number getShowDuration() {
		return showDuration;
	}

	public void setShowDuration(Number showDuration) {
		this.showDuration = showDuration;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}
}