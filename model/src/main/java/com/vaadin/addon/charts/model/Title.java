package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;
public class Title extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private String align;
	private Number[] margin;
	private Number offset;
	private Number rotation;
	private Style style;
	private String text;

	public Title() {
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public Number[] getMargin() {
		return margin;
	}

	public void setMargin(Number[] margin) {
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

	public Title(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}