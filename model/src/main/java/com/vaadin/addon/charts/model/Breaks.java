package com.vaadin.addon.charts.model;
public class Breaks extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Number breakSize;
	private Number from;
	private Number repeat;
	private Number to;

	public Breaks() {
	}

	public Number getBreakSize() {
		return breakSize;
	}

	public void setBreakSize(Number breakSize) {
		this.breakSize = breakSize;
	}

	public Number getFrom() {
		return from;
	}

	public void setFrom(Number from) {
		this.from = from;
	}

	public Number getRepeat() {
		return repeat;
	}

	public void setRepeat(Number repeat) {
		this.repeat = repeat;
	}

	public Number getTo() {
		return to;
	}

	public void setTo(Number to) {
		this.to = to;
	}
}