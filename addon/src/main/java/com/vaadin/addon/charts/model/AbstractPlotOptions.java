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

import java.util.Date;

import com.vaadin.addon.charts.ChartTheme;
import com.vaadin.addon.charts.model.style.Color;

/**
 * Plot options are used to customize the representation of the chart. There are
 * lots of configurable things like colors, fonts, tooltips etc. Some of these
 * configurations are applicable for only certain chart types, so be sure to
 * check relevant subclass.
 * <p>
 * These configurations can be set in multiple places. Initial defaults for plot
 * options are set by the framework and can be customized with
 * {@link ChartTheme}. The most common method to use plot options is to call
 * {@link Configuration#setPlotOptions(AbstractPlotOptions)}. Values defined
 * here will override defaults from the theme. Options that are not defined at
 * this level will be inherited from the theme defaults. If the chart is a
 * "combination chart", plot options for different types can be defined.
 * <p>
 * Third place where plot options can be used is at series level. If chart has
 * several series of data, configurations defined at theme and component level
 * can still by fine tuned with
 * {@link AbstractSeries#setPlotOptions(AbstractPlotOptions)}. The same method
 * is also used to define the chart type for the series in "combination charts".
 * <p>
 * {@link PlotOptionsSeries} is a special plot options type that can be used to
 * define default options shared by all chart types.
 */
public abstract class AbstractPlotOptions extends AbstractConfigurationObject {
    
    private Labels dataLabels;

    private Stacking stacking;

    private Color lineColor;
    private Color fillColor;

    private Number fillOpacity;
    private Number lineWidth;
    private Number zIndex;
    private Number pointStart;
    private Number pointInterval;

    private Boolean visible;
    private Boolean shadow;
    private Boolean allowPointSelect;
    private Boolean showInLegend;
    private Boolean enableMouseTracking;
    private Boolean stickyTracking;

    private Cursor cursor;

    private Boolean animation;

    private Marker marker;
    private PointPlacement pointPlacement;

    private Color color;

    private States states;

    private Tooltip tooltip;

    /**
     * @return the type of chart
     */
    public abstract ChartType getChartType();

    /**
     * @see #setDataLabels()
     */
    public Labels getDataLabels() {
        return dataLabels;
    }

    /**
     * Set the labels for plot point items (points/bars/columns etc.)
     * 
     * @param dataLabels
     */
    public void setDataLabels(Labels dataLabels) {
        this.dataLabels = dataLabels;
    }

    /**
     * @see #setzIndex(Number)
     * @return Z index or null if not defined
     */
    public Number getzIndex() {
        return zIndex;
    }

    /**
     * Define the z index of the series. Defaults to null.
     * 
     * @param zIndex
     */
    public void setzIndex(Number zIndex) {
        this.zIndex = zIndex;
    }

    /**
     * @see #setVisible(Boolean)
     * @return the visibility, true if undefined.
     */
    public boolean isVisible() {
        return visible == null ? true : visible;
    }

    /**
     * Set the initial visibility of the series. Defaults to true.
     * 
     * @param visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * @see #setStacking(Stacking)
     * @return Stacking or null if not defined
     */
    public Stacking getStacking() {
        return stacking;
    }

    /**
     * Sets whether to stack the values of each series on top of each other.
     * Possible values are null to disable, {@link Stacking#NORMAL} to stack by
     * value or {@link Stacking#PERCENT}. Defaults to null.
     * 
     * @param stacking
     */
    public void setStacking(Stacking stacking) {
        this.stacking = stacking;
    }

    /**
     * @see #setLineColor(Color)
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * Sets a separate color for the graph line. By default the line takes the
     * color of the series, but the lineColor setting allows setting a separate
     * color for the line without altering the fillColor. Defaults to null
     * 
     * @param lineColor
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * @see #setLineWidth(Number)
     * @return Line width or null if undefined
     */
    public Number getLineWidth() {
        return lineWidth;
    }

    /**
     * Sets the with of the graph line in pixels. Defaults to 2.
     * 
     * @param lineWidth
     */
    public void setLineWidth(Number lineWidth) {
        this.lineWidth = lineWidth;
    }

    /**
     * @see #setFillColor(Color)
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Sets the fill color or gradient for the area. When null, the series'
     * color is used with the series' fillOpacity. Defaults to null.
     * 
     * @param fillColor
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * @see #setFillOpacity(Number)
     * @return Fill opacity or null if not defined
     */
    public Number getFillOpacity() {
        return fillOpacity;
    }

    /**
     * Sets the fill opacity for the area. Defaults to .75.
     * 
     * @param fillOpacity
     */
    public void setFillOpacity(Number fillOpacity) {
        this.fillOpacity = fillOpacity;
    }

    /**
     * Sets whether to apply a drop shadow below the graph line. Defaults to
     * true.
     */
    public void setShadow(Boolean shadows) {
        shadow = shadows;
    }

    /**
     * @see #setShadow(boolean)
     */
    public boolean isShadows() {
        return shadow == null ? true : shadow;
    }

    /**
     * Enables or disables the mouse tracking for a specific series. This
     * includes point tooltips and click events on graphs and points. For large
     * data sets it improves performance. Enabled by default.
     * 
     * @param enableMouseTracking
     */
    public void setEnableMouseTracking(Boolean enableMouseTracking) {
        this.enableMouseTracking = enableMouseTracking;
    }

    /**
     * @see #setEnableMouseTracking(Boolean)
     */
    public boolean isEnableMouseTracking() {
        return enableMouseTracking == null ? true : enableMouseTracking;
    }

    /**
     * You can set the cursor to {@link Cursor#POINTER} if you have click events
     * attached to the series, to signal to the user that the points and lines
     * can be clicked. Defaults to {@link Cursor#NONE}.
     */
    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    /**
     * @see #setCursor(Cursor)
     */
    public Cursor getCursor() {
        return cursor;
    }

