package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * <p>
 * The Y axis or value axis. Normally this is the vertical axis, though if the
 * chart is inverted this is the horizontal axis. In case of multiple axes, the
 * yAxis node is an array of configuration objects.
 * </p>
 * <p>
 * See <a class="internal" href="#axis.object">the Axis object</a> for
 * programmatic access to the axis.
 * </p>
 */
public class YAxis extends Axis {

	private static final long serialVersionUID = 1L;
	private Boolean allowDecimals;
	private Color alternateGridColor;
	private Breaks[] breaks;
	private ArrayList<String> categories;
	private Number ceiling;
	private Crosshair crosshair;
	private DateTimeLabelFormats dateTimeLabelFormats;
	private Boolean endOnTick;
	private Number floor;
	private Color gridLineColor;
	private String gridLineDashStyle;
	private String gridLineInterpolation;
	private Number gridLineWidth;
	private Number gridZIndex;
	private String id;
	private Labels labels;
	private Color lineColor;
	private Number lineWidth;
	private Number linkedTo;
	private Color maxColor;
	private Number maxPadding;
	private Color minColor;
	private Number minPadding;
	private Number minRange;
	private Number minTickInterval;
	private Color minorGridLineColor;
	private String minorGridLineDashStyle;
	private Number minorGridLineWidth;
	private Color minorTickColor;
	private Object minorTickInterval;
	private Number minorTickLength;
	private String minorTickPosition;
	private Number minorTickWidth;
	private Number offset;
	private Boolean opposite;
	private ArrayList<PlotBand> plotBands;
	private ArrayList<PlotLine> plotLines;
	private Boolean reversed;
	private Boolean reversedStacks;
	private Boolean showEmpty;
	private Boolean showFirstLabel;
	private Boolean showLastLabel;
	private StackLabels stackLabels;
	private Number startOfWeek;
	private Boolean startOnTick;
	private Number tickAmount;
	private Color tickColor;
	private Number tickInterval;
	private Number tickLength;
	private Number tickPixelInterval;
	private String tickPosition;
	private Object tickPositioner;
	private Number[] tickPositions;
	private Number tickWidth;
	private String tickmarkPlacement;
	private AxisTitle title;
	private String type;
	private Object[] units;
	private Boolean visible;
	private Number pane;
	private ArrayList<Stop> stops;

	public YAxis() {
	}

	/**
	 * @see #setAllowDecimals(Boolean)
	 */
	public Boolean getAllowDecimals() {
		return allowDecimals;
	}

	/**
	 * Whether to allow decimals in this axis' ticks. When counting integers,
	 * like persons or hits on a web page, decimals must be avoided in the axis
	 * tick labels.
	 * <p>
	 * Defaults to: true
	 */
	public void setAllowDecimals(Boolean allowDecimals) {
		this.allowDecimals = allowDecimals;
	}

	/**
	 * @see #setAlternateGridColor(Color)
	 */
	public Color getAlternateGridColor() {
		return alternateGridColor;
	}

	/**
	 * When using an alternate grid color, a band is painted across the plot
	 * area between every other grid line.
	 */
	public void setAlternateGridColor(Color alternateGridColor) {
		this.alternateGridColor = alternateGridColor;
	}

	/**
	 * @see #setBreaks(Breaks[])
	 */
	public Breaks[] getBreaks() {
		return breaks;
	}

	/**
	 * An array defining breaks in the axis, the sections defined will be left
	 * out and all the points shifted closer to each other. Requires that the
	 * broken-axis.js module is loaded.
	 */
	public void setBreaks(Breaks[] breaks) {
		this.breaks = breaks;
	}

	public String[] getCategories() {
		String[] arr = new String[categories.size()];
		categories.toArray(arr);
		return arr;
	}

	public void setCategories(String... categories) {
		this.categories = new ArrayList<String>(Arrays.asList(categories));
	}

	public void addCategory(String category) {
		if (this.categories == null) {
			this.categories = new ArrayList<String>();
		}
		this.categories.add(category);
	}

	public void removeCategory(String category) {
		this.categories.remove(category);
	}

