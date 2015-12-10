package com.vaadin.addon.charts.examples.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.BoxPlotItem;
import com.vaadin.addon.charts.model.DashStyle;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Label;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.PlotOptionsBoxplot;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class BoxPlotExample extends AbstractVaadinChartExample {

    private CheckBox useCustomStyles;
    private Chart chart;
    private DataSeries observations;

    @Override
    public String getDescription() {
        return "Simple Box Plot chart";
    }

    @Override
    protected Component getChart() {
        chart = new Chart();

        chart.getConfiguration().setTitle("Box Plot Example");

        Legend legend = new Legend();
        legend.setEnabled(false);
        chart.getConfiguration().setLegend(legend);

        XAxis xaxis = chart.getConfiguration().getxAxis();
        xaxis.setTitle(new AxisTitle("Experiment No."));
        xaxis.setCategories(new String[] { "1", "2", "3", "4", "5" });

        YAxis yAxis = chart.getConfiguration().getyAxis();

        yAxis.setTitle(new AxisTitle("Observations"));
        PlotLine plotLine = new PlotLine();
        plotLine.setColor(new SolidColor("red"));
        plotLine.setValue(932);
        plotLine.setWidth(1);
        plotLine.setZIndex(0);
        plotLine.setDashStyle(DashStyle.DASHDOT.toString());
        Label label = new Label("Theoretical mean: 932");
        label.setAlign(HorizontalAlign.CENTER.toString());
        Style style = new Style();
        style.setColor(new SolidColor("gray"));
        label.setStyle(style);
        plotLine.setLabel(label);
        PlotLine plotLine2 = new PlotLine();
        plotLine2.setColor(new SolidColor("blue"));
        plotLine2.setValue(800);
        plotLine2.setWidth(1);
        plotLine2.setZIndex(500);
        plotLine2.setDashStyle(DashStyle.SHORTDASHDOTDOT.toString());
        Label label2 = new Label("Second plotline: 800");
        label2.setAlign(HorizontalAlign.CENTER.toString());
        Style style2 = new Style();
        style2.setColor(new SolidColor("gray"));
        label2.setStyle(style2);
        plotLine2.setLabel(label2);

        yAxis.setPlotLines(new PlotLine[] { plotLine, plotLine2 });

        observations = new DataSeries();
        observations.setName("Observations");

        // Add PlotBoxItems contain all fields relevant for plot box chart
        observations.add(new BoxPlotItem(760, 801, 848, 895, 965));

        // Example with no arg constructor
        BoxPlotItem plotBoxItem = new BoxPlotItem();
        plotBoxItem.setLow(733);
        plotBoxItem.setLowerQuartile(853);
        plotBoxItem.setMedian(939);
        plotBoxItem.setUpperQuartile(980);
        plotBoxItem.setHigh(1080);
        observations.add(plotBoxItem);

        observations.add(new BoxPlotItem(714, 762, 817, 870, 918));
        observations.add(new BoxPlotItem(724, 802, 806, 871, 950));
        observations.add(new BoxPlotItem(834, 836, 864, 882, 910));
        observations.setPlotOptions(getPlotBoxOptions());
        chart.getConfiguration().addSeries(observations);

        return chart;
    }

    @Override
    protected void setup() {
        useCustomStyles = new CheckBox("Use custom styling");
        useCustomStyles.setDebugId("styles");
        useCustomStyles.setImmediate(true);
        super.setup();
        addComponentAsFirst(useCustomStyles);
        useCustomStyles.addListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                observations.setPlotOptions(getPlotBoxOptions());
                chart.drawChart();
            }
        });
    }

    private PlotOptionsBoxplot getPlotBoxOptions() {
        PlotOptionsBoxplot options = new PlotOptionsBoxplot();

        if (useCustomStyles.getValue()) {
            // optional styling
            options.setMedianColor(new SolidColor("cyan"));
            options.setMedianWidth(1);

            options.setStemColor(new SolidColor("green"));
            // FIXME remove toString() once enums are used in model (CHARTS-159)
            options.setStemDashStyle(DashStyle.SHORTDOT.toString());
            options.setStemWidth(4);

            options.setWhiskerColor(new SolidColor("magenta"));
            options.setWhiskerLength("70");
            options.setWhiskerWidth(5);
        }

        return options;
    }

}
