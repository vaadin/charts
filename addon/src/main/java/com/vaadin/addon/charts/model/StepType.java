/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model;

/**
 * Defines different step line types. Configurable in {@link PlotOptionsLine}.
 */
public enum StepType implements ChartEnum {
    RIGHT("right"), CENTER("center"), LEFT("left"), NONE("");

    private String highchartNAme;

    private StepType(String n) {
        this.highchartNAme = n;
    }

    public String toString() {
        return highchartNAme;
    }

}
