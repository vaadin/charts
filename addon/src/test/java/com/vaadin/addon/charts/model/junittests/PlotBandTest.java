package com.vaadin.addon.charts.model.junittests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.PlotBands;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.style.SolidColor;

public class PlotBandTest {

    private XAxis axis;
    private PlotBands plotBand1;
    private PlotBands plotBand2;
    private PlotBands plotBand3;

    @Before
    public void setup() {
        Chart chart = new Chart(ChartType.PIE);
        Configuration conf = chart.getConfiguration();
        axis = new XAxis();
        conf.addxAxis(axis);

        plotBand1 = new PlotBands();
        plotBand1.setFrom(1);
        plotBand1.setTo(2);
        plotBand1.setColor(SolidColor.ALICEBLUE);
        plotBand2 = new PlotBands();
        plotBand2.setFrom(2);
        plotBand2.setTo(3);
        plotBand2.setColor(SolidColor.ANTIQUEWHITE);
        plotBand3 = new PlotBands();
        plotBand3.setFrom(3);
        plotBand3.setTo(4);
        plotBand3.setColor(SolidColor.AQUA);

        List<PlotBands> plotbands = new ArrayList<PlotBands>();
        plotbands.add(plotBand1);
        plotbands.add(plotBand2);
        plotbands.add(plotBand3);
        axis.setPlotBands(new PlotBands[] { plotBand1, plotBand2, plotBand3 });
    }

    // FIXME missing generated API axis.removePlotBand
    // @Test
    // public void testRemovePlotBand() {
    // assertTrue(axis.getPlotBands().length == 3);
    // axis.removePlotBand(plotBand1);
    // assertTrue(axis.getPlotBands().length == 2);
    // axis.removePlotBand(plotBand1);
    // assertTrue(axis.getPlotBands().length == 1);
    // axis.removePlotBand(plotBand1);
    // assertTrue(axis.getPlotBands()[0].getColor() == SolidColor.AQUA);
    // axis.removePlotBand(plotBand1);
    //
    // assertTrue(axis.getPlotBands().length == 0);
    // }
}
