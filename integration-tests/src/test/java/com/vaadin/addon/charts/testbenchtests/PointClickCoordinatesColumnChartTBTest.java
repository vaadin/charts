package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.pointclickevent.PointClickCoordinatesColumnChart;

@Ignore("Absolute coordinates affected by Theme change")
public class PointClickCoordinatesColumnChartTBTest
        extends AbstractPointClickCoordinatesTest {

    @Override
    protected String getTestViewName() {
        return PointClickCoordinatesColumnChart.class.getSimpleName();
    }

}
