package com.vaadin.addon.charts;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;

class SeriesEnabled implements PartialChange {

    private int seriesIndex;
    private boolean enabled;

    public SeriesEnabled(int seriesIndex, boolean enabled) {
        this.seriesIndex = seriesIndex;
        this.enabled = enabled;
    }

    public void paint(Chart chart, PaintTarget target) throws PaintException {
        target.addAttribute("enabledSeriesIndex", seriesIndex);
        target.addAttribute("enabled", enabled);
    }

}