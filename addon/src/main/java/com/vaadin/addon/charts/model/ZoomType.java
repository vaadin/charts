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
 * Parameters for in what dimensions the user can zoom by dragging the mouse.
 */
public enum ZoomType implements ChartEnum {
    X("x"), Y("y"), XY("xy");

    private final String zoomType;

    private ZoomType(String zoomType) {
        this.zoomType = zoomType;
    }

    @Override
    public String toString() {
        return zoomType;
    }
}
