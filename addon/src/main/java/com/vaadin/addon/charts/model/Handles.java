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

import com.vaadin.addon.charts.model.style.Color;
/**
 * Options for the handles for dragging the zoomed area.
 */
public class Handles extends AbstractConfigurationObject {

	private Color backgroundColor;
	private Color borderColor;

	public Handles() {
	}

	/**
	 * @see #setBackgroundColor(Color)
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * The fill for the handle.
	 * <p>
	 * Defaults to: #ebe7e8
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @see #setBorderColor(Color)
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * The stroke for the handle border and the stripes inside.
	 * <p>
	 * Defaults to: #b2b1b6
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
}
