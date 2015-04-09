package com.vaadin.addon.charts.demoandtestapp.lineandscatter;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;

@SkipFromDemo
public class BasicLineWithAutoRotation extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Basic Line With Auto Rotate";
    }

    @Override
    protected Component getChart() {
        VerticalLayout layout = new VerticalLayout();
        final Chart chart = new Chart(ChartType.LINE);
        chart.setHeight("400px");
        chart.setWidth("100%");

        Configuration configuration = chart.getConfiguration();

        configuration.getTitle().setText("Monthly Average Temperature");
        configuration.getSubTitle().setText("Source: WorldClimate.com");

        configuration.getxAxis().setCategories("January", "February", "March",
                "April", "May", "June", "July", "August", "September",
                "October", "November", "December");

        Axis xAxis = configuration.getxAxis();
        Labels xLabels = xAxis.getLabels();
        xLabels.setAutoRotation(-10, -20, -30, -40, 50, -60, -70, -80, -90);

        ListSeries ls = new ListSeries();
        ls.setName("Tokyo");
        ls.setData(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,
                13.9, 9.6);
        configuration.addSeries(ls);
        ls = new ListSeries();
        ls.setName("New York");
        ls.setData(-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1,
                8.6, 2.5);
        configuration.addSeries(ls);

        layout.addComponent(chart);
        final Slider slider = new Slider("Width (50 - 100)", 50, 100);
        slider.setWidth("200px");
        slider.setValue(100d);

        layout.addComponent(slider);
        layout.addComponent(new Button("Set min width",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        slider.setValue(50d);
                    }
                }));

        slider.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                double newValue = slider.getValue();
                chart.setWidth((float) newValue, Unit.PERCENTAGE);

            }
        });

        return layout;
    }
}
