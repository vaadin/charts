package com.vaadin.addon.charts.model;

/**
 * You can set the cursor to POINTER("pointer") if you have click events
 * attached to the series, to signal to the user that the points and lines can
 * be clicked. Defaults to ''.
 */
public enum Cursor implements ChartEnum {
    POINTER("pointer");

    private String cursor;

    private Cursor(String cursor) {
        this.cursor = cursor;
    }

    public String toString() {
        return cursor;
    }

}
