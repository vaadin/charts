package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.SolidColor;

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
    private Integer pane;
    private String gridLineInterpolation;
    private String minColor;
    private String maxColor;

    /**
     * @see #setStackLabels(StackLabels)
     */
    public StackLabels getStackLabels() {
        return stackLabels;
    }

    /**
     * Sets the stack labels. The stack labels show the total value for each bar
     * in a stacked column or bar chart. The label will be placed on top of
     * positive columns and below negative columns. In case of an inverted
     * column chart or a bar chart the label is placed to the right of positive
     * bars and to the left of negative bars.
     * 
     * @param stackLabels
     */
    public void setStackLabels(StackLabels stackLabels) {
        this.stackLabels = stackLabels;
    }

    /**
     * Sets the plot lines for the Y-axis
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
     * Sets the index of the pane onto which this axis is rendered.
     * 
     * @param pane
     * @see #setPane(Pane) for a more strongly typed API
     */
    public void setPane(Integer pane) {
        this.pane = pane;
    }

    /**
     * @see #setPane(Number)
     * @return The index of the pane onto which this axis is rendered.
     */
    public Integer getPane() {
        return pane;
    }

    /**
     * Defines a polar graph type. E.g. "POLYGON" for a spiderweb chart
     * 
     * @param gridLineInterpolation
     */
    public void setGridLineInterpolation(String gridLineInterpolation) {
        this.gridLineInterpolation = gridLineInterpolation;
    }

    /**
     * @see #setGridLineInterpolation(String)
     */
    public String getGridLineInterpolation() {
        return gridLineInterpolation;
    }

    /**
     * Sets the pane onto which this axis is rendered.
     * 
     * @param pane
     */
    public void setPane(Pane pane) {
        if (pane.getPaneIndex() == null) {
            throw new IllegalStateException(
                    "Pane must be attached to configuration");
        }
        setPane(pane.getPaneIndex());
    }

    /**
     * Returns current minimum color.
     * 
     * @return Minimum color.
     */
    public String getMinColor() {
        return minColor;
    }

    /**
     * Sets the minimum color.
     * 
     * @param minColor
     *            Minimum color.
     */
    public void setMinColor(String minColor) {
        this.minColor = minColor;
    }

    /**
     * Sets the minimum color.
     * 
     * @param color
     *            Minimum color.
     */
    public void setMinColor(SolidColor color) {
        this.minColor = color.toString();
    }

    /**
     * Returns current maximum color.
     * 
     * @return Maximum color.
     */
    public String getMaxColor() {
        return maxColor;
    }

    /**
     * Sets the new maximum color.
     * 
     * @param maxColor
     *            Maximum color.
     */
    public void setMaxColor(String maxColor) {
        this.maxColor = maxColor;
    }

    /**
     * Sets the new maximum color.
     * 
     * @param color
     *            Maximum color.
     */
    public void setMaxColor(SolidColor color) {
        this.maxColor = color.toString();
    }
}
