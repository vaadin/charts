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
 * Series for range type data
 */
@SuppressWarnings("serial")
public class RangeSeries extends AbstractSeries {
    private Number[][] data;

    private Number yAxis;

    public RangeSeries() {
    }

    /**
     * Constructs a RangeSeries with the given name
     * 
     * @param name
     */
    public RangeSeries(String name) {
        super(name);
    }

    /**
     * Constructs a RangeSeries with the given values
     * 
     * @param values
     */
    public RangeSeries(Number[]... values) {
        data = values;
    }

    /**
     * Constructs a RangeSeries with the given name and values
     * 
     * @param name
     * @param values
     */
    public RangeSeries(String name, Number[]... values) {
        this(name);
        data = values;
    }

    /**
     * @return The numeric data of this series.
     */
    public Number[][] getData() {
        return data;
    }

    /**
     * Sets the numeric data for this series.
     * 
     * @param data
     */
    public void setData(Number[]... data) {
        this.data = data;
    }

    /**
     * @see #setyAxis(Number)
     * @return The index of the Y-axis that this series is bound to or null if
     *         not defined
     */
    public Number getyAxis() {
        return yAxis;
    }

    /**
     * Sets the index of the Y-axis that this series should be bound to. When
     * using dual or multiple Y-axes, this number defines which {@link YAxis}
     * the particular series is connected to. It refers to the index of the axis
     * in the Y-axis array, with 0 being the first. Defaults to 0.
     * 
     * @param yAxis
     */
    public void setyAxis(Number yAxis) {
        this.yAxis = yAxis;
    }
}
