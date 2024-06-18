/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;
/**
 * Highchart by default puts a credits label in the lower right corner of the
 * chart. This can be changed using these options.
 */
public class Credits extends AbstractConfigurationObject {

	private Boolean enabled;
	private String href;
	private Position position;
	private Style style;
	private String text;

	public Credits() {
	}

	public Credits(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setEnabled(Boolean)
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Whether to show the credits text.
	 * <p>
	 * Defaults to: true
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setHref(String)
	 */
	public String getHref() {
		return href;
	}

	/**
	 * The URL for the credits label.
	 * <p>
	 * Defaults to: http://www.highcharts.com
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @see #setPosition(Position)
	 */
	public Position getPosition() {
		if (position == null) {
			position = new Position();
		}
		return position;
	}

	/**
	 * Position configuration for the credits label. Supported properties are
	 * <code>align</code>, <code>verticalAlign</code>, <code>x</code> and
	 * <code>y</code>. Defaults to
	 * 
	 * <pre>
	 * position: {
	 * 		align: 'right',
	 * 		x: -10,
	 * 		verticalAlign: 'bottom',
	 * 		y: -5
	 * 	}
	 * </pre>
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @see #setStyle(Style)
	 */
	public Style getStyle() {
		if (style == null) {
			style = new Style();
		}
		return style;
	}

	/**
	 * CSS styles for the credits label. Defaults to:
	 * 
	 * <pre>
	 * style: {
	 * 		cursor: 'pointer',
	 * 		color: '#909090',
	 * 		fontSize: '10px'
	 * 
	 * 	}
	 * </pre>
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	public Credits(String text) {
		this.text = text;
	}

	/**
	 * @see #setText(String)
	 */
	public String getText() {
		return text;
	}

	/**
	 * The text for the credits label.
	 * <p>
	 * Defaults to: Highcharts.com
	 */
	public void setText(String text) {
		this.text = text;
	}
}