/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples.declarative;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.declarative.ChartComponentMapper;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.timeline.util.StockPrices;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Component;
import com.vaadin.ui.declarative.Design;

@DesignRoot(value = "compare_multiple_series.html")
public class DeclarativeCompareMultipleSeries extends
        AbstractVaadinChartExample {

    private static final String DATA_SERIES_ID = "seriesId";
    Chart myChart;

    @Override
    public String getDescription() {
        return "Timeline with flags using declarative format";
    }

    @Override
    protected void setup() {
        Design.setComponentMapper(new ChartComponentMapper());
        Design.read(this);
        addSeriesTo(myChart.getConfiguration());
    }

    private void addSeriesTo(Configuration configuration) {
        DataSeries aaplSeries = new DataSeries();
        aaplSeries.setName("AAPL");
        for (StockPrices.PriceData data : StockPrices.fetchAaplPrice()) {
            DataSeriesItem item = new DataSeriesItem();
            item.setX(data.getDate());
            item.setY(data.getPrice());
            aaplSeries.add(item);
        }
        DataSeries googSeries = new DataSeries();
        googSeries.setName("GOOG");
        for (StockPrices.PriceData data : StockPrices.fetchGoogPrice()) {
            DataSeriesItem item = new DataSeriesItem();
            item.setX(data.getDate());
            item.setY(data.getPrice());
            googSeries.add(item);
        }
        DataSeries msftSeries = new DataSeries();
        msftSeries.setName("MSFT");
        for (StockPrices.PriceData data : StockPrices.fetchMsftPrice()) {
            DataSeriesItem item = new DataSeriesItem();
            item.setX(data.getDate());
            item.setY(data.getPrice());
            msftSeries.add(item);
        }
        configuration.setSeries(aaplSeries, googSeries, msftSeries);
    }

    @Override
    protected Component getChart() {
        return null;
    }

}
