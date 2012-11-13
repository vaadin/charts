package com.vaadin.addon.charts.model;

/**
 * The layout of the legend items. Can be one of HORIZONTAL("horizontal") or
 * VERTICAL("vertical"). Defaults to HORIZONTAL.
 */
public enum LayoutDirection implements ChartEnum {

    VERTICAL("vertical"), HORIZONTAL("horizontal");

    private LayoutDirection(String type) {
        this.type = type;
    }

    private String type;

    @Override
    public String toString() {
        return type;
    }
}
