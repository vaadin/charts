/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts;

import com.vaadin.addon.charts.model.Series;

/**
 * The LegendItemClickEvent class stores information on click events on the
 * charts's legend items.
 */
public class LegendItemClickEvent extends AbstractSeriesEvent {

    /**
     * Constructs a LegendItemClickEvent
     * 
     * @param source
     * @param seriesName
     */
    public LegendItemClickEvent(Chart source, Series series, int seriesItemIndex) {
        super(source, series, seriesItemIndex);
    }



}