	/**
	 * @see #setCeiling(Number)
	 */
	public Number getCeiling() {
		return ceiling;
	}

	/**
	 * The highest allowed value for automatically computed axis extremes.
	 * <p>
	 * Defaults to:
	 */
	public void setCeiling(Number ceiling) {
		this.ceiling = ceiling;
	}

	/**
	 * @see #setCrosshair(Crosshair)
	 */
	public Crosshair getCrosshair() {
		return crosshair;
	}

	/**
	 * Configure a crosshair that follows either the mouse pointer or the
	 * hovered point.
	 * <p>
	 * Defaults to: false
	 */
	public void setCrosshair(Crosshair crosshair) {
		this.crosshair = crosshair;
	}

	/**
	 * @see #setDateTimeLabelFormats(DateTimeLabelFormats)
	 */
	public DateTimeLabelFormats getDateTimeLabelFormats() {
		return dateTimeLabelFormats;
	}

	/**
	 * For a datetime axis, the scale will automatically adjust to the
	 * appropriate unit. This member gives the default string representations
	 * used for each unit. For an overview of the replacement codes, see
	 * dateFormat. Defaults to:
	 * 
	 * <pre>
	 * {
	 * 		millisecond: '%H:%M:%S.%L',
	 * 		second: '%H:%M:%S',
	 * 		minute: '%H:%M',
	 * 		hour: '%H:%M',
	 * 		day: '%e. %b',
	 * 		week: '%e. %b',
	 * 		month: '%b \'%y',
	 * 		year: '%Y'
	 * 	}
	 * </pre>
	 */
	public void setDateTimeLabelFormats(
			DateTimeLabelFormats dateTimeLabelFormats) {
		this.dateTimeLabelFormats = dateTimeLabelFormats;
	}

	/**
	 * @see #setEndOnTick(Boolean)
	 */
	public Boolean getEndOnTick() {
		return endOnTick;
	}

	/**
	 * Whether to force the axis to end on a tick. Use this option with the
	 * <code>maxPadding</code> option to control the axis end.
	 * <p>
	 * Defaults to: true
	 */
	public void setEndOnTick(Boolean endOnTick) {
		this.endOnTick = endOnTick;
	}

	/**
	 * @see #setFloor(Number)
	 */
	public Number getFloor() {
		return floor;
	}

	/**
	 * The lowest allowed value for automatically computed axis extremes.
	 * <p>
	 * Defaults to: null
	 */
	public void setFloor(Number floor) {
		this.floor = floor;
	}

	/**
	 * @see #setGridLineColor(Color)
	 */
	public Color getGridLineColor() {
		return gridLineColor;
	}

	/**
	 * Color of the grid lines extending the ticks across the plot area.
	 * <p>
	 * Defaults to: #D8D8D8
	 */
	public void setGridLineColor(Color gridLineColor) {
		this.gridLineColor = gridLineColor;
	}

	/**
	 * @see #setGridLineDashStyle(String)
	 */
	public String getGridLineDashStyle() {
		return gridLineDashStyle;
	}

	/**
	 * The dash or dot style of the grid lines. For possible values, see <a
	 * href=
	 * "http://jsfiddle.net/gh/get/jquery/1.7.2/highslide-software/highcharts.com/tree/master/samples/highcharts/plotoptions/series-dashstyle-all/"
	 * >this demonstration</a>.
	 * <p>
	 * Defaults to: Solid
	 */
	public void setGridLineDashStyle(String gridLineDashStyle) {
		this.gridLineDashStyle = gridLineDashStyle;
	}

	/**
	 * @see #setGridLineInterpolation(String)
	 */
	public String getGridLineInterpolation() {
		return gridLineInterpolation;
	}

	/**
	 * Polar charts only. Whether the grid lines should draw as a polygon with
	 * straight lines between categories, or as circles. Can be either
	 * <code>circle</code> or <code>polygon</code>.
	 * <p>
	 * Defaults to: null
	 */
	public void setGridLineInterpolation(String gridLineInterpolation) {
		this.gridLineInterpolation = gridLineInterpolation;
	}

	/**
	 * @see #setGridLineWidth(Number)
	 */
	public Number getGridLineWidth() {
		return gridLineWidth;
	}

