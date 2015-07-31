package com.vaadin.addon.charts.model.junittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vaadin.addon.charts.model.PlotOptionsHeatMap;
import com.vaadin.addon.charts.model.PlotOptionsTreeMap;

/**
 * Tests that PlotOptions configuration are serialized correctly as JSON
 *
 */
public class PlotOptionsJSONSerializationTest {

    @Test
    public void toString_heatMapTurboThresholdIsSet_plotOptionsSerializedWithTurboThreshold() {

        PlotOptionsHeatMap plotOptions = new PlotOptionsHeatMap();
        plotOptions.setTurboThreshold(0);

        String json = plotOptions.toString();
        String expected = "{\"turboThreshold\":0}";

        assertEquals(expected, json);
    }

    @Test
    public void toString_treeMapTurboThresholdIsSet_plotOptionsSerializedWithTurboThreshold() {

        PlotOptionsTreeMap plotOptions = new PlotOptionsTreeMap();
        plotOptions.setTurboThreshold(0);

        String json = plotOptions.toString();
        String expected = "{\"turboThreshold\":0}";

        assertEquals(expected, json);
    }

}
