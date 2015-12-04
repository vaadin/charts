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