	/**
	 * The width of the grid lines extending the ticks across the plot area.
	 * <p>
	 * Defaults to: 1
	 */
	public void setGridLineWidth(Number gridLineWidth) {
		this.gridLineWidth = gridLineWidth;
	}

	/**
	 * @see #setGridZIndex(Number)
	 */
	public Number getGridZIndex() {
		return gridZIndex;
	}

	/**
	 * The Z index of the grid lines.
	 * <p>
	 * Defaults to: 1
	 */
	public void setGridZIndex(Number gridZIndex) {
		this.gridZIndex = gridZIndex;
	}

	/**
	 * @see #setId(String)
	 */
	public String getId() {
		return id;
	}

	/**
	 * An id for the axis. This can be used after render time to get a pointer
	 * to the axis object through <code>chart.get()</code>.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @see #setLabels(Labels)
	 */
	public Labels getLabels() {
		return labels;
	}

	/**
	 * 
	 */
	public void setLabels(Labels labels) {
		this.labels = labels;
	}

	/**
	 * @see #setLineColor(Color)
	 */
	public Color getLineColor() {
		return lineColor;
	}

	/**
	 * The color of the line marking the axis itself.
	 * <p>
	 * Defaults to: #C0D0E0
	 */
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	/**
	 * @see #setLineWidth(Number)
	 */
	public Number getLineWidth() {
		return lineWidth;
	}

	/**
	 * The width of the line marking the axis itself.
	 * <p>
	 * Defaults to: 0
	 */
	public void setLineWidth(Number lineWidth) {
		this.lineWidth = lineWidth;
	}

	/**
	 * @see #setLinkedTo(Number)
	 */
	public Number getLinkedTo() {
		return linkedTo;
	}

	/**
	 * Index of another axis that this axis is linked to. When an axis is linked
	 * to a master axis, it will take the same extremes as the master, but as
	 * assigned by min or max or by setExtremes. It can be used to show
	 * additional info, or to ease reading the chart by duplicating the scales.
	 */
	public void setLinkedTo(Number linkedTo) {
		this.linkedTo = linkedTo;
	}

	/**
	 * @see #setMaxColor(Color)
	 */
	public Color getMaxColor() {
		return maxColor;
	}

	/**
	 * Solid gauge only. Unless <a href="#yAxis.stops">stops</a> are set, the
	 * color to represent the maximum value of the Y axis.
	 * <p>
	 * Defaults to: #102D4C
	 */
	public void setMaxColor(Color maxColor) {
		this.maxColor = maxColor;
	}

	/**
	 * @see #setMaxPadding(Number)
	 */
	public Number getMaxPadding() {
		return maxPadding;
	}

	/**
	 * Padding of the max value relative to the length of the axis. A padding of
	 * 0.05 will make a 100px axis 5px longer. This is useful when you don't
	 * want the highest data value to appear on the edge of the plot area.
	 * <p>
	 * Defaults to: 0.05
	 */
	public void setMaxPadding(Number maxPadding) {
		this.maxPadding = maxPadding;
	}

	/**
	 * @see #setMinColor(Color)
	 */
	public Color getMinColor() {
		return minColor;
	}

	/**
	 * Solid gauge only. Unless <a href="#yAxis.stops">stops</a> are set, the
	 * color to represent the minimum value of the Y axis.
	 * <p>
	 * Defaults to: #EFEFFF
	 */
	public void setMinColor(Color minColor) {
		this.minColor = minColor;
	}

	/**
	 * @see #setMinPadding(Number)
	 */
	public Number getMinPadding() {
		return minPadding;
	}

	/**
	 * Padding of the min value relative to the length of the axis. A padding of
	 * 0.05 will make a 100px axis 5px longer. This is useful when you don't
	 * want the lowest data value to appear on the edge of the plot area.
	 * <p>
	 * Defaults to: 0.05
	 */
	public void setMinPadding(Number minPadding) {
		this.minPadding = minPadding;
	}

	/**
	 * @see #setMinRange(Number)
	 */
	public Number getMinRange() {
		return minRange;
	}

