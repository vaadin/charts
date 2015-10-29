package com.vaadin.addon.charts.model.junittests;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vaadin.addon.charts.events.AxisRescaledEvent;
import com.vaadin.addon.charts.events.ConfigurationChangeListener;
import com.vaadin.addon.charts.events.DataAddedEvent;
import com.vaadin.addon.charts.events.DataRemovedEvent;
import com.vaadin.addon.charts.events.DataUpdatedEvent;
import com.vaadin.addon.charts.events.ItemSlicedEvent;
import com.vaadin.addon.charts.events.SeriesStateEvent;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.YAxis;

/**
 * Tests for the JSON serialization in {@link Configuration}
 *
 */
public class ConfigurationJSONSerializationTest {

    @Test
    public void configurationJSONSerialization_configurationSerializedWithChangeListener_changeListenerNotSerialized() {
        Configuration conf = new Configuration();
        conf.addChangeListener(new ConfigurationChangeListener() {
            @Override
            public void dataAdded(DataAddedEvent event) {

            }

            @Override
            public void drilldownAdded(int seriesIndex, int pointIndex,
                    Series series) {

            }

            @Override
            public void dataRemoved(DataRemovedEvent event) {

            }

            @Override
            public void dataUpdated(DataUpdatedEvent event) {

            }

            @Override
            public void seriesStateChanged(SeriesStateEvent event) {

            }

            @Override
            public void animationChanged(boolean animation) {

            }

            @Override
            public void axisRescaled(AxisRescaledEvent event) {

            }

            @Override
            public void itemSliced(ItemSlicedEvent event) {

            }
        });
        assertEquals(
                "{\"plotOptions\":{},\"series\":[],\"exporting\":{\"enabled\":false}}",
                toJSON(conf));
    }

    @Test
    public void configurationJSONSerialization_configurationSerializedWithYAxis_yAxisConfigurationNotSerialized() {
        Configuration conf = new Configuration();
        YAxis axis = new YAxis();
        // axis.setConfiguration(conf);
        conf.addyAxis(axis);
        assertEquals(
                "{\"yAxis\":[{}],\"plotOptions\":{},\"series\":[],\"exporting\":{\"enabled\":false}}",
                toJSON(conf));
    }
}
