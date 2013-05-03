package com.vaadin.addon.charts.demoandtestapp.container;

import java.util.Calendar;
import java.util.Date;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Component;

@SkipFromDemo
public class ContainerSeriesWithSpline extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Simple Chart with ContainerSeries";
    }

    @Override
    protected Component getChart() {
        // Create container with two points
        IndexedContainer indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("Count", Integer.class, 0);
        indexedContainer.addContainerProperty("Date", Date.class, new Date());
        
        Calendar cal = Calendar.getInstance();
        cal.set(2013, 2, 22, 12, 00);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        
        Item item = indexedContainer.addItem("p1");
        item.getItemProperty("Date").setValue(cal.getTime());
        item.getItemProperty("Count").setValue(5);
        
        cal.add(Calendar.DATE, 1);
        item = indexedContainer.addItem("p2");
        item.getItemProperty("Date").setValue(cal.getTime());
        item.getItemProperty("Count").setValue(10);
       
        cal.add(Calendar.DATE, 1);
        item = indexedContainer.addItem("p3");
        item.getItemProperty("Date").setValue(cal.getTime());
        item.getItemProperty("Count").setValue(5);
       
        // Create chart config
        Configuration config = new Configuration();
        
        config.getxAxis().setType(AxisType.DATETIME);
                        
        // Wrap container in a container data series
        ContainerDataSeries cds = new ContainerDataSeries(indexedContainer);                            
        cds.setPlotOptions(new PlotOptionsSpline());
        cds.setYPropertyId("Count");
        cds.setXPropertyId("Date");            
                                        
        // Add points to series
        config.addSeries(cds); 
        
        // Create chart and render
        Chart chart = new Chart();              
        chart.setSizeFull();                                                            
        chart.drawChart(config);
                
        return chart;
    }
}
