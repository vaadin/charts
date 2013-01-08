package com.vaadin.addon.charts.demoandtestapp.pie;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.CreditPosition;
import com.vaadin.addon.charts.model.Credits;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class PieChartWithCredits extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Pie chart with customized credits";
    }

    @Override
    protected Component getChart() {
        Component ret = createChart();
        ret.setWidth("100%");
        ret.setHeight("450px");
        return ret;
    }

    public static Chart createChart() {
        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Browser market shares at a specific website, 2010");

        Credits credits = new Credits(true);
        credits.setPosition(new CreditPosition());
        credits.getPosition().setHorizontalAlign(HorizontalAlign.CENTER);
        credits.getPosition().setVerticalAlign(VerticalAlign.MIDDLE);
        credits.getPosition().setX(0);
        credits.getPosition().setY(10);
        conf.setCredits(credits);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setCursor(Cursor.POINTER);
        Labels dataLabels = new Labels();
        dataLabels.setEnabled(true);
        dataLabels.setColor(new SolidColor(0, 0, 0));
        dataLabels.setConnectorColor(new SolidColor(0, 0, 0));
        dataLabels
                .setFormatter("'<b>'+ this.point.name +'</b>: '+ this.percentage +' %'");
        plotOptions.setDataLabels(dataLabels);
        conf.setPlotOptions(plotOptions);

        DataSeries series = new DataSeries();
        series.addData(new DataSeriesItem("Firefox", 45.0));
        series.addData(new DataSeriesItem("IE", 26.8));
        DataSeriesItem chrome = new DataSeriesItem("Chrome", 12.8);
        chrome.setSliced(true);
        chrome.setSelected(true);
        series.addData(chrome);
        series.addData(new DataSeriesItem("Safari", 8.5));
        series.addData(new DataSeriesItem("Opera", 6.2));
        series.addData(new DataSeriesItem("Others", 0.7));
        conf.setSeries(series);

        chart.drawChart(conf);

        return chart;
    }

}
