package com.vaadin.addon.charts.model;

/**
 * The type of axis. Can be one of "linear", "logarithmic" or "datetime". In a
 * datetime axis, the numbers are given in milliseconds, and tick marks are
 * placed on appropriate values like full hours or days. Defaults to LINEAR.
 */
public enum AxisType implements ChartEnum {
    LINEAR("linear"), LOGARITHMIC("logarithmic"), DATETIME("datetime");

    private String type;

    private AxisType(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }

}
