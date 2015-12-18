package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * A pie chart is a circular chart divided into sectors, illustrating numerical
 * proportion.
 */
public class PlotOptionsPie extends AbstractPlotOptions {

	private static final long serialVersionUID = 1L;
	private Boolean allowPointSelect;
	private Boolean animation;
	private Color borderColor;
	private Number borderWidth;
	private Object[] center;
	private Object colors;
	private Cursor cursor;
	private DataLabels dataLabels;
	private Number depth;
	private Boolean enableMouseTracking;
	private Number endAngle;
	private Boolean getExtremesFromAll;
	private Boolean ignoreHiddenPoint;
	private Object innerSize;
	private ArrayList<String> keys;
	private String linkedTo;
	private Number minSize;
	private Boolean selected;
	private Object shadow;
	private Boolean showInLegend;
	private Object size;
	private Number slicedOffset;
	private Number startAngle;
	private States states;
	private Boolean stickyTracking;
	private SeriesTooltip tooltip;
	private Boolean visible;
	private String zoneAxis;
	private ArrayList<Zones> zones;

	public PlotOptionsPie() {
	}

	@Override
	public ChartType getChartType() {
		return ChartType.PIE;
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
	 * <dd>When using jQuery as the general framework, the easing can be set to
	 * <code>linear</code> or <code>swing</code>. More easing functions are
	 * available with the use of jQuery plug-ins, most notably the jQuery UI
	 * suite. See <a href="http://api.jquery.com/animate/">the jQuery docs</a>.
	 * When using MooTools as the general framework, use the property name
	 * <code>transition</code> instead of <code>easing</code>.</dd>
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
	 * The color of the border surrounding each slice.
	 * <p>
	 * Defaults to: #FFFFFF
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
	 * The width of the border surrounding each slice.
	 * <p>
	 * Defaults to: 1
	 */
	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
	}

	/**
	 * @see #setCenter(Object[])
	 */
	public Object[] getCenter() {
		return center;
	}

	/**
	 * @see #setColors(Object)
	 */
	public Object getColors() {
		return colors;
	}

	/**
	 * A series specific or series type specific color set to use instead of the
	 * global <a href="#colors">colors</a>.
	 */
	public void setColors(Object colors) {
		this.colors = colors;
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
	 * The thickness of a 3D pie. Requires <code>highcharts-3d.js</code>
	 * <p>
	 * Defaults to: 0
	 */
	public void setDepth(Number depth) {
		this.depth = depth;
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
	 * @see #setEndAngle(Number)
	 */
	public Number getEndAngle() {
		return endAngle;
	}

	/**
	 * The end angle of the pie in degrees where 0 is top and 90 is right.
	 * Defaults to <code>startAngle</code> plus 360.
	 * <p>
	 * Defaults to: null
	 */
	public void setEndAngle(Number endAngle) {
		this.endAngle = endAngle;
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
	 * @see #setIgnoreHiddenPoint(Boolean)
	 */
	public Boolean getIgnoreHiddenPoint() {
		return ignoreHiddenPoint;
	}

	/**
	 * <p>
	 * Equivalent to <a
	 * href="#chart.ignoreHiddenSeries">chart.ignoreHiddenSeries</a>, this
	 * option tells whether the series shall be redrawn as if the hidden point
	 * were <code>null</code>.
	 * </p>
	 * <p>
	 * The default value changed from <code>false</code> to <code>true</code>
	 * with Highcharts 3.0.
	 * </p>
	 * <p>
	 * Defaults to: true
	 */
	public void setIgnoreHiddenPoint(Boolean ignoreHiddenPoint) {
		this.ignoreHiddenPoint = ignoreHiddenPoint;
	}

	/**
	 * @see #setInnerSize(Object)
	 */
	public Object getInnerSize() {
		return innerSize;
	}

	/**
	 * <p>
	 * The size of the inner diameter for the pie. A size greater than 0 renders
	 * a donut chart. Can be a percentage or pixel value. Percentages are
	 * relative to the pie size. Pixel values are given as integers.
	 * </p>
	 * 
	 * <p>
	 * Note: in Highcharts < 4.1.2, the percentage was relative to the plot
	 * area, not the pie size.
	 * </p>
	 * <p>
	 * Defaults to: 0
	 */
	public void setInnerSize(Object innerSize) {
		this.innerSize = innerSize;
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
	 * @see #setMinSize(Number)
	 */
	public Number getMinSize() {
		return minSize;
	}

	/**
	 * The minimum size for a pie in response to auto margins. The pie will try
	 * to shrink to make room for data labels in side the plot area, but only to
	 * this size.
	 * <p>
	 * Defaults to: 80
	 */
	public void setMinSize(Number minSize) {
		this.minSize = minSize;
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
	 * @see #setShadow(Object)
	 */
	public Object getShadow() {
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
	public void setShadow(Object shadow) {
		this.shadow = shadow;
	}

	/**
	 * @see #setShowInLegend(Boolean)
	 */
	public Boolean getShowInLegend() {
		return showInLegend;
	}

	/**
	 * Whether to display this particular series or series type in the legend.
	 * Since 2.1, pies are not shown in the legend by default.
	 * <p>
	 * Defaults to: false
	 */
	public void setShowInLegend(Boolean showInLegend) {
		this.showInLegend = showInLegend;
	}

	/**
	 * @see #setSize(Object)
	 */
	public Object getSize() {
		return size;
	}

	/**
	 * The diameter of the pie relative to the plot area. Can be a percentage or
	 * pixel value. Pixel values are given as integers. The default behaviour
	 * (as of 3.0) is to scale to the plot area and give room for data labels
	 * within the plot area. As a consequence, the size of the pie may vary when
	 * points are updated and data labels more around. In that case it is best
	 * to set a fixed value, for example <code>"75%"</code>.
	 * <p>
	 * Defaults to:
	 */
	public void setSize(Object size) {
		this.size = size;
	}

	/**
	 * @see #setSlicedOffset(Number)
	 */
	public Number getSlicedOffset() {
		return slicedOffset;
	}

	/**
	 * If a point is sliced, moved out from the center, how many pixels should
	 * it be moved?.
	 * <p>
	 * Defaults to: 10
	 */
	public void setSlicedOffset(Number slicedOffset) {
		this.slicedOffset = slicedOffset;
	}

	/**
	 * @see #setStartAngle(Number)
	 */
	public Number getStartAngle() {
		return startAngle;
	}

	/**
	 * The start angle of the pie slices in degrees where 0 is top and 90 right.
	 * <p>
	 * Defaults to: 0
	 */
	public void setStartAngle(Number startAngle) {
		this.startAngle = startAngle;
	}

	/**
	 * @see #setStates(States)
	 */
	public States getStates() {
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
	 * @see #setTooltip(SeriesTooltip)
	 */
	public SeriesTooltip getTooltip() {
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

	public void setCenter(String x, String y) {
		this.center = new String[]{x, y};
	}

	public void setCenter(Number x, Number y) {
		this.center = new Number[]{x, y};
	}
}