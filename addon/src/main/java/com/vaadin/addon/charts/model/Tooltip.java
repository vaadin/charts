package com.vaadin.addon.charts.model;

/**
 * Options for the tooltip that appears when the user hovers over a series or
 * point.
 */
public class Tooltip extends AbstractConfigurationObject {

    private String _fn_formatter;
    private Number valueDecimals;
    private String pointFormat;
    private Boolean shared;
    private Boolean crosshairs;
    private String headerFormat;
    private Boolean enabled;
    private String valueSuffix;
    private String valuePrefix;

    /**
     * Sets the JS function to format the text of the tooltip. Setting formatter
     * overrides {@link #setPointFormat(String)} and
     * {@link #setHeaderFormat(String)}. Return false to disable tooltip for a
     * specific point on series.
     * <p>
     * A subset of HTML is supported. The HTML of the tooltip is parsed and
     * converted to SVG, therefore this isn't a complete HTML renderer. The
     * following tabs are supported:
     * <p>
     * <code>&lt;b>&lt;/b>, &lt;strong>&lt;/strong>, &lt;i>&lt;/i>,
     * &lt;em>&lt;/em>, &lt;br/>
     * , &lt;span>&lt;/span></code>.
     * <p>
     * Spans can be styled with a style attribute, but only text-related CSS
     * that is shared with SVG is handled.
     * <p>
     * Tooltip can be shared between multiple series through the shared option.
     * The available data in the formatter differ a bit depending on whether the
     * tooltip is shared or not. In a shared tooltip, all properties except x,
     * which is common for all points, are kept in an array, this.points.
     * <p>
     * Available data:
     * <ul>
     * <li>this.percentage (not shared)
     * <li>this.points[i].percentage (shared) Stacked series and pies only. The
     * point's percentage of the total.
     * <li>this.point (not shared)
     * <li>this.points[i].point (shared) The point object. The point name, if
     * defined, is available through this.point.name.
     * <li>this.points In a shared tooltip, this is an array containing all
     * other properties for each point.
     * <li>this.series (not shared)
     * <li>this.points[i].series (shared) The series object. The series name is
     * available through this.series.name.
     * <li>this.total (not shared)
     * <li>this.points[i].total (shared) Stacked series only. The total value at
     * this point's x value.
     * <li>this.x The x value. This property is the same regardless of the
     * tooltip being shared or not.
     * <li>this.y (not shared)
     * <li>this.points[i].y (shared) The y value.
     * </ul>
     * 
     * <p>
     * If formatter is a simple one liner, it can be given in a shorthanded form
     * e.g. like this: <code>
     * "this.series.name +': '+ this.y +''"
     * </code>
     * 
     * @param formatter
     * @see #getFormatter()
     * @see #getHeaderFormat()
     * @see #getPointFormat()
     */
    public void setFormatter(String formatter) {
        _fn_formatter = formatter;
    }

    /**
     * @see #setFormatter(String)
     * @return
     */
    public String getFormatter() {
        return _fn_formatter;
    }

    /**
     * @see #setValueDecimals(Number)
     * @return
     */
    public Number getValueDecimals() {
        return valueDecimals;
    }

    /**
     * How many decimals to show in each series' y value. This is overridable in
     * each series' tooltip options object. The default is to preserve all
     * decimals.
     * 
     * @param valueDecimals
     */
    public void setValueDecimals(Number valueDecimals) {
        this.valueDecimals = valueDecimals;
    }

    /**
     * When the tooltip is shared, the entire plot area will capture mouse
     * movement, and tooltip texts for all series will be shown in a single
     * bubble. This is recommended for single series charts and for iPad
     * optimized sites. Defaults to false.
     * 
     * @param shared
     */
    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    /**
     * @see #setShared(boolean)
     * @return
     */
    public boolean isShared() {
        return shared == null ? false : shared;
    }

    /**
     * @see #setPointFormat(String)
     * @return
     */
    public String getPointFormat() {
        return pointFormat;
    }

    /**
     * Sets the tooltip formatting string for the point part. The HTML of the
     * point's part in the tooltip. Variables are enclosed by curly brackets.
     * Available variables are point.x, point.y, series.name and series.color
     * and other properties on the same form. Furthermore, point.y can be
     * extended by the tooltip.yPrefix and tooltip.ySuffix variables. This can
     * also be overridden for each series, which makes it a good hook for
     * displaying units.
     * <p>
     * Defaults to <code>
     * &lt;span style="color:{series.color}"&gt;{series.name}&lt;/span&gt;: &lt;b&gt;{point.y}&lt;/b&gt;
     * </code>
     * 
     * @param pointFormat
     */
    public void setPointFormat(String pointFormat) {
        this.pointFormat = pointFormat;
    }

    /**
     * Display crosshairs to connect the points with their corresponding axis
     * values. The crosshairs can be defined as a boolean, an array of booleans
     * or an object.
     * 
     * Boolean If the crosshairs option is true, a single crosshair relating to
     * the x axis will be shown. Array of booleans In an array of booleans, the
     * first value turns on the x axis crosshair and the second value to the y
     * axis crosshair. Use [true, true] to show complete crosshairs. Array of
     * objects In an array of objects, the first value applies to the x axis
     * crosshair and the second value to the y axis crosshair. For each
     * dimension, a width, color, dashStyle and zIndex can be given.
     * 
     * Defaults to null. Defaults to null.
     * 
     * @param b
     */
    public void setCrosshairs(Boolean crosshairs) {
        this.crosshairs = crosshairs;
    }

    /**
     * @see #setCrosshairs(Boolean)
     * @return
     */
    public Boolean isCrosshairs() {
        return crosshairs;
    }

    /**
     * Sets the formatter string for tooltips header part.
     * 
     * @param headerFormat
     * @see #getHeaderFormat()
     * @see #getFormatter()
     */
    public void setHeaderFormat(String headerFormat) {
        this.headerFormat = headerFormat;
    }

    /**
     * Formatter string to tooltips header part.
     * <p>
     * Defaults to <code>
     * &lt;span style="font-size: 10px"&gt;{point.key}&lt;/span&gt;&lt;br/&gt;
     * </code>
     * 
     * @return
     * @see #setHeaderFormat(String)
     * @see #getPointFormat()
     * @see #getFormatter()
     */
    public String getHeaderFormat() {
        return headerFormat;
    }

    /**
     * @see #setEnabled(Boolean)
     * @return true if tooltips are displayed
     */
    public boolean isEnabled() {
        return enabled == null ? true : enabled;
    }

    /**
     * Enable or disable the tooltip. Defaults to true if set to null.
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * A string to append to each series' y value. Overridable in each series'
     * tooltip options object. Defaults to "".
     * 
     * @param valueSuffix
     */
    public void setValueSuffix(String valueSuffix) {
        this.valueSuffix = valueSuffix;
    }

    /**
     * @see #setValueSuffix(String)
     * @return
     */
    public String getValueSuffix() {
        return valueSuffix;
    }

    /**
     * A string to prepend to each series' y value. Overridable in each series'
     * tooltip options object. Defaults to "".
     * 
     * @param string
     */
    public void setValuePrefix(String valuePrefix) {
        this.valuePrefix = valuePrefix;
    }

    /**
     * @see #setValuePrefix(String)
     * @return
     */
    public String getValuePrefix() {
        return valuePrefix;
    }

}
