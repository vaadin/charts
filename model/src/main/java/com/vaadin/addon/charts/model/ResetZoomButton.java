package com.vaadin.addon.charts.model;
public class ResetZoomButton extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object position;
	private String relativeTo;
	private Object theme;

	public ResetZoomButton() {
	}

	public Object getPosition() {
		return position;
	}

	public void setPosition(Object position) {
		this.position = position;
	}

	public String getRelativeTo() {
		return relativeTo;
	}

	public void setRelativeTo(String relativeTo) {
		this.relativeTo = relativeTo;
	}

	public Object getTheme() {
		return theme;
	}

	public void setTheme(Object theme) {
		this.theme = theme;
	}
}