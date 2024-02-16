/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples.timeline;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.timeline.util.StockPrices;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataGrouping;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.OhlcItem;
import com.vaadin.addon.charts.model.PlotOptionsCandlestick;
import com.vaadin.addon.charts.model.RangeSelector;
import com.vaadin.addon.charts.model.TimeUnit;
import com.vaadin.addon.charts.model.TimeUnitMultiples;
import com.vaadin.ui.Component;

public class Candlestick extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Single line chart with timeline";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart(ChartType.CANDLESTICK);
        chart.setHeight("450px");
        chart.setWidth("100%");
        chart.setTimeline(true);

        Configuration configuration = chart.getConfiguration();
        configuration.getTitle().setText("AAPL Stock Price");

        DataSeries dataSeries = new DataSeries();
        PlotOptionsCandlestick plotOptionsCandlestick = new PlotOptionsCandlestick();
        DataGrouping grouping = new DataGrouping();
        grouping.addUnit(new TimeUnitMultiples(TimeUnit.WEEK, 1));
        grouping.addUnit(new TimeUnitMultiples(TimeUnit.MONTH, 1, 2, 3, 4, 6));
        plotOptionsCandlestick.setDataGrouping(grouping);
        dataSeries.setPlotOptions(plotOptionsCandlestick);
        for (StockPrices.OhlcData data : StockPrices.fetchAaplOhlcPrice()) {
            OhlcItem item = new OhlcItem();
            item.setX(data.getDate());
            item.setLow(data.getLow());
            item.setHigh(data.getHigh());
            item.setClose(data.getClose());
            item.setOpen(data.getOpen());
            dataSeries.add(item);
        }
        configuration.setSeries(dataSeries);

        RangeSelector rangeSelector = new RangeSelector();
        rangeSelector.setSelected(4);
        configuration.setRangeSelector(rangeSelector);

        chart.drawChart(configuration);

        return chart;
    }
}
