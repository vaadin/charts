package com.vaadin.addon.charts;

/*-
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2019 Vaadin Ltd
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
