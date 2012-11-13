package com.vaadin.addon.charts;

public class ChartClickEvent extends com.vaadin.ui.Component.Event {

    private Double xAxisValue;
    private Double yAxisValue;

    public ChartClickEvent(Chart source, double xAxis, double yAxis) {
        super(source);
        xAxisValue = xAxis;
        yAxisValue = yAxis;
    }

    public double getxAxisValue() {
        return xAxisValue;
    }

    public double getyAxisValue() {
        return yAxisValue;
    }

}
