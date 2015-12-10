package com.vaadin.addon.charts.model.junittests;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AbstractPlotOptions;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConfigurationTest {

    private Configuration conf;

    @Before
    public void setup() {
        ListSeries row1 = new ListSeries("row1", 1, 2);
        ListSeries row2 = new ListSeries("row2", 4, 5);
        ListSeries row3 = new ListSeries("row3", 7, 8);
        ListSeries row4 = new ListSeries("row4", 10, 11);

        String[] cols = { "col1", "col2" };

        Chart chart = new Chart(ChartType.COLUMN);
        conf = chart.getConfiguration();

        XAxis axis = new XAxis();
        axis.setCategories(cols);
        conf.addxAxis(axis);

        conf.setSeries(row1, row2, row3, row4);
    }

    @Test
    public void testReverseListSeries() {
        assertTrue(((ListSeries) conf.getSeries().get(0)).getData()[0]
                .intValue() == 1);
        assertTrue(((ListSeries) conf.getSeries().get(1)).getData()[0]
                .intValue() == 4);
        assertTrue(((ListSeries) conf.getSeries().get(2)).getData()[0]
                .intValue() == 7);
        assertTrue(((ListSeries) conf.getSeries().get(3)).getData()[0]
                .intValue() == 10);

        assertTrue(((ListSeries) conf.getSeries().get(0)).getData()[1]
                .intValue() == 2);
        assertTrue(((ListSeries) conf.getSeries().get(1)).getData()[1]
                .intValue() == 5);
        assertTrue(((ListSeries) conf.getSeries().get(2)).getData()[1]
                .intValue() == 8);
        assertTrue(((ListSeries) conf.getSeries().get(3)).getData()[1]
                .intValue() == 11);

        conf.reverseListSeries();

        assertTrue(((ListSeries) conf.getSeries().get(0)).getData()[0]
                .intValue() == 1);
        assertTrue(((ListSeries) conf.getSeries().get(1)).getData()[0]
                .intValue() == 2);

        assertTrue(((ListSeries) conf.getSeries().get(0)).getData()[1]
                .intValue() == 4);
        assertTrue(((ListSeries) conf.getSeries().get(1)).getData()[1]
                .intValue() == 5);

        assertTrue(((ListSeries) conf.getSeries().get(0)).getData()[2]
                .intValue() == 7);
        assertTrue(((ListSeries) conf.getSeries().get(1)).getData()[2]
                .intValue() == 8);

        assertTrue(((ListSeries) conf.getSeries().get(0)).getData()[3]
                .intValue() == 10);
        assertTrue(((ListSeries) conf.getSeries().get(1)).getData()[3]
                .intValue() == 11);

    }

    @Test
    public void getAllPlotOptions_getAfterChartCreation_EmptyArrayReturned() {
        Chart chart = new Chart();
        AbstractPlotOptions[] result = chart.getConfiguration()
                .getAllPlotOptions();

        assertEquals(0, result.length);
    }

    @Test
    public void setSeries_containerDataSeriesWithName_NameAppearsInLegend() {

        Configuration conf = new Configuration();
        // FIXME use enums directly
        conf.getChart().setType(ChartType.AREA.toString());

        XAxis xAxis = new XAxis();
        xAxis.setCategories(new String[] { "A", "B", "C", "D", "E" });
        conf.addxAxis(xAxis);
        YAxis yAxis = new YAxis();
        AxisTitle title = new AxisTitle();
        title.setText("Numbers");
        yAxis.setTitle(title);
        conf.addyAxis(yAxis);

        Container container = createIndexedContainer();

        ContainerDataSeries containerDataSeries1 = new ContainerDataSeries(
                container);
        containerDataSeries1.setName("Test Series1");
        containerDataSeries1.setYPropertyId("number1");
        containerDataSeries1.setNamePropertyId("name");
        // if a 'plotOptionsArea' is not set, the name of this series will not
        // be shown in legend
        // containerDataSeries1.setPlotOptions(new PlotOptionsArea());

        conf.setSeries(containerDataSeries1);

        Assert.assertTrue(toJSON(conf).contains("Test Series1"));
    }

    protected IndexedContainer createIndexedContainer() {
        IndexedContainer indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("name", String.class, null);
        indexedContainer.addContainerProperty("number1", Integer.class, null);

        Item item1 = indexedContainer.addItem(1);
        item1.getItemProperty("name").setValue("A");
        item1.getItemProperty("number1").setValue(10);

        Item item2 = indexedContainer.addItem(2);
        item2.getItemProperty("name").setValue("B");
        item2.getItemProperty("number1").setValue(11);

        Item item3 = indexedContainer.addItem(3);
        item3.getItemProperty("name").setValue("C");
        item3.getItemProperty("number1").setValue(0);

        Item item4 = indexedContainer.addItem(4);
        item4.getItemProperty("name").setValue("D");
        item4.getItemProperty("number1").setValue(15);

        Item item5 = indexedContainer.addItem(5);
        item5.getItemProperty("name").setValue("E");
        item5.getItemProperty("number1").setValue(9);

        return indexedContainer;
    }
}
