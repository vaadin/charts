package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.style.Style;
public class Labels extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private String align;
	private Number[] autoRotation;
	private Number distance;
	private Boolean enabled;
	private String format;
	private Object formatter;
	private Number padding;
	private Number rotation;
	private Number staggerLines;
	private Number step;
	private Style style;
	private Boolean useHTML;
	private Number x;
	private Number y;
	private Number zIndex;

	public Labels() {
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public Number[] getAutoRotation() {
		return autoRotation;
	}

	public void setAutoRotation(Number[] autoRotation) {
		this.autoRotation = autoRotation;
	}

	public Number getDistance() {
		return distance;
	}

	public void setDistance(Number distance) {
		this.distance = distance;
	}

	public Labels(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Object getFormatter() {
		return formatter;
	}

	public void setFormatter(Object formatter) {
		this.formatter = formatter;
	}

	public Number getPadding() {
		return padding;
	}

	public void setPadding(Number padding) {
		this.padding = padding;
	}

	public Number getRotation() {
		return rotation;
	}

	public void setRotation(Number rotation) {
		this.rotation = rotation;
	}

	public Number getStaggerLines() {
		return staggerLines;
	}

	public void setStaggerLines(Number staggerLines) {
		this.staggerLines = staggerLines;
	}

	public Number getStep() {
		return step;
	}

	public void setStep(Number step) {
		this.step = step;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public Boolean getUseHTML() {
		return useHTML;
	}

	public void setUseHTML(Boolean useHTML) {
		this.useHTML = useHTML;
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

	public Number getZIndex() {
		return zIndex;
	}

	public void setZIndex(Number zIndex) {
		this.zIndex = zIndex;
	}
}