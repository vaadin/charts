package com.vaadin.addon.charts.model.junittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vaadin.addon.charts.model.Labels;

/**
 * Tests that {@link Labels} configuration options are serialized correctly as
 * JSON
 *
 */
public class LabelsSerializationTest {

    @Test
    public void toString_autoRotationLimitIsSet_LabelsSerializedWithAutoRotationLimit() {

        Labels labels = new Labels(true);
        labels.setAutoRotationLimit(40);

        String json = labels.toString();
        String expected = "{\"enabled\":true,\"autoRotationLimit\":40}";

        assertEquals(expected, json);
    }

}
