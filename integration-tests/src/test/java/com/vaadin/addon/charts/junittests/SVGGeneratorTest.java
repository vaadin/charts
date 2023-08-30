/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.junittests;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsBar;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.TreeSeries;
import com.vaadin.addon.charts.model.TreeSeriesItem;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.util.SVGGenerator;

public class SVGGeneratorTest {

    @Test
    public void test() {

        Configuration conf = createConf();

        String generatedSVG = SVGGenerator.getInstance().generate(conf);

        validateSvg(generatedSVG);

        try {
            // Some time to check CPU usage in rest manually
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            generatedSVG = SVGGenerator.getInstance().generate(conf);
            validateSvg(generatedSVG);
        }

        long middle = System.currentTimeMillis();

        // Ensure the same instance still works
        generatedSVG = SVGGenerator.getInstance().generate(conf);

        long spentMillis = System.currentTimeMillis() - middle;
        System.out.println("Time spent for rendering one svg:" + spentMillis);

        Assert.assertTrue(
                "Generating SVG took more than 200ms : " + spentMillis + "ms",
                spentMillis < 200);

        SVGGenerator.getInstance().destroy();

    }

    private void validateSvg(String generatedSVG) {
        Assert.assertTrue(generatedSVG.startsWith("<svg "));
        Assert.assertTrue(generatedSVG.contains("Population"));
        Assert.assertTrue(generatedSVG.contains("Source"));
        Assert.assertTrue(generatedSVG.contains("America"));
        Assert.assertTrue(generatedSVG.contains("millions"));
        Assert.assertTrue(generatedSVG.contains("Year 2008"));
    }

    private Configuration createConf() {
        Configuration conf = new Configuration();

        conf.setTitle("Historic World Population by Region");
        conf.setSubTitle("Source: Wikipedia.org");

        XAxis x = new XAxis();
        x.setCategories("Africa", "America", "Asia", "Europe", "Oceania");
        x.setTitle(new AxisTitle((String) null));
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        AxisTitle title = new AxisTitle("Population (millions)");
        title.setAlign(VerticalAlign.HIGH);
        y.setTitle(title);
        conf.addyAxis(y);

        Tooltip tooltip = new Tooltip();
        // tooltip.setFormatter("this.series.name +': '+ this.y +' millions'");
        conf.setTooltip(tooltip);

        PlotOptionsBar plot = new PlotOptionsBar();
        plot.setDataLabels(new DataLabels(true));
        conf.setPlotOptions(plot);

        Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setAlign(HorizontalAlign.RIGHT);
        legend.setVerticalAlign(VerticalAlign.TOP);
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
        return conf;
    }

    @Test
    public void testWide() throws InterruptedException, URISyntaxException {

        Configuration conf = new Configuration();
        conf.getChart().setType(ChartType.COLUMN);
        conf.getChart().setMarginRight(200);
        Legend legend = conf.getLegend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setAlign(HorizontalAlign.RIGHT);
        legend.setVerticalAlign(VerticalAlign.MIDDLE);
        legend.setBorderWidth(0);

        Random r = new Random();

        for (int i = 0; i < 20; i++) {
            String name = RandomStringUtils.randomAlphabetic(r.nextInt(20));
            DataSeries dataSeries = new DataSeries(name);
            dataSeries.add(new DataSeriesItem(name, r.nextInt(100)));
            conf.addSeries(dataSeries);
        }

        SVGGenerator instance = SVGGenerator.getInstance();
        String generatedSVG = instance.withHeight(400).withWidth(1200)
                .generate(conf);

        Assert.assertTrue(generatedSVG.contains("width=\"1200\""));
        Assert.assertTrue(generatedSVG.contains("height=\"400\""));

        SVGGenerator.getInstance().destroy();

    }

    @Test
    public void generate_TreemapChart_withoutException() {
        Chart chart = new Chart(ChartType.TREEMAP);
        Configuration conf = chart.getConfiguration();
        TreeSeries series = new TreeSeries();
        conf.setSeries(series);

        for (int i = 0; i < 10; ++i) {
            double y = Math.random();
            TreeSeriesItem item = new TreeSeriesItem("Attempt " + i,
                    (int) Math.floor(y * 100));
            int r = (int) (255 * y);
            int g = 255 - (int) (255 * y);
            item.setColor(new SolidColor(r, g, 100));
            series.add(item);
        }
        String generatedSVG = SVGGenerator.getInstance().generate(conf);
        Assert.assertTrue(generatedSVG != null);
    }

}
