package com.vaadin.addon.charts.model.junittests;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vaadin.addon.charts.model.Labels;

/**
 * Tests that {@link Labels} configuration options are serialized correctly as
 * JSON
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

    // FIXME add autorotationlimit
    // @Test
    // public void
    // toString_autoRotationLimitIsSet_labelsSerializedWithAutoRotationLimit() {
    //
    // Labels labels = new Labels(true);
    // labels.setAutoRotationLimit(40);
    //
    // String json = labels.toString();
    // String expected = "{\"enabled\":true,\"autoRotationLimit\":40}";
    //
    // assertEquals(expected, json);
    // }

    @Test
    public void toJSON_paddingIsSet_labelsSerializedWithPadding() {

        Labels labels = new Labels(true);
        labels.setPadding(8);

        String json = toJSON(labels);
        String expected = "{\"enabled\":true,\"padding\":8}";

        assertEquals(expected, json);
    }

    // FIXME test and add allowoverlap
    // @Test
    // public void toString_allowOverlapIsSet_labelsSerializedWithAllowOverlap()
    // {
    //
    // Labels labels = new Labels(true);
    // labels.setAllowOverlap(true);
    //
    // String json = labels.toString();
    // String expected = "{\"enabled\":true,\"allowOverlap\":true}";
    //
    // assertEquals(expected, json);
    // }

}
