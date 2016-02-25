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
/**
 * Options for the waterfall series type.
 */
public class PlotOptionsWaterfall extends AbstractPlotOptions {

	private Boolean allowPointSelect;
	private Boolean animation;
	private Color borderColor;
	private Number borderRadius;
	private Number borderWidth;
	private Color color;
	private Boolean colorByPoint;
	private ArrayList<Color> colors;
	private Cursor cursor;
	private DashStyle dashStyle;
	private DataLabels dataLabels;
	private Number depth;
	private Color edgeColor;
	private Number edgeWidth;
	private Boolean enableMouseTracking;
	private Boolean getExtremesFromAll;
	private Number groupPadding;
	private Number groupZPadding;
	private Boolean grouping;
	private ArrayList<String> keys;
	private Color lineColor;
	private String linkedTo;
	private Number maxPointWidth;
	private Number minPointLength;
	private Number pointInterval;
	private IntervalUnit pointIntervalUnit;
	private Number pointPadding;
	private PointPlacement pointPlacement;
	private Number pointRange;
	private Number pointStart;
	private Number pointWidth;
	private Boolean selected;
	private Boolean shadow;
	private Boolean showCheckbox;
	private Boolean showInLegend;
	private Boolean softThreshold;
	private States states;
	private Boolean stickyTracking;
	private Number threshold;
	private SeriesTooltip tooltip;
	private Color upColor;
	private Boolean visible;
	private String zoneAxis;
	private ArrayList<Zones> zones;

	public PlotOptionsWaterfall() {
	}

