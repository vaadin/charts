package com.vaadin.addon.charts.examples.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.Back;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Frame;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Options3d;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

@SkipFromDemo
//Test for background with Gradient Color
public class Basic3DColumnWithGradientBackground extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Basic 3d columns";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.COLUMN);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Monthly Average Rainfall");
        conf.setSubTitle("Source: WorldClimate.com");

        XAxis x = new XAxis();
        x.setCategories(new String[] { "Jan", "Feb", "Mar", "Apr" });
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        y.setTitle("Rainfall (mm)");
        conf.addyAxis(y);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("this.x +': '+ this.y +' mm'");
        conf.setTooltip(tooltip);

        PlotOptionsColumn plot = new PlotOptionsColumn();
        plot.setPointPadding(0.2);
        plot.setBorderWidth(0);
        plot.setGroupZPadding(10);
        conf.setPlotOptions(plot);

        Options3d options3d = new Options3d();
        options3d.setEnabled(true);
        options3d.setAlpha(5);
        options3d.setBeta(30);
        options3d.setDepth(100);
        options3d.setViewDistance(200);
        Frame frame = new Frame();
        options3d.setFrame(frame);
        frame.setBack(new Back());
        GradientColor c= GradientColor.createLinear(0, 0, 1, 0);
        c.addColorStop(0,SolidColor.RED);
        c.addColorStop(1,SolidColor.AQUAMARINE);

        frame.getBack().setColor(c);
        frame.getBack().setSize(1);
        conf.getChart().setOptions3d(options3d);

        conf.addSeries(new ListSeries("Tokyo", 49.9, 71.5, 106.4, 129.2));
        chart.drawChart(conf);
        return chart;

    }
}
