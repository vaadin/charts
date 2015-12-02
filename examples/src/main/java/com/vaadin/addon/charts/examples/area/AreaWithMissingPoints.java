package com.vaadin.addon.charts.examples.area;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.Subtitle;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class AreaWithMissingPoints extends AbstractVaadinChartExample {
    @Override
    public String getDescription() {
        return "Area with missing points";
    }

    @Override
    protected Component getChart() {

        Chart chart = new Chart(ChartType.AREA);

        Configuration conf = chart.getConfiguration();

        conf.getChart().setSpacingBottom(30);

        conf.setTitle(new Title("Fruit consumption *"));

        Subtitle subTitle = new Subtitle(
                "* Jane\'s banana consumption is unknown");
        subTitle.setFloating(true);
        subTitle.setY(15);
        // FIXME remove toString() once enums are used in model (CHARTS-159)
        subTitle.setAlign(HorizontalAlign.RIGHT.toString());
        subTitle.setVerticalAlign(VerticalAlign.BOTTOM.toString());
        conf.setSubTitle(subTitle);

        XAxis xAxis = new XAxis();
        xAxis.setCategories(new String[] { "Apples", "Pears", "Oranges",
                "Bananas", "Grapes", "Plums", "Strawberries", "Raspberries" });
        conf.addxAxis(xAxis);

        Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL.toString());
        legend.setAlign(HorizontalAlign.LEFT.toString());
        legend.setFloating(true);
        legend.setVerticalAlign(VerticalAlign.TOP.toString());
        legend.setX(150);
        legend.setY(100);
        legend.setBorderWidth(1);
        legend.setBackgroundColor(new SolidColor("#ffffff"));
        conf.setLegend(legend);

        PlotOptionsArea plotOptions = new PlotOptionsArea();
        plotOptions.setFillOpacity(0.5);
        conf.setPlotOptions(plotOptions);

        conf.addSeries(new ListSeries("John", 0, 1, 4, 4, 5, 2, 3, 7));
        conf.addSeries(new ListSeries("Jane", 1, 0, 3, null, 3, 1, 2, 1));

        chart.drawChart(conf);

        return chart;
    }
}
