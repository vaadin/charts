package com.vaadin.addon.charts.themes;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

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
public class VaadinTheme extends Theme {

    protected static final int BORDER_RADIUS = 2;
    protected final static SolidColor COLOR1 = new SolidColor(74, 112, 147);
    protected final static SolidColor COLOR2 = new SolidColor(73, 207, 229);
    protected final static SolidColor COLOR3 = new SolidColor(255, 188, 65);
    protected final static SolidColor COLOR4 = new SolidColor(194, 71, 88);
    protected final static SolidColor COLOR5 = new SolidColor(119, 170, 84);
    protected final static SolidColor COLOR6 = new SolidColor(112, 113, 200);
    protected final static SolidColor COLOR7 = new SolidColor(197, 143, 186);
    protected final static SolidColor COLOR8 = new SolidColor(181, 185, 198);

    protected final static SolidColor TITLE_COLOR = new SolidColor(68, 105, 139);
    protected final static SolidColor SUBTITLE_COLOR = new SolidColor(151, 151,
            151);
    protected final static SolidColor LINE_COLOR = new SolidColor(229, 229, 229);
    protected final static SolidColor TEXT_COLOR = new SolidColor(151, 151, 151);
    protected final static SolidColor GRID_COLOR = new SolidColor(151, 151, 151);
    protected final static SolidColor LABEL_COLOR = new SolidColor(151, 151,
            151);

    protected final static SolidColor BGCOLOR = new SolidColor(255, 255, 255);
    protected final static SolidColor BGCOLOR_LIGHT_GRAY = new SolidColor(0, 0,
            0, 0.02);
    protected final static SolidColor TRANSPARENT_COLOR = new SolidColor(255,
            255, 255, 0.0);

    protected final static String DEFAULT_FONT_FAMILIES = "Arial";
    protected static final Color LEGEND_TEXT_COLOR = new SolidColor(85, 85, 85);

    public VaadinTheme() {
        setColors(COLOR1, COLOR2, COLOR3, COLOR4, COLOR5, COLOR6, COLOR7,
                COLOR8);
        Style style = new Style();
        style.setFontFamily(DEFAULT_FONT_FAMILIES);
        style.setFontSize("12px");
        getChart().setStyle(style);

        getTitle().setColor(TITLE_COLOR);
        getTitle().setFontSize("16px");
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

        getTooltip().setBackgroundColor(new SolidColor(255, 255, 255));
        getTooltip().setBorderWidth(1);
        getTooltip().setBorderRadius(BORDER_RADIUS);
        getTooltip().getStyle().setColor(TITLE_COLOR);

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
        getCredits().setHref("http://vaadin.com");

        getyAxis().setMinorTickInterval(TickIntervalStyle.NONE);
        getyAxis().setAlternateGridColor(BGCOLOR_LIGHT_GRAY);
        getyAxis().setGridLineColor(LINE_COLOR);
        getyAxis().setLineWidth(0);
        getyAxis().setTickWidth(0);

        getxAxis().setLineColor(LINE_COLOR);
        getxAxis().setGridLineWidth(0);

        getPlotOptions().getLine().setLineWidth(1);

    }

    protected void setAxisDefaults(AxisStyle style) {
        style.setGridLineColor(GRID_COLOR);
        style.setLineColor(GRID_COLOR);
        style.setLineWidth(1);
        style.setTickColor(GRID_COLOR);
        style.setAlternateGridColor(new SolidColor(255, 255, 255, 0.0));

        style.getTitle().setColor(TITLE_COLOR);
        style.getTitle().setFontWeight(FontWeight.BOLD);

        style.getSubtitle().setColor(SUBTITLE_COLOR);
        style.getSubtitle().setFontSize("10px");
        style.getSubtitle().setFontWeight(FontWeight.NORMAL);

        style.getLabels().setFontWeight(FontWeight.NORMAL);
        style.getLabels().setColor(LABEL_COLOR);
        style.getLabels().setFontSize("12px");

    }

}
