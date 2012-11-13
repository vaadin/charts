package com.vaadin.addon.charts.themes;

import com.vaadin.addon.charts.model.style.FontWeight;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.addon.charts.model.style.TickIntervalStyle;

/**
 * Theme based on Grid theme on HighCharts demo page
 */
public class GridTheme extends HighChartsDefaultTheme {

    public final static String FONT_FAMILIES = "\"Trebuchet MS\", Verdana, sans-serif";

    public GridTheme() {
        setColors(new SolidColor("#058DC7"), new SolidColor("#50B432"),
                new SolidColor("#ED561B"), new SolidColor("#DDDF00"),
                new SolidColor("#24CBE5"), new SolidColor("#64E572"),
                new SolidColor("#FF9655"), new SolidColor("#FFF263"),
                new SolidColor("#6AF9C4"));
        
        Style style = new Style();
        style.setFontFamily(FONT_FAMILIES);
        style.setFontSize("12px");
        getChart().setStyle(style);

        getChart().setPlotBackgroundColor(new SolidColor(255, 255, 255, .9));
        getChart().setPlotShadow(true);
        getChart().setPlotBorderWidth(1);

        getTitle().setColor(new SolidColor("#000"));
        getTitle().setFontSize("16px");
        getTitle().setFontWeight(FontWeight.BOLD);

        getSubtitle().setColor(new SolidColor("#666666"));
        getSubtitle().setFontWeight(FontWeight.BOLD);

        getxAxis().setGridLineWidth(1);
        getxAxis().setLineColor(new SolidColor("#000"));
        getxAxis().setTickColor(new SolidColor("#000"));
        getxAxis().getLabels().setColor(new SolidColor("#000"));
        getxAxis().getLabels().setFontSize("11px");
        getxAxis().getTitle().setColor(new SolidColor("#333"));
        getxAxis().getTitle().setFontWeight(FontWeight.BOLD);

        getyAxis().setMinorTickInterval(TickIntervalStyle.AUTO);
        getyAxis().setLineColor(new SolidColor("#000"));
        getyAxis().setLineWidth(1);
        getyAxis().setTickWidth(1);
        getyAxis().setTickColor(new SolidColor("#000"));
        getyAxis().getLabels().setColor(new SolidColor("#000"));
        getyAxis().getLabels().setFontSize("11px");
        getyAxis().getTitle().setColor(new SolidColor("#333"));
        getyAxis().getTitle().setFontWeight(FontWeight.BOLD);
 
        getLegend().getItemStyle().setColor(new SolidColor("black"));

        getLegend().getItemHoverStyle().setColor(new SolidColor("#039"));

        getLegend().getItemHiddenStyle().setColor(new SolidColor("gray"));

        getLabels().setColor(new SolidColor("#99b"));
    }
}
