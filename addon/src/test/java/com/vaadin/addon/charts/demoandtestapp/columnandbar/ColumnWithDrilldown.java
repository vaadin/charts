package com.vaadin.addon.charts.demoandtestapp.columnandbar;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.PointClickEvent;
import com.vaadin.addon.charts.PointClickListener;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.FontWeight;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.themes.VaadinTheme;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class ColumnWithDrilldown extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Basic column";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart(ChartType.COLUMN);

        Color[] colors = new VaadinTheme().getColors();
        final Configuration conf = chart.getConfiguration();

        conf.setTitle("Browser market share, April, 2011");
        conf.setSubTitle("Click the columns to view versions. Click again to view brands.");

        XAxis x = new XAxis();
        x.setCategories("MSIE", "Firefox", "Chrome", "Safari", "Opera");
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setTitle("Total percent market share");
        conf.addyAxis(y);

        PlotOptionsColumn column = new PlotOptionsColumn();
        column.setCursor(Cursor.POINTER);
        column.setDataLabels(new Labels(true));
        column.getDataLabels().setColor(colors[0]);
        column.getDataLabels().getStyle().setFontWeight(FontWeight.BOLD);
        column.getDataLabels().setFormatter("this.y +'%'");

        conf.setPlotOptions(column);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("function() {"
                + " var point = this.point, s = this.x +':<b>'+ this.y +'% "
                + "market share</b><br/>'; " + "if (!point.drilldown) { "
                + "s += 'Click to view '+ point.category +' versions'; "
                + "} else { " + "s += 'Click to return to browser brands'; "
                + "} " //
                + "return s; }");
        conf.setTooltip(tooltip);

        final DrilldownSeries series = new DrilldownSeries(conf, 55.11, 21.63,
                11.94, 7.15, 2.14);
        series.setName("Browser brands");
        PlotOptionsColumn plotOptionsColumn = new PlotOptionsColumn();
        plotOptionsColumn.setColor(new SolidColor("#ffffff"));
        series.setPlotOptions(plotOptionsColumn);

        Drilldown drill = new Drilldown("MSIE versions");
        drill.setData(10.85, 7.35, 33.06, 2.81);
        drill.setCategories("MSIE 6.0", "MSIE 7.0", "MSIE 8.0", "MSIE 9.0");
        drill.setColor(colors[0]);
        series.addDrilldown("MSIE", drill);

        drill = new Drilldown("Firefox versions");
        drill.setData(0.20, 0.83, 1.58, 13.12, 5.43);
        drill.setCategories("Firefox 2.0", "Firefox 3.0", "Firefox 3.5",
                "Firefox 3.6", "Firefox 4.0");
        drill.setColor(colors[1]);
        series.addDrilldown("Firefox", drill);

        drill = new Drilldown("Chrome versions");
        drill.setData(0.12, 0.19, 0.12, 0.36, 0.32, 9.91, 0.50, 0.22);
        drill.setCategories("Chrome 5.0", "Chrome 6.0", "Chrome 7.0",
                "Chrome 8.0", "Chrome 9.0", "Chrome 10.0", "Chrome 11.0",
                "Chrome 12.0");
        drill.setColor(colors[2]);
        series.addDrilldown("Chrome", drill);

        drill = new Drilldown("Safari versions");
        drill.setData(4.55, 1.42, 0.23, 0.21, 0.20, 0.19, 0.14);
        drill.setCategories("Safari 5.0", "Safari 4.0", "Safari Win 5.0",
                "Safari 4.1", "Safari/Maxthon", "Safari 3.1", "Safari 4.1");
        drill.setColor(colors[3]);
        series.addDrilldown("Safari", drill);

        drill = new Drilldown("Opera versions");
        drill.setData(0.12, 0.37, 1.65);
        drill.setCategories("Opera 9.x", "Opera 10.x", "Opera 11.x");
        drill.setColor(colors[4]);
        series.addDrilldown("Opera", drill);

        conf.setSeries(series);
        conf.setExporting(false);

        chart.addColumnClickListener(new PointClickListener() {
            @Override
            public void onClick(PointClickEvent event) {
                if (!series.isDrilldown()) {
                    series.drillDown(event.getCategory());
                } else {
                    series.backToUp();
                }
                chart.drawChart(conf);
            }
        });

        chart.drawChart(conf);
        return chart;
    }
}
