package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.pointclickevent.PointClickCoordinatesTreeMapChart;

@Ignore("Absolute coordinates affected by Theme change")
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
