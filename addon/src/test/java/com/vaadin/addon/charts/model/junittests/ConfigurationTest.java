/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model.junittests;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AbstractPlotOptions;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataProviderSeries;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;

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
    public void getPlotOptions_getAfterChartCreation_EmptyCollectionReturned() {
        Chart chart = new Chart();
        Collection<AbstractPlotOptions> result =
            chart.getConfiguration().getPlotOptions();
        assertEquals(0, result.size());
    }

    @Test
    public void getPlotOptionsForType_noPlotOptionFound_ReturnNull() {
        Chart chart = new Chart();
        AbstractPlotOptions result =
            chart.getConfiguration().getPlotOptions(ChartType.LINE);
        assertNull(result);
    }

    @Test
    public void getPlotOptionsForType_PlotOptionsLineIsCreated_ReturnTheLinePlotOptions() {
        Chart chart = new Chart();
        PlotOptionsLine expected = new PlotOptionsLine();
        chart.getConfiguration().setPlotOptions(expected);
        AbstractPlotOptions result =
            chart.getConfiguration().getPlotOptions(ChartType.LINE);
        assertEquals(expected, result);
    }

    @Test
    public void setSeries_containerDataSeriesWithName_NameAppearsInLegend() {

        Configuration conf = new Configuration();
        conf.getChart().setType(ChartType.AREA);

        XAxis xAxis = new XAxis();
        xAxis.setCategories("A", "B", "C", "D", "E");
        conf.addxAxis(xAxis);
        YAxis yAxis = new YAxis();
        AxisTitle title = new AxisTitle();
        title.setText("Numbers");
        yAxis.setTitle(title);
        conf.addyAxis(yAxis);

        DataProviderSeries<Pair> ds = new DataProviderSeries<>(createDataProvider());


        ds.setName("Test Series1");
        ds.setY(Pair::getValue);
        ds.setPointName(Pair::getName);

        // if a 'plotOptionsArea' is not set, the name of this series will not
        // be shown in legend
        // containerDataSeries1.setPlotOptions(new PlotOptionsArea());

        conf.setSeries(ds);

        Assert.assertTrue(toJSON(conf).contains("Test Series1"));
    }

    private class Pair {
        private String name;
        private Integer value;

        public Pair(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Integer getValue() {
            return value;
        }
    }

    private DataProvider<Pair, ?> createDataProvider() {
        Collection<Pair> col =new ArrayList<>();
        col.add(new Pair("A",10));
        col.add(new Pair("B",11));
        col.add(new Pair("C",10));
        col.add(new Pair("D",15));
        col.add(new Pair("E",9));

        return new ListDataProvider<>(col);
    }
}
