package com.vaadin.addon.charts.model;

import java.util.ArrayList;
import java.util.Arrays;
public class PlotOptionsBoxplot extends AbstractPlotOptions {

	private static final long serialVersionUID = 1L;
	private Boolean allowPointSelect;
	private Object color;
	private Boolean colorByPoint;
	private Object colors;
	private String cursor;
	private Number depth;
	private Object edgeColor;
	private Number edgeWidth;
	private Boolean enableMouseTracking;
	private Object fillColor;
	private Boolean getExtremesFromAll;
	private Number groupPadding;
	private Number groupZPadding;
	private Boolean grouping;
	private ArrayList<String> keys;
	private Number lineWidth;
	private String linkedTo;
	private Number maxPointWidth;
	private Object medianColor;
	private Number medianWidth;
	private Object negativeColor;
	private Point point;
	private Number pointInterval;
	private String pointIntervalUnit;
	private Number pointPadding;
	private Object pointPlacement;
	private Number pointRange;
	private Number pointStart;
	private Number pointWidth;
	private Boolean selected;
	private Boolean showCheckbox;
	private Boolean showInLegend;
	private States states;
	private Object stemColor;
	private String stemDashStyle;
	private Number stemWidth;
	private Boolean stickyTracking;
	private Tooltip tooltip;
	private Number turboThreshold;
	private Boolean visible;
	private Object whiskerColor;
	private Object whiskerLength;
	private Number whiskerWidth;
	private String zoneAxis;
	private ArrayList<Zones> zones;

	public PlotOptionsBoxplot() {
	}

	public Boolean getAllowPointSelect() {
		return allowPointSelect;
	}

	public void setAllowPointSelect(Boolean allowPointSelect) {
		this.allowPointSelect = allowPointSelect;
	}

	public Object getColor() {
		return color;
	}

	public void setColor(Object color) {
		this.color = color;
	}

	public Boolean getColorByPoint() {
		return colorByPoint;
	}

	public void setColorByPoint(Boolean colorByPoint) {
		this.colorByPoint = colorByPoint;
	}

	public Object getColors() {
		return colors;
	}

	public void setColors(Object colors) {
		this.colors = colors;
	}

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public Number getDepth() {
		return depth;
	}

	public void setDepth(Number depth) {
		this.depth = depth;
	}

