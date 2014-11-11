package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

/**
 * Series interface for all kinds of Series
 */
public interface Series {

    /**
     * @see #setName(String)
     * @return The name of the series.
     */
    public String getName();

    /**
     * Sets the name of the series as shown in the legend, tooltip etc. Defaults
     * to "".
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
