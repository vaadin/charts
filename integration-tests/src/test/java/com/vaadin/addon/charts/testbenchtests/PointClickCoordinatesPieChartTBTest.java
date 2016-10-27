package com.vaadin.addon.charts.testbenchtests;

import org.junit.Ignore;

import com.vaadin.addon.charts.examples.pointclickevent.PointClickCoordinatesPieChart;

@Ignore("Absolute coordinates affected by Theme change")
public class PointClickCoordinatesPieChartTBTest
        extends AbstractPointClickCoordinatesTest {

    @Override
    protected String getTestViewName() {
        return PointClickCoordinatesPieChart.class.getSimpleName();
    }

}
