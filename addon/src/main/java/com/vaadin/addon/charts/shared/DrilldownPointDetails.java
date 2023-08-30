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
 * Helper class to store and transfer drilldown point details.
 */
public class DrilldownPointDetails implements Serializable {

    private String id;
    private int index;
    private int seriesIndex;

    public DrilldownPointDetails() {
    }

    public DrilldownPointDetails(String id, int index, int seriesIndex) {
        this.id = id;
        this.index = index;
        this.seriesIndex = seriesIndex;
    }

    public String getId() {
        return id;
    }

    public void setId(String pointId) {
        id = pointId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int pointIndex) {
        index = pointIndex;
    }

    public int getSeriesIndex() {
        return seriesIndex;
    }

    public void setSeriesIndex(int seriesIndex) {
        this.seriesIndex = seriesIndex;
    }

}
