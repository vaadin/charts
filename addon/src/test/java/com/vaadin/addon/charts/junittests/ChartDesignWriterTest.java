package com.vaadin.addon.charts.junittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.vaadin.addon.charts.declarative.ChartDesignWriter;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.PlotOptionsFlags;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.declarative.DesignContext;

public class ChartDesignWriterTest {

    @Test
    public void writeConfiguration_stringValueDefinedInConfiguration_theValueIsAddedAsAttribute() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        configuration.setTitle(new Title("my title"));
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals("<chart-title text=\"my title\"></chart-title>", parent
                .child(0).toString());
    }

    @Test
    public void writeConfiguration_enumValueDefinedInConfiguration_theValueIsAddedAsAttribute() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        configuration.setLegend(legend);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals("<legend layout=\"vertical\"></legend>", parent.child(0)
                .toString());
    }

    @Test
    public void writeConfiguration_numberValueDefinedInConfiguration_theValueIsAddedAsAttribute() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        Legend legend = new Legend();
        legend.setY(100);
        configuration.setLegend(legend);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals("<legend y=\"100\"></legend>", parent.child(0).toString());
    }

    @Test
    public void writeConfiguration_axisPropertyIsSet_theValueIsAddedAsAttribute() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        configuration.getyAxis().setMin(-5);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals("<y-axis min=\"-5\"></y-axis>", parent.child(0).toString());
    }

    @Test
    public void writeConfiguration_enableDataLabelsInPlotoptions_plotOptionsElementHasInnerTypeElement() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        DataLabels dataLabels = new DataLabels(true);
        PlotOptionsLine plotOptionsLine = new PlotOptionsLine();
        plotOptionsLine.setDataLabels(dataLabels);
        configuration.setPlotOptions(plotOptionsLine);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals(
                "<plot-options><line><data-labels enabled></data-labels></line></plot-options>",
                removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    @Test
    public void writeConfiguration_plotOptionsWithReservedWord_prefixIsWrittenToReservedTagName() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        PlotOptionsArea plotOptionsArea = new PlotOptionsArea();
        plotOptionsArea.setAnimation(false);
        configuration.addPlotOptions(plotOptionsArea);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals(
                "<plot-options><chart-area animation=\"false\"></chart-area></plot-options>",
                removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    @Test
    public void writeConfiguration_plotOptionsWithReservedPropertyWord_prefixIsWrittenToReservedProperty() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        PlotOptionsFlags plotOptionsFlags = new PlotOptionsFlags();
        // plotOptionsFlags.setAllowPointSelect(true);
        plotOptionsFlags.setOnSeries("dataseries");
        configuration.addPlotOptions(plotOptionsFlags);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals(
                "<plot-options><flags draw-on-series=\"dataseries\"></flags></plot-options>",
                removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    @Test
    public void writeConfiguration_multiplePlotOptions_allPlotOptionsAreChildrenOfPlotOptionsTag() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        PlotOptionsLine plotOptionsLine = new PlotOptionsLine();
        plotOptionsLine.setAnimation(false);
        PlotOptionsSpline plotOptionsSpline = new PlotOptionsSpline();
        plotOptionsSpline.setVisible(false);
        configuration.setPlotOptions(plotOptionsLine, plotOptionsSpline);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        // Expected (the order of plot options is unknown):
        // "<plot-options>
        // <line animation=\"false\">
        // </line>
        // <spline visible=\"false\">
        // </spline>
        // </plot-options>"
        assertEquals("plot-options", parent.child(0).tagName());
        Elements plotOptions = parent.child(0).children();
        assertEquals(2, plotOptions.size());
        assertPlotOptions("line", "animation", "false", plotOptions);
        assertPlotOptions("spline", "visible", "false", plotOptions);
    }

    private void assertPlotOptions(String type, String attribute,
            String attributeValue, Elements plotOptions) {
        Element typeElement = find(type, plotOptions);
        assertNotNull(typeElement);
        assertTrue(typeElement.hasAttr(attribute));
        assertEquals(attributeValue, typeElement.attr(attribute));
    }

    private Element find(String tagname, Elements elements) {
        for (Element element : elements) {
            if (tagname.equals(element.tagName())) {
                return element;
            }
        }
        return null;
    }

    @Test
    public void writeConfiguration_emptyObject_nothingIsAddedToParentElement() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals("<test>\n <exporting></exporting>\n</test>", parent.toString());
    }

    @Test
    public void writeConfiguration_attributeNameHasMultipleWords_theAttributeValueIsAddedToElement() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        configuration.getChart().setMarginLeft(100);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals("<chart margin-left=\"100\"></chart>", parent.child(0)
                .toString());
    }

    @Test
    public void writeConfiguration_arrayNode_theValuesInArrayAreSeparatedWithComma() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        configuration.getxAxis().setCategories("Jan", "Feb", "Mar");
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals("<x-axis><categories>Jan, Feb, Mar</categories></x-axis>",
                removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    @Test
    public void writeConfiguration_formulaNode_theFormulaIsAddedToElementAsAttribute() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        configuration
                .getTooltip()
                .setFormatter(
                        "function() {return '' + this.series.name + ' ' + this.x + ': ' + this.y + '°C';}");
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals(
                "<tooltip formatter=\"function() {return '' + this.series.name + ' ' + this.x + ': ' + this.y + '°C';}\"></tooltip>",
                removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    @Test
    public void writeConfiguration_yAxisWithTitle_theYAxisWithTitleIsInTheElement() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        AxisTitle title = new AxisTitle("Temperature");
        configuration.getyAxis().setTitle(title);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals(
                "<y-axis><chart-title text=\"Temperature\"></chart-title></y-axis>",
                removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    @Test
    public void writeConfiguration_plotLines_thePlotLinesAreTheElement() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        PlotLine plotLine = new PlotLine();
        plotLine.setValue(0);
        plotLine.setWidth(2);
        configuration.getyAxis().setPlotLines(plotLine);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals(
                "<y-axis><plot-lines value=\"0\" width=\"2\"></plot-lines></y-axis>",
                removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    @Test
    public void writeConfiguration_titleWithStyle_theStyleIsInnerElementOfTitle() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        configuration.getTitle().getStyle().setTop("12");
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals(
                "<chart-title><chart-style top=\"12\"></chart-style></chart-title>",
                removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    @Test
    public void writeConfiguration_frameFor3D_theFrameElementHasPrefix() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        configuration.getChart().getOptions3d().getFrame().getBack().setSize(1);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals(
                "<chart><options3d><chart-frame><back size=\"1\"></back></chart-frame></options3d></chart>",
                removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    @Test
    public void writeConfiguration_chartHasSolidColor_theSolidColorIsWrittenAsAttribute() {
        DesignContext designContext = new DesignContext();
        Configuration configuration = new Configuration();
        configuration.getChart().setBackgroundColor(new SolidColor("black"));
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals(
            "<chart background-color=\"black\"></chart>",
            removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    @Test
    public void writeConfiguration_chartHasLinearGradientBackgroundColor_theLinearGradientIsWrittenAsElement() {
        Configuration configuration = new Configuration();
        GradientColor gradientColor = GradientColor.createLinear(0, 0, 1, 1);
        gradientColor.addColorStop(0, new SolidColor("white"));
        gradientColor.addColorStop(1, new SolidColor("black"));
        configuration.getChart().setBackgroundColor(gradientColor);
        Element parent = new Element(Tag.valueOf("test"), "");
        DesignContext designContext = new DesignContext();
        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals(
            "<chart><background-color><linear-gradient x1=\"0\" y1=\"0\" x2=\"1\" y2=\"1\"></linear-gradient>"+
                "<stops position=\"0\" color=\"white\"></stops>"+
                "<stops position=\"1\" color=\"black\"></stops>"+
            "</background-color></chart>",
            removeWhitespacesBetweenTags(parent.child(0).toString()));

    }

    @Test
    public void writeConfiguration_chartHasRadialGradientBackgroundColor_theRadialGradientIsWrittenAsElement() {
        Configuration configuration = new Configuration();
        DesignContext designContext = new DesignContext();
        GradientColor gradientColor = GradientColor.createRadial(0.5, 0.3, 0.7);
        gradientColor.addColorStop(0, new SolidColor("black"));
        gradientColor.addColorStop(1, new SolidColor("white"));
        configuration.getChart().setBackgroundColor(gradientColor);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent, designContext);

        assertEquals(
            "<chart><background-color><radial-gradient cx=\"0.5\" cy=\"0.3\" r=\"0.7\"></radial-gradient>"+
                "<stops position=\"0\" color=\"black\"></stops>"+
                "<stops position=\"1\" color=\"white\"></stops>"+
            "</background-color></chart>",
            removeWhitespacesBetweenTags(parent.child(0).toString()));

    }

    private String removeWhitespacesBetweenTags(String html) {
        return html.replaceAll(">\\s+", ">").replaceAll("\\s+<", "<");
    }

}
