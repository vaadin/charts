package com.vaadin.addon.charts.examples.columnandbar;

import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
@SkipFromDemo
public class BarWithDisabledState extends BasicBarNoTitle {

    private Component chart;

    @Override
    protected Component getChart() {
        chart = super.getChart();
        chart.setId("bar-with-disabled-state");

        return chart;
    }

    @Override
    protected void setup() {
        super.setup();

        Button button = new Button("toggle enabled", e -> chart.setEnabled(!chart.isEnabled()));
        button.setId("disable-button");
        addComponent(button);
    }
}
