package com.vaadin.addon.charts.demoandtestapp.area;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HoverState;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.MarkerStates;
import com.vaadin.addon.charts.model.MarkerSymbolEnum;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.SubTitle;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class BasicArea extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Basic Area";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.AREA);

        Configuration conf = chart.getConfiguration();

        conf.setTitle(new Title("US and USSR nuclear stockpiles"));
        conf.setSubTitle(new SubTitle(
                "Source: <a href=\"http://thebulletin.metapress.com/content/c4120650912x74k7/fulltext.pdf\">thebulletin.metapress.com</a>"));

        PlotOptionsArea plotOptions = new PlotOptionsArea();
        plotOptions.setPointStart(1940);
        Marker marker = new Marker();
        marker.setEnabled(false);
        marker.setSymbol(MarkerSymbolEnum.CIRCLE);
        marker.setRadius(2);
        marker.setStates(new MarkerStates(new HoverState(true)));
        plotOptions.setMarker(marker);
        conf.getPlotOptions().setArea(plotOptions);

        XAxis xAxis = new XAxis();
        Labels labels = new Labels();
        // Display x axis value (year) as non formatted integer
        labels.setFormatter("this.value");
        xAxis.setLabels(labels);
        conf.addxAxis(xAxis);

        YAxis yAxis = new YAxis();
        yAxis.setTitle(new Title("Nuclear weapon states"));
        labels = new Labels();
        // display y axis value in kilos as there is such a pile of weapons
        labels.setFormatter("this.value / 1000 +'k'");
        yAxis.setLabels(labels);
        conf.addyAxis(yAxis);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("this.series.name +' produced <b>'+ Highcharts.numberFormat(this.y, 0) +'</b><br/>warheads in '+ this.x");
        conf.setTooltip(tooltip);

        final Number usaNumbers[] = new Number[] { null, null, null, null,
                null, 6, 11, 32, 110, 235, 369, 640, 1005, 1436, 2063, 3057,
                4618, 6444, 9822, 15468, 20434, 24126, 27387, 29459, 31056,
                31982, 32040, 31233, 29224, 27342, 26662, 26956, 27912, 28999,
                28965, 27826, 25579, 25722, 24826, 24605, 24304, 23464, 23708,
                24099, 24357, 24237, 24401, 24344, 23586, 22380, 21004, 17287,
                14747, 13076, 12555, 12144, 11009, 10950, 10871, 10824, 10577,
                10527, 10475, 10421, 10358, 10295, 10104 };
        conf.addSeries(new ListSeries("USA", usaNumbers));
        final Number ussrNumbers[] = new Number[] { null, null, null, null,
                null, null, null, null, null, null, 5, 25, 50, 120, 150, 200,
                426, 660, 869, 1060, 1605, 2471, 3322, 4238, 5221, 6129, 7089,
                8339, 9399, 10538, 11643, 13092, 14478, 15915, 17385, 19055,
                21205, 23044, 25393, 27935, 30062, 32049, 33952, 35804, 37431,
                39197, 45000, 43000, 41000, 39000, 37000, 35000, 33000, 31000,
                29000, 27000, 25000, 24000, 23000, 22000, 21000, 20000, 19000,
                18000, 18000, 17000, 16000 };
        conf.addSeries(new ListSeries("USSR/Russia", ussrNumbers));

        chart.drawChart(conf);

        return chart;
    }

}
