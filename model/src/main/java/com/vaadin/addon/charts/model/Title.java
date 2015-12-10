package com.vaadin.addon.charts.model;

import java.util.ArrayList;
import java.util.Arrays;
import com.vaadin.addon.charts.model.style.Style;
public class Title extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private String align;
	private Boolean floating;
	private ArrayList<Number> margin;
	private Style style;
	private String text;
	private Boolean useHTML;
	private String verticalAlign;
	private Number x;
	private Number y;

	public Title() {
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public Boolean getFloating() {
		return floating;
	}

	public void setFloating(Boolean floating) {
		this.floating = floating;
	}

	public Number[] getMargin() {
		Number[] arr = new Number[margin.size()];
		margin.toArray(arr);
		return arr;
	}

	public void setMargin(Number... margin) {
		this.margin = new ArrayList<Number>(Arrays.asList(margin));
	}

	public void addMargin(Number margin) {
		if (this.margin == null) {
			this.margin = new ArrayList<Number>();
		}
		this.margin.add(margin);
	}

	public void removeMargin(Number margin) {
		this.margin.remove(margin);
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

	public Boolean getUseHTML() {
		return useHTML;
	}

	public void setUseHTML(Boolean useHTML) {
		this.useHTML = useHTML;
	}

	public String getVerticalAlign() {
		return verticalAlign;
	}

	public void setVerticalAlign(String verticalAlign) {
		this.verticalAlign = verticalAlign;
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