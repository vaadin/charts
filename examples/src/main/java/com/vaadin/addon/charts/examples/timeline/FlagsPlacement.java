package com.vaadin.addon.charts.examples.timeline;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.timeline.util.StockPrices;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.FlagItem;
import com.vaadin.addon.charts.model.PlotOptionsFlags;
import com.vaadin.addon.charts.model.RangeSelector;
import com.vaadin.ui.Component;

public class FlagsPlacement extends AbstractVaadinChartExample {

    public static final String DATA_SERIES_ID = "dataseries";

    @Override
    public String getDescription() {
        return "Single line chart with timeline";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");
        chart.setTimeline(true);

        Configuration configuration = chart.getConfiguration();
        configuration.getTitle().setText("AAPL Stock Price");

        DataSeries dataSeries = new DataSeries();
        dataSeries.setId(DATA_SERIES_ID);
        for (StockPrices.PriceData data : StockPrices.fetchAaplPrice()) {
            DataSeriesItem item = new DataSeriesItem();
            item.setX(data.getDate());
            item.setY(data.getPrice());
            dataSeries.add(item);
        }

        DataSeries flagsOnSeries = new DataSeries();
        flagsOnSeries.setName("Flags on series");
        PlotOptionsFlags plotOptionsFlags = new PlotOptionsFlags();
        plotOptionsFlags.setOnSeries(DATA_SERIES_ID);
        flagsOnSeries.setPlotOptions(plotOptionsFlags);
        for (int i = 30; i < dataSeries.size(); i += 30) {
            flagsOnSeries.add(new FlagItem(dataSeries.get(i).getX(),
                    "On series", "On series flag tooltip"));
        }

        DataSeries flagsOnAxis = new DataSeries();
        flagsOnAxis.setName("Flags on axis");
        flagsOnAxis.setPlotOptions(new PlotOptionsFlags());
        for (int i = 15; i < dataSeries.size(); i += 30) {
            flagsOnAxis.add(new FlagItem(dataSeries.get(i).getX(), "On axis"));
        }
        configuration.setSeries(dataSeries, flagsOnSeries, flagsOnAxis);

        RangeSelector rangeSelector = new RangeSelector();
        rangeSelector.setSelected(1);
        configuration.setRangeSelector(rangeSelector);

        chart.drawChart(configuration);

        return chart;
    }
}
