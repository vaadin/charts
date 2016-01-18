package com.vaadin.addon.charts.examples.themes;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.themes.HighChartsDefaultTheme;

public class CustomTheme extends HighChartsDefaultTheme {


    public CustomTheme() {
        Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");

        getChart().setBorderWidth(5);
        getChart().setBorderColor(new SolidColor("#EBBA95"));

        getTooltip().setBorderWidth(5);
        getTooltip().setBorderColor(new SolidColor("#EBBA95"));

    }

}