	/**
	 * <p>
	 * The minimum range to display on this axis. The entire axis will not be
	 * allowed to span over a smaller interval than this. For example, for a
	 * datetime axis the main unit is milliseconds. If minRange is set to
	 * 3600000, you can't zoom in more than to one hour.
	 * </p>
	 * 
	 * <p>
	 * The default minRange for the x axis is five times the smallest interval
	 * between any of the data points.
	 * </p>
	 * 
	 * <p>
	 * On a logarithmic axis, the unit for the minimum range is the power. So a
	 * minRange of 1 means that the axis can be zoomed to 10-100, 100-1000,
	 * 1000-10000 etc.
	 * </p>
	 * 
	 * <p>
	 * Note that the <code>minPadding</code>, <code>maxPadding</code>,
	 * <code>startOnTick</code> and <code>endOnTick</code> settings also affect
	 * how the extremes of the axis are computed.
	 * </p>
	 */
	public void setMinRange(Number minRange) {
		this.minRange = minRange;
	}

	/**
	 * @see #setMinTickInterval(Number)
	 */
	public Number getMinTickInterval() {
		return minTickInterval;
	}

	/**
	 * The minimum tick interval allowed in axis values. For example on zooming
	 * in on an axis with daily data, this can be used to prevent the axis from
	 * showing hours. Defaults to the closest distance between two points on the
	 * axis.
	 */
	public void setMinTickInterval(Number minTickInterval) {
		this.minTickInterval = minTickInterval;
	}

	/**
	 * @see #setMinorGridLineColor(Color)
	 */
	public Color getMinorGridLineColor() {
		return minorGridLineColor;
	}

	/**
	 * Color of the minor, secondary grid lines.
	 * <p>
	 * Defaults to: #E0E0E0
	 */
	public void setMinorGridLineColor(Color minorGridLineColor) {
		this.minorGridLineColor = minorGridLineColor;
	}

	/**
	 * @see #setMinorGridLineDashStyle(String)
	 */
	public String getMinorGridLineDashStyle() {
		return minorGridLineDashStyle;
	}

	/**
	 * The dash or dot style of the minor grid lines. For possible values, see
	 * <a href=
	 * "http://jsfiddle.net/gh/get/jquery/1.7.1/highslide-software/highcharts.com/tree/master/samples/highcharts/plotoptions/series-dashstyle-all/"
	 * >this demonstration</a>.
	 * <p>
	 * Defaults to: Solid
	 */
	public void setMinorGridLineDashStyle(String minorGridLineDashStyle) {
		this.minorGridLineDashStyle = minorGridLineDashStyle;
	}

	/**
	 * @see #setMinorGridLineWidth(Number)
	 */
	public Number getMinorGridLineWidth() {
		return minorGridLineWidth;
	}

	/**
	 * Width of the minor, secondary grid lines.
	 * <p>
	 * Defaults to: 1
	 */
	public void setMinorGridLineWidth(Number minorGridLineWidth) {
		this.minorGridLineWidth = minorGridLineWidth;
	}

	/**
	 * @see #setMinorTickColor(Color)
	 */
	public Color getMinorTickColor() {
		return minorTickColor;
	}

	/**
	 * Color for the minor tick marks.
	 * <p>
	 * Defaults to: #A0A0A0
	 */
	public void setMinorTickColor(Color minorTickColor) {
		this.minorTickColor = minorTickColor;
	}

	/**
	 * @see #setMinorTickInterval(Object)
	 */
	public Object getMinorTickInterval() {
		return minorTickInterval;
	}

	/**
	 * <p>
	 * Tick interval in scale units for the minor ticks. On a linear axis, if
	 * <code>"auto"</code>, the minor tick interval is calculated as a fifth of
	 * the tickInterval. If <code>null</code>, minor ticks are not shown.
	 * </p>
	 * <p>
	 * On logarithmic axes, the unit is the power of the value. For example,
	 * setting the minorTickInterval to 1 puts one tick on each of 0.1, 1, 10,
	 * 100 etc. Setting the minorTickInterval to 0.1 produces 9 ticks between 1
	 * and 10, 10 and 100 etc. A minorTickInterval of "auto" on a log axis
	 * results in a best guess, attempting to enter approximately 5 minor ticks
	 * between each major tick.
	 * </p>
	 * 
	 * <p>
	 * If user settings dictate minor ticks to become too dense, they don't make
	 * sense, and will be ignored to prevent performance problems.</a>
	 * 
	 * <p>
	 * On axes using <a href="#xAxis.categories">categories</a>, minor ticks are
	 * not supported.
	 * </p>
	 */
	public void setMinorTickInterval(Object minorTickInterval) {
		this.minorTickInterval = minorTickInterval;
	}

