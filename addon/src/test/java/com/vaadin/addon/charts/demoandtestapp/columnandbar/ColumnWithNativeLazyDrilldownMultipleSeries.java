package com.vaadin.addon.charts.demoandtestapp.columnandbar;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartDrilldownEvent;
import com.vaadin.addon.charts.ChartDrilldownListener;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Drilldown;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.shared.DrilldownPointDetails;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
@SkipFromDemo
public class ColumnWithNativeLazyDrilldownMultipleSeries extends
        AbstractVaadinChartExample {

    private Map<String, DataSeries> drillSeries;
    private Configuration conf;
    private String[] topCategories;

    @Override
    public String getDescription() {
        return "Column chart with laxy loading drilldown by id";
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
        column.setDataLabels(new Labels(true));
        column.getDataLabels().setFormatter("this.y +'%'");

        conf.setPlotOptions(column);

        Tooltip tooltip = new Tooltip();
        tooltip.setHeaderFormat("<span style=\"font-size:11px\">{series.name}</span><br>");
        tooltip.setPointFormat("<span style=\"color:{point.color}\">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>");
        conf.setTooltip(tooltip);

        createSeries(1);
        createSeries(2);

        drillSeries = new HashMap<String, DataSeries>();

        addDrillSeries(1);
        addDrillSeries(2);

        chart.addChartDrilldownListener(new ChartDrilldownListener() {

            @Override
            public void onDrilldown(ChartDrilldownEvent event) {
                if (!event.isHasDrilldownSeries()) {
                    if (event.getPoint() != null) {
                        doPointDrilldown(event.getPoint());
                    } else if (event.getPoints() != null) {
                        for (DrilldownPointDetails point : event.getPoints()) {
                            doPointDrilldown(point);
                        }
                    }
                } else {
                    System.out.println("has!");
                }
            }
        });
        return chart;
    }

    private void createSeries(int index) {
        topCategories = new String[] { "MSIE", "Firefox", "Chrome", "Safari",
                "Opera" };
        Number[] ys = new Number[] { 55.11 - index - index - index,
                21.63 - index, 11.94 + index, 7.15 + index + index,
                2.14 + index };
        DataSeries series = new DataSeries();
        series.setName("Browser brands" + index);
        for (int i = 0; i < topCategories.length; i++) {
            DataSeriesItem item = new DataSeriesItem(topCategories[i], ys[i]);
            item.setDrilldown(topCategories[i] + index);
            item.setId(topCategories[i] + index);
            series.add(item);
        }

        conf.addSeries(series);

        Drilldown drilldown = conf.getDrilldown();

        DataSeries drill = new DataSeries("MSIE versions" + index);
        drill.setId("MSIE" + index);
        String[] categories = new String[] { "MSIE 6.0", "MSIE 7.0",
                "MSIE 8.0", "MSIE 9.0" };
        ys = new Number[] { 10.85 + index, 7.35 - index - index,
                33.06 + index + index, 2.81 - index };
        drill.setData(categories, ys);
        drilldown.addSeries(drill);

    }

    private void addDrillSeries(int index) {
        DataSeries drill = new DataSeries("MSIE versions" + index);
        String[] categories = new String[] { "MSIE 6.0", "MSIE 7.0",
                "MSIE 8.0", "MSIE 9.0" };
        Number[] ys = new Number[] { 10.85, 7.35, 33.06, 2.81 };
        drill.setData(categories, ys);
        drillSeries.put("MSIE" + index, drill);

        drill = new DataSeries("Firefox versions" + index);
        categories = new String[] { "Firefox 2.0", "Firefox 3.0",
                "Firefox 3.5", "Firefox 3.6", "Firefox 4.0" };
        ys = new Number[] { 0.20, 0.83, 1.58, 13.12, 5.43 };
        drill.setData(categories, ys);
        drillSeries.put("Firefox" + index, drill);

        drill = new DataSeries("Chrome versions" + index);
        categories = new String[] { "Chrome 5.0", "Chrome 6.0", "Chrome 7.0",
                "Chrome 8.0", "Chrome 9.0", "Chrome 10.0", "Chrome 11.0",
                "Chrome 12.0" };
        ys = new Number[] { 0.12, 0.19, 0.12, 0.36, 0.32, 9.91, 0.50, 0.22 };
        drill.setData(categories, ys);
        drillSeries.put("Chrome" + index, drill);

        drill = new DataSeries("Safari versions" + index);
        categories = new String[] { "Safari 5.0", "Safari 4.0",
                "Safari Win 5.0", "Safari 4.1", "Safari/Maxthon", "Safari 3.1",
                "Safari 4.1" };
        ys = new Number[] { 4.55, 1.42, 0.23, 0.21, 0.20, 0.19, 0.14 };
        drill.setData(categories, ys);
        drillSeries.put("Safari" + index, drill);

        drill = new DataSeries("Opera versions" + index);
        categories = new String[] { "Opera 9.x", "Opera 10.x", "Opera 11.x" };
        ys = new Number[] { 0.12, 0.37, 1.65 };
        drill.setData(categories, ys);
        drillSeries.put("Opera" + index, drill);

    }

    private void doPointDrilldown(DrilldownPointDetails point) {
        Drilldown drilldown = conf.getDrilldown();
        if (point.getId() != null) {
            String pointId = point.getId();
            DataSeries series = drillSeries.get(pointId);
            if (series != null) {
                drilldown.addPointDrilldown(pointId, series);
            }
        } else {
            int seriesIndex = point.getSeriesIndex();
            DataSeries series = drillSeries.get(topCategories[point.getIndex()
                    + (seriesIndex + 1)]);
            int pointIndex = point.getIndex();
            if (series != null) {
                drilldown.addPointDrilldown(seriesIndex, pointIndex, series);
            }
        }

    }
}
