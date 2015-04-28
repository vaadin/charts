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
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to store and transfer drilldown event details.
 */
public class DrilldownEventDetails implements Serializable {

    private DrilldownPointDetails point;
    private List<DrilldownPointDetails> points = new ArrayList<DrilldownPointDetails>();
    private boolean category;
    private boolean hasDrilldownSeries;

    public DrilldownEventDetails() {
    }

    public boolean isCategory() {
        return category;
    }

    public void setCategory(boolean category) {
        this.category = category;
    }

    public boolean isHasDrilldownSeries() {
        return hasDrilldownSeries;
    }

    public void setHasDrilldownSeries(boolean hasDrilldownSeries) {
        this.hasDrilldownSeries = hasDrilldownSeries;
    }

    public DrilldownPointDetails getPoint() {
        return point;
    }

    public void setPoint(DrilldownPointDetails point) {
        this.point = point;
    }

    public List<DrilldownPointDetails> getPoints() {
        return points;
    }

    public void setPoints(List<DrilldownPointDetails> points) {
        this.points = points;
    }

    public void addPoint(DrilldownPointDetails pointToAdd) {
        points.add(pointToAdd);
    }
}
