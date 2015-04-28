package com.vaadin.addon.charts;

import java.util.List;

import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.shared.DrilldownEventDetails;
import com.vaadin.addon.charts.shared.DrilldownPointDetails;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
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

/**
 * The ChartDrilldownEvent class stores information on click events in drilldown
 * points
 */
public class ChartDrilldownEvent extends com.vaadin.ui.Component.Event {

    private final Series series;
    private final DrilldownEventDetails details;

    /**
     * Construct a ChartDrilldownEvent
     * 
     * @param source
     * @param series
     * @param details
     */
    public ChartDrilldownEvent(Chart source, Series series,
            DrilldownEventDetails details) {
        super(source);
        this.details = details;
        this.series = series;
    }

    /**
     * Returns the point where the click was done. If {@link #isCategory()} is
     * true then the point will be the first point of the {@link #getPoints()}
     * list.
     * 
     * @return
     */
    public DrilldownPointDetails getPoint() {
        return details.getPoint();
    }

    /**
     * Valid when {@link #isCategory()} is true
     * 
     * @return the list with al points in the clicked category
     */
    public List<DrilldownPointDetails> getPoints() {
        return details.getPoints();
    }

    /**
     * @return true if click was done in category with multiple points
     */
    public boolean isCategory() {
        return details.isCategory();
    }

    /**
     * @return true if chart configuration already has a drilldown series for
     *         clicked point
     */
    public boolean isHasDrilldownSeries() {
        return details.isHasDrilldownSeries();
    }

    /**
     * Returns the {@link #getPoint()} series.
     * 
     * @return
     */
    public Series getSeries() {
        return series;
    }
}
