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
import com.vaadin.addon.charts.shared.MouseEventDetails;

/**
 * The LegendItemClickEvent class stores information on click events on the
 * charts's legend items.
 */
public class LegendItemClickEvent extends AbstractSeriesEvent {

    private MouseEventDetails mouseEventDetails;

    /**
     * Constructs a LegendItemClickEvent
     * 
     * @param source
     * @param seriesName
     * @param mouseEventDetails
     */
    public LegendItemClickEvent(Chart source, Series series, int seriesItemIndex, MouseEventDetails mouseEventDetails) {
        super(source, series, seriesItemIndex);
        this.mouseEventDetails = mouseEventDetails;
    }

    /**
     * Returns the mouse event details for this legend item click
     * @return
     */
    public MouseEventDetails getMouseEventDetails() {
        return mouseEventDetails;
    }
}
