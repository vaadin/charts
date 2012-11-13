package com.vaadin.addon.charts.model.style;

import com.vaadin.addon.charts.model.ChartEnum;

public enum TickIntervalStyle implements ChartEnum {

    AUTO("auto"), NONE("");

    private TickIntervalStyle(String type) {
        this.type = type;
    }

    private String type;

    @Override
    public String toString() {
        return type;
    }
}
