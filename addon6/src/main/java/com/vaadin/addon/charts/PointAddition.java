package com.vaadin.addon.charts;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;

class PointAddition implements PartialChange {

    private int seriesIndex;
	private String pointjson;
	private boolean shift;

	public PointAddition(String string, int seriesIndex, boolean shift) {
    	this.pointjson  =string;
    	this.seriesIndex = seriesIndex;
    	this.shift = shift;
	}

	public void paint(Chart chart, PaintTarget target)
            throws PaintException {
        target.addAttribute("addedPoint", pointjson);
        target.addAttribute("seriesIndex", seriesIndex);
        target.addAttribute("shift", shift);
    }

}