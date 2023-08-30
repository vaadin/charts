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

import com.vaadin.addon.charts.model.style.ButtonTheme;

/**
 * Tests that {@link ButtonTheme} configuration options are serialized correctly
 * as JSON
 *
 */
public class ButtonThemeJSONSerializationTest {

    @Test
    public void toJSON_widthIsNull_themeSerializedWithNullWidth() {

        ButtonTheme buttonTheme = new ButtonTheme();
        buttonTheme.setWidth(null);

        String json = toJSON(buttonTheme);
        String expected = "{\"width\":null}";

        assertEquals(expected, json);
    }

    @Test
    public void toJSON_widthIsDefault_themeSerializedWithoutWidth() {

        ButtonTheme buttonTheme = new ButtonTheme();

        String json = toJSON(buttonTheme);
        String expected = "{}";

        assertEquals(expected, json);
    }

    @Test
    public void toJSON_strokeWidthIsSet_themeSerializedWithDashInProperty() {

        ButtonTheme buttonTheme = new ButtonTheme();
        buttonTheme.setStrokeWidth(4);

        String json = toJSON(buttonTheme);
        String expected = "{\"stroke-width\":4}";

        assertEquals(expected, json);
    }


}
