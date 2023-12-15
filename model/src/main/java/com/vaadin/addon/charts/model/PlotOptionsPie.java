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

import com.vaadin.addon.charts.model.style.Color;
import java.util.ArrayList;
import java.util.Arrays;
import com.vaadin.server.SizeWithUnit;
import com.vaadin.server.Sizeable.Unit;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vaadin.addon.charts.model.serializers.SizeSerializer;
/**
 * A pie chart is a circular chart divided into sectors, illustrating numerical
 * proportion.
 */
public class PlotOptionsPie extends AbstractPlotOptions {

	private Boolean allowPointSelect;
	private Boolean animation;
	private Number animationLimit;
	private Color borderColor;
	private Number borderWidth;
	private String[] center;
	private ArrayList<Color> colors;
	private Cursor cursor;
	private DataLabels dataLabels;
	private Number depth;
	private Boolean enableMouseTracking;
	private Number endAngle;
	private Boolean getExtremesFromAll;
	private Boolean ignoreHiddenPoint;
	@JsonSerialize(using = SizeSerializer.class)
	private String innerSize;
	private ArrayList<String> keys;
	private String linkedTo;
	private Number minSize;
	private Boolean selected;
	private Boolean shadow;
	private Boolean showInLegend;
	@JsonSerialize(using = SizeSerializer.class)
	private String size;
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
	 * The color of the border surrounding each slice. When <code>null</code>,
	 * the border takes the same color as the slice fill. This can be used
	 * together with a <code>borderWidth</code> to fill drawing gaps created by
	 * antialiazing artefacts in borderless pies.
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
	 * <p>
	 * The width of the border surrounding each slice.
	 * </p>
	 * 
	 * <p>
	 * When setting the border width to 0, there may be small gaps between the
	 * slices due to SVG antialiasing artefacts. To work around this, keep the
	 * border width at 0.5 or 1, but set the <code>borderColor</code> to
	 * <code>null</code> instead.
	 * </p>
	 * <p>
	 * Defaults to: 1
	 */
	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
	}

	/**
	 * The center of the pie chart relative to the plot area. Can be percentages
	 * or pixel values. The default behaviour (as of 3.0) is to center the pie
	 * so that all slices and data labels are within the plot area. As a
	 * consequence, the pie may actually jump around in a chart with dynamic
	 * values, as the data labels move. In that case, the center should be
	 * explicitly set, for example to <code>["50%", "50%"]</code>.
	 * <p>
	 * Defaults to: [null, null]
	 */
	public void setCenter(String[] center) {
		this.center = center;
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
	 * A series specific or series type specific color set to use instead of the
	 * global <a href="#colors">colors</a>.
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
	 * @see #setInnerSize(String)
	 */
	public float getInnerSize() {
		String tmp = innerSize;
		if (innerSize == null) {
			return -1.0f;
		}
		if (this.innerSize.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	/**
	 * Sets the innerSize using String presentation. String presentation is
	 * similar to what is used in Cascading Style Sheets. Size can be pixels or
	 * percentage, otherwise IllegalArgumentException is thrown. The empty
	 * string ("") or null will unset the height and set the units to pixels.
	 * 
	 * @param innerSize
	 *            CSS style string representation
	 */
	public void setInnerSize(String innerSize) {
		SizeWithUnit sizeWithUnit = SizeWithUnit.parseStringSize(innerSize);
		if (sizeWithUnit != null) {
			Unit unit = sizeWithUnit.getUnit();
			if (!(unit.equals(Unit.PERCENTAGE) || unit.equals(Unit.PIXELS))) {
				throw new IllegalArgumentException(
						unit.toString()
								+ "is not a valid unit for sizing. Only percentage and pixels are allowed.");
			}
			setInnerSize(sizeWithUnit.getSize(), sizeWithUnit.getUnit());
		} else {
			setInnerSize(-1, Unit.PIXELS);
		}
	}

	/**
	 * @see #setInnerSize(float,Unit)
	 */
	public Unit getInnerSizeUnit() {
		if (this.innerSize == null) {
			return Unit.PIXELS;
		}
		if (this.innerSize.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	/**
	 * Sets the innerSize using Vaadin Unit. Only Unit.PIXELS and
	 * Unit.PERCENTAGE are supported. In all other cases,
	 * IllegalArgumentException is thrown.
	 * 
	 * @param innerSize
	 * @param unit
	 *            the unit used for the innerSize
	 */
	public void setInnerSize(float innerSize, Unit unit) {
		if (!(unit.equals(Unit.PERCENTAGE) || unit.equals(Unit.PIXELS))) {
			throw new IllegalArgumentException(
					unit.toString()
							+ "is not a valid unit for sizing. Only percentage and pixels are allowed.");
		}
		String value = Float.toString(innerSize);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (innerSize == -1) {
			value = null;
		}
		this.innerSize = value;
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
	 * @see #setSize(String)
	 */
	public float getSize() {
		String tmp = size;
		if (size == null) {
			return -1.0f;
		}
		if (this.size.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	/**
	 * Sets the size using String presentation. String presentation is similar
	 * to what is used in Cascading Style Sheets. Size can be pixels or
	 * percentage, otherwise IllegalArgumentException is thrown. The empty
	 * string ("") or null will unset the height and set the units to pixels.
	 * 
	 * @param size
	 *            CSS style string representation
	 */
	public void setSize(String size) {
		SizeWithUnit sizeWithUnit = SizeWithUnit.parseStringSize(size);
		if (sizeWithUnit != null) {
			Unit unit = sizeWithUnit.getUnit();
			if (!(unit.equals(Unit.PERCENTAGE) || unit.equals(Unit.PIXELS))) {
				throw new IllegalArgumentException(
						unit.toString()
								+ "is not a valid unit for sizing. Only percentage and pixels are allowed.");
			}
			setSize(sizeWithUnit.getSize(), sizeWithUnit.getUnit());
		} else {
			setSize(-1, Unit.PIXELS);
		}
	}

	/**
	 * @see #setSize(float,Unit)
	 */
	public Unit getSizeUnit() {
		if (this.size == null) {
			return Unit.PIXELS;
		}
		if (this.size.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	/**
	 * Sets the size using Vaadin Unit. Only Unit.PIXELS and Unit.PERCENTAGE are
	 * supported. In all other cases, IllegalArgumentException is thrown.
	 * 
	 * @param size
	 * @param unit
	 *            the unit used for the size
	 */
	public void setSize(float size, Unit unit) {
		if (!(unit.equals(Unit.PERCENTAGE) || unit.equals(Unit.PIXELS))) {
			throw new IllegalArgumentException(
					unit.toString()
							+ "is not a valid unit for sizing. Only percentage and pixels are allowed.");
		}
		String value = Float.toString(size);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (size == -1) {
			value = null;
		}
		this.size = value;
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

	public void setCenter(String x, String y) {
		this.center = new String[]{x, y};
	}

	public String[] getCenter() {
		return this.center;
	}
}