/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts;

import com.vaadin.addon.charts.model.Series;
import com.vaadin.ui.Component;

/**
 * ShowSeriesEvent when the series is shown
 */
public class SeriesShowEvent extends AbstractSeriesEvent {

    public SeriesShowEvent(Component source, Series series, int seriesItemIndex) {
        super(source, series, seriesItemIndex);
    }

}
