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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.addon.charts.model.style.Color;

/**
 * Abstract Axis class.
 */
public abstract class Axis extends AbstractConfigurationObject {

    private Number min;
    private Number max;

    private Labels labels;
    private String[] categories;

    private Title title;

    private TickmarkPlacement tickmarkPlacement;

    private List<PlotBand> plotBands;

    private AxisType type;
    private Boolean startOnTick;
    private Boolean showFirstLabel;
    private Number maxZoom;
    private Number minRange;

    private Boolean allowDecimals;
    private Boolean reversed;
    private Number maxPadding;
    private Boolean showLastLabel;
    private Number lineWidth;
    private Number gridLineWidth;
    private Number minorGridLineWidth;
    private Color alternateGridColor;
    private String minorGridColor;
    private DateTimeLabelFormats dateTimeLabelFormats;
    private Number tickInterval;
    private Object minorTickInterval;
    private Boolean opposite;
    private Number tickPixelInterval;
    private Number linkedTo;
    private Number minPadding;
    private Boolean showLastTickLabel;
    private Color minorTickColor;
    private Color tickColor;
    private Number tickLength;
    private Number tickWidth;
    private Number minorTickLength;
    private Number minorTickWidth;
    private TickPosition tickPosition;
    private TickPosition minorTickPosition;

    private Color lineColor;
    private Number offset;
    private Boolean endOnTick;

    /**
     * @see #setCategories(String...)
     */
    public String[] getCategories() {
        return categories;
    }

    /**
     * If categories are present for the Axis, names are used instead of numbers
     * for that axis. Example:
     * 
     * "Apples", "Bananas", "Oranges"
     * 
     * Defaults to null.
     * 
     * @param categories
     */
    public void setCategories(String... categories) {
        this.categories = categories;
    }

    /**
     * @see #setTitle(String)
     * @return Axis title or null if not defined
     */
    public Title getTitle() {
        return title;
    }

    /**
     * The axis title, which is shown next to it.
     * 
     * @param text
     *            Text of title
     */
    public void setTitle(String text) {
        setTitle(new Title(text));
    }

    /**
     * The axis title, which is shown next to it.
     * 
     * @param title
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * @see #setMin(Number)
     * @return the minimum value of the axis or null
     */
    public Number getMin() {
        return min;
    }

    /**
     * The minimum value of the axis. If null the min value is automatically
     * calculated. If the startOnTick option is true, the min value might be
     * rounded down. Defaults to null.
     * 
     * @param min
     */
    public void setMin(Number min) {
        this.min = min;
    }

    /**
     * @see #setMax(Number)
     * @return Maximum value of axis or null
     */
    public Number getMax() {
        return max;
    }

    /**
     * The maximum value of the axis. If null, the max value is automatically
     * calculated. If the endOnTick option is true, the max value might be
     * rounded up. The actual maximum value is also influenced by
     * chart.alignTicks. Defaults to null.
     * 
     * @param max
     */
    public void setMax(Number max) {
        this.max = max;
    }

    /**
     * @return The axis labels, showing the number or category for each tick.
     */
    public Labels getLabels() {
        if (labels == null) {
            labels = new Labels();
        }
        return labels;
    }

    /**
     * Sets the axis labels, showing the number or category for each tick.
     * 
     * @param labels
     */
    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    /**
     * @see #setTickmarkPlacement(TickmarkPlacement)
     */
    public TickmarkPlacement getTickmarkPlacement() {
        return tickmarkPlacement;
    }

    /**
     * For categorized axes only. If {@link TickmarkPlacement#ON} the tick mark
     * is placed in the center of the category, if
     * {@link TickmarkPlacement#BETWEEN} the tick mark is placed between
     * categories. Defaults to {@link TickmarkPlacement#BETWEEN}.
     * 
     * @param tickmarkPlacement
     */
    public void setTickmarkPlacement(TickmarkPlacement tickmarkPlacement) {
        this.tickmarkPlacement = tickmarkPlacement;
    }

    /**
     * @see #setPlotBands(PlotBand...)
     */
    public List<PlotBand> getPlotBands() {
        return plotBands;
    }

    /**
     * Sets the plot bands of the axis. A plot band is a colored band stretching
     * across the plot area marking an interval on the axis.
     * 
     * @param plotBands
     */
    public void setPlotBands(PlotBand... plotBands) {
        this.plotBands = new ArrayList(Arrays.asList(plotBands));
    }

