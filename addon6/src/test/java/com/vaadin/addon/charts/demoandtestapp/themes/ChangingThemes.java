package com.vaadin.addon.charts.demoandtestapp.themes;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.area.PercentageArea;
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
public class ChangingThemes extends AbstractVaadinChartExample {

    protected VerticalLayout content;
    protected Chart chart;
    ChartOptions options = new ChartOptions();

    public ChangingThemes() {
        content = this;
    }

    @Override
    public String getDescription() {
        return "Changing themes";
    }

    @Override
    protected Component getChart() {
        options.setTheme(new HighChartsDefaultTheme());
        HorizontalLayout buttons = new HorizontalLayout();
        content.addComponent(buttons);

        Button vaadin = new Button("Vaadin", new ThemeChangeListener(
                VaadinTheme.class));
        vaadin.setDebugId("vaadin-button");
        buttons.addComponent(vaadin);

        Button grid = new Button("Grid", new ThemeChangeListener(
                GridTheme.class));
        grid.setDebugId("grid-button");
        buttons.addComponent(grid);

        Button skies = new Button("Skies", new ThemeChangeListener(
                SkiesTheme.class));
        skies.setDebugId("skies-button");
        buttons.addComponent(skies);

        Button gray = new Button("Gray", new ThemeChangeListener(
                GrayTheme.class));
        gray.setDebugId("gray-button");
        buttons.addComponent(gray);

        Button highchart = new Button("Highcharts", new ThemeChangeListener(
                HighChartsDefaultTheme.class));
        highchart.setDebugId("hs-button");
        buttons.addComponent(highchart);

        chart = PercentageArea.createNewChart();
        chart.setWidth("100%");
        chart.setHeight("400px");
        return chart;
    }

    class ThemeChangeListener implements Button.ClickListener {

        private Class<? extends Theme> themeclass;

        public ThemeChangeListener(Class<? extends Theme> themeclass) {
            this.themeclass = themeclass;
        }

        @Override
        public void buttonClick(ClickEvent event) {
            try {
                options.setTheme(themeclass.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

}
