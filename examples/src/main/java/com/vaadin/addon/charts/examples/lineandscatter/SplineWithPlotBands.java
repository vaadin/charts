package com.vaadin.addon.charts.examples.lineandscatter;

import java.util.Date;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Hover;
import com.vaadin.addon.charts.model.Label;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.MarkerSymbolEnum;
import com.vaadin.addon.charts.model.PlotBand;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.States;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.ui.Component;

public class SplineWithPlotBands extends AbstractVaadinChartExample {

    private static final int ONE_HOUR = 60 * 60 * 1000;
    private static final SolidColor TRANSPARENT = new SolidColor(0, 0, 0, 0);
    private static final SolidColor LIGHT_BLUE = new SolidColor(68, 170, 213,
            0.1);
    private static final SolidColor LIGHT_GRAY = new SolidColor("#606060");

    @Override
    public String getDescription() {
        return "Spline With Plot Bands";
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Component getChart() {
        Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");

        Configuration configuration = chart.getConfiguration();
        // FIXME remove toString() once enums are used in model (CHARTS-159)
        configuration.getChart().setType(ChartType.SPLINE.toString());

        configuration.getTitle().setText("Wind speed during two days");
        configuration
                .getSubTitle()
                .setText(
                        "October 6th and 7th 2009 at two locations in Vik i Sogn, Norway");

        configuration.getxAxis().setType(AxisType.DATETIME.toString());

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new Title("Wind speed (m/s)"));
        yAxis.setMin(0);
        yAxis.setMinorGridLineWidth(0);
        yAxis.setGridLineWidth(0);

        // disable alternate grid color from Vaadin theme, disturbs
        // demonstrating plotbands
        yAxis.setAlternateGridColor(TRANSPARENT);

        Style style = new Style();
        style.setColor(LIGHT_GRAY);

        final PlotBand lightAir = new PlotBand();
        lightAir.setFrom(0.3);
        lightAir.setTo(1.5);
        lightAir.setColor(LIGHT_BLUE);
        lightAir.setLabel(new Label("Light air"));
        lightAir.getLabel().setStyle(style);

        final PlotBand lightBreeze = new PlotBand();
        lightBreeze.setFrom(1.5);
        lightBreeze.setTo(3.3);
        lightBreeze.setColor(TRANSPARENT);
        lightBreeze.setLabel(new Label("Light breeze"));
        lightBreeze.getLabel().setStyle(style);

        final PlotBand gentleBreeze = new PlotBand();
        gentleBreeze.setFrom(3.3);
        gentleBreeze.setTo(5.5);
        gentleBreeze.setColor(LIGHT_BLUE);
        gentleBreeze.setLabel(new Label("Gentle breeze"));
        gentleBreeze.getLabel().setStyle(style);

        final PlotBand moderateBreeze = new PlotBand();
        moderateBreeze.setFrom(5.5);
        moderateBreeze.setTo(8);
        moderateBreeze.setColor(TRANSPARENT);
        moderateBreeze.setLabel(new Label("Moderate breeze"));
        moderateBreeze.getLabel().setStyle(style);

        final PlotBand freshBreeze = new PlotBand();
        freshBreeze.setFrom(8);
        freshBreeze.setTo(11);
        freshBreeze.setColor(LIGHT_BLUE);
        freshBreeze.setLabel(new Label("Fresh breeze"));
        freshBreeze.getLabel().setStyle(style);

        final PlotBand strongBreeze = new PlotBand();
        strongBreeze.setFrom(11);
        strongBreeze.setTo(14);
        strongBreeze.setColor(TRANSPARENT);
        strongBreeze.setLabel(new Label("Strong breeze"));
        strongBreeze.getLabel().setStyle(style);

        final PlotBand highWind = new PlotBand();
        highWind.setFrom(14);
        highWind.setTo(15);
        highWind.setColor(LIGHT_BLUE);
        highWind.setLabel(new Label("High wind"));
        highWind.getLabel().setStyle(style);

        yAxis.setPlotBands(lightAir, lightBreeze,gentleBreeze,
                moderateBreeze, freshBreeze, strongBreeze,
                highWind);

        // FIXME missing generated API
        // configuration
        // .getTooltip()
        // .setFormatter(
        // "Highcharts.dateFormat('%e. %b %Y, %H:00', this.x) +': '+ this.y +' m/s'");

        PlotOptionsSpline plotOptions = new PlotOptionsSpline();
        configuration.setPlotOptions(plotOptions);
        plotOptions.setMarker(new Marker(false));
        plotOptions.getMarker().setLineWidth(4);
        plotOptions.getMarker().setSymbol(MarkerSymbolEnum.CIRCLE.toString());
        States states = new States();
        Hover hover = new Hover(true);
        hover.setRadius(5);
        hover.setLineWidth(1);
        states.setHover(hover);
        plotOptions.getMarker().setStates(states);

        plotOptions.setPointInterval(ONE_HOUR);
        plotOptions.setPointStart(new Date(2009 - 1900, 9 - 1, 6).getTime());

        ListSeries ls = new ListSeries();
        ls.setName("Hestavollane");
        ls.setData(4.3, 5.1, 4.3, 5.2, 5.4, 4.7, 3.5, 4.1, 5.6, 7.4, 6.9, 7.1,
                7.9, 7.9, 7.5, 6.7, 7.7, 7.7, 7.4, 7.0, 7.1, 5.8, 5.9, 7.4,
                8.2, 8.5, 9.4, 8.1, 10.9, 10.4, 10.9, 12.4, 12.1, 9.5, 7.5,
                7.1, 7.5, 8.1, 6.8, 3.4, 2.1, 1.9, 2.8, 2.9, 1.3, 4.4, 4.2,
                3.0, 3.0);
        configuration.addSeries(ls);

        ls = new ListSeries();
        ls.setName("Voll");
        ls.setData(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.3, 0.0,
                0.0, 0.4, 0.0, 0.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.6, 1.2, 1.7, 0.7, 2.9, 4.1, 2.6, 3.7, 3.9, 1.7, 2.3,
                3.0, 3.3, 4.8, 5.0, 4.8, 5.0, 3.2, 2.0, 0.9, 0.4, 0.3, 0.5, 0.4);
        configuration.addSeries(ls);

        chart.drawChart(configuration);
        return chart;
    }
}
