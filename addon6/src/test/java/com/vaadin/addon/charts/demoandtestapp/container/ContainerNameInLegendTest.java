package com.vaadin.addon.charts.demoandtestapp.container;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

public class ContainerNameInLegendTest {

    @Test
    public void test() {

        Configuration conf = new Configuration();
        conf.getChart().setType(ChartType.AREA);

        XAxis xAxis = new XAxis();
        xAxis.setCategories("A", "B", "C", "D", "E");
        conf.addxAxis(xAxis);
        YAxis yAxis = new YAxis();
        yAxis.setTitle("Numbers");
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

        Assert.assertTrue(conf.toString().contains("Test Series1"));

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
