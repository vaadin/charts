package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
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

import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.style.Color;

/**
 * Plot options are used to customize the representation of the chart. Many
 * properties can be configured, including colors, fonts, tooltips, etc. Some of
 * these properties are applicable only to certain chart types, so be sure to
 * use the relevant subclass.
 * <p>
 * These configuration properties can be set in multiple places. Initial
 * defaults for plot options are set by the framework and can be customized with
 * {@link ChartOptions}. The most common method to use plot options is to call
 * {@link Configuration#setPlotOptions(AbstractPlotOptions)}. Values defined
 * here will override defaults from the theme. Options that are not defined at
 * this level will be inherited from the theme defaults. If the chart is a
 * combined chart, plot options for different types can be defined.
 * <p>
 * The third place where plot options can be used is at the series level. If
 * chart has several series of data, configurations defined in the theme and at
 * the component level can be fine tuned with
 * {@link AbstractSeries#setPlotOptions(AbstractPlotOptions)}. The same method
 * is also used to define the chart type for the series in combined charts.
 * <p>
 * {@link PlotOptionsSeries} is a special plot options type that can be used to
 * define default options shared by all chart types.
 */
public abstract class AbstractPlotOptions extends AbstractConfigurationObject {

    private Labels dataLabels;
    private Number zIndex;
    private Boolean showInLegend;
    private Boolean enableMouseTracking;
    private Boolean stickyTracking;
    private Cursor cursor;
    private Object animation;
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
     * @return Z-index or null if not defined
     */
    public Number getzIndex() {
        return zIndex;
    }

    /**
     * Define the Z-index of the series. Defaults to null.
     * 
     * @param zIndex
     */
    public void setzIndex(Number zIndex) {
        this.zIndex = zIndex;
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
     * Sets the whether to use animation for initial plotting.
     * 
     * @param animation
     */
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    /**
     * Checks if animation is set as a Boolean and if so, returns that setting.
     * Otherwise returns <code>null</code>.
     * 
     * @see #setAnimation(Boolean)
     * @see #setAnimation(Animation)
     * @return <code>null</code> when the animation is not set as Boolean,
     *         otherwise a corresponding Boolean.
     */
    public Boolean isAnimation() {
        return animation instanceof Boolean ? (Boolean) animation : null;
    }

    /**
     * Returns current animation settings. Can be a Boolean (
     * {@link #isAnimation()}), or an {@link Animation} instance.
     * 
     * @see #setAnimation(Boolean)
     * @see #setAnimation(Animation)
     * @return Current animation setting.
     */
    public Object getAnimation() {
        return animation;
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
