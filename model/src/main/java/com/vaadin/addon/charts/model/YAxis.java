package com.vaadin.addon.charts.model;

import java.util.ArrayList;
import java.util.Arrays;
public class YAxis extends Axis {

	private static final long serialVersionUID = 1L;
	private Boolean allowDecimals;
	private Object alternateGridColor;
	private Breaks[] breaks;
	private ArrayList<String> categories;
	private Number ceiling;
	private Crosshair crosshair;
	private Object dateTimeLabelFormats;
	private Boolean endOnTick;
	private Number floor;
	private Object gridLineColor;
	private String gridLineDashStyle;
	private String gridLineInterpolation;
	private Number gridLineWidth;
	private Number gridZIndex;
	private String id;
	private Labels labels;
	private Object lineColor;
	private Number lineWidth;
	private Number linkedTo;
	private Object maxColor;
	private Number maxPadding;
	private Object minColor;
	private Number minPadding;
	private Number minRange;
	private Number minTickInterval;
	private Object minorGridLineColor;
	private String minorGridLineDashStyle;
	private Number minorGridLineWidth;
	private Object minorTickColor;
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
	private Object[][] stops;
	private Number tickAmount;
	private Object tickColor;
	private Number tickInterval;
	private Number tickLength;
	private Number tickPixelInterval;
	private String tickPosition;
	private Object tickPositioner;
	private Number[] tickPositions;
	private Number tickWidth;
	private String tickmarkPlacement;
	private Title title;
	private String type;
	private Object[] units;
	private Boolean visible;
	private Number pane;

	public YAxis() {
	}

	public Boolean getAllowDecimals() {
		return allowDecimals;
	}

	public void setAllowDecimals(Boolean allowDecimals) {
		this.allowDecimals = allowDecimals;
	}

	public Object getAlternateGridColor() {
		return alternateGridColor;
	}

	public void setAlternateGridColor(Object alternateGridColor) {
		this.alternateGridColor = alternateGridColor;
	}

	public Breaks[] getBreaks() {
		return breaks;
	}

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

	public Number getCeiling() {
		return ceiling;
	}

	public void setCeiling(Number ceiling) {
		this.ceiling = ceiling;
	}

	public Crosshair getCrosshair() {
		return crosshair;
	}

	public void setCrosshair(Crosshair crosshair) {
		this.crosshair = crosshair;
	}

	public Object getDateTimeLabelFormats() {
		return dateTimeLabelFormats;
	}

	public void setDateTimeLabelFormats(Object dateTimeLabelFormats) {
		this.dateTimeLabelFormats = dateTimeLabelFormats;
	}

	public Boolean getEndOnTick() {
		return endOnTick;
	}

	public void setEndOnTick(Boolean endOnTick) {
		this.endOnTick = endOnTick;
	}

	public Number getFloor() {
		return floor;
	}

	public void setFloor(Number floor) {
		this.floor = floor;
	}

	public Object getGridLineColor() {
		return gridLineColor;
	}

	public void setGridLineColor(Object gridLineColor) {
		this.gridLineColor = gridLineColor;
	}

	public String getGridLineDashStyle() {
		return gridLineDashStyle;
	}

	public void setGridLineDashStyle(String gridLineDashStyle) {
		this.gridLineDashStyle = gridLineDashStyle;
	}

	public String getGridLineInterpolation() {
		return gridLineInterpolation;
	}

	public void setGridLineInterpolation(String gridLineInterpolation) {
		this.gridLineInterpolation = gridLineInterpolation;
	}

	public Number getGridLineWidth() {
		return gridLineWidth;
	}

	public void setGridLineWidth(Number gridLineWidth) {
		this.gridLineWidth = gridLineWidth;
	}

	public Number getGridZIndex() {
		return gridZIndex;
	}

