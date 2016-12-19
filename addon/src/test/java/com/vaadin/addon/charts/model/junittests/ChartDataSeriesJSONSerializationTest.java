package com.vaadin.addon.charts.model.junittests;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataProviderSeries;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;

public class ChartDataSeriesJSONSerializationTest {

    private class TestInstantItem {
        private Instant date;
        private Integer value;

        public TestInstantItem(Instant date, Integer value) {
            this.date = date;
            this.value = value;
        }

        public Instant getDate() {
            return date;
        }

        public Integer getValue() {
            return value;
        }
    }

    private class TestDateItem {
        private Date date;
        private Integer value;

        public TestDateItem(Date date, Integer value) {
            this.date = date;
            this.value = value;
        }

        public Date getDate() {
            return date;
        }

        public Integer getValue() {
            return value;
        }
    }

    private class TestItem {
        private Integer x;
        private Integer y;
        private Integer z;

        public TestItem(Integer x, Integer y) {
            this.x = x;
            this.y = y;
            z = null;
        }

        public TestItem(Integer x, Integer y, Integer z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Integer getX() {
            return x;
        }

        public Integer getY() {
            return y;
        }

        public Integer getZ() {
            return z;
        }
    }

    private class TestItemOHLC {
        private Number x;
        private Number o;
        private Number h;
        private Number l;
        private Number c;

        public TestItemOHLC(int x, double o, double h, double l,
                double c) {
            this.x = x;
            this.o = o;
            this.h = h;
            this.l = l;
            this.c = c;
        }

        public Number getX() {
            return x;
        }

        public Number getO() {
            return o;
        }

        public Number getH() {
            return h;
        }

        public Number getL() {
            return l;
        }

        public Number getC() {
            return c;
        }

    }

    private DataProvider<TestItem, ?> dataProvider;
    private DataProviderSeries<TestItem> chartDataSeries;
    private Collection<TestItem> col = new ArrayList<>();

    @Before
    public void setup() {
        Collection<TestItem> col = new ArrayList<>();
        dataProvider = new ListDataProvider<>(col);

        chartDataSeries = new DataProviderSeries<>(dataProvider);

    }

    @Test
    public void serialize_ContainerWithXY_ValuesMappedAsArray() {
        col.add(new TestItem(80, 80));
        col.add(new TestItem(20, 20));
        dataProvider = new ListDataProvider<>(col);
        chartDataSeries = new DataProviderSeries<>(dataProvider);
        chartDataSeries.setX(TestItem::getX);
        chartDataSeries.setY(TestItem::getY);
        assertEquals("{\"data\":[[80,80],[20,20]]}", toJSON(chartDataSeries));
    }

    @Test(expected = RuntimeException.class)
    public void serialize_ContainerWithoutY_ExceptionIsThrown() {
        dataProvider = new ListDataProvider<>(col);

        chartDataSeries = new DataProviderSeries<>(dataProvider);
        chartDataSeries.setId("foo");
        chartDataSeries.setX(TestItem::getX);

        toJSON(chartDataSeries);
    }

    @Test(expected = RuntimeException.class)
    public void serialize_ContainerWithoutYAndLow_ExceptionIsThrown() {
        col.add(new TestItem(80, 80));
        col.add(new TestItem(20, 20));
        dataProvider = new ListDataProvider<>(col);
        chartDataSeries = new DataProviderSeries<>(dataProvider);
        chartDataSeries.setX(TestItem::getX);
        chartDataSeries.setHigh(TestItem::getY);

        toJSON(chartDataSeries);
    }

    @Test
    public void serialize_ContainerWithXYZ_UnmappedPropertyNotSerialized() {
        col.add(new TestItem(80, 80, 80));
        col.add(new TestItem(20, 20, 20));
        dataProvider = new ListDataProvider<>(col);
        chartDataSeries = new DataProviderSeries<>(dataProvider);
        chartDataSeries.setX(TestItem::getX);
        chartDataSeries.setY(TestItem::getY);

        assertEquals("{\"data\":[[80,80],[20,20]]}", toJSON(chartDataSeries));
    }

    @Test
    public void serialize_ContainerWithXOHLC_SerializedAsArray() {

        Collection<TestItemOHLC> col = new ArrayList<>();
        col.add(new TestItemOHLC(2, 113.84,
                115.92, 113.75,
                115.19));
        DataProviderSeries<TestItemOHLC> chartDataSeries = new DataProviderSeries<>(
                new ListDataProvider<>(col));
        chartDataSeries.setX(TestItemOHLC::getX);
        chartDataSeries.setOpen(TestItemOHLC::getO);
        chartDataSeries.setHigh(TestItemOHLC::getH);
        chartDataSeries.setLow(TestItemOHLC::getL);
        chartDataSeries.setClose(TestItemOHLC::getC);

        assertEquals("{\"data\":[[2,113.84,115.92,113.75,115.19]]}",
                toJSON(chartDataSeries));
    }

    @Test
    public void serialize_ZMappedToName_ValuesMappedAsObject() {
        col.add(new TestItem(80, 80, 80));
        col.add(new TestItem(20, 20, 20));
        dataProvider = new ListDataProvider<>(col);
        chartDataSeries = new DataProviderSeries<>(dataProvider);
        chartDataSeries.setX(TestItem::getX);
        chartDataSeries.setY(TestItem::getY);
        chartDataSeries.setPointName(TestItem::getZ);
        assertEquals(
                "{\"data\":[{\"x\":80,\"y\":80,\"name\":80},{\"x\":20,\"y\":20,\"name\":20}]}",
                toJSON(chartDataSeries));
    }

