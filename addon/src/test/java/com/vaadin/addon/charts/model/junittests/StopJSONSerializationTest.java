package com.vaadin.addon.charts.model.junittests;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vaadin.addon.charts.model.Stop;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;

public class StopJSONSerializationTest {

    @Test
    public void toJSON_axisWithStops_StopsCorrectlySerialized() {
        YAxis yaxis = new YAxis();

        Stop stop1 = new Stop(0.1f, SolidColor.GREEN);
        Stop stop2 = new Stop(0.5f, SolidColor.YELLOW);
        Stop stop3 = new Stop(0.9f, SolidColor.RED);
        yaxis.setStops(stop1, stop2, stop3);

        // stops array should look like this
        // stops: [
        // [0.1, '#55BF3B'], // green
        // [0.5, '#DDDF0D'], // yellow
        // [0.9, '#DF5353'] // red
        // ]

        String expected = "{\"stops\":[[0.1,\"#008000\"],[0.5,\"#FFFF00\"],[0.9,\"#FF0000\"]]}";

        assertEquals(expected, toJSON(yaxis));
    }

}
