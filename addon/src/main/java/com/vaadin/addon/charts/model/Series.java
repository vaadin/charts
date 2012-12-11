package com.vaadin.addon.charts.model;

/**
 * Series interface for all kind of Series
 */
public interface Series {

    /**
     * @see #setName(String)
     * @return
     */
    public String getName();

    /**
     * The name of the series as shown in the legend, tooltip etc. Defaults to
     * "".
     * 
     * @param name
     */
    public void setName(String name);

    /**
     * Sets the configuration to which this series is linked.
     * 
     * @param configuration
     */
    public void setConfiguration(Configuration configuration);

}
