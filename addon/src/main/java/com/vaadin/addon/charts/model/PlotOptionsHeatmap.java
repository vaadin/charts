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
/**
 * <p>
 * The heatmap series type. This series type is available both in Highcharts and
 * Highmaps.
 * </p>
 * 
 * <p>
 * The colors of each heat map point is usually determined by its value and
 * controlled by settings on the <a href="#colorAxis">colorAxis</a>.
 * </p>
 */
public class PlotOptionsHeatmap extends AbstractPlotOptions {

	private Boolean allowPointSelect;
	private Boolean animation;
	private Number animationLimit;
	private Color borderColor;
	private Number borderRadius;
	private Number borderWidth;
	private Color color;
	private Boolean colorByPoint;
	private ArrayList<Color> colors;
	private Number colsize;
	private Number cropThreshold;
	private Cursor cursor;
	private DataLabels dataLabels;
	private Boolean enableMouseTracking;
	private Boolean getExtremesFromAll;
	private ArrayList<String> keys;
	private String linkedTo;
	private Number maxPointWidth;
	private Number rowsize;
	private Boolean selected;
	private Boolean shadow;
	private Boolean showCheckbox;
	private Boolean showInLegend;
	private States states;
	private Boolean stickyTracking;
	private SeriesTooltip tooltip;
	private Number turboThreshold;
	private Boolean visible;
	private String zoneAxis;
	private ArrayList<Zones> zones;

	public PlotOptionsHeatmap() {
	}

	@Override
	public ChartType getChartType() {
		return ChartType.HEATMAP;
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
	 * @see #setAnimationLimit(Number)
	 */
	public Number getAnimationLimit() {
		return animationLimit;
	}

	/**
	 * For some series, there is a limit that shuts down initial animation by
	 * default when the total number of points in the chart is too high. For
	 * example, for a column chart and its derivatives, animation doesn't run if
	 * there is more than 250 points totally. To disable this cap, set
	 * <code>animationLimit</code> to <code>Infinity</code>.
	 */
	public void setAnimationLimit(Number animationLimit) {
		this.animationLimit = animationLimit;
	}

	/**
	 * @see #setBorderColor(Color)
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * The color of the border surrounding each column or bar.
	 * <p>
	 * Defaults to: #FFFFFF
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
	 * The main color of the series. In heat maps this color is rarely used, as
	 * we mostly use the color to denote the value of each point. Unless options
	 * are set in the <a href="#colorAxis">colorAxis</a>, the default value is
	 * pulled from the <a href="#colors">options.colors</a> array.
	 * <p>
	 * Defaults to: null
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

	/**
	 * @see #setColors(Color...)
	 */
	public Color[] getColors() {
		if (colors == null) {
			return new Color[]{};
		}
		Color[] arr = new Color[colors.size()];
		colors.toArray(arr);
		return arr;
	}

	/**
	 * A series specific or series type specific color set to apply instead of
	 * the global <a href="#colors">colors</a> when <a
	 * href="#plotOptions.column.colorByPoint">colorByPoint</a> is true.
	 */
	public void setColors(Color... colors) {
		this.colors = new ArrayList<Color>(Arrays.asList(colors));
	}

	/**
	 * Adds color to the colors array
	 * 
	 * @param color
	 *            to add
	 * @see #setColors(Color...)
	 */
	public void addColor(Color color) {
		if (this.colors == null) {
			this.colors = new ArrayList<Color>();
		}
		this.colors.add(color);
	}

	/**
	 * Removes first occurrence of color in colors array
	 * 
	 * @param color
	 *            to remove
	 * @see #setColors(Color...)
	 */
	public void removeColor(Color color) {
		this.colors.remove(color);
	}

	/**
	 * @see #setColsize(Number)
	 */
	public Number getColsize() {
		return colsize;
	}

	/**
	 * The column size - how many X axis units each column in the heatmap should
	 * span.
	 * <p>
	 * Defaults to: 1
	 */
	public void setColsize(Number colsize) {
		this.colsize = colsize;
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
	 * Defaults to: 50
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
	 * @see #setDataLabels(DataLabels)
	 */
	public DataLabels getDataLabels() {
		if (dataLabels == null) {
			dataLabels = new DataLabels();
		}
		return dataLabels;
	}

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

	/**
	 * @see #setKeys(String...)
	 */
	public String[] getKeys() {
		if (keys == null) {
			return new String[]{};
		}
		String[] arr = new String[keys.size()];
		keys.toArray(arr);
		return arr;
	}

	/**
	 * An array specifying which option maps to which key in the data point
	 * array. This makes it convenient to work with unstructured data arrays
	 * from different sources.
	 */
	public void setKeys(String... keys) {
		this.keys = new ArrayList<String>(Arrays.asList(keys));
	}

	/**
	 * Adds key to the keys array
	 * 
	 * @param key
	 *            to add
	 * @see #setKeys(String...)
	 */
	public void addKey(String key) {
		if (this.keys == null) {
			this.keys = new ArrayList<String>();
		}
		this.keys.add(key);
	}

	/**
	 * Removes first occurrence of key in keys array
	 * 
	 * @param key
	 *            to remove
	 * @see #setKeys(String...)
	 */
	public void removeKey(String key) {
		this.keys.remove(key);
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
	 * @see #setRowsize(Number)
	 */
	public Number getRowsize() {
		return rowsize;
	}

	/**
	 * The row size - how many Y axis units each heatmap row should span.
	 * <p>
	 * Defaults to: 1
	 */
	public void setRowsize(Number rowsize) {
		this.rowsize = rowsize;
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
	 * @see #setTurboThreshold(Number)
	 */
	public Number getTurboThreshold() {
		return turboThreshold;
	}

	/**
	 * When a series contains a data array that is longer than this, only one
	 * dimensional arrays of numbers, or two dimensional arrays with x and y
	 * values are allowed. Also, only the first point is tested, and the rest
	 * are assumed to be the same format. This saves expensive data checking and
	 * indexing in long series. Set it to <code>0</code> disable.
	 * <p>
	 * Defaults to: 1000
	 */
	public void setTurboThreshold(Number turboThreshold) {
		this.turboThreshold = turboThreshold;
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

	/**
	 * @see #setZones(Zones...)
	 */
	public Zones[] getZones() {
		if (zones == null) {
			return new Zones[]{};
		}
		Zones[] arr = new Zones[zones.size()];
		zones.toArray(arr);
		return arr;
	}

	/**
	 * An array defining zones within a series. Zones can be applied to the X
	 * axis, Y axis or Z axis for bubbles, according to the
	 * <code>zoneAxis</code> option.
	 */
	public void setZones(Zones... zones) {
		this.zones = new ArrayList<Zones>(Arrays.asList(zones));
	}

	/**
	 * Adds zone to the zones array
	 * 
	 * @param zone
	 *            to add
	 * @see #setZones(Zones...)
	 */
	public void addZone(Zones zone) {
		if (this.zones == null) {
			this.zones = new ArrayList<Zones>();
		}
		this.zones.add(zone);
	}

	/**
	 * Removes first occurrence of zone in zones array
	 * 
	 * @param zone
	 *            to remove
	 * @see #setZones(Zones...)
	 */
	public void removeZone(Zones zone) {
		this.zones.remove(zone);
	}
}