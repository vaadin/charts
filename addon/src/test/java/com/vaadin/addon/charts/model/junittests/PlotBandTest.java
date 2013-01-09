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
    private PlotBand plotBand2;
    private PlotBand plotBand3;

    @Before
    public void setup() {
        Chart chart = new Chart(ChartType.PIE);
        Configuration conf = chart.getConfiguration();
        axis = new XAxis();
        conf.addxAxis(axis);

        plotBand1 = new PlotBand(1, 2, SolidColor.ALICEBLUE);
        plotBand2 = new PlotBand(2, 3, SolidColor.ANTIQUEWHITE);
        plotBand3 = new PlotBand(3, 4, SolidColor.AQUA);

        axis.setPlotBands(plotBand1, plotBand2, plotBand3);
    }

    @Test
    public void testRemovePlotBand() {
        assertTrue(axis.getPlotBands().size() == 3);
        axis.removePlotBand(plotBand1);
        assertTrue(axis.getPlotBands().size() == 2);
        axis.removePlotBand(plotBand2);
        assertTrue(axis.getPlotBands().size() == 1);
        axis.removePlotBand(plotBand2);
        assertTrue(axis.getPlotBands().get(0).getColor() == SolidColor.AQUA);
        axis.removePlotBand(plotBand3);
        assertTrue(axis.getPlotBands().size() == 0);
    }

}
