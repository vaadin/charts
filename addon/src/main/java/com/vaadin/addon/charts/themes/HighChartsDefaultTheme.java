package com.vaadin.addon.charts.themes;

/*-
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2019 Vaadin Ltd
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

import com.vaadin.addon.charts.model.style.AxisStyle;
import com.vaadin.addon.charts.model.style.FontWeight;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.addon.charts.model.style.StylePosition;
import com.vaadin.addon.charts.model.style.Theme;
import com.vaadin.addon.charts.model.style.TickIntervalStyle;

@SuppressWarnings("serial")
public class HighChartsDefaultTheme extends Theme {
    private static final SolidColor TITLE_FONT_COLOR = new SolidColor("#3E576F");
    private static final SolidColor GRAY = new SolidColor("#666666");
    private static final SolidColor GRAY2 = new SolidColor("#646464");
    private static final String DEFAULT_FONT = "\"Lucida Grande\", \"Lucida Sans Unicode\", Verdana, Arial, Helvetica, sans-serif";
    private static final SolidColor PLOT_BORDER_COLOR = new SolidColor(
            "#C0C0C0");

    public HighChartsDefaultTheme() {

        setColors(new SolidColor("#2f7ed8"), new SolidColor("#0d233a"),
                new SolidColor("#8bbc21"), new SolidColor("#910000"),
                new SolidColor("#1aadce"), new SolidColor("#492970"),
                new SolidColor("#f28f43"), new SolidColor("#77a1e5"),
                new SolidColor("#c42525"), new SolidColor("#a6c96a"));

        Style style = new Style();
        style.setFontFamily(DEFAULT_FONT);
        style.setFontSize("12px");
        getChart().setStyle(style);

        getChart().setClassName("");
        getChart().setPlotBackgroundImage("");
        getChart().setPlotBackgroundColor(new SolidColor(255, 255, 255, 0.0));
        getChart().setBackgroundColor(new SolidColor("#FFFFFF"));
        getChart().setPlotBorderColor(PLOT_BORDER_COLOR);
        getChart().setPlotShadow(false);
        getChart().setPlotBorderWidth(0);

        getTitle().setColor(TITLE_FONT_COLOR);
        getTitle().setFontSize("16px");
        getTitle().setFontWeight(FontWeight.NORMAL);

        getSubtitle().setColor(new SolidColor("#6D869F"));
        getSubtitle().setFontSize("12px");
        getSubtitle().setFontWeight(FontWeight.NORMAL);

        setAxisDefaults(getxAxis());
        getxAxis().setGridLineWidth(0);

        setAxisDefaults(getyAxis());
        getyAxis().setLineWidth(0);

        getyAxis().setGridLineWidth(1);
        getyAxis().setTickWidth(0);
        getyAxis().setMinorTickInterval(TickIntervalStyle.NONE);

        getTooltip().setBackgroundColor(new SolidColor(255, 255, 255, 0.85));
        getTooltip().setBorderWidth(2);
        getTooltip().getStyle().setColor(new SolidColor("#333333"));

        getLegend().setBorderRadius(5);
        getLegend().setBackgroundColor(new SolidColor(255, 255, 255, 0));
        getLegend().setBorderColor(GRAY2);
        getLegend().setItemStyle(new Style());
        getLegend().getItemStyle().setColor(TITLE_FONT_COLOR);
        getLegend().getItemStyle().setPosition(StylePosition.RELATIVE);

        getLegend().setItemHoverStyle(new Style());
        getLegend().getItemHoverStyle().setColor(new SolidColor("#000"));

        getLegend().setItemHiddenStyle(new Style());
        getLegend().getItemHiddenStyle().setColor(new SolidColor("#CCC"));

        getPlotOptions().getArearange().setShadow(false);
        getPlotOptions().getAreasplinerange().setShadow(false);

        getPlotOptions().getLine().setShadow(false);
        getPlotOptions().getSpline().setShadow(false);
        getPlotOptions().getBar().setShadow(false);
        getPlotOptions().getColumn().setShadow(false);
        getPlotOptions().getArea().setShadow(false);
        getPlotOptions().getPie().setShadow(false);

    }

    protected void setAxisDefaults(AxisStyle style) {
        style.setGridLineColor(PLOT_BORDER_COLOR);
        style.setLineColor(new SolidColor("#C0D0E0"));
        style.setLineWidth(1);
        style.setTickColor(new SolidColor("#C0D0E0"));
        style.setAlternateGridColor(new SolidColor(255, 255, 255, 0.0));
        style.getTitle().setFontWeight(FontWeight.NORMAL);
        style.getTitle().setColor(TITLE_FONT_COLOR);
        style.getSubtitle().setFontSize("10px");
        style.getSubtitle().setFontWeight(FontWeight.NORMAL);
        style.getSubtitle().setColor(GRAY);
        style.getLabels().setFontSize("11px");
        style.getLabels().setFontWeight(FontWeight.NORMAL);
        style.getLabels().setColor(GRAY);

    }
}
