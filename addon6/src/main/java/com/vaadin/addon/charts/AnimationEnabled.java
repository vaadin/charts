package com.vaadin.addon.charts;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;

class AnimationEnabled implements PartialChange {

	private boolean enabled;

	public AnimationEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void paint(Chart chart, PaintTarget target) throws PaintException {
		target.addAttribute("animationEnabled", enabled);
	}

}