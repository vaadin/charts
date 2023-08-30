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
package com.vaadin.addon.charts.examples.librarydata;

import java.io.IOException;
import java.util.ArrayList;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LibraryData extends AbstractVaadinChartExample implements
        ClickListener, ValueChangeListener {

    private VerticalLayout content;
    private Helmet.Result result;
    private TextField searchField = new TextField("Query string");
    private Button prevButton = new Button("<", this);
    private Button nextButton = new Button(">", this);
    private Label pageLabel = new Label("-");
    private DataSeries series;
    private Chart chart;

    @Override
    protected void setup() {
        searchField.addValueChangeListener(this);
        searchField.setValue("orwell");
        searchField.setImmediate(true);

        HorizontalLayout searchNavi = new HorizontalLayout();
        searchNavi.addComponent(searchField);
        searchNavi.addComponent(prevButton);
        searchNavi.addComponent(pageLabel);
        searchNavi.addComponent(nextButton);
        updateSearchNavi();
        searchNavi.setWidth("100%");

        content = this;
        Component map = getChart();
        content.setSizeFull();
        content.addComponent(searchNavi);
        content.addComponent(map);
        content.setExpandRatio(map, 1);
    }

    @Override
    protected Component getChart() {
        chart = new Chart(ChartType.BAR);
        chart.setSizeFull();
        Configuration config = chart.getConfiguration();
        boolean inverted = config.getChart().getInverted();
        config.getChart().setInverted(!inverted);
        config.getLegend().setEnabled(false);
        YAxis axis = new YAxis();
        axis.setTitle("Pages");
        config.addyAxis(axis);
        series = new DataSeries();
        config.setSeries(series);
        updateChart();
        return chart;
    }

    private void updateChart() {
        if (series != null) {
            ArrayList<DataSeriesItem> list = new ArrayList<DataSeriesItem>();
            ArrayList<String> categories = new ArrayList<String>();
            for (Helmet.Record r : result.records) {
                int numPages = r.getNumPages();
                if (numPages != -1) {
                    String name = String.format("%s, written by %s %s",
                            r.title, r.author, r.description.isEmpty() ? ""
                                    : "<br/>" + r.description);
                    list.add(new DataSeriesItem(name, numPages));
                    categories.add(r.title.length() > 25 ? r.title.substring(0,
                            25) + "..." : r.title);
                }
            }
            series.setData(list);
            XAxis getyAxes = chart.getConfiguration().getxAxis();
            getyAxes.setCategories(categories.toArray(new String[categories
                    .size()]));
            chart.drawChart(chart.getConfiguration());
        }
    }

    @Override
    public void buttonClick(ClickEvent event) {
        try {
            if (event.getButton() == prevButton) {
                result = Helmet.search(searchField.getValue(),
                        result.currentPage - 1);
            } else if (event.getButton() == nextButton && result.hasMorePages()) {
                result = Helmet.search(searchField.getValue(),
                        result.currentPage + 1);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        updateChart();
        updateSearchNavi();
    }

    @Override
    public void valueChange(ValueChangeEvent event) {
        try {
            result = Helmet.search(searchField.getValue());
            updateChart();
            updateSearchNavi();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void updateSearchNavi() {
        if (result == null) {
            pageLabel.setValue("-");
            prevButton.setEnabled(false);
            nextButton.setEnabled(false);
        } else {
            pageLabel.setValue(String.format("Page %d of %d",
                    result.currentPage, result.getPageCount()));
            prevButton.setEnabled(result.currentPage > 0);
            nextButton.setEnabled(result.currentPage < result.getPageCount());
        }
    }

    @Override
    public String getDescription() {
        return "Shows an example how data provided by a rest api can be easily plotted with Vaadin Charts.";
    }
}
