package com.vaadin.addon.charts.model.junittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AbstractPlotOptions;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.XAxis;

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

}
