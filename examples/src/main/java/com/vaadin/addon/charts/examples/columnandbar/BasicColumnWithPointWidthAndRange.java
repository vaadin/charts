/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples.columnandbar;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.ui.Button;
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
        return "Basic bar with point width and range set";
    }

    @Override
    protected Component getChart() {
        chart = new Chart(ChartType.COLUMN);

        Configuration conf = chart.getConfiguration();
        conf.setTitle("Visualize point width and point range");

        plotOptions = new PlotOptionsColumn();
        // plotOptions.setPointRange(10);
        plotOptions.setPointWidth(100);
        conf.setPlotOptions(plotOptions);

        DataSeries dataSeries = new DataSeries();
        dataSeries.addData(new Number[][]{{0, 1}, {2, 2}, {10, 3}});
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

        final NativeSelect<String> option = new NativeSelect<>();
        option.setCaption("Option");
        option.setItems("", "pointWidth", "pointRange");
        option.setSelectedItem("pointWidth");
        option.addSelectionListener(sc -> {
            slider.setEnabled(!sc.getSelectedItem().get().equals(""));
        });

        horizontalLayout.addComponent(option);
        horizontalLayout.addComponent(slider);
        Button button = new Button("Apply");
        button.addClickListener(e -> {
            if (slider.isEnabled()) {
                if (option.getSelectedItem().get().equals("pointWidth")) {
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
        });
        horizontalLayout.addComponent(button);

        addComponent(horizontalLayout);
    }
}
