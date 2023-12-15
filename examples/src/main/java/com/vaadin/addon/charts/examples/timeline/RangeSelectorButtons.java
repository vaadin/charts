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
package com.vaadin.addon.charts.examples.timeline;

import static com.vaadin.addon.charts.model.RangeSelectorTimespan.ALL;
import static com.vaadin.addon.charts.model.RangeSelectorTimespan.MONTH;
import static com.vaadin.addon.charts.model.RangeSelectorTimespan.YEAR;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.examples.timeline.util.StockPrices;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataGrouping;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.RangeSelector;
import com.vaadin.addon.charts.model.RangeSelectorButton;
import com.vaadin.addon.charts.model.TimeUnit;
import com.vaadin.addon.charts.model.TimeUnitMultiples;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

@SkipFromDemo
public class RangeSelectorButtons extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Chart with custom buttons for range selector.";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");
        chart.setTimeline(true);

        Configuration configuration = chart.getConfiguration();

        YAxis yAxis = new YAxis();

        PlotLine plotLine = new PlotLine();
        plotLine.setValue(2);
        plotLine.setWidth(2);
        plotLine.setColor(SolidColor.SILVER);
        yAxis.setPlotLines(plotLine);
        configuration.addyAxis(yAxis);

        DataSeries aaplSeries = new DataSeries();
        for (StockPrices.PriceData data : StockPrices.fetchAaplPrice()) {
            DataSeriesItem item = new DataSeriesItem();
            item.setX(data.getDate());
            item.setY(data.getPrice());
            aaplSeries.add(item);
        }

        configuration.setSeries(aaplSeries);

        RangeSelector rangeSelector = new RangeSelector();
        rangeSelector.setSelected(1);

        RangeSelectorButton button = new RangeSelectorButton(MONTH, 3, "D");
        DataGrouping grouping = new DataGrouping();
        grouping.setForced(true);
        grouping.setUnits(new TimeUnitMultiples(TimeUnit.DAY, 1));
        button.setDataGrouping(grouping);
        rangeSelector.addButton(button);
        button = new RangeSelectorButton(YEAR, 1, "W");
        grouping = new DataGrouping();
        grouping.setForced(true);
        grouping.setUnits(new TimeUnitMultiples(TimeUnit.WEEK, 1));
        button.setDataGrouping(grouping);
        rangeSelector.addButton(button);
        button = new RangeSelectorButton(ALL, "M");
        grouping = new DataGrouping();
        grouping.setForced(true);
        grouping.setUnits(new TimeUnitMultiples(TimeUnit.MONTH, 1));
        button.setDataGrouping(grouping);
        rangeSelector.addButton(button);

        configuration.setRangeSelector(rangeSelector);

        chart.drawChart(configuration);
        return chart;
    }
}
