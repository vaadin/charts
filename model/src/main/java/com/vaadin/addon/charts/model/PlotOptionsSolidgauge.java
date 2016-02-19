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

import java.util.ArrayList;
import java.util.Arrays;
/**
 * A gauge showing values using a filled arc with colors indicating the value.
 * The solid gauge plots values against the <code>yAxis</code>, which is
 * extended with some color options, <a href="#yAxis.minColor">minColor</a>, <a
 * href="#yAxis.maxColor">maxColor</a> and <a href="#yAxis.stops">stops</a>, to
 * control the color of the gauge itself.
 */
public class PlotOptionsSolidgauge extends AbstractPlotOptions {

	private Boolean animation;
	private Cursor cursor;
	private DataLabels dataLabels;
	private Boolean enableMouseTracking;
	private Boolean getExtremesFromAll;
	private ArrayList<String> keys;
	private String linecap;
	private Number overshoot;
	private Boolean selected;
	private Boolean showCheckbox;
	private Boolean showInLegend;
	private States states;
	private Boolean stickyTracking;
	private SeriesTooltip tooltip;
	private Boolean visible;
	private Boolean wrap;
	private String zoneAxis;
	private ArrayList<Zones> zones;

	public PlotOptionsSolidgauge() {
	}

	@Override
	public ChartType getChartType() {
		return ChartType.SOLIDGAUGE;
	}

	/**
	 * @see #setAnimation(Boolean)
	 */
	public Boolean getAnimation() {
		return animation;
	}

