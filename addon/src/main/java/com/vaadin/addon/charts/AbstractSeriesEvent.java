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
import com.vaadin.ui.Component;

public abstract class AbstractSeriesEvent
    extends com.vaadin.ui.Component.Event {

    private final Series series;
    private final int seriesItemIndex;

    public AbstractSeriesEvent(Component source, Series series,
        int seriesItemIndex) {
        super(source);
        this.series = series;
        this.seriesItemIndex = seriesItemIndex;
    }

    /**
     * @return the series where the event occurred
     */
    public Series getSeries() {
        return series;
    }

    /**
     * @return the item index of the series
     */
    public int getSeriesItemIndex() {
        return seriesItemIndex;
    }

}
