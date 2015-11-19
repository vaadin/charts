package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;
public class StackLabels extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private String align;
	private Boolean enabled;
	private String format;
	private String _fn_formatter;
	private Number rotation;
	private Style style;
	private String textAlign;
	private Boolean useHTML;
	private String verticalAlign;
	private Number x;
	private Number y;

	public StackLabels() {
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public StackLabels(Boolean enabled) {
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

	public String getFormatter() {
		return _fn_formatter;
	}

	public void setFormatter(String _fn_formatter) {
		this._fn_formatter = _fn_formatter;
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

	public String getTextAlign() {
		return textAlign;
	}

	public void setTextAlign(String textAlign) {
		this.textAlign = textAlign;
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