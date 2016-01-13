package com.vaadin.addon.charts.model.junittests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.util.ChartSerialization;
import org.junit.Test;

import java.io.IOException;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;

/**
 * Tests that {@link Labels} and {@link DataLabels} configuration options are
 * serialized correctly as JSON
 *
 */
public class LabelsJSONSerializationTest {

    @Test
    public void toJSON_enabledSet_labelsSerializedWithEnabled() {

        Labels labels = new Labels(false);

        String json = toJSON(labels);
        String expected = "{\"enabled\":false}";

        assertEquals(expected, json);
    }

    @Test
    public void toJSON_autoRotationLimitIsSet_labelsSerializedWithAutoRotationLimit() throws IOException {

        Labels labels = new Labels(true);
        labels.setAutoRotationLimit(40);

        ObjectMapper om = ChartSerialization.createObjectMapper();

        String json = toJSON(labels);
        Labels fromJson = om.readValue(json, Labels.class);

        assertEquals(40,fromJson.getAutoRotationLimit());
    }

    @Test
    public void toJSON_paddingIsSet_labelsSerializedWithPadding() throws IOException {

        Labels labels = new Labels(true);
        labels.setPadding(8);

        ObjectMapper om = ChartSerialization.createObjectMapper();

        String json = toJSON(labels);
        Labels fromJson = om.readValue(json, Labels.class);

        assertEquals(8,fromJson.getPadding());
    }

    @Test
    public void toJSON_allowOverlapIsSet_labelsSerializedWithAllowOverlap() throws IOException {

        DataLabels labels = new DataLabels(true);
        labels.setAllowOverlap(true);
        ObjectMapper om = ChartSerialization.createObjectMapper();

        String json = toJSON(labels);
        DataLabels fromJson = om.readValue(json, DataLabels.class);

        assertEquals(true,fromJson.getAllowOverlap());
    }

}
