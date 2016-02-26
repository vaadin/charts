package com.vaadin.addon.charts.junittests;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.vaadin.addon.charts.declarative.ChartDesignReader;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsTreemap;
import com.vaadin.ui.declarative.DesignException;

public class ChartDesignReaderTest {

    @Test
    public void readConfiguration_stringValueDefinedInFragment_theSameValueIsInConfiguration() {
        Elements elements = createElements("<title text=\"my title\"></title>");
        Configuration configuration = new Configuration();

        ChartDesignReader
                .readConfigurationFromElements(elements, configuration);

        assertEquals("my title", configuration.getTitle().getText());
    }

    @Test
    public void readConfiguration_tooltipWithColor_SolidColorIsInConfiguration() {
        Elements elements = createElements("<tooltip background-color=\"#FF00FF\"></tooltip>");
        Configuration configuration = new Configuration();

        ChartDesignReader
                .readConfigurationFromElements(elements, configuration);

        assertNotNull(configuration.getTooltip().getBackgroundColor());
        assertEquals("#FF00FF", configuration.getTooltip().getBackgroundColor()
                .toString());
    }

    @Test
    public void readConfiguration_enumValueDefinedInFragment_theSameValueIsInConfiguration() {
        Elements elements = createElements("<legend layout=\"vertical\"></legend>");
        Configuration configuration = new Configuration();

        ChartDesignReader
            .readConfigurationFromElements(elements, configuration);

        assertEquals(LayoutDirection.VERTICAL, configuration.getLegend().getLayout());
    }

    @Test
    public void readConfiguration_numberValueDefinedInFragment_theSameValueIsInConfiguration() {
        Elements elements = createElements("<legend y=\"100\"></legend>");
        Configuration configuration = new Configuration();

        ChartDesignReader
            .readConfigurationFromElements(elements, configuration);

        assertEquals(100L, configuration.getLegend().getY());
    }

    @Test
    public void readConfiguration_axisHasAttributes_theSameValueIsInConfiguration() {
        Elements elements = createElements("<y-axis min=\"-5\"></y-axis>");
        Configuration configuration = new Configuration();

        ChartDesignReader
            .readConfigurationFromElements(elements, configuration);

        assertEquals(-5L, configuration.getyAxis().getMin());
    }

    @Test
    public void readConfiguration_chartTitleHasTextOnlyContent_theContentIsSetAsTitleText() {
        Elements elements = createElements("<title>my title</title>");
        Configuration configuration = new Configuration();

        ChartDesignReader
            .readConfigurationFromElements(elements, configuration);

        assertEquals("my title", configuration.getTitle().getText());
    }

    @Test
    public void readConfiguration_chartSubTitleHasTextOnlyContent_theContentIsSetAsTitleText() {
        Elements elements = createElements("<subtitle>my title</subtitle>");
        Configuration configuration = new Configuration();

        ChartDesignReader
            .readConfigurationFromElements(elements, configuration);

        assertEquals("my title", configuration.getSubTitle().getText());
    }

    @Test
    public void readConfiguration_axisTitleHasTextOnlyContent_theContentIsSetAsTitleText() {
        Elements elements = createElements("<y-axis><title>my title</title></y-axis>");
        Configuration configuration = new Configuration();

        ChartDesignReader
            .readConfigurationFromElements(elements, configuration);

        assertEquals("my title", configuration.getyAxis().getTitle().getText());
    }

    @Test
    public void readConfiguration_arrayProperty_theSameValuesAreInConfiguration() {
        Elements elements = createElements("<x-axis><categories>Jan, Feb, Mar</categories></x-axis>");
        Configuration configuration = new Configuration();

        ChartDesignReader
            .readConfigurationFromElements(elements, configuration);

        assertArrayEquals(
            new String[] { "Jan", "Feb", "Mar" },
            configuration.getxAxis().getCategories());
    }

    @Test
    public void readConfiguration_multiValueNodes_allTheNodesAreInTheConfiguration() {
        Elements elements = createElements("<y-axis><title>First</title></y-axis>"+
                "<y-axis><title>Second</title></y-axis>");
        Configuration configuration = new Configuration();

        ChartDesignReader
            .readConfigurationFromElements(elements, configuration);

        assertEquals(2, configuration.getyAxes().getNumberOfAxes());
        assertEquals("First", configuration.getyAxis(0).getTitle().getText());
        assertEquals("Second", configuration.getyAxis(1).getTitle().getText());
    }

    @Test
    public void readConfiguration_plotOptionsWithTypeLine_plotOptionsLineIsAddedToConfiguration() {
        Elements elements = createElements("<plot-options><line></line></plot-options>");
        Configuration configuration = new Configuration();

        ChartDesignReader
            .readConfigurationFromElements(elements, configuration);

        assertEquals(1, configuration.getPlotOptions().size());
        assertThat(
            configuration.getPlotOptions(ChartType.LINE),
            instanceOf(PlotOptionsLine.class));
    }

    @Test(expected = DesignException.class)
    public void readConfiguration_plotOptionsWithoutType_designExceptionIsThrown() {
        Elements elements = createElements("<plot-options></plot-options>");
        Configuration configuration = new Configuration();

        ChartDesignReader
            .readConfigurationFromElements(elements, configuration);

        fail();
    }

    @Test
    public void readConfiguration_enableDataLabelsInPlotoptions_dataLabelsAreEnabledInConfiguration() {
        Elements elements = createElements("<plot-options><line><data-labels enabled=\"true\"></data-labels></line></plot-options>");
        Configuration configuration = new Configuration();

        ChartDesignReader
            .readConfigurationFromElements(elements, configuration);

        PlotOptionsLine lineOptions =
            (PlotOptionsLine) configuration.getPlotOptions(ChartType.LINE);
        assertEquals(true, lineOptions.getDataLabels().getEnabled());
    }

    @Test
    public void readConfiguration_arrayType_theNewValueIsAddedToArrayInConfiguration() {
        Elements elements = createElements("<plot-options><treemap><levels level=\"1\"></levels></treemap></plot-options>");
        Configuration configuration = new Configuration();

        ChartDesignReader
            .readConfigurationFromElements(elements, configuration);

        PlotOptionsTreemap lineOptions =
            (PlotOptionsTreemap) configuration.getPlotOptions(ChartType.TREEMAP);
        assertEquals(1, lineOptions.getLevels().length);
        assertEquals(1L, lineOptions.getLevels()[0].getLevel());
    }

    private Elements createElements(String configHtml) {
        Document doc = Jsoup.parseBodyFragment(configHtml);
        return doc.body().children();
    }

}
