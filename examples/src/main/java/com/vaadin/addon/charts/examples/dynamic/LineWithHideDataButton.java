package com.vaadin.addon.charts.examples.dynamic;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SkipFromDemo
public class LineWithHideDataButton extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Basic Line With Data Labels";
    }

    @Override
    protected Component getChart() {
        Label extremes = new Label();
        extremes.setId("extremesLabel");

        Chart chart = new Chart();

        Configuration configuration = chart.getConfiguration();

        ListSeries ls = new ListSeries();
        ls.setData(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,
                13.9, 9.6);
        configuration.addSeries(ls);
        chart.addXAxesExtremesChangeListener((event) -> extremes.setValue(
                "Min " + event.getMinimum() + " Max: " + event.getMaximum()));

        final Button showHideSeries = new Button("Show/Hide series");
        showHideSeries.setId("showHideSeriesButton");
        showHideSeries
                .addClickListener((e) -> ls.setVisible(!ls.isVisible()));

        return new VerticalLayout(showHideSeries, extremes, chart);
    }

}
