package com.vaadin.addon.charts.model;

/**
 * Options for solid gauge.
 * 
 * @author Vaadin Ltd
 * @since 2.0
 *
 */
public class PlotOptionsSolidGauge extends AbstractPlotOptions {

    private static final long serialVersionUID = 20141107;

    @Override
    public ChartType getChartType() {
        return ChartType.SOLIDGAUGE;
    }

}
