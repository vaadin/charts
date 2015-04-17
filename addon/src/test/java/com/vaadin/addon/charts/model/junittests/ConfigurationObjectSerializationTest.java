package com.vaadin.addon.charts.model.junittests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.AxisList;
import com.vaadin.addon.charts.model.ChartType;
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
public class ConfigurationObjectSerializationTest {

    public static final String BasicTypeJson = "{\"number\":5,\"string\":\"somestring\"}";

    @Test
    public void toString_ObjectWithBasicTypesAndValues_ValuesSerialized() {
        BasicType object = new BasicType();

        assertEquals(BasicTypeJson, object.toString());
    }

    @Test
    public void toString_ObjectWithInnerObject_InnerObjectAndValuesSerialized() {
        InceptionObject object = new InceptionObject();

        assertEquals("{\"inner\":" + BasicTypeJson + ",\"outerNumber\":2}",
                object.toString());
    }

    @Test
    public void toString_ObjectWithCollections_CollectionsSerializedAsJsonArrays() {
        CollectionObject object = new CollectionObject();

        assertEquals("{\"numberList\":[1,2,3,4],\"objectList\":["
                + BasicTypeJson + "," + BasicTypeJson + "]}", object.toString());
    }

    @Test
    public void toString_ChartTypeEnum_SerializedByType() {
        ObjectContainer object = new ObjectContainer(ChartType.AREA);

        assertEquals("{\"object\":\"area\"}", object.toString());
    }

    @Test
    public void toString_ColorEnum_SerializedByColor() {
        ObjectContainer object = new ObjectContainer(SolidColor.BLUE);

        assertEquals("{\"object\":\"#0000FF\"}", object.toString());
    }

    @Test
    public void toString_AxisListWithOneItem_SerializedAsSingleAxis() {
        AxisList axisList = new AxisList();
        axisList.addAxis(new XAxis());
        ObjectContainer object = new ObjectContainer(axisList);
        String axisJson = "{\"axisIndex\":0}";

        assertEquals("{\"object\":" + axisJson + "}", object.toString());
    }

    @Test
    public void toString_AxisListWithTwoItems_SerializedAsAxisArray() {
        AxisList axisList = new AxisList();
        axisList.addAxis(new XAxis());
        axisList.addAxis(new XAxis());
        ObjectContainer object = new ObjectContainer(axisList);
        String axisJson1 = "{\"axisIndex\":0}";
        String axisJson2 = "{\"axisIndex\":1}";

        String expected = String.format("{\"object\":[%s,%s]}", axisJson1,
                axisJson2);

        assertEquals(expected, object.toString());
    }

    @Test
    public void toString_PaneListWithOneItem_SerializedAsSinglePane() {
        PaneList paneList = new PaneList();
        paneList.addPane(new Pane());
        ObjectContainer object = new ObjectContainer(paneList);
        String paneJson = "{\"paneIndex\":0}";

        assertEquals("{\"object\":" + paneJson + "}", object.toString());
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

        assertEquals(expected, object.toString());
    }

    @Test
    public void toString_seriesWithLinePlotOptions_PlotOptionsAndTypeFlattenedToSeriesLevel() {
        PlotOptionsLine lineOptions = new PlotOptionsLine();
        lineOptions.setAnimation(true);
        ListSeries series = new ListSeries();
        series.setPlotOptions(lineOptions);

        assertEquals("{\"animation\":true,\"data\":[],\"type\":\"line\"}",
                series.toString());
    }

    @Test
    public void toString_TitleWithValue_ValueSerializedToText() {
        Title title = new Title("foobar");

        assertEquals("{\"text\":\"foobar\"}", title.toString());
    }

    @Test
    public void toString_TitleWithNullValue_NullSerializedToText() {
        Title title = new Title(null);

        assertEquals("{\"text\":null}", title.toString());
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
            this.object = contents;
        }

        public Object getObject() {
            return object;
        }
    }
}
