package com.vaadin.addon.charts.model;
public class PlotOptionsPolygon extends AbstractPlotOptions {

	private static final long serialVersionUID = 1L;
	private Boolean allowPointSelect;
	private Boolean animation;
	private Object color;
	private Number cropThreshold;
	private String cursor;
	private String dashStyle;
	private DataLabels dataLabels;
	private Boolean enableMouseTracking;
	private Number lineWidth;
	private String linkedTo;
	private Marker marker;
	private Object negativeColor;
	private Point point;
	private Number pointInterval;
	private String pointIntervalUnit;
	private Number pointStart;
	private Boolean selected;
	private Object shadow;
	private Boolean showCheckbox;
	private Boolean showInLegend;
	private States states;
	private Boolean stickyTracking;
	private Number threshold;
	private Tooltip tooltip;
	private Number turboThreshold;
	private Boolean visible;
	private String zoneAxis;
	private Zones[] zones;

	public PlotOptionsPolygon() {
	}

	@Override
	public ChartType getChartType() {
		return ChartType.POLYGON;
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

	public Object getColor() {
		return color;
	}

	public void setColor(Object color) {
		this.color = color;
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

	public String getDashStyle() {
		return dashStyle;
	}

	public void setDashStyle(String dashStyle) {
		this.dashStyle = dashStyle;
	}

	public DataLabels getDataLabels() {
		return dataLabels;
	}

	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}

	public Boolean getEnableMouseTracking() {
		return enableMouseTracking;
	}

	public void setEnableMouseTracking(Boolean enableMouseTracking) {
		this.enableMouseTracking = enableMouseTracking;
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

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
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

	public Number getPointStart() {
		return pointStart;
	}

	public void setPointStart(Number pointStart) {
		this.pointStart = pointStart;
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
		return zones;
	}

	public void setZones(Zones[] zones) {
		this.zones = zones;
	}
}