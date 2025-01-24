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
package com.vaadin.addon.charts.model;

import java.io.Serializable;

public interface ChartConfiguration extends Serializable {

    void fireAxesRescaled(Axis axis, Number minimum, Number maximum,
            boolean redraw, boolean animate);
}