	/**
	 * @see #setMinorTickLength(Number)
	 */
	public Number getMinorTickLength() {
		return minorTickLength;
	}

	/**
	 * The pixel length of the minor tick marks.
	 * <p>
	 * Defaults to: 2
	 */
	public void setMinorTickLength(Number minorTickLength) {
		this.minorTickLength = minorTickLength;
	}

	/**
	 * @see #setMinorTickPosition(String)
	 */
	public String getMinorTickPosition() {
		return minorTickPosition;
	}

	/**
	 * The position of the minor tick marks relative to the axis line. Can be
	 * one of <code>inside</code> and <code>outside</code>.
	 * <p>
	 * Defaults to: outside
	 */
	public void setMinorTickPosition(String minorTickPosition) {
		this.minorTickPosition = minorTickPosition;
	}

	/**
	 * @see #setMinorTickWidth(Number)
	 */
	public Number getMinorTickWidth() {
		return minorTickWidth;
	}

	/**
	 * The pixel width of the minor tick mark.
	 * <p>
	 * Defaults to: 0
	 */
	public void setMinorTickWidth(Number minorTickWidth) {
		this.minorTickWidth = minorTickWidth;
	}

	/**
	 * @see #setOffset(Number)
	 */
	public Number getOffset() {
		return offset;
	}

	/**
	 * The distance in pixels from the plot area to the axis line. A positive
	 * offset moves the axis with it's line, labels and ticks away from the plot
	 * area. This is typically used when two or more axes are displayed on the
	 * same side of the plot.
	 * <p>
	 * Defaults to: 0
	 */
	public void setOffset(Number offset) {
		this.offset = offset;
	}

	/**
	 * @see #setOpposite(Boolean)
	 */
	public Boolean getOpposite() {
		return opposite;
	}

	/**
	 * Whether to display the axis on the opposite side of the normal. The
	 * normal is on the left side for vertical axes and bottom for horizontal,
	 * so the opposite sides will be right and top respectively. This is
	 * typically used with dual or multiple axes.
	 * <p>
	 * Defaults to: false
	 */
	public void setOpposite(Boolean opposite) {
		this.opposite = opposite;
	}

	public PlotBand[] getPlotBands() {
		PlotBand[] arr = new PlotBand[plotBands.size()];
		plotBands.toArray(arr);
		return arr;
	}

	public void setPlotBands(PlotBand... plotBands) {
		this.plotBands = new ArrayList<PlotBand>(Arrays.asList(plotBands));
	}

	public void addPlotBand(PlotBand plotBand) {
		if (this.plotBands == null) {
			this.plotBands = new ArrayList<PlotBand>();
		}
		this.plotBands.add(plotBand);
	}

	public void removePlotBand(PlotBand plotBand) {
		this.plotBands.remove(plotBand);
	}

	public PlotLine[] getPlotLines() {
		PlotLine[] arr = new PlotLine[plotLines.size()];
		plotLines.toArray(arr);
		return arr;
	}

	public void setPlotLines(PlotLine... plotLines) {
		this.plotLines = new ArrayList<PlotLine>(Arrays.asList(plotLines));
	}

	public void addPlotLine(PlotLine plotLine) {
		if (this.plotLines == null) {
			this.plotLines = new ArrayList<PlotLine>();
		}
		this.plotLines.add(plotLine);
	}

	public void removePlotLine(PlotLine plotLine) {
		this.plotLines.remove(plotLine);
	}

	/**
	 * @see #setReversed(Boolean)
	 */
	public Boolean getReversed() {
		return reversed;
	}

