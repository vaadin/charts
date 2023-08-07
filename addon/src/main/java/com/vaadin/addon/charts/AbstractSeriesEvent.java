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
