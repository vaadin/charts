package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
public class Point extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Events events;

	public Point() {
	}

	public Events getEvents() {
		return events;
	}

	public void setEvents(Events events) {
		this.events = events;
	}
}