    /**
     * Sets the whether to use animation for initial plotting.
     * 
     * @param animation
     */
    public void setAnimation(Boolean animation) {
        this.animation = animation;
    }

    /**
     * @see #setAnimation(Boolean)
     * @return true if animation is enabled
     */
    public boolean isAnimation() {
        return animation == null ? true : animation;
    }

    /**
     * If no X values are given for the points in a series, pointStart defines
     * on which value to start. For example, if a series contains a yearly value
     * starting from 1945, set pointStart to 1945. Defaults to 0.
     * 
     * @param pointStart
     */
    public void setPointStart(Number pointStart) {
        this.pointStart = pointStart;
    }

    /**
     * If no X values are given for the points in a series, pointStart defines
     * on what value to start. For example, if a series contains a yearly value
     * starting from 1945, set pointStart to 1945. Defaults to 0.
     * 
     * @param pointStart
     */
    public void setPointStart(Date date) {
        pointStart = date.getTime();
    }

    /**
     * @see #setPointStart(Number)
     */
    public Number getPointStart() {
        return pointStart;
    }

    /**
     * If no X values are given for the points in a series, pointInterval
     * defines the interval of the values on the X-axis. For example, if a
     * series contains a value for every decade starting from year 0, set
     * pointInterval to 10. Defaults to 1.
     * 
     * @param pointInterval
     */
    public void setPointInterval(Number pointInterval) {
        this.pointInterval = pointInterval;
    }

    /**
     * @see #setPointInterval(Number)
     */
    public Number getPointInterval() {
        return pointInterval;
    }

    /**
     * In a column chart, when pointPlacement is {@link PointPlacement#ON}, the
     * point will not create any padding of the X-axis.
     * 
     * In a polar column chart {@link PointPlacement#ON} means that the first
     * column points directly north.
     * 
     * If pointPlacement is {@link PointPlacement#BETWEEN}, the columns will be
     * laid out between ticks. This is useful for example for visualizing an
     * amount between two points in time or in a certain sector of a polar
     * chart.
     * 
     * Defaults to null in Cartesian charts, {@link PointPlacement#BETWEEN} in
     * polar charts.
     * 
     * @param pointPlacement
     */
    public void setPointPlacement(PointPlacement pointPlacement) {
        this.pointPlacement = pointPlacement;
    }

    /**
     * @see #setPointPlacement(PointPlacement)
     * @return the polar placement or null if none.
     */
    public PointPlacement getPointPlacement() {
        return pointPlacement;
    }

    /**
     * @see #setAllowPointSelect(Boolean)
     */
    public boolean isAllowPointSelect() {
        return allowPointSelect == null ? false : allowPointSelect;
    }

    /**
     * Sets whether to allow points in this series to be selected by clicking on
     * the markers, bars or pie slices. Defaults to false.
     * 
     * @param allowPointSelect
     */
    public void setAllowPointSelect(Boolean allowPointSelect) {
        this.allowPointSelect = allowPointSelect;
    }

    /**
     * @see #setShowInLegend(Boolean)
     */
    public boolean isShowInLegend() {
        return showInLegend == null ? false : showInLegend;
    }

    /**
     * Sets whether to display this particular series or series type in the
     * legend. Defaults to false.
     * 
     * @param showInLegend
     */
    public void setShowInLegend(Boolean showInLegend) {
        this.showInLegend = showInLegend;
    }

    /**
     * @see #setMarker(Marker)
     */
    public Marker getMarker() {
        return marker;
    }

    /**
     * Sets the marker used for the plot point items (points/bars/columns)
     * 
     * @param marker
     */
    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    /**
     * Sets the sticky tracking of mouse events. When true (default), the
     * mouseOut event on a series isn't triggered until the mouse moves over
     * another series, or out of the plot area. When false, the mouseOut event
     * on a series is triggered when the mouse leaves the area around the
     * series' graph or markers. This also implies the tooltip. When
     * stickyTracking is false and tooltip.shared is false, the tooltip will be
     * hidden when moving the mouse between series. Defaults to true.
     * 
     * @param stickyTracking
     */
    public void setStickyTracking(Boolean stickyTracking) {
        this.stickyTracking = stickyTracking;
    }

    /**
     * @see #setStickyTracking(Boolean)
     * @return whether sticky tracking is on or off, true if undefined.
     */
    public Boolean isStickyTracking() {
        return stickyTracking == null ? true : stickyTracking;
    }

    /**
     * @see #setColor(Color)
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the main color of the series. In line type series it applies to the
     * line and the point markers unless otherwise specified. In bar type series
     * it applies to the bars unless a color is specified per point. The default
     * value is pulled from the VaadinTheme colors
     * 
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @see #setStates(States)
     */
    public States getStates() {
        return states;
    }

    /**
     * Sets the wrapper object for all the series options in specific states.
     * 
     * @param states
     */
    public void setStates(States states) {
        this.states = states;
    }

    /**
     * @see #setTooltip(Tooltip)
     */
    public Tooltip getTooltip() {
        if (tooltip == null) {
            tooltip = new Tooltip();
        }
        return tooltip;
    }

    /**
     * Sets the configuration object for the tooltip rendering of each single
     * series. Properties are inherited from tooltip. Overridable properties are
     * {@link Tooltip#setHeaderFormat(String)},
     * {@link Tooltip#setPointFormat(String)},
     * {@link Tooltip#setValueDecimals(Number)},
     * {@link Tooltip#setValuePrefix(String)} and
     * {@link Tooltip#setValueSuffix(String)}. Defaults to nothing.
     * 
     * @param tooltip
     */
    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

}
