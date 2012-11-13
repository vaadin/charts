package com.vaadin.addon.charts.model;

import java.util.Date;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;

/**
 * A colored band stretching across the plot area marking an interval on the
 * axis.
 */
public class PlotBand extends AbstractConfigurationObject {
    private Number from;
    private Number to;
    private Color color;
    private Number zIndex;
    private PlotBandLabel label;
    private String id;
    private String innerRadius;
    private String outerRadius;

    /**
     * Default constructor
     */
    public PlotBand() {

    }

    /**
     * Create new PlotBand with from, to and color values
     * 
     * @param from
     *            The start position of the plot band in axis units.
     * @param to
     *            The end position of the plot band in axis units.
     * @param color
     *            The color of the plot band.
     */
    public PlotBand(Number from, Number to, SolidColor color) {
        setFrom(from);
        setTo(to);
        setColor(color);
    }

    /**
     * The start position of the plot band in axis units. Defaults to null.
     * 
     * @return From value
     */
    public Number getFrom() {
        return from;
    }

    /**
     * @see #getFrom()
     * @param from
     */
    public void setFrom(Number from) {
        this.from = from;
    }

    /**
     * The end position of the plot band in axis units. Defaults to null.
     * 
     * @return To value
     */
    public Number getTo() {
        return to;
    }

    /**
     * @see #getTo()
     * @param to
     */
    public void setTo(Number to) {
        this.to = to;
    }

    /**
     * The color of the plot band. Defaults to null.
     * 
     * @return Color value
     */
    public Color getColor() {
        return color;
    }

    /**
     * @see #getColor()
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * The z index of the plot band within the chart. Defaults to null.
     * 
     * @return Z index value
     */
    public Number getzIndex() {
        return zIndex;
    }

    /**
     * @see #getzIndex()
     * @param zIndex
     */
    public void setzIndex(Number zIndex) {
        this.zIndex = zIndex;
    }

    /**
     * Text labels for the plot bands
     * 
     * @param label
     */
    public void setLabel(PlotBandLabel label) {
        this.label = label;
    }

    /**
     * @see #setLabel(PlotBandLabel)
     * @return
     */
    public PlotBandLabel getLabel() {
        return label;
    }

    /**
     * The start position of the plot band in axis units. Defaults to null.
     * 
     * @param date
     */
    public void setFrom(Date date) {
        from = date.getTime();
    }

    /**
     * The end position of the plot band in axis units. Defaults to null.
     * 
     * @return To value
     */
    public void setTo(Date date) {
        to = date.getTime();
    }

    /**
     * An id used for identifying the plot band in Axis.removePlotBand. Defaults
     * to null.
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @see #setId(String)
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Only applicable for ChartType.GAUGE, sets the inner radius of plot band
     * on the gauge
     * 
     * @param innerRadius
     */
    public void setInnerRadius(String innerRadius) {
        this.innerRadius = innerRadius;
    }

    /**
     * @see #setInnerRadius(String)
     * @return
     */
    public String getInnerRadius() {
        return innerRadius;
    }

    /**
     * Only applicable for ChartType.GAUGE, sets the outer radius of plot band
     * on the gauge
     * 
     * @param outerRadius
     */
    public void setOuterRadius(String outerRadius) {
        this.outerRadius = outerRadius;
    }

    /**
     * @see #setOuterRadius(String)
     * @return
     */
    public String getOuterRadius() {
        return outerRadius;
    }
}
