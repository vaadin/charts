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

    /**
     * @see #getBar()
     * @return
     */
    public PlotOptionStyle getBar() {
        return bar;
    }

    /**
     * Set style options BAR charts
     * 
     * @param bar
     */
    public void setBar(PlotOptionStyle bar) {
        this.bar = bar;
    }

    /**
     * @see #getArea()
     * @return
     */
    public PlotOptionStyle getArea() {
        return area;
    }

    /**
     * Set style options AREA charts
     * 
     * @param bar
     */
    public void setArea(PlotOptionStyle area) {
        this.area = area;
    }

    /**
     * @see #getPie()
     * @return
     */
    public PlotOptionStyle getPie() {
        return pie;
    }

    /**
     * Set style options PIE charts
     * 
     * @param bar
     */
    public void setPie(PlotOptionStyle pie) {
        this.pie = pie;
    }

    /**
     * @see #getLine()
     * @return
     */
    public PlotOptionStyle getLine() {
        return line;
    }

    /**
     * Set style options LINE charts
     * 
     * @param bar
     */
    public void setLine(PlotOptionStyle line) {
        this.line = line;
    }

    /**
     * @see #getColumn()
     * @return
     */
    public PlotOptionStyle getColumn() {
        return column;
    }

    /**
     * Set style options COLUMN charts
     * 
     * @param bar
     */
    public void setColumn(PlotOptionStyle column) {
        this.column = column;
    }

    /**
     * @see #getSpline()
     * @return
     */
    public PlotOptionStyle getSpline() {
        return spline;
    }

    /**
     * Set style options SPLINE charts
     * 
     * @param bar
     */
    public void setSpline(PlotOptionStyle spline) {
        this.spline = spline;
    }

    /**
     * @see #getSeries()
     * @return
     */
    public PlotOptionStyle getSeries() {
        return series;
    }

    /**
     * Set style options SERIES charts
     * 
     * @param bar
     */
    public void setSeries(PlotOptionStyle series) {
        this.series = series;
    }

}
