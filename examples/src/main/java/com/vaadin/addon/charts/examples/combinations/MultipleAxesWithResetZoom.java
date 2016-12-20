package com.vaadin.addon.charts.examples.combinations;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@SkipFromDemo
public class MultipleAxesWithResetZoom extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Multiple axes with reset zoom button";
    }

    @Override
    protected Component getChart() {
        final Chart chart = (Chart) new MultipleAxes().getChart();
        chart.getConfiguration().getTooltip().setEnabled(false);
        Button button = new Button("Reset zoom", new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                chart.getConfiguration().resetZoom();
            }
        });
        VerticalLayout verticalLayout = new VerticalLayout(chart, button);
        verticalLayout.setSpacing(false);
        verticalLayout.setMargin(false);
        return verticalLayout;
    }

}
