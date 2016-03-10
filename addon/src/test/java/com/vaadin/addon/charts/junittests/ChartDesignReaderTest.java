package com.vaadin.addon.charts.junittests;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.vaadin.addon.charts.declarative.ChartDesignReader;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.PlotOptionsFlags;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.PlotOptionsTreemap;
import com.vaadin.addon.charts.model.style.GradientColor;

public class ChartDesignReaderTest {

    @Test
    public void readConfiguration_stringValueDefinedInFragment_theSameValueIsInConfiguration() {
        Elements elements = createElements("<chart-title text=\"my title\"></chart-title>");
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

        assertEquals(LayoutDirection.VERTICAL, configuration.getLegend()
                .getLayout());
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
        Elements elements = createElements("<chart-title>my title</chart-title>");
        Configuration configuration = new Configuration();

        ChartDesignReader
                .readConfigurationFromElements(elements, configuration);

        assertEquals("my title", configuration.getTitle().getText());
    }

    @Test
    public void readConfiguration_chartTitleHasTextInAttibuteAndContent_theAttributeIsSetAsTitleText() {
        Elements elements = createElements("<chart-title text=\"my title\">this text should be ignored</chart-title>");
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
        Elements elements = createElements("<y-axis><chart-title>my title</chart-title></y-axis>");
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

        assertArrayEquals(new String[] { "Jan", "Feb", "Mar" }, configuration
                .getxAxis().getCategories());
    }

    @Test
    public void readConfiguration_multiValueNodes_allTheNodesAreInTheConfiguration() {
        Elements elements = createElements("<y-axis><chart-title>First</chart-title></y-axis>"
                + "<y-axis><chart-title>Second</chart-title></y-axis>");
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
        assertThat(configuration.getPlotOptions(ChartType.LINE),
                instanceOf(PlotOptionsLine.class));
    }

    @Test
    public void readConfiguration_plotOptionsWithOnSeriesProperty_onSeriesIsAddedToConfiguration() {
        Elements elements = createElements("<plot-options><flags draw-on-series=\"dataseries\"></flags></plot-options>");
        Configuration configuration = new Configuration();

        ChartDesignReader
                .readConfigurationFromElements(elements, configuration);

        assertEquals(1, configuration.getPlotOptions().size());
        assertThat(configuration.getPlotOptions(ChartType.FLAGS),
                instanceOf(PlotOptionsFlags.class));
        assertEquals("dataseries",
                ((PlotOptionsFlags) configuration
                        .getPlotOptions(ChartType.FLAGS)).getOnSeries());

    }

    @Test
    public void readConfiguration_plotOptionsWithReservedTagName_plotOptionsIsAddedToConfiguration() {
        Elements elements = createElements("<plot-options><chart-area></chart-area></plot-options>");
        Configuration configuration = new Configuration();

        ChartDesignReader
                .readConfigurationFromElements(elements, configuration);

        assertEquals(1, configuration.getPlotOptions().size());
        assertThat(configuration.getPlotOptions(ChartType.AREA),
                instanceOf(PlotOptionsArea.class));
    }

    @Test
    public void readConfiguration_multiplePlotOptions_plotOptionsLineIsAddedToConfiguration() {
        Elements elements = createElements("<plot-options><line></line><spline></spline></plot-options>");
        Configuration configuration = new Configuration();

        ChartDesignReader
                .readConfigurationFromElements(elements, configuration);

        assertEquals(2, configuration.getPlotOptions().size());
        assertThat(configuration.getPlotOptions(ChartType.LINE),
                instanceOf(PlotOptionsLine.class));
        assertThat(configuration.getPlotOptions(ChartType.SPLINE),
                instanceOf(PlotOptionsSpline.class));
    }

    @Test
    public void readConfiguration_multiplePlotOptions_attributesAreReadToCorrectPlotOptions() {
        Elements elements = createElements("<plot-options>"
                + "<line animation=\"true\"></line>"
                + "<spline animation=\"false\"></spline>" + "</plot-options>");
        Configuration configuration = new Configuration();

        ChartDesignReader
                .readConfigurationFromElements(elements, configuration);

        PlotOptionsLine line = (PlotOptionsLine) configuration
                .getPlotOptions(ChartType.LINE);
        PlotOptionsSpline spline = (PlotOptionsSpline) configuration
                .getPlotOptions(ChartType.SPLINE);
        assertFalse(spline.getAnimation());
        assertTrue(line.getAnimation());
    }

