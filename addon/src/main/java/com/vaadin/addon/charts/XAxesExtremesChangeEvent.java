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
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.ui.Component;

/**
 * The XAxesExtremesChangeEvent class stores information on changed axis extremes
 */
public class XAxesExtremesChangeEvent extends AbstractAxesExtremesChangeEvent{

    public XAxesExtremesChangeEvent(Chart chart, XAxis axis, Number minimum,
        Number maximum) {
        super(chart, axis, minimum, maximum);
    }

    @Override
    public XAxis getAxis() {
        return (XAxis) super.getAxis();
    }
}
