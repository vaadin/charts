package com.vaadin.addon.charts.model.junittests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonElement;
import com.vaadin.addon.charts.model.AbstractContainerSeries;
import com.vaadin.addon.charts.model.ContainerSeries;
import com.vaadin.addon.charts.model.gsonhelpers.AbstractContainerSeriesSerializer;
import com.vaadin.data.Item;

public class AbstractContainerSeriesSerializerTest {

    private AbstractContainerSeries container;
    private AbstractContainerSeriesSerializer serializer;

    @Before
    public void setup() {
        serializer = new AbstractContainerSeriesSerializer();

        container = new ContainerSeries();
        container.addContainerProperty("x", Number.class, null);
        container.addContainerProperty("y", Number.class, null);
        container.addContainerProperty("z", Number.class, null);

        Item ie = container.addItem(1);
        ie.getItemProperty("x").setValue(80);
        ie.getItemProperty("y").setValue(80);
        ie.getItemProperty("z").setValue(80);

        ie = container.addItem(2);
        ie.getItemProperty("x").setValue(20);
        ie.getItemProperty("y").setValue(20);
        ie.getItemProperty("z").setValue(20);
    }

    @Test
    public void test() {
        JsonElement json = serializer.serialize(container, null, null);
        assertTrue(json
                .toString()
                .equals("{\"data\":[{\"x\":80,\"y\":80,\"z\":80},{\"x\":20,\"y\":20,\"z\":20}]}"));

        container.addDefaultPropertyId("x");
        json = serializer.serialize(container, null, null);
        assertTrue(json
                .toString()
                .equals("{\"data\":[{\"x\":80,\"y\":80,\"z\":80},{\"x\":20,\"y\":20,\"z\":20}]}"));

        container.addDefaultPropertyId("y");
        json = serializer.serialize(container, null, null);
        assertTrue(json
                .toString()
                .equals("{\"data\":[{\"x\":80,\"y\":80,\"z\":80},{\"x\":20,\"y\":20,\"z\":20}]}"));

        container.addDefaultPropertyId("z");
        json = serializer.serialize(container, null, null);
        assertTrue(json.toString().equals("{\"data\":[[80,80,80],[20,20,20]]}"));

        container.clearDefaultPropertyIds();
        container.removeContainerProperty("z");
        json = serializer.serialize(container, null, null);
        assertTrue(json.toString().equals(
                "{\"data\":[{\"x\":80,\"y\":80},{\"x\":20,\"y\":20}]}"));

        container.addDefaultPropertyId("x");
        container.removeContainerProperty("y");
        Item i = container.addItem(123);
        i.getItemProperty("x").setValue(10);
        json = serializer.serialize(container, null, null);
        assertTrue(json.toString().equals("{\"data\":[80,20,10]}"));
    }

}
