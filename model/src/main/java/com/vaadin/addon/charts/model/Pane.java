package com.vaadin.addon.charts.model;
public class Pane extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object[] background;
	private Object[] center;
	private Number endAngle;
	private Object size;
	private Number startAngle;

	public Pane() {
	}

	public Object[] getBackground() {
		return background;
	}

	public void setBackground(Object[] background) {
		this.background = background;
	}

	public Object[] getCenter() {
		return center;
	}

	public Number getEndAngle() {
		return endAngle;
	}

	public void setEndAngle(Number endAngle) {
		this.endAngle = endAngle;
	}

	public Object getSize() {
		return size;
	}

	public void setSize(Object size) {
		this.size = size;
	}

	public Number getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(Number startAngle) {
		this.startAngle = startAngle;
	}

	public void setCenter(Number x, Number y) {
		this.center = new Number[]{x, y};
	}

	public void setCenter(String x, String y) {
		this.center = new String[]{x, y};
	}
}