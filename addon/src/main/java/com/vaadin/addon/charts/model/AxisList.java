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
