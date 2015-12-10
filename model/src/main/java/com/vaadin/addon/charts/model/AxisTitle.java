package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;
public class AxisTitle extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private String align;
	private Number margin;
	private Number offset;
	private Number rotation;
	private Style style;
	private String text;
	private Number x;
	private Number y;

	public AxisTitle() {
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public Number getMargin() {
		return margin;
	}

	public void setMargin(Number margin) {
		this.margin = margin;
	}

	public Number getOffset() {
		return offset;
	}

	public void setOffset(Number offset) {
		this.offset = offset;
	}

	public Number getRotation() {
		return rotation;
	}

	public void setRotation(Number rotation) {
		this.rotation = rotation;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public AxisTitle(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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