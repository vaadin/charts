package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.util.SizeWithUnit;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Funnel charts are a type of chart often used to visualize stages in a sales
 * project, where the top are the initial stages with the most clients. It
 * requires that the <code>modules/funnel.js</code> file is loaded.
 */
public class PlotOptionsFunnel extends AbstractPlotOptions {

	private static final long serialVersionUID = 1L;
	private Boolean allowPointSelect;
	private Color borderColor;
	private Number borderWidth;
	private Object[] center;
	private Object colors;
	private String cursor;
	private DataLabelsFunnel dataLabels;
	private Number depth;
	private Boolean enableMouseTracking;
	private Boolean getExtremesFromAll;
	private String height;
	private ArrayList<String> keys;
	private String linkedTo;
	private Number minSize;
	private String neckHeight;
	private String neckWidth;
	private Boolean reversed;
	private Boolean selected;
	private Object shadow;
	private Boolean showInLegend;
	private Number slicedOffset;
	private States states;
	private Boolean stickyTracking;
	private SeriesTooltip tooltip;
	private Boolean visible;
	private String width;
	private String zoneAxis;
	private ArrayList<Zones> zones;

	public PlotOptionsFunnel() {
	}

	@Override
	public ChartType getChartType() {
		return ChartType.FUNNEL;
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
	 * @see #setCursor(String)
	 */
	public String getCursor() {
		return cursor;
	}

	/**
	 * You can set the cursor to "pointer" if you have click events attached to
	 * the series, to signal to the user that the points and lines can be
	 * clicked.
	 */
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	/**
	 * @see #setDataLabels(DataLabelsFunnel)
	 */
	public DataLabelsFunnel getDataLabels() {
		return dataLabels;
	}

	/**
	 * 
	 */
	public void setDataLabels(DataLabelsFunnel dataLabels) {
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

	public float getHeight() {
		String tmp = height;
		if (height == null) {
			return -1.0f;
		}
		if (this.height.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getHeightUnit() {
		if (this.height == null) {
			return Unit.PIXELS;
		}
		if (this.height.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setHeight(String height) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(height);
		if (tmp != null) {
			setHeight(tmp.getSize(), tmp.getUnit());
		} else {
			setHeight(-1, Unit.PIXELS);
		}
	}

	public void setHeight(float height, Unit unit) {
		String value = Float.toString(height);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (height == -1) {
			value = null;
		}
		this.height = value;
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

	public float getNeckHeight() {
		String tmp = neckHeight;
		if (neckHeight == null) {
			return -1.0f;
		}
		if (this.neckHeight.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getNeckHeightUnit() {
		if (this.neckHeight == null) {
			return Unit.PIXELS;
		}
		if (this.neckHeight.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setNeckHeight(String neckHeight) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(neckHeight);
		if (tmp != null) {
			setNeckHeight(tmp.getSize(), tmp.getUnit());
		} else {
			setNeckHeight(-1, Unit.PIXELS);
		}
	}

	public void setNeckHeight(float neckHeight, Unit unit) {
		String value = Float.toString(neckHeight);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (neckHeight == -1) {
			value = null;
		}
		this.neckHeight = value;
	}

	public float getNeckWidth() {
		String tmp = neckWidth;
		if (neckWidth == null) {
			return -1.0f;
		}
		if (this.neckWidth.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getNeckWidthUnit() {
		if (this.neckWidth == null) {
			return Unit.PIXELS;
		}
		if (this.neckWidth.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setNeckWidth(String neckWidth) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(neckWidth);
		if (tmp != null) {
			setNeckWidth(tmp.getSize(), tmp.getUnit());
		} else {
			setNeckWidth(-1, Unit.PIXELS);
		}
	}

	public void setNeckWidth(float neckWidth, Unit unit) {
		String value = Float.toString(neckWidth);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (neckWidth == -1) {
			value = null;
		}
		this.neckWidth = value;
	}

	/**
	 * @see #setReversed(Boolean)
	 */
	public Boolean getReversed() {
		return reversed;
	}

	/**
	 * A reversed funnel has the widest area down. A reversed funnel with no
	 * neck width and neck height is a pyramid.
	 * <p>
	 * Defaults to: false
	 */
	public void setReversed(Boolean reversed) {
		this.reversed = reversed;
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

	public float getWidth() {
		String tmp = width;
		if (width == null) {
			return -1.0f;
		}
		if (this.width.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getWidthUnit() {
		if (this.width == null) {
			return Unit.PIXELS;
		}
		if (this.width.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setWidth(String width) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(width);
		if (tmp != null) {
			setWidth(tmp.getSize(), tmp.getUnit());
		} else {
			setWidth(-1, Unit.PIXELS);
		}
	}

	public void setWidth(float width, Unit unit) {
		String value = Float.toString(width);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (width == -1) {
			value = null;
		}
		this.width = value;
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