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

import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.YAxis;

/**
 * The YAxesExtremesChangedEvent class stores information on changed axis extremes
 */
public class YAxesExtremesChangeEvent extends AbstractAxesExtremesChangeEvent{

    public YAxesExtremesChangeEvent(Chart chart, YAxis axis, Number minimum,
        Number maximum) {
        super(chart, axis, minimum, maximum);
    }

    @Override
    public YAxis getAxis() {
        return (YAxis) super.getAxis();
    }
}
