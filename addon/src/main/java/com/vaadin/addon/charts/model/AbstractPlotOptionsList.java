package com.vaadin.addon.charts.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Alternative AbstractPlotOptionsContainer to allow multiple plot options
 */
public class AbstractPlotOptionsList extends AbstractConfigurationObject {
    private final List<AbstractPlotOptions> plotOptionsList = new ArrayList<AbstractPlotOptions>();

    /**
     * Get number or plotOptions in list
     * 
     * @return
     */
    public int getNumberOfPlotOptions() {
        return plotOptionsList.size();
    }

    /**
     * Get AbstractPlotOptions with given index
     * 
     * @param index
     *            Index of plotOptions
     * @return AbstractPlotOptions with given index
     */
    public AbstractPlotOptions getPlotOptions(int index) {
        return plotOptionsList.get(index);
    }

    /**
     * Get plotOptions list. Use this only for serialization.
     * 
     * @return
     */
    public List<AbstractPlotOptions> getPlotOptions() {
        return plotOptionsList;
    }

    /**
     * Add new plotOptions to list
     * 
     * @param plotOptions
     *            AbstractPlotOptions added
     */
    public void addPlotOptions(AbstractPlotOptions axis) {
        plotOptionsList.add(axis);
    }

    /**
     * Add new plotOptions to list
     * 
     * @param plotOptions
     */
    public void removePlotOptions(AbstractPlotOptions axis) {
        plotOptionsList.remove(axis);
    }

    /**
     * Removes all the plotOptions
     */
    public void clear() {
        plotOptionsList.clear();
    }
}
