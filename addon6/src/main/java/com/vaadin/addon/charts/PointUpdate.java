package com.vaadin.addon.charts;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;

class PointUpdate implements PartialChange {

	private int seriesIndex;
	private int pointIndex;
	private String pointjson;

	public PointUpdate(String string, int pointIndex, int seriesIndex) {
		this.pointjson = string;
		this.seriesIndex = seriesIndex;
		this.pointIndex = pointIndex;
	}

	public PointUpdate(Number value, int pointIndex, int seriesIndex) {
		pointjson = "{y:"+value+"}";
		this.seriesIndex = seriesIndex;
		this.pointIndex = pointIndex;
	}

	public void paint(Chart chart, PaintTarget target) throws PaintException {
		target.addAttribute("updatedPoint", pointjson);
		target.addAttribute("seriesIndex", seriesIndex);
		target.addAttribute("pointIndex", pointIndex);
	}

}