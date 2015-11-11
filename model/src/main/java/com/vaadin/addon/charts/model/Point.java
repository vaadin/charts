package com.vaadin.addon.charts.model;
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