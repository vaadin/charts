/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model;

/**
 * Global options that don't apply to each chart. These options, like the
 * <code>lang</code> options, must be set using the
 * <code>Highcharts.setOptions</code> method.
 * 
 * <pre>
 * Highcharts.setOptions({
 * 	global: {
 * 		useUTC: false
 * 	}
 * });
 * </pre>
 */
public class Global extends AbstractConfigurationObject {

	private String VMLRadialGradientURL;
	private String canvasToolsURL;
	private Number timezoneOffset;
	private Boolean useUTC;

	public Global() {
	}

	/**
	 * @see #setVMLRadialGradientURL(String)
	 */
	public String getVMLRadialGradientURL() {
		return VMLRadialGradientURL;
	}

	/**
	 * Path to the pattern image required by VML browsers in order to draw
	 * radial gradients.
	 * <p>
	 * Defaults to:
	 * http://code.highcharts.com/{version}/gfx/vml-radial-gradient.png
	 */
	public void setVMLRadialGradientURL(String VMLRadialGradientURL) {
		this.VMLRadialGradientURL = VMLRadialGradientURL;
	}

	/**
	 * @see #setCanvasToolsURL(String)
	 */
	public String getCanvasToolsURL() {
		return canvasToolsURL;
	}

	/**
	 * The URL to the additional file to lazy load for Android 2.x devices.
	 * These devices don't support SVG, so we download a helper file that
	 * contains <a href="http://code.google.com/p/canvg/">canvg</a>, its
	 * dependency rbcolor, and our own CanVG Renderer class. To avoid hotlinking
	 * to our site, you can install canvas-tools.js on your own server and
	 * change this option accordingly.
	 * <p>
	 * Defaults to: http://code.highcharts.com/{version}/modules/canvas-tools.js
	 */
	public void setCanvasToolsURL(String canvasToolsURL) {
		this.canvasToolsURL = canvasToolsURL;
	}

	/**
	 * @see #setTimezoneOffset(Number)
	 */
	public Number getTimezoneOffset() {
		return timezoneOffset;
	}

	/**
	 * The timezone offset in minutes. Positive values are west, negative values
	 * are east of UTC, as in the ECMAScript <a href=
	 * "https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date/getTimezoneOffset"
	 * >getTimezoneOffset</a> method. Use this to display UTC based data in a
	 * predefined time zone.
	 * <p>
	 * Defaults to: 0
	 */
	public void setTimezoneOffset(Number timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}

	/**
	 * @see #setUseUTC(Boolean)
	 */
	public Boolean getUseUTC() {
		return useUTC;
	}

	/**
	 * Whether to use UTC time for axis scaling, tickmark placement and time
	 * display in <code>Highcharts.dateFormat</code>. Advantages of using UTC is
	 * that the time displays equally regardless of the user agent's time zone
	 * settings. Local time can be used when the data is loaded in real time or
	 * when correct Daylight Saving Time transitions are required.
	 * <p>
	 * Defaults to: true
	 */
	public void setUseUTC(Boolean useUTC) {
		this.useUTC = useUTC;
	}
}
