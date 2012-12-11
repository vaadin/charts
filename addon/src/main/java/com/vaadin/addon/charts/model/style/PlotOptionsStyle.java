package com.vaadin.addon.charts.model.style;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.PlotOptionsBar;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.addon.charts.model.PlotOptionsSpline;

/**
 * Styles for PlotOptions of different plot types
 */
public class PlotOptionsStyle extends AbstractConfigurationObject {
    private PlotOptionsBar bar = new PlotOptionsBar();
    private PlotOptionsArea area = new PlotOptionsArea();
    private PlotOptionsPie pie = new PlotOptionsPie();
    private PlotOptionsLine line = new PlotOptionsLine();
    private PlotOptionsColumn column = new PlotOptionsColumn();
    private PlotOptionsSpline spline = new PlotOptionsSpline();
    private PlotOptionsSeries series = new PlotOptionsSeries();

    /**
     * @see #getBar()
     * @return
     */
    public PlotOptionsBar getBar() {
        return bar;
    }

    /**
     * Set style options BAR charts
     * 
     * @param bar
     */
    public void setBar(PlotOptionsBar bar) {
        this.bar = bar;
    }

    /**
     * @see #getArea()
     * @return
     */
    public PlotOptionsArea getArea() {
        return area;
    }

    /**
     * Set style options AREA charts
     * 
     * @param bar
     */
    public void setArea(PlotOptionsArea area) {
        this.area = area;
    }

    /**
     * @see #getPie()
     * @return
     */
    public PlotOptionsPie getPie() {
        return pie;
    }

    /**
     * Set style options PIE charts
     * 
     * @param bar
     */
    public void setPie(PlotOptionsPie pie) {
        this.pie = pie;
    }

    /**
     * @see #getLine()
     * @return
     */
    public PlotOptionsLine getLine() {
        return line;
    }

    /**
     * Set style options LINE charts
     * 
     * @param bar
     */
    public void setLine(PlotOptionsLine line) {
        this.line = line;
    }

    /**
     * @see #getColumn()
     * @return
     */
    public PlotOptionsColumn getColumn() {
        return column;
    }

    /**
     * Set style options COLUMN charts
     * 
     * @param bar
     */
    public void setColumn(PlotOptionsColumn column) {
        this.column = column;
    }

    /**
     * @see #getSpline()
     * @return
     */
    public PlotOptionsSpline getSpline() {
        return spline;
    }

    /**
     * Set style options SPLINE charts
     * 
     * @param bar
     */
    public void setSpline(PlotOptionsSpline spline) {
        this.spline = spline;
    }

    /**
     * @see #getSeries()
     * @return
     */
    public PlotOptionsSeries getSeries() {
        return series;
    }

    /**
     * Set style rules for all chart types
     * 
     * @param bar
     */
    public void setSeries(PlotOptionsSeries series) {
        this.series = series;
    }

}
