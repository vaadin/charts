/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
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
