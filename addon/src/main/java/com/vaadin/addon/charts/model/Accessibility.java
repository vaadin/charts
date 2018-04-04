package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2018 Vaadin Ltd
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

import javax.annotation.Generated;
/**
 * <p>
 * Options for configuring accessibility for the chart. Requires the <a
 * href="//code.highcharts.com/modules/accessibility.js">accessibility
 * module</a> to be loaded. For a description of the module and information on
 * its features, see <a
 * href="http://www.highcharts.com/docs/chart-concepts/accessibility">Highcharts
 * Accessibility</a>.
 * </p>
 */
@Generated(value = "This class is generated and shouldn't be modified", comments = "Incorrect and missing API should be reported to https://github.com/vaadin/charts/issues/new")
public class Accessibility extends AbstractConfigurationObject {

	private Boolean describeSingleSeries;
	private Boolean enabled;
	private KeyboardNavigation keyboardNavigation;
	private Object onTableAnchorClick;
	private String pointDateFormat;
	private Object pointDateFormatter;
	private Object pointDescriptionFormatter;
	private Object screenReaderSectionFormatter;
	private Object seriesDescriptionFormatter;

	public Accessibility() {
	}

	/**
	 * @see #setDescribeSingleSeries(Boolean)
	 */
	public Boolean getDescribeSingleSeries() {
		return describeSingleSeries;
	}

	/**
	 * Whether or not to add series descriptions to charts with a single series.
	 * <p>
	 * Defaults to: false
	 */
	public void setDescribeSingleSeries(Boolean describeSingleSeries) {
		this.describeSingleSeries = describeSingleSeries;
	}

	public Accessibility(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setEnabled(Boolean)
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Enable accessibility features for the chart.
	 * <p>
	 * Defaults to: true
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setKeyboardNavigation(KeyboardNavigation)
	 */
	public KeyboardNavigation getKeyboardNavigation() {
		if (keyboardNavigation == null) {
			keyboardNavigation = new KeyboardNavigation();
		}
		return keyboardNavigation;
	}

	/**
	 * Options for keyboard navigation.
	 */
	public void setKeyboardNavigation(KeyboardNavigation keyboardNavigation) {
		this.keyboardNavigation = keyboardNavigation;
	}

	/**
	 * @see #setOnTableAnchorClick(Object)
	 */
	public Object getOnTableAnchorClick() {
		if (onTableAnchorClick == null) {
			onTableAnchorClick = new Object();
		}
		return onTableAnchorClick;
	}

	/**
	 * <p>
	 * Function to run upon clicking the "View as Data Table" link in the screen
	 * reader region.
	 * </p>
	 * 
	 * <p>
	 * By default Highcharts will insert and set focus to a data table
	 * representation of the chart.
	 * </p>
	 */
	public void setOnTableAnchorClick(Object onTableAnchorClick) {
		this.onTableAnchorClick = onTableAnchorClick;
	}

	/**
	 * @see #setPointDateFormat(String)
	 */
	public String getPointDateFormat() {
		return pointDateFormat;
	}

	/**
	 * <p>
	 * Date format to use for points on datetime axes when describing them to
	 * screen reader users.
	 * </p>
	 * <p>
	 * Defaults to the same format as in tooltip.
	 * </p>
	 * <p>
	 * For an overview of the replacement codes, see <a
	 * href="#Highcharts.dateFormat">dateFormat</a>.
	 * </p>
	 */
	public void setPointDateFormat(String pointDateFormat) {
		this.pointDateFormat = pointDateFormat;
	}

	/**
	 * @see #setPointDateFormatter(Object)
	 */
	public Object getPointDateFormatter() {
		if (pointDateFormatter == null) {
			pointDateFormatter = new Object();
		}
		return pointDateFormatter;
	}

	/**
	 * <p>
	 * Formatter function to determine the date/time format used with points on
	 * datetime axes when describing them to screen reader users. Receives one
	 * argument, <code>point</code>, referring to the point to describe. Should
	 * return a date format string compatible with <a
	 * href="#Highcharts.dateFormat">dateFormat</a>.
	 * </p>
	 */
	public void setPointDateFormatter(Object pointDateFormatter) {
		this.pointDateFormatter = pointDateFormatter;
	}

	/**
	 * @see #setPointDescriptionFormatter(Object)
	 */
	public Object getPointDescriptionFormatter() {
		if (pointDescriptionFormatter == null) {
			pointDescriptionFormatter = new Object();
		}
		return pointDescriptionFormatter;
	}

	/**
	 * <p>
	 * Formatter function to use instead of the default for point descriptions.
	 * Receives one argument, <code>point</code>, referring to the point to
	 * describe. Should return a String with the description of the point for a
	 * screen reader user.
	 * </p>
	 */
	public void setPointDescriptionFormatter(Object pointDescriptionFormatter) {
		this.pointDescriptionFormatter = pointDescriptionFormatter;
	}

	/**
	 * @see #setScreenReaderSectionFormatter(Object)
	 */
	public Object getScreenReaderSectionFormatter() {
		if (screenReaderSectionFormatter == null) {
			screenReaderSectionFormatter = new Object();
		}
		return screenReaderSectionFormatter;
	}

	/**
	 * <p>
	 * A formatter function to create the HTML contents of the hidden screen
	 * reader information region. Receives one argument, <code>chart</code>,
	 * referring to the chart object. Should return a String with the HTML
	 * content of the region.
	 * </p>
	 * <p>
	 * The link to view the chart as a data table will be added automatically
	 * after the custom HTML content.
	 * </p>
	 * <p>
	 * Defaults to: undefined
	 */
	public void setScreenReaderSectionFormatter(
			Object screenReaderSectionFormatter) {
		this.screenReaderSectionFormatter = screenReaderSectionFormatter;
	}

	/**
	 * @see #setSeriesDescriptionFormatter(Object)
	 */
	public Object getSeriesDescriptionFormatter() {
		if (seriesDescriptionFormatter == null) {
			seriesDescriptionFormatter = new Object();
		}
		return seriesDescriptionFormatter;
	}

	/**
	 * <p>
	 * Formatter function to use instead of the default for series descriptions.
	 * Receives one argument, <code>series</code>, referring to the series to
	 * describe. Should return a String with the description of the series for a
	 * screen reader user.
	 * </p>
	 */
	public void setSeriesDescriptionFormatter(Object seriesDescriptionFormatter) {
		this.seriesDescriptionFormatter = seriesDescriptionFormatter;
	}
}