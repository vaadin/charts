/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model.junittests;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.PlotBand;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

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

        plotBand1 = new PlotBand();
        plotBand1.setFrom(1);
        plotBand1.setTo(2);
        plotBand1.setColor(SolidColor.ALICEBLUE);
        plotBand2 = new PlotBand();
        plotBand2.setFrom(2);
        plotBand2.setTo(3);
        plotBand2.setColor(SolidColor.ANTIQUEWHITE);
        plotBand3 = new PlotBand();
        plotBand3.setFrom(3);
        plotBand3.setTo(4);
        plotBand3.setColor(SolidColor.AQUA);
        List<PlotBand> plotbands = new ArrayList<PlotBand>();
        plotbands.add(plotBand1);
        plotbands.add(plotBand2);
        plotbands.add(plotBand3);
        axis.setPlotBands( plotBand1, plotBand2, plotBand3 );
    }

    @Test
    public void testRemovePlotBand() {
        Assert.assertTrue(axis.getPlotBands().length == 3);
        axis.removePlotBand(plotBand1);
        Assert.assertTrue(axis.getPlotBands().length == 2);
        axis.removePlotBand(plotBand2);
        Assert.assertTrue(axis.getPlotBands().length == 1);
        axis.removePlotBand(plotBand1);
        Assert.assertTrue(axis.getPlotBands()[0].getColor().equals(SolidColor.AQUA));
        axis.removePlotBand(plotBand3);

        Assert.assertTrue(axis.getPlotBands().length == 0);
    }
}
