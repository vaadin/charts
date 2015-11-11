package com.vaadin.addon.charts.model;
public class ContextButton extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private String align;
	private Boolean enabled;
	private Number height;
	private Object[] menuItems;
	private Object onclick;
	private String symbol;
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
	private Number x;
	private Number y;

	public ContextButton() {
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public ContextButton(Boolean enabled) {
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

	public Object[] getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(Object[] menuItems) {
		this.menuItems = menuItems;
	}

	public Object getOnclick() {
		return onclick;
	}

	public void setOnclick(Object onclick) {
		this.onclick = onclick;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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

	public ContextButton(String text) {
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