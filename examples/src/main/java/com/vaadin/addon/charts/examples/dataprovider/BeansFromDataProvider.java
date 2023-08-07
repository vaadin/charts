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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataProviderSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Component;

@SkipFromDemo
public class BeansFromDataProvider extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Simple Chart with DataProvider";
    }

    @Override
    protected Component getChart() {
        ListDataProvider<ClaimsReportItem> dp = DataProvider
                .ofCollection(getMockData());

        // Create ChartDataSeries
        DataProviderSeries<ClaimsReportItem> series = new DataProviderSeries<>(dp);
        series.setName("Claims");
        series.setPlotOptions(new PlotOptionsColumn());
        series.setY(ClaimsReportItem::getAmount);
        series.setPointName(ClaimsReportItem::getType);

        // Create chart & configuration
        Chart chart = new Chart();
        Configuration configuration = chart.getConfiguration();
        configuration.getTitle().setText("Claim Processing");

        // Create Y Axis
        YAxis y = new YAxis();
        y.setTitle("Amount");
        configuration.addyAxis(y);

        // use category names from beans on x axis instead of index
        configuration.getxAxis().setType(AxisType.CATEGORY);
        configuration.setSeries(series);

        chart.setSizeFull();

        return chart;
    }
    private Collection<ClaimsReportItem> getMockData(){
        Collection<ClaimsReportItem> col = new ArrayList<>();
        col.add(new ClaimsReportItem("manual", 100));
        col.add(new ClaimsReportItem("automatic", 600));
        return col;
    }
    private class ClaimsReportItem implements Serializable {
        private String type;
        private Integer amount;

        public ClaimsReportItem(String type, Integer amount) {
            this.type = type;
            this.amount = amount;

        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}
