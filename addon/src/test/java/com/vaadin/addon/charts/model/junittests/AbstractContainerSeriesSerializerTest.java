package com.vaadin.addon.charts.model.junittests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonElement;
import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.addon.charts.model.gsonhelpers.ContainerDataSeriesSerializer;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

public class AbstractContainerSeriesSerializerTest {

    private Container vaadinContainer;
    private ContainerDataSeries container;
    private ContainerDataSeriesSerializer serializer;

    @Before
    public void setup() {
        serializer = new ContainerDataSeriesSerializer();

        vaadinContainer = new IndexedContainer();

        container = new ContainerDataSeries(vaadinContainer);
        container.setXPropertyId("x");
        container.setYPropertyId("y");

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
    }

    @Test
    public void test() {
        JsonElement json = serializer.serialize(container, null, null);
        System.out.println(json.toString());
        assertTrue(json.toString().equals("{\"data\":[[80,80],[20,20]]}"));

        container.addAttributeToPropertyIdMapping("name", "z");
        json = serializer.serialize(container, null, null);
        System.out.println(json.toString());
        assertTrue(json
                .toString()
                .equals("{\"data\":[{\"x\":80,\"y\":80,\"name\":80},{\"x\":20,\"y\":20,\"name\":20}]}"));

        Item ie = vaadinContainer.addItem(3);
        ie.getItemProperty("x").setValue(10);
        ie.getItemProperty("y").setValue(10);
        json = serializer.serialize(container, null, null);
        System.out.println(json.toString());
        assertTrue(json
                .toString()
                .equals("{\"data\":[{\"x\":80,\"y\":80,\"name\":80},{\"x\":20,\"y\":20,\"name\":20},[10,10]]}"));

    }

}
