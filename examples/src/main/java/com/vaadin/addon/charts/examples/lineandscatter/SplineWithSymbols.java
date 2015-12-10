package com.vaadin.addon.charts.examples.lineandscatter;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.MarkerSymbolEnum;
import com.vaadin.addon.charts.model.MarkerSymbolUrl;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.YAxis;
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

        Configuration configuration = chart.getConfiguration();
        // FIXME remove toString() once enums are used in model (CHARTS-159)
        configuration.getChart().setType(ChartType.SPLINE.toString());

        configuration.getTitle().setText("Monthly Average Temperature");
        configuration.getSubTitle().setText("Source: WorldClimate.com");

        configuration.getxAxis().setCategories(
                new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                        "Aug", "Sep", "Oct", "Nov", "Dec" });

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new AxisTitle("Temperature"));
        Labels labels = new Labels();
        labels.setFormat("{value}°");
        labels.setFormatter("this.value +'°'");
        yAxis.setLabels(labels);

        // FIXME missing generated API
        configuration.getTooltip().setShared(true);
        // configuration.getTooltip().setCrosshairs(true);

        PlotOptionsSpline plotOptions = new PlotOptionsSpline();
        configuration.setPlotOptions(plotOptions);
        plotOptions.setMarker(new Marker(true));
        plotOptions.getMarker().setRadius(4);
        plotOptions.getMarker().setLineColor(new SolidColor("#666666"));
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
        // FIXME missing generated API
        ls.get("26.5")
                .getMarker()
                .setSymbol(
                        new MarkerSymbolUrl(
                                "http://www.highcharts.com/demo/gfx/sun.png")
                                );

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
        ls.get("3.9")
                .getMarker()
                .setSymbol(
                        new MarkerSymbolUrl(
                                "http://www.highcharts.com/demo/gfx/snow.png")
                                );
        configuration.addSeries(ls);

        chart.drawChart(configuration);
        return chart;
    }
}
