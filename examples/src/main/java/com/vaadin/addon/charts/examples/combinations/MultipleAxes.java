package com.vaadin.addon.charts.examples.combinations;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.ZoomType;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class MultipleAxes extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Multiple axes";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        Color[] colors = getThemeColors();

        conf.getChart().setZoomType(ZoomType.XY);
        conf.setTitle("Average Monthly Weather Data for Tokyo");
        conf.setSubTitle("Source: WorldClimate.com");

        XAxis x = new XAxis();
        x.setCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
                "Sep", "Oct", "Nov", "Dec");
        conf.addxAxis(x);

        YAxis y1 = new YAxis();
        Labels labels = new Labels();
        labels.setFormatter("return this.value +'°C'");
        Style style = new Style();
        style.setColor(colors[1]);
        labels.setStyle(style);
        y1.setLabels(labels);
        y1.setOpposite(true);
        AxisTitle title = new AxisTitle("Temperature");
        style = new Style();
        style.setColor(colors[1]);
        y1.setTitle(title);
        conf.addyAxis(y1);

        YAxis y2 = new YAxis();
        y2.setGridLineWidth(0);
        title = new AxisTitle("Rainfall");
        style = new Style();
        style.setColor(colors[0]);
        y2.setTitle(title);
        labels = new Labels();
        labels.setFormatter("this.value +' mm'");
        style = new Style();
        style.setColor(colors[0]);
        labels.setStyle(style);
        y2.setLabels(labels);
        conf.addyAxis(y2);

        YAxis y3 = new YAxis();
        y3.setGridLineWidth(0);
        conf.addyAxis(y3);
        title = new AxisTitle("Sea-Level Pressure");
        style = new Style();
        style.setColor(colors[2]);
        y3.setTitle(title);
        labels = new Labels();
        labels.setFormatter("this.value +' mb'");
        style = new Style();
        style.setColor(colors[2]);
        labels.setStyle(style);
        y3.setLabels(labels);
        y3.setOpposite(true);
        chart.drawChart(conf);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("function() { "
                + "var unit = { 'Rainfall': 'mm', 'Temperature': '°C', 'Sea-Level Pressure': 'mb' }[this.series.name];"
                + "return ''+ this.x +': '+ this.y +' '+ unit; }");
        conf.setTooltip(tooltip);

        Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setAlign(HorizontalAlign.LEFT);
        legend.setX(120);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setY(80);
        legend.setFloating(true);
        conf.setLegend(legend);

        DataSeries series = new DataSeries();
        PlotOptionsColumn plotOptionsColumn = new PlotOptionsColumn();
        plotOptionsColumn.setColor(colors[0]);
        series.setPlotOptions(plotOptionsColumn);
        series.setName("Rainfall");
        series.setyAxis(1);
        series.setData(49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5,
                216.4, 194.1, 95.6, 54.4);
        conf.addSeries(series);

        series = new DataSeries();
        PlotOptionsSpline plotOptionsSpline = new PlotOptionsSpline();
        plotOptionsSpline.setColor(colors[2]);
        series.setPlotOptions(plotOptionsSpline);
        series.setName("Sea-Level Pressure");
        series.setyAxis(2);
        series.setData(1016, 1016, 1015.9, 1015.5, 1012.3, 1009.5, 1009.6,
                1010.2, 1013.1, 1016.9, 1018.2, 1016.7);
        conf.addSeries(series);

        series = new DataSeries();
        plotOptionsSpline = new PlotOptionsSpline();
        plotOptionsSpline.setColor(colors[1]);
        series.setPlotOptions(plotOptionsSpline);
        series.setName("Temperature");
        series.setData(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,
                13.9, 9.6);
        conf.addSeries(series);

        chart.drawChart(conf);

        return chart;
    }
}
