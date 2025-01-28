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

import com.vaadin.addon.charts.model.SeriesTooltip;
import com.vaadin.addon.charts.model.Tooltip;

/**
 * Tests that {@link Tooltip} and {@link SeriesTooltip} configuration options
 * are serialized correctly as JSON
 *
 */
public class TooltipJSONSerializationTest {

    @Test
    public void toJSON_TooltipWithPointFormatter_serializedAsFunction() {

        Tooltip tooltip = new Tooltip(true);
        tooltip.setPointFormatter("this.y units");

        String json = toJSON(tooltip);

        String expected = "{\"enabled\":true,\"_fn_pointFormatter\":\"this.y units\"}";

        assertEquals(expected, json);
    }

    @Test
    public void toJSON_SeriesTooltipWithPointFormatter_serializedAsFunction() {

        SeriesTooltip tooltip = new SeriesTooltip();
        tooltip.setPointFormatter("this.y units");

        String json = toJSON(tooltip);

        String expected = "{\"_fn_pointFormatter\":\"this.y units\"}";

        assertEquals(expected, json);
    }

}