    /**
     * Removes the given PlotBand if exists. Remember to call
     * Chart.drawChart(Configuration); after the update if you want to update
     * visible chart component.
     * 
     * @param plotBand
     */
    public void removePlotBand(PlotBand plotBand) {
        plotBands.remove(plotBand);
    }

    /**
     * Sets the type of this axis. Can be one of {@link AxisType#LINEAR},
     * {@link AxisType#LOGARITHMIC}, or {@link AxisType#DATETIME}. In a
     * {@link AxisType#DATETIME} axis, the numbers are given in milliseconds,
     * and tick marks are placed on appropriate values like full hours or days.
     * 
     * @param type
     */
    public void setType(AxisType type) {
        this.type = type;
    }

    /**
     * @see #setType(AxisType)
     * @return Type of axis or null if not defined
     */
    public AxisType getType() {
        return type;
    }

    /**
     * Sets whether to force the axis to start on a tick. Use this option with
     * the maxPadding option to control the axis start. Defaults to false.
     * 
     * @param startOnTick
     */
    public void setStartOnTick(Boolean startOnTick) {
        this.startOnTick = startOnTick;
    }

    /**
     * @see #setStartOnTick(Boolean)
     */
    public boolean isStartOnTick() {
        return startOnTick == null ? false : startOnTick;
    }

    /**
     * Sets whether to show the first tick label. Defaults to true.
     * 
     * @param showFirstLabel
     */
    public void setShowFirstLabel(Boolean showFirstLabel) {
        this.showFirstLabel = showFirstLabel;
    }

    /**
     * @see #setShowFirstLabel(Boolean)
     */
    public boolean isShowFirstLabel() {
        return showFirstLabel == null ? true : showFirstLabel;
    }

    /**
     * The minimum range to display on this axis. The entire axis will not be
     * allowed to span over a smaller interval than this. For example, for a
     * DATETIME axis the main unit is milliseconds. If minRange is set to
     * 3600000, you can't zoom in more than to one hour.
     * 
     * The default minRange for the x axis is five times the smallest interval
     * between any of the data points.
     * 
     * On a logarithmic axis, the unit for the minimum range is the power. So a
     * minRange of 1 means that the axis can be zoomed to 10-100, 100-1000,
     * 1000-10000 etc.
     * 
     * @param minRange
     */
    public void setMinRange(Number minRange) {
        this.minRange = minRange;
    }

    /**
     * @see #setMinRange(Number)
     */
    public Number getMinRange() {
        return minRange;
    }

    /**
     * @see #setAllowDecimals(Boolean)
     */
    public boolean isAllowDecimals() {
        return allowDecimals == null ? true : allowDecimals;
    }

    /**
     * Sets whether to allow decimals in this axis' ticks. When counting
     * integers, like persons or hits on a web page, decimals must be avoided in
     * the axis tick labels. Defaults to true.
     * 
     * @param allowDecimals
     */
    public void setAllowDecimals(Boolean allowDecimals) {
        this.allowDecimals = allowDecimals;
    }

