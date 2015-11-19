package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;
public class Drilldown extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Style activeAxisLabelStyle;
	private Style activeDataLabelStyle;
	private Boolean allowPointDrilldown;
	private Object animation;
	private DrillUpButton drillUpButton;
	private PlotOptionsSeries series;

	public Drilldown() {
	}

	public Style getActiveAxisLabelStyle() {
		return activeAxisLabelStyle;
	}

	public void setActiveAxisLabelStyle(Style activeAxisLabelStyle) {
		this.activeAxisLabelStyle = activeAxisLabelStyle;
	}

	public Style getActiveDataLabelStyle() {
		return activeDataLabelStyle;
	}

	public void setActiveDataLabelStyle(Style activeDataLabelStyle) {
		this.activeDataLabelStyle = activeDataLabelStyle;
	}

	public Boolean getAllowPointDrilldown() {
		return allowPointDrilldown;
	}

	public void setAllowPointDrilldown(Boolean allowPointDrilldown) {
		this.allowPointDrilldown = allowPointDrilldown;
	}

	public Object getAnimation() {
		return animation;
	}

	public void setAnimation(Object animation) {
		this.animation = animation;
	}

	public DrillUpButton getDrillUpButton() {
		return drillUpButton;
	}

	public void setDrillUpButton(DrillUpButton drillUpButton) {
		this.drillUpButton = drillUpButton;
	}

	public PlotOptionsSeries getSeries() {
		return series;
	}

	public void setSeries(PlotOptionsSeries series) {
		this.series = series;
	}
}