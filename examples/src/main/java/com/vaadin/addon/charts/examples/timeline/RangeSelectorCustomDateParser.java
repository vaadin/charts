package com.vaadin.addon.charts.examples.timeline;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 *
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 *
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.examples.timeline.util.StockPrices;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.RangeSelector;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.ButtonTheme;
import com.vaadin.addon.charts.model.style.FontWeight;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.ui.Component;

@SkipFromDemo
public class RangeSelectorCustomDateParser extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Chart with custom date parser for range selector.";
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
        for(StockPrices.PriceData data : StockPrices.fetchAaplPriceWithTime()) {
            DataSeriesItem item = new DataSeriesItem();
            item.setX(data.getDate());
            item.setY(data.getPrice());
            aaplSeries.add(item);
        }

        configuration.setSeries(aaplSeries);

        RangeSelector rangeSelector = new RangeSelector();
        rangeSelector.setSelected(4);
        ButtonTheme theme=new ButtonTheme();

        Style style=new Style();
        style.setColor(new SolidColor("#0766d8"));
        style.setFontWeight(FontWeight.BOLD);
        theme.setStyle(style);
        rangeSelector.setButtonTheme(theme);

        Style inputStyle=new Style();
        inputStyle.setColor(new SolidColor("#0766d8"));
        inputStyle.setFontWeight(FontWeight.BOLD);
        rangeSelector.setInputStyle(inputStyle);

        rangeSelector.setInputDateFormat("%H:%M:%S.%L");
        rangeSelector.setInputEditDateFormat("%H:%M:%S.%L");
        //All the data is for 2009 Jan 28, so we don't bother to parse date only parse time
        rangeSelector.setInputDateParser("function(value) {" +
                "value = value.split(/[:\\.]/);\n" +
                "return Date.UTC(\n" +
                "   2009,\n" +
                "   0,\n" +
                "   28,\n" +
                "   parseInt(value[0], 10),\n" +
                "   parseInt(value[1], 10),\n" +
                "   parseInt(value[2], 10),\n" +
                "   parseInt(value[3], 10)" +
                ");}");


        configuration.setRangeSelector(rangeSelector);

        chart.drawChart(configuration);
        return chart;
    }
}
