/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples.pointclickevent;

import java.util.Arrays;
import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.themes.ValoLightTheme;

@SkipFromDemo
public class PointClickCoordinatesDonutChart
        extends AbstractPointClickCoordinatesChart {

    private static Random rand = new Random(0);
    private static Color[] colors = new ValoLightTheme().getColors();

    public PointClickCoordinatesDonutChart() {
        super(ChartType.PIE);
        rand = new Random(0);
    }

    @Override
    protected Chart createChart() {
        Chart chart = super.createChart();

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Browser market share, April, 2011");

        YAxis yaxis = new YAxis();
        yaxis.setTitle("Total percent market share");

        PlotOptionsPie pie = new PlotOptionsPie();
        pie.setShadow(false);
        conf.setPlotOptions(pie);

        conf.getTooltip().setValueSuffix("%");

        chart.drawChart(conf);

        return chart;
    }

    @Override
    protected void addSeries(Configuration conf) {
        conf.setSeries(createInnerSeries(), createOuterSeries());
    }

    private Series createInnerSeries() {
        DataSeries innerSeries = new DataSeries();
        innerSeries.setName("Browsers");
        PlotOptionsPie innerPieOptions = new PlotOptionsPie();
        innerSeries.setPlotOptions(innerPieOptions);
        innerPieOptions.setSize("237px");
        innerPieOptions.setDataLabels(new DataLabels());
        innerPieOptions.getDataLabels()
                .setFormatter("this.y > 5 ? this.point.name : null");
        innerPieOptions.getDataLabels().setColor(new SolidColor(255, 255, 255));
        innerPieOptions.getDataLabels().setDistance(-30);

        Color[] innerColors = Arrays.copyOf(colors, 5);
        innerSeries.setData(
                new String[] { "MSIE", "Firefox", "Chrome", "Safari", "Opera" },
                new Number[] { 55.11, 21.63, 11.94, 7.15, 2.14 }, innerColors);
        return innerSeries;
    }

    private Series createOuterSeries() {
        DataSeries outerSeries = new DataSeries();
        outerSeries.setName("Versions");
        PlotOptionsPie outerSeriesOptions = new PlotOptionsPie();
        outerSeries.setPlotOptions(outerSeriesOptions);
        outerSeriesOptions.setInnerSize("237px");
        outerSeriesOptions.setSize("318px");
        outerSeriesOptions.setDataLabels(new DataLabels());
        outerSeriesOptions.getDataLabels().setFormatter(
                "this.y > 1 ? '<b>'+ this.point.name +':</b> '+ this.y +'%' : null");

        DataSeriesItem[] outerItems = new DataSeriesItem[] {
                /* @formatter:off */
                new DataSeriesItem("MSIE 6.0", 10.85, color(0)),
                new DataSeriesItem("MSIE 7.0", 7.35, color(0)),
                new DataSeriesItem("MSIE 8.0", 33.06, color(0)),
                new DataSeriesItem("MSIE 9.0", 2.81, color(0)),
                new DataSeriesItem("Firefox 2.0", 0.20, color(1)),
                new DataSeriesItem("Firefox 3.0", 0.83, color(1)),
                new DataSeriesItem("Firefox 3.5", 1.58, color(1)),
                new DataSeriesItem("Firefox 3.6", 13.12, color(1)),
                new DataSeriesItem("Firefox 4.0", 5.43, color(1)),
                new DataSeriesItem("Chrome 5.0", 0.12, color(2)),
                new DataSeriesItem("Chrome 6.0", 0.19, color(2)),
                new DataSeriesItem("Chrome 7.0", 0.12, color(2)),
                new DataSeriesItem("Chrome 8.0", 0.36, color(2)),
                new DataSeriesItem("Chrome 9.0", 0.32, color(2)),
                new DataSeriesItem("Chrome 10.0", 9.91, color(2)),
                new DataSeriesItem("Chrome 11.0", 0.50, color(2)),
                new DataSeriesItem("Chrome 12.0", 0.22, color(2)),
                new DataSeriesItem("Safari 5.0", 4.55, color(3)),
                new DataSeriesItem("Safari 4.0", 1.42, color(3)),
                new DataSeriesItem("Safari Win 5.0", 0.23, color(3)),
                new DataSeriesItem("Safari 4.1", 0.21, color(3)),
                new DataSeriesItem("Safari/Maxthon", 0.20, color(3)),
                new DataSeriesItem("Safari 3.1", 0.19, color(3)),
                new DataSeriesItem("Safari 4.1", 0.14, color(3)),
                new DataSeriesItem("Opera 9.x", 0.12, color(4)),
                new DataSeriesItem("Opera 10.x", 0.37, color(4)),
                new DataSeriesItem("Opera 11.x", 1.65, color(4))
                /* @formatter:on */
        };

        outerSeries.setData(Arrays.asList(outerItems));
        return outerSeries;
    }

    /**
     * Add a small random factor to a color form the vaadin theme.
     *
     * @param colorIndex
     *            the index of the color in the colors array.
     * @return the new color
     */
    private static SolidColor color(int colorIndex) {
        SolidColor c = (SolidColor) colors[colorIndex];
        String cStr = c.toString().substring(1);

        int r = Integer.parseInt(cStr.substring(0, 2), 16);
        int g = Integer.parseInt(cStr.substring(2, 4), 16);
        int b = Integer.parseInt(cStr.substring(4, 6), 16);

        double opacity = (50 + rand.nextInt(95 - 50)) / 100.0;

        return new SolidColor(r, g, b, opacity);
    }

}
