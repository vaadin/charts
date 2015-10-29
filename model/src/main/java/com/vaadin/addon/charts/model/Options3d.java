package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
public class Options3d extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Number alpha;
	private Number beta;
	private Number depth;
	private Boolean enabled;
	private Frame frame;
	private Number viewDistance;

	public Options3d() {
	}

	public Number getAlpha() {
		return alpha;
	}

	public void setAlpha(Number alpha) {
		this.alpha = alpha;
	}

	public Number getBeta() {
		return beta;
	}

	public void setBeta(Number beta) {
		this.beta = beta;
	}

	public Number getDepth() {
		return depth;
	}

	public void setDepth(Number depth) {
		this.depth = depth;
	}

	public Options3d(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public Number getViewDistance() {
		return viewDistance;
	}

	public void setViewDistance(Number viewDistance) {
		this.viewDistance = viewDistance;
	}
}