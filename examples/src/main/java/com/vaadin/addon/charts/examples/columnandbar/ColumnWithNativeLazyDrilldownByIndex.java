package com.vaadin.addon.charts.examples.columnandbar;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.DrilldownCallback;
import com.vaadin.addon.charts.DrilldownEvent;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
@SkipFromDemo
public class ColumnWithNativeLazyDrilldownByIndex extends
        AbstractVaadinChartExample {

    private Map<String, DataSeries> drillSeries;
    private Configuration conf;
    private String[] topCategories;

    @Override
    public String getDescription() {
        return "Column chart with lazy loading drilldown by index";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart(ChartType.COLUMN);
        chart.setId("chart");

        conf = chart.getConfiguration();

        conf.setTitle("Browser market share, April, 2011");
        conf.setSubTitle("Click the columns to view versions. Click again to view brands.");
        conf.getLegend().setEnabled(false);

        XAxis x = new XAxis();
        x.setType(AxisType.CATEGORY);
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setTitle("Total percent market share");
        conf.addyAxis(y);

        PlotOptionsColumn column = new PlotOptionsColumn();
        column.setCursor(Cursor.POINTER);
        column.setDataLabels(new DataLabels(true));
        column.getDataLabels().setFormatter("this.y +'%'");

        conf.setPlotOptions(column);

        Tooltip tooltip = new Tooltip();
        tooltip.setEnabled(false);
        conf.setTooltip(tooltip);
        topCategories = new String[] { "MSIE", "Firefox", "Chrome", "Safari",
                "Opera" };

        DataSeries series = new DataSeries();
        series.setName("Browser brands");
        PlotOptionsColumn plotOptionsColumn = new PlotOptionsColumn();
        plotOptionsColumn.setColorByPoint(true);
        series.setPlotOptions(plotOptionsColumn);

        DataSeriesItem item = new DataSeriesItem("MSIE", 55.11);
        series.addItemWithDrilldown(item);

        item = new DataSeriesItem("Firefox", 21.63);
        series.addItemWithDrilldown(item);

        item = new DataSeriesItem("Chrome", 11.94);
        series.addItemWithDrilldown(item);

        item = new DataSeriesItem("Safari", 7.15);
        series.addItemWithDrilldown(item);

        item = new DataSeriesItem("Opera", 2.14);
        series.addItemWithDrilldown(item);

        conf.addSeries(series);

        drillSeries = new HashMap<String, DataSeries>();

        DataSeries drill = new DataSeries("MSIE versions");
        String[] categories = new String[] { "MSIE 6.0", "MSIE 7.0",
                "MSIE 8.0", "MSIE 9.0" };
        Number[] ys = new Number[] { 10.85, 7.35, 33.06, 2.81 };
        drill.setData(categories, ys);
        drillSeries.put("MSIE", drill);

        drill = new DataSeries("Firefox versions");
        categories = new String[] { "Firefox 2.0", "Firefox 3.0",
                "Firefox 3.5", "Firefox 3.6", "Firefox 4.0" };
        ys = new Number[] { 0.20, 0.83, 1.58, 13.12, 5.43 };
        drill.setData(categories, ys);
        drillSeries.put("Firefox", drill);

        drill = new DataSeries("Chrome versions");
        categories = new String[] { "Chrome 5.0", "Chrome 6.0", "Chrome 7.0",
                "Chrome 8.0", "Chrome 9.0", "Chrome 10.0", "Chrome 11.0",
                "Chrome 12.0" };
        ys = new Number[] { 0.12, 0.19, 0.12, 0.36, 0.32, 9.91, 0.50, 0.22 };
        drill.setData(categories, ys);
        drillSeries.put("Chrome", drill);

        drill = new DataSeries("Safari versions");
        categories = new String[] { "Safari 5.0", "Safari 4.0",
                "Safari Win 5.0", "Safari 4.1", "Safari/Maxthon", "Safari 3.1",
                "Safari 4.1" };
        ys = new Number[] { 4.55, 1.42, 0.23, 0.21, 0.20, 0.19, 0.14 };
        drill.setData(categories, ys);
        drillSeries.put("Safari", drill);

        drill = new DataSeries("Opera versions");
        categories = new String[] { "Opera 9.x", "Opera 10.x", "Opera 11.x" };
        ys = new Number[] { 0.12, 0.37, 1.65 };
        drill.setData(categories, ys);
        drillSeries.put("Opera", drill);

        chart.setDrilldownCallback(new DrilldownCallback() {

            @Override
            public Series handleDrilldown(DrilldownEvent event) {
                return getPointDrilldown(event.getItemIndex());
            }
        });
        return chart;
    }

    private Series getPointDrilldown(int itemIndex) {
        return drillSeries.get(topCategories[itemIndex]);
    }

}