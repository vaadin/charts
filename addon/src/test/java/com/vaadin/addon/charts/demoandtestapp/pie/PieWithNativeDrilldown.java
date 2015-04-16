package com.vaadin.addon.charts.demoandtestapp.pie;

import static com.vaadin.addon.charts.model.DrillUpButtonRelativeTo.SPACINGBOX;
import static com.vaadin.addon.charts.model.style.FontWeight.BOLD;
import static com.vaadin.addon.charts.model.style.SolidColor.ALICEBLUE;
import static com.vaadin.addon.charts.model.style.SolidColor.ANTIQUEWHITE;
import static com.vaadin.addon.charts.model.style.SolidColor.FIREBRICK;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.DrillUpButton;
import com.vaadin.addon.charts.model.DrillUpButtonTheme;
import com.vaadin.addon.charts.model.Drilldown;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
@SkipFromDemo
public class PieWithNativeDrilldown extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Pie chart with eager loading drilldown";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.PIE);
        Configuration conf = chart.getConfiguration();

        conf.setTitle("Browser market share, April, 2011");
        conf.setSubTitle("Click the columns to view versions. Click again to view brands.");
        conf.getLegend().setEnabled(false);

        PlotOptionsPie column = new PlotOptionsPie();
        column.setCursor(Cursor.POINTER);
        column.setDataLabels(new Labels(true));

        conf.setPlotOptions(column);

        Tooltip tooltip = new Tooltip();
        tooltip.setHeaderFormat("<span style=\"font-size:11px\">{series.name}</span><br>");
        tooltip.setPointFormat("<span style=\"color:{point.color}\">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>");
        conf.setTooltip(tooltip);

        String[] categories = new String[] { "MSIE", "Firefox", "Chrome",
                "Safari", "Opera" };
        Number[] ys = new Number[] { 55.11, 21.63, 11.94, 7.15, 2.14 };
        DataSeries series = new DataSeries();
        series.setName("Browser brands");
        for (int i = 0; i < categories.length; i++) {
            DataSeriesItem item = new DataSeriesItem(categories[i], ys[i]);
            // no drilldown for safari
            if (!categories[i].equals("Safari")) {
                item.setDrilldown(categories[i]);
            }
            series.add(item);
        }
        conf.addSeries(series);

        Drilldown drilldown = conf.getDrilldown();
        Style style = new Style();
        style.setFontWeight(BOLD);
        style.setColor(FIREBRICK);
        style.setFontSize("16px");
        drilldown.setActiveDataLabelStyle(style);
        DrillUpButton button = drilldown.getDrillUpButton();
        button.setRelativeTo(SPACINGBOX);
        DrillUpButtonTheme theme = new DrillUpButtonTheme();
        GradientColor gradient1 = GradientColor.createLinear(0, 0, 0, 1);
        gradient1.addColorStop(0, ALICEBLUE);
        gradient1.addColorStop(1, ANTIQUEWHITE);
        theme.setFill(gradient1);
        button.setTheme(theme);

        DataSeries drill = new DataSeries("MSIE versions");
        drill.setId("MSIE");
        categories = new String[] { "MSIE 6.0", "MSIE 7.0", "MSIE 8.0",
                "MSIE 9.0" };
        ys = new Number[] { 10.85, 7.35, 33.06, 2.81 };
        drill.setData(categories, ys);
        drilldown.addSeries(drill);

        drill = new DataSeries("Firefox versions");
        drill.setId("Firefox");
        categories = new String[] { "Firefox 2.0", "Firefox 3.0",
                "Firefox 3.5", "Firefox 3.6", "Firefox 4.0" };
        ys = new Number[] { 0.20, 0.83, 1.58, 13.12, 5.43 };
        drill.setData(categories, ys);
        drilldown.addSeries(drill);

        drill = new DataSeries("Chrome versions");
        drill.setId("Chrome");
        categories = new String[] { "Chrome 5.0", "Chrome 6.0", "Chrome 7.0",
                "Chrome 8.0", "Chrome 9.0", "Chrome 10.0", "Chrome 11.0",
                "Chrome 12.0" };
        ys = new Number[] { 0.12, 0.19, 0.12, 0.36, 0.32, 9.91, 0.50, 0.22 };
        drill.setData(categories, ys);
        drilldown.addSeries(drill);

        drill = new DataSeries("Opera versions");
        drill.setId("Opera");
        categories = new String[] { "Opera 9.x", "Opera 10.x", "Opera 11.x" };
        ys = new Number[] { 0.12, 0.37, 1.65 };
        drill.setData(categories, ys);
        drilldown.addSeries(drill);

        return chart;
    }
}
