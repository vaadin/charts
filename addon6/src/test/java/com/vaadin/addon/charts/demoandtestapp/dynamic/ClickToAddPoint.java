package com.vaadin.addon.charts.demoandtestapp.dynamic;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartClickEvent;
import com.vaadin.addon.charts.ChartClickListener;
import com.vaadin.addon.charts.PointClickEvent;
import com.vaadin.addon.charts.PointClickListener;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.addon.charts.model.Title;
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

    @Override
    public String getDescription() {
        return "Click to add point";
    }

    @Override
    protected Component getChart() {
        lastAction.setDebugId("lastAction");
        chart = new Chart();
        chart.setDebugId("chart");
        chart.setWidth("500px");

        final Configuration configuration = new Configuration();
        configuration.getChart().setType(ChartType.SCATTER);
        configuration.getTitle().setText("User supplied data");
        configuration
                .getSubTitle()
                .setText(
                        "Click the plot area to add a point. Click a point to remove it.");

        Axis xAxis = configuration.getxAxis();
        xAxis.setMinPadding(0.2);
        xAxis.setMaxPadding(0.2);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new Title("Value"));
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
            }
        });
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(chart);
        verticalLayout.addComponent(lastAction);
        return verticalLayout;
    }

    @Override
    protected void setup() {
        super.setup();
        final CheckBox checkbox = new CheckBox("Animate additions");
        checkbox.setImmediate(true);
        checkbox.setValue(true);
        checkbox.addListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                chart.getConfiguration().getChart()
                        .setAnimation((Boolean) checkbox.getValue());
            }
        });
        addComponentAsFirst(checkbox);
    }
}
