package com.vaadin.addon.charts.examples.lineandscatter;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartClickEvent;
import com.vaadin.addon.charts.ChartClickListener;
import com.vaadin.addon.charts.PointClickEvent;
import com.vaadin.addon.charts.PointClickListener;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.HorizontalAlign;
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
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class BasicLineGettingMousePointerPosition extends
        AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Chart opening a sub window on point that was clicked.";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        chart.setId("chart");

        Color[] colors = getThemeColors();

        Configuration conf = chart.getConfiguration();

        conf.getChart().setZoomType(ZoomType.XY);

        conf.setTitle("Average Monthly Temperature and Rainfall in Tokyo");
        conf.setSubTitle("Source: WorldClimate.com");

        XAxis x = new XAxis();
        x.setCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
                "Sep", "Oct", "Nov", "Dec");
        conf.addxAxis(x);

        YAxis primary = new YAxis();
        primary.setTitle("Temperature");
        Style style = new Style();
        style.setColor(colors[0]);
        primary.getTitle().setStyle(style);
        conf.addyAxis(primary);

        YAxis snd = new YAxis();
        snd.setTitle("Rainfall");
        snd.setOpposite(true);
        style = new Style();
        style.setColor(colors[1]);
        snd.getTitle().setStyle(style);
        conf.addyAxis(snd);

        Tooltip tooltip = new Tooltip(false);
        conf.setTooltip(tooltip);

        Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setAlign(HorizontalAlign.LEFT);
        legend.setX(120);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setY(100);
        legend.setFloating(true);
        legend.setBackgroundColor(new SolidColor("#FFFFFF"));
        conf.setLegend(legend);

        DataSeries series = new DataSeries();
        series.setPlotOptions(new PlotOptionsColumn());
        series.setName("Rainfall");
        series.setData(49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5,
                216.4, 194.1, 95.6, 54.4);
        series.setyAxis(1);
        conf.addSeries(series);

        series = new DataSeries();
        PlotOptionsSpline plotOptions = new PlotOptionsSpline();
        series.setPlotOptions(plotOptions);
        series.setName("Temperature");
        series.setData(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,
                13.9, 9.6);
        conf.addSeries(series);

        chart.addPointClickListener(new PointClickListener() {

            @Override
            public void onClick(PointClickEvent event) {
                Window win = new Window("PointClickEvent window");
                win.setContent(new Label(
                        "Browser client area coordinates: point X:"
                                + event.getAbsoluteX() + " Y:"
                                + event.getAbsoluteY()));
                win.setPositionX(event.getAbsoluteX());
                win.setPositionY(event.getAbsoluteY());
                getUI().addWindow(win);
            }
        });

        chart.addChartClickListener(new ChartClickListener() {

            @Override
            public void onClick(ChartClickEvent event) {
                Window win = new Window("Chart Click Event Window");
                win.setContent(new Label(
                        "Browser client area coordinates: point X:"
                                + event.getAbsoluteX() + " Y:"
                                + event.getAbsoluteY()));
                win.setPositionX(event.getAbsoluteX());
                win.setPositionY(event.getAbsoluteY());
                getUI().addWindow(win);

            }
        });

        return chart;
    }

}
