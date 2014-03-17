package com.vaadin.addon.charts.demoandtestapp;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.ArrayUtils;
import static org.apache.xalan.xsltc.compiler.util.Type.Int;

public class PartialUpdateConflicts extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Test that partial updates dont mess data. Use ?debug mode!";
    }

    private Integer[] data = new Integer[]{1, 2, 3};

    @Override
    protected Component getChart() {
        final Chart chart = new Chart();
        chart.setHeight("200px");

        final ListSeries listSeries = new ListSeries(data);

        chart.getConfiguration().addSeries(listSeries);

        Button button = new Button("Do stuff", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                listSeries.addData(100, true, true);
                ArrayUtils.reverse(data);
                chart.getConfiguration().setSeries(new ListSeries(data));
                chart.drawChart();
            }
        });
        button.setId("b1");

        final Chart chart2 = new Chart();
        chart2.setHeight("200px");

        final DataSeries ds = new DataSeries();
        int i = 0;
        for (Integer value : data) {
            ds.add(new DataSeriesItem(i++, value));
        }

        chart2.getConfiguration().addSeries(ds);

        Button button2 = new Button("Do stuff DataSeries", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                final DataSeriesItem dataSeriesItem = new DataSeriesItem(ds.size(), new Random().nextInt(10));
                ds.add(dataSeriesItem);
                ArrayUtils.reverse(data);
                chart2.getConfiguration().setSeries(new ListSeries(data));
                chart2.drawChart();
            }
        });
        button2.setId("b2");

        return new VerticalLayout(chart, button, chart2, button2);
    }

}
