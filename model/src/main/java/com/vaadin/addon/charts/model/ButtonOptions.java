package com.vaadin.addon.charts.model;
public class ButtonOptions extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private String align;
	private Boolean enabled;
	private Number height;
	private Object symbolFill;
	private Number symbolSize;
	private Object symbolStroke;
	private Number symbolStrokeWidth;
	private Number symbolX;
	private Number symbolY;
	private String text;
	private Object theme;
	private String verticalAlign;
	private Number width;
	private Number y;

	public ButtonOptions() {
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public ButtonOptions(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Number getHeight() {
		return height;
	}

	public void setHeight(Number height) {
		this.height = height;
	}

	public Object getSymbolFill() {
		return symbolFill;
	}

	public void setSymbolFill(Object symbolFill) {
		this.symbolFill = symbolFill;
	}

	public Number getSymbolSize() {
		return symbolSize;
	}

	public void setSymbolSize(Number symbolSize) {
		this.symbolSize = symbolSize;
	}

	public Object getSymbolStroke() {
		return symbolStroke;
	}

	public void setSymbolStroke(Object symbolStroke) {
		this.symbolStroke = symbolStroke;
	}

	public Number getSymbolStrokeWidth() {
		return symbolStrokeWidth;
	}

	public void setSymbolStrokeWidth(Number symbolStrokeWidth) {
		this.symbolStrokeWidth = symbolStrokeWidth;
	}

	public Number getSymbolX() {
		return symbolX;
	}

	public void setSymbolX(Number symbolX) {
		this.symbolX = symbolX;
	}

	public Number getSymbolY() {
		return symbolY;
	}

	public void setSymbolY(Number symbolY) {
		this.symbolY = symbolY;
	}

	public ButtonOptions(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Object getTheme() {
		return theme;
	}

	public void setTheme(Object theme) {
		this.theme = theme;
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

	public Number getY() {
		return y;
	}

	public void setY(Number y) {
		this.y = y;
	}
}