package com.vaadin.addon.charts.demoandtestapp.dynamic;

import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.themes.VaadinTheme;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Slider;
import com.vaadin.ui.Slider.ValueOutOfBoundsException;

@SuppressWarnings("serial")
public class ModifyOnePoint extends AbstractVaadinChartExample {

    private DataSeriesItem dataSeriesItem;
    private DataSeries series;

    @Override
    public String getDescription() {
        return "Tests click event processing on the server side. Clicking on chart adds new point to series at clicked y value. ";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart();
        chart.setHeight("300px");
        chart.setWidth("100%");

        final Configuration configuration = new Configuration();
        configuration.getTooltip().setEnabled(false);

        configuration.getChart().setType(ChartType.SPLINE);

        series = new DataSeries();
        series.add(createBasicPoint(1, 4));
        series.add(new DataSeriesItem(3, 4));
        dataSeriesItem = new DataSeriesItem(4, 4);
        Marker marker = new Marker(true);
        marker.setRadius(10);
        marker.setFillColor(new SolidColor("red"));
        dataSeriesItem.setMarker(marker);
        dataSeriesItem.setName("Special point");
        series.add(dataSeriesItem);
        series.add(new DataSeriesItem(8, 3));

        configuration.setSeries(series);

        chart.drawChart(configuration);

        return chart;
    }

    static final Color[] colors = new VaadinTheme().getColors();

    static final Color COLOR_NORMAL = colors[0];

    private DataSeriesItem createBasicPoint(int i, int j) {
        DataSeriesItem dataSeriesItem2 = new DataSeriesItem(1, 4);
        return dataSeriesItem2;
    }

    @Override
    protected void setup() {
        super.setup();

        FormLayout formLayout = new FormLayout();
        formLayout
                .setCaption("Special point settings, only updated point state is sent to client.");
        formLayout.setMargin(true);

        final Slider sliderX = new Slider();
        sliderX.setMin(3);
        sliderX.setMax(8);
        sliderX.setResolution(1);
        try {
            sliderX.setValue(4d);
        } catch (ValueOutOfBoundsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sliderX.setCaption("X");
        sliderX.addListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                dataSeriesItem.setX((Number) sliderX.getValue());
                series.update(dataSeriesItem);
            }
        });
        sliderX.setWidth("200px");
        sliderX.setImmediate(true);
        formLayout.addComponent(sliderX);

        final Slider sliderY = new Slider();
        sliderY.setMin(0);
        sliderY.setMax(10);
        sliderY.setResolution(1);
        try {
            sliderY.setValue(4d);
        } catch (ValueOutOfBoundsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sliderY.setCaption("Y");
        sliderY.addListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                dataSeriesItem.setY((Number) sliderY.getValue());
                updateItemInChart();
            }
        });
        sliderY.setWidth("200px");
        sliderY.setImmediate(true);
        formLayout.addComponent(sliderY);

        Button c = new Button("Pseudorandom", new Button.ClickListener() {
            Random r = new Random(0);

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    sliderX.setValue(r.nextDouble() * 5 + 3);
                    sliderY.setValue(r.nextDouble() * 10);
                } catch (ValueOutOfBoundsException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        c.setDebugId("random");
        formLayout.addComponent(c);

        addComponentAsFirst(formLayout);

    }

    private void updateItemInChart() {
        series.update(dataSeriesItem);
    }

}
