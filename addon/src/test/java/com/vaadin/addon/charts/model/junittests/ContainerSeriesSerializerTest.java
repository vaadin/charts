package com.vaadin.addon.charts.model.junittests;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonElement;
import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.addon.charts.model.gsonhelpers.ContainerDataSeriesSerializer;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

public class ContainerSeriesSerializerTest {

    private Container vaadinContainer;
    private ContainerDataSeries containerSeries;
    private ContainerDataSeriesSerializer serializer;

    @Before
    public void setup() {
        serializer = new ContainerDataSeriesSerializer();

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

        JsonElement json = serializer.serialize(containerSeries, null, null);

        assertEquals("{\"data\":[[80,80],[20,20]]}", json.toString());
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

        JsonElement json = serializer.serialize(containerSeries, null, null);
        assertEquals("{\"data\":[[80,80],[20,20]]}", json.toString());
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

        JsonElement json = serializer.serialize(containerSeries, null, null);

        assertEquals(
                "{\"data\":[{\"x\":80,\"y\":80,\"name\":80},{\"x\":20,\"y\":20,\"name\":20}]}",
                json.toString());
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

        JsonElement json = serializer.serialize(containerSeries, null, null);

        assertEquals(
                "{\"data\":[{\"x\":80,\"y\":80,\"name\":80},{\"x\":20,\"y\":20,\"name\":20},{\"x\":10,\"y\":10}]}",
                json.toString());
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

        JsonElement json = serializer.serialize(containerSeries, null, null);

        assertEquals(expected, json.toString());
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

        JsonElement json = serializer.serialize(containerSeries, null, null);

        assertEquals("{\"data\":[{\"high\":5,\"low\":-5}]}", json.toString());
    }

}
