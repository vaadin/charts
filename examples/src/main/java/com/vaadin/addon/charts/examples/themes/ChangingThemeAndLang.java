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
package com.vaadin.addon.charts.examples.themes;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.examples.area.PercentageArea;
import com.vaadin.addon.charts.model.Lang;
import com.vaadin.addon.charts.model.style.Theme;
import com.vaadin.addon.charts.themes.GrayTheme;
import com.vaadin.addon.charts.themes.GridTheme;
import com.vaadin.addon.charts.themes.HighChartsDefaultTheme;
import com.vaadin.addon.charts.themes.SkiesTheme;
import com.vaadin.addon.charts.themes.VaadinTheme;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * Test for changing Themes after charts are drawn
 */
@SuppressWarnings("serial")
@SkipFromDemo
public class ChangingThemeAndLang extends AbstractVaadinChartExample {

    protected VerticalLayout content;
    protected Chart chart;

    public ChangingThemeAndLang() {
        content = this;
    }

    @Override
    public String getDescription() {
        return "Changing themes";
    }

    @Override
    protected Component getChart() {
        ChartOptions.get().setTheme(new HighChartsDefaultTheme());
        HorizontalLayout buttons = new HorizontalLayout();
        content.addComponent(buttons);

        Button vaadin = new Button("Vaadin", new ThemeChangeListener(
                VaadinTheme.class));
        vaadin.setId("vaadin-button");
        buttons.addComponent(vaadin);

        Button grid = new Button("Grid", new ThemeChangeListener(
                GridTheme.class));
        grid.setId("grid-button");
        buttons.addComponent(grid);

        Button skies = new Button("Skies", new ThemeChangeListener(
                SkiesTheme.class));
        skies.setId("skies-button");
        buttons.addComponent(skies);

        Button gray = new Button("Gray", new ThemeChangeListener(
                GrayTheme.class));
        gray.setId("gray-button");
        buttons.addComponent(gray);

        Button highchart = new Button("Highcharts", new ThemeChangeListener(
                HighChartsDefaultTheme.class));
        highchart.setId("hs-button");
        buttons.addComponent(highchart);

        chart = PercentageArea.createNewChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        return chart;
    }

    static class ThemeChangeListener implements Button.ClickListener {
        static int i = 0;
        Lang a = new Lang();
        Lang b = new Lang();

        private Class<? extends Theme> themeclass;

        public ThemeChangeListener(Class<? extends Theme> themeclass) {
            this.themeclass = themeclass;
            a.setDecimalPoint("(POINT)");
            b.setDecimalPoint("(PISTE)");
        }

        @Override
        public void buttonClick(ClickEvent event) {
            try {
                ChartOptions.get().setTheme(themeclass.newInstance());
                i++;
                Lang l = b;
                if (i % 2 == 0) {
                    l = a;
                }
                ChartOptions.get().setLang(l);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

}
