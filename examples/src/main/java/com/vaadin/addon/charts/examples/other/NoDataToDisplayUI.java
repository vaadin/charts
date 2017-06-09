package com.vaadin.addon.charts.examples.other;


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
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

@SkipFromDemo
public class NoDataToDisplayUI extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Test with No data displayed.";
    }

    @Override
    protected Component getChart() {
        final VerticalLayout layout = new VerticalLayout();
        Chart chart = new Chart(ChartType.COLUMN);
        Configuration conf = chart.getConfiguration();

        chart.drawChart(conf);
        layout.addComponents(chart);

        return layout;
    }

}
