package com.vaadin.addon.charts.model;

/**
 * Alignment of the title relative to the axis values and more generically
 * vertical alignment. Possible values are BOTTOM("bottom"), LOW("low"),
 * MIDDLE("middle"), HIGH("high"), TOP("top")
 */
public enum VerticalAlign implements ChartEnum {
    BOTTOM("bottom"), LOW("low"), MIDDLE("middle"), HIGH("high"), TOP("top");

    private final String align;

    private VerticalAlign(String align) {
        this.align = align;
    }

    @Override
    public String toString() {
        return align;
    }
}
