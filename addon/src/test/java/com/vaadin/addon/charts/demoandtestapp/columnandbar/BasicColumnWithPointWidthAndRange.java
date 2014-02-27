package com.vaadin.addon.charts.demoandtestapp.columnandbar;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Slider;

@SuppressWarnings("serial")
public class BasicColumnWithPointWidthAndRange extends
        AbstractVaadinChartExample {

    private PlotOptionsColumn plotOptions;
    private Chart chart;

    @Override
    public String getDescription() {
        return "Basic bar with point with and range set";
    }

    @Override
    protected Component getChart() {
        chart = new Chart(ChartType.COLUMN);

        Configuration conf = chart.getConfiguration();

        plotOptions = new PlotOptionsColumn();
        // plotOptions.setPointRange(10);
        plotOptions.setPointWidth(100);
        conf.setPlotOptions(plotOptions);

        DataSeries dataSeries = new DataSeries();
        dataSeries.addData(new Number[][] { { 0, 1 }, { 2, 2 }, { 10, 3 } });
        conf.setSeries(dataSeries);

        chart.drawChart(conf);

        return chart;
    }

    @Override
    protected void setup() {
        super.setup();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(true);
        final Slider slider = new Slider("Value (1-100)", 1, 100);
        slider.setWidth("200px");
        slider.setValue(100d);

        final NativeSelect option = new NativeSelect();
        option.setCaption("Option");
        option.setNullSelectionAllowed(true);
        option.addItem("pointWidth");
        option.addItem("pointRange");
        option.setValue("pointWidth");
        option.setImmediate(true);
        option.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                slider.setEnabled(event.getProperty().getValue() != null);
            }
        });

        horizontalLayout.addComponent(option);
        horizontalLayout.addComponent(slider);
        Button button = new Button("Apply");
        button.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                if (slider.isEnabled()) {
                    if (option.getValue().equals("pointWidth")) {
                        plotOptions.setPointWidth(slider.getValue());
                        plotOptions.setPointRange(null);
                    } else {
                        plotOptions.setPointRange(slider.getValue());
                        plotOptions.setPointWidth(null);
                    }
                } else {
                    plotOptions.setPointRange(null);
                    plotOptions.setPointWidth(null);
                }
                chart.drawChart();
            }
        });
        horizontalLayout.addComponent(button);

        addComponent(horizontalLayout);
    }
}