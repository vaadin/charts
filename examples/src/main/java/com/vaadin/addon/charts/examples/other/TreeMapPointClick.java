package com.vaadin.addon.charts.examples.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.PointClickEvent;
import com.vaadin.addon.charts.PointClickListener;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.TreeSeries;
import com.vaadin.addon.charts.model.TreeSeriesItem;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SkipFromDemo
public class TreeMapPointClick extends AbstractVaadinChartExample {

    private Label lastAction = new Label();

    @Override
    public String getDescription() {
        return "Tree map point click listener";
    }

    @Override
    protected Component getChart() {
        Chart chart = new Chart(ChartType.TREEMAP);
        chart.addPointClickListener(new PointClickListener() {
            @Override
            public void onClick(PointClickEvent event) {
                lastAction.setValue(event.getClass().getSimpleName());
            }
        });
        chart.getConfiguration().addSeries(createSeries());

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(chart);
        verticalLayout.addComponent(lastAction);
        return verticalLayout;
    }

    private TreeSeries createSeries() {
        TreeSeries series = new TreeSeries();

        TreeSeriesItem apples = new TreeSeriesItem("A", "Apples");
        apples.setColor(new SolidColor("#EC2500"));

        TreeSeriesItem bananas = new TreeSeriesItem("B", "Bananas");
        bananas.setColor(new SolidColor("#ECE100"));

        TreeSeriesItem oranges = new TreeSeriesItem("O", "Oranges");
        oranges.setColor(new SolidColor("#EC9800"));

        TreeSeriesItem anneA = new TreeSeriesItem("Anne", apples, 5);
        TreeSeriesItem rickA = new TreeSeriesItem("Rick", apples, 3);
        TreeSeriesItem peterA = new TreeSeriesItem("Peter", apples, 4);

        TreeSeriesItem anneB = new TreeSeriesItem("Anne", bananas, 4);
        TreeSeriesItem rickB = new TreeSeriesItem("Rick", bananas, 10);
        TreeSeriesItem peterB = new TreeSeriesItem("Peter", bananas, 1);

        TreeSeriesItem anneO = new TreeSeriesItem("Anne", oranges, 1);
        TreeSeriesItem rickO = new TreeSeriesItem("Rick", oranges, 3);
        TreeSeriesItem peterO = new TreeSeriesItem("Peter", oranges, 3);

        TreeSeriesItem susanne = new TreeSeriesItem("Susanne", 2);
        susanne.setParent("Kiwi");
        susanne.setColor(new SolidColor("#9EDE00"));

        series.addAll(apples, bananas, oranges, anneA, rickA, peterA, anneB,
                rickB, peterB, anneO, rickO, peterO, susanne);

        return series;
    }
}
