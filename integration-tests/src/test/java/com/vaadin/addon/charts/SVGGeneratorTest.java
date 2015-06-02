package com.vaadin.addon.charts;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Labels;
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
import com.vaadin.addon.charts.util.SVGGenerator;

public class SVGGeneratorTest {

    @Test
    @Ignore("Phantomjs not installed on our build server")
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

        Assert.assertTrue("Generating SVG took more than 200ms : "
                + spentMillis + "ms", spentMillis < 200);

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
        x.setTitle((String) null);
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        Title title = new Title("Population (millions)");
        title.setVerticalAlign(VerticalAlign.HIGH);
        y.setTitle(title);
        conf.addyAxis(y);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("this.series.name +': '+ this.y +' millions'");
        conf.setTooltip(tooltip);

        PlotOptionsBar plot = new PlotOptionsBar();
        plot.setDataLabels(new Labels(true));
        conf.setPlotOptions(plot);

        Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setHorizontalAlign(HorizontalAlign.RIGHT);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setX(-100);
        legend.setY(100);
        legend.setFloating(true);
        legend.setBorderWidth(1);
        legend.setBackgroundColor("#FFFFFF");
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
    @Ignore("Phantomjs not installed on our build server")
    public void testWide() throws InterruptedException, URISyntaxException {

        Configuration conf = new Configuration();
        conf.getChart().setType(ChartType.COLUMN);
        conf.getChart().setMarginRight(200);
        Legend legend = conf.getLegend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setHorizontalAlign(HorizontalAlign.RIGHT);
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
        String generatedSVG = instance.generate(conf, 1200, 400);

        Assert.assertTrue(generatedSVG.contains("width=\"1200\""));
        Assert.assertTrue(generatedSVG.contains("height=\"400\""));

        SVGGenerator.getInstance().destroy();

    }

}
