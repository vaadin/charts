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
import java.util.Objects;

import org.junit.Test;

import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataProviderSeries;
import com.vaadin.addon.charts.model.ListSeries;
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

        public Pair<ListDataProvider<T>, DataProviderSeries<T>> build() {
            final ListDataProvider<T> dataProvider = new ListDataProvider<>(colls);
            return new Pair<>(dataProvider, new DataProviderSeries<>(dataProvider));
        }
    }

    private <T> Pair<ListDataProvider<T>, DataProviderSeries<T>> createTuple(T... items) {
        final TupleBuilder<T> builder = TupleBuilder.builder();
        for (final T item : items) {
            builder.addItem(item);
        }
        return builder.build();
    }

    @Test
    public void serialize_ContainerWithXY_ValuesMappedAsArray() {

        final Pair<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> pair = createTuple(new TestItem(80, 80), new TestItem(20, 20));

        pair.getT2().setX(TestItem::getX);
        pair.getT2().setY(TestItem::getY);

        String actual = toJSON(pair.getT2());
        String expected = "{\"data\":[[80,80],[20,20]]}";
        assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void serialize_ContainerWithoutY_ExceptionIsThrown() {
        final Pair<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> pair = createTuple();
        pair.getT2().setId("foo");
        pair.getT2().setX(TestItem::getX);
        toJSON(pair.getT2());
    }

    @Test(expected = RuntimeException.class)
    public void serialize_ContainerWithoutYAndLow_ExceptionIsThrown() {
        final Pair<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> pair = createTuple(new TestItem(80, 80), new TestItem(20, 20));
        final DataProviderSeries<TestItem> dataProviderSeries = pair.getT2();

        dataProviderSeries.setX(TestItem::getX);
        dataProviderSeries.setHigh(TestItem::getY);
        toJSON(dataProviderSeries);
    }

    @Test
    public void serialize_ContainerWithXYZ_UnmappedPropertyNotSerialized() {
        final Pair<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> pair = createTuple(new TestItem(80, 80, 80), new TestItem(20, 20, 20));
        final DataProviderSeries<TestItem> chartDataSeries = pair.getT2();
        chartDataSeries.setX(TestItem::getX);
        chartDataSeries.setY(TestItem::getY);

        final String actual = toJSON(chartDataSeries);
        final String expected = "{\"data\":[[80,80],[20,20]]}";
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_ContainerWithXOHLC_SerializedAsArray() {
        final Pair<ListDataProvider<TestItemOHLC>, DataProviderSeries<TestItemOHLC>> pair = createTuple(new TestItemOHLC(2, 113.84, 115.92, 113.75, 115.19));
        final DataProviderSeries<TestItemOHLC> chartDataSeries = pair.getT2();

        chartDataSeries.setX(TestItemOHLC::getX);
        chartDataSeries.setOpen(TestItemOHLC::getO);
        chartDataSeries.setHigh(TestItemOHLC::getH);
        chartDataSeries.setLow(TestItemOHLC::getL);
        chartDataSeries.setClose(TestItemOHLC::getC);

        final String actual = toJSON(chartDataSeries);
        final String expected = "{\"data\":[[2,113.84,115.92,113.75,115.19]]}";
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_ZMappedToName_ValuesMappedAsObject() {
        final Pair<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> pair = createTuple(new TestItem(80, 80, 80), new TestItem(20, 20, 20));
        final DataProviderSeries<TestItem> chartDataSeries = pair.getT2();

        chartDataSeries.setX(TestItem::getX);
        chartDataSeries.setY(TestItem::getY);
        chartDataSeries.setPointName(TestItem::getZ);

        final String actual = toJSON(chartDataSeries);
        final String expected = "{\"data\":[{\"x\":80,\"y\":80,\"name\":80},{\"x\":20,\"y\":20,\"name\":20}]}";

        assertEquals(expected, actual);
    }

    @Test
    public void serialize_ContainerItemWithMissingZ_MissingItemSerializedCorrectly() {
        final Pair<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> pair = createTuple(new TestItem(80, 80, 80), new TestItem(20, 20, 20), new TestItem(10, 10, null));
        final DataProviderSeries<TestItem> series = pair.getT2();

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

        String expected = "{\"data\":[[" + instant.toEpochMilli() + ",80]]}";
        assertEquals(expected, toJSON(chartDataSeries));
    }

    @Test
    public void serialize_ContainerWithLowAndHighValues_LowAndHighValuesSerialized() {
        final Pair<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> pair = createTuple(new TestItem(-5, 5, null));
        final DataProviderSeries<TestItem> dataProviderSeries = pair.getT2();

        dataProviderSeries.setLow(TestItem::getX);
        dataProviderSeries.setHigh(TestItem::getY);

        final String actual = toJSON(dataProviderSeries);
        final String expected = "{\"data\":[{\"high\":5,\"low\":-5}]}";
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_ContainerWithLinePlotOptions_PlotOptionsAndTypeSerialized() {
        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setShowInLegend(true);

        final Pair<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> pair = createTuple();
        final DataProviderSeries<TestItem> dataProviderSeries = pair.getT2();

        dataProviderSeries.setY(TestItem::getY);
        dataProviderSeries.setPlotOptions(plotOptions);

        Configuration config = new Configuration();
        config.addSeries(dataProviderSeries);

        String actual = toJSON(dataProviderSeries);
        String expected = "{\"type\":\"line\",\"showInLegend\":true,\"data\":[]}";
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_ContainerWithSeriesPlotOptions_PlotTypeNotSerialized() {
        PlotOptionsSeries plotOptions = new PlotOptionsSeries();
        plotOptions.setShowInLegend(true);

        final Pair<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> pair = createTuple();
        final DataProviderSeries<TestItem> dataProviderSeries = pair.getT2();

        dataProviderSeries.setY(TestItem::getY);
        dataProviderSeries.setPlotOptions(plotOptions);

        Configuration config = new Configuration();
        config.addSeries(dataProviderSeries);

        String actual = toJSON(dataProviderSeries);
        String expected = "{\"showInLegend\":true,\"data\":[]}";
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_ContainerWithNameAndStack_NameAndStackSerialized() {

        final Pair<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> pair = createTuple();
        final DataProviderSeries<TestItem> dataProviderSeries = pair.getT2();

        dataProviderSeries.setY(TestItem::getY);
        dataProviderSeries.setName("foo");
        dataProviderSeries.setStack("bar");
        Configuration config = new Configuration();
        config.addSeries(dataProviderSeries);

        String actual = toJSON(dataProviderSeries);
        String expected = "{\"name\":\"foo\",\"stack\":\"bar\",\"data\":[]}";
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_SeriesHasId_IdSerialized() {
        final Pair<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> pair = createTuple();
        final DataProviderSeries<TestItem> dataProviderSeries = pair.getT2();

        dataProviderSeries.setId("foo");
        dataProviderSeries.setY(TestItem::getY);

        String actual = toJSON(dataProviderSeries);
        String expected = "{\"id\":\"foo\",\"data\":[]}";
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_ContainerWithFilteredValues_dataWasFiltered() {

        final Pair<ListDataProvider<TestItem>, DataProviderSeries<TestItem>> pair = createTuple(new TestItem(80, 80), new TestItem(20, 20));

        DataProviderSeries<TestItem> dataProviderSeries = pair.getT2();
        ListDataProvider<TestItem> dataProvider = pair.getT1();

        dataProvider.addFilter(item -> item.getX() >= 50);
        dataProviderSeries.setX(TestItem::getX);
        dataProviderSeries.setY(TestItem::getY);
        String actual = toJSON(dataProviderSeries);
        String expected = "{\"data\":[[80,80]]}";
        assertEquals(expected, actual);
    }

    @Test
    public void serialize_SeriesHasZIndex_zIndexSerialized() {
        final ListSeries dataSeries = new ListSeries(1,2,3); 
        dataSeries.setZIndex(134);

        String actual = toJSON(dataSeries);
        String expected = "{\"zIndex\":134,\"data\":[1,2,3]}";
        assertEquals(expected, actual);
    }

    public static class Pair<T1, T2> {
        private T1 t1;
        private T2 t2;

        public Pair(final T1 t1, final T2 t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        public T1 getT1() {
            return t1;
        }

        public T2 getT2() {
            return t2;
        }

        @Override
        public String toString() {
            return "Pair{" + "t1=" + t1 + ", t2=" + t2 + '}';
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Pair))
                return false;
            final Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(t1, pair.t1) && Objects.equals(t2, pair.t2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(t1, t2);
        }
    }

}
