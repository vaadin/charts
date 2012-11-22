package com.vaadin.addon.charts.demoandtestapp.pie;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class PieWithGradientFill extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Pie with gradient fill";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Browser market shares at a specific website, 2010");

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setCursor(Cursor.POINTER);
        Labels dataLabels = new Labels();
        dataLabels.setEnabled(true);
        dataLabels.setColor(SolidColor.BLACK);
        dataLabels.setConnectorColor(SolidColor.BLACK);
        dataLabels
                .setFormatter("'<b>'+ this.point.name +'</b>: '+ this.percentage +' %'");
        plotOptions.setDataLabels(dataLabels);
        conf.setPlotOptions(plotOptions);

        DataSeries series = new DataSeries();
        series.setType(ChartType.PIE);
        DataSeriesItem item = new DataSeriesItem("Firefox", 45.0);
        item.setColor(getGradientColor(new SolidColor(255, 128, 0),
                new SolidColor(128, 64, 0)));
        series.addData(item);

        item = new DataSeriesItem("IE", 26.8);
        item.setColor(getGradientColor(new SolidColor(0, 255, 255),
                new SolidColor(0, 128, 128)));
        series.addData(item);

        DataSeriesItem chrome = new DataSeriesItem("Chrome", 12.8);
        chrome.setColor(getGradientColor(new SolidColor(255, 255, 0),
                new SolidColor(128, 128, 0)));
        chrome.setSliced(true);
        chrome.setSelected(true);
        series.addData(chrome);

        item = new DataSeriesItem("Safari", 8.5);
        item.setColor(getGradientColor(new SolidColor(0, 128, 255),
                new SolidColor(0, 64, 128)));
        series.addData(item);

        item = new DataSeriesItem("Opera", 6.2);
        item.setColor(getGradientColor(new SolidColor(255, 0, 0),
                new SolidColor(128, 0, 0)));
        series.addData(item);

        item = new DataSeriesItem("Others", 0.7);
        item.setColor(getGradientColor(new SolidColor(0, 128, 0),
                new SolidColor(0, 64, 0)));
        series.addData(item);

        conf.setSeries(series);

        chart.drawChart(conf);
        return chart;
    }

    private GradientColor getGradientColor(SolidColor start, SolidColor end) {
        GradientColor color = GradientColor.createRadial(0.5, 0.3, 0.7);
        color.addColorStop(0, start);
        color.addColorStop(1, end);
        return color;
    }

}
