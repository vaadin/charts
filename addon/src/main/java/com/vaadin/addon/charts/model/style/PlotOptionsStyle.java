package com.vaadin.addon.charts.model.style;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;

/**
 * Styles for PlotOptions of different plot types
 */
public class PlotOptionsStyle extends AbstractConfigurationObject {
    private PlotOptionStyle bar = new PlotOptionStyle();
    private PlotOptionStyle area = new PlotOptionStyle();
    private PlotOptionStyle pie = new PlotOptionStyle();
    private PlotOptionStyle line = new PlotOptionStyle();
    private PlotOptionStyle column = new PlotOptionStyle();
    private PlotOptionStyle spline = new PlotOptionStyle();
    private PlotOptionStyle series = new PlotOptionStyle();

    public PlotOptionStyle getBar() {
        return bar;
    }

    public void setBar(PlotOptionStyle bar) {
        this.bar = bar;
    }

    public PlotOptionStyle getArea() {
        return area;
    }

    public void setArea(PlotOptionStyle area) {
        this.area = area;
    }

    public PlotOptionStyle getPie() {
        return pie;
    }

    public void setPie(PlotOptionStyle pie) {
        this.pie = pie;
    }

    public PlotOptionStyle getLine() {
        return line;
    }

    public void setLine(PlotOptionStyle line) {
        this.line = line;
    }

    public PlotOptionStyle getColumn() {
        return column;
    }

    public void setColumn(PlotOptionStyle column) {
        this.column = column;
    }

    public PlotOptionStyle getSpline() {
        return spline;
    }

    public void setSpline(PlotOptionStyle spline) {
        this.spline = spline;
    }

    public PlotOptionStyle getSeries() {
        return series;
    }

    public void setSeries(PlotOptionStyle series) {
        this.series = series;
    }

}