	/**
	 * Whether to reverse the axis so that the highest number is closest to the
	 * origin. If the chart is inverted, the x axis is reversed by default.
	 * <p>
	 * Defaults to: false
	 */
	public void setReversed(Boolean reversed) {
		this.reversed = reversed;
	}

	/**
	 * @see #setReversedStacks(Boolean)
	 */
	public Boolean getReversedStacks() {
		return reversedStacks;
	}

	/**
	 * If <code>true</code>, the first series in a stack will be drawn on top in
	 * a positive, non-reversed Y axis. If <code>false</code>, the first series
	 * is in the base of the stack.
	 * <p>
	 * Defaults to: true
	 */
	public void setReversedStacks(Boolean reversedStacks) {
		this.reversedStacks = reversedStacks;
	}

	/**
	 * @see #setShowEmpty(Boolean)
	 */
	public Boolean getShowEmpty() {
		return showEmpty;
	}

	/**
	 * Whether to show the axis line and title when the axis has no data.
	 * <p>
	 * Defaults to: true
	 */
	public void setShowEmpty(Boolean showEmpty) {
		this.showEmpty = showEmpty;
	}

	/**
	 * @see #setShowFirstLabel(Boolean)
	 */
	public Boolean getShowFirstLabel() {
		return showFirstLabel;
	}

	/**
	 * Whether to show the first tick label.
	 * <p>
	 * Defaults to: true
	 */
	public void setShowFirstLabel(Boolean showFirstLabel) {
		this.showFirstLabel = showFirstLabel;
	}

	/**
	 * @see #setShowLastLabel(Boolean)
	 */
	public Boolean getShowLastLabel() {
		return showLastLabel;
	}

	/**
	 * Whether to show the last tick label.
	 * <p>
	 * Defaults to: true
	 */
	public void setShowLastLabel(Boolean showLastLabel) {
		this.showLastLabel = showLastLabel;
	}

	/**
	 * @see #setStackLabels(StackLabels)
	 */
	public StackLabels getStackLabels() {
		return stackLabels;
	}

	/**
	 * The stack labels show the total value for each bar in a stacked column or
	 * bar chart. The label will be placed on top of positive columns and below
	 * negative columns. In case of an inverted column chart or a bar chart the
	 * label is placed to the right of positive bars and to the left of negative
	 * bars.
	 */
	public void setStackLabels(StackLabels stackLabels) {
		this.stackLabels = stackLabels;
	}

	/**
	 * @see #setStartOfWeek(Number)
	 */
	public Number getStartOfWeek() {
		return startOfWeek;
	}

	/**
	 * For datetime axes, this decides where to put the tick between weeks. 0 =
	 * Sunday, 1 = Monday.
	 * <p>
	 * Defaults to: 1
	 */
	public void setStartOfWeek(Number startOfWeek) {
		this.startOfWeek = startOfWeek;
	}

	/**
	 * @see #setStartOnTick(Boolean)
	 */
	public Boolean getStartOnTick() {
		return startOnTick;
	}

	/**
	 * Whether to force the axis to start on a tick. Use this option with the
	 * <code>maxPadding</code> option to control the axis start.
	 * <p>
	 * Defaults to: true
	 */
	public void setStartOnTick(Boolean startOnTick) {
		this.startOnTick = startOnTick;
	}

	/**
	 * @see #setTickAmount(Number)
	 */
	public Number getTickAmount() {
		return tickAmount;
	}

	/**
	 * <p>
	 * The amount of ticks to draw on the axis. This opens up for aligning the
	 * ticks of multiple charts or panes within a chart. This option overrides
	 * the <code>tickPixelInterval</code> option.
	 * </p>
	 * <p>
	 * This option only has an effect on linear axes. Datetime, logarithmic or
	 * category axes are not affected.
	 * </p>
	 */
	public void setTickAmount(Number tickAmount) {
		this.tickAmount = tickAmount;
	}

	/**
	 * @see #setTickColor(Color)
	 */
	public Color getTickColor() {
		return tickColor;
	}

	/**
	 * Color for the main tick marks.
	 * <p>
	 * Defaults to: #C0D0E0
	 */
	public void setTickColor(Color tickColor) {
		this.tickColor = tickColor;
	}

