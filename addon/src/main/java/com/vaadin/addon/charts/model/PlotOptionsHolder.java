package com.vaadin.addon.charts.model;

/**
 * The plotOptions is a wrapper object for configuration objects for each series
 * type. The configuration objects for each series can also be overridden for
 * each series item as given in the series array.
 * 
 * Configuration options for the series are given in three levels. Options for
 * all series in a chart are given in the plotOptions.series object. Then
 * options for all series of a specific type are given in the plotOptions of
 * that type, for example plotOptions.line. Next, options for one single series
 * are given in the series array.
 */
public class PlotOptionsHolder extends AbstractConfigurationObject {
    private PlotOptionsBar bar;
    private PlotOptionsArea area;
    private PlotOptionsPie pie;
    private PlotOptionsLine line;
    private PlotOptionsColumn column;
    private PlotOptionsSpline spline;
    private PlotOptionsSeries series;
    private PlotOptionsGauge gauge;
    private PlotOptionsColumnRange columnrange;

    /**
     * @see #setBar(PlotOptionsBar)
     * @return
     */
    public PlotOptionsBar getBar() {
        return bar;
    }

    /**
     * Sets the plot options for bar charts
     * 
     * @param bar
     */
    public void setBar(PlotOptionsBar bar) {
        this.bar = bar;
    }

    /**
     * @see #setArea(PlotOptionsArea)
     * @return
     */
    public PlotOptionsArea getArea() {
        return area;
    }

    /**
     * Sets the plot options for area charts
     * 
     * @param area
     */
    public void setArea(PlotOptionsArea area) {
        this.area = area;
    }

    /**
     * @see #setPie(PlotOptionsPie)
     * @return
     */
    public PlotOptionsPie getPie() {
        return pie;
    }

    /**
     * Sets the plot options for pie charts
     * 
     * @param pie
     */
    public void setPie(PlotOptionsPie pie) {
        this.pie = pie;
    }

    /**
     * Sets the plot options for line charts
     * 
     * @param line
     */
    public void setLine(PlotOptionsLine line) {
        this.line = line;
    }

    /**
     * @see #setLine(PlotOptionsLine)
     * @return
     */
    public PlotOptionsLine getLine() {
        return line;
    }

    /**
     * @see #setColumn(PlotOptionsColumn)
     * @return
     */
    public PlotOptionsColumn getColumn() {
        return column;
    }

    /**
     * Sets the plot options for column charts
     * 
     * @param column
     */
    public void setColumn(PlotOptionsColumn column) {
        this.column = column;
    }

    /**
     * Sets the plot options for spline charts
     * 
     * @param spline
     */
    public void setSpline(PlotOptionsSpline spline) {
        this.spline = spline;
    }

    /**
     * @see #setSpline(PlotOptionsSpline)
     * @return
     */
    public PlotOptionsSpline getSpline() {
        return spline;
    }

    /**
     * @see #setSeries(PlotOptionsSeries)
     * @return
     */
    public PlotOptionsSeries getSeries() {
        return series;
    }

    /**
     * Sets the plot options for series charts
     * 
     * @param series
     */
    public void setSeries(PlotOptionsSeries series) {
        this.series = series;
    }

    /**
     * Sets the plot options for gauge charts
     * 
     * @param gauge
     */
    public void setGauge(PlotOptionsGauge gauge) {
        this.gauge = gauge;
    }

    /**
     * @see #setGauge(PlotOptionsGauge)
     * @return
     */
    public PlotOptionsGauge getGauge() {
        return gauge;
    }

    /**
     * Sets the plot options for column range charts
     * 
     * @param columnRange
     */
    public void setColumnRange(PlotOptionsColumnRange columnRange) {
        this.columnrange = columnRange;
    }

    /**
     * @see #setColumnRange(PlotOptionsColumnRange)
     * @return
     */
    public PlotOptionsColumnRange getColumnRange() {
        return columnrange;
    }
}
