package com.vaadin.addon.charts.examples.combinations;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.area.PercentageArea;
import com.vaadin.addon.charts.examples.pie.PieChart;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class DualCharts extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Two charts in one UI";
    }

    @Override
    protected Component getChart() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");

        Chart chartA = PercentageArea.createNewChart();
        chartA.setHeight("450px");
        chartA.setWidth("100%");
        layout.addComponent(chartA);
        layout.setExpandRatio(chartA, 1.0f);

        Chart chartB = PieChart.createChart();
        chartB.setHeight("450px");
        chartB.setWidth("100%");
        layout.addComponent(chartB);
        layout.setExpandRatio(chartB, 1.0f);
        return layout;
    }

}
