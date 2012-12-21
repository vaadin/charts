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