	/**
	 * <p>
	 * Enable or disable the initial animation when a series is displayed. The
	 * animation can also be set as a configuration object. Please note that
	 * this option only applies to the initial animation of the series itself.
	 * For other animations, see <a href="#chart.animation">chart.animation</a>
	 * and the animation parameter under the API methods. The following
	 * properties are supported:
	 * </p>
	 * <dl>
	 * <dt>duration</dt>
	 * <dd>The duration of the animation in milliseconds.</dd>
	 * <dt>easing</dt>
	 * <dd>A string reference to an easing function set on the <code>Math</code>
	 * object. See <a href=
	 * "http://jsfiddle.net/gh/get/jquery/1.7.2/highcharts/highcharts/tree/master/samples/highcharts/plotoptions/series-animation-easing/"
	 * >the easing demo</a>.</dd>
	 * </dl>
	 * <p>
	 * Due to poor performance, animation is disabled in old IE browsers for
	 * column charts and polar charts.
	 * </p>
	 * <p>
	 * Defaults to: true
	 */
	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}

	/**
	 * @see #setCursor(Cursor)
	 */
	public Cursor getCursor() {
		return cursor;
	}

	/**
	 * You can set the cursor to "pointer" if you have click events attached to
	 * the series, to signal to the user that the points and lines can be
	 * clicked.
	 */
	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}

	/**
	 * @see #setDataLabels(DataLabels)
	 */
	public DataLabels getDataLabels() {
		if (dataLabels == null) {
			dataLabels = new DataLabels();
		}
		return dataLabels;
	}

	/**
	 * Data labels for the gauge. For gauges, the data labels are enabled by
	 * default and shown in a bordered box below the point.
	 */
	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}

	/**
	 * @see #setEnableMouseTracking(Boolean)
	 */
	public Boolean getEnableMouseTracking() {
		return enableMouseTracking;
	}

	/**
	 * Enable or disable the mouse tracking for a specific series. This includes
	 * point tooltips and click events on graphs and points. For large datasets
	 * it improves performance.
	 * <p>
	 * Defaults to: true
	 */
	public void setEnableMouseTracking(Boolean enableMouseTracking) {
		this.enableMouseTracking = enableMouseTracking;
	}

	/**
	 * @see #setGetExtremesFromAll(Boolean)
	 */
	public Boolean getGetExtremesFromAll() {
		return getExtremesFromAll;
	}

	/**
	 * Whether to use the Y extremes of the total chart width or only the zoomed
	 * area when zooming in on parts of the X axis. By default, the Y axis
	 * adjusts to the min and max of the visible data. Cartesian series only.
	 * <p>
	 * Defaults to: false
	 */
	public void setGetExtremesFromAll(Boolean getExtremesFromAll) {
		this.getExtremesFromAll = getExtremesFromAll;
	}

	public String[] getKeys() {
		String[] arr = new String[keys.size()];
		keys.toArray(arr);
		return arr;
	}

	public void setKeys(String... keys) {
		this.keys = new ArrayList<String>(Arrays.asList(keys));
	}

	public void addKey(String key) {
		if (this.keys == null) {
			this.keys = new ArrayList<String>();
		}
		this.keys.add(key);
	}

	public void removeKey(String key) {
		this.keys.remove(key);
	}

	/**
	 * @see #setLinecap(String)
	 */
	public String getLinecap() {
		return linecap;
	}

	/**
	 * Whether the strokes of the solid gauge should be <code>round</code> or
	 * <code>square</code>.
	 * <p>
	 * Defaults to: square
	 */
	public void setLinecap(String linecap) {
		this.linecap = linecap;
	}

	/**
	 * @see #setOvershoot(Number)
	 */
	public Number getOvershoot() {
		return overshoot;
	}

	/**
	 * Allow the dial to overshoot the end of the perimeter axis by this many
	 * degrees. Say if the gauge axis goes from 0 to 60, a value of 100, or
	 * 1000, will show 5 degrees beyond the end of the axis.
	 * <p>
	 * Defaults to: 0
	 */
	public void setOvershoot(Number overshoot) {
		this.overshoot = overshoot;
	}

	/**
	 * @see #setSelected(Boolean)
	 */
	public Boolean getSelected() {
		return selected;
	}

	/**
	 * Whether to select the series initially. If <code>showCheckbox</code> is
	 * true, the checkbox next to the series name will be checked for a selected
	 * series.
	 * <p>
	 * Defaults to: false
	 */
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	/**
	 * @see #setShowCheckbox(Boolean)
	 */
	public Boolean getShowCheckbox() {
		return showCheckbox;
	}

	/**
	 * If true, a checkbox is displayed next to the legend item to allow
	 * selecting the series. The state of the checkbox is determined by the
	 * <code>selected</code> option.
	 * <p>
	 * Defaults to: false
	 */
	public void setShowCheckbox(Boolean showCheckbox) {
		this.showCheckbox = showCheckbox;
	}

	/**
	 * @see #setShowInLegend(Boolean)
	 */
	public Boolean getShowInLegend() {
		return showInLegend;
	}

	/**
	 * Whether to display this particular series or series type in the legend.
	 * Defaults to false for gauge series.
	 */
	public void setShowInLegend(Boolean showInLegend) {
		this.showInLegend = showInLegend;
	}

	/**
	 * @see #setStates(States)
	 */
	public States getStates() {
		if (states == null) {
			states = new States();
		}
		return states;
	}

	/**
	 * A wrapper object for all the series options in specific states.
	 */
	public void setStates(States states) {
		this.states = states;
	}

	/**
	 * @see #setStickyTracking(Boolean)
	 */
	public Boolean getStickyTracking() {
		return stickyTracking;
	}

	/**
	 * Sticky tracking of mouse events. When true, the <code>mouseOut</code>
	 * event on a series isn't triggered until the mouse moves over another
	 * series, or out of the plot area. When false, the <code>mouseOut</code>
	 * event on a series is triggered when the mouse leaves the area around the
	 * series' graph or markers. This also implies the tooltip. When
	 * <code>stickyTracking</code> is false and <code>tooltip.shared</code> is
	 * false, the tooltip will be hidden when moving the mouse between series.
	 * Defaults to true for line and area type series, but to false for columns,
	 * pies etc.
	 * <p>
	 * Defaults to: true
	 */
	public void setStickyTracking(Boolean stickyTracking) {
		this.stickyTracking = stickyTracking;
	}

	/**
	 * @see #setTooltip(SeriesTooltip)
	 */
	public SeriesTooltip getTooltip() {
		if (tooltip == null) {
			tooltip = new SeriesTooltip();
		}
		return tooltip;
	}

	/**
	 * A configuration object for the tooltip rendering of each single series.
	 * Properties are inherited from <a href="#tooltip">tooltip</a>, but only
	 * the following properties can be defined on a series level.
	 */
	public void setTooltip(SeriesTooltip tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * @see #setVisible(Boolean)
	 */
	public Boolean getVisible() {
		return visible;
	}

	/**
	 * Set the initial visibility of the series.
	 * <p>
	 * Defaults to: true
	 */
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	/**
	 * @see #setWrap(Boolean)
	 */
	public Boolean getWrap() {
		return wrap;
	}

	/**
	 * When this option is <code>true</code>, the dial will wrap around the
	 * axes. For instance, in a full-range gauge going from 0 to 360, a value of
	 * 400 will point to 40. When <code>wrap</code> is <code>false</code>, the
	 * dial stops at 360.
	 * <p>
	 * Defaults to: true
	 */
	public void setWrap(Boolean wrap) {
		this.wrap = wrap;
	}

	/**
	 * @see #setZoneAxis(String)
	 */
	public String getZoneAxis() {
		return zoneAxis;
	}

	/**
	 * Defines the Axis on which the zones are applied.
	 * <p>
	 * Defaults to: y
	 */
	public void setZoneAxis(String zoneAxis) {
		this.zoneAxis = zoneAxis;
	}

	public Zones[] getZones() {
		Zones[] arr = new Zones[zones.size()];
		zones.toArray(arr);
		return arr;
	}

	public void setZones(Zones... zones) {
		this.zones = new ArrayList<Zones>(Arrays.asList(zones));
	}

	public void addZone(Zones zone) {
		if (this.zones == null) {
			this.zones = new ArrayList<Zones>();
		}
		this.zones.add(zone);
	}

	public void removeZone(Zones zone) {
		this.zones.remove(zone);
	}
}