    /**
     * Sets whether to reverse the axis so that the highest number is closest to
     * the origin. If the chart is inverted, the x axis is reversed by default.
     * Defaults to false.
     * 
     * @param reversed
     */
    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }

    /**
     * @see #setReversed(Boolean)
     */
    public boolean isReversed() {
        return reversed == null ? false : reversed;
    }

    /**
     * Sets the padding of the max value relative to the length of the axis. A
     * padding of 0.05 will make a 100px axis 5px longer. This is useful when
     * you don't want the highest data value to appear on the edge of the plot
     * area. When the max option is set or a max extreme is set using
     * axis.setExtremes(), the maxPadding setting will be ignored. Defaults to
     * 0.01.
     */
    public void setMaxPadding(Number maxPadding) {
        this.maxPadding = maxPadding;
    }

    /**
     * @see #setMaxPadding(Number)
     */
    public Number getMaxPadding() {
        return maxPadding;
    }

    /**
     * Sets whether to show the last tick label. Defaults to false.
     * 
     * @param showLastLabel
     */
    public void setShowLastLabel(Boolean showLastLabel) {
        this.showLastLabel = showLastLabel;
    }

    /**
     * @see #setShowLastLabel(Boolean)
     */
    public boolean isShowLastLabel() {
        return showLastLabel == null ? false : showLastLabel;
    }

    /**
     * Sets the width of the line marking the axis itself. Defaults to 1.
     * 
     * @param lineWidth
     */
    public void setLineWidth(Number lineWidth) {
        this.lineWidth = lineWidth;
    }

    /**
     * @see #setLineWidth(Number)
     */
    public Number getLineWidth() {
        return lineWidth;
    }

    /**
     * Sets the width of the minor, secondary grid lines. Defaults to 1.
     * 
     * @param minorGridLineWidth
     */
    public void setMinorGridLineWidth(Number minorGridLineWidth) {
        this.minorGridLineWidth = minorGridLineWidth;
    }

    /**
     * @see #setMinorGridLineWidth(Number)
     */
    public Number getMinorGridLineWidth() {
        return minorGridLineWidth;
    }

    /**
     * Sets the width of the grid lines extending the ticks across the plot
     * area. Defaults to 0.
     * 
     * @param gridLineWidth
     */
    public void setGridLineWidth(Number gridLineWidth) {
        this.gridLineWidth = gridLineWidth;
    }

    /**
     * @see #setGridLineWidth(Number)
     */
    public Number getGridLineWidth() {
        return gridLineWidth;
    }

    /**
     * When using an alternate grid color, a band is painted across the plot
     * area between every other grid line. Defaults to null.
     * 
     * @param alternateGridColor
     */
    public void setAlternateGridColor(Color alternateGridColor) {
        this.alternateGridColor = alternateGridColor;
    }

    /**
     * @see #setAlternateGridColor(String)
     */
    public Color getAlternateGridColor() {
        return alternateGridColor;
    }

    /**
     * @see #setMinorGridColor(String)
     * 
     * @return Color or minor grid line, or null if not defined
     */
    public String getMinorGridColor() {
        return minorGridColor;
    }

    /**
     * Sets the color of the minor, secondary grid lines.
     * 
     * @param minorGridColor
     *            Color
     */
    public void setMinorGridColor(String minorGridColor) {
        this.minorGridColor = minorGridColor;
    }

    /**
     * @see #setOpposite(Boolean)
     * @return Whether the axis is shown on the opposite side of the normal.
     *         null if not defined.
     */
    public boolean isOpposite() {
        return opposite == null ? false : opposite;
    }

    /**
     * Sets whether to display the axis on the opposite side of the normal. The
     * normal is on the left side for vertical axes and bottom for horizontal,
     * so the opposite sides will be right and top respectively. This is
     * typically used with dual or multiple axes. Defaults to false.
     * 
     * @param opposite
     */
    public void setOpposite(Boolean opposite) {
        this.opposite = opposite;
    }

    /**
     * For a DATETIME axis, the scale will automatically adjust to the
     * appropriate unit. This member gives the default string representations
     * used for each unit. For an overview of the replacement codes, see
     * DateFormat. Defaults to:
     * 
     * { second: '%H:%M:%S', minute: '%H:%M', hour: '%H:%M', day: '%e. %b',
     * week: '%e. %b', month: '%b \'%y', year: '%Y' }
     * 
     * @param dateTimeLabelFormats
     */
    public void setDateTimeLabelFormats(
            DateTimeLabelFormats dateTimeLabelFormats) {
        this.dateTimeLabelFormats = dateTimeLabelFormats;
    }

    /**
     * @see #setDateTimeLabelFormats(String)
     */
    public DateTimeLabelFormats getDateTimeLabelFormats() {
        return dateTimeLabelFormats;
    }

    /**
     * Sets the interval of the tick marks in the unit of the axis. When null,
     * the tick interval is computed to approximately follow the
     * tickPixelInterval on LINEAR and DATETIME axes. On categorized axes, a
     * null tickInterval will default to 1, one category. Note that DATETIME
     * axes are based on milliseconds, so for example an interval of one day is
     * expressed as 24 * 3600 * 1000.
     * 
     * On logarithmic axes, the tickInterval is based on powers, so a
     * tickInterval of 1 means one tick on each of 0.1, 1, 10, 100 etc. A
     * tickInterval of 2 means a tick of 0.1, 10, 1000 etc. A tickInterval of
     * 0.2 puts a tick on 0.1, 0.2, 0.4, 0.6, 0.8, 1, 2, 4, 6, 8, 10, 20, 40
     * etc.
     */
    public void setTickInterval(Number tickInterval) {
        this.tickInterval = tickInterval;
    }

    /**
     * @see #setTickInterval(Number)
     */
    public Number getTickInterval() {
        return tickInterval;
    }

    /**
     * Sets the tick interval in scale units for the minor ticks. If null, minor
     * ticks are not shown.
     * 
     * On logarithmic axes, the unit is the power of the value. For example,
     * setting the minorTickInterval to 1 puts one tick on each of 0.1, 1, 10,
     * 100 etc. Setting the minorTickInterval to 0.1 produces 9 ticks between 1
     * and 10, 10 and 100 etc. Defaults to null.
     * 
     * @see #setMinorTickInterval(String)
     * 
     * @param minorTickInterval
     */
    public void setMinorTickInterval(Number minorTickInterval) {
        this.minorTickInterval = minorTickInterval;
    }

    /**
     * @see #setMinorTickInterval(Number)
     */
    public Object getMinorTickInterval() {
        return minorTickInterval;
    }

    /**
     * If tickInterval is null this option sets the approximate pixel interval
     * of the tick marks. Not applicable to categorized axis. Defaults to 72 for
     * the Y axis and 100 for the X axis.
     * 
     * @param tickPixelInterval
     */
    public void setTickPixelInterval(Number tickPixelInterval) {
        this.tickPixelInterval = tickPixelInterval;
    }

    /**
     * @see #setTickPixelInterval(Number)
     */
    public Number getTickPixelInterval() {
        return tickPixelInterval;
    }

    /**
     * @see #setLinkedTo(Number)
     * @return The index of another axis that this axis is linked to or null if
     *         undefined.
     */
    public Number getLinkedTo() {
        return linkedTo;
    }

    /**
     * Index of another axis that this axis is linked to. When an axis is linked
     * to a master axis, it will take the same extremes as the master, but as
     * assigned by {@link #setMin(Number)} or {@link #setMax(Number)} or by
     * {@link #setExtremes(Number, Number)}. It can be used to show additional
     * info, or to ease reading the chart by duplicating the scales. Defaults to
     * null.
     * 
     * @param linkedTo
     */
    public void setLinkedTo(Number linkedTo) {
        this.linkedTo = linkedTo;
    }

    /**
     * Sets the padding of the min value relative to the length of the axis. A
     * padding of 0.05 will make a 100px axis 5px longer. This is useful when
     * you don't want the lowest data value to appear on the edge of the plot
     * area. Defaults to 0.05.
     * 
     * @param minPadding
     */
    public void setMinPadding(Number minPadding) {
        this.minPadding = minPadding;
    }

    /**
     * @see #setMinPadding(Number)
     */
    public Number getMinPadding() {
        return minPadding;
    }

    /**
     * Sets whether to show the last tick label. Defaults to false.
     * 
     * @param showLastTickLabel
     */
    public void setShowLastTickLabel(Boolean showLastTickLabel) {
        this.showLastTickLabel = showLastTickLabel;
    }

    /**
     * @see #setShowLastTickLabel(Boolean)
     */
    public boolean isShowLastTickLabel() {
        return showLastTickLabel == null ? false : showLastTickLabel;
    }

    /**
     * Sets the minimum and maximum of the axes after rendering has finished. If
     * the startOnTick and endOnTick options are true, the minimum and maximum
     * values are rounded off to the nearest tick. To prevent this, these
     * options can be set to false before calling setExtremes.
     * 
     * @param min
     *            The new minimum value
     * @param max
     *            The new maximum value
     */
    public void setExtremes(Number min, Number max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Sets the pixel width of the minor tick mark. Defaults to 0.
     * 
     * @param minorTickWidth
     */
    public void setMinorTickWidth(Number minorTickWidth) {
        this.minorTickWidth = minorTickWidth;
    }

    /**
     * Sets the pixel length of the minor tick marks. Defaults to 2.
     * 
     * @param minorTickLength
     */
    public void setMinorTickLength(Number minorTickLength) {
        this.minorTickLength = minorTickLength;
    }

    /**
     * Sets the pixel width of the major tick marks. Defaults to 1.
     * 
     * @param tickWidth
     */
    public void setTickWidth(Number tickWidth) {
        this.tickWidth = tickWidth;
    }

    /**
     * Sets the pixel length of the main tick marks. Defaults to 5.
     * 
     * @param tickLength
     */
    public void setTickLength(Number tickLength) {
        this.tickLength = tickLength;
    }

    /**
     * Sets the color for the main tick marks. Defaults to #C0D0E0.
     * 
     * @param tickColor
     */
    public void setTickColor(Color tickColor) {
        this.tickColor = tickColor;
    }

    /**
     * Sets the color for the minor tick marks. Defaults to #A0A0A0.
     * 
     * @param minorTickColor
     */
    public void setMinorTickColor(Color minorTickColor) {
        this.minorTickColor = minorTickColor;
    }

    /**
     * @see #setMinorTickColor(Color)
     */
    public Color getMinorTickColor() {
        return minorTickColor;
    }

    /**
     * @see #setTickColor(Color)
     */
    public Color getTickColor() {
        return tickColor;
    }

    /**
     * @see #setTickLength(int)
     */
    public Number getTickLength() {
        return tickLength;
    }

    /**
     * @see #setTickWidth(Number)
     */
    public Number getTickWidth() {
        return tickWidth;
    }

    /**
     * @see #setMinorTickLength(Number)
     */
    public Number getMinorTickLength() {
        return minorTickLength;
    }

    /**
     * @see #setMinorTickWidth(Number)
     */
    public Number getMinorTickWidth() {
        return minorTickWidth;
    }

    /**
     * Sets the position of the major tick marks relative to the axis line. Can
     * be either INSIDE or OUTSIDE. Defaults to OUTSIDE.
     * 
     * @param tickPosition
     */
    public void setTickPosition(TickPosition tickPosition) {
        this.tickPosition = tickPosition;
    }

    /**
     * @see #setTickPosition(TickPosition)
     */
    public TickPosition getTickPosition() {
        return tickPosition;
    }

    /**
     * Sets the position of the minor tick marks relative to the axis line. Can
     * be either INSIDE or OUTSIDE. Defaults to OUTSIDE.
     * 
     * @param minorTickPosition
     */
    public void setMinorTickPosition(TickPosition minorTickPosition) {
        this.minorTickPosition = minorTickPosition;
    }

    /**
     * @see #setMinorTickPosition(TickPosition)
     */
    public TickPosition getMinorTickPosition() {
        return minorTickPosition;
    }

    /**
     * Sets the tick interval in scale units for the minor ticks. On a linear
     * axis, if "auto", the minor tick interval is calculated as a fifth of the
     * tickInterval. If null, minor ticks are not shown.
     * 
     * On logarithmic axes, the unit is the power of the value. For example,
     * setting the minorTickInterval to 1 puts one tick on each of 0.1, 1, 10,
     * 100 etc. Setting the minorTickInterval to 0.1 produces 9 ticks between 1
     * and 10, 10 and 100 etc. A minorTickInterval of "auto" on a log axis
     * results in a best guess, attempting to enter approximately 5 minor ticks
     * between each major tick. . Defaults to null. <br />
     * <br />
     * This method is used to set textual interval like "auto"
     * 
     * @see #setMinorTickInterval(Number)
     * 
     * @param minorTickInterval
     */
    public void setMinorTickInterval(String minorTickInterval) {
        this.minorTickInterval = minorTickInterval;
    }

    /**
     * @see #setLineColor(Color)
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * Sets the color of the line marking the axis itself. Defaults to
     * "#C0D0E0".
     * 
     * @param lineColor
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * @see #setOffset(Number)
     */
    public Number getOffset() {
        return offset;
    }

    /**
     * Sets the distance in pixels from the plot area to the axis line. A
     * positive offset moves the axis with its line, labels and ticks away from
     * the plot area. This is typically used when two or more axes are displayed
     * on the same side of the plot. Defaults to 0.
     * 
     * @param offset
     */
    public void setOffset(Number offset) {
        this.offset = offset;
    }

    /**
     * @see #setEndOnTick(Boolean)
     */
    public boolean isEndOnTick() {
        return endOnTick == null ? false : endOnTick;
    }

    /**
     * Sets whether to force the axis to end on a tick. Use this option with the
     * maxPadding option to control the axis end. Defaults to false.
     * 
     * @param endOnTick
     */
    public void setEndOnTick(Boolean endOnTick) {
        this.endOnTick = endOnTick;
    }

}
