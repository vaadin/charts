package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;
public class Legend extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private String align;
	private Object backgroundColor;
	private Object borderColor;
	private Number borderRadius;
	private Number borderWidth;
	private Boolean enabled;
	private Boolean floating;
	private Number itemDistance;
	private Style itemHiddenStyle;
	private Style itemHoverStyle;
	private Number itemMarginBottom;
	private Number itemMarginTop;
	private Style itemStyle;
	private Number itemWidth;
	private String labelFormat;
	private Object labelFormatter;
	private String layout;
	private Number lineHeight;
	private Number[] margin;
	private Number maxHeight;
	private Navigation navigation;
	private Number padding;
	private Boolean reversed;
	private Boolean rtl;
	private Object shadow;
	private Number symbolHeight;
	private Number symbolPadding;
	private Number symbolRadius;
	private Number symbolWidth;
	private Title title;
	private Boolean useHTML;
	private String verticalAlign;
	private Number width;
	private Number x;
	private Number y;

	public Legend() {
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
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

	public Legend(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getFloating() {
		return floating;
	}

	public void setFloating(Boolean floating) {
		this.floating = floating;
	}

	public Number getItemDistance() {
		return itemDistance;
	}

	public void setItemDistance(Number itemDistance) {
		this.itemDistance = itemDistance;
	}

	public Style getItemHiddenStyle() {
		return itemHiddenStyle;
	}

	public void setItemHiddenStyle(Style itemHiddenStyle) {
		this.itemHiddenStyle = itemHiddenStyle;
	}

	public Style getItemHoverStyle() {
		return itemHoverStyle;
	}

	public void setItemHoverStyle(Style itemHoverStyle) {
		this.itemHoverStyle = itemHoverStyle;
	}

	public Number getItemMarginBottom() {
		return itemMarginBottom;
	}

	public void setItemMarginBottom(Number itemMarginBottom) {
		this.itemMarginBottom = itemMarginBottom;
	}

	public Number getItemMarginTop() {
		return itemMarginTop;
	}

	public void setItemMarginTop(Number itemMarginTop) {
		this.itemMarginTop = itemMarginTop;
	}

	public Style getItemStyle() {
		return itemStyle;
	}

	public void setItemStyle(Style itemStyle) {
		this.itemStyle = itemStyle;
	}

	public Number getItemWidth() {
		return itemWidth;
	}

	public void setItemWidth(Number itemWidth) {
		this.itemWidth = itemWidth;
	}

	public String getLabelFormat() {
		return labelFormat;
	}

	public void setLabelFormat(String labelFormat) {
		this.labelFormat = labelFormat;
	}

	public Object getLabelFormatter() {
		return labelFormatter;
	}

	public void setLabelFormatter(Object labelFormatter) {
		this.labelFormatter = labelFormatter;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public Number getLineHeight() {
		return lineHeight;
	}

	public void setLineHeight(Number lineHeight) {
		this.lineHeight = lineHeight;
	}

	public Number[] getMargin() {
		return margin;
	}

	public void setMargin(Number[] margin) {
		this.margin = margin;
	}

	public Number getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(Number maxHeight) {
		this.maxHeight = maxHeight;
	}

	public Navigation getNavigation() {
		return navigation;
	}

	public void setNavigation(Navigation navigation) {
		this.navigation = navigation;
	}

	public Number getPadding() {
		return padding;
	}

	public void setPadding(Number padding) {
		this.padding = padding;
	}

	public Boolean getReversed() {
		return reversed;
	}

	public void setReversed(Boolean reversed) {
		this.reversed = reversed;
	}

	public Boolean getRtl() {
		return rtl;
	}

	public void setRtl(Boolean rtl) {
		this.rtl = rtl;
	}

	public Object getShadow() {
		return shadow;
	}

	public void setShadow(Object shadow) {
		this.shadow = shadow;
	}

	public Number getSymbolHeight() {
		return symbolHeight;
	}

	public void setSymbolHeight(Number symbolHeight) {
		this.symbolHeight = symbolHeight;
	}

	public Number getSymbolPadding() {
		return symbolPadding;
	}

	public void setSymbolPadding(Number symbolPadding) {
		this.symbolPadding = symbolPadding;
	}

	public Number getSymbolRadius() {
		return symbolRadius;
	}

	public void setSymbolRadius(Number symbolRadius) {
		this.symbolRadius = symbolRadius;
	}

	public Number getSymbolWidth() {
		return symbolWidth;
	}

	public void setSymbolWidth(Number symbolWidth) {
		this.symbolWidth = symbolWidth;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
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

	public Number getWidth() {
		return width;
	}

	public void setWidth(Number width) {
		this.width = width;
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