    @Test
    public void serialize_ContainerItemWithMissingZ_MissingItemSerializedCorrectly() {
        col.add(new TestItem(80, 80, 80));
        col.add(new TestItem(20, 20, 20));
        col.add(new TestItem(10, 10, null));
        dataProvider = new ListDataProvider<>(col);
        chartDataSeries = new DataProviderSeries<>(dataProvider);
        chartDataSeries.setX(TestItem::getX);
        chartDataSeries.setY(TestItem::getY);
        chartDataSeries.setPointName(TestItem::getZ);

        assertEquals(
                "{\"data\":[{\"x\":80,\"y\":80,\"name\":80},{\"x\":20,\"y\":20,\"name\":20},{\"x\":10,\"y\":10}]}",
                toJSON(chartDataSeries));
    }

    @Test
    public void serialize_ContainerWithNonUTCDate_DateSerializedAsUTC() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 1);
        calendar.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));

        Date utcTime = calendar.getTime();

        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Helsinki"));
        calendar.set(Calendar.HOUR, 1);

        Date helsinkiTime = calendar.getTime();
        Collection<TestDateItem> col = new ArrayList<>();
        col.add(new TestDateItem(helsinkiTime, 80));
        DataProvider<TestDateItem, ?> DataProvider = new ListDataProvider<>(
                col);
        DataProviderSeries<TestDateItem> chartDataSeries = new DataProviderSeries<>(
                DataProvider);
        chartDataSeries.setX(TestDateItem::getDate);
        chartDataSeries.setY(TestDateItem::getValue);

        String expected = "{\"data\":[[" + utcTime.getTime() + ",80]]}";
        assertEquals(expected, toJSON(chartDataSeries));
    }

    @Test
    public void serialize_Instant_ToHigcharts() {
        LocalDateTime dateTime = LocalDateTime.now();
        Collection<TestInstantItem> col = new ArrayList<>();
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        col.add(new TestInstantItem(instant, 80));
        DataProvider<TestInstantItem, ?> DataProvider = new ListDataProvider<>(
                col);

        DataProviderSeries<TestInstantItem> chartDataSeries = new DataProviderSeries<>(
                DataProvider);
        chartDataSeries.setX(TestInstantItem::getDate);
        chartDataSeries.setY(TestInstantItem::getValue);

        String expected = "{\"data\":[[" + instant.getEpochSecond() * 1000
                + ",80]]}";
        assertEquals(expected, toJSON(chartDataSeries));
    }

    @Test
    public void serialize_ContainerWithLowAndHighValues_LowAndHighValuesSerialized() {
        col.add(new TestItem(-5, 5, null));

        dataProvider = new ListDataProvider<>(col);
        chartDataSeries = new DataProviderSeries<>(dataProvider);
        chartDataSeries.setLow(TestItem::getX);
        chartDataSeries.setHigh(TestItem::getY);

        assertEquals("{\"data\":[{\"high\":5,\"low\":-5}]}",
                toJSON(chartDataSeries));
    }

    @Test
    public void serialize_ContainerWithLinePlotOptions_PlotOptionsAndTypeSerialized() {
        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setShowInLegend(true);
        dataProvider = new ListDataProvider<>(col);

        chartDataSeries = new DataProviderSeries<>(dataProvider);
        chartDataSeries.setY(TestItem::getY);
        chartDataSeries.setPlotOptions(plotOptions);

        Configuration config = new Configuration();
        config.addSeries(chartDataSeries);
        assertEquals("{\"type\":\"line\",\"showInLegend\":true,\"data\":[]}",
                toJSON(chartDataSeries));
    }

    @Test
    public void serialize_ContainerWithSeriesPlotOptions_PlotTypeNotSerialized() {
        PlotOptionsSeries plotOptions = new PlotOptionsSeries();
        plotOptions.setShowInLegend(true);
        dataProvider = new ListDataProvider<>(col);

        chartDataSeries = new DataProviderSeries<>(dataProvider);
        chartDataSeries.setY(TestItem::getY);
        chartDataSeries.setPlotOptions(plotOptions);

        Configuration config = new Configuration();
        config.addSeries(chartDataSeries);

        assertEquals("{\"showInLegend\":true,\"data\":[]}",
                toJSON(chartDataSeries));
    }

    @Test
    public void serialize_ContainerWithNameAndStack_NameAndStackSerialized() {
        PlotOptionsSeries plotOptions = new PlotOptionsSeries();
        plotOptions.setShowInLegend(true);
        dataProvider = new ListDataProvider<>(col);

        chartDataSeries = new DataProviderSeries<>(dataProvider);
        chartDataSeries.setY(TestItem::getY);
        chartDataSeries.setName("foo");
        chartDataSeries.setStack("bar");
        Configuration config = new Configuration();
        config.addSeries(chartDataSeries);

        assertEquals("{\"name\":\"foo\",\"stack\":\"bar\",\"data\":[]}",
                toJSON(chartDataSeries));
    }

    @Test
    public void serialize_SeriesHasId_IdSerialized() {
        dataProvider = new ListDataProvider<>(col);

        chartDataSeries = new DataProviderSeries<>(dataProvider);
        chartDataSeries.setId("foo");
        chartDataSeries.setY(TestItem::getY);

        assertEquals("{\"id\":\"foo\",\"data\":[]}", toJSON(chartDataSeries));
    }

}
