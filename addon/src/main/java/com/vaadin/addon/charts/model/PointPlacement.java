package com.vaadin.addon.charts.model;

/**
 * Possible values: null, ON("on"), BETWEEN("between").
 * 
 * In a column chart, when pointPlacement is "on", the point will not create any
 * padding of the X axis. In a polar column chart this means that the first
 * column points directly north. If the pointPlacement is "between", the columns
 * will be laid out between ticks. This is useful for example for visualizing an
 * amount between two points in time or in a certain sector of a polar chart.
 * 
 * Defaults to null in Cartesian charts, "between" in polar charts.
 */
public enum PointPlacement implements ChartEnum {
    ON("on"), BETWEEN("between");

    private final String pointPlacement;

    private PointPlacement(String pointPlacement) {
        this.pointPlacement = pointPlacement;
    }

    @Override
    public String toString() {
        return pointPlacement;
    }
}
