package com.vaadin.addon.charts.model;

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

/**
 * Adds all Y specific fields to Axis
 */
public class YAxis extends Axis {
    private StackLabels stackLabels;
    private PlotLine[] plotLines;
    private Number pane;
    private String gridLineInterpolation;

    /**
     * @see #setStackLabels(StackLabels)
     * @return
     */
    public StackLabels getStackLabels() {
        return stackLabels;
    }

    /**
     * The stack labels show the total value for each bar in a stacked column or
     * bar chart. The label will be placed on top of positive columns and below
     * negative columns. In case of an inverted column chart or a bar chart the
     * label is placed to the right of positive bars and to the left of negative
     * bars.
     * 
     * @param stackLabels
     */
    public void setStackLabels(StackLabels stackLabels) {
        this.stackLabels = stackLabels;
    }

    /**
     * PlotLines for yAxis
     * 
     * @param plotLines
     */
    public void setPlotLines(PlotLine... plotLines) {
        this.plotLines = plotLines;
    }

    /**
     * @see #setPlotLines(PlotLine[])
     */
    public PlotLine[] getPlotLines() {
        return plotLines;
    }

    /**
     * The pane index to where this axis is rendered
     * 
     * @param pane
     */
    public void setPane(Number pane) {
        this.pane = pane;
    }

    /**
     * @see #setPane(Number)
     * @return
     */
    public Number getPane() {
        return pane;
    }

    /**
     * Define polar graph type. E.g. "POLYGON" for spiderweb chart
     * 
     * @param gridLineInterpolation
     */
    public void setGridLineInterpolation(String gridLineInterpolation) {
        this.gridLineInterpolation = gridLineInterpolation;
    }

    /**
     * @see #setGridLineInterpolation(String)
     * @return
     */
    public String getGridLineInterpolation() {
        return gridLineInterpolation;
    }
}
