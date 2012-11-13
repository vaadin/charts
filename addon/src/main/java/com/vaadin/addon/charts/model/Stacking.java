package com.vaadin.addon.charts.model;

/**
 * Whether to stack the values of each series on top of each other. Possible
 * values are null to disable, NORMAL("normal") to stack by value or
 * PERCENT("percent"). Defaults to null.
 */
public enum Stacking implements ChartEnum {

    NORMAL("normal"), PERCENT("percent");

    private Stacking(String type) {
        this.type = type;
    }

    private String type;

    @Override
    public String toString() {
        return type;
    }
}
