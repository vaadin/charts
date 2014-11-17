package com.vaadin.addon.charts.model;

import java.io.Serializable;

import com.vaadin.addon.charts.model.style.SolidColor;

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

/**
 * Adds all Y specific fields to Axis
 */
public class YAxis extends AbstractColorAxis {
    private StackLabels stackLabels;
    private PlotLine[] plotLines;
    private Integer pane;
    private String gridLineInterpolation;
    private Object[][] stops;

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
     * @see #setStops(Stop...)
     * @return array of tuples, where the first item is a float between 0 and 1
     *         assigning the relative position in the gradient, and the second
     *         item is the color.
     */
    public Object[][] getStops() {
        return stops;
    }

    /**
     * Only for {@link ChartType#SOLIDGAUGE} series only. Color stops for the
     * solid gauge. Use this in cases where a linear gradient between a minColor
     * and maxColor is not sufficient.
     * 
     * @param stopObjects
     */
    public void setStops(Stop... stopObjects) {
        if (stopObjects != null) {
            stops = new Object[stopObjects.length][2];
            for (int i = 0; i < stopObjects.length; i++) {
                Stop stop = stopObjects[i];
                if (stop.color != null) {
                    stops[i][0] = stop.getPosition();
                    stops[i][1] = stop.getColor().toString();
                }
            }
        }
    }

    /**
     * Class used for {@link ChartType#SOLIDGAUGE} series only. Color stops for
     * the solid gauge. Use this in cases where a linear gradient between a
     * minColor and maxColor is not sufficient.
     */
    public static class Stop implements Serializable {
        private final Float position;
        private final SolidColor color;

        /**
         * The stop is a tuple, where the first item is a float between 0 and 1
         * assigning the relative position in the gradient, and the second item
         * is the color.
         * 
         * @param position
         * @param color
         */
        public Stop(Float position, SolidColor color) {
            this.position = position;
            this.color = color;
        }

        /**
         * Float between 0 and 1 assigning the relative position in the gradient
         * 
         * @return position
         */
        public Float getPosition() {
            return position;
        }

        /**
         * Color of the stop in the gradient
         * 
         * @return color
         */
        public SolidColor getColor() {
            return color;
        }

    }
}
