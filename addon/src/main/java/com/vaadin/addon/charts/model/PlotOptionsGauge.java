package com.vaadin.addon.charts.model;

/**
 * Plot options that are specific for ChartType.GAUGE charts
 */
public class PlotOptionsGauge extends AbstractPlotOptions {
    private Dial dial;

    /**
     * Options for the dial or arrow pointer of the gauge.
     * 
     * @param dial
     */
    public void setDial(Dial dial) {
        this.dial = dial;
    }

    /**
     * @see #setDial(Dial)
     * @return
     */
    public Dial getDial() {
        return dial;
    }

    @Override
    public ChartType getChartType() {
        return ChartType.GAUGE;
    }
}
