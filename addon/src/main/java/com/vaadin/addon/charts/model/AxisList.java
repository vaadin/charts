package com.vaadin.addon.charts.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Alternative AxisContainer to allow multiple axes
 */
public class AxisList extends AbstractConfigurationObject {

    private List<Axis> axesList = new ArrayList<Axis>();

    /**
     * Get number or axes in list
     * 
     * @return
     */
    public int getNumberOfAxes() {
        return axesList.size();
    }

    /**
     * Get Axis with given index
     * 
     * @param index
     *            Index of axis
     * @return Axis with given index
     */
    public Axis getAxis(int index) {
        return axesList.get(index);
    }

    /**
     * Get axes list. Use this only for serialization.
     * 
     * @return
     */
    public List<Axis> getAxes() {
        return axesList;
    }

    /**
     * Add new axis to list
     * 
     * @param axis
     *            Axis added
     */
    public void addAxis(Axis axis) {
        axesList.add(axis);
    }

    /**
     * Add new axis to list
     * 
     * @param axis
     */
    public void removeAxis(Axis axis) {
        axesList.remove(axis);
    }
}
