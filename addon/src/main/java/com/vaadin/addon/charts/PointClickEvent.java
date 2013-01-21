package com.vaadin.addon.charts;

import com.vaadin.addon.charts.model.Series;

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
 * The PointClickEvent class stores data for click events on the points of the
 * chart.
 */
public class PointClickEvent extends com.vaadin.ui.Component.Event {

    private final Double x;
    private final Double y;
    private final String category;
    private final Series series;

    /**
     * Construct a PointClickEvent
     * 
     * @param source
     * @param x
     * @param y
     * @param series
     * @param category
     */
    public PointClickEvent(Chart source, double x, double y, Series series,
            String category) {
        super(source);
        this.x = x;
        this.y = y;
        this.series = series;
        this.category = category;
    }

    /**
     * @return the X coordinate of the point that was clicked.
     */
    public double getX() {
        return x;
    }

    /**
     * @return the Y coordinate of the point that was clicked.
     */
    public double getY() {
        return y;
    }
    
    /**
     * @return the series containing the point that was clicked
     */
    public Series getSeries() {
        return series;
    }

    /**
     * @return the name of the category for the point that was clicked.
     */
    public String getCategory() {
        return category;
    }

}
