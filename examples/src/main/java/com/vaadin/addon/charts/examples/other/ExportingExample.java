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
package com.vaadin.addon.charts.examples.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.columnandbar.DualAxesLineAndColumn;
import com.vaadin.addon.charts.model.Exporting;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ExportingExample extends DualAxesLineAndColumn {

    @Override
    public String getDescription() {
        return "This example demonstrates the exporting feature. "
                + "<div style='font-size:smaller'>Note that using "
                + "this feature your charts may visit "
                + "Highsoft's export server, unless you customize the feature "
                + "to use your own export server. In most cases it's a better "
                + "idea to use <tt>com.vaadin.addon.charts.util.SVGGenerator</tt> in "
                + "that it works completely on your own servers.</div>";
    }

    @Override
    protected Component getChart() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSpacing(true);
        verticalLayout.addComponent(new Label(getDescription(),
                ContentMode.HTML));
        Chart chart = (Chart) super.getChart();
        // Enabling exporting adds a button to UI via users can download the
        // chart e.g. for printing
        Exporting exporting = new Exporting(true);

        // One can customize the filename
        exporting.setFilename("mychartfile");

        // Exporting is by default done on highcharts public servers, but you
        // can also use your own server
        // exporting.setUrl("http://my.own.server.com");

        // Actually use these settings in the chart
        chart.getConfiguration().setExporting(exporting);

        // Simpler boolean API can also be used to just toggle the service
        // on/off
        // chart.getConfiguration().setExporting(true);

        verticalLayout.addComponent(chart);
        return verticalLayout;
    }
}
