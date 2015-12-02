package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.Style;
public class DataLabels extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private String align;
	private Boolean allowOverlap;
	private Color backgroundColor;
	private Color borderColor;
	private Number borderRadius;
	private Number borderWidth;
	private Color color;
	private Boolean crop;
	private Boolean defer;
	private Boolean enabled;
	private String format;
	private String _fn_formatter;
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
	private String connectorColor;
	private Number connectorPadding;
	private Number connectorWidth;
	private Number distance;
	private Boolean softConnector;
	private Number xHigh;
	private Number xLow;
	private Number yHigh;
	private Number yLow;

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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
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

	public String getFormatter() {
		return _fn_formatter;
	}

	public void setFormatter(String _fn_formatter) {
		this._fn_formatter = _fn_formatter;
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

	public String getConnectorColor() {
		return connectorColor;
	}

	public void setConnectorColor(String connectorColor) {
		this.connectorColor = connectorColor;
	}

	public Number getConnectorPadding() {
		return connectorPadding;
	}

	public void setConnectorPadding(Number connectorPadding) {
		this.connectorPadding = connectorPadding;
	}

	public Number getConnectorWidth() {
		return connectorWidth;
	}

	public void setConnectorWidth(Number connectorWidth) {
		this.connectorWidth = connectorWidth;
	}

	public Number getDistance() {
		return distance;
	}

	public void setDistance(Number distance) {
		this.distance = distance;
	}

	public Boolean getSoftConnector() {
		return softConnector;
	}

	public void setSoftConnector(Boolean softConnector) {
		this.softConnector = softConnector;
	}

	public Number getXHigh() {
		return xHigh;
	}

	public void setXHigh(Number xHigh) {
		this.xHigh = xHigh;
	}

	public Number getXLow() {
		return xLow;
	}

	public void setXLow(Number xLow) {
		this.xLow = xLow;
	}

	public Number getYHigh() {
		return yHigh;
	}

	public void setYHigh(Number yHigh) {
		this.yHigh = yHigh;
	}

	public Number getYLow() {
		return yLow;
	}

	public void setYLow(Number yLow) {
		this.yLow = yLow;
	}
}