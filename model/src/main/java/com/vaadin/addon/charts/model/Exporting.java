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

/**
 * Options for the exporting module. For an overview on the matter, see <a
 * href="http://www.highcharts.com/docs/export-module/export-module-overview"
 * >the docs</a>.
 */
public class Exporting extends AbstractConfigurationObject {

	private Boolean allowHTML;
	private Buttons buttons;
	private Boolean enabled;
	private Boolean fallbackToExportServer;
	private String filename;
	private Number printMaxWidth;
	private Number scale;
	private Number sourceHeight;
	private Number sourceWidth;
	private String type;
	private String url;
	private Number width;

	public Exporting() {
	}

	/**
	 * @see #setAllowHTML(Boolean)
	 */
	public Boolean getAllowHTML() {
		return allowHTML;
	}

	/**
	 * <p>
	 * Experimental setting to allow HTML inside the chart (added through the
	 * <code>useHTML</code> options), directly in the exported image. This
	 * allows you to preserve complicated HTML structures like tables or
	 * bi-directional text in exported charts.
	 * </p>
	 * 
	 * <p>
	 * Disclaimer: The HTML is rendered in a <code>foreignObject</code> tag in
	 * the generated SVG. The official export server is based on PhantomJS,
	 * which supports this, but other SVG clients, like Batik, does not support
	 * it. This also applies to downloaded SVG that you want to open in a
	 * desktop client.
	 * </p>
	 * <p>
	 * Defaults to: false
	 */
	public void setAllowHTML(Boolean allowHTML) {
		this.allowHTML = allowHTML;
	}

	/**
	 * @see #setButtons(Buttons)
	 */
	public Buttons getButtons() {
		if (buttons == null) {
			buttons = new Buttons();
		}
		return buttons;
	}

	/**
	 * Options for the export related buttons, print and export. In addition to
	 * the default buttons listed here, custom buttons can be added. See <a
	 * href="#navigation.buttonOptions">navigation.buttonOptions</a> for general
	 * options.
	 */
	public void setButtons(Buttons buttons) {
		this.buttons = buttons;
	}

	public Exporting(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setEnabled(Boolean)
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Whether to enable the exporting module. Disabling the module will hide
	 * the context button, but API methods will still be available.
	 * <p>
	 * Defaults to: true
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setFallbackToExportServer(Boolean)
	 */
	public Boolean getFallbackToExportServer() {
		return fallbackToExportServer;
	}

	/**
	 * Whether or not to fall back to the export server if the offline-exporting
	 * module is unable to export the chart on the client side.
	 * <p>
	 * Defaults to: true
	 */
	public void setFallbackToExportServer(Boolean fallbackToExportServer) {
		this.fallbackToExportServer = fallbackToExportServer;
	}

	/**
	 * @see #setFilename(String)
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * The filename, without extension, to use for the exported chart.
	 * <p>
	 * Defaults to: chart
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @see #setPrintMaxWidth(Number)
	 */
	public Number getPrintMaxWidth() {
		return printMaxWidth;
	}

	/**
	 * When printing the chart from the menu item in the burger menu, if the
	 * on-screen chart exceeds this width, it is resized. After printing or
	 * cancelled, it is restored. The default width makes the chart fit into
	 * typical paper format. Note that this does not affect the chart when
	 * printing the web page as a whole.
	 * <p>
	 * Defaults to: 780
	 */
	public void setPrintMaxWidth(Number printMaxWidth) {
		this.printMaxWidth = printMaxWidth;
	}

	/**
	 * @see #setScale(Number)
	 */
	public Number getScale() {
		return scale;
	}

	/**
	 * Defines the scale or zoom factor for the exported image compared to the
	 * on-screen display. While for instance a 600px wide chart may look good on
	 * a website, it will look bad in print. The default scale of 2 makes this
	 * chart export to a 1200px PNG or JPG.
	 * <p>
	 * Defaults to: 2
	 */
	public void setScale(Number scale) {
		this.scale = scale;
	}

	/**
	 * @see #setSourceHeight(Number)
	 */
	public Number getSourceHeight() {
		return sourceHeight;
	}

	/**
	 * Analogous to <a href="#exporting.sourceWidth">sourceWidth</a>
	 */
	public void setSourceHeight(Number sourceHeight) {
		this.sourceHeight = sourceHeight;
	}

	/**
	 * @see #setSourceWidth(Number)
	 */
	public Number getSourceWidth() {
		return sourceWidth;
	}

	/**
	 * The width of the original chart when exported, unless an explicit <a
	 * href="#chart.width">chart.width</a> is set. The width exported raster
	 * image is then multiplied by <a href="#exporting.scale">scale</a>.
	 */
	public void setSourceWidth(Number sourceWidth) {
		this.sourceWidth = sourceWidth;
	}

	/**
	 * @see #setType(String)
	 */
	public String getType() {
		return type;
	}

	/**
	 * Default MIME type for exporting if <code>chart.exportChart()</code> is
	 * called without specifying a <code>type</code> option. Possible values are
	 * <code>image/png</code>, <code>image/jpeg</code>,
	 * <code>application/pdf</code> and <code>image/svg+xml</code>.
	 * <p>
	 * Defaults to: image/png
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @see #setUrl(String)
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * The URL for the server module converting the SVG string to an image
	 * format. By default this points to Highchart's free web service.
	 * <p>
	 * Defaults to: http://export.highcharts.com
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @see #setWidth(Number)
	 */
	public Number getWidth() {
		return width;
	}

	/**
	 * The pixel width of charts exported to PNG or JPG. As of Highcharts 3.0,
	 * the default pixel width is a function of the <a
	 * href="#chart.width">chart.width</a> or <a
	 * href="#exporting.sourceWidth">exporting.sourceWidth</a> and the <a
	 * href="#exporting.scale">exporting.scale</a>.
	 * <p>
	 * Defaults to: undefined
	 */
	public void setWidth(Number width) {
		this.width = width;
	}
}