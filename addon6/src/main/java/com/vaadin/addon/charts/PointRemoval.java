package com.vaadin.addon.charts;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;

class PointRemoval implements PartialChange {

    private int seriesIndex;
    private int pointIndex;

    public PointRemoval(int pi, int seriesIndex) {
        this.seriesIndex = seriesIndex;
        this.pointIndex = pi;
    }

    public void paint(Chart chart, PaintTarget target) throws PaintException {
        target.addAttribute("removedPointIndex", pointIndex);
        target.addAttribute("seriesIndex", seriesIndex);
    }

}