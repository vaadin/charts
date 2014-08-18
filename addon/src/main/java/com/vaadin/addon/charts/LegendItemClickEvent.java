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
 * The LegendItemClickEvent class stores information on click events on the
 * charts's legend items.
 */
public class LegendItemClickEvent extends com.vaadin.ui.Component.Event {

    private Series series;
    private int seriesItemIndex;
    /**
     * Constructs a LegendItemClickEvent
     * 
     * @param source
     * @param seriesName
     */
    public LegendItemClickEvent(Chart source, Series series,
 int seriesItemIndex) {
        super(source);
        this.series = series;
        this.seriesItemIndex = seriesItemIndex;
    }

    /**
     * @return the series which legend item was clicked
     */
    public Series getSeries() {
        return series;
    }

    /**
     * @return the item index of clicked legend item.
     */
    public int getSeriesItemIndex() {
        return seriesItemIndex;
    }

}
