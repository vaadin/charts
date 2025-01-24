/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
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
 * The PointClickEvent class stores data for unselect events on the points of the
 * chart.
 */
public class PointUnselectEvent extends AbstractPointEvent {

    public PointUnselectEvent(Chart source, Series series, String category,
        int pointIndex) {
        super(source, series, category, pointIndex);
    }
}
