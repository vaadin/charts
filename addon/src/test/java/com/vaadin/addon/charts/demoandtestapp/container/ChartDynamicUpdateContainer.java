package com.vaadin.addon.charts.demoandtestapp.container;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartClickEvent;
import com.vaadin.addon.charts.ChartClickListener;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AbstractContainerSeries;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ContainerSeries;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ChartDynamicUpdateContainer extends AbstractVaadinChartExample {

    private Chart chart;
    private Component tableLo;
    private AbstractContainerSeries container;

    @Override
    public String getDescription() {
        return "Chart with contianer that is updated dynamically";
    }

    @Override
    protected Component getChart() {
        HorizontalLayout lo = new HorizontalLayout();
        container = createContainer();
        tableLo = createTable(container);
        chart = createChart(container);

        lo.setWidth("100%");
        lo.setHeight("450px");
        lo.addComponents(tableLo, chart);

        tableLo.setWidth("180px");
        chart.setSizeFull();

        lo.setExpandRatio(chart, 1);
        return lo;
    }

    private AbstractContainerSeries createContainer() {
        AbstractContainerSeries container = new ContainerSeries();
        container.addContainerProperty("x", Number.class, null);
        container.addContainerProperty("y", Number.class, null);

        container.addDefaultPropertyId("x");
        container.addDefaultPropertyId("y");

        container.setName("User supplied data");
        container.setType(ChartType.LINE);

        Item ie = container.addItem(1);
        ie.getItemProperty("x").setValue(80);
        ie.getItemProperty("y").setValue(80);

        ie = container.addItem(2);
        ie.getItemProperty("x").setValue(20);
        ie.getItemProperty("y").setValue(20);

        return container;
    }

    private Component createTable(final Container container) {
        Table t = new Table();
        t.setContainerDataSource(container);
        t.setImmediate(true);
        t.setPageLength(8);

        final TextField tf1 = new TextField();
        final TextField tf2 = new TextField();
        tf1.setColumns(3);
        tf2.setColumns(3);
        Button submitButton = new Button("Submit", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Item ie = container.addItem(container.size() + 1);
                Integer x = new Integer(tf1.getValue().toString());
                Integer y = new Integer(tf2.getValue().toString());

                ie.getItemProperty("x").setValue(x);
                ie.getItemProperty("y").setValue(y);

                ChartDynamicUpdateContainer.this.container.addPoint(x, y);
            }
        });

        return new VerticalLayout(new Label(
                "User supplied data from Vaadin Container"), t,
                new HorizontalLayout(tf1, tf2, submitButton));
    }

    public Chart createChart(final AbstractContainerSeries container) {
        final Chart chart = new Chart();

        final Configuration configuration = new Configuration();
        configuration.getChart().setType(ChartType.SCATTER);
        configuration.getTitle().setText(
                "User supplied data from Vaadin Container");
        configuration.getSubTitle().setText(
                "Click the plot area to add a point.");

        Axis xAxis = configuration.getxAxis();
        xAxis.setMinPadding(0.2);
        xAxis.setMaxPadding(0.2);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle("Value");
        yAxis.setPlotLines(new PlotLine(0, 1, new SolidColor("#808080")));

        Legend legend = configuration.getLegend();
        legend.setEnabled(false);

        configuration.setExporting(false);
        configuration.addSeries(container);

        chart.drawChart(configuration);

        chart.addChartClickListener(new ChartClickListener() {

            @Override
            public void onClick(ChartClickEvent event) {
                double x = Math.round(event.getxAxisValue());
                double y = Math.round(event.getyAxisValue());
                Item newItem = container.addItem(container.size() + 1);
                newItem.getItemProperty("x").setValue(x);
                newItem.getItemProperty("y").setValue(y);
                ChartDynamicUpdateContainer.this.container.addPoint(x, y);
            }
        });

        return chart;
    }
}
