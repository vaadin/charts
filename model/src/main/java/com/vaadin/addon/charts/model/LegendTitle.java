package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;
public class LegendTitle extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Style style;
	private String text;

	public LegendTitle() {
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public LegendTitle(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}