	/**
	 * @see #setTickInterval(Number)
	 */
	public Number getTickInterval() {
		return tickInterval;
	}

	/**
	 * <p>
	 * The interval of the tick marks in axis units. When <code>null</code>, the
	 * tick interval is computed to approximately follow the <a
	 * href="#xAxis.tickPixelInterval">tickPixelInterval</a> on linear and
	 * datetime axes. On categorized axes, a <code>null</code> tickInterval will
	 * default to 1, one category. Note that datetime axes are based on
	 * milliseconds, so for example an interval of one day is expressed as
	 * <code>24 * 3600 * 1000</code>.
	 * </p>
	 * <p>
	 * On logarithmic axes, the tickInterval is based on powers, so a
	 * tickInterval of 1 means one tick on each of 0.1, 1, 10, 100 etc. A
	 * tickInterval of 2 means a tick of 0.1, 10, 1000 etc. A tickInterval of
	 * 0.2 puts a tick on 0.1, 0.2, 0.4, 0.6, 0.8, 1, 2, 4, 6, 8, 10, 20, 40
	 * etc.
	 * </p>
	 * <p>
	 * If the tickInterval is too dense for labels to be drawn, Highcharts may
	 * remove ticks.
	 * </p>
	 */
	public void setTickInterval(Number tickInterval) {
		this.tickInterval = tickInterval;
	}

	/**
	 * @see #setTickLength(Number)
	 */
	public Number getTickLength() {
		return tickLength;
	}

	/**
	 * The pixel length of the main tick marks.
	 * <p>
	 * Defaults to: 10
	 */
	public void setTickLength(Number tickLength) {
		this.tickLength = tickLength;
	}

	/**
	 * @see #setTickPixelInterval(Number)
	 */
	public Number getTickPixelInterval() {
		return tickPixelInterval;
	}

	/**
	 * If tickInterval is <code>null</code> this option sets the approximate
	 * pixel interval of the tick marks. Not applicable to categorized axis.
	 * Defaults to <code>72</code> for the Y axis and <code>100</code> for the X
	 * axis.
	 */
	public void setTickPixelInterval(Number tickPixelInterval) {
		this.tickPixelInterval = tickPixelInterval;
	}

	/**
	 * @see #setTickPosition(String)
	 */
	public String getTickPosition() {
		return tickPosition;
	}

	/**
	 * The position of the major tick marks relative to the axis line. Can be
	 * one of <code>inside</code> and <code>outside</code>.
	 * <p>
	 * Defaults to: outside
	 */
	public void setTickPosition(String tickPosition) {
		this.tickPosition = tickPosition;
	}

	/**
	 * @see #setTickPositioner(Object)
	 */
	public Object getTickPositioner() {
		return tickPositioner;
	}

	/**
	 * A callback function returning array defining where the ticks are laid out
	 * on the axis. This overrides the default behaviour of <a
	 * href="#xAxis.tickPixelInterval">tickPixelInterval</a> and <a
	 * href="#xAxis.tickInterval">tickInterval</a>. The automatic tick positions
	 * are accessible through <code>this.tickPositions</code> and can be
	 * modified by the callback.
	 */
	public void setTickPositioner(Object tickPositioner) {
		this.tickPositioner = tickPositioner;
	}

	/**
	 * @see #setTickPositions(Number[])
	 */
	public Number[] getTickPositions() {
		return tickPositions;
	}

	/**
	 * An array defining where the ticks are laid out on the axis. This
	 * overrides the default behaviour of <a
	 * href="#xAxis.tickPixelInterval">tickPixelInterval</a> and <a
	 * href="#xAxis.tickInterval">tickInterval</a>.
	 */
	public void setTickPositions(Number[] tickPositions) {
		this.tickPositions = tickPositions;
	}

	/**
	 * @see #setTickWidth(Number)
	 */
	public Number getTickWidth() {
		return tickWidth;
	}

	/**
	 * The pixel width of the major tick marks.
	 * <p>
	 * Defaults to: 0
	 */
	public void setTickWidth(Number tickWidth) {
		this.tickWidth = tickWidth;
	}

