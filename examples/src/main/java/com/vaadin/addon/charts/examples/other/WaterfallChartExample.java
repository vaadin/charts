package com.vaadin.addon.charts.examples.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.DataSeriesItem3d;
import com.vaadin.addon.charts.model.PlotOptionsWaterfall;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.WaterFallSum;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class WaterfallChartExample extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Bubble Chart";
    }

    Color[] colors = getThemeColors();

    private Color sumColor = colors[0];
    private Color upColor = colors[1];
    private Color color = colors[2];

    @Override
    protected Component getChart() {
        Chart chart = new Chart();

        Configuration conf = chart.getConfiguration();
        conf.setTitle((String) null);

        DataSeries dataSeries = new DataSeries();

        dataSeries.add(new DataSeriesItem("Start", 120000));
        dataSeries.add(new DataSeriesItem("Product Revenue", 569000));
        dataSeries.add(new DataSeriesItem("Service Revenue", 231000));
        WaterFallSum positiveBalanse = new WaterFallSum("Positive Balance");
        positiveBalanse.setColor(sumColor);
        positiveBalanse.setIntermediate(true);
        dataSeries.add(positiveBalanse);

        dataSeries.add(new DataSeriesItem("Fixed Costs", -342000));
        dataSeries.add(new DataSeriesItem("Variable Costs", -233000));
        WaterFallSum balance = new WaterFallSum("Balance");
        balance.setColor(sumColor);
        dataSeries.add(balance);

        PlotOptionsWaterfall opts = new PlotOptionsWaterfall();
        opts.setColor(color);
        opts.setUpColor(upColor);
        DataLabels dataLabels = new DataLabels(true);
        dataLabels.setVerticalAlign(VerticalAlign.TOP);
        dataLabels.setY(-30);
        dataLabels.setFormatter("this.y / 1000 + 'k'");
        opts.setDataLabels(dataLabels);

        dataSeries.setPlotOptions(opts);

        conf.addSeries(dataSeries);

        conf.getxAxis().setType(AxisType.CATEGORY);

        return chart;
    }

    public DataSeriesItem item(int x, int y, int z) {
        DataSeriesItem3d dataSeriesItem = new DataSeriesItem3d();
        dataSeriesItem.setX(x);
        dataSeriesItem.setY(y);
        dataSeriesItem.setZ(z);
        return dataSeriesItem;
    }

}