    @Test
    public void readConfiguration_enableDataLabelsInPlotoptions_dataLabelsAreEnabledInConfiguration() {
        Elements elements = createElements("<plot-options><line><data-labels enabled=\"true\"></data-labels></line></plot-options>");
        Configuration configuration = new Configuration();

        ChartDesignReader
                .readConfigurationFromElements(elements, configuration);

        PlotOptionsLine lineOptions = (PlotOptionsLine) configuration
                .getPlotOptions(ChartType.LINE);
        assertEquals(true, lineOptions.getDataLabels().getEnabled());
    }

    @Test
    public void readConfiguration_arrayType_theNewValueIsAddedToArrayInConfiguration() {
        Elements elements = createElements("<plot-options><treemap><levels level=\"1\"></levels></treemap></plot-options>");
        Configuration configuration = new Configuration();

        ChartDesignReader
                .readConfigurationFromElements(elements, configuration);

        PlotOptionsTreemap lineOptions = (PlotOptionsTreemap) configuration
                .getPlotOptions(ChartType.TREEMAP);
        assertEquals(1, lineOptions.getLevels().length);
        assertEquals(1L, lineOptions.getLevels()[0].getLevel());
    }

    @Test
    public void readConfiguration_titleWithStyleAsInnerElement_theTitleAndStyleAreInConfiguration() {
        Elements elements = createElements("<chart-title text=\"foobar\"><chart-style top=\"12\"></chart-style></chart-title>");
        Configuration configuration = new Configuration();

        ChartDesignReader
                .readConfigurationFromElements(elements, configuration);

        assertEquals("12", configuration.getTitle().getStyle().getTop());
        assertEquals("foobar", configuration.getTitle().getText());
    }

    @Test
    public void readConfiguration_subtitleWithStyleAsInnerElement_theTitleAndStyleAreInConfiguration() {
        Elements elements = createElements("<subtitle text=\"foobar\"><chart-style top=\"12\"></chart-style></subtitle>");
        Configuration configuration = new Configuration();

        ChartDesignReader
                .readConfigurationFromElements(elements, configuration);

        assertEquals("12", configuration.getSubTitle().getStyle().getTop());
        assertEquals("foobar", configuration.getSubTitle().getText());
    }

    @Test
    public void readConfiguration_frameHasBackSizeDefined_theFrameBackSizeIsDefinedInConfiguration() {
        Elements elements = createElements("<chart><options3d><chart-frame><back size=\"1\"></back></chart-frame></options3d></chart>");
        Configuration configuration = new Configuration();

        ChartDesignReader
                .readConfigurationFromElements(elements, configuration);

        assertEquals(1L, configuration.getChart().getOptions3d().getFrame()
                .getBack().getSize());
    }

    @Test
    public void readConfiguration_chartHasLinearGradientBackgroundColor_theLinearGradientIsDefinedInConfiguration() {
        Elements elements = createElements("<chart><background-color><linear-gradient x1=\"0\" y1=\"0\" x2=\"1\" y2=\"1\"></linear-gradient>"+
                                                                    "<stops position=\"0\" color=\"white\"></stops>"+
                                                                    "<stops position=\"1\" color=\"black\"></stops></background-color></chart>");
        Configuration configuration = new Configuration();

        ChartDesignReader
            .readConfigurationFromElements(elements, configuration);

        assertThat(configuration.getChart().getBackgroundColor(),
            instanceOf(GradientColor.class));
        GradientColor backgroundColor =
            (GradientColor) configuration.getChart().getBackgroundColor();
        assertNotNull(backgroundColor.getLinearGradient());
    }

    @Test
    public void readConfiguration_chartHasSolidBackgroundColor_theSolidColorIsDefinedInConfiguration() {
        Elements elements = createElements("<chart background-color=\"white\"></chart>");
        Configuration configuration = new Configuration();

        ChartDesignReader
            .readConfigurationFromElements(elements, configuration);

        assertEquals("white", configuration.getChart().getBackgroundColor().toString());
    }

    private Elements createElements(String configHtml) {
        Document doc = Jsoup.parseBodyFragment(configHtml);
        return doc.body().children();
    }

}
