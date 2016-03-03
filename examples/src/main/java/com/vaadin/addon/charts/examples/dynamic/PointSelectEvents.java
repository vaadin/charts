package com.vaadin.addon.charts.examples.dynamic;

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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.PointSelectEvent;
import com.vaadin.addon.charts.PointSelectListener;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DashStyle;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.FlagItem;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.PlotOptionsFlags;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PointSelectEvents extends AbstractVaadinChartExample {

    private Chart chart;
    private VerticalLayout historyLayout;
    @Override
    public String getDescription() {
        return "Test server side events";
    }

    @Override
    protected Component getChart() {
        chart = new Chart();
        historyLayout=new VerticalLayout();
        chart.setId("chart");
        chart.setWidth("100%");

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.LINE);

        PlotOptionsSeries opt = new PlotOptionsSeries();

        opt.setAnimation(false);
        opt.setAllowPointSelect(false);
        configuration.setPlotOptions(opt);
        final DataSeries series1 = createDataSeries(20);
        series1.setId("dataseries");
        series1.get(0).setY(105);
        series1.get(3).setY(95);



        PlotLine plotLine=new PlotLine();
        plotLine.setColor(SolidColor.RED);
        plotLine.setDashStyle(DashStyle.DASH);
        plotLine.setWidth(2);
        plotLine.setValue(40);
        configuration.getxAxis().addPlotLine(plotLine);

        DataSeries flagsOnSeries = new DataSeries();
        flagsOnSeries.setName("");
        PlotOptionsFlags plotOptionsFlags = new PlotOptionsFlags();
        plotOptionsFlags.setOnSeries("dataseries");
        flagsOnSeries.setPlotOptions(plotOptionsFlags);
        flagsOnSeries.add(new FlagItem(40,"Your text"));

        configuration.setSeries(series1,flagsOnSeries);
        chart.drawChart(configuration);

        chart.addPointSelectListener(new PointSelectListener() {
            @Override
            public void onSelect(PointSelectEvent event) {
                logEvent(event);
            }
        });

        historyLayout.setCaption("History");
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.addComponent(chart);
        layout.addComponent(historyLayout);
        return layout;
    }




    private DataSeries createDataSeries(Number yvalue) {
        final DataSeries series = new DataSeries();
        series.add(new DataSeriesItem(20, yvalue));
        series.add(new DataSeriesItem(40, yvalue.intValue() + 10));
        series.add(new DataSeriesItem(60, yvalue.intValue() - 15));
        series.add(new DataSeriesItem(80, yvalue));
        return series;
    }

    private void logEvent(Event event) {
        String name = event.getClass().getSimpleName();
        String details = createEventString(event);
        Label history = new Label(name + ": " + details + "\n");
        historyLayout.addComponentAsFirst(history);
    }

    private String createEventString(Event event) {
        ObjectMapper mapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setVisibility(PropertyAccessor.ALL,
                        JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD,
                        JsonAutoDetect.Visibility.ANY);

        try {
            return mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
