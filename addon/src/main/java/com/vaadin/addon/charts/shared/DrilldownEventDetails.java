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
package com.vaadin.addon.charts.shared;

import java.io.Serializable;

/**
 * Helper class to store and transfer drilldown event details.
 */
public class DrilldownEventDetails implements Serializable {

    private DrilldownPointDetails point;

    public DrilldownEventDetails() {
    }

    public DrilldownPointDetails getPoint() {
        return point;
    }

    public void setPoint(DrilldownPointDetails point) {
        this.point = point;
    }

}
