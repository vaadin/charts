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

import com.vaadin.addon.charts.model.AbstractPlotOptions;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.style.AxisStyle;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.FontWeight;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.addon.charts.model.style.Theme;
import com.vaadin.addon.charts.model.style.TickIntervalStyle;

/**
 * The default theme for Vaadin Charts
 */
@SuppressWarnings("serial")
public class VaadinLightTheme extends Theme {

    protected static final int BORDER_RADIUS = 4;
    protected final static SolidColor COLOR1 = new SolidColor(236, 100, 100);
    protected final static SolidColor COLOR2 = new SolidColor(152, 223, 88);
    protected final static SolidColor COLOR3 = new SolidColor(48, 144, 240);
    protected final static SolidColor COLOR4 = new SolidColor(249, 221, 81);
    protected final static SolidColor COLOR5 = new SolidColor(36, 220, 212);
    protected final static SolidColor COLOR6 = new SolidColor(236, 100, 165);
    protected final static SolidColor COLOR7 = new SolidColor(104, 92, 176);
    protected final static SolidColor COLOR8 = new SolidColor(255, 125, 66);

    protected final static SolidColor TITLE_COLOR = new SolidColor(95, 124, 225);
    protected final static SolidColor TOOLTIP_TEXT_COLOR = new SolidColor(51,
            51, 51);
    protected final static SolidColor TOOLTIP_BACKGROUND_COLOR = new SolidColor(
            255, 255, 255);
    protected final static SolidColor SUBTITLE_COLOR = new SolidColor(155, 155,
            155);
    protected final static SolidColor LINE_COLOR = new SolidColor(223, 223, 223);
    protected final static SolidColor TEXT_COLOR = new SolidColor(155, 155, 155);
    protected final static SolidColor GRID_COLOR = new SolidColor(250, 250, 250);
    protected final static SolidColor LABEL_COLOR = new SolidColor(155, 155,
            155);

    protected final static SolidColor BGCOLOR = new SolidColor(255, 255, 255);
    protected final static SolidColor BGCOLOR_LIGHT_GRAY = new SolidColor(0, 0,
            0, 0.02);
    protected final static SolidColor TRANSPARENT_COLOR = new SolidColor(255,
            255, 255, 0.0);

    protected final static String DEFAULT_FONT_FAMILIES = "Open Sans";
    protected static final Color LEGEND_TEXT_COLOR = new SolidColor(155, 155,
            155);

    public VaadinLightTheme() {
        setColors(COLOR1, COLOR2, COLOR3, COLOR4, COLOR5, COLOR6, COLOR7,
                COLOR8);
        Style style = new Style();
        style.setFontFamily(DEFAULT_FONT_FAMILIES);
        style.setFontSize("12px");
        getChart().setStyle(style);

        getTitle().setColor(TITLE_COLOR);
        getTitle().setFontSize("18px");
        getTitle().setFontWeight(FontWeight.BOLD);

        getSubtitle().setColor(SUBTITLE_COLOR);
        getSubtitle().setFontWeight(FontWeight.NORMAL);

        getChart().setClassName("vaadin-chart");
        getChart().setPlotBackgroundImage("");
        getChart().setBackgroundColor(BGCOLOR);
        getChart().setPlotBackgroundColor(TRANSPARENT_COLOR);
        getChart().setPlotBorderWidth(0);
        getChart().setBorderRadius(0);
        getChart().setPlotShadow(false);

        setAxisDefaults(getxAxis());

        setAxisDefaults(getyAxis());

        getTooltip().setBackgroundColor(TOOLTIP_BACKGROUND_COLOR);
        getTooltip().setBorderWidth(0);
        getTooltip().setBorderRadius(BORDER_RADIUS);
        getTooltip().getStyle().setColor(TOOLTIP_TEXT_COLOR);

        getLegend().getItemStyle().setColor(LEGEND_TEXT_COLOR);
        Style itemHoverStyle = new Style();
        itemHoverStyle.setColor(new SolidColor(0, 0, 0));
        getLegend().setItemHoverStyle(itemHoverStyle);
        getLegend().setItemHiddenStyle(new Style());
        getLegend().getItemHiddenStyle()
                .setColor(new SolidColor(128, 128, 128));
        getLegend().setBorderRadius(BORDER_RADIUS);
        getLegend().setBorderColor(LINE_COLOR);
        getLegend().setBackgroundColor(BGCOLOR_LIGHT_GRAY);

        getLabels().setColor(TEXT_COLOR);

        getCredits().setStyle(new Style());
        getCredits().getStyle().setFontSize("10px");
        getCredits().getStyle().setColor(new SolidColor(128, 128, 128));
        getCredits().setText("Vaadin Charts");
        getCredits().setHref("https://vaadin.com/add-ons/charts");

        getyAxis().setMinorTickInterval(TickIntervalStyle.NONE);
        getyAxis().setAlternateGridColor(BGCOLOR_LIGHT_GRAY);
        getyAxis().setGridLineColor(LINE_COLOR);

        getxAxis().setGridLineWidth(0);

        // Shadows on by default, off in range stuff
        getPlotOptions().getArearange().setShadow(false);
        getPlotOptions().getAreasplinerange().setShadow(false);
        getPlotOptions().getLine().setShadow(true);
        getPlotOptions().getSpline().setShadow(true);
        getPlotOptions().getBar().setShadow(false);
        getPlotOptions().getColumn().setShadow(true);
        getPlotOptions().getArea().setShadow(true);
        getPlotOptions().getPie().setShadow(true);

        getPlotOptions().getPie().setBorderWidth(0);
        getPlotOptions().getBar().setBorderWidth(0);
        getPlotOptions().getBar().setPointPadding(0);
        getPlotOptions().getPyramid().setBorderWidth(0);

        setDataLabelsDefaults(getPlotOptions().getPyramid());
        setDataLabelsDefaults(getPlotOptions().getPie());
    }

    protected void setDataLabelsDefaults(AbstractPlotOptions plotOptions) {
        Labels labels = new Labels();
        labels.setColor(TEXT_COLOR);
        labels.getStyle().setFontFamily(DEFAULT_FONT_FAMILIES);
        labels.getStyle().setFontSize("14px");
        plotOptions.setDataLabels(labels);
    }

    protected void setAxisDefaults(AxisStyle style) {
        style.setGridLineColor(GRID_COLOR);
        style.setLineColor(GRID_COLOR);
        style.setLineWidth(0);
        style.setTickWidth(0);
        style.setAlternateGridColor(new SolidColor(255, 255, 255, 0.0));

        style.getTitle().setColor(TEXT_COLOR);
        style.getTitle().setFontWeight(FontWeight.BOLD);

        style.getSubtitle().setColor(SUBTITLE_COLOR);
        style.getSubtitle().setFontSize("10px");
        style.getSubtitle().setFontWeight(FontWeight.NORMAL);

        style.getLabels().setFontWeight(FontWeight.NORMAL);
        style.getLabels().setColor(LABEL_COLOR);
        style.getLabels().setFontSize("12px");
    }

}
