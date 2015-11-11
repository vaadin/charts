package com.vaadin.addon.charts.model;

import java.io.Serializable;

public interface ChartConfiguration extends Serializable {

    void fireAxesRescaled(Axis axis, Number minimum, Number maximum,
            boolean redraw, boolean animate);
}
