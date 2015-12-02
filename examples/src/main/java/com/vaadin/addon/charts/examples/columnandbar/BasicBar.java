package com.vaadin.addon.charts.examples.columnandbar;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsBar;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class BasicBar extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Basic bar";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.BAR);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Historic World Population by Region");
        conf.setSubTitle("Source: Wikipedia.org");

        XAxis x = new XAxis();
        x.setCategories(new String[] { "Africa", "America", "Asia", "Europe",
                "Oceania" });
        x.setTitle(new Title((String) null));
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        Title title = new Title("Population (millions)");
        // FIXME remove toString() once enums are used in model (CHARTS-159)
        title.setAlign(VerticalAlign.MIDDLE.toString());
        y.setTitle(title);
        conf.addyAxis(y);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("this.series.name +': '+ this.y +' millions'");
        conf.setTooltip(tooltip);

        PlotOptionsBar plot = new PlotOptionsBar();
        plot.setDataLabels(new DataLabels(true));
        conf.setPlotOptions(plot);

        Legend legend = new Legend();
        // FIXME remove toString() once enums are used in model (CHARTS-159)
        legend.setLayout(LayoutDirection.VERTICAL.toString());
        legend.setAlign(HorizontalAlign.RIGHT.toString());
        legend.setVerticalAlign(VerticalAlign.TOP.toString());
        legend.setX(-100);
        legend.setY(100);
        legend.setFloating(true);
        legend.setBorderWidth(1);
        legend.setBackgroundColor(new SolidColor("#FFFFFF"));
        legend.setShadow(true);
        conf.setLegend(legend);

        conf.disableCredits();

        List<Series> series = new ArrayList<Series>();
        series.add(new ListSeries("Year 1800", 107, 31, 635, 203, 2));
        series.add(new ListSeries("Year 1900", 133, 156, 947, 408, 6));
        series.add(new ListSeries("Year 2008", 973, 914, 4054, 732, 34));
        conf.setSeries(series);

        chart.drawChart(conf);

        return chart;
    }
}
