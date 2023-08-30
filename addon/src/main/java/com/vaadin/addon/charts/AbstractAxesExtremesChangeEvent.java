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

import com.vaadin.addon.charts.model.Axis;
import com.vaadin.ui.Component;

public abstract class AbstractAxesExtremesChangeEvent extends Component.Event {

    private final Axis axis;
    private final Number minimum;
    private final Number maximum;

    public AbstractAxesExtremesChangeEvent(Chart chart, Axis axis, Number minimum,
        Number maximum) {
        super(chart);
        this.axis = axis;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public Number getMinimum() {
        return minimum;
    }

    public Number getMaximum() {
        return maximum;
    }

    public Axis getAxis() {
        return axis;
    }
}
