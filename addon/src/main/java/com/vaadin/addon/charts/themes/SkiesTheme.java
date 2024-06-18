/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.themes;

import com.vaadin.addon.charts.model.style.FontWeight;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;

@SuppressWarnings("serial")
public class SkiesTheme extends HighChartsDefaultTheme {

    public static final String FONT_FAMILIES = "Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif";

    public SkiesTheme() {
        setColors(new SolidColor("#514F78"), new SolidColor("#42A07B"),
                new SolidColor("#9B5E4A"), new SolidColor("#72727F"),
                new SolidColor("#1F949A"), new SolidColor("#82914E"),
                new SolidColor("#86777F"), new SolidColor("#42A07B"));

        Style style = new Style();
        style.setFontFamily(FONT_FAMILIES);
        style.setFontSize("12px");
        getChart().setStyle(style);

        getChart().setClassName("skies");
        getChart().setBorderWidth(0);
        getChart().setPlotShadow(true);
        getChart().setPlotBackgroundImage(
                "http://www.highcharts.com/demo/gfx/skies.jpg");
        GradientColor bgColor = GradientColor.createLinear(0, 0, 0, 400);
        bgColor.addColorStop(0, new SolidColor(255, 255, 255, 1));
        bgColor.addColorStop(1, new SolidColor(255, 255, 255, 0));
        getChart().setBackgroundColor(bgColor);
        getChart().setPlotBorderWidth(1);

        getTitle().setColor(new SolidColor("#3E576F"));
        getTitle().setFontSize("16px");
        getTitle().setFontWeight(FontWeight.NORMAL);

        getSubtitle().setColor(new SolidColor("#6D869F"));
        getSubtitle().setFontWeight(FontWeight.NORMAL);

        getxAxis().setGridLineWidth(0);
        getxAxis().setLineColor(new SolidColor("#C0D0E0"));
        getxAxis().setTickColor(new SolidColor("#C0D0E0"));
        getxAxis().getLabels().setColor(new SolidColor("#666"));
        getxAxis().getLabels().setFontWeight(FontWeight.BOLD);
        getxAxis().getTitle().setColor(new SolidColor("#666"));

        getyAxis().setAlternateGridColor(new SolidColor(255, 255, 255, .5));
        getyAxis().setLineColor(new SolidColor("#C0D0E0"));
        getyAxis().setTickColor(new SolidColor("#C0D0E0"));
        getyAxis().setTickWidth(1);
        getyAxis().getLabels().setColor(new SolidColor("#666"));
        getyAxis().getLabels().setFontWeight(FontWeight.BOLD);
        getyAxis().getTitle().setColor(new SolidColor("#666"));

        getLegend().setItemStyle(new Style());
        getLegend().getItemStyle().setColor(new SolidColor("#3E576F"));

        getLegend().getItemHoverStyle().setColor(new SolidColor("black"));

        getLegend().getItemHiddenStyle().setColor(new SolidColor("silver"));

        getLabels().setColor(new SolidColor("#3E576F"));

        /* Shadows on by default, off in range stuff */
        getPlotOptions().getArearange().setShadow(false);
        getPlotOptions().getAreasplinerange().setShadow(false);

        getPlotOptions().getLine().setShadow(true);
        getPlotOptions().getSpline().setShadow(true);
        getPlotOptions().getBar().setShadow(true);
        getPlotOptions().getColumn().setShadow(true);
        getPlotOptions().getArea().setShadow(true);
        getPlotOptions().getPie().setShadow(true);

    }
}
