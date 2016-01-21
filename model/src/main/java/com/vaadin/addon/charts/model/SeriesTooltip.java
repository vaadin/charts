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
/**
 * A configuration object for the tooltip rendering of each single series.
 * Properties are inherited from <a href="#tooltip">tooltip</a>, but only the
 * following properties can be defined on a series level.
 */
public class SeriesTooltip extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private DateTimeLabelFormats dateTimeLabelFormats;
	private Boolean followPointer;
	private Boolean followTouchMove;
	private String footerFormat;
	private String headerFormat;
	private Number hideDelay;
	private String pointFormat;
	private Object pointFormatter;
	private Number valueDecimals;
	private String valuePrefix;
	private String valueSuffix;
	private String xDateFormat;
	private Number changeDecimals;
	private Shape shape;

	public SeriesTooltip() {
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

	/**
	 * @see #setChangeDecimals(Number)
	 */
	public Number getChangeDecimals() {
		return changeDecimals;
	}

	/**
	 * How many decimals to show for the <code>point.change</code> value when
	 * the <code>series.compare</code> option is set. This is overridable in
	 * each series' tooltip options object. The default is to preserve all
	 * decimals.
	 * <p>
	 * Defaults to:
	 */
	public void setChangeDecimals(Number changeDecimals) {
		this.changeDecimals = changeDecimals;
	}

	/**
	 * @see #setShape(Shape)
	 */
	public Shape getShape() {
		return shape;
	}

	/**
	 * The name of a symbol to use for the border around the tooltip. In
	 * Highstock 1.x, the shape was <code>square</code>.
	 * <p>
	 * Defaults to: callout
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}
}