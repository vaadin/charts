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
package com.vaadin.addon.charts.client.ui;

import com.vaadin.addon.charts.shared.DrilldownEventDetails;
import com.vaadin.addon.charts.shared.DrilldownPointDetails;

/**
 * Helper class for constructing a DrilldownEventDetails object
 */
public class DrilldownEventDetailsBuilder {

    /**
     * Creates a {@link DrilldownEventDetails} based on the
     * {@link ChartDrilldownEvent}
     * 
     * @param event
     * @param widget
     * @return
     */
    public static DrilldownEventDetails buildDrilldownEventDetails(
            ChartDrilldownEvent event, HighchartWidget widget) {
        DrilldownEventDetails result = new DrilldownEventDetails();
        result.setPoint(buildDrilldownPoint(event.getPoint(), widget));

        return result;
    }

    private static DrilldownPointDetails buildDrilldownPoint(
            HighchartPoint point, HighchartWidget widget) {
        DrilldownPointDetails result = new DrilldownPointDetails();
        if (point.getId() != null) {
            result.setId(point.getId());
        }

        HighchartSeries series = point.getSeries();

        int pointIndex = series.indexOf(point);
        result.setIndex(pointIndex);

        int seriesIndex = widget.getSeriesIndex(series);
        result.setSeriesIndex(seriesIndex);

        return result;
    }

}
