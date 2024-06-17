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

import static org.junit.Assert.*;

import com.vaadin.addon.charts.declarative.ChartDesignWriter;
import com.vaadin.addon.charts.declarative.ChartDesignReader;
import com.vaadin.addon.charts.model.Configuration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Test;

public class ChartDesignTest {

    @Test
    public void readAndWriteConfiguration_basicLineChart_theOutputHtmlIsTheSameAsInput() {
        String testHtml =
            "<chart margin-bottom=\"25\" margin-right=\"130\"></chart>\n" +
            "<chart-title text=\"Monthly Average Temperature\"></chart-title>\n" +
            "<subtitle text=\"Source: WorldClimate.com\"></subtitle>\n" +
            "<x-axis>\n" +
            " <categories>\n" +
            "  Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec\n" +
            " </categories>\n" +
            "</x-axis>\n" +
            "<y-axis min=\"-5\">\n" +
            " <chart-title align=\"middle\" text=\"Temperature (°C)\"></chart-title>\n" +
            "</y-axis>\n" +
            "<tooltip formatter=\"function() {return '' + this.series.name + ' ' + this.x + ': ' + this.y + '°C';}\"></tooltip>\n" +
            "<legend align=\"right\" border-width=\"0\" layout=\"vertical\" vertical-align=\"top\" x=\"-10\" y=\"100\"></legend>\n" +
            "<plot-options>\n" +
            " <line>\n" +
            "  <data-labels enabled=\"true\"></data-labels>\n" +
            " </line>\n" +
            "</plot-options>";
        Elements elements = createElements(testHtml);
        Configuration configuration = new Configuration();
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignReader.readConfigurationFromElements(elements, configuration);
        ChartDesignWriter.writeConfigurationToElement(configuration, parent);

        assertEquals(testHtml, parent.html());
    }

    @Test
    public void readAndWriteConfiguration_compareMultipleSeries_theOutputHtmlIsTheSameAsInput() {
        String testHtml =
            "<chart-title text=\"AAPL Stock Price\"></chart-title>\n" +
            "<y-axis>\n" +
            " <labels formatter=\"function() {return (this.value > 0 ? ' + ' : '') + this.value + '%';}\"></labels>\n" +
            " <plot-lines value=\"0\" width=\"2\"></plot-lines>\n" +
            "</y-axis>\n" +
            "<tooltip point-format=\"<span style=&quot;color:{series.color}&quot;>{series.name}</span>: <b>{point.y}</b>\n" +
            "({point.change}%)<br/>\" value-decimals=\"2\"></tooltip>\n" +
            "<plot-options>\n" +
            " <series compare=\"percent\"></series>\n" +
            "</plot-options>\n" +
            "<range-selector selected=\"4\"></range-selector>";
        Elements elements = createElements(testHtml);
        Configuration configuration = new Configuration();
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignReader.readConfigurationFromElements(elements, configuration);
        ChartDesignWriter.writeConfigurationToElement(configuration, parent);

        assertEquals(testHtml, parent.html());
    }

    @Test
    public void readAndWriteConfiguration_treemapWithLevels_theOutputHtmlIsTheSameAsInput() {
        String testHtml =
            "<chart-title text=\"Fruit consumption\"></chart-title>\n" +
            "<plot-options>\n" +
            " <treemap alternate-starting-direction=\"true\" layout-algorithm=\"stripes\">\n" +
            "  <levels layout-algorithm=\"sliceanddice\" level=\"1\">\n" +
            "   <data-labels align=\"left\" enabled=\"true\" vertical-align=\"top\">\n" +
            "    <chart-style font-weight=\"bold\" font-size=\"15px\"></chart-style>\n" +
            "   </data-labels>\n" +
            "  </levels>\n" +
            " </treemap>\n" +
            "</plot-options>";
        Elements elements = createElements(testHtml);
        Configuration configuration = new Configuration();
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignReader.readConfigurationFromElements(elements, configuration);
        ChartDesignWriter.writeConfigurationToElement(configuration, parent);

        assertEquals(testHtml, parent.html());
    }


    private Elements createElements(String configHtml) {
        Document doc = Jsoup.parseBodyFragment(configHtml);
        return doc.body().children();
    }



}
