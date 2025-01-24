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
 * What frame the button should be placed related to. Can be either "plot" or
 * "chart". Defaults to plot.
 */
public enum ResetZoomButtonRelativeTo implements ChartEnum {

    PLOT("plot"), CHART("chart");

    ResetZoomButtonRelativeTo(String frame) {
        this.frame = frame;
    }

    private String frame;

    @Override
    public String toString() {
        return frame;
    }
}
