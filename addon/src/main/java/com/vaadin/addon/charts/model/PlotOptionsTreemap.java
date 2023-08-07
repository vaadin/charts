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

import javax.annotation.Generated;
import com.vaadin.addon.charts.model.style.Color;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * The size of the point shape is determined by its value relative to its
 * siblings values. Requires the module <code>heatmap.js</code> as well, if
 * functionality such as the <a
 * href="http://api.highcharts.com/highmaps#colorAxis">colorAxis</a> is to be
 * used.
 */
@Generated(value = "This class is generated and shouldn't be modified", comments = "Incorrect and missing API should be reported to https://github.com/vaadin/charts/issues/new")
public class PlotOptionsTreemap extends AbstractPlotOptions {

	private Boolean allowDrillToNode;
	private Boolean allowPointSelect;
	private Boolean alternateStartingDirection;
	private Boolean animation;
	private Number animationLimit;
	private Color borderColor;
	private Number borderWidth;
	private Color color;
	private Boolean colorByPoint;
	private ArrayList<Color> colors;
	private Number cropThreshold;
	private Cursor cursor;
	private DataLabels dataLabels;
	private Boolean enableMouseTracking;
	private Boolean getExtremesFromAll;
	private Boolean interactByLeaf;
	private ArrayList<String> keys;
	private TreeMapLayoutAlgorithm layoutAlgorithm;
	private TreeMapLayoutStartingDirection layoutStartingDirection;
	private Boolean levelIsConstant;
	private ArrayList<Level> levels;
	private String linkedTo;
	private Number maxPointWidth;
	private Boolean selected;
	private Boolean shadow;
	private Boolean showCheckbox;
	private Boolean showInLegend;
	private Number sortIndex;
	private States states;
	private Boolean stickyTracking;
	private SeriesTooltip tooltip;
	private Number turboThreshold;
	private Boolean visible;
	private String zoneAxis;
	private ArrayList<Zones> zones;

	public PlotOptionsTreemap() {
	}

	@Override
	public ChartType getChartType() {
		return ChartType.TREEMAP;
	}

	/**
	 * @see #setAllowDrillToNode(Boolean)
	 */
	public Boolean getAllowDrillToNode() {
		return allowDrillToNode;
	}

	/**
	 * When enabled the user can click on a point which is a parent and zoom in
	 * on its children.
	 * <p>
	 * Defaults to: false
	 */
	public void setAllowDrillToNode(Boolean allowDrillToNode) {
		this.allowDrillToNode = allowDrillToNode;
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
	 * @see #setAlternateStartingDirection(Boolean)
	 */
	public Boolean getAlternateStartingDirection() {
		return alternateStartingDirection;
	}

	/**
	 * Enabling this option will make the treemap alternate the drawing
	 * direction between vertical and horizontal. The next levels starting
	 * direction will always be the opposite of the previous.
	 * <p>
	 * Defaults to: false
	 */
	public void setAlternateStartingDirection(Boolean alternateStartingDirection) {
		this.alternateStartingDirection = alternateStartingDirection;
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
	 * The color of the border surrounding each tree map item.
	 * <p>
	 * Defaults to: #E0E0E0
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
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
	 * performance on large series.
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
	 * @see #setInteractByLeaf(Boolean)
	 */
	public Boolean getInteractByLeaf() {
		return interactByLeaf;
	}

	/**
	 * This option decides if the user can interact with the parent nodes or
	 * just the leaf nodes. When this option is undefined, it will be true by
	 * default. However when allowDrillToNode is true, then it will be false by
	 * default.
	 */
	public void setInteractByLeaf(Boolean interactByLeaf) {
		this.interactByLeaf = interactByLeaf;
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
	 * @see #setLayoutAlgorithm(TreeMapLayoutAlgorithm)
	 */
	public TreeMapLayoutAlgorithm getLayoutAlgorithm() {
		return layoutAlgorithm;
	}

	/**
	 * This option decides which algorithm is used for setting position and
	 * dimensions of the points. Can be one of <code>sliceAndDice</code>,
	 * <code>stripes</code>, <code>squarified</code> or <code>strip</code>.
	 * <p>
	 * Defaults to: sliceAndDice
	 */
	public void setLayoutAlgorithm(TreeMapLayoutAlgorithm layoutAlgorithm) {
		this.layoutAlgorithm = layoutAlgorithm;
	}

	/**
	 * @see #setLayoutStartingDirection(TreeMapLayoutStartingDirection)
	 */
	public TreeMapLayoutStartingDirection getLayoutStartingDirection() {
		return layoutStartingDirection;
	}

	/**
	 * Defines which direction the layout algorithm will start drawing. Possible
	 * values are "vertical" and "horizontal".
	 * <p>
	 * Defaults to: vertical
	 */
	public void setLayoutStartingDirection(
			TreeMapLayoutStartingDirection layoutStartingDirection) {
		this.layoutStartingDirection = layoutStartingDirection;
	}

	/**
	 * @see #setLevelIsConstant(Boolean)
	 */
	public Boolean getLevelIsConstant() {
		return levelIsConstant;
	}

	/**
	 * Used together with the levels and allowDrillToNode options. When set to
	 * false the first level visible when drilling is considered to be level
	 * one. Otherwise the level will be the same as the tree structure.
	 * <p>
	 * Defaults to: true
	 */
	public void setLevelIsConstant(Boolean levelIsConstant) {
		this.levelIsConstant = levelIsConstant;
	}

	/**
	 * @see #setLevels(Level...)
	 */
	public Level[] getLevels() {
		if (levels == null) {
			return new Level[]{};
		}
		Level[] arr = new Level[levels.size()];
		levels.toArray(arr);
		return arr;
	}

	/**
	 * Set options on specific levels. Takes precedence over series options, but
	 * not point options.
	 */
	public void setLevels(Level... levels) {
		this.levels = new ArrayList<Level>(Arrays.asList(levels));
	}

	/**
	 * Adds level to the levels array
	 * 
	 * @param level
	 *            to add
	 * @see #setLevels(Level...)
	 */
	public void addLevel(Level level) {
		if (this.levels == null) {
			this.levels = new ArrayList<Level>();
		}
		this.levels.add(level);
	}

	/**
	 * Removes first occurrence of level in levels array
	 * 
	 * @param level
	 *            to remove
	 * @see #setLevels(Level...)
	 */
	public void removeLevel(Level level) {
		this.levels.remove(level);
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
	 * Whether to display this series type or specific series item in the
	 * legend.
	 * <p>
	 * Defaults to: false
	 */
	public void setShowInLegend(Boolean showInLegend) {
		this.showInLegend = showInLegend;
	}

	/**
	 * @see #setSortIndex(Number)
	 */
	public Number getSortIndex() {
		return sortIndex;
	}

	/**
	 * The sort index of the point inside the treemap level.
	 */
	public void setSortIndex(Number sortIndex) {
		this.sortIndex = sortIndex;
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
