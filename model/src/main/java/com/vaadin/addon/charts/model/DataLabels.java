package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.style.Style;
public class DataLabels extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private String align;
	private Boolean allowOverlap;
	private Object backgroundColor;
	private Object borderColor;
	private Number borderRadius;
	private Number borderWidth;
	private Object color;
	private Boolean crop;
	private Boolean defer;
	private Boolean enabled;
	private String format;
	private Object formatter;
	private Boolean inside;
	private String overflow;
	private Number padding;
	private Number rotation;
	private Object shadow;
	private String shape;
	private Style style;
	private Boolean useHTML;
	private String verticalAlign;
	private Number x;
	private Number y;
	private Number zIndex;

	public DataLabels() {
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public Boolean getAllowOverlap() {
		return allowOverlap;
	}

	public void setAllowOverlap(Boolean allowOverlap) {
		this.allowOverlap = allowOverlap;
	}

	public Object getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Object backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Object getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Object borderColor) {
		this.borderColor = borderColor;
	}

	public Number getBorderRadius() {
		return borderRadius;
	}

	public void setBorderRadius(Number borderRadius) {
		this.borderRadius = borderRadius;
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

	public Boolean getCrop() {
		return crop;
	}

	public void setCrop(Boolean crop) {
		this.crop = crop;
	}

	public Boolean getDefer() {
		return defer;
	}

	public void setDefer(Boolean defer) {
		this.defer = defer;
	}

	public DataLabels(Boolean enabled) {
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

	public Boolean getInside() {
		return inside;
	}

	public void setInside(Boolean inside) {
		this.inside = inside;
	}

	public String getOverflow() {
		return overflow;
	}

	public void setOverflow(String overflow) {
		this.overflow = overflow;
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

	public Object getShadow() {
		return shadow;
	}

	public void setShadow(Object shadow) {
		this.shadow = shadow;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
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

	public Number getZIndex() {
		return zIndex;
	}

	public void setZIndex(Number zIndex) {
		this.zIndex = zIndex;
	}
}