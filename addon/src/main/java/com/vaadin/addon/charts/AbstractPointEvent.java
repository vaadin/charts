package com.vaadin.addon.charts;

/*
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
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

import com.vaadin.addon.charts.model.Series;

public abstract class AbstractPointEvent extends com.vaadin.ui.Component.Event {

    private final String category;
    private final Series series;
    private int pointIndex;

    public AbstractPointEvent(Chart source, Series series, String category,
        int pointIndex) {
        super(source);
        this.series = series;
        this.category = category;
        this.pointIndex = pointIndex;
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

    /**
     * @return the index of the point in its series that was clicked
     */
    public int getPointIndex() {
        return pointIndex;
    }


}
