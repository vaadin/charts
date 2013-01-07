package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

/**
 * The Tooltip class holds options for the tooltip, which appears when the user
 * hovers over a series or point.
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
     * Sets a JavaScript function to format the text of the tooltip. Setting the
     * formatter overrides {@link #setPointFormat(String)} and
     * {@link #setHeaderFormat(String)}. Return false from the JavaScript to
     * disable tooltip for a specific point on series.
     * <p>
     * A subset of HTML is supported. The HTML of the tooltip is parsed and
     * converted to SVG, therefore this isn't a complete HTML renderer. The
     * following elements are supported:
     * <p>
     * <code>&lt;b>&lt;/b>, &lt;strong>&lt;/strong>, &lt;i>&lt;/i>,
     * &lt;em>&lt;/em>, &lt;br/>, &lt;span>&lt;/span></code>.
     * <p>
     * Spans can be styled with a style attribute, but only text-related CSS
     * that is shared with SVG is handled.
     * <p>
     * A tooltip can be shared between multiple series through the shared
     * option. The available data in the formatter differ a bit depending on
     * whether the tooltip is shared or not. In a shared tooltip, all properties
     * except x, which is common for all points, are kept in an array,
     * this.points.
     * <p>
     * Available data:
     * <ul>
     * <li><code>this.percentage</code> (not shared)
     * <li><code>this.points[i].percentage</code> (shared) Stacked series and
     * pies only. The point's percentage of the total.
     * <li><code>this.point</code> (not shared)
     * <li><code>this.points[i].point</code> (shared) The point object. The
     * point name, if defined, is available through this.point.name.
     * <li><code>this.points</code> In a shared tooltip, this is an array
     * containing all other properties for each point.
     * <li><code>this.series</code> (not shared)
     * <li><code>this.points[i].series</code> (shared) The series object. The
     * series name is available through this.series.name.
     * <li><code>this.total</code> (not shared)
     * <li><code>this.points[i].total</code> (shared) Stacked series only. The
     * total value at this point's x value.
     * <li><code>this.x</code> The X value. This property is the same regardless
     * of the tooltip being shared or not.
     * <li><code>this.y</code> (not shared)
     * <li><code>this.points[i].y</code> (shared) The Y value.
     * </ul>
     * 
     * <p>
     * If the formatter is a simple one liner, it can be given in a shorthanded
     * form, e.g. <code>
     * setFormatter("this.series.name +': '+ this.y +''")
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
     * @return The JavaScript tooltip formatting function or null if undefined.
     */
    public String getFormatter() {
        return _fn_formatter;
    }

    /**
     * @see #setValueDecimals(Number)
     */
    public Number getValueDecimals() {
        return valueDecimals;
    }

    /**
     * Sets how many decimals to show in each series' Y value. This is
     * overridable in each series' tooltip options object. The default is to
     * preserve all decimals.
     * 
     * @param valueDecimals
     */
    public void setValueDecimals(Number valueDecimals) {
        this.valueDecimals = valueDecimals;
    }

    /**
     * Sets whether the tooltip is shared. When the tooltip is shared, the
     * entire plot area will capture mouse movement, and tooltip texts for all
     * series will be shown in a single bubble. This is recommended for single
     * series charts and for iPad optimized apps. Defaults to false.
     * 
     * @param shared
     */
    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    /**
     * @see #setShared(boolean)
     */
    public boolean isShared() {
        return shared == null ? false : shared;
    }

    /**
     * @see #setPointFormat(String)
     */
    public String getPointFormat() {
        return pointFormat;
    }

    /**
     * Sets the tooltip formatting string for the point part. The HTML of the
     * point's part in the tooltip. Variables are enclosed by curly brackets.
     * Available variables are <code>point.x</code>, <code>point.y</code>,
     * <code>series.name</code> and <code>series.color</code> as well as other
     * properties on the same form. Furthermore, <code>point.y</code> can be
     * extended by the {@link #setValuePrefix(String)} and
     * {@link #setValueSuffix(String)} values. This can also be overridden for
     * each series, which makes it a good hook for displaying units.
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
     * Sets whether to display crosshairs to connect the points with their
     * corresponding axis values. If the crosshairs option is true, a single
     * crosshair relating to the X-axis will be shown. Defaults to null.
     * 
     * @param b
     */
    public void setCrosshairs(Boolean crosshairs) {
        this.crosshairs = crosshairs;
    }

    /**
     * @see #setCrosshairs(Boolean)
     */
    public Boolean isCrosshairs() {
        return crosshairs;
    }

    /**
     * Sets the formatter string for the header part of tooltips.
     * <p>
     * Defaults to <code>
     * &lt;span style="font-size: 10px"&gt;{point.key}&lt;/span&gt;&lt;br/&gt;
     * </code>
     * 
     * @param headerFormat
     * @see #setFormatter(String)
     * @see #setPointFormat(String)
     */
    public void setHeaderFormat(String headerFormat) {
        this.headerFormat = headerFormat;
    }

    /**
     * @return The formatter string for the header part of tooltips or null if
     *         undefined
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
     * Enables or disables tooltips. Defaults to true.
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Sets a string to append to each series' Y value. Overridable in each
     * series' tooltip options object. Defaults to "".
     * 
     * @param valueSuffix
     */
    public void setValueSuffix(String valueSuffix) {
        this.valueSuffix = valueSuffix;
    }

    /**
     * @see #setValueSuffix(String)
     */
    public String getValueSuffix() {
        return valueSuffix;
    }

    /**
     * Sets a string to prepend to each series' Y value. Overridable in each
     * series' tooltip options object. Defaults to "".
     * 
     * @param string
     */
    public void setValuePrefix(String valuePrefix) {
        this.valuePrefix = valuePrefix;
    }

    /**
     * @see #setValuePrefix(String)
     */
    public String getValuePrefix() {
        return valuePrefix;
    }

}
