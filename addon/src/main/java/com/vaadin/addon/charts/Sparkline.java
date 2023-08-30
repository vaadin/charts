/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts;

import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;

/**
 * Extension for {@link Chart} which generates the data as a simple Sparkline.
 * The Sparkline is always a {@link ChartType#LINE}.
 * <p/>
 * Note that the configuration is done only at construction time. Further
 * modifications to the object can break the styling.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Sparkline">Sparkline in
 *      Wikipedia</a>
 */
public class Sparkline extends Chart {

    public Sparkline(Number... values) {
        this(new ListSeries(values));
    }

    public Sparkline(int width, int height, Number... values) {
        this(width, height, new ListSeries(values));
    }

    public Sparkline(int width, int height, Series series) {
        this(series);
        setWidth(width, Unit.PIXELS);
        setHeight(height, Unit.PIXELS);
    }

    public Sparkline(Series series) {
        getConfiguration().addSeries(series);

        configureAsSparkline();
    }

    protected void configureAsSparkline() {
        getConfiguration().setTitle("");
        getConfiguration().setLegend(new Legend(false));
        getConfiguration().getChart().setBackgroundColor(
                new SolidColor(0, 0, 0, 0));
        getConfiguration().getChart().setMargin(0);
        Series series = getConfiguration().getSeries().get(0);

        PlotOptionsLine options = new PlotOptionsLine();
        options.setEnableMouseTracking(false);
        options.setMarker(new Marker(false));
        options.setShowInLegend(false);
        options.setAnimation(false);
        options.setShadow(false);

        series.setPlotOptions(options);

        configureXAxis(getConfiguration().getxAxis());
        configureYAxis(getConfiguration().getyAxis());
    }

    protected void configureXAxis(XAxis axis) {
        axis.setLineWidth(0);
        axis.setMinorGridLineWidth(0);
        axis.setMinorTickLength(0);
        axis.setMinorTickWidth(0);
        axis.setGridLineWidth(0);

        axis.setMaxPadding(0.1);
        axis.setMinPadding(0.1);

        axis.setLabels(new Labels(false));
        axis.setLineColor(new SolidColor(0, 0, 0, 0));

        axis.setTitle("");
        axis.setStartOnTick(false);
        axis.setEndOnTick(false);
    }

    protected void configureYAxis(YAxis axis) {
        axis.setLineWidth(0);
        axis.setMinorGridLineWidth(0);
        axis.setMinorTickLength(0);
        axis.setMinorTickWidth(0);
        axis.setGridLineWidth(0);

        axis.setMaxPadding(0.1);
        axis.setMinPadding(0.1);

        axis.setLabels(new Labels(false));
        axis.setLineColor(new SolidColor(0, 0, 0, 0));

        axis.setTitle("");
        axis.setStartOnTick(false);
        axis.setEndOnTick(false);
    }
}
