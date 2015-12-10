package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;
public class LegendTitle extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Style style;
	private String text;

	public LegendTitle() {
	}

	/**
	 * @see #setStyle(Style)
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * Generic CSS styles for the legend title.
	 * <p>
	 * Defaults to: {"fontWeight":"bold"}
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	public LegendTitle(String text) {
		this.text = text;
	}

	/**
	 * @see #setText(String)
	 */
	public String getText() {
		return text;
	}

	/**
	 * A text or HTML string for the title.
	 * <p>
	 * Defaults to: null
	 */
	public void setText(String text) {
		this.text = text;
	}
}