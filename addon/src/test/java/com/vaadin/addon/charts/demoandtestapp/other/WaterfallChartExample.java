package com.vaadin.addon.charts.demoandtestapp.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.DataSeriesItem3d;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsWaterfall;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.WaterFallSum;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.FontWeight;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class WaterfallChartExample extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Bubble Chart";
    }

    private Color sumColor = new SolidColor("black");
    private Color upColor = new SolidColor("green");
    private Color color = new SolidColor("red");

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
        WaterFallSum balanse = new WaterFallSum("Balance");
        balanse.setColor(sumColor);
        dataSeries.add(balanse);

        PlotOptionsWaterfall opts = new PlotOptionsWaterfall();
        opts.setColor(color);
        opts.setUpColor(upColor);
        Labels dataLabels = new Labels(true);
        dataLabels.setVerticalAlign(VerticalAlign.TOP);
        dataLabels.setY(-30);
        dataLabels.setColor(new SolidColor("black"));
        dataLabels
                .setFormatter("this.y / 1000 + 'k'");
        Style style = new Style();
        style.setColor(new SolidColor("#ffffff"));
        style.setFontWeight(FontWeight.BOLD);
        dataLabels.setStyle(style);
        opts.setDataLabels(dataLabels);
        opts.setPointPadding(0);

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
