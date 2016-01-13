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

import com.vaadin.addon.charts.model.style.Style;
/**
 * A title to be added on top of the legend.
 */
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