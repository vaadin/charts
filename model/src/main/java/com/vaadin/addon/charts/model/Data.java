package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
public class Data extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object color;
	private Number colorValue;
	private Object dataLabels;
	private String drilldown;
	private String id;
	private Boolean isIntermediateSum;
	private Boolean isSum;
	private Number legendIndex;
	private Marker marker;
	private String name;
	private String parent;
	private Boolean sliced;
	private Number x;
	private Number y;

	public Data() {
	}

	public Object getColor() {
		return color;
	}

	public void setColor(Object color) {
		this.color = color;
	}

	public Number getColorValue() {
		return colorValue;
	}

	public void setColorValue(Number colorValue) {
		this.colorValue = colorValue;
	}

	public Object getDataLabels() {
		return dataLabels;
	}

	public void setDataLabels(Object dataLabels) {
		this.dataLabels = dataLabels;
	}

	public String getDrilldown() {
		return drilldown;
	}

	public void setDrilldown(String drilldown) {
		this.drilldown = drilldown;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsIntermediateSum() {
		return isIntermediateSum;
	}

	public void setIsIntermediateSum(Boolean isIntermediateSum) {
		this.isIntermediateSum = isIntermediateSum;
	}

	public Boolean getIsSum() {
		return isSum;
	}

	public void setIsSum(Boolean isSum) {
		this.isSum = isSum;
	}

	public Number getLegendIndex() {
		return legendIndex;
	}

	public void setLegendIndex(Number legendIndex) {
		this.legendIndex = legendIndex;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Boolean getSliced() {
		return sliced;
	}

	public void setSliced(Boolean sliced) {
		this.sliced = sliced;
	}

	public Number getX() {
		return x;
	}

	public void setX(Number x) {
		this.x = x;
	}

	public Number getY() {
		return y;
	}

	public void setY(Number y) {
		this.y = y;
	}
}