package com.vaadin.addon.charts.demoandtestapp.lineandscatter;

import java.util.Date;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.MarkerStates;
import com.vaadin.addon.charts.model.MarkerSymbolEnum;
import com.vaadin.addon.charts.model.PlotBand;
import com.vaadin.addon.charts.model.PlotBandLabel;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.State;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class SplineWithPlotBandRemoveFunctionality extends
        AbstractVaadinChartExample {

    private static final int ONE_HOUR = 60 * 60 * 1000;
    private static final SolidColor TRANSPARENT = new SolidColor(0, 0, 0, 0);
    private static final SolidColor LIGHT_BLUE = new SolidColor(68, 170, 213,
            0.1);
    private static final SolidColor LIGHT_GRAY = new SolidColor("#606060");
    private PlotBand[] plotBands;

    @Override
    public String getDescription() {
        return "Spline With Plot Bands";
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Component getChart() {
        final Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");

        final Configuration configuration = new Configuration();
        configuration.getChart().setType(ChartType.SPLINE);

        configuration.getTitle().setText("Wind speed during two days");
        configuration
                .getSubTitle()
                .setText(
                        "October 6th and 7th 2009 at two locations in Vik i Sogn, Norway");

        configuration.getxAxis().setType(AxisType.DATETIME);

        Axis yAxis = configuration.getyAxis();
        yAxis.setTitle(new Title("Wind speed (m/s)"));
        yAxis.setMin(0);
        yAxis.setMinorGridLineWidth(0);
        yAxis.setGridLineWidth(0);

        // disable alternate grid color from Vaadin theme, disturbs
        // demonstrating plotbands
        yAxis.setAlternateGridColor(TRANSPARENT);

        plotBands = createPlotBands(yAxis);

        configuration
                .getTooltip()
                .setFormatter(
                        "Highcharts.dateFormat('%e. %b %Y, %H:00', this.x) +': '+ this.y +' m/s'");

        PlotOptionsSpline plotOptions = new PlotOptionsSpline();
        configuration.setPlotOptions(plotOptions);
        plotOptions.setMarker(new Marker(false));
        plotOptions.getMarker().setLineWidth(4);
        MarkerStates states = new MarkerStates(new State(true));
        states.getHover().setSymbol(MarkerSymbolEnum.CIRCLE);
        states.getHover().setRadius(5);
        states.getHover().setLineWidth(1);
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

        final Button removePlotBand = new Button("Remove PlotBands");
        removePlotBand.setDebugId("vaadin-button");
        removePlotBand.addListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                if (chart.getConfiguration().getyAxis().getPlotBands()
                        .isEmpty()) {
                    plotBands = createPlotBands(chart.getConfiguration()
                            .getyAxis());
                    removePlotBand.setCaption("Remove PlotBands");
                } else {
                    for (int i = 0; i < plotBands.length; i++) {
                        chart.getConfiguration().getyAxis()
                                .removePlotBand(plotBands[i]);
                    }

                    removePlotBand.setCaption("Restore PlotBands");
                }
                chart.drawChart(configuration);
            }
        });
        
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(removePlotBand);
        verticalLayout.addComponent(chart);
        
        return verticalLayout;
    }

    private PlotBand[] createPlotBands(Axis yAxis) {
        final PlotBand lightAir = new PlotBand(0.3, 1.5, LIGHT_BLUE);
        lightAir.setLabel(new PlotBandLabel("Light air"));
        lightAir.getLabel().setStyle(new Style());
        lightAir.getLabel().getStyle().setColor(LIGHT_GRAY);

        final PlotBand lightBreeze = new PlotBand(1.5, 3.3, TRANSPARENT);
        lightBreeze.setLabel(new PlotBandLabel("Light breeze"));
        lightBreeze.getLabel().setStyle(new Style());
        lightBreeze.getLabel().getStyle().setColor(LIGHT_GRAY);

        final PlotBand gentleBreeze = new PlotBand(3.3, 5.5, LIGHT_BLUE);
        gentleBreeze.setLabel(new PlotBandLabel("Gentle breeze"));
        gentleBreeze.getLabel().setStyle(new Style());
        gentleBreeze.getLabel().getStyle().setColor(LIGHT_GRAY);

        final PlotBand moderateBreeze = new PlotBand(5.5, 8, TRANSPARENT);
        moderateBreeze.setLabel(new PlotBandLabel("Moderate breeze"));
        moderateBreeze.getLabel().setStyle(new Style());
        moderateBreeze.getLabel().getStyle().setColor(LIGHT_GRAY);

        final PlotBand freshBreeze = new PlotBand(8, 11, LIGHT_BLUE);
        freshBreeze.setLabel(new PlotBandLabel("Fresh breeze"));
        freshBreeze.getLabel().setStyle(new Style());
        freshBreeze.getLabel().getStyle().setColor(LIGHT_GRAY);

        final PlotBand strongBreeze = new PlotBand(11, 14, TRANSPARENT);
        strongBreeze.setLabel(new PlotBandLabel("Strong breeze"));
        strongBreeze.getLabel().setStyle(new Style());
        strongBreeze.getLabel().getStyle().setColor(LIGHT_GRAY);

        final PlotBand highWind = new PlotBand(14, 15, LIGHT_BLUE);
        highWind.setLabel(new PlotBandLabel("High wind"));
        highWind.getLabel().setStyle(new Style());
        highWind.getLabel().getStyle().setColor(LIGHT_GRAY);

        yAxis.setPlotBands(lightAir, lightBreeze, gentleBreeze, moderateBreeze,
                freshBreeze, strongBreeze, highWind);

        return new PlotBand[] { lightAir, lightBreeze, gentleBreeze,
                moderateBreeze, freshBreeze, strongBreeze, highWind };
    }
}
