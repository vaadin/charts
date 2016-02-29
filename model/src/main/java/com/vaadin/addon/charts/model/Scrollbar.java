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

import com.vaadin.addon.charts.model.style.Color;
/**
 * The scrollbar is a means of panning over the X axis of a chart.
 */
public class Scrollbar extends AbstractConfigurationObject {

	private Color barBackgroundColor;
	private Color barBorderColor;
	private Number barBorderRadius;
	private Number barBorderWidth;
	private Color buttonArrowColor;
	private Color buttonBackgroundColor;
	private Color buttonBorderColor;
	private Number buttonBorderRadius;
	private Number buttonBorderWidth;
	private Boolean enabled;
	private Number height;
	private Boolean liveRedraw;
	private Number minWidth;
	private Color rifleColor;
	private Color trackBackgroundColor;
	private Number trackBorderRadius;

	public Scrollbar() {
	}

	/**
	 * @see #setBarBackgroundColor(Color)
	 */
	public Color getBarBackgroundColor() {
		return barBackgroundColor;
	}

	/**
	 * The background color of the scrollbar itself.
	 * <p>
	 * Defaults to: #bfc8d1
	 */
	public void setBarBackgroundColor(Color barBackgroundColor) {
		this.barBackgroundColor = barBackgroundColor;
	}

	/**
	 * @see #setBarBorderColor(Color)
	 */
	public Color getBarBorderColor() {
		return barBorderColor;
	}

	/**
	 * The color of the scrollbar's border.
	 * <p>
	 * Defaults to: #bfc8d1
	 */
	public void setBarBorderColor(Color barBorderColor) {
		this.barBorderColor = barBorderColor;
	}

	/**
	 * @see #setBarBorderRadius(Number)
	 */
	public Number getBarBorderRadius() {
		return barBorderRadius;
	}

	/**
	 * The border rounding radius of the bar.
	 * <p>
	 * Defaults to: 0
	 */
	public void setBarBorderRadius(Number barBorderRadius) {
		this.barBorderRadius = barBorderRadius;
	}

	/**
	 * @see #setBarBorderWidth(Number)
	 */
	public Number getBarBorderWidth() {
		return barBorderWidth;
	}

	/**
	 * The width of the bar's border.
	 * <p>
	 * Defaults to: 1
	 */
	public void setBarBorderWidth(Number barBorderWidth) {
		this.barBorderWidth = barBorderWidth;
	}

	/**
	 * @see #setButtonArrowColor(Color)
	 */
	public Color getButtonArrowColor() {
		return buttonArrowColor;
	}

	/**
	 * The color of the small arrow inside the scrollbar buttons.
	 * <p>
	 * Defaults to: #666
	 */
	public void setButtonArrowColor(Color buttonArrowColor) {
		this.buttonArrowColor = buttonArrowColor;
	}

	/**
	 * @see #setButtonBackgroundColor(Color)
	 */
	public Color getButtonBackgroundColor() {
		return buttonBackgroundColor;
	}

	/**
	 * The color of scrollbar buttons.
	 * <p>
	 * Defaults to: #ebe7e8
	 */
	public void setButtonBackgroundColor(Color buttonBackgroundColor) {
		this.buttonBackgroundColor = buttonBackgroundColor;
	}

	/**
	 * @see #setButtonBorderColor(Color)
	 */
	public Color getButtonBorderColor() {
		return buttonBorderColor;
	}

	/**
	 * The color of the border of the scrollbar buttons.
	 * <p>
	 * Defaults to: #bbbbbb
	 */
	public void setButtonBorderColor(Color buttonBorderColor) {
		this.buttonBorderColor = buttonBorderColor;
	}

	/**
	 * @see #setButtonBorderRadius(Number)
	 */
	public Number getButtonBorderRadius() {
		return buttonBorderRadius;
	}

	/**
	 * The corner radius of the scrollbar buttons.
	 * <p>
	 * Defaults to: 0
	 */
	public void setButtonBorderRadius(Number buttonBorderRadius) {
		this.buttonBorderRadius = buttonBorderRadius;
	}

	/**
	 * @see #setButtonBorderWidth(Number)
	 */
	public Number getButtonBorderWidth() {
		return buttonBorderWidth;
	}

	/**
	 * The border width of the scrollbar buttons.
	 * <p>
	 * Defaults to: 1
	 */
	public void setButtonBorderWidth(Number buttonBorderWidth) {
		this.buttonBorderWidth = buttonBorderWidth;
	}

	public Scrollbar(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setEnabled(Boolean)
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Enable or disable the scrollbar.
	 * <p>
	 * Defaults to: true
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setHeight(Number)
	 */
	public Number getHeight() {
		return height;
	}

	/**
	 * The height of the scrollbar. The height also applies to the width of the
	 * scroll arrows so that they are always squares. Defaults to 20 for touch
	 * devices and 14 for mouse devices.
	 */
	public void setHeight(Number height) {
		this.height = height;
	}

	/**
	 * @see #setLiveRedraw(Boolean)
	 */
	public Boolean getLiveRedraw() {
		return liveRedraw;
	}

	/**
	 * Whether to redraw the main chart as the scrollbar or the navigator zoomed
	 * window is moved. Defaults to <code>true</code> for modern browsers and
	 * <code>false</code> for legacy IE browsers as well as mobile devices.
	 */
	public void setLiveRedraw(Boolean liveRedraw) {
		this.liveRedraw = liveRedraw;
	}

	/**
	 * @see #setMinWidth(Number)
	 */
	public Number getMinWidth() {
		return minWidth;
	}

	/**
	 * The minimum width of the scrollbar.
	 * <p>
	 * Defaults to: 6
	 */
	public void setMinWidth(Number minWidth) {
		this.minWidth = minWidth;
	}

	/**
	 * @see #setRifleColor(Color)
	 */
	public Color getRifleColor() {
		return rifleColor;
	}

	/**
	 * The color of the small rifles in the middle of the scrollbar.
	 * <p>
	 * Defaults to: #666
	 */
	public void setRifleColor(Color rifleColor) {
		this.rifleColor = rifleColor;
	}

	/**
	 * @see #setTrackBackgroundColor(Color)
	 */
	public Color getTrackBackgroundColor() {
		return trackBackgroundColor;
	}

	/**
	 * The color of the track background.
	 * <p>
	 * Defaults to: #eeeeee
	 */
	public void setTrackBackgroundColor(Color trackBackgroundColor) {
		this.trackBackgroundColor = trackBackgroundColor;
	}

	/**
	 * @see #setTrackBorderRadius(Number)
	 */
	public Number getTrackBorderRadius() {
		return trackBorderRadius;
	}

	/**
	 * The corner radius of the border of the scrollbar track.
	 * <p>
	 * Defaults to: 0
	 */
	public void setTrackBorderRadius(Number trackBorderRadius) {
		this.trackBorderRadius = trackBorderRadius;
	}
}