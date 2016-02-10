package com.vaadin.addon.charts.examples.dynamic;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartClickEvent;
import com.vaadin.addon.charts.ChartClickListener;
import com.vaadin.addon.charts.PointClickEvent;
import com.vaadin.addon.charts.PointClickListener;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ClickToAddPoint extends AbstractVaadinChartExample {

    private Chart chart;
    private Label lastAction = new Label();
    private Label eventDetails = new Label();

    @Override
    public String getDescription() {
        return "Click to add point";
    }

    @Override
    protected Component getChart() {
        lastAction.setId("lastAction");
        eventDetails.setId("eventDetails");
        chart = new Chart();
        chart.setId("chart");
        chart.setWidth("500px");

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.SCATTER);
        configuration.getTitle().setText("User supplied data");
        configuration
                .getSubTitle()
                .setText(
                        "Click the plot area to add a point. Click a point to remove it.");

        XAxis xAxis = configuration.getxAxis();
        xAxis.setMinPadding(0.2);
        xAxis.setMaxPadding(0.2);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new AxisTitle("Value"));
        yAxis.setPlotLines(new PlotLine(0, 1, new SolidColor("#808080")));
        yAxis.setMinPadding(0.2);
        yAxis.setMaxPadding(0.2);

        Legend legend = configuration.getLegend();
        legend.setEnabled(false);
        configuration.setExporting(false);

        PlotOptionsSeries opt = new PlotOptionsSeries();
        opt.setLineWidth(1);
        configuration.setPlotOptions(opt);

        final DataSeries series = new DataSeries();
        series.add(new DataSeriesItem(20, 20));
        series.add(new DataSeriesItem(80, 80));
        configuration.setSeries(series);

        chart.drawChart(configuration);
        chart.addChartClickListener(new ChartClickListener() {

            @Override
            public void onClick(ChartClickEvent event) {
                double x = Math.round(event.getxAxisValue());
                double y = Math.round(event.getyAxisValue());
                series.add(new DataSeriesItem(x, y));
                lastAction.setValue("Added point " + x + "," + y);
                eventDetails.setValue(createEventString(event));
            }
        });
        chart.addPointClickListener(new PointClickListener() {

            @Override
            public void onClick(PointClickEvent event) {
                DataSeries ds = (DataSeries) event.getSeries();
                DataSeriesItem dataSeriesItem2 = ds.get(event.getPointIndex());
                ds.remove(dataSeriesItem2);
                lastAction.setValue("Removed point at index "
                        + event.getPointIndex());
                eventDetails.setValue(createEventString(event));
            }
        });
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(chart);
        verticalLayout.addComponent(lastAction);
        verticalLayout.addComponent(eventDetails);
        return verticalLayout;
    }

    private String createEventString(Event event) {
        ObjectMapper mapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setVisibility(PropertyAccessor.ALL,
                        JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD,
                        JsonAutoDetect.Visibility.ANY);

        try {
            return mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void setup() {
        super.setup();
        final CheckBox checkbox = new CheckBox("Animate additions");
        checkbox.setImmediate(true);
        checkbox.setValue(true);
        checkbox.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                chart.getConfiguration().getChart()
                        .setAnimation(checkbox.getValue());
            }
        });
        addComponentAsFirst(checkbox);
    }
}
