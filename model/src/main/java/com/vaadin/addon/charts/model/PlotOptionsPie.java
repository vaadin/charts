package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.AbstractPlotOptions;
import com.vaadin.addon.charts.model.ChartType;
public class PlotOptionsPie extends AbstractPlotOptions {

	private static final long serialVersionUID = 1L;
	private Boolean allowPointSelect;
	private Boolean animation;
	private Object borderColor;
	private Number borderWidth;
	private Number[] center;
	private Object colors;
	private String cursor;
	private DataLabels dataLabels;
	private Number depth;
	private Boolean enableMouseTracking;
	private Number endAngle;
	private Boolean ignoreHiddenPoint;
	private Object innerSize;
	private String linkedTo;
	private Number minSize;
	private Point point;
	private Boolean selected;
	private Object shadow;
	private Boolean showInLegend;
	private Object size;
	private Number slicedOffset;
	private Number startAngle;
	private States states;
	private Boolean stickyTracking;
	private Tooltip tooltip;
	private Boolean visible;
	private String zoneAxis;
	private Zones[] zones;

	public PlotOptionsPie() {
	}

	@Override
	public ChartType getChartType() {
		return ChartType.PIE;
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

	public Number getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
	}

	public Number[] getCenter() {
		return center;
	}

	public void setCenter(Number[] center) {
		this.center = center;
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

	public Boolean getEnableMouseTracking() {
		return enableMouseTracking;
	}

	public void setEnableMouseTracking(Boolean enableMouseTracking) {
		this.enableMouseTracking = enableMouseTracking;
	}

	public Number getEndAngle() {
		return endAngle;
	}

	public void setEndAngle(Number endAngle) {
		this.endAngle = endAngle;
	}

	public Boolean getIgnoreHiddenPoint() {
		return ignoreHiddenPoint;
	}

	public void setIgnoreHiddenPoint(Boolean ignoreHiddenPoint) {
		this.ignoreHiddenPoint = ignoreHiddenPoint;
	}

	public Object getInnerSize() {
		return innerSize;
	}

	public void setInnerSize(Object innerSize) {
		this.innerSize = innerSize;
	}

	public String getLinkedTo() {
		return linkedTo;
	}

	public void setLinkedTo(String linkedTo) {
		this.linkedTo = linkedTo;
	}

	public Number getMinSize() {
		return minSize;
	}

	public void setMinSize(Number minSize) {
		this.minSize = minSize;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
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

	public Boolean getShowInLegend() {
		return showInLegend;
	}

	public void setShowInLegend(Boolean showInLegend) {
		this.showInLegend = showInLegend;
	}

	public Object getSize() {
		return size;
	}

	public void setSize(Object size) {
		this.size = size;
	}

	public Number getSlicedOffset() {
		return slicedOffset;
	}

	public void setSlicedOffset(Number slicedOffset) {
		this.slicedOffset = slicedOffset;
	}

	public Number getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(Number startAngle) {
		this.startAngle = startAngle;
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

	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
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
		return zones;
	}

	public void setZones(Zones[] zones) {
		this.zones = zones;
	}
}