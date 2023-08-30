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

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.server.Sizeable.Unit;

/**
 * Tests that {@link YAxis} size configuration options is serialized correctly
 *
 */
public class AxisJSONSerializationTest {

    @Test
    public void toJSON_heightSetAsPercentage_heightSerializedAsString() {

        YAxis axis = new YAxis();
        axis.setHeight(50, Unit.PERCENTAGE);

        String json = toJSON(axis);
        String expected = "{\"height\":\"50.0%\"}";

        assertEquals(expected, json);
    }

    @Test
    public void toJSON_heightSetAsPixels_heightSerializedAsNumber() {

        YAxis axis = new YAxis();
        axis.setHeight(50, Unit.PIXELS);

        String json = toJSON(axis);
        String expected = "{\"height\":50.0}";

        assertEquals(expected, json);
    }

}
