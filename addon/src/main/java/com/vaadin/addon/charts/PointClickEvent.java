package com.vaadin.addon.charts;

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
    private final String seriesName;
    private final String category;

    /**
     * Construct a PointClickEvent
     * 
     * @param source
     * @param x
     * @param y
     * @param seriesName
     * @param category
     */
    public PointClickEvent(Chart source, double x, double y, String seriesName,
            String category) {
        super(source);
        this.x = x;
        this.y = y;
        this.seriesName = seriesName;
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
     * @return the name of the series containing the point that was clicked.
     */
    public String getSeriesName() {
        return seriesName;
    }

    /**
     * @return the name of the category for the point that was clicked.
     */
    public String getCategory() {
        return category;
    }

}
