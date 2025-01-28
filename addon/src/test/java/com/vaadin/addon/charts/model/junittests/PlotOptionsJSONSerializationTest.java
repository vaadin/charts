/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model.junittests;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vaadin.addon.charts.model.PlotOptionsHeatmap;
import com.vaadin.addon.charts.model.PlotOptionsTreemap;

/**
 * Tests that PlotOptions configuration are serialized correctly as JSON
 *
 */
public class PlotOptionsJSONSerializationTest {

    @Test
    public void toString_heatMapTurboThresholdIsSet_plotOptionsSerializedWithTurboThreshold() {

        PlotOptionsHeatmap plotOptions = new PlotOptionsHeatmap();
        plotOptions.setTurboThreshold(0);

        String json = toJSON(plotOptions);
        String expected = "{\"turboThreshold\":0}";

        assertEquals(expected, json);
    }

    @Test
    public void toString_treeMapTurboThresholdIsSet_plotOptionsSerializedWithTurboThreshold() {

        PlotOptionsTreemap plotOptions = new PlotOptionsTreemap();
        plotOptions.setTurboThreshold(0);

        String json = toJSON(plotOptions);
        String expected = "{\"turboThreshold\":0}";

        assertEquals(expected, json);
    }

}
