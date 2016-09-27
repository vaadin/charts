package com.vaadin.addon.charts.model.junittests;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import com.vaadin.addon.charts.model.ChartDataSeries;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.server.data.DataSource;
import com.vaadin.server.data.ListDataSource;

public class ChartDataSeriesJSONSerializationTest {

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
            this.z = null;
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
    private DataSource<TestItem> dataSource;
    private ChartDataSeries<TestItem> chartDataSeries;
    private Collection<TestItem> col=new ArrayList<TestItem>();
    @Before
    public void setup() {
        Collection<TestItem> col=new ArrayList<TestItem>();
        dataSource = new ListDataSource<TestItem>(col);

        chartDataSeries = new ChartDataSeries(dataSource);

    }

    @SuppressWarnings("unchecked")
    @Test
    public void serialize_ContainerWithXY_ValuesMappedAsArray() {
        col.add(new TestItem(80,80));
        col.add(new TestItem(20,20));
        dataSource = new ListDataSource<TestItem>(col);
        chartDataSeries = new ChartDataSeries(dataSource);
        chartDataSeries.setXValueProvider(TestItem::getX);
        chartDataSeries.setYValueProvider(TestItem::getY);
        assertEquals("{\"data\":[[80,80],[20,20]]}", toJSON(chartDataSeries));
    }

    @Test(expected = RuntimeException.class)
    public void serialize_ContainerWithoutY_ExceptionIsThrown() {
        dataSource = new ListDataSource<TestItem>(col);

        chartDataSeries = new ChartDataSeries(dataSource);
        chartDataSeries.setId("foo");
        chartDataSeries.setXValueProvider(TestItem::getX);

        toJSON(chartDataSeries);
    }
    @Test(expected = RuntimeException.class)
    public void serialize_ContainerWithoutYAndLow_ExceptionIsThrown() {
        col.add(new TestItem(80,80));
        col.add(new TestItem(20,20));
        dataSource = new ListDataSource<TestItem>(col);
        chartDataSeries = new ChartDataSeries(dataSource);
        chartDataSeries.setXValueProvider(TestItem::getX);
        chartDataSeries.setHighDataProvider(TestItem::getY);

        toJSON(chartDataSeries);
    }
    @SuppressWarnings("unchecked")
    @Test
    public void serialize_ContainerWithXYZ_UnmappedPropertyNotSerialized() {
        col.add(new TestItem(80,80,80));
        col.add(new TestItem(20,20,20));
        dataSource = new ListDataSource<TestItem>(col);
        chartDataSeries = new ChartDataSeries(dataSource);
        chartDataSeries.setXValueProvider(TestItem::getX);
        chartDataSeries.setYValueProvider(TestItem::getY);

        assertEquals("{\"data\":[[80,80],[20,20]]}", toJSON(chartDataSeries));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void serialize_ZMappedToName_ValuesMappedAsObject() {
        col.add(new TestItem(80,80,80));
        col.add(new TestItem(20,20,20));
        dataSource = new ListDataSource<TestItem>(col);
        chartDataSeries = new ChartDataSeries(dataSource);
        chartDataSeries.setXValueProvider(TestItem::getX);
        chartDataSeries.setYValueProvider(TestItem::getY);
        chartDataSeries.addDataProvider("name", TestItem::getZ);
        assertEquals(
                "{\"data\":[{\"x\":80,\"y\":80,\"name\":80},{\"x\":20,\"y\":20,\"name\":20}]}",
                toJSON(chartDataSeries));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void serialize_ContainerItemWithMissingZ_MissingItemSerializedCorrectly() {
        col.add(new TestItem(80,80,80));
        col.add(new TestItem(20,20,20));
        col.add(new TestItem(10,10,null));
        dataSource = new ListDataSource<TestItem>(col);
        chartDataSeries = new ChartDataSeries(dataSource);
        chartDataSeries.setXValueProvider(TestItem::getX);
        chartDataSeries.setYValueProvider(TestItem::getY);
        chartDataSeries.addDataProvider("name", TestItem::getZ);

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
        DataSource<TestDateItem> dataSource = new ListDataSource<>(col);
        ChartDataSeries<TestDateItem> chartDataSeries = new ChartDataSeries(dataSource);
        chartDataSeries.setXValueProvider(TestDateItem::getDate);
        chartDataSeries.setYValueProvider(TestDateItem::getValue);

        String expected = "{\"data\":[[" + utcTime.getTime() + ",80]]}";
        assertEquals(expected, toJSON(chartDataSeries));
    }

    @Test
    public void serialize_ContainerWithLowAndHighValues_LowAndHighValuesSerialized() {
        col.add(new TestItem(-5,5,null));

        dataSource = new ListDataSource<TestItem>(col);
        chartDataSeries = new ChartDataSeries(dataSource);
        chartDataSeries.setLowDataProvider(TestItem::getX);
        chartDataSeries.setHighDataProvider(TestItem::getY);

        assertEquals("{\"data\":[{\"high\":5,\"low\":-5}]}",
                toJSON(chartDataSeries));
    }

    @Test
    public void serialize_ContainerWithLinePlotOptions_PlotOptionsAndTypeSerialized() {
        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setShowInLegend(true);
        dataSource = new ListDataSource<TestItem>(col);

        chartDataSeries = new ChartDataSeries(dataSource);
        chartDataSeries.setYValueProvider(TestItem::getY);
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
        dataSource = new ListDataSource<TestItem>(col);

        chartDataSeries = new ChartDataSeries(dataSource);
        chartDataSeries.setYValueProvider(TestItem::getY);
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
        dataSource = new ListDataSource<TestItem>(col);

        chartDataSeries = new ChartDataSeries(dataSource);
        chartDataSeries.setYValueProvider(TestItem::getY);
        chartDataSeries.setName("foo");
        chartDataSeries.setStack("bar");
        Configuration config = new Configuration();
        config.addSeries(chartDataSeries);

        assertEquals("{\"name\":\"foo\",\"stack\":\"bar\",\"data\":[]}",
                toJSON(chartDataSeries));
    }

    @Test
    public void serialize_SeriesHasId_IdSerialized() {
        dataSource = new ListDataSource<TestItem>(col);

        chartDataSeries = new ChartDataSeries(dataSource);
        chartDataSeries.setId("foo");
        chartDataSeries.setYValueProvider(TestItem::getY);

        assertEquals("{\"id\":\"foo\",\"data\":[]}", toJSON(chartDataSeries));
    }

}
