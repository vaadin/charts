package com.vaadin.addon.charts.model;
public class Levels extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object borderColor;
	private String borderDashStyle;
	private Number borderWidth;
	private Object color;
	private DataLabels dataLabels;
	private String layoutAlgorithm;
	private String layoutStartingDirection;
	private Number level;

	public Levels() {
	}

	public Object getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Object borderColor) {
		this.borderColor = borderColor;
	}

	public String getBorderDashStyle() {
		return borderDashStyle;
	}

	public void setBorderDashStyle(String borderDashStyle) {
		this.borderDashStyle = borderDashStyle;
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

	public DataLabels getDataLabels() {
		return dataLabels;
	}

	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}

	public String getLayoutAlgorithm() {
		return layoutAlgorithm;
	}

	public void setLayoutAlgorithm(String layoutAlgorithm) {
		this.layoutAlgorithm = layoutAlgorithm;
	}

	public String getLayoutStartingDirection() {
		return layoutStartingDirection;
	}

	public void setLayoutStartingDirection(String layoutStartingDirection) {
		this.layoutStartingDirection = layoutStartingDirection;
	}

	public Number getLevel() {
		return level;
	}

	public void setLevel(Number level) {
		this.level = level;
	}
}