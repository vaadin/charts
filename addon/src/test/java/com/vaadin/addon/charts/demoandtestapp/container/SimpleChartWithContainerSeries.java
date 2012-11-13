package com.vaadin.addon.charts.demoandtestapp.container;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AbstractContainerSeries;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ContainerSeries;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.themes.VaadinTheme;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Table;

public class SimpleChartWithContainerSeries extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Simple Chart with ContainerSeries";
    }

    @Override
    protected Component getChart() {
        CssLayout lo = new CssLayout();
        AbstractContainerSeries container = createContainer();
        Component table = createTable(container);
        Component chart = createChart(container);

        table.setWidth("50%");
        table.setHeight("100%");
        chart.setWidth("50%");
        chart.setHeight("100%");

        lo.setWidth("100%");
        lo.setHeight("450px");
        lo.addComponents(table);
        lo.addComponent(chart);
        return lo;
    }

    private AbstractContainerSeries createContainer() {
        AbstractContainerSeries container = new ContainerSeries();
        container.addContainerProperty("name", String.class, null);
        container.addContainerProperty("y", Number.class, null);
        container.addContainerProperty("color", Color.class, null);

        container.setName("Browser share");
        container.setType(ChartType.PIE);

        String[] names = new String[] { "MSIE", "Firefox", "Chrome", "Safari",
                "Opera" };
        Number[] values = new Number[] { 55.11, 21.63, 11.94, 7.15, 2.14 };
        Color[] colors = new VaadinTheme().getColors();

        for (int i = 0; i < names.length; i++) {
            Item ie = container.addItem(i);
            ie.getItemProperty("name").setValue(names[i]);
            ie.getItemProperty("y").setValue(values[i]);
            ie.getItemProperty("color").setValue(colors[i]);
        }

        return container;
    }

    private Component createTable(Container container) {
        Table t = new Table();
        t.setCaption("Data from Vaadin Container");
        t.setContainerDataSource(container);
        t.setImmediate(true);
        return t;
    }

    public static Chart createChart(Series container) {
        final Chart chart = new Chart();

        final Configuration configuration = new Configuration();
        configuration.getChart().setType(ChartType.PIE);
        configuration.getTitle().setText("Data from Vaadin Container");

        configuration.setSeries(container);

        chart.drawChart(configuration);

        return chart;
    }
}
