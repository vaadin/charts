package com.vaadin.addon.charts.model;

/**
 * Decides in what dimensions the user can zoom by dragging the mouse. Can be
 * one of X("x"), Y("y"), XY("xy"). Defaults to "".
 * 
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
