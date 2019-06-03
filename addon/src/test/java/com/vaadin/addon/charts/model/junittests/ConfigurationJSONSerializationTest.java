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
import com.vaadin.addon.charts.events.SeriesChangedEvent;
import com.vaadin.addon.charts.events.SeriesStateEvent;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Lang;
import com.vaadin.addon.charts.model.ListSeries;
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

            @Override
            public void resetZoom(boolean redraw, boolean animate) {

            }

            @Override
            public void seriesChanged(SeriesChangedEvent event) {

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
        axis.setConfiguration(conf);
        conf.addyAxis(axis);
        assertEquals(
                "{\"yAxis\":{\"axisIndex\":0},\"plotOptions\":{},\"series\":[],\"exporting\":{\"enabled\":false}}",
                toJSON(conf));
    }

    @Test
    public void configurationJSONSerialization_configurationSerializedWithLang_langConfigurationSerialized() {
        Configuration conf = new Configuration();
        Lang lang = new Lang();
        lang.setNoData("No data for chart");
        conf.setLang(lang);
        assertEquals(
          "{\"plotOptions\":{},\"series\":[],\"exporting\":{\"enabled\":false},\"lang\":{\"noData\":\"No data for chart\"}}",
          toJSON(conf));
    }

    @Test
    public void configurationJSONSerialization_configurationSerializedWithoutLang_langConfigurationNotSerialized() {
        Configuration conf = new Configuration();
        assertEquals(
          "{\"plotOptions\":{},\"series\":[],\"exporting\":{\"enabled\":false}}",
          toJSON(conf));
    }

    @Test
    public void configurationJSONSerialization_setSeriesAddSeries_noExceptions() {
        Configuration conf = new Configuration();
        conf.setSeries(new ListSeries(),new ListSeries());
        conf.addSeries(new ListSeries());
        assertEquals(
          "{\"plotOptions\":{},\"series\":[{\"data\":[]},{\"data\":[]},{\"data\":[]}],\"exporting\":{\"enabled\":false}}",
          toJSON(conf));
    }
}
