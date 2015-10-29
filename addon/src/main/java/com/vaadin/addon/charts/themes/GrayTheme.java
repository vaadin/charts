package com.vaadin.addon.charts.themes;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

import com.vaadin.addon.charts.model.style.FontWeight;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;

/**
 * A theme based on the Gray theme on the HighCharts demo page
 */
@SuppressWarnings("serial")
public class GrayTheme extends HighChartsDefaultTheme {

    public final static String FONT_FAMILIES = "Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif";

    public GrayTheme() {
        setColors(new SolidColor("#DDDF0D"), new SolidColor("#7798BF"),
                new SolidColor("#55BF3B"), new SolidColor("#DF5353"),
                new SolidColor("#aaeeee"), new SolidColor("#ff0066"),
                new SolidColor("#eeaaee"), new SolidColor("#55BF3B"),
                new SolidColor("#DF5353"), new SolidColor("#7798BF"),
                new SolidColor("#aaeeee"));

        Style style = new Style();
        style.setFontFamily(FONT_FAMILIES);
        style.setFontSize("12px");
        getChart().setStyle(style);

        GradientColor bgColor = GradientColor.createLinear(0, 0, 0, 400);
        bgColor.addColorStop(0, new SolidColor(96, 96, 96));
        bgColor.addColorStop(1, new SolidColor(16, 16, 16));
        getChart().setBackgroundColor(bgColor);
        getChart().setBorderWidth(0);
        getChart().setBorderRadius(15);
        // No need to set plotbackgroundcolor to null
        getChart().setPlotShadow(false);
        getChart().setPlotBorderWidth(0);

        getTitle().setColor(new SolidColor("#FFF"));
        getTitle().setFontSize("16px");

        getSubtitle().setColor(new SolidColor("#DDD"));

        getxAxis().setGridLineWidth(0);
        getxAxis().setLineColor(new SolidColor("#999"));
        getxAxis().setTickColor(new SolidColor("#999"));
        getxAxis().getLabels().setColor(new SolidColor("#999"));
        getxAxis().getLabels().setFontWeight(FontWeight.BOLD);

        getxAxis().getTitle().setColor(new SolidColor("#AAA"));
        getxAxis().getTitle().setFontWeight(FontWeight.BOLD);

        // No need to set alternative grid color to null
        // No need to set minor tick interval to null
        getyAxis().setGridLineColor(new SolidColor(255, 255, 255, .1));
        getyAxis().setLineWidth(0);
        getyAxis().setTickWidth(0);
        getyAxis().getLabels().setColor(new SolidColor("#999"));
        getyAxis().getLabels().setFontWeight(FontWeight.BOLD);
        getyAxis().getTitle().setColor(new SolidColor("#AAA"));
        getyAxis().getTitle().setFontWeight(FontWeight.BOLD);

        getLegend().setItemStyle(new Style());
        getLegend().getItemStyle().setColor(new SolidColor("#CCC"));

        getLegend().getItemHoverStyle().setColor(new SolidColor("#FFF"));

        getLegend().getItemHiddenStyle().setColor(new SolidColor("#333"));

        getLabels().setColor(new SolidColor("#CCC"));

        GradientColor tooltipBgColor = GradientColor.createLinear(0, 0, 0, 50);
        tooltipBgColor.addColorStop(0, new SolidColor(96, 96, 96, .8));
        tooltipBgColor.addColorStop(1, new SolidColor(16, 16, 16, .8));
        getTooltip().setBackgroundColor(tooltipBgColor);
        getTooltip().setBorderWidth(0);
        getTooltip().getStyle().setColor(new SolidColor("#FFF"));

        /* Shadows on by default, off in range stuff */
        getPlotOptions().getArearange().setShadow(false);
        getPlotOptions().getAreasplinerange().setShadow(false);

        getPlotOptions().getLine().setShadow(true);
        getPlotOptions().getSpline().setShadow(true);
        getPlotOptions().getBar().setShadow(true);
        getPlotOptions().getColumn().setShadow(true);
        getPlotOptions().getArea().setShadow(true);
        getPlotOptions().getPie().setShadow(true);

        // TODO: Add missing parts to theme
        // URL: http://www.highcharts.com/demo/bar-negative-stack/gray

    }

}
