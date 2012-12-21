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

import com.vaadin.addon.charts.model.style.Color;

/**
 * Common plot options shared by all extending PlotOptions classes
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
    private Boolean selected;
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
    
    public abstract ChartType getChartType();

    /**
     * @see #setDataLabels()
     */
    public Labels getDataLabels() {
        return dataLabels;
    }

    /**
     * Labels for plot point items (points/bars/columns etc.)
     * 
     * @param dataLabels
     */
    public void setDataLabels(Labels dataLabels) {
        this.dataLabels = dataLabels;
    }

    /**
     * @see #setSelected(Boolean)
     * @return Selection state or null if not defined
     */
    public boolean isSelected() {
        return selected == null ? false : selected;
    }

    /**
     * Whether to select the series initially. If showCheckbox is true, the
     * checkbox next to the series name will be checked for a selected series.
     * Defaults to false.
     * 
     * @param selected
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
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
     * @return Visibility or null if not defined
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
     * Whether to stack the values of each series on top of each other. Possible
     * values are null to disable, "normal" to stack by value or "percent".
     * Defaults to null.
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
     * A separate color for the graph line. By default the line takes the color
     * of the series, but the lineColor setting allows setting a separate color
     * for the line without altering the fillColor. Defaults to null
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
     * Pixel with of the graph line. Defaults to 2.
     * 
     * @param lineWidth
     */
    public void setLineWidth(Number lineWidth) {
        this.lineWidth = lineWidth;
    }

    /**
     * @see #setFillColor(Color)
     * @return
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Fill color or gradient for the area. When null, the series' color is used
     * with the series' fillOpacity. Defaults to null.
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
     * Fill opacity for the area. Defaults to .75.
     * 
     * @param fillOpacity
     */
    public void setFillOpacity(Number fillOpacity) {
        this.fillOpacity = fillOpacity;
    }

    /**
     * Whether to apply a drop shadow to the graph line. Defaults to true.
     */
    public void setShadow(Boolean shadows) {
        shadow = shadows;
    }

    /**
     * @see #setShadow(boolean)
     * @return
     */
    public boolean isShadows() {
        return shadow == null ? true : shadow;
    }

    /**
     * Enable or disable the mouse tracking for a specific series. This includes
     * point tooltips and click events on graphs and points. For large datasets
     * it improves performance. Defaults to true.
     * 
     * @param enableMouseTracking
     */
    public void setEnableMouseTracking(Boolean enableMouseTracking) {
        this.enableMouseTracking = enableMouseTracking;
    }

    /**
     * @see #setEnableMouseTracking(Boolean)
     * @return
     */
    public boolean isEnableMouseTracking() {
        return enableMouseTracking == null ? true : enableMouseTracking;
    }

    /**
     * You can set the cursor to "pointer" if you have click events attached to
     * the series, to signal to the user that the points and lines can be
     * clicked. Defaults to ''.
     */
    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    /**
     * @see #setCursor(Cursor)
     * @return
     */
    public Cursor getCursor() {
        return cursor;
    }

    /**
     * Set the animation for initial plotting.
     * 
     * @param animation
     */
    public void setAnimation(Boolean animation) {
        this.animation = animation;
    }

    /**
     * @see #setAnimation(Boolean)
     * @return true is animation are enabled
     */
    public boolean isAnimation() {
        return animation == null ? true : animation;
    }

    /**
     * If no x values are given for the points in a series, pointStart defines
     * on what value to start. For example, if a series contains one yearly
     * value starting from 1945, set pointStart to 1945. Defaults to 0.
     * 
     * @param pointStart
     */
    public void setPointStart(Number pointStart) {
        this.pointStart = pointStart;
    }
    
    /**
     * If no x values are given for the points in a series, pointStart defines
     * on what value to start. For example, if a series contains one yearly
     * value starting from 1945, set pointStart to 1945. Defaults to 0.
     * 
     * @param pointStart
     */
    public void setPointStart(Date date) {
        pointStart = date.getTime();
    }


    /**
     * @see #setPointStart(Number)
     * @return
     */
    public Number getPointStart() {
        return pointStart;
    }

    /**
     * If no x values are given for the points in a series, pointInterval
     * defines the interval of the x values. For example, if a series contains
     * one value every decade starting from year 0, set pointInterval to 10. .
     * Defaults to 1.
     * 
     * @param pointInterval
     */
    public void setPointInterval(Number pointInterval) {
        this.pointInterval = pointInterval;
    }

    /**
     * @see #setPointInterval(Number)
     * @return
     */
    public Number getPointInterval() {
        return pointInterval;
    }

    /**
     * Possible values: null, "on", "between".
     * 
     * In a column chart, when pointPlacement is "on", the point will not create
     * any padding of the X axis. In a polar column chart this means that the
     * first column points directly north. If the pointPlacement is "between",
     * the columns will be laid out between ticks. This is useful for example
     * for visualizing an amount between two points in time or in a certain
     * sector of a polar chart.
     * 
     * Defaults to null in Cartesian charts, "between" in polar charts.
     * 
     * @param pointPlacement
     */
    public void setPointPlacement(PointPlacement pointPlacement) {
        this.pointPlacement = pointPlacement;
    }

    /**
     * @see #setPointPlacement(PointPlacement)
     * @return
     */
    public PointPlacement getPointPlacement() {
        return pointPlacement;
    }

    /**
     * @see #setAllowPointSelect(Boolean)
     * @return
     */
    public boolean isAllowPointSelect() {
        return allowPointSelect == null ? false : allowPointSelect;
    }

    /**
     * Allow this series' points to be selected by clicking on the markers, bars
     * or pie slices. Defaults to false.
     * 
     * @param allowPointSelect
     */
    public void setAllowPointSelect(Boolean allowPointSelect) {
        this.allowPointSelect = allowPointSelect;
    }

    /**
     * @see #setShowInLegend(Boolean)
     * @return
     */
    public boolean isShowInLegend() {
        return showInLegend == null ? false : showInLegend;
    }

    /**
     * Whether to display this particular series or series type in the legend.
     * Defaults to false.
     * 
     * @param showInLegend
     */
    public void setShowInLegend(Boolean showInLegend) {
        this.showInLegend = showInLegend;
    }

    /**
     * @see #setMarker(Marker)
     * @return
     */
    public Marker getMarker() {
        return marker;
    }

    /**
     * Marker used for the plot point items (points/bars/columns)
     * 
     * @param marker
     */
    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    /**
     * Sticky tracking of mouse events. When true, the mouseOut event on a
     * series isn't triggered until the mouse moves over another series, or out
     * of the plot area. When false, the mouseOut event on a series is triggered
     * when the mouse leaves the area around the series' graph or markers. This
     * also implies the tooltip. When stickyTracking is false and tooltip.shared
     * is false, the tooltip will be hidden when moving the mouse between
     * series. Defaults to true.
     * 
     * @param stickyTracking
     */
    public void setStickyTracking(Boolean stickyTracking) {
        this.stickyTracking = stickyTracking;
    }

    /**
     * @see #setStickyTracking(Boolean)
     * @return
     */
    public Boolean isStickyTracking() {
        return stickyTracking;
    }
    
    /**
     * @see #setColor(Color)
     * 
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * The main color or the series. In line type series it applies to the line
     * and the point markers unless otherwise specified. In bar type series it
     * applies to the bars unless a color is specified per point. The default
     * value is pulled from the VaadinTheme's colors
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
     * A wrapper object for all the series options in specific states.
     * 
     * @param states
     */
    public void setStates(States states) {
        this.states = states;
    }
    
    /**
     * @see #setTooltip(Tooltip)
     * @return
     */
    public Tooltip getTooltip() {
        if (tooltip == null) {
            tooltip = new Tooltip();
        }
        return tooltip;
    }

    /**
     * A configuration object for the tooltip rendering of each single series.
     * Properties are inherited from tooltip. Overridable properties are
     * headerFormat, pointFormat, valueDecimals, xDateFormat, valuePrefix and
     * valueSuffix. . Defaults to {}.
     * 
     * @param tooltip
     */
    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

}
