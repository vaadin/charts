/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples.container;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.ExampleUtil;
import com.vaadin.addon.charts.model.AbstractSeries;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.v7.addon.charts.model.ContainerDataSeries;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.v7.ui.Table;

public class ChartWithExternalContainer extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Chart with Container containing much data";
    }

    @Override
    protected Component getChart() {
        HorizontalLayout lo = new HorizontalLayout();
        lo.setSpacing(true);
        Container vaadinContainer = ExampleUtil.getOrderContainer();

        ContainerDataSeries container1 = createContainerView1(vaadinContainer);
        ContainerDataSeries container2 = createContainerView2(vaadinContainer);
        Component table = createTable(vaadinContainer);
        Component chart1 = createChart1(container1);
        Component chart2 = createChart2(container2);

        table.setSizeFull();
        chart1.setSizeFull();
        chart2.setSizeFull();

        lo.setWidth("100%");
        lo.setHeight("450px");
        lo.addComponents(table);
        lo.addComponent(chart1);
        lo.addComponent(chart2);
        lo.setExpandRatio(table, 0.2f);
        lo.setExpandRatio(chart1, 0.4f);
        lo.setExpandRatio(chart2, 0.4f);
        return lo;
    }

    private ContainerDataSeries createContainerView1(Container vaadinContainer) {
        ContainerDataSeries container = new ContainerDataSeries(vaadinContainer);
        container.setName("Order item quantities");
        container.setPlotOptions(new PlotOptionsPie());
        container.setYPropertyId(ExampleUtil.ORDER_QUANTITY_PROPERTY_ID);
        container.setNamePropertyId(ExampleUtil.ORDER_DESCRIPTION_PROPERTY_ID);
        return container;
    }

    private ContainerDataSeries createContainerView2(Container vaadinContainer) {
        ContainerDataSeries container = new ContainerDataSeries(vaadinContainer);
        container.setName("Order item prices");
        container.setPlotOptions(new PlotOptionsColumn());
        container.setYPropertyId(ExampleUtil.ORDER_ITEMPRICE_PROPERTY_ID);
        container.setNamePropertyId(ExampleUtil.ORDER_DESCRIPTION_PROPERTY_ID);
        return container;
    }

    private Component createTable(Container container) {
        Table t = new Table();
        t.setCaption("Data from Vaadin Container");
        t.setContainerDataSource(container);
        t.setItemCaptionMode(ItemCaptionMode.ID);
        t.setImmediate(true);
        return t;
    }

    public static Chart createChart1(Series container) {
        final Chart chart = new Chart();

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.PIE);
        configuration.getTitle().setText("Order item quantities");
        configuration.getLegend().setEnabled(false);

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        configuration.setPlotOptions(plotOptions);

        configuration.setSeries(container);
        chart.drawChart(configuration);
        return chart;
    }

    public static Chart createChart2(AbstractSeries container) {
        final Chart chart = new Chart();

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.COLUMN);
        configuration.getTitle().setText("Order item totals");
        configuration.getLegend().setEnabled(false);

        YAxis ax = new YAxis();
        ax.setTitle("");
        configuration.addyAxis(ax);

        PlotOptionsColumn plotOptions = new PlotOptionsColumn();
        configuration.setPlotOptions(plotOptions);

        configuration.setSeries(container);
        chart.drawChart(configuration);

        return chart;
    }
}
