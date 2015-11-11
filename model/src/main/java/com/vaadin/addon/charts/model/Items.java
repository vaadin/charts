package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;
public class Items extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private String html;
	private Style style;

	public Items() {
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}
}