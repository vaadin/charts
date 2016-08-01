package com.vaadin.addon.charts.junittests;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.events.ConfigurationChangeListener;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Drilldown;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.YAxis;

/**
 * Tests for Java serialization of various parts of the Charts model.
 *
 */
public class SerializationTest {

    @Test
    public void serializeCharts_emptyChart_chartSerializedWithoutErrors()
            throws IOException, ClassNotFoundException {
        Chart input = new Chart();

        Chart output = serializeObject(input);

        assertNotNull(output);
    }

    @Test
    public void serializeChart_chartWithFieldValues_chartValuesSerializedCorrectly()
            throws IOException, ClassNotFoundException {
        Chart input = new Chart();
        input.setJsonConfig("foobar");
        Chart output = serializeObject(input);

        assertNotNull(output);
        assertNotNull(output.getJsonConfig());
        assertNotNull(output.getConfiguration());
    }

    @Test
    public void serializeChart_chartConfigurationWithChangeListener_changeListenerSerialized()
            throws IOException, ClassNotFoundException, NoSuchFieldException,
            IllegalAccessException {
        Chart input = new Chart();
        input.beforeClientResponse(true);

        List<ConfigurationChangeListener> changeListenersBeforeSerialization = getPrivateField(
                "changeListeners", input.getConfiguration(),
                Configuration.class);
        assertNotNull("ChangeListeners was null before serialization",
                changeListenersBeforeSerialization);
        assertThat("ChangeListeners was empty before serialization",
                changeListenersBeforeSerialization, is(not(empty())));

        Chart output = serializeObject(input);
        List<ConfigurationChangeListener> changeListenersAfterSerialization = getPrivateField(
                "changeListeners", output.getConfiguration(),
                Configuration.class);
        assertNotNull("ChangeListeners was null after serialization",
                changeListenersAfterSerialization);
        assertThat("ChangeListeners was empty after serialization",
                changeListenersAfterSerialization, is(not(empty())));
    }

    @Test
    public void serializeChart_configurationWithSeries_seriesConfigLinkSerializedCorrectly()
            throws IOException, ClassNotFoundException {
        Chart input = new Chart();
        ListSeries series = new ListSeries();
        input.getConfiguration().addSeries(series);

        Chart output = serializeObject(input);

        ListSeries outputSeries = (ListSeries) output.getConfiguration()
                .getSeries().get(0);
        assertNotNull("Series config was null after serialization",
                outputSeries.getConfiguration());
        assertNotNull("Series config was null after serialization",
                output.getConfiguration());
        assertEquals(outputSeries.getConfiguration(), output.getConfiguration());
    }

    @Test
    public void serializeChart_configurationWithAxis_axisConfigLinkSerializedCorrectly()
            throws IOException, ClassNotFoundException, IllegalAccessException,
            NoSuchFieldException {
        Chart input = new Chart();
        YAxis axis = new YAxis();
        axis.setConfiguration(input.getConfiguration());
        input.getConfiguration().addyAxis(axis);

        Chart output = serializeObject(input);

        YAxis outputAxis = output.getConfiguration().getyAxis();
        assertNotNull("Axis config was null after serialization",
                getPrivateField("configuration", outputAxis, Axis.class));
        assertNotNull("Axis config was null after serialization",
                output.getConfiguration());
        assertEquals(getPrivateField("configuration", outputAxis, Axis.class),
                output.getConfiguration());
    }

    @Test
    public void serializeChart_configurationWithDrilldown_drilldownSeriesListSerializedCorrectly()
            throws IOException, ClassNotFoundException, NoSuchFieldException,
            IllegalAccessException {
        Chart input = new Chart();
        DataSeries dataSeries = new DataSeries();
        ListSeries drilldownSeries = new ListSeries();
        drilldownSeries.setId("id");
        dataSeries.addItemWithDrilldown(new DataSeriesItem("foobar", 42),
                drilldownSeries);
        input.getConfiguration().addSeries(dataSeries);

        Chart output = serializeObject(input);

        DataSeries outputSeries = (DataSeries) output.getConfiguration()
                .getSeries().get(0);
        List<Series> outputDrilldownSeries = getPrivateField("drilldownSeries",
                outputSeries, DataSeries.class);
        assertNotNull("Drilldown series list was null after serialization",
                outputDrilldownSeries);
        assertThat("Drilldown series list was empty after serialization",
                outputDrilldownSeries, is(not(empty())));
    }

    @Test
    public void serializeChart_configurationWithDrilldown_drilldownConfigurationLinkSerializedCorrectly()
            throws IOException, ClassNotFoundException, NoSuchFieldException,
            IllegalAccessException {
        Chart input = new Chart();
        DataSeries dataSeries = new DataSeries();
        ListSeries drilldownSeries = new ListSeries();
        drilldownSeries.setId("id");
        dataSeries.addItemWithDrilldown(new DataSeriesItem("foobar", 42),
                drilldownSeries);
        input.getConfiguration().addSeries(dataSeries);

        Chart output = serializeObject(input);

        Drilldown outputDrilldown = output.getConfiguration().getDrilldown();
        assertNotNull(
                "Drilldown configuration link was null after serialization",
                outputDrilldown.getConfiguration());
    }

    @Test
    public void serializeDataSeries_seriesWithOneItem_dataSeriesItemsSerializedCorrectly()
            throws IOException, ClassNotFoundException, NoSuchFieldException,
            IllegalAccessException {
        // this should make the item customized
        final DataSeriesItem dataSeriesItem = new DataSeriesItem("foobar", 42);
        DataSeries input = new DataSeries(dataSeriesItem);

        DataSeries output = serializeObject(input);
        final DataSeriesItem outputDataSeriesItem = output.get(0);
        assertTrue(
                "DataSeriesItem.isCustomized() was false after serialization",
                outputDataSeriesItem.isCustomized());
    }

    private <T, R> R getPrivateField(String fieldName, T target, Class<T> clazz)
            throws NoSuchFieldException, IllegalAccessException {
        final Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (R) field.get(target);
    }

    private <T> T serializeObject(T input) throws IOException,
            ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ObjectOutputStream out = new ObjectOutputStream(
                byteArrayOutputStream);
        out.writeObject(input);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                byteArrayOutputStream.toByteArray());
        final ObjectInputStream in = new ObjectInputStream(byteArrayInputStream);
        return (T) in.readObject();
    }
}
