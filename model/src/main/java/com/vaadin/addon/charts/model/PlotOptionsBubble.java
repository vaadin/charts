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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import com.vaadin.addon.charts.util.Util;
import com.vaadin.addon.charts.util.SizeWithUnit;
/**
 * A bubble series is a three dimensional series type where each point renders
 * an X, Y and Z value. Each points is drawn as a bubble where the position
 * along the X and Y axes mark the X and Y values, and the size of the bubble
 * relates to the Z value.
 */
public class PlotOptionsBubble extends AbstractPlotOptions {

	private static final long serialVersionUID = 1L;
	private Boolean allowPointSelect;
	private Boolean animation;
	private Color color;
	private Number cropThreshold;
	private Cursor cursor;
	private DashStyle dashStyle;
	private DataLabels dataLabels;
	private Boolean displayNegative;
	private Boolean enableMouseTracking;
	private Boolean getExtremesFromAll;
	private ArrayList<String> keys;
	private Number lineWidth;
	private String linkedTo;
	private Marker marker;
	private Color negativeColor;
	private Number pointInterval;
	private IntervalUnit pointIntervalUnit;
	private Number pointStart;
	private Boolean selected;
	private Boolean shadow;
	private Boolean showCheckbox;
	private Boolean showInLegend;
	private String sizeBy;
	private Boolean sizeByAbsoluteValue;
	private Boolean softThreshold;
	private States states;
	private Boolean stickyTracking;
	private Number threshold;
	private SeriesTooltip tooltip;
	private Boolean visible;
	private Number zMax;
	private Number zMin;
	private Number zThreshold;
	private String zoneAxis;
	private ArrayList<Zones> zones;
	private String minSize;
	private String maxSize;

	public PlotOptionsBubble() {
	}

	@Override
	public ChartType getChartType() {
		return ChartType.BUBBLE;
	}

	/**
	 * @see #setAllowPointSelect(Boolean)
	 */
	public Boolean getAllowPointSelect() {
		return allowPointSelect;
	}

