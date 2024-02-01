/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
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