	public Object getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Object edgeColor) {
		this.edgeColor = edgeColor;
	}

	public Number getEdgeWidth() {
		return edgeWidth;
	}

	public void setEdgeWidth(Number edgeWidth) {
		this.edgeWidth = edgeWidth;
	}

	public Boolean getEnableMouseTracking() {
		return enableMouseTracking;
	}

	public void setEnableMouseTracking(Boolean enableMouseTracking) {
		this.enableMouseTracking = enableMouseTracking;
	}

	public Object getFillColor() {
		return fillColor;
	}

	public void setFillColor(Object fillColor) {
		this.fillColor = fillColor;
	}

	public Boolean getGetExtremesFromAll() {
		return getExtremesFromAll;
	}

	public void setGetExtremesFromAll(Boolean getExtremesFromAll) {
		this.getExtremesFromAll = getExtremesFromAll;
	}

	public Number getGroupPadding() {
		return groupPadding;
	}

	public void setGroupPadding(Number groupPadding) {
		this.groupPadding = groupPadding;
	}

	public Number getGroupZPadding() {
		return groupZPadding;
	}

	public void setGroupZPadding(Number groupZPadding) {
		this.groupZPadding = groupZPadding;
	}

	public Boolean getGrouping() {
		return grouping;
	}

	public void setGrouping(Boolean grouping) {
		this.grouping = grouping;
	}

	public String[] getKeys() {
		String[] arr = new String[keys.size()];
		keys.toArray(arr);
		return arr;
	}

	public void setKeys(String... keys) {
		this.keys = new ArrayList<String>(Arrays.asList(keys));
	}

	public void addKey(String key) {
		if (this.keys == null) {
			this.keys = new ArrayList<String>();
		}
		this.keys.add(key);
	}

	public void removeKey(String key) {
		this.keys.remove(key);
	}

	public Number getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(Number lineWidth) {
		this.lineWidth = lineWidth;
	}

	public String getLinkedTo() {
		return linkedTo;
	}

	public void setLinkedTo(String linkedTo) {
		this.linkedTo = linkedTo;
	}

	public Number getMaxPointWidth() {
		return maxPointWidth;
	}

	public void setMaxPointWidth(Number maxPointWidth) {
		this.maxPointWidth = maxPointWidth;
	}

	public Object getMedianColor() {
		return medianColor;
	}

	public void setMedianColor(Object medianColor) {
		this.medianColor = medianColor;
	}

	public Number getMedianWidth() {
		return medianWidth;
	}

	public void setMedianWidth(Number medianWidth) {
		this.medianWidth = medianWidth;
	}

	public Object getNegativeColor() {
		return negativeColor;
	}

	public void setNegativeColor(Object negativeColor) {
		this.negativeColor = negativeColor;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Number getPointInterval() {
		return pointInterval;
	}

	public void setPointInterval(Number pointInterval) {
		this.pointInterval = pointInterval;
	}

	public String getPointIntervalUnit() {
		return pointIntervalUnit;
	}

	public void setPointIntervalUnit(String pointIntervalUnit) {
		this.pointIntervalUnit = pointIntervalUnit;
	}

	public Number getPointPadding() {
		return pointPadding;
	}

	public void setPointPadding(Number pointPadding) {
		this.pointPadding = pointPadding;
	}

	public Object getPointPlacement() {
		return pointPlacement;
	}

	public void setPointPlacement(Object pointPlacement) {
		this.pointPlacement = pointPlacement;
	}

	public Number getPointRange() {
		return pointRange;
	}

	public void setPointRange(Number pointRange) {
		this.pointRange = pointRange;
	}

	public Number getPointStart() {
		return pointStart;
	}

	public void setPointStart(Number pointStart) {
		this.pointStart = pointStart;
	}

	public Number getPointWidth() {
		return pointWidth;
	}

	public void setPointWidth(Number pointWidth) {
		this.pointWidth = pointWidth;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Boolean getShowCheckbox() {
		return showCheckbox;
	}

	public void setShowCheckbox(Boolean showCheckbox) {
		this.showCheckbox = showCheckbox;
	}

	public Boolean getShowInLegend() {
		return showInLegend;
	}

	public void setShowInLegend(Boolean showInLegend) {
		this.showInLegend = showInLegend;
	}

	public States getStates() {
		return states;
	}

	public void setStates(States states) {
		this.states = states;
	}

	public Object getStemColor() {
		return stemColor;
	}

	public void setStemColor(Object stemColor) {
		this.stemColor = stemColor;
	}

	public String getStemDashStyle() {
		return stemDashStyle;
	}

	public void setStemDashStyle(String stemDashStyle) {
		this.stemDashStyle = stemDashStyle;
	}

	public Number getStemWidth() {
		return stemWidth;
	}

	public void setStemWidth(Number stemWidth) {
		this.stemWidth = stemWidth;
	}

	public Boolean getStickyTracking() {
		return stickyTracking;
	}

	public void setStickyTracking(Boolean stickyTracking) {
		this.stickyTracking = stickyTracking;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	public Number getTurboThreshold() {
		return turboThreshold;
	}

	public void setTurboThreshold(Number turboThreshold) {
		this.turboThreshold = turboThreshold;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Object getWhiskerColor() {
		return whiskerColor;
	}

	public void setWhiskerColor(Object whiskerColor) {
		this.whiskerColor = whiskerColor;
	}

	public Object getWhiskerLength() {
		return whiskerLength;
	}

	public void setWhiskerLength(Object whiskerLength) {
		this.whiskerLength = whiskerLength;
	}

	public Number getWhiskerWidth() {
		return whiskerWidth;
	}

	public void setWhiskerWidth(Number whiskerWidth) {
		this.whiskerWidth = whiskerWidth;
	}

	public String getZoneAxis() {
		return zoneAxis;
	}

	public void setZoneAxis(String zoneAxis) {
		this.zoneAxis = zoneAxis;
	}

	public Zones[] getZones() {
		Zones[] arr = new Zones[zones.size()];
		zones.toArray(arr);
		return arr;
	}

	public void setZones(Zones... zones) {
		this.zones = new ArrayList<Zones>(Arrays.asList(zones));
	}

	public void addZone(Zones zone) {
		if (this.zones == null) {
			this.zones = new ArrayList<Zones>();
		}
		this.zones.add(zone);
	}

	public void removeZone(Zones zone) {
		this.zones.remove(zone);
	}

	@Override
	public ChartType getChartType() {
		return ChartType.BOXPLOT;
	}
}