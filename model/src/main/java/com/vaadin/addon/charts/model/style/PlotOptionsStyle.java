package com.vaadin.addon.charts.model.style;

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

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.PlotOptionsAreaRange;
import com.vaadin.addon.charts.model.PlotOptionsAreaSpline;
import com.vaadin.addon.charts.model.PlotOptionsAreaSplineRange;
import com.vaadin.addon.charts.model.PlotOptionsBar;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.PlotOptionsPolygon;
import com.vaadin.addon.charts.model.PlotOptionsPyramid;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.PlotOptionsTreeMap;
import com.vaadin.addon.charts.model.PlotOptionsWaterfall;

/**
 * Styles for PlotOptions of different plot types
 */
@SuppressWarnings("serial")
public class PlotOptionsStyle extends AbstractConfigurationObject {
    private PlotOptionsBar bar = new PlotOptionsBar();
    private PlotOptionsArea area = new PlotOptionsArea();
    private PlotOptionsAreaRange arearange = new PlotOptionsAreaRange();
    private PlotOptionsAreaSpline areaspline = new PlotOptionsAreaSpline();
    private PlotOptionsAreaSplineRange areasplinerange = new PlotOptionsAreaSplineRange();
    private PlotOptionsPie pie = new PlotOptionsPie();
    private PlotOptionsLine line = new PlotOptionsLine();
    private PlotOptionsColumn column = new PlotOptionsColumn();
    private PlotOptionsSpline spline = new PlotOptionsSpline();
    private PlotOptionsSeries series = new PlotOptionsSeries();
    private PlotOptionsPyramid pyramid = new PlotOptionsPyramid();
    private PlotOptionsWaterfall waterfall = new PlotOptionsWaterfall();
    private PlotOptionsTreeMap treemap = new PlotOptionsTreeMap();
    private PlotOptionsPolygon polygon = new PlotOptionsPolygon();

    /**
     * @see #setBar(PlotOptionsBar)
     */
    public PlotOptionsBar getBar() {
        return bar;
    }

    /**
     * Sets the style options for {@link ChartType#BAR} charts
     * 
     * @param bar
     */
    public void setBar(PlotOptionsBar bar) {
        this.bar = bar;
    }

    /**
     * @see #setArea(PlotOptionsArea)
     */
    public PlotOptionsArea getArea() {
        return area;
    }

    /**
     * Sets the style options for {@link ChartType#AREA} charts
     * 
     * @param bar
     */
    public void setArea(PlotOptionsArea area) {
        this.area = area;
    }

    /**
     * @see #setPie(PlotOptionsPie)
     */
    public PlotOptionsPie getPie() {
        return pie;
    }

    /**
     * Sets the style options for {@link ChartType#PIE} charts
     * 
     * @param bar
     */
    public void setPie(PlotOptionsPie pie) {
        this.pie = pie;
    }

    /**
     * @see #setLine(PlotOptionsLine)
     */
    public PlotOptionsLine getLine() {
        return line;
    }

    /**
     * Sets the style options for {@link ChartType#LINE} charts
     * 
     * @param bar
     */
    public void setLine(PlotOptionsLine line) {
        this.line = line;
    }

    /**
     * @see #setColumn(PlotOptionsColumn)
     */
    public PlotOptionsColumn getColumn() {
        return column;
    }

    /**
     * Sets the style options for {@link ChartType#COLUMN} charts
     * 
     * @param bar
     */
    public void setColumn(PlotOptionsColumn column) {
        this.column = column;
    }

    /**
     * @see #setSpline(PlotOptionsSpline)
     */
    public PlotOptionsSpline getSpline() {
        return spline;
    }

    /**
     * Sets the style options for {@link ChartType#SPLINE} charts
     * 
     * @param bar
     */
    public void setSpline(PlotOptionsSpline spline) {
        this.spline = spline;
    }

    /**
     * @see #setSeries(PlotOptionsSeries)
     */
    public PlotOptionsSeries getSeries() {
        return series;
    }

    /**
     * Sets the style rules for all chart types
     * 
     * @param series
     */
    public void setSeries(PlotOptionsSeries series) {
        this.series = series;
    }

    /**
     * @see #setArearange(PlotOptionsAreaRange)
     */
    public PlotOptionsAreaRange getArearange() {
        return arearange;
    }

    /**
     * Sets the style options for {@link ChartType#AREARANGE} charts
     * 
     * @param arearange
     */
    public void setArearange(PlotOptionsAreaRange arearange) {
        this.arearange = arearange;
    }

    /**
     * @see #setAreasplinerange(PlotOptionsAreaSplineRange)
     */
    public PlotOptionsAreaSplineRange getAreasplinerange() {
        return areasplinerange;
    }

    /**
     * Sets the style options for {@link ChartType#AREASPLINERANGE} charts
     * 
     * @param areasplinerange
     */
    public void setAreasplinerange(PlotOptionsAreaSplineRange areasplinerange) {
        this.areasplinerange = areasplinerange;
    }

    /**
     * @see #setAreaspline(PlotOptionsAreaSpline)
     */
    public PlotOptionsAreaSpline getAreaspline() {
        return areaspline;
    }

    /**
     * Sets the style options for {@link ChartType#AREASPLINE} charts
     * 
     * @param areaspline
     */
    public void setAreaspline(PlotOptionsAreaSpline areaspline) {
        this.areaspline = areaspline;
    }

    /**
     * @see #setPyramid(PlotOptionsPyramid)
     */
    public PlotOptionsPyramid getPyramid() {
        return pyramid;
    }

    /**
     * Sets the style options for {@link ChartType#PYRAMID} charts
     * 
     * @param pyramid
     */
    public void setPyramid(PlotOptionsPyramid pyramid) {
        this.pyramid = pyramid;
    }

    /**
     * @see #setWaterfall(PlotOptionsWaterfall)
     * @return waterfall
     */
    public PlotOptionsWaterfall getWaterfall() {
        return waterfall;
    }

    /**
     * Sets the style options for {@link ChartType#WATERFALL} charts
     * 
     * @param waterfall
     */
    public void setWaterfall(PlotOptionsWaterfall waterfall) {
        this.waterfall = waterfall;
    }

    /**
     * @see #setTreemap(PlotOptionsTreeMap)
     * @return
     */
    public PlotOptionsTreeMap getTreeMap() {
        return treemap;
    }

    /**
     * Sets the style options for {@link ChartType#TREEMAP} charts
     *
     * @param treemap
     */
    public void setTreemap(PlotOptionsTreeMap treemap) {
        this.treemap = treemap;
    }

    /**
     * @see #setPolygon(PlotOptionsPolygon)
     * @return polygon
     */
    public PlotOptionsPolygon getPolygon() {
        return polygon;
    }

    /**
     * Sets the style options for {@link ChartType#POLYGON} charts
     * 
     * @param polygon
     */
    public void setPolygon(PlotOptionsPolygon polygon) {
        this.polygon = polygon;
    }

}
