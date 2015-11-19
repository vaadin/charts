package com.vaadin.addon.charts.model;

import java.util.ArrayList;
import java.util.Arrays;
public class PlotOptionsColumn extends AbstractPlotOptions {

	private static final long serialVersionUID = 1L;
	private Boolean allowPointSelect;
	private Boolean animation;
	private Object borderColor;
	private Number borderRadius;
	private Number borderWidth;
	private Object color;
	private Boolean colorByPoint;
	private Object colors;
	private Number cropThreshold;
	private String cursor;
	private DataLabels dataLabels;
	private Number depth;
	private Object edgeColor;
	private Number edgeWidth;
	private Boolean enableMouseTracking;
	private Boolean getExtremesFromAll;
	private Number groupPadding;
	private Number groupZPadding;
	private Boolean grouping;
	private ArrayList<String> keys;
	private String linkedTo;
	private Number maxPointWidth;
	private Number minPointLength;
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
	private Object shadow;
	private Boolean showCheckbox;
	private Boolean showInLegend;
	private Boolean softThreshold;
	private String stacking;
	private States states;
	private Boolean stickyTracking;
	private Number threshold;
	private Tooltip tooltip;
	private Number turboThreshold;
	private Boolean visible;
	private String zoneAxis;
	private ArrayList<Zones> zones;

	public PlotOptionsColumn() {
	}

	public Boolean getAllowPointSelect() {
		return allowPointSelect;
	}

	public void setAllowPointSelect(Boolean allowPointSelect) {
		this.allowPointSelect = allowPointSelect;
	}

	public Boolean getAnimation() {
		return animation;
	}

	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}

	public Object getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Object borderColor) {
		this.borderColor = borderColor;
	}

	public Number getBorderRadius() {
		return borderRadius;
	}

	public void setBorderRadius(Number borderRadius) {
		this.borderRadius = borderRadius;
	}

	public Number getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
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

	public Number getCropThreshold() {
		return cropThreshold;
	}

	public void setCropThreshold(Number cropThreshold) {
		this.cropThreshold = cropThreshold;
	}

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public DataLabels getDataLabels() {
		return dataLabels;
	}

	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
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

	public Number getMinPointLength() {
		return minPointLength;
	}

	public void setMinPointLength(Number minPointLength) {
		this.minPointLength = minPointLength;
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

	public Object getShadow() {
		return shadow;
	}

	public void setShadow(Object shadow) {
		this.shadow = shadow;
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

	public Boolean getSoftThreshold() {
		return softThreshold;
	}

	public void setSoftThreshold(Boolean softThreshold) {
		this.softThreshold = softThreshold;
	}

	public String getStacking() {
		return stacking;
	}

	public void setStacking(String stacking) {
		this.stacking = stacking;
	}

	public States getStates() {
		return states;
	}

	public void setStates(States states) {
		this.states = states;
	}

	public Boolean getStickyTracking() {
		return stickyTracking;
	}

	public void setStickyTracking(Boolean stickyTracking) {
		this.stickyTracking = stickyTracking;
	}

	public Number getThreshold() {
		return threshold;
	}

	public void setThreshold(Number threshold) {
		this.threshold = threshold;
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
		return ChartType.COLUMN;
	}
}