	@Override
	public ChartType getChartType() {
		return ChartType.WATERFALL;
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
	 * @see #setBorderColor(Color)
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * The color of the border of each waterfall column.
	 * <p>
	 * Defaults to: #333333
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * @see #setBorderRadius(Number)
	 */
	public Number getBorderRadius() {
		return borderRadius;
	}

	/**
	 * The corner radius of the border surrounding each column or bar.
	 * <p>
	 * Defaults to: 0
	 */
	public void setBorderRadius(Number borderRadius) {
		this.borderRadius = borderRadius;
	}

	/**
	 * @see #setBorderWidth(Number)
	 */
	public Number getBorderWidth() {
		return borderWidth;
	}

	/**
	 * The width of the border surrounding each column or bar.
	 * <p>
	 * Defaults to: 1
	 */
	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
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
	 * @see #setColorByPoint(Boolean)
	 */
	public Boolean getColorByPoint() {
		return colorByPoint;
	}

	/**
	 * When using automatic point colors pulled from the
	 * <code>options.colors</code> collection, this option determines whether
	 * the chart should receive one color per series or one color per point.
	 * <p>
	 * Defaults to: false
	 */
	public void setColorByPoint(Boolean colorByPoint) {
		this.colorByPoint = colorByPoint;
	}

	public Color[] getColors() {
		Color[] arr = new Color[colors.size()];
		colors.toArray(arr);
		return arr;
	}

	public void setColors(Color... colors) {
		this.colors = new ArrayList<Color>(Arrays.asList(colors));
	}

	public void addColor(Color color) {
		if (this.colors == null) {
			this.colors = new ArrayList<Color>();
		}
		this.colors.add(color);
	}

	public void removeColor(Color color) {
		this.colors.remove(color);
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
	 * A name for the dash style to use for the line connecting the columns of
	 * the waterfall series. Possible values:
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
	 * .
	 * <p>
	 * Defaults to: Dot
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
	 * @see #setDepth(Number)
	 */
	public Number getDepth() {
		return depth;
	}

	/**
	 * Depth of the columns in a 3D column chart. Requires
	 * <code>highcharts-3d.js</code>.
	 * <p>
	 * Defaults to: 25
	 */
	public void setDepth(Number depth) {
		this.depth = depth;
	}

	/**
	 * @see #setEdgeColor(Color)
	 */
	public Color getEdgeColor() {
		return edgeColor;
	}

	/**
	 * 3D columns only. The color of the edges. Similar to
	 * <code>borderColor</code>, except it defaults to the same color as the
	 * column.
	 * <p>
	 * Defaults to:
	 */
	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	/**
	 * @see #setEdgeWidth(Number)
	 */
	public Number getEdgeWidth() {
		return edgeWidth;
	}

	/**
	 * 3D columns only. The width of the colored edges.
	 * <p>
	 * Defaults to: 1
	 */
	public void setEdgeWidth(Number edgeWidth) {
		this.edgeWidth = edgeWidth;
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

	/**
	 * @see #setGroupPadding(Number)
	 */
	public Number getGroupPadding() {
		return groupPadding;
	}

	/**
	 * Padding between each value groups, in x axis units.
	 * <p>
	 * Defaults to: 0.2
	 */
	public void setGroupPadding(Number groupPadding) {
		this.groupPadding = groupPadding;
	}

	/**
	 * @see #setGroupZPadding(Number)
	 */
	public Number getGroupZPadding() {
		return groupZPadding;
	}

	/**
	 * The spacing between columns on the Z Axis in a 3D chart. Requires
	 * <code>highcharts-3d.js</code>.
	 * <p>
	 * Defaults to: 1
	 */
	public void setGroupZPadding(Number groupZPadding) {
		this.groupZPadding = groupZPadding;
	}

	/**
	 * @see #setGrouping(Boolean)
	 */
	public Boolean getGrouping() {
		return grouping;
	}

	/**
	 * Whether to group non-stacked columns or to let them render independent of
	 * each other. Non-grouped columns will be laid out individually and overlap
	 * each other.
	 * <p>
	 * Defaults to: true
	 */
	public void setGrouping(Boolean grouping) {
		this.grouping = grouping;
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
	 * @see #setLineColor(Color)
	 */
	public Color getLineColor() {
		return lineColor;
	}

	/**
	 * The color of the line that connects columns in a waterfall series.
	 * <p>
	 * Defaults to: #333333
	 */
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
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
	 * @see #setMaxPointWidth(Number)
	 */
	public Number getMaxPointWidth() {
		return maxPointWidth;
	}

	/**
	 * The maximum allowed pixel width for a column, translated to the height of
	 * a bar in a bar chart. This prevents the columns from becoming too wide
	 * when there is a small number of points in the chart.
	 * <p>
	 * Defaults to: null
	 */
	public void setMaxPointWidth(Number maxPointWidth) {
		this.maxPointWidth = maxPointWidth;
	}

	/**
	 * @see #setMinPointLength(Number)
	 */
	public Number getMinPointLength() {
		return minPointLength;
	}

	/**
	 * The minimal height for a column or width for a bar. By default, 0 values
	 * are not shown. To visualize a 0 (or close to zero) point, set the minimal
	 * point length to a pixel value like 3. In stacked column charts,
	 * minPointLength might not be respected for tightly packed values.
	 * <p>
	 * Defaults to: 0
	 */
	public void setMinPointLength(Number minPointLength) {
		this.minPointLength = minPointLength;
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
	 * @see #setPointPadding(Number)
	 */
	public Number getPointPadding() {
		return pointPadding;
	}

	/**
	 * Padding between each column or bar, in x axis units.
	 * <p>
	 * Defaults to: 0.1
	 */
	public void setPointPadding(Number pointPadding) {
		this.pointPadding = pointPadding;
	}

	/**
	 * @see #setPointPlacement(PointPlacement)
	 */
	public PointPlacement getPointPlacement() {
		return pointPlacement;
	}

	/**
	 * <p>
	 * Possible values: <code>null</code>, <code>"on"</code>,
	 * <code>"between"</code>.
	 * </p>
	 * <p>
	 * In a column chart, when pointPlacement is <code>"on"</code>, the point
	 * will not create any padding of the X axis. In a polar column chart this
	 * means that the first column points directly north. If the pointPlacement
	 * is <code>"between"</code>, the columns will be laid out between ticks.
	 * This is useful for example for visualising an amount between two points
	 * in time or in a certain sector of a polar chart.
	 * </p>
	 * <p>
	 * Since Highcharts 3.0.2, the point placement can also be numeric, where 0
	 * is on the axis value, -0.5 is between this value and the previous, and
	 * 0.5 is between this value and the next. Unlike the textual options,
	 * numeric point placement options won't affect axis padding.
	 * </p>
	 * <p>
	 * Note that pointPlacement needs a <a
	 * href="#plotOptions.series.pointRange">pointRange</a> to work. For column
	 * series this is computed, but for line-type series it needs to be set.
	 * </p>
	 * <p>
	 * Defaults to <code>null</code> in cartesian charts, <code>"between"</code>
	 * in polar charts.
	 */
	public void setPointPlacement(PointPlacement pointPlacement) {
		this.pointPlacement = pointPlacement;
	}

	/**
	 * @see #setPointRange(Number)
	 */
	public Number getPointRange() {
		return pointRange;
	}

	/**
	 * The X axis range that each point is valid for. This determines the width
	 * of the column. On a categorized axis, the range will be 1 by default (one
	 * category unit). On linear and datetime axes, the range will be computed
	 * as the distance between the two closest data points.
	 */
	public void setPointRange(Number pointRange) {
		this.pointRange = pointRange;
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
	 * @see #setPointWidth(Number)
	 */
	public Number getPointWidth() {
		return pointWidth;
	}

	/**
	 * A pixel value specifying a fixed width for each column or bar. When
	 * <code>null</code>, the width is calculated from the
	 * <code>pointPadding</code> and <code>groupPadding</code>.
	 */
	public void setPointWidth(Number pointWidth) {
		this.pointWidth = pointWidth;
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
	 * Defaults to: true
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
	 * Defaults to true for line and area type series, but to false for columns,
	 * pies etc.
	 * <p>
	 * Defaults to: true
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
	 * The Y axis value to serve as the base for the columns, for distinguishing
	 * between values above and below a threshold. If <code>null</code>, the
	 * columns extend from the padding Y axis minimum.
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
	 * @see #setUpColor(Color)
	 */
	public Color getUpColor() {
		return upColor;
	}

	/**
	 * The color used specifically for positive point columns. When not
	 * specified, the general series color is used.
	 * <p>
	 * Defaults to:
	 */
	public void setUpColor(Color upColor) {
		this.upColor = upColor;
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
}