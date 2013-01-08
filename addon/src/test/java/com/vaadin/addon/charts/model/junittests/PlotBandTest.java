package com.vaadin.addon.charts.model.junittests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.PlotBand;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.style.SolidColor;

public class PlotBandTest {

    private XAxis axis;
    private PlotBand plotBand1;

    @Before
    public void setup() {
        Chart chart = new Chart(ChartType.PIE);
        Configuration conf = chart.getConfiguration();
        axis = new XAxis();
        conf.addxAxis(axis);

        plotBand1 = new PlotBand(1, 2, SolidColor.ALICEBLUE);
        PlotBand plotBand2 = new PlotBand(2, 3, SolidColor.ANTIQUEWHITE);
        PlotBand plotBand3 = new PlotBand(3, 4, SolidColor.AQUA);

        plotBand2.setId("xxx");
        plotBand3.setId("xxx");

        axis.setPlotBands(plotBand1, plotBand2, plotBand3);
    }

    @Test
    public void testRemovePlotBand() {
        assertTrue(axis.getPlotBands().size() == 3);
        axis.removePlotBand("xxx");
        assertTrue(axis.getPlotBands().size() == 2);
        axis.removePlotBand("xxx");
        assertTrue(axis.getPlotBands().size() == 1);
        axis.removePlotBand("xxx");
        assertTrue(axis.getPlotBands().get(0).getId() == null);
        assertTrue(axis.getPlotBands().get(0).getColor() == SolidColor.ALICEBLUE);
        plotBand1.setId("xxx");
        axis.removePlotBand("xxx");
        assertTrue(axis.getPlotBands().size() == 0);
    }

}
