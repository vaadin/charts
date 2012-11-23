package com.vaadin.addon.charts;

/**
 * Server side event class for click events on the chart's area
 */
public class ChartClickEvent extends com.vaadin.ui.Component.Event {

    private final Double xAxisValue;
    private final Double yAxisValue;

    /**
     * Construct ChartClickEvent
     * 
     * @param source
     * @param xAxis
     * @param yAxis
     */
    public ChartClickEvent(Chart source, double xAxis, double yAxis) {
        super(source);
        xAxisValue = xAxis;
        yAxisValue = yAxis;
    }

    /**
     * X location of the click
     * 
     * @return
     */
    public double getxAxisValue() {
        return xAxisValue;
    }

    /**
     * Y location of the click
     * 
     * @return
     */
    public double getyAxisValue() {
        return yAxisValue;
    }

}
