package com.vaadin.addon.charts.testbenchtests;

import com.vaadin.addon.charts.examples.pointclickevent.PointClickCoordinatesTreeMapChart;

public class PointClickCoordinatesTreeMapChartTBTest
        extends AbstractPointClickCoordinatesTest {

    @Override
    protected String getTestViewName() {
        return PointClickCoordinatesTreeMapChart.class.getSimpleName();
    }

    @Override
    protected void assertChartClickEventCoordinates() {
        // All coordinates within TreeMap cause a PointClickEvent
    }
}
