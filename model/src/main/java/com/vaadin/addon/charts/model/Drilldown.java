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
 * <p>
 * Options for drill down, the concept of inspecting increasingly high
 * resolution data through clicking on chart items like columns or pie slices.
 * </p>
 * 
 * <p>
 * The drilldown feature requires the <code>drilldown.js</code> file to be
 * loaded, found in the <code>modules</code> directory of the download package,
 * or online at <a
 * href="http://code.highcharts.com/modules/drilldown.js">code.highcharts
 * .com/modules/drilldown.js</a>.
 * </p>
 */
public class Drilldown extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Style activeAxisLabelStyle;
	private Style activeDataLabelStyle;
	private Boolean allowPointDrilldown;
	private Object animation;
	private DrillUpButton drillUpButton;
	private PlotOptionsSeries series;

	public Drilldown() {
	}

	/**
	 * @see #setActiveAxisLabelStyle(Style)
	 */
	public Style getActiveAxisLabelStyle() {
		return activeAxisLabelStyle;
	}

	/**
	 * Additional styles to apply to the X axis label for a point that has
	 * drilldown data. By default it is underlined and blue to invite to
	 * interaction. Defaults to:
	 * 
	 * <pre>
	 * activeAxisLabelStyle: {
	 * 		cursor: 'pointer',
	 * 		color: '#0d233a',
	 * 		fontWeight: 'bold',
	 * 		textDecoration: 'underline'			
	 * 	}
	 * </pre>
	 */
	public void setActiveAxisLabelStyle(Style activeAxisLabelStyle) {
		this.activeAxisLabelStyle = activeAxisLabelStyle;
	}

	/**
	 * @see #setActiveDataLabelStyle(Style)
	 */
	public Style getActiveDataLabelStyle() {
		return activeDataLabelStyle;
	}

	/**
	 * Additional styles to apply to the data label of a point that has
	 * drilldown data. By default it is underlined and blue to invite to
	 * interaction. Defaults to:
	 * 
	 * <pre>
	 * activeAxisLabelStyle: {
	 * 		cursor: 'pointer',
	 * 		color: '#0d233a',
	 * 		fontWeight: 'bold',
	 * 		textDecoration: 'underline'			
	 * 	}
	 * </pre>
	 */
	public void setActiveDataLabelStyle(Style activeDataLabelStyle) {
		this.activeDataLabelStyle = activeDataLabelStyle;
	}

	/**
	 * @see #setAllowPointDrilldown(Boolean)
	 */
	public Boolean getAllowPointDrilldown() {
		return allowPointDrilldown;
	}

	/**
	 * When this option is false, clicking a single point will drill down all
	 * points in the same category, equivalent to clicking the X axis label.
	 * <p>
	 * Defaults to: true
	 */
	public void setAllowPointDrilldown(Boolean allowPointDrilldown) {
		this.allowPointDrilldown = allowPointDrilldown;
	}

	/**
	 * @see #setAnimation(Object)
	 */
	public Object getAnimation() {
		return animation;
	}

	/**
	 * <p>
	 * Set the animation for all drilldown animations. Animation of a drilldown
	 * occurs when drilling between a column point and a column series, or a pie
	 * slice and a full pie series. Drilldown can still be used between series
	 * and points of different types, but animation will not occur.
	 * </p>
	 * 
	 * <p>
	 * The animation can either be set as a boolean or a configuration object.
	 * If <code>true</code>, it will use the 'swing' jQuery easing and a
	 * duration of 500 ms. If used as a configuration object, the following
	 * properties are supported:
	 * </p>
	 * <dl>
	 * <dt>duration</dt>
	 * <dd>The duration of the animation in milliseconds.</dd>
	 * 
	 * <dt>easing</dt>
	 * <dd>A string reference to an easing function set on the <code>Math</code>
	 * object. See <a href=
	 * "http://jsfiddle.net/gh/get/jquery/1.7.2/highcharts/highcharts/tree/master/samples/highcharts/plotoptions/series-animation-easing/"
	 * >the easing demo</a>.</dd>
	 * </dl>
	 */
	public void setAnimation(Object animation) {
		this.animation = animation;
	}

	/**
	 * @see #setDrillUpButton(DrillUpButton)
	 */
	public DrillUpButton getDrillUpButton() {
		return drillUpButton;
	}

	/**
	 * Options for the drill up button that appears when drilling down on a
	 * series. The text for the button is defined in <a
	 * href="#lang.drillUpText">lang.drillUpText</a>.
	 */
	public void setDrillUpButton(DrillUpButton drillUpButton) {
		this.drillUpButton = drillUpButton;
	}

	/**
	 * @see #setSeries(PlotOptionsSeries)
	 */
	public PlotOptionsSeries getSeries() {
		return series;
	}

	/**
	 * An array of series configurations for the drill down. Each series
	 * configuration uses the same syntax as the <a href="#series">series</a>
	 * option set. These drilldown series are hidden by default. The drilldown
	 * series is linked to the parent series' point by its <code>id</code>.
	 */
	public void setSeries(PlotOptionsSeries series) {
		this.series = series;
	}
}