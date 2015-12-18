package com.vaadin.addon.charts.examples.columnandbar;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartDrillupEvent;
import com.vaadin.addon.charts.ChartDrillupListener;
import com.vaadin.addon.charts.DrilldownCallback;
import com.vaadin.addon.charts.DrilldownEvent;
import com.vaadin.addon.charts.PointClickEvent;
import com.vaadin.addon.charts.PointClickListener;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@SkipFromDemo
public class ColumnWithLazyMultiLevelDrilldown extends
        AbstractVaadinChartExample {

    private Map<String, DataSeries> drillSeries;
    private Configuration conf;
    private Label log;

    @Override
    public String getDescription() {
        return "Column chart with eager loading drilldown";
    }

    public ColumnWithLazyMultiLevelDrilldown() {
        super();
        log = new Label("log");
        log.setId("log");
    }

    @Override
    protected Component getChart() {
        VerticalLayout layout = new VerticalLayout();
        final Chart chart = new Chart(ChartType.COLUMN);
        chart.setId("chart");

        conf = chart.getConfiguration();

        conf.setTitle("Global happiness index");
        conf.setSubTitle("Source: www.happyplanetindex.org");
        conf.getLegend().setEnabled(false);

        XAxis x = new XAxis();
        x.setType(AxisType.CATEGORY);
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setTitle(new AxisTitle("Total"));
        conf.addyAxis(y);

        PlotOptionsColumn column = new PlotOptionsColumn();
        column.setCursor(Cursor.POINTER);
        column.setDataLabels(new DataLabels(true));

        conf.setPlotOptions(column);

        DataSeries series = new DataSeries();
        series.setName("Regions");
        PlotOptionsColumn plotOptionsColumn = new PlotOptionsColumn();
        plotOptionsColumn.setColorByPoint(true);
        series.setPlotOptions(plotOptionsColumn);

        DataSeriesItem item = new DataSeriesItem("Latin America and Carribean",
                60);
        item.setId("Latin America and Carribean");
        series.addItemWithDrilldown(item);

        item = new DataSeriesItem("Western Nations", 50);
        item.setId("Western Nations");
        series.addItemWithDrilldown(item);

        conf.addSeries(series);

        drillSeries = new HashMap<String, DataSeries>();

        DataSeries drill = new DataSeries(
                "Latin America and Carribean Countries");

        item = new DataSeriesItem("Costa Rica", 64);
        item.setId("Costa Rica");
        drill.addItemWithDrilldown(item);

        item = new DataSeriesItem("Colombia", 59.8);
        item.setId("Colombia");
        drill.addItemWithDrilldown(item);

        item = new DataSeriesItem("Belize", 59.3);
        item.setId("Belize");
        drill.addItemWithDrilldown(item);

        drillSeries.put("Latin America and Carribean", drill);

        drill = new DataSeries("Western Nations Countries");

        item = new DataSeriesItem("New Zealand", 51.6);
        item.setId("New Zealand");
        drill.addItemWithDrilldown(item);

        item = new DataSeriesItem("Norway", 51.4);
        item.setId("Norway");
        drill.addItemWithDrilldown(item);

        item = new DataSeriesItem("Switzerland", 50.3);
        item.setId("Switzerland");
        drill.addItemWithDrilldown(item);

        drillSeries.put("Western Nations", drill);

        drill = new DataSeries("Details Costa Rica");
        drill.setId("Details Costa Rica");
        String[] categories = new String[] { "Life Expectancy",
                "Well-being (0-10)", "Footprint (gha/capita)" };
        Number[] ys = new Number[] { 79.3, 7.3, 2.5 };
        drill.setData(categories, ys);
        drillSeries.put("Costa Rica", drill);

        drill = new DataSeries("Details Colombia");
        drill.setId("Details Colombia");
        ys = new Number[] { 73.7, 6.4, 1.8 };
        drill.setData(categories, ys);
        drillSeries.put("Colombia", drill);

        drill = new DataSeries("Details Belize");
        drill.setId("Details Belize");
        ys = new Number[] { 76.1, 6.5, 2.1 };
        drill.setData(categories, ys);
        drillSeries.put("Belize", drill);

        drill = new DataSeries("Details New Zealand");
        drill.setId("Details New Zealand");
        ys = new Number[] { 80.7, 7.2, 4.3 };

        drill.setData(categories, ys);
        drillSeries.put("New Zealand", drill);

        drill = new DataSeries("Details Norway");
        drill.setId("Details Norway");
        ys = new Number[] { 81.1, 7.6, 4.8 };
        drill.setData(categories, ys);
        drillSeries.put("Norway", drill);

        drill = new DataSeries("Details Switzerland");
        drill.setId("Details Switzerland");
        ys = new Number[] { 82.3, 7.5, 5.0 };
        drill.setData(categories, ys);
        drillSeries.put("Switzerland", drill);

        chart.setDrilldownCallback(new DrilldownCallback() {

            @Override
            public Series handleDrilldown(DrilldownEvent event) {
                log("DrilldownEvent: " + event.getItem().getId());
                return getPointDrilldown(event.getItem());
            }
        });

        chart.addPointClickListener(new PointClickListener() {

            @Override
            public void onClick(PointClickEvent event) {
                log("PointClickEvent: " + event.getSeries().getName()
                        + " index :" + event.getPointIndex());
            }
        });

        chart.addChartDrillupListener(new ChartDrillupListener() {

            @Override
            public void onDrillup(ChartDrillupEvent event) {
                log("ChartDrillupEvent");
            }
        });

        layout.addComponent(chart);
        layout.addComponent(log);
        return layout;
    }

    private void log(String newStringValue) {
        log.setValue(newStringValue);
    }

    private Series getPointDrilldown(DataSeriesItem point) {
        String pointId = point.getId();
        DataSeries series = drillSeries.get(pointId);
        return series;
    }
}
