package com.vaadin.addon.charts.examples.container;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartDataSeries;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.server.data.DataSource;
import com.vaadin.server.data.ListDataSource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;


public class VSevenSimpleChartWithContainerSeries extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Simple Chart with ContainerSeries";
    }

    private DataSource<Browser> browsers = new ListDataSource<>(getMockData());

    @Override
    protected Component getChart() {
        HorizontalLayout lo = new HorizontalLayout();
        Component table = createGrid();
        ChartDataSeries ds = createChartDS();
        Component chart = createChart(ds);

        table.setSizeFull();
        chart.setSizeFull();

        lo.setWidth("100%");
        lo.setHeight("450px");
        lo.addComponents(table);
        lo.addComponent(chart);
        return lo;
    }

     private Component createGrid() {

        Grid<Browser> grid = new Grid();
        grid.setDataSource(browsers);
        grid.addColumn("name", Browser::getName);
        grid.addColumn("y", browser ->Double.toString(browser.getShare().doubleValue()));
        grid.addColumn("color", browser -> browser.getColor().toString());
        grid.setCaption("Data from Vaadin Container");
        return grid;
    }

    @SuppressWarnings("unchecked")
    private ChartDataSeries createChartDS() {
        ChartDataSeries<Browser> ds = new ChartDataSeries(browsers);
        ds.addDataProvider("name", Browser::getName);
        ds.addDataProvider("y", Browser::getShare);
        ds.addDataProvider("color", Browser::getColor);
        ds.setName("Browser share");

        return ds;
    }

    public static Chart createChart(Series ds) {
        final Chart chart = new Chart();

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.PIE);
        configuration.getTitle().setText("Data from Vaadin Container");

        configuration.setSeries(ds);

        chart.drawChart(configuration);

        return chart;
    }

    private Collection<Browser> getMockData(){
        Collection<Browser> browsers= new ArrayList<>();
        String[] names = new String[] { "MSIE", "Firefox", "Chrome", "Safari",
                "Opera" };
        Number[] values = new Number[] { 55.11, 21.63, 11.94, 7.15, 2.14 };
        Color[] colors = getThemeColors();
        for (int i = 0; i < names.length; i++) {
            browsers.add(new Browser(names[i],values[i],colors[i]));

        }
        return  browsers;
    }

    private class Browser{
        private String name;
        private Number share;
        private Color color;
        public Browser(String name,Number share,Color color){
            this.name=name;
            this.share=share;
            this.color = color;
        }
        public String getName() {
            return name;
        }

        public Number getShare() {
            return share;
        }

        public Color getColor() {
            return color;
        }

    }
}
