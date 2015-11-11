package com.vaadin.addon.charts.model;
public class Label extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private String align;
	private Number rotation;
	private Object style;
	private String text;
	private String textAlign;
	private Boolean useHTML;
	private String verticalAlign;
	private Number x;
	private Number y;

	public Label() {
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public Number getRotation() {
		return rotation;
	}

	public void setRotation(Number rotation) {
		this.rotation = rotation;
	}

	public Object getStyle() {
		return style;
	}

	public void setStyle(Object style) {
		this.style = style;
	}

	public Label(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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