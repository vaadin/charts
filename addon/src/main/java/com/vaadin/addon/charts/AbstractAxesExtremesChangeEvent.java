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
