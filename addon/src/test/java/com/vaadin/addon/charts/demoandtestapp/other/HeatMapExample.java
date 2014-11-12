package com.vaadin.addon.charts.demoandtestapp.other;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.RangeSeries;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Component;

public class HeatMapExample extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Heat map";
    }

    @Override
    protected Component getChart() {

        Chart chart = new Chart();

        Configuration config = new Configuration();
        config.getChart().setType(ChartType.HEATMAP);
        config.getChart().setMarginTop(40);
        config.getChart().setMarginBottom(40);

        config.getTitle().setText("Sales per employee per weekday");

        config.getxAxis()
                .setCategories("Marta", "Mysia", "Misiek", "Maniek", "Miki",
                        "Guillermo", "Jonatan", "Zdzisław", "Antoni", "Zygmunt");
        config.getyAxis().setCategories("Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday");

        config.getColorAxis().setMin(0);
        config.getColorAxis().setMinColor(SolidColor.WHITE);
        config.getColorAxis().setMaxColor(SolidColor.CYAN);

        config.getLegend().setLayout(LayoutDirection.VERTICAL);
        config.getLegend().setHorizontalAlign(HorizontalAlign.RIGHT);
        config.getLegend().setMargin(0);
        config.getLegend().setVerticalAlign(VerticalAlign.TOP);
        config.getLegend().setY(25);
        config.getLegend().setSymbolHeight(320);

        RangeSeries rs = new RangeSeries("Sales per employee",
                this.getRawData());

        config.setSeries(rs);

        chart.drawChart(config);

        // System.err.println(config.toString());

        return chart;
    }

    /**
     * Raw data to the heatmap chart, as in highcharts.org demo.
     * 
     * @return Array of arrays of numbers.
     */
    private Number[][] getRawData() {
        return new Number[][] { { 0, 0, 10 }, { 0, 1, 19 }, { 0, 2, 8 },
                { 0, 3, 24 }, { 0, 4, 67 }, { 1, 0, 92 }, { 1, 1, 58 },
                { 1, 2, 78 }, { 1, 3, 117 }, { 1, 4, 48 }, { 2, 0, 35 },
                { 2, 1, 15 }, { 2, 2, 123 }, { 2, 3, 64 }, { 2, 4, 52 },
                { 3, 0, 72 }, { 3, 1, 132 }, { 3, 2, 114 }, { 3, 3, 19 },
                { 3, 4, 16 }, { 4, 0, 38 }, { 4, 1, 5 }, { 4, 2, 8 },
                { 4, 3, 117 }, { 4, 4, 115 }, { 5, 0, 88 }, { 5, 1, 32 },
                { 5, 2, 12 }, { 5, 3, 6 }, { 5, 4, 120 }, { 6, 0, 13 },
                { 6, 1, 44 }, { 6, 2, 88 }, { 6, 3, 98 }, { 6, 4, 96 },
                { 7, 0, 31 }, { 7, 1, 1 }, { 7, 2, 82 }, { 7, 3, 32 },
                { 7, 4, 30 }, { 8, 0, 85 }, { 8, 1, 97 }, { 8, 2, 123 },
                { 8, 3, 64 }, { 8, 4, 84 }, { 9, 0, 47 }, { 9, 1, 114 },
                { 9, 2, 31 }, { 9, 3, 48 }, { 9, 4, 91 } };
    }

}
