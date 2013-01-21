package com.vaadin.addon.charts.demoandtestapp.lineandscatter;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.MarkerSymbolEnum;
import com.vaadin.addon.charts.model.MarkerSymbolUrl;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

public class SplineWithSymbols extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Spline With Symbols";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");

        Configuration configuration = new Configuration();
        configuration.getChart().setType(ChartType.SPLINE);

        configuration.getTitle().setText("Monthly Average Temperature");
        configuration.getSubTitle().setText("Source: WorldClimate.com");

        configuration.getxAxis().setCategories("Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

        Axis yAxis = configuration.getyAxis();
        yAxis.setTitle(new Title("Temperature"));
        Labels labels = new Labels();
        labels.setFormatter("this.value +'°'");
        yAxis.setLabels(labels);

        configuration.getTooltip().setShared(true);
        configuration.getTooltip().setCrosshairs(true);

        PlotOptionsSpline plotOptions = new PlotOptionsSpline();
        configuration.setPlotOptions(plotOptions);
        plotOptions.setMarker(new Marker(true));
        plotOptions.getMarker().setRadius(4);
        plotOptions.getMarker()
                .setLineColor(new SolidColor("#666666"));
        plotOptions.getMarker().setLineWidth(1);

        DataSeries ls = new DataSeries();
        plotOptions = new PlotOptionsSpline();
        Marker marker = new Marker();
        marker.setSymbol(MarkerSymbolEnum.SQUARE);
        plotOptions.setMarker(marker);
        ls.setPlotOptions(plotOptions);
        ls.setName("Tokyo");

        ls.setData(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,
                13.9, 9.6);
        ls.getData("26.5")
                .getMarker()
                .setSymbol(
                        new MarkerSymbolUrl(
                                "url(http://www.highcharts.com/demo/gfx/sun.png)"));

        configuration.addSeries(ls);

        ls = new DataSeries();
        plotOptions = new PlotOptionsSpline();
        marker = new Marker();
        marker.setSymbol(MarkerSymbolEnum.DIAMOND);
        plotOptions.setMarker(marker);
        ls.setPlotOptions(plotOptions);
        ls.setName("London");
        ls.setData(3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6,
                4.8);
        ls.getData("3.9")
                .getMarker()
                .setSymbol(
                        new MarkerSymbolUrl(
                                "url(http://www.highcharts.com/demo/gfx/snow.png)"));
        configuration.addSeries(ls);

        chart.drawChart(configuration);
        return chart;
    }
}
