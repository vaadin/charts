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
     * @see #setType(ChartType)
     * @return
     */
    public ChartType getType();

    /**
     * The type of series. Can be one of AREA, LINE, SPLINE(, AREASPLINE,
     * COLUMN, BAR, PIE, SCATTER, GAUGE, AREARANGE, COLUMNRANGE,
     * AREASPLINERANGE. Defaults to LINE.
     * 
     * @param type
     */
    public void setType(ChartType type);

    /**
     * Sets the configuration to which this series is linked.
     * 
     * @param configuration
     */
    public void setConfiguration(Configuration configuration);

}
