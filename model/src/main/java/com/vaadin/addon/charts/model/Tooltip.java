package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
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
import com.vaadin.addon.charts.model.style.Style;
/**
 * Options for the tooltip that appears when the user hovers over a series or
 * point.
 */
public class Tooltip extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Boolean animation;
	private Color backgroundColor;
	private Color borderColor;
	private Number borderRadius;
	private Number borderWidth;
	private Object[] crosshairs;
	private DateTimeLabelFormats dateTimeLabelFormats;
	private Boolean enabled;
	private Boolean followPointer;
	private Boolean followTouchMove;
	private String footerFormat;
	private String _fn_formatter;
	private String headerFormat;
	private Number hideDelay;
	private String pointFormat;
	private Object pointFormatter;
	private Object positioner;
	private Boolean shadow;
	private Shape shape;
	private Boolean shared;
	private Number snap;
	private Style style;
	private Boolean useHTML;
	private Number valueDecimals;
	private String valuePrefix;
	private String valueSuffix;
	private String xDateFormat;

	public Tooltip() {
	}

	/**
	 * @see #setAnimation(Boolean)
	 */
	public Boolean getAnimation() {
		return animation;
	}

	/**
	 * Enable or disable animation of the tooltip. In slow legacy IE browsers
	 * the animation is disabled by default.
	 * <p>
	 * Defaults to: true
	 */
	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}

	/**
	 * @see #setBackgroundColor(Color)
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * The background color or gradient for the tooltip.
	 * <p>
	 * Defaults to: rgba(255, 255, 255, 0.85)
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @see #setBorderColor(Color)
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * The color of the tooltip border. When <code>null</code>, the border takes
	 * the color of the corresponding series or point.
	 * <p>
	 * Defaults to: null
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
	 * The radius of the rounded border corners.
	 * <p>
	 * Defaults to: 3
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
	 * The pixel width of the tooltip border.
	 * <p>
	 * Defaults to: 1
	 */
	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
	}

	/**
	 * @see #setCrosshairs(Object[])
	 */
	public Object[] getCrosshairs() {
		return crosshairs;
	}

	/**
	 * Since 4.1, the crosshair definitions are moved to the Axis object in
	 * order for a better separation from the tooltip. See <a
	 * href="#xAxis.crosshair">xAxis.crosshair<a>.
	 */
	public void setCrosshairs(Object[] crosshairs) {
		this.crosshairs = crosshairs;
	}

	/**
	 * @see #setDateTimeLabelFormats(DateTimeLabelFormats)
	 */
	public DateTimeLabelFormats getDateTimeLabelFormats() {
		return dateTimeLabelFormats;
	}

	/**
	 * <p>
	 * For series on a datetime axes, the date format in the tooltip's header
	 * will by default be guessed based on the closest data points. This member
	 * gives the default string representations used for each unit. For an
	 * overview of the replacement codes, see <a
	 * href="#Highcharts.dateFormat">dateFormat</a>.
	 * </p>
	 * 
	 * <p>
	 * Defaults to:
	 * 
	 * <pre>
	 * {
	 * 	    millisecond:"%A, %b %e, %H:%M:%S.%L",
	 * 	    second:"%A, %b %e, %H:%M:%S",
	 * 	    minute:"%A, %b %e, %H:%M",
	 * 	    hour:"%A, %b %e, %H:%M",
	 * 	    day:"%A, %b %e, %Y",
	 * 	    week:"Week from %A, %b %e, %Y",
	 * 	    month:"%B %Y",
	 * 	    year:"%Y"
	 * 	}
	 * </pre>
	 * 
	 * </p>
	 * <p>
	 * Defaults to:
	 */
	public void setDateTimeLabelFormats(
			DateTimeLabelFormats dateTimeLabelFormats) {
		this.dateTimeLabelFormats = dateTimeLabelFormats;
	}

	public Tooltip(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setEnabled(Boolean)
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Enable or disable the tooltip.
	 * <p>
	 * Defaults to: true
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setFollowPointer(Boolean)
	 */
	public Boolean getFollowPointer() {
		return followPointer;
	}

	/**
	 * <p>
	 * Whether the tooltip should follow the mouse as it moves across columns,
	 * pie slices and other point types with an extent. By default it behaves
	 * this way for scatter, bubble and pie series by override in the
	 * <code>plotOptions</code> for those series types.
	 * </p>
	 * <p>
	 * For touch moves to behave the same way, <a
	 * href="#tooltip.followTouchMove">followTouchMove</a> must be
	 * <code>true</code> also.
	 * </p>
	 * <p>
	 * Defaults to: false
	 */
	public void setFollowPointer(Boolean followPointer) {
		this.followPointer = followPointer;
	}

	/**
	 * @see #setFollowTouchMove(Boolean)
	 */
	public Boolean getFollowTouchMove() {
		return followTouchMove;
	}

	/**
	 * Whether the tooltip should follow the finger as it moves on a touch
	 * device. If <a href="#chart.zoomType">chart.zoomType</a> is set, it will
	 * override <code>followTouchMove</code>.
	 * <p>
	 * Defaults to: true
	 */
	public void setFollowTouchMove(Boolean followTouchMove) {
		this.followTouchMove = followTouchMove;
	}

	/**
	 * @see #setFooterFormat(String)
	 */
	public String getFooterFormat() {
		return footerFormat;
	}

	/**
	 * A string to append to the tooltip format.
	 * <p>
	 * Defaults to: false
	 */
	public void setFooterFormat(String footerFormat) {
		this.footerFormat = footerFormat;
	}

	public String getFormatter() {
		return _fn_formatter;
	}

	public void setFormatter(String _fn_formatter) {
		this._fn_formatter = _fn_formatter;
	}

	/**
	 * @see #setHeaderFormat(String)
	 */
	public String getHeaderFormat() {
		return headerFormat;
	}

	/**
	 * <p>
	 * The HTML of the tooltip header line. Variables are enclosed by curly
	 * brackets. Available variables are <code>point.key</code>,
	 * <code>series.name</code>, <code>series.color</code> and other members
	 * from the <code>point</code> and <code>series</code> objects. The
	 * <code>point.key</code> variable contains the category name, x value or
	 * datetime string depending on the type of axis. For datetime axes, the
	 * <code>point.key</code> date format can be set using tooltip.xDateFormat.
	 * </p>
	 * 
	 * <p>
	 * Defaults to
	 * <code>&lt;span style="font-size: 10px"&gt;{point.key}&lt;/span&gt;&lt;br/&gt;</code>
	 * </p>
	 * <p>
	 * Defaults to:
	 */
	public void setHeaderFormat(String headerFormat) {
		this.headerFormat = headerFormat;
	}

	/**
	 * @see #setHideDelay(Number)
	 */
	public Number getHideDelay() {
		return hideDelay;
	}

	/**
	 * The number of milliseconds to wait until the tooltip is hidden when mouse
	 * out from a point or chart.
	 * <p>
	 * Defaults to: 500
	 */
	public void setHideDelay(Number hideDelay) {
		this.hideDelay = hideDelay;
	}

	/**
	 * @see #setPointFormat(String)
	 */
	public String getPointFormat() {
		return pointFormat;
	}

	/**
	 * <p>
	 * The HTML of the point's line in the tooltip. Variables are enclosed by
	 * curly brackets. Available variables are point.x, point.y, series.name and
	 * series.color and other properties on the same form. Furthermore, point.y
	 * can be extended by the <code>tooltip.valuePrefix</code> and
	 * <code>tooltip.valueSuffix</code> variables. This can also be overridden
	 * for each series, which makes it a good hook for displaying units.
	 * </p>
	 * <p>
	 * Defaults to: <span style="color:{point.color}">\u25CF</span>
	 * {series.name}: <b>{point.y}</b><br/>
	 */
	public void setPointFormat(String pointFormat) {
		this.pointFormat = pointFormat;
	}

	/**
	 * @see #setPointFormatter(Object)
	 */
	public Object getPointFormatter() {
		return pointFormatter;
	}

	/**
	 * A callback function for formatting the HTML output for a single point in
	 * the tooltip. Like the <code>pointFormat</code> string, but with more
	 * flexibility.
	 * <p>
	 * Defaults to:
	 */
	public void setPointFormatter(Object pointFormatter) {
		this.pointFormatter = pointFormatter;
	}

	/**
	 * @see #setPositioner(Object)
	 */
	public Object getPositioner() {
		return positioner;
	}

	/**
	 * <p>
	 * A callback function to place the tooltip in a default position. The
	 * callback receives three parameters: <code>labelWidth</code>,
	 * <code>labelHeight</code> and <code>point</code>, where point contains
	 * values for <code>plotX</code> and <code>plotY</code> telling where the
	 * reference point is in the plot area. Add <code>chart.plotLeft</code> and
	 * <code>chart.plotTop</code> to get the full coordinates.
	 * </p>
	 * 
	 * <p>
	 * The return should be an object containing x and y values, for example
	 * <code>{ x: 100, y: 100 }</code>.
	 * </p>
	 */
	public void setPositioner(Object positioner) {
		this.positioner = positioner;
	}

	/**
	 * @see #setShadow(Boolean)
	 */
	public Boolean getShadow() {
		return shadow;
	}

	/**
	 * Whether to apply a drop shadow to the tooltip.
	 * <p>
	 * Defaults to: true
	 */
	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}

	/**
	 * @see #setShape(Shape)
	 */
	public Shape getShape() {
		return shape;
	}

	/**
	 * The name of a symbol to use for the border around the tooltip. In
	 * Highcharts 3.x and less, the shape was <code>square</code>.
	 * <p>
	 * Defaults to: callout
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}

	/**
	 * @see #setShared(Boolean)
	 */
	public Boolean getShared() {
		return shared;
	}

	/**
	 * When the tooltip is shared, the entire plot area will capture mouse
	 * movement or touch events. Tooltip texts for series types with ordered
	 * data (not pie, scatter, flags etc) will be shown in a single bubble. This
	 * is recommended for single series charts and for tablet/mobile optimized
	 * charts.
	 * <p>
	 * Defaults to: false
	 */
	public void setShared(Boolean shared) {
		this.shared = shared;
	}

	/**
	 * @see #setSnap(Number)
	 */
	public Number getSnap() {
		return snap;
	}

	/**
	 * Proximity snap for graphs or single points. Does not apply to bars,
	 * columns and pie slices. It defaults to 10 for mouse-powered devices and
	 * 25 for touch devices. Note that since Highcharts 4.1 the whole plot area
	 * by default captures pointer events in order to show the tooltip, so for
	 * tooltip.snap to make sense, <a
	 * href="#plotOptions.series.stickyTracking">stickyTracking</a> must be
	 * <code>false</code>.
	 */
	public void setSnap(Number snap) {
		this.snap = snap;
	}

	/**
	 * @see #setStyle(Style)
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * CSS styles for the tooltip. The tooltip can also be styled through the
	 * CSS class <code>.highcharts-tooltip</code>.
	 * <p>
	 * Defaults to: { "color": "#333333", "cursor": "default", "fontSize":
	 * "12px", "padding": "8px", "pointerEvents": "none", "whiteSpace": "nowrap"
	 * }
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	/**
	 * @see #setUseHTML(Boolean)
	 */
	public Boolean getUseHTML() {
		return useHTML;
	}

	/**
	 * Use HTML to render the contents of the tooltip instead of SVG. Using HTML
	 * allows advanced formatting like tables and images in the tooltip. It is
	 * also recommended for rtl languages as it works around rtl bugs in early
	 * Firefox.
	 * <p>
	 * Defaults to: false
	 */
	public void setUseHTML(Boolean useHTML) {
		this.useHTML = useHTML;
	}

	/**
	 * @see #setValueDecimals(Number)
	 */
	public Number getValueDecimals() {
		return valueDecimals;
	}

	/**
	 * How many decimals to show in each series' y value. This is overridable in
	 * each series' tooltip options object. The default is to preserve all
	 * decimals.
	 */
	public void setValueDecimals(Number valueDecimals) {
		this.valueDecimals = valueDecimals;
	}

	/**
	 * @see #setValuePrefix(String)
	 */
	public String getValuePrefix() {
		return valuePrefix;
	}

	/**
	 * A string to prepend to each series' y value. Overridable in each series'
	 * tooltip options object.
	 */
	public void setValuePrefix(String valuePrefix) {
		this.valuePrefix = valuePrefix;
	}

	/**
	 * @see #setValueSuffix(String)
	 */
	public String getValueSuffix() {
		return valueSuffix;
	}

	/**
	 * A string to append to each series' y value. Overridable in each series'
	 * tooltip options object.
	 */
	public void setValueSuffix(String valueSuffix) {
		this.valueSuffix = valueSuffix;
	}

	/**
	 * @see #setXDateFormat(String)
	 */
	public String getXDateFormat() {
		return xDateFormat;
	}

	/**
	 * The format for the date in the tooltip header if the X axis is a datetime
	 * axis. The default is a best guess based on the smallest distance between
	 * points in the chart.
	 */
	public void setXDateFormat(String xDateFormat) {
		this.xDateFormat = xDateFormat;
	}
}