package com.vaadin.addon.charts.junittests;

import static org.junit.Assert.*;

import com.vaadin.addon.charts.declarative.ChartDesignWriter;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.Title;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Test;

public class ChartDesignWriterTest {

    @Test
    public void writeConfiguration_stringValueDefinedInConfiguration_theValueIsAddedAsAttribute() {
        Configuration configuration = new Configuration();
        configuration.setTitle(new Title("my title"));
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent);

        assertEquals(
            "<title text=\"my title\"></title>", parent.child(0).toString());
    }

    @Test
    public void writeConfiguration_enumValueDefinedInConfiguration_theValueIsAddedAsAttribute() {
        Configuration configuration = new Configuration();
        Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        configuration.setLegend(legend);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent);

        assertEquals(
            "<legend layout=\"vertical\"></legend>", parent.child(0).toString());
    }

    @Test
    public void writeConfiguration_numberValueDefinedInConfiguration_theValueIsAddedAsAttribute() {
        Configuration configuration = new Configuration();
        Legend legend = new Legend();
        legend.setY(100);
        configuration.setLegend(legend);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent);

        assertEquals(
            "<legend y=\"100\"></legend>", parent.child(0).toString());
    }

    @Test
    public void writeConfiguration_axisPropertyIsSet_theValueIsAddedAsAttribute() {
        Configuration configuration = new Configuration();
        configuration.getyAxis().setMin(-5);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent);

        assertEquals(
            "<y-axis min=\"-5\"></y-axis>", parent.child(0).toString());
    }

    @Test
    public void writeConfiguration_enableDataLabelsInPlotoptions_plotOptionsElementHasInnerTypeElement() {
        Configuration configuration = new Configuration();
        DataLabels dataLabels = new DataLabels(true);
        PlotOptionsLine plotOptionsLine = new PlotOptionsLine();
        plotOptionsLine.setDataLabels(dataLabels);
        configuration.setPlotOptions(plotOptionsLine);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent);

        assertEquals("<plot-options><line><data-labels enabled=\"true\"></data-labels></line></plot-options>",
            removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    @Test
    public void writeConfiguration_multiplePlotOptions_allPlotOptionsAreChildrenOfPlotOptionsTag() {
        Configuration configuration = new Configuration();
        PlotOptionsLine plotOptionsLine = new PlotOptionsLine();
        plotOptionsLine.setAnimation(false);
        PlotOptionsSpline plotOptionsSpline = new PlotOptionsSpline();
        plotOptionsSpline.setVisible(false);
        configuration.setPlotOptions(plotOptionsLine, plotOptionsSpline);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent);


        // Expected (the order of plot options is unknown):
        // "<plot-options>
        //    <line animation=\"false\">
        //    </line>
        //    <spline visible=\"false\">
        //    </spline>
        // </plot-options>"
        assertEquals("plot-options",parent.child(0).tagName());
        Elements plotOptions = parent.child(0).children();
        assertEquals(2, plotOptions.size());
        assertPlotOptions("line","animation", "false", plotOptions);
        assertPlotOptions("spline","visible", "false", plotOptions);
    }

    private void assertPlotOptions(String type, String attribute, String attributeValue, Elements plotOptions) {
        Element typeElement = find(type, plotOptions);
        assertNotNull(typeElement);
        assertTrue(typeElement.hasAttr(attribute));
        assertEquals(attributeValue, typeElement.attr(attribute));
    }

    private Element find(String tagname, Elements elements) {
        for (Element element : elements) {
            if(tagname.equals(element.tagName())) {
                return element;
            }
        }
        return null;
    }

    @Test
    public void writeConfiguration_emptyObject_nothingIsAddedToParentElement() {
        Configuration configuration = new Configuration();
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent);

        assertEquals("<test></test>", parent.toString());
    }

    @Test
    public void writeConfiguration_attributeNameHasMultipleWords_theAttributeValueIsAddedToElement() {
        Configuration configuration = new Configuration();
        configuration.getChart().setMarginLeft(100);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent);

        assertEquals(
            "<chart margin-left=\"100\"></chart>", parent.child(0).toString());
    }


    @Test
    public void writeConfiguration_arrayNode_theValuesInArrayAreSeparatedWithComma() {
        Configuration configuration = new Configuration();
        configuration.getxAxis().setCategories("Jan","Feb","Mar");
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent);

        assertEquals(
            "<x-axis><categories>Jan, Feb, Mar</categories></x-axis>",
            removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    @Test
    public void writeConfiguration_formulaNode_theFormulaIsAddedToElementAsAttribute() {
        Configuration configuration = new Configuration();
        configuration.getTooltip().setFormatter("function() {return '' + this.series.name + ' ' + this.x + ': ' + this.y + '°C';}");
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent);

        assertEquals(
            "<tooltip formatter=\"function() {return '' + this.series.name + ' ' + this.x + ': ' + this.y + '°C';}\"></tooltip>",
            removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    @Test
    public void writeConfiguration_yAxisWithTitle_theYAxisWithTitleIsInTheElement() {
        Configuration configuration = new Configuration();
        AxisTitle title = new AxisTitle("Temperature");
        configuration.getyAxis().setTitle(title);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent);

        assertEquals(
            "<y-axis><title text=\"Temperature\"></title></y-axis>",
            removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    @Test
    public void writeConfiguration_plotLines_thePlotLinesAreTheElement() {
        Configuration configuration = new Configuration();
        PlotLine plotLine = new PlotLine();
        plotLine.setValue(0);
        plotLine.setWidth(2);
        configuration.getyAxis().setPlotLines(plotLine);
        Element parent = new Element(Tag.valueOf("test"), "");

        ChartDesignWriter.writeConfigurationToElement(configuration, parent);

        assertEquals(
            "<y-axis><plot-lines value=\"0\" width=\"2\"></plot-lines></y-axis>",
            removeWhitespacesBetweenTags(parent.child(0).toString()));
    }

    private String removeWhitespacesBetweenTags(String html) {
        return html.replaceAll(">\\s+",">").replaceAll("\\s+<", "<");
    }

}
