package com.vaadin.addon.charts.model;

/**
 * DataSeriesItem that can hold also Z value. Used in e.g. bubble charts.
 */
public class DataSeriesItem3d extends DataSeriesItem {

    private Number z;

    /**
     * Sets the z value of the point.
     * 
     * @param z
     */
    public void setZ(Number z) {
        this.z = z;
        makeCustomized();
    }

    /**
     * @return the z value
     */
    public Number getZ() {
        return z;
    }
}
