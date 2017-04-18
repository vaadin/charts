package com.vaadin.addon.charts.model.junittests;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.rapidpm.frp.model.Tuple;

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
        private ZonedDateTime date;
        private Integer value;

        public TestDateItem(ZonedDateTime date, Integer value) {
            this.date = date;
            this.value = value;
        }

        public ZonedDateTime getDate() {
            return date;
        }

        public Integer getValue() {
            return value;
        }
    }

    private class TestItem {
        private final Integer x;
        private final Integer y;
        private final Integer z;

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

        public TestItemOHLC(int x, double o, double h, double l, double c) {
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

    private static final class TupleBuilder<T> {

        public static <T> TupleBuilder<T> builder() {
            return new TupleBuilder<>();
        }

        final List<T> colls = new ArrayList<>();

        public TupleBuilder<T> addItem(T item) {
            colls.add(item);
            return this;
        }

        public Tuple<ListDataProvider<T>, DataProviderSeries<T>> build() {
            final ListDataProvider<T> dataProvider = new ListDataProvider<>(colls);
            return new Tuple<>(dataProvider, new DataProviderSeries<>(dataProvider));
        }
    }

    private <T> Tuple<ListDataProvider<T>, DataProviderSeries<T>> createTuple(T... items) {
        final TupleBuilder<T> builder = TupleBuilder.builder();
        for (T item : items) {
            builder.addItem(item);
        }
        return builder.build();
    }

    private ListDataProvider<TestItem> dataProvider;
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

        final Tuple<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> tuple =
            createTuple(
                new TestItem(80, 80),
                new TestItem(20, 20));

        tuple.getT2().setX(TestItem::getX);
        tuple.getT2().setY(TestItem::getY);

        String actual = toJSON(tuple.getT2());
        String expected = "{\"data\":[[80,80],[20,20]]}";
        assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void serialize_ContainerWithoutY_ExceptionIsThrown() {
        final Tuple<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> tuple = createTuple();
        tuple.getT2().setId("foo");
        tuple.getT2().setX(TestItem::getX);
        toJSON(tuple.getT2());
    }

    @Test(expected = RuntimeException.class)
    public void serialize_ContainerWithoutYAndLow_ExceptionIsThrown() {
        final Tuple<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> tuple =
            createTuple(new TestItem(80, 80), new TestItem(20, 20));
        final DataProviderSeries<TestItem> dataProviderSeries = tuple.getT2();

        dataProviderSeries.setX(TestItem::getX);
        dataProviderSeries.setHigh(TestItem::getY);
        toJSON(dataProviderSeries);
    }

    @Test
    public void serialize_ContainerWithXYZ_UnmappedPropertyNotSerialized() {
        final Tuple<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> tuple =
            createTuple(new TestItem(80, 80, 80), new TestItem(20, 20, 20));
        final DataProviderSeries<TestItem> chartDataSeries = tuple.getT2();
        chartDataSeries.setX(TestItem::getX);
        chartDataSeries.setY(TestItem::getY);

        final String actual = toJSON(chartDataSeries);
        final String expected = "{\"data\":[[80,80],[20,20]]}";
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_ContainerWithXOHLC_SerializedAsArray() {
        final Tuple<ListDataProvider<TestItemOHLC>, DataProviderSeries<TestItemOHLC>> tuple
            = createTuple(new TestItemOHLC(2, 113.84, 115.92, 113.75, 115.19));
        final DataProviderSeries<TestItemOHLC> chartDataSeries = tuple.getT2();

        chartDataSeries.setX(TestItemOHLC::getX);
        chartDataSeries.setOpen(TestItemOHLC::getO);
        chartDataSeries.setHigh(TestItemOHLC::getH);
        chartDataSeries.setLow(TestItemOHLC::getL);
        chartDataSeries.setClose(TestItemOHLC::getC);

        assertEquals("{\"data\":[[2,113.84,115.92,113.75,115.19]]}", toJSON(chartDataSeries));
    }

    @Test
    public void serialize_ZMappedToName_ValuesMappedAsObject_Orig() {
        col.add(new TestItem(80, 80, 80));
        col.add(new TestItem(20, 20, 20));
        dataProvider = new ListDataProvider<>(col);
        chartDataSeries = new DataProviderSeries<>(dataProvider);
        chartDataSeries.setX(TestItem::getX);
        chartDataSeries.setY(TestItem::getY);
        chartDataSeries.setPointName(TestItem::getZ);
        assertEquals("{\"data\":[{\"x\":80,\"y\":80,\"name\":80},{\"x\":20,\"y\":20,\"name\":20}]}", toJSON(chartDataSeries));
    }
    @Test
    public void serialize_ZMappedToName_ValuesMappedAsObject() {
        final Tuple<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> tuple
            = createTuple(
                new TestItem(80, 80, 80),
                new TestItem(20, 20, 20));
        final DataProviderSeries<TestItem> chartDataSeries = tuple.getT2();

        chartDataSeries.setX(TestItem::getX);
        chartDataSeries.setY(TestItem::getY);
        chartDataSeries.setPointName(TestItem::getZ);
        final String actual = toJSON(chartDataSeries);
        final String expected = "{\"data\":[{\"x\":80,\"y\":80,\"name\":80},{\"x\":20,\"y\":20,\"name\":20}]}";

        assertEquals(expected, actual);
    }

    @Test
    public void serialize_ContainerItemWithMissingZ_MissingItemSerializedCorrectly() {
        final Tuple<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> tuple
            = createTuple(
                new TestItem(80, 80, 80),
                new TestItem(20, 20, 20),
                new TestItem(10, 10, null));
        final DataProviderSeries<TestItem> series = tuple.getT2();

        series.setX(TestItem::getX);
        series.setY(TestItem::getY);
        series.setPointName(TestItem::getZ);

        String actual = toJSON(series);
        String expected = "{\"data\":[{\"x\":80,\"y\":80,\"name\":80},{\"x\":20,\"y\":20,\"name\":20},{\"x\":10,\"y\":10}]}";
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_ContainerWithNonUTCDate_DateSerializedAsUTC() {
        final ZonedDateTime nowUTC = ZonedDateTime.of(2010, 10, 10, 10, 39, 00, 00, ZoneId.of("UTC"));
        final ZonedDateTime nowEuropeParis = ZonedDateTime.of(2010, 10, 10, 10, 39, 00, 00, ZoneId.of("Europe/Paris"));

        final Collection<TestDateItem> colEurope = singletonList(new TestDateItem(nowEuropeParis, 80));
        final DataProvider<TestDateItem, ?> dataProviderEurope = new ListDataProvider<>(colEurope);

        final DataProviderSeries<TestDateItem> chartDataSeriesEurope = new DataProviderSeries<>(dataProviderEurope);
        chartDataSeriesEurope.setX(TestDateItem::getDate);
        chartDataSeriesEurope.setY(TestDateItem::getValue);

        final Collection<TestDateItem> colUTC = singletonList(new TestDateItem(nowUTC, 80));
        final DataProvider<TestDateItem, ?> dataProviderUTC = new ListDataProvider<>(colUTC);

        final DataProviderSeries<TestDateItem> chartDataSeriesUTC = new DataProviderSeries<>(dataProviderUTC);
        chartDataSeriesUTC.setX(TestDateItem::getDate);
        chartDataSeriesUTC.setY(TestDateItem::getValue);

        final String actualEurope = toJSON(chartDataSeriesEurope);
        final String actualUTC = toJSON(chartDataSeriesUTC);

        assertNotEquals(actualUTC, actualEurope);  // not loosing TimeZone

    }

    @Test
    public void serialize_Instant_ToHigcharts() {
        LocalDateTime dateTime = LocalDateTime.now();
        Collection<TestInstantItem> col = new ArrayList<>();
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        col.add(new TestInstantItem(instant, 80));
        DataProvider<TestInstantItem, ?> DataProvider = new ListDataProvider<>(col);

        DataProviderSeries<TestInstantItem> chartDataSeries = new DataProviderSeries<>(DataProvider);
        chartDataSeries.setX(TestInstantItem::getDate);
        chartDataSeries.setY(TestInstantItem::getValue);

        String expected = "{\"data\":[[" + instant.getEpochSecond() * 1000 + ",80]]}";
        assertEquals(expected, toJSON(chartDataSeries));
    }

    @Test
    public void serialize_ContainerWithLowAndHighValues_LowAndHighValuesSerialized() {
        col.add(new TestItem(-5, 5, null));

        dataProvider = new ListDataProvider<>(col);
        chartDataSeries = new DataProviderSeries<>(dataProvider);
        chartDataSeries.setLow(TestItem::getX);
        chartDataSeries.setHigh(TestItem::getY);

        final String actual = toJSON(chartDataSeries);
        final String expected = "{\"data\":[{\"high\":5,\"low\":-5}]}";
        assertEquals(expected, actual);
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
        assertEquals("{\"type\":\"line\",\"showInLegend\":true,\"data\":[]}", toJSON(chartDataSeries));
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

        assertEquals("{\"showInLegend\":true,\"data\":[]}", toJSON(chartDataSeries));
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

        assertEquals("{\"name\":\"foo\",\"stack\":\"bar\",\"data\":[]}", toJSON(chartDataSeries));
    }

    @Test
    public void serialize_SeriesHasId_IdSerialized() {
        dataProvider = new ListDataProvider<>(col);

        chartDataSeries = new DataProviderSeries<>(dataProvider);
        chartDataSeries.setId("foo");
        chartDataSeries.setY(TestItem::getY);

        assertEquals("{\"id\":\"foo\",\"data\":[]}", toJSON(chartDataSeries));
    }

    @Test
    public void serialize_ContainerWithFilteredValues_dataWasFiltered() {
        col.add(new TestItem(80, 80));
        col.add(new TestItem(20, 20));
        dataProvider = DataProvider.ofCollection(col);
        chartDataSeries = new DataProviderSeries<>(dataProvider);
        dataProvider.addFilter(item -> {
            return item.getX() >= 50;
        });
        chartDataSeries.setX(TestItem::getX);
        chartDataSeries.setY(TestItem::getY);
        assertEquals("{\"data\":[[80,80]]}", toJSON(chartDataSeries));
    }

}