	public void setGridZIndex(Number gridZIndex) {
		this.gridZIndex = gridZIndex;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Labels getLabels() {
		return labels;
	}

	public void setLabels(Labels labels) {
		this.labels = labels;
	}

	public Object getLineColor() {
		return lineColor;
	}

	public void setLineColor(Object lineColor) {
		this.lineColor = lineColor;
	}

	public Number getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(Number lineWidth) {
		this.lineWidth = lineWidth;
	}

	public Number getLinkedTo() {
		return linkedTo;
	}

	public void setLinkedTo(Number linkedTo) {
		this.linkedTo = linkedTo;
	}

	public Object getMaxColor() {
		return maxColor;
	}

	public void setMaxColor(Object maxColor) {
		this.maxColor = maxColor;
	}

	public Number getMaxPadding() {
		return maxPadding;
	}

	public void setMaxPadding(Number maxPadding) {
		this.maxPadding = maxPadding;
	}

	public Object getMinColor() {
		return minColor;
	}

	public void setMinColor(Object minColor) {
		this.minColor = minColor;
	}

	public Number getMinPadding() {
		return minPadding;
	}

	public void setMinPadding(Number minPadding) {
		this.minPadding = minPadding;
	}

	public Number getMinRange() {
		return minRange;
	}

	public void setMinRange(Number minRange) {
		this.minRange = minRange;
	}

	public Number getMinTickInterval() {
		return minTickInterval;
	}

	public void setMinTickInterval(Number minTickInterval) {
		this.minTickInterval = minTickInterval;
	}

	public Object getMinorGridLineColor() {
		return minorGridLineColor;
	}

	public void setMinorGridLineColor(Object minorGridLineColor) {
		this.minorGridLineColor = minorGridLineColor;
	}

	public String getMinorGridLineDashStyle() {
		return minorGridLineDashStyle;
	}

	public void setMinorGridLineDashStyle(String minorGridLineDashStyle) {
		this.minorGridLineDashStyle = minorGridLineDashStyle;
	}

	public Number getMinorGridLineWidth() {
		return minorGridLineWidth;
	}

	public void setMinorGridLineWidth(Number minorGridLineWidth) {
		this.minorGridLineWidth = minorGridLineWidth;
	}

	public Object getMinorTickColor() {
		return minorTickColor;
	}

	public void setMinorTickColor(Object minorTickColor) {
		this.minorTickColor = minorTickColor;
	}

	public Object getMinorTickInterval() {
		return minorTickInterval;
	}

	public void setMinorTickInterval(Object minorTickInterval) {
		this.minorTickInterval = minorTickInterval;
	}

	public Number getMinorTickLength() {
		return minorTickLength;
	}

	public void setMinorTickLength(Number minorTickLength) {
		this.minorTickLength = minorTickLength;
	}

	public String getMinorTickPosition() {
		return minorTickPosition;
	}

	public void setMinorTickPosition(String minorTickPosition) {
		this.minorTickPosition = minorTickPosition;
	}

	public Number getMinorTickWidth() {
		return minorTickWidth;
	}

	public void setMinorTickWidth(Number minorTickWidth) {
		this.minorTickWidth = minorTickWidth;
	}

	public Number getOffset() {
		return offset;
	}

	public void setOffset(Number offset) {
		this.offset = offset;
	}

	public Boolean getOpposite() {
		return opposite;
	}

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

	public Boolean getReversed() {
		return reversed;
	}

	public void setReversed(Boolean reversed) {
		this.reversed = reversed;
	}

	public Boolean getReversedStacks() {
		return reversedStacks;
	}

	public void setReversedStacks(Boolean reversedStacks) {
		this.reversedStacks = reversedStacks;
	}

	public Boolean getShowEmpty() {
		return showEmpty;
	}

	public void setShowEmpty(Boolean showEmpty) {
		this.showEmpty = showEmpty;
	}

	public Boolean getShowFirstLabel() {
		return showFirstLabel;
	}

	public void setShowFirstLabel(Boolean showFirstLabel) {
		this.showFirstLabel = showFirstLabel;
	}

	public Boolean getShowLastLabel() {
		return showLastLabel;
	}

	public void setShowLastLabel(Boolean showLastLabel) {
		this.showLastLabel = showLastLabel;
	}

	public StackLabels getStackLabels() {
		return stackLabels;
	}

	public void setStackLabels(StackLabels stackLabels) {
		this.stackLabels = stackLabels;
	}

	public Number getStartOfWeek() {
		return startOfWeek;
	}

	public void setStartOfWeek(Number startOfWeek) {
		this.startOfWeek = startOfWeek;
	}

	public Boolean getStartOnTick() {
		return startOnTick;
	}

	public void setStartOnTick(Boolean startOnTick) {
		this.startOnTick = startOnTick;
	}

	public Object[][] getStops() {
		return stops;
	}

	public void setStops(Object[][] stops) {
		this.stops = stops;
	}

	public Number getTickAmount() {
		return tickAmount;
	}

	public void setTickAmount(Number tickAmount) {
		this.tickAmount = tickAmount;
	}

	public Object getTickColor() {
		return tickColor;
	}

	public void setTickColor(Object tickColor) {
		this.tickColor = tickColor;
	}

	public Number getTickInterval() {
		return tickInterval;
	}

	public void setTickInterval(Number tickInterval) {
		this.tickInterval = tickInterval;
	}

	public Number getTickLength() {
		return tickLength;
	}

	public void setTickLength(Number tickLength) {
		this.tickLength = tickLength;
	}

	public Number getTickPixelInterval() {
		return tickPixelInterval;
	}

	public void setTickPixelInterval(Number tickPixelInterval) {
		this.tickPixelInterval = tickPixelInterval;
	}

	public String getTickPosition() {
		return tickPosition;
	}

	public void setTickPosition(String tickPosition) {
		this.tickPosition = tickPosition;
	}

	public Object getTickPositioner() {
		return tickPositioner;
	}

	public void setTickPositioner(Object tickPositioner) {
		this.tickPositioner = tickPositioner;
	}

	public Number[] getTickPositions() {
		return tickPositions;
	}

	public void setTickPositions(Number[] tickPositions) {
		this.tickPositions = tickPositions;
	}

	public Number getTickWidth() {
		return tickWidth;
	}

	public void setTickWidth(Number tickWidth) {
		this.tickWidth = tickWidth;
	}

	public String getTickmarkPlacement() {
		return tickmarkPlacement;
	}

	public void setTickmarkPlacement(String tickmarkPlacement) {
		this.tickmarkPlacement = tickmarkPlacement;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object[] getUnits() {
		return units;
	}

	public void setUnits(Object[] units) {
		this.units = units;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Number getPane() {
		return pane;
	}

	public void setPane(Number pane) {
		this.pane = pane;
	}

	public void setTitle(String title) {
		Title t = new Title();
		t.setText(title);
		this.setTitle(t);
	}

	public void setLinkedTo(YAxis axis) {
		linkedTo = axis.getAxisIndex();
	}
}