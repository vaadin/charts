package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;
public class Credits extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Boolean enabled;
	private String href;
	private Position position;
	private Style style;
	private String text;

	public Credits() {
	}

	public Credits(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public Credits(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}