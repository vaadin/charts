package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.Style;
public class Tooltip extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private DateTimeLabelFormats dateTimeLabelFormats;
	private Boolean followPointer;
	private Boolean followTouchMove;
	private String footerFormat;
	private String headerFormat;
	private Number hideDelay;
	private String pointFormat;
	private Object pointFormatter;
	private Number valueDecimals;
	private String valuePrefix;
	private String valueSuffix;
	private String xDateFormat;
	private Boolean animation;
	private Color backgroundColor;
	private Color borderColor;
	private Number borderRadius;
	private Number borderWidth;
	private Object[] crosshairs;
	private Boolean enabled;
	private String _fn_formatter;
	private Object positioner;
	private Boolean shadow;
	private String shape;
	private Boolean shared;
	private Number snap;
	private Style style;
	private Boolean useHTML;

	public Tooltip() {
	}

	public DateTimeLabelFormats getDateTimeLabelFormats() {
		return dateTimeLabelFormats;
	}

	public void setDateTimeLabelFormats(
			DateTimeLabelFormats dateTimeLabelFormats) {
		this.dateTimeLabelFormats = dateTimeLabelFormats;
	}

	public Boolean getFollowPointer() {
		return followPointer;
	}

	public void setFollowPointer(Boolean followPointer) {
		this.followPointer = followPointer;
	}

	public Boolean getFollowTouchMove() {
		return followTouchMove;
	}

	public void setFollowTouchMove(Boolean followTouchMove) {
		this.followTouchMove = followTouchMove;
	}

	public String getFooterFormat() {
		return footerFormat;
	}

	public void setFooterFormat(String footerFormat) {
		this.footerFormat = footerFormat;
	}

	public String getHeaderFormat() {
		return headerFormat;
	}

	public void setHeaderFormat(String headerFormat) {
		this.headerFormat = headerFormat;
	}

	public Number getHideDelay() {
		return hideDelay;
	}

	public void setHideDelay(Number hideDelay) {
		this.hideDelay = hideDelay;
	}

	public String getPointFormat() {
		return pointFormat;
	}

	public void setPointFormat(String pointFormat) {
		this.pointFormat = pointFormat;
	}

	public Object getPointFormatter() {
		return pointFormatter;
	}

	public void setPointFormatter(Object pointFormatter) {
		this.pointFormatter = pointFormatter;
	}

	public Number getValueDecimals() {
		return valueDecimals;
	}

	public void setValueDecimals(Number valueDecimals) {
		this.valueDecimals = valueDecimals;
	}

	public String getValuePrefix() {
		return valuePrefix;
	}

	public void setValuePrefix(String valuePrefix) {
		this.valuePrefix = valuePrefix;
	}

	public String getValueSuffix() {
		return valueSuffix;
	}

	public void setValueSuffix(String valueSuffix) {
		this.valueSuffix = valueSuffix;
	}

	public String getXDateFormat() {
		return xDateFormat;
	}

	public void setXDateFormat(String xDateFormat) {
		this.xDateFormat = xDateFormat;
	}

	public Boolean getAnimation() {
		return animation;
	}

	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
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

	public Object[] getCrosshairs() {
		return crosshairs;
	}

	public void setCrosshairs(Object[] crosshairs) {
		this.crosshairs = crosshairs;
	}

	public Tooltip(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFormatter() {
		return _fn_formatter;
	}

	public void setFormatter(String _fn_formatter) {
		this._fn_formatter = _fn_formatter;
	}

	public Object getPositioner() {
		return positioner;
	}

	public void setPositioner(Object positioner) {
		this.positioner = positioner;
	}

	public Boolean getShadow() {
		return shadow;
	}

	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public Boolean getShared() {
		return shared;
	}

	public void setShared(Boolean shared) {
		this.shared = shared;
	}

	public Number getSnap() {
		return snap;
	}

	public void setSnap(Number snap) {
		this.snap = snap;
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
}