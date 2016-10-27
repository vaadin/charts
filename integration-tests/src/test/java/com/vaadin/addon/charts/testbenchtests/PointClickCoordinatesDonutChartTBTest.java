package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.pointclickevent.PointClickCoordinatesDonutChart;

@Ignore("Absolute coordinates affected by Theme change")
public class PointClickCoordinatesDonutChartTBTest
        extends AbstractPointClickCoordinatesTest {

    @Override
    protected String getTestViewName() {
        return PointClickCoordinatesDonutChart.class.getSimpleName();
    }

}