	/**
	 * @see #setTickmarkPlacement(String)
	 */
	public String getTickmarkPlacement() {
		return tickmarkPlacement;
	}

	/**
	 * For categorized axes only. If <code>on</code> the tick mark is placed in
	 * the center of the category, if <code>between</code> the tick mark is
	 * placed between categories. The default is <code>between</code> if the
	 * <code>tickInterval</code> is 1, else <code>on</code>.
	 * <p>
	 * Defaults to: null
	 */
	public void setTickmarkPlacement(String tickmarkPlacement) {
		this.tickmarkPlacement = tickmarkPlacement;
	}

	/**
	 * @see #setTitle(AxisTitle)
	 */
	public AxisTitle getTitle() {
		return title;
	}

	/**
	 * 
	 */
	public void setTitle(AxisTitle title) {
		this.title = title;
	}

	/**
	 * @see #setType(String)
	 */
	public String getType() {
		return type;
	}

	/**
	 * The type of axis. Can be one of <code>"linear"</code>,
	 * <code>"logarithmic"</code>, <code>"datetime"</code> or
	 * <code>"category"</code>. In a datetime axis, the numbers are given in
	 * milliseconds, and tick marks are placed on appropriate values like full
	 * hours or days. In a category axis, the <a href="#series.data">point
	 * names</a> of the chart's series are used for categories, if not a <a
	 * href="#xAxis.categories">categories</a> array is defined.
	 * <p>
	 * Defaults to: linear
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @see #setUnits(Object[])
	 */
	public Object[] getUnits() {
		return units;
	}

	/**
	 * Datetime axis only. An array determining what time intervals the ticks
	 * are allowed to fall on. Each array item is an array where the first value
	 * is the time unit and the second value another array of allowed multiples.
	 * Defaults to:
	 * 
	 * <pre>
	 * units: [[
	 * 		'millisecond', // unit name
	 * 		[1, 2, 5, 10, 20, 25, 50, 100, 200, 500] // allowed multiples
	 * 	], [
	 * 		'second',
	 * 		[1, 2, 5, 10, 15, 30]
	 * 	], [
	 * 		'minute',
	 * 		[1, 2, 5, 10, 15, 30]
	 * 	], [
	 * 		'hour',
	 * 		[1, 2, 3, 4, 6, 8, 12]
	 * 	], [
	 * 		'day',
	 * 		[1]
	 * 	], [
	 * 		'week',
	 * 		[1]
	 * 	], [
	 * 		'month',
	 * 		[1, 3, 6]
	 * 	], [
	 * 		'year',
	 * 		null
	 * 	]]
	 * </pre>
	 * <p>
	 * Defaults to:
	 */
	public void setUnits(Object[] units) {
		this.units = units;
	}

	/**
	 * @see #setVisible(Boolean)
	 */
	public Boolean getVisible() {
		return visible;
	}

	/**
	 * Whether axis, including axis title, line, ticks and labels, should be
	 * visible.
	 * <p>
	 * Defaults to: true
	 */
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Number getPane() {
		return pane;
	}

	public void setPane(Number pane) {
		this.pane = pane;
	}

	public void setPane(Pane pane) {
		if (pane.getPaneIndex() == null) {
			throw new IllegalStateException(
					"Pane must be attached to configuration");
		}
		this.pane = pane.getPaneIndex();
	}

	public void setTitle(String title) {
		AxisTitle t = new AxisTitle();
		t.setText(title);
		this.setTitle(t);
	}

	public void setLinkedTo(YAxis axis) {
		linkedTo = axis.getAxisIndex();
	}

	public Stop[] getStops() {
		Stop[] arr = new Stop[stops.size()];
		stops.toArray(arr);
		return arr;
	}

	public void setStops(Stop... stops) {
		this.stops = new ArrayList<Stop>(Arrays.asList(stops));
	}

	public void addStop(Stop stop) {
		if (this.stops == null) {
			this.stops = new ArrayList<Stop>();
		}
		this.stops.add(stop);
	}

	public void removeStop(Stop stop) {
		this.stops.remove(stop);
	}
}