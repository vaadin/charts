package com.vaadin.addon.charts.demoandtestapp.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsPyramid;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class PyramidChartExample extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
	return "Pyramid chart example";
    }

    @Override
    protected Component getChart() {
	DataSeries dataSeries = new DataSeries("Unique users");
	dataSeries.add(new DataSeriesItem("Website visits", 15654));
	dataSeries.add(new DataSeriesItem("Downloads", 4064));
	dataSeries.add(new DataSeriesItem("Requested price list", 1987));
	dataSeries.add(new DataSeriesItem("Invoice sent", 976));
	dataSeries.add(new DataSeriesItem("Finalized", 846));

	Chart chart = new Chart();

	Configuration conf = chart.getConfiguration();

	conf.setTitle("Sales pyramid");
	conf.getLegend().setEnabled(false);

	PlotOptionsPyramid options = new PlotOptionsPyramid();

	options.setWidthPercentage(70); // With default (90%), long labels in
					// this example may be cut
	// options.setWidth(400); // in pixels

	Labels dataLabels = new Labels();
	dataLabels.setFormat("<b>{point.name}</b> ({point.y:,.0f})");
	dataLabels.setSoftConnector(false);
	dataLabels.setColor(new SolidColor("black"));
	options.setDataLabels(dataLabels);

	dataSeries.setPlotOptions(options);
	conf.addSeries(dataSeries);

	return chart;
    }

}
