package com.vaadin.addon.charts.model;
public class PlotOptionsAreaSplineRange extends AbstractPlotOptions {

	private static final long serialVersionUID = 1L;
	private Boolean allowPointSelect;
	private Boolean animation;
	private Object color;
	private Boolean connectNulls;
	private Number cropThreshold;
	private String cursor;
	private String dashStyle;
	private DataLabels dataLabels;
	private Boolean enableMouseTracking;
	private Object fillColor;
	private Number fillOpacity;
	private Object lineColor;
	private Number lineWidth;
	private String linkedTo;
	private Object negativeColor;
	private Object negativeFillColor;
	private Point point;
	private Number pointInterval;
	private String pointIntervalUnit;
	private Object pointPlacement;
	private Number pointStart;
	private Boolean selected;
	private Object shadow;
	private Boolean showCheckbox;
	private Boolean showInLegend;
	private States states;
	private String step;
	private Boolean stickyTracking;
	private Tooltip tooltip;
	private Boolean trackByArea;
	private Number turboThreshold;
	private Boolean visible;
	private String zoneAxis;
	private Zones[] zones;

	public PlotOptionsAreaSplineRange() {
	}

	@Override
	public ChartType getChartType() {
		return ChartType.AREASPLINERANGE;
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

	public Boolean getConnectNulls() {
		return connectNulls;
	}

	public void setConnectNulls(Boolean connectNulls) {
		this.connectNulls = connectNulls;
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

	public Object getFillColor() {
		return fillColor;
	}

	public void setFillColor(Object fillColor) {
		this.fillColor = fillColor;
	}

	public Number getFillOpacity() {
		return fillOpacity;
	}

	public void setFillOpacity(Number fillOpacity) {
		this.fillOpacity = fillOpacity;
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

	public String getLinkedTo() {
		return linkedTo;
	}

	public void setLinkedTo(String linkedTo) {
		this.linkedTo = linkedTo;
	}

	public Object getNegativeColor() {
		return negativeColor;
	}

	public void setNegativeColor(Object negativeColor) {
		this.negativeColor = negativeColor;
	}

	public Object getNegativeFillColor() {
		return negativeFillColor;
	}

	public void setNegativeFillColor(Object negativeFillColor) {
		this.negativeFillColor = negativeFillColor;
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

	public Object getPointPlacement() {
		return pointPlacement;
	}

	public void setPointPlacement(Object pointPlacement) {
		this.pointPlacement = pointPlacement;
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

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
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

	public Boolean getTrackByArea() {
		return trackByArea;
	}

	public void setTrackByArea(Boolean trackByArea) {
		this.trackByArea = trackByArea;
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