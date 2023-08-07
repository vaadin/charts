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
package com.vaadin.addon.charts.examples.dataprovider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataProviderSeries;
import com.vaadin.addon.charts.model.Hover;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.provider.AbstractDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SkipFromDemo
public class ChartWithExternalDataProviderWithChangingData
        extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Chart with data provider where the data changes";
    }

    private ChartDataProvider<?> data = new ChartDataProvider<SerializablePredicate<?>>(
            getMockData());

    @Override
    protected Component getChart() {
        HorizontalLayout lo = new HorizontalLayout();
        VerticalLayout vlo = new VerticalLayout();

        DataProviderSeries<Data> ds = createChartDS();
        Component grid = createGrid();

        TextField field = new TextField("New data value");
        Binder<Data> binder = new Binder<>();
        binder.forField(field)
                .withValidator((String v) -> v != null && !v.isEmpty(),
                        "The field cannot be empty")
                .withConverter(new StringToDoubleConverter("Not a double"))
                .bind(Data::getValue, Data::setValue);
        binder.readBean(new Data(0.0));

        Button button = new Button("Add data", e -> {
            Data v = new Data(0.0);
            try {
                binder.writeBean(v);
                data.add(v);
                v = new Data(1.0);
                binder.readBean(v);
            } catch (ValidationException ve) {
            }
        });
        Component chart = createChart(ds);
        vlo.addComponents(field, button, grid);
        vlo.setSpacing(true);
        lo.addComponents(vlo, chart);

        grid.setSizeFull();
        chart.setSizeFull();
        lo.setSizeFull();
        lo.setExpandRatio(vlo, 1);
        lo.setExpandRatio(chart, 3);
        return lo;
    }

    private DataProviderSeries<Data> createChartDS() {
        DataProviderSeries<Data> ds = new DataProviderSeries<>(data,
                Data::getValue);
        ds.setName("Data from data provider");
        return ds;
    }

    private Component createGrid() {
        Grid<Data> g = new Grid<>();
        g.setDataProvider(data);
        g.setCaption("Data from Vaadin DataProvider");
        g.addColumn(data -> Double.toString(data.getValue()))
                .setCaption("Data from data provider");
        return g;
    }

    public static Chart createChart(Series ds) {
        final Chart chart = new Chart(ChartType.AREA);

        final Configuration configuration = chart.getConfiguration();
        configuration.getTitle().setText("Data from Vaadin DataProvider");
        configuration.getLegend().setEnabled(false);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new AxisTitle("Data values"));
        yAxis.setMin(0.6);
        yAxis.setStartOnTick(false);
        yAxis.setShowFirstLabel(false);

        configuration.getTooltip().setShared(true);

        PlotOptionsArea plotOptions = new PlotOptionsArea();

        GradientColor fillColor = GradientColor.createLinear(0, 0, 0, 1);
        fillColor.addColorStop(0, new SolidColor("#4572A7"));
        fillColor.addColorStop(1, new SolidColor(2, 0, 0, 0));
        plotOptions.setFillColor(fillColor);

        plotOptions.setLineWidth(1);
        plotOptions.setShadow(false);

        Marker marker = plotOptions.getMarker();
        marker.setEnabled(false);
        Hover hoverState = new Hover(true);
        hoverState.setRadius(5);
        hoverState.setLineWidth(1);
        marker.getStates().setHover(hoverState);

        plotOptions.getStates().setHover(new Hover(true));
        plotOptions.setShadow(false);
        configuration.setPlotOptions(plotOptions);

        configuration.setSeries(ds);

        chart.drawChart(configuration);

        return chart;
    }

    private List<Data> getMockData() {
        List<Data> data = new ArrayList<>();
        for (Double value : getContainerData()) {
            data.add(new Data(value));
        }
        return data;
    }

    private class Data {
        private double value;

        private Data(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double d) {
            value = d;
        }
    }

    private Double[] getContainerData() {
        return new Double[] { 0.8446, 0.8445, 0.8444 };
    }

    private static class ChartDataProvider<F>
            extends AbstractDataProvider<Data, F> {

        List<Data> data;

        public ChartDataProvider(List<Data> data) {
            this.data = data;
        }

        public void add(Data item) {
            data.add(item);
            refreshAll();
        }

        @Override
        public boolean isInMemory() {
            return true;
        }

        @Override
        public int size(Query<Data, F> t) {
            return data.size();
        }

        @Override
        public Stream<Data> fetch(Query<Data, F> query) {
            return data.stream();
        }

    }
}
