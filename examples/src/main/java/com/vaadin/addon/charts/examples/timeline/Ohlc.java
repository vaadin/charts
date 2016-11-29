package com.vaadin.addon.charts.examples.timeline;

import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.timeline.util.StockPrices;
import com.vaadin.addon.charts.examples.timeline.util.StockPrices.OhlcData;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataGrouping;
import com.vaadin.addon.charts.model.DataProviderSeries;
import com.vaadin.addon.charts.model.PlotOptionsOhlc;
import com.vaadin.addon.charts.model.RangeSelector;
import com.vaadin.addon.charts.model.TimeUnit;
import com.vaadin.addon.charts.model.TimeUnitMultiples;
import com.vaadin.server.data.DataProvider;
import com.vaadin.server.data.ListDataProvider;
import com.vaadin.ui.Component;

public class Ohlc extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Single line chart with timeline";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart(ChartType.OHLC);
        chart.setHeight("450px");
        chart.setWidth("100%");
        chart.setTimeline(true);

        Configuration configuration = chart.getConfiguration();
        configuration.getTitle().setText("AAPL Stock Price");

        List<OhlcData> data = StockPrices.fetchAaplOhlcPrice();
        DataProvider<OhlcData> ds = new ListDataProvider<>(data);
        DataProviderSeries<OhlcData> dataSeries = new DataProviderSeries<>(ds);
        dataSeries.setX(OhlcData::getDate);
        dataSeries.setLow(OhlcData::getLow);
        dataSeries.setHigh(OhlcData::getHigh);
        dataSeries.setClose(OhlcData::getClose);
        dataSeries.setOpen(OhlcData::getOpen);

        PlotOptionsOhlc plotOptionsOhlc = new PlotOptionsOhlc();
        DataGrouping grouping = new DataGrouping();
        grouping.addUnit(new TimeUnitMultiples(TimeUnit.WEEK, 1));
        grouping.addUnit(new TimeUnitMultiples(TimeUnit.MONTH, 1, 2, 3, 4, 6));
        plotOptionsOhlc.setDataGrouping(grouping);
        plotOptionsOhlc.setTurboThreshold(0);
        dataSeries.setPlotOptions(plotOptionsOhlc);
        configuration.setSeries(dataSeries);

        RangeSelector rangeSelector = new RangeSelector();
        rangeSelector.setSelected(1);
        configuration.setRangeSelector(rangeSelector);

        return chart;
    }
}
