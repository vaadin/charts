package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Alternative AxisContainer to allow multiple axes
 */
public class AxisList extends AbstractConfigurationObject {

    private List<Axis> axesList = new ArrayList<Axis>();

    /**
     * @return the number of axes in the list
     */
    public int getNumberOfAxes() {
        return axesList.size();
    }

    /**
     * Finds the axis at the given index
     * 
     * @param index
     *            The index of the axis
     * @return The axis at the given index
     */
    public Axis getAxis(int index) {
        return axesList.get(index);
    }

    /**
     * @return The list of axes. Used only for serialization.
     */
    public List<Axis> getAxes() {
        return axesList;
    }

    /**
     * Adds a new axis to the list
     * 
     * @param axis
     *            The axis to add
     */
    public void addAxis(Axis axis) {
        axesList.add(axis);
        updateIndexes();
    }

    private void updateIndexes() {
        for (int i = 0; i < axesList.size(); i++) {
            Axis axis = axesList.get(i);
            axis.setAxisIndex(i);
        }
    }

    /**
     * Removes an axis from the list
     * 
     * @param axis
     *            The axis to remove
     */
    public void removeAxis(Axis axis) {
        axesList.remove(axis);
        updateIndexes();
    }
}
