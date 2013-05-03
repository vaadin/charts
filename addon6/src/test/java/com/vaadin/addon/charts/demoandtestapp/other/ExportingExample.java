package com.vaadin.addon.charts.demoandtestapp.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.columnandbar.DualAxesLineAndColumn;
import com.vaadin.addon.charts.model.ExportButton;
import com.vaadin.addon.charts.model.Exporting;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class ExportingExample extends DualAxesLineAndColumn {

    @Override
    public String getDescription() {
        return "An example demonstrating how exporting feature is used.";
    }

    @Override
    protected Component getChart() {
        Chart chart = (Chart) super.getChart();
        // Enabling exporting adds a button to UI via users can download the
        // chart e.g. for printing
        Exporting exporting = new Exporting(true);
        
        // One can customize the filename
        exporting.setFilename("mychartfile.pdf");

        // and choose whether to post raster images to exporting server
        exporting.setEnableImages(true);
        
        // Exporting is by default done on highcharts public servers, but you
        // can also use your own server
        // exporting.setUrl("http://my.own.server.com");
        
        // Some options can be set per button, here we disable the print button.
        ExportButton printButton = new ExportButton();
        printButton.setEnabled(false);
        exporting.setPrintButton(printButton);
        
        // Actually use these settings in the chart
        chart.getConfiguration().setExporting(exporting);

        // Simpler boolean API can also be used to just toggle the service
        // on/off
        // chart.getConfiguration().setExporting(true);

        return chart;

    }
}
