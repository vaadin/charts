/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model.junittests;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.Attributes;
import com.vaadin.addon.charts.model.AxisList;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Drilldown;
import com.vaadin.addon.charts.model.LegendTitle;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Pane;
import com.vaadin.addon.charts.model.PaneList;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.style.SolidColor;

/**
 * Tests for the JSON serialization in {@link AbstractConfigurationObject}.
 *
 * Some serialization cases are tested by other test classes.
 */
public class ConfigurationObjectJSONSerializationTest {

    public static final String BasicTypeJson = "{\"number\":5,\"string\":\"somestring\"}";

    @Test
    public void toJSON_ObjectWithBasicTypesAndValues_ValuesSerialized() {
        BasicType object = new BasicType();

        assertEquals(BasicTypeJson, toJSON(object));
    }

    @Test
    public void toJSON_ObjectWithInnerObject_InnerObjectAndValuesSerialized() {
        InceptionObject object = new InceptionObject();

        assertEquals("{\"inner\":" + BasicTypeJson + ",\"outerNumber\":2}",
                toJSON(object));
    }

    @Test
    public void toJSON_ObjectWithCollections_CollectionsSerializedAsJsonArrays() {
        CollectionObject object = new CollectionObject();

        assertEquals("{\"numberList\":[1,2,3,4],\"objectList\":["
                + BasicTypeJson + "," + BasicTypeJson + "]}", toJSON(object));
    }

    @Test
    public void toJSON_ChartTypeEnum_SerializedByType() {
        ObjectContainer object = new ObjectContainer(ChartType.AREA);

        assertEquals("{\"object\":\"area\"}", toJSON(object));
    }

    @Test
    public void toJSON_ColorEnum_SerializedByColor() {
        ObjectContainer object = new ObjectContainer(SolidColor.BLUE);

        assertEquals("{\"object\":\"#0000FF\"}", toJSON(object));
    }

    @Test
    public void toString_AxisListWithOneItem_SerializedAsSingleAxis() {
        AxisList<XAxis> axisList = new AxisList<XAxis>();
        axisList.addAxis(new XAxis());
        ObjectContainer object = new ObjectContainer(axisList);
        String axisJson = "{\"axisIndex\":0}";

        assertEquals("{\"object\":" + axisJson + "}", toJSON(object));
    }

    @Test
    public void toString_AxisListWithTwoItems_SerializedAsAxisArray() {
        AxisList<XAxis> axisList = new AxisList<XAxis>();
        axisList.addAxis(new XAxis());
        axisList.addAxis(new XAxis());
        ObjectContainer object = new ObjectContainer(axisList);
        String axisJson1 = "{\"axisIndex\":0}";
        String axisJson2 = "{\"axisIndex\":1}";

        String expected = String.format("{\"object\":[%s,%s]}", axisJson1,
                axisJson2);

        assertEquals(expected, toJSON(object));
    }

    @Test
    public void toString_PaneListWithOneItem_SerializedAsSinglePane() {
        PaneList paneList = new PaneList();
        paneList.addPane(new Pane());
        ObjectContainer object = new ObjectContainer(paneList);
        String paneJson = "{\"paneIndex\":0}";

        assertEquals("{\"object\":" + paneJson + "}", toJSON(object));
    }

    @Test
    public void toString_PaneListWithTwoItems_SerializedAsPaneArray() {
        PaneList paneList = new PaneList();
        paneList.addPane(new Pane());
        paneList.addPane(new Pane());
        ObjectContainer object = new ObjectContainer(paneList);
        String paneJson1 = "{\"paneIndex\":0}";
        String paneJson2 = "{\"paneIndex\":1}";

        String expected = String.format("{\"object\":[%s,%s]}", paneJson1,
                paneJson2);

        assertEquals(expected, toJSON(object));
    }

    @Test
    public void toJSON_seriesWithLinePlotOptions_PlotOptionsAndTypeFlattenedToSeriesLevel() {
        PlotOptionsLine lineOptions = new PlotOptionsLine();
        lineOptions.setAnimation(true);
        ListSeries series = new ListSeries();
        series.setPlotOptions(lineOptions);
        assertEquals("{\"animation\":true,\"data\":[],\"type\":\"line\"}",
                toJSON(series));
    }

    @Test
    public void toJSON_TitleWithValue_ValueSerializedToText() {
        Title title = new Title();
        title.setText("foobar");
        assertEquals("{\"text\":\"foobar\"}", toJSON(title));
    }

    @Test
    public void toJSON_TitleWithNullValue_NullSerializedToText() {
        Title title = new Title();

        assertEquals("{\"text\":null}", toJSON(title));
    }

    @Test
    public void toJSON_AxisTitleWithNullValue_NullSerializedToText() {
        AxisTitle title = new AxisTitle();

        assertEquals("{\"text\":null}", toJSON(title));
    }

    @Test
    public void toJSON_LegendTitleWithNullValue_NullSerializedToText() {
        LegendTitle title = new LegendTitle();
        assertEquals("{\"text\":null}", toJSON(title));
    }

    @Test
    public void toJSON_drilldownWithConfiguration_drilldownSerializedToText() {
        Drilldown drilldown = new Drilldown();
        drilldown.setConfiguration(new Configuration());
        assertEquals("{\"series\":[]}", toJSON(drilldown));
    }

    @Test
    public void toJSON_AtributesWithStrokeWidth_StrokeWidthSerializedWithDash() {
        Attributes attributes = new Attributes();
        attributes.setStroke(SolidColor.RED);
        attributes.setStrokeWidth(10);

        assertEquals("{\"stroke\":\"#FF0000\",\"stroke-width\":10}",
                toJSON(attributes));
    }

    /*
     * Helper classes for serialization testing, so that we don't need to use
     * the actual classes
     */
    public static class BasicType extends AbstractConfigurationObject {

        private final Number number = 5;
        private final String string = "somestring";

        public Number getNumber() {
            return number;
        }

        public String getString() {
            return string;
        }
    }

    public static class InceptionObject extends AbstractConfigurationObject {

        private final BasicType inner = new BasicType();

        private final Number outerNumber = 2;

        public BasicType getInner() {
            return inner;
        }

        public Number getOuterNumber() {
            return outerNumber;
        }
    }

    public static class CollectionObject extends AbstractConfigurationObject {
        private final List<Integer> numberList = Arrays.asList(1, 2, 3, 4);
        private final List<BasicType> objectList = Arrays.asList(
                new BasicType(), new BasicType());

        public List<Integer> getNumberList() {
            return numberList;
        }

        public List<BasicType> getObjectList() {
            return objectList;
        }
    }

    public static class ObjectContainer extends AbstractConfigurationObject {
        private Object object;

        public ObjectContainer() {

        }

        public ObjectContainer(Object contents) {
            object = contents;
        }

        public Object getObject() {
            return object;
        }
    }
}
