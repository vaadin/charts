package com.vaadin.addon.charts.model.junittests;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.vaadin.addon.charts.model.PlotOptionsSeries;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

public class ContainerSeriesSerializerTest {

    private Container vaadinContainer;
    private ContainerDataSeries containerSeries;

    @Before
    public void setup() {
        vaadinContainer = new IndexedContainer();

        containerSeries = new ContainerDataSeries(vaadinContainer);

    }

    @SuppressWarnings("unchecked")
    @Test
    public void serialize_ContainerWithXY_ValuesMappedAsArray() {
        containerSeries.setXPropertyId("x");
        containerSeries.setYPropertyId("y");

        vaadinContainer.addContainerProperty("x", Number.class, null);
        vaadinContainer.addContainerProperty("y", Number.class, null);

        Item ie = vaadinContainer.addItem(1);
        ie.getItemProperty("x").setValue(80);
        ie.getItemProperty("y").setValue(80);

        ie = vaadinContainer.addItem(2);
        ie.getItemProperty("x").setValue(20);
        ie.getItemProperty("y").setValue(20);

        assertEquals("{\"data\":[[80,80],[20,20]]}", containerSeries.toString());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void serialize_ContainerWithXYZ_UnmappedPropertyNotSerialized() {
        containerSeries.setXPropertyId("x");
        containerSeries.setYPropertyId("y");

        vaadinContainer.addContainerProperty("x", Number.class, null);
        vaadinContainer.addContainerProperty("y", Number.class, null);
        vaadinContainer.addContainerProperty("z", Number.class, null);

        Item ie = vaadinContainer.addItem(1);
        ie.getItemProperty("x").setValue(80);
        ie.getItemProperty("y").setValue(80);
        ie.getItemProperty("z").setValue(80);

        ie = vaadinContainer.addItem(2);
        ie.getItemProperty("x").setValue(20);
        ie.getItemProperty("y").setValue(20);
        ie.getItemProperty("z").setValue(20);

        assertEquals("{\"data\":[[80,80],[20,20]]}", containerSeries.toString());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void serialize_ZMappedToName_ValuesMappedAsObject() {
        containerSeries.setXPropertyId("x");
        containerSeries.setYPropertyId("y");
        containerSeries.addAttributeToPropertyIdMapping("name", "z");

        vaadinContainer.addContainerProperty("x", Number.class, null);
        vaadinContainer.addContainerProperty("y", Number.class, null);
        vaadinContainer.addContainerProperty("z", Number.class, null);

        Item ie = vaadinContainer.addItem(1);
        ie.getItemProperty("x").setValue(80);
        ie.getItemProperty("y").setValue(80);
        ie.getItemProperty("z").setValue(80);

        ie = vaadinContainer.addItem(2);
        ie.getItemProperty("x").setValue(20);
        ie.getItemProperty("y").setValue(20);
        ie.getItemProperty("z").setValue(20);

        assertEquals(
                "{\"data\":[{\"x\":80,\"y\":80,\"name\":80},{\"x\":20,\"y\":20,\"name\":20}]}",
                containerSeries.toString());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void serialize_ContainerItemWithMissingZ_MissingItemSerializedCorrectly() {
        containerSeries.setXPropertyId("x");
        containerSeries.setYPropertyId("y");
        containerSeries.addAttributeToPropertyIdMapping("name", "z");

        vaadinContainer.addContainerProperty("x", Number.class, null);
        vaadinContainer.addContainerProperty("y", Number.class, null);
        vaadinContainer.addContainerProperty("z", Number.class, null);

        Item ie = vaadinContainer.addItem(1);
        ie.getItemProperty("x").setValue(80);
        ie.getItemProperty("y").setValue(80);
        ie.getItemProperty("z").setValue(80);

        ie = vaadinContainer.addItem(2);
        ie.getItemProperty("x").setValue(20);
        ie.getItemProperty("y").setValue(20);
        ie.getItemProperty("z").setValue(20);

        ie = vaadinContainer.addItem(3);
        ie.getItemProperty("x").setValue(10);
        ie.getItemProperty("y").setValue(10);

        assertEquals(
                "{\"data\":[{\"x\":80,\"y\":80,\"name\":80},{\"x\":20,\"y\":20,\"name\":20},{\"x\":10,\"y\":10}]}",
                containerSeries.toString());
    }

    @Test
    public void serialize_ContainerWithNonUTCDate_DateSerializedAsUTC() {
        containerSeries.setXPropertyId("x");
        containerSeries.setYPropertyId("y");

        vaadinContainer.addContainerProperty("x", Date.class, null);
        vaadinContainer.addContainerProperty("y", Number.class, null);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 1);
        calendar.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

        Date utcTime = calendar.getTime();

        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Helsinki"));
        calendar.set(Calendar.HOUR, 1);

        Date helsinkiTime = calendar.getTime();

        Item ie = vaadinContainer.addItem(1);
        ie.getItemProperty("x").setValue(helsinkiTime);
        ie.getItemProperty("y").setValue(80);

        String expected = "{\"data\":[[" + utcTime.getTime() + ",80]]}";

        assertEquals(expected, containerSeries.toString());
    }

    @Test
    public void serialize_ContainerWithLowAndHighValues_LowAndHighValuesSerialized() {
        containerSeries.setHighPropertyId("somehigh");
        containerSeries.setLowPropertyId("somelow");

        vaadinContainer.addContainerProperty("somehigh", Number.class, null);
        vaadinContainer.addContainerProperty("somelow", Number.class, null);

        Item item = vaadinContainer.getItem(vaadinContainer.addItem());
        item.getItemProperty("somehigh").setValue(5);
        item.getItemProperty("somelow").setValue(-5);

        assertEquals("{\"data\":[{\"high\":5,\"low\":-5}]}",
                containerSeries.toString());
    }

    @Test
    public void serialize_ContainerWithLinePlotOptions_PlotOptionsAndTypeSerialized() {
        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setShowInLegend(true);
        containerSeries.setPlotOptions(plotOptions);

        Configuration config = new Configuration();
        config.addSeries(containerSeries);

        assertEquals("{\"type\":\"line\",\"showInLegend\":true,\"data\":[]}",
                containerSeries.toString());
    }

    @Test
    public void serialize_ContainerWithSeriesPlotOptions_PlotTypeNotSerialized() {
        PlotOptionsSeries plotOptions = new PlotOptionsSeries();
        plotOptions.setShowInLegend(true);
        containerSeries.setPlotOptions(plotOptions);

        Configuration config = new Configuration();
        config.addSeries(containerSeries);

        assertEquals("{\"showInLegend\":true,\"data\":[]}",
                containerSeries.toString());
    }

    @Test
    public void serialize_ContainerWithNameAndStack_NameAndStackSerialized() {
        containerSeries.setName("foo");
        containerSeries.setStack("bar");
        Configuration config = new Configuration();
        config.addSeries(containerSeries);

        assertEquals("{\"name\":\"foo\",\"stack\":\"bar\",\"data\":[]}",
                containerSeries.toString());
    }

    @Test
    public void serialize_SeriesHasId_IdSerialized() {
        containerSeries.setId("foo");

        assertEquals("{\"id\":\"foo\",\"data\":[]}", containerSeries.toString());
    }

}
