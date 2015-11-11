package com.vaadin.addon.charts.model;
public class PlotOptionsSolidGauge extends AbstractPlotOptions {

	private static final long serialVersionUID = 1L;
	private Boolean animation;
	private String cursor;
	private DataLabels dataLabels;
	private Boolean enableMouseTracking;
	private Number overshoot;
	private Point point;
	private Boolean selected;
	private Boolean showCheckbox;
	private Boolean showInLegend;
	private States states;
	private Boolean stickyTracking;
	private Tooltip tooltip;
	private Boolean visible;
	private Boolean wrap;
	private String zoneAxis;
	private Zones[] zones;

	public PlotOptionsSolidGauge() {
	}

	@Override
	public ChartType getChartType() {
		return ChartType.SOLIDGAUGE;
	}

	public Boolean getAnimation() {
		return animation;
	}

	public void setAnimation(Boolean animation) {
		this.animation = animation;
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

	public Boolean getEnableMouseTracking() {
		return enableMouseTracking;
	}

	public void setEnableMouseTracking(Boolean enableMouseTracking) {
		this.enableMouseTracking = enableMouseTracking;
	}

	public Number getOvershoot() {
		return overshoot;
	}

	public void setOvershoot(Number overshoot) {
		this.overshoot = overshoot;
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

	public Boolean getWrap() {
		return wrap;
	}

	public void setWrap(Boolean wrap) {
		this.wrap = wrap;
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