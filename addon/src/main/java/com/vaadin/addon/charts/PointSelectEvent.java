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

/**
 * The PointClickEvent class stores data for select events on the points of the
 * chart.
 */
public class PointSelectEvent extends AbstractPointEvent {
    public PointSelectEvent(Chart source, Series series, String category,
        int pointIndex) {
        super(source, series, category, pointIndex);
    }
}
