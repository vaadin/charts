package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.util.SizeWithUnit;
public class Pane extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object[] background;
	private Object[] center;
	private Number endAngle;
	private String size;
	private Number startAngle;
	private Integer paneIndex;

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

	public float getSize() {
		String tmp = size;
		if (size == null) {
			return -1.0f;
		}
		if (this.size.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getSizeUnit() {
		if (this.size == null) {
			return Unit.PIXELS;
		}
		if (this.size.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setSize(String size) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(size);
		if (tmp != null) {
			setSize(tmp.getSize(), tmp.getUnit());
		} else {
			setSize(-1, Unit.PIXELS);
		}
	}

	public void setSize(float size, Unit unit) {
		String value = Float.toString(size);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (size == -1) {
			value = null;
		}
		this.size = value;
	}

	public Number getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(Number startAngle) {
		this.startAngle = startAngle;
	}

	Integer getPaneIndex() {
		return paneIndex;
	}

	void setPaneIndex(Integer paneIndex) {
		this.paneIndex = paneIndex;
	}

	public Pane(Number startAngle, Number endAngle) {
		this.startAngle = startAngle;
		this.endAngle = endAngle;
	}

	public void setCenter(String x, String y) {
		this.center = new String[]{x, y};
	}

	public void setCenter(Number x, Number y) {
		this.center = new Number[]{x, y};
	}
}