	/**
	 * Allow this series' points to be selected by clicking on the markers, bars
	 * or pie slices.
	 * <p>
	 * Defaults to: false
	 */
	public void setAllowPointSelect(Boolean allowPointSelect) {
		this.allowPointSelect = allowPointSelect;
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
	 * @see #setColor(Color)
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * The main color or the series. In line type series it applies to the line
	 * and the point markers unless otherwise specified. In bar type series it
	 * applies to the bars unless a color is specified per point. The default
	 * value is pulled from the <code>options.colors</code> array.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @see #setCropThreshold(Number)
	 */
	public Number getCropThreshold() {
		return cropThreshold;
	}

	/**
	 * When the series contains less points than the crop threshold, all points
	 * are drawn, event if the points fall outside the visible plot area at the
	 * current zoom. The advantage of drawing all points (including markers and
	 * columns), is that animation is performed on updates. On the other hand,
	 * when the series contains more points than the crop threshold, the series
	 * data is cropped to only contain points that fall within the plot area.
	 * The advantage of cropping away invisible points is to increase
	 * performance on large series. .
	 * <p>
	 * Defaults to: 300
	 */
	public void setCropThreshold(Number cropThreshold) {
		this.cropThreshold = cropThreshold;
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
	 * @see #setDashStyle(DashStyle)
	 */
	public DashStyle getDashStyle() {
		return dashStyle;
	}

	/**
	 * A name for the dash style to use for the graph. Applies only to series
	 * type having a graph, like <code>line</code>, <code>spline</code>,
	 * <code>area</code> and <code>scatter</code> in case it has a
	 * <code>lineWidth</code>. The value for the <code>dashStyle</code> include:
	 * <ul>
	 * <li>Solid</li>
	 * <li>ShortDash</li>
	 * <li>ShortDot</li>
	 * <li>ShortDashDot</li>
	 * <li>ShortDashDotDot</li>
	 * <li>Dot</li>
	 * <li>Dash</li>
	 * <li>LongDash</li>
	 * <li>DashDot</li>
	 * <li>LongDashDot</li>
	 * <li>LongDashDotDot</li>
	 * </ul>
	 * <p>
	 * Defaults to: Solid
	 */
	public void setDashStyle(DashStyle dashStyle) {
		this.dashStyle = dashStyle;
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
	 * 
	 */
	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}

	/**
	 * @see #setDisplayNegative(Boolean)
	 */
	public Boolean getDisplayNegative() {
		return displayNegative;
	}

	/**
	 * Whether to display negative sized bubbles. The threshold is given by the
	 * <a href="#plotOptions.bubble.zThreshold">zThreshold</a> option, and
	 * negative bubbles can be visualized by setting <a
	 * href="#plotOptions.bubble.negativeColor">negativeColor</a>.
	 * <p>
	 * Defaults to: true
	 */
	public void setDisplayNegative(Boolean displayNegative) {
		this.displayNegative = displayNegative;
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
	 * @see #setLineWidth(Number)
	 */
	public Number getLineWidth() {
		return lineWidth;
	}

	/**
	 * The width of the line connecting the data points.
	 * <p>
	 * Defaults to: 0
	 */
	public void setLineWidth(Number lineWidth) {
		this.lineWidth = lineWidth;
	}

	/**
	 * @see #setLinkedTo(String)
	 */
	public String getLinkedTo() {
		return linkedTo;
	}

	/**
	 * The <a href="#series.id">id</a> of another series to link to.
	 * Additionally, the value can be ":previous" to link to the previous
	 * series. When two series are linked, only the first one appears in the
	 * legend. Toggling the visibility of this also toggles the linked series.
	 * <p>
	 * Defaults to:
	 */
	public void setLinkedTo(String linkedTo) {
		this.linkedTo = linkedTo;
	}

	/**
	 * @see #setMarker(Marker)
	 */
	public Marker getMarker() {
		if (marker == null) {
			marker = new Marker();
		}
		return marker;
	}

	/**
	 * 
	 */
	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	/**
	 * @see #setNegativeColor(Color)
	 */
	public Color getNegativeColor() {
		return negativeColor;
	}

	/**
	 * When a point's Z value is below the <a
	 * href="#plotOptions.bubble.zThreshold">zThreshold</a> setting, this color
	 * is used.
	 * <p>
	 * Defaults to: null
	 */
	public void setNegativeColor(Color negativeColor) {
		this.negativeColor = negativeColor;
	}

	/**
	 * @see #setPointInterval(Number)
	 */
	public Number getPointInterval() {
		return pointInterval;
	}

	/**
	 * <p>
	 * If no x values are given for the points in a series, pointInterval
	 * defines the interval of the x values. For example, if a series contains
	 * one value every decade starting from year 0, set pointInterval to 10.
	 * </p>
	 * <p>
	 * Since Highcharts 4.1, it can be combined with
	 * <code>pointIntervalUnit</code> to draw irregular intervals.
	 * </p>
	 * <p>
	 * Defaults to: 1
	 */
	public void setPointInterval(Number pointInterval) {
		this.pointInterval = pointInterval;
	}

	/**
	 * @see #setPointIntervalUnit(IntervalUnit)
	 */
	public IntervalUnit getPointIntervalUnit() {
		return pointIntervalUnit;
	}

	/**
	 * On datetime series, this allows for setting the <a
	 * href="plotOptions.series.pointInterval">pointInterval</a> to the two
	 * irregular time units, <code>month</code> and <code>year</code>. Combine
	 * it with <code>pointInterval</code> to draw quarters, 6 months, 10 years
	 * etc.
	 */
	public void setPointIntervalUnit(IntervalUnit pointIntervalUnit) {
		this.pointIntervalUnit = pointIntervalUnit;
	}

	/**
	 * @see #setPointStart(Number)
	 */
	public Number getPointStart() {
		return pointStart;
	}

	/**
	 * If no x values are given for the points in a series, pointStart defines
	 * on what value to start. For example, if a series contains one yearly
	 * value starting from 1945, set pointStart to 1945.
	 * <p>
	 * Defaults to: 0
	 */
	public void setPointStart(Number pointStart) {
		this.pointStart = pointStart;
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
	 * @see #setShadow(Boolean)
	 */
	public Boolean getShadow() {
		return shadow;
	}

	/**
	 * Whether to apply a drop shadow to the graph line. Since 2.3 the shadow
	 * can be an object configuration containing <code>color</code>,
	 * <code>offsetX</code>, <code>offsetY</code>, <code>opacity</code> and
	 * <code>width</code>.
	 * <p>
	 * Defaults to: false
	 */
	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
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
	 * The default value is <code>true</code> for standalone series,
	 * <code>false</code> for linked series.
	 * <p>
	 * Defaults to: true
	 */
	public void setShowInLegend(Boolean showInLegend) {
		this.showInLegend = showInLegend;
	}

	/**
	 * @see #setSizeBy(String)
	 */
	public String getSizeBy() {
		return sizeBy;
	}

	/**
	 * Whether the bubble's value should be represented by the area or the width
	 * of the bubble. The default, <code>area</code>, corresponds best to the
	 * human perception of the size of each bubble.
	 * <p>
	 * Defaults to: area
	 */
	public void setSizeBy(String sizeBy) {
		this.sizeBy = sizeBy;
	}

	/**
	 * @see #setSizeByAbsoluteValue(Boolean)
	 */
	public Boolean getSizeByAbsoluteValue() {
		return sizeByAbsoluteValue;
	}

	/**
	 * When this is true, the absolute value of z determines the size of the
	 * bubble. This means that with the default <code>zThreshold</code> of 0, a
	 * bubble of value -1 will have the same size as a bubble of value 1, while
	 * a bubble of value 0 will have a smaller size according to
	 * <code>minSize</code>.
	 * <p>
	 * Defaults to: false
	 */
	public void setSizeByAbsoluteValue(Boolean sizeByAbsoluteValue) {
		this.sizeByAbsoluteValue = sizeByAbsoluteValue;
	}

	/**
	 * @see #setSoftThreshold(Boolean)
	 */
	public Boolean getSoftThreshold() {
		return softThreshold;
	}

	/**
	 * <p>
	 * When this is true, the series will not cause the Y axis to cross the zero
	 * plane (or <a href="#plotOptions.series.threshold">threshold</a> option)
	 * unless the data actually crosses the plane.
	 * </p>
	 * 
	 * <p>
	 * For example, if <code>softThreshold</code> is <code>false</code>, a
	 * series of 0, 1, 2, 3 will make the Y axis show negative values according
	 * to the <code>minPadding</code> option. If <code>softThreshold</code> is
	 * <code>true</code>, the Y axis starts at 0.
	 * </p>
	 * <p>
	 * Defaults to: false
	 */
	public void setSoftThreshold(Boolean softThreshold) {
		this.softThreshold = softThreshold;
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
	 * <p>
	 * Defaults to: false
	 */
	public void setStickyTracking(Boolean stickyTracking) {
		this.stickyTracking = stickyTracking;
	}

	/**
	 * @see #setThreshold(Number)
	 */
	public Number getThreshold() {
		return threshold;
	}

	/**
	 * The threshold, also called zero level or base level. For line type series
	 * this is only used in conjunction with <a
	 * href="#plotOptions.series.negativeColor">negativeColor</a>.
	 * <p>
	 * Defaults to: 0
	 */
	public void setThreshold(Number threshold) {
		this.threshold = threshold;
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
	 * @see #setZMax(Number)
	 */
	public Number getZMax() {
		return zMax;
	}

	/**
	 * The minimum for the Z value range. Defaults to the highest Z value in the
	 * data.
	 * <p>
	 * Defaults to: null
	 */
	public void setZMax(Number zMax) {
		this.zMax = zMax;
	}

	/**
	 * @see #setZMin(Number)
	 */
	public Number getZMin() {
		return zMin;
	}

	/**
	 * The minimum for the Z value range. Defaults to the lowest Z value in the
	 * data.
	 * <p>
	 * Defaults to: null
	 */
	public void setZMin(Number zMin) {
		this.zMin = zMin;
	}

	/**
	 * @see #setZThreshold(Number)
	 */
	public Number getZThreshold() {
		return zThreshold;
	}

	/**
	 * When <a href="#plotOptions.bubble.displayNegative">displayNegative</a> is
	 * <code>false</code>, bubbles with lower Z values are skipped. When
	 * <code>displayNegative</code> is <code>true</code> and a <a
	 * href="#plotOptions.bubble.negativeColor">negativeColor</a> is given,
	 * points with lower Z is colored.
	 * <p>
	 * Defaults to: 0
	 */
	public void setZThreshold(Number zThreshold) {
		this.zThreshold = zThreshold;
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

	public void setPointStart(Date date) {
		this.pointStart = Util.toHighchartsTS(date);
	}

	public float getMinSize() {
		String tmp = minSize;
		if (minSize == null) {
			return -1.0f;
		}
		if (this.minSize.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getMinSizeUnit() {
		if (this.minSize == null) {
			return Unit.PIXELS;
		}
		if (this.minSize.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setMinSize(String minSize) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(minSize);
		if (tmp != null) {
			setMinSize(tmp.getSize(), tmp.getUnit());
		} else {
			setMinSize(-1, Unit.PIXELS);
		}
	}

	public void setMinSize(float minSize, Unit unit) {
		String value = Float.toString(minSize);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (minSize == -1) {
			value = null;
		}
		this.minSize = value;
	}

	public float getMaxSize() {
		String tmp = maxSize;
		if (maxSize == null) {
			return -1.0f;
		}
		if (this.maxSize.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getMaxSizeUnit() {
		if (this.maxSize == null) {
			return Unit.PIXELS;
		}
		if (this.maxSize.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setMaxSize(String maxSize) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(maxSize);
		if (tmp != null) {
			setMaxSize(tmp.getSize(), tmp.getUnit());
		} else {
			setMaxSize(-1, Unit.PIXELS);
		}
	}

	public void setMaxSize(float maxSize, Unit unit) {
		String value = Float.toString(maxSize);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (maxSize == -1) {
			value = null;
		}
		this.maxSize = value;
	}
}