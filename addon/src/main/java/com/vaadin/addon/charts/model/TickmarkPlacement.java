package com.vaadin.addon.charts.model;

/**
 * For categorized axes only. If ON("on") the tick mark is placed in the center
 * of the category, if BETWEEN("between") the tick mark is placed between
 * categories. Defaults to BETWEEN("between").
 */
public enum TickmarkPlacement implements ChartEnum {
    ON("on"), BETWEEN("between");

    private final String tickmarkPlacement;

    private TickmarkPlacement(String tickmarkPlacement) {
        this.tickmarkPlacement = tickmarkPlacement;
    }

    @Override
    public String toString() {
        return tickmarkPlacement;
    }
}
