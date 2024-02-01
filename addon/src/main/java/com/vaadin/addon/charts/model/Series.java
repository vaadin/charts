/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model;

/**
 * Series interface for all kinds of Series
 */
public interface Series {

    /**
     * @see #setName(String)
     * @return The name of the series.
     */
    String getName();

    /**
     * Sets the name of the series as shown in the legend, tooltip etc. Defaults
     * to "".
     * 
     * @param name
     */
    void setName(String name);

    /**
     * @see #setZIndex(String)
     * @return the visual z index of the series.
     */
    Number getZIndex();

    /**
     * Define the visual z index of the series. With no z index, the series
     * defined last are on top. With a z index, the series with the highest z
     * index is on top.
     * 
     * @param zIndex
     *            to set to the series
     */
    void setZIndex(Number zIndex);

    /**
     * Sets the configuration to which this series is linked.
     * 
     * @param configuration
     */
    void setConfiguration(Configuration configuration);

    /**
     * Gets the plot options related to this specific series. This is needed
     * e.g. in combined charts.
     *
     * @return
     */
    AbstractPlotOptions getPlotOptions();

    /**
     * Sets the plot options for this specific series. The type of the plot
     * options also explicitly sets the chart type used when rendering this
     * particular series. If plot options is null, the component wide chart type
     * is used.
     * <p>
     * Options that are not defined at this level will be inherited from the
     * chart and theme levels.
     *
     * @param plotOptions
     */
    void setPlotOptions(AbstractPlotOptions plotOptions);

    /**
     * @return the series ID
     */
    String getId();

    /**
     * Sets an id for the series
     * 
     * @param id
     *            new ID to set
     */
    void setId(String id);
}
