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

import java.util.Date;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.util.Util;

/**
 * The DataSeriesItem class represents a single entry in a {@link DataSeries}.
 */
@SuppressWarnings("serial")
public class DataSeriesItem extends AbstractConfigurationObject {
    private String name;
    private Number y;
    private Number x;
    private Number low;
    private Number high;
    private Boolean sliced;
    private Boolean selected;
    private Color color;
    private Number legendIndex;
    private Marker marker;
    private String id;
    private Dial dial;
    /*
     * Flag to indicate if this item can be passed in optimized form to
     * rendering library.
     */
    private boolean customized = false;

    public DataSeriesItem() {
    }

    /**
     * Constructs an item with a category name and a Y value
     * 
     * @param categoryName
     * @param y
     */
    public DataSeriesItem(String categoryName, Number y) {
        this.name = categoryName;
        this.y = y;
        customized = true;
    }

    /**
     * Constructs an item with a category name and a value on the Y-axis and
     * assigns the specified color to the item.
     * 
     * @param categoryName
     * @param y
     * @param color
     */
    public DataSeriesItem(String categoryName, Number y, Color color) {
        this.name = categoryName;
        this.y = y;
        setColor(color);
        customized = true;
    }

    /**
     * Constructs an item with X and Y values
     * 
     * @param x
     * @param y
     */
    public DataSeriesItem(Number x, Number y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs an item with numerical values for the X- and Y-axis and
     * assigns the specified color to the item.
     * 
     * @param x
     * @param y
     * @param color
     */
    public DataSeriesItem(Number x, Number y, Color color) {
        this.x = x;
        this.y = y;
        setColor(color);
        customized = true;
    }

    /**
     * Constructs a DataSeriesItem with the given date as X value and Y value.
     * 
     * @param date
     * @param y
     */
    public DataSeriesItem(Date date, Number y) {
        setX(date);
        setY(y);
    }

    /**
     * Sets the given date as the x value.
     * <p>
     * Note, that internally the Date value is stored as an "epoch timestamp",
     * adjusted by timezone offset. So when calling {@link #getX()} you might
     * get a bit odd result.
     * 
     * @param date
     */
    public void setX(Date date) {
        setX(Util.toHighchartsTS(date));
    }

    /**
     * Constructs a DataSeriesItem with the given date as X value with min and
     * max values for use in range visualizations.
     * 
     * @param date
     * @param min
     * @param max
     */
    public DataSeriesItem(Date date, Number min, Number max) {
        setX(date);
        setLow(min);
        setHigh(max);
    }

    /**
     * Constructs a DataSeriesItem with the given X, min and max values for use
     * in range visualizations.
     * 
     * @param x
     * @param min
     * @param max
     */
    public DataSeriesItem(Number x, Number min, Number max) {
        setX(x);
        setLow(min);
        setHigh(max);
    }

    /**
     * @see #setName(String)
     * @return The category name of the data item or null if not defined
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the category name of the data item as shown in the legend, tooltip,
     * dataLabel etc. Defaults to "".
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
        customized = true;
    }

    /**
     * @see #setY(Number)
     * @return The Y value of this data item.
     */
    public Number getY() {
        return y;
    }

    /**
     * Sets the Y value of this data item. Defaults to null.
     * 
     * @param y
     */
    public void setY(Number y) {
        this.y = y;
    }

    /**
     * @see #setX(Number)
     * @return The X value of this data item.
     */
    public Number getX() {
        return x;
    }

    /**
     * Sets the X value of this data item. Defaults to null.
     * 
     * @param x
     */
    public void setX(Number x) {
        this.x = x;
    }

    /**
     * @see #setSliced(Boolean)
     * @return Whether this data item is displayed offset from the center in a
     *         pie chart.
     */
    public boolean getSliced() {
        return sliced == null ? false : sliced;
    }

    /**
     * Sets whether to display a slice offset from the center. Defaults to
     * false.
     * 
     * <em>Note</em> This applies to pie charts only.
     * 
     * @param sliced
     */
    public void setSliced(Boolean sliced) {
        this.sliced = sliced;
        customized = true;
    }

    /**
     * @see #setSelected(Boolean)
     */
    public boolean isSelected() {
        return selected == null ? false : selected;
    }

    /**
     * Sets whether the data item is selected or not.
     * 
     * @param selected
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
        customized = true;
    }

    /**
     * @see #setColor(Color)
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the individual color for the point. Defaults to null. This might not
     * work for all chart types.
     * 
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
        customized = true;
    }

    /**
     * @see #setLegendIndex(Number)
     * @return The index of the legend or null if not defined. Only applicable
     *         for pie charts.
     */
    public Number getLegendIndex() {
        return legendIndex;
    }

    /**
     * Sets the sequential index of the pie slice in the legend. Defaults to
     * undefined.
     * 
     * <em>Note</em> This applies to pie charts only.
     * 
     * @param legendIndex
     */
    public void setLegendIndex(Number legendIndex) {
        this.legendIndex = legendIndex;
        customized = true;
    }

    /**
     * Sets the marker of this data series item
     * 
     * @param marker
     */
    public void setMarker(Marker marker) {
        this.marker = marker;
        customized = true;
    }

    /**
     * @see #setMarker(Marker)
     * @return The marker of this data series item. If none is specified a new
     *         {@link Marker} will be created.
     */
    public Marker getMarker() {
        if (marker == null) {
            marker = new Marker();
            marker.setEnabled(true);
        }
        return marker;
    }

    /**
     * @see #setId(String)
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID for the point. This can be used after rendering to get a
     * reference to the point object. Defaults to null.
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
        customized = true;
    }

    /**
     * Sets the dial or arrow pointer of the gauge.
     * 
     * <em>Note</em> This is only applicable for gauge charts.
     * 
     * @param dial
     */
    public void setDial(Dial dial) {
        this.dial = dial;
        customized = true;
    }

    /**
     * @see #setDial(Dial)
     * @return The dial or arrow pointer of a gauge chart. Only applicable for
     *         gauge charts.
     */
    public Dial getDial() {
        return dial;
    }

    /**
     * @return true if the data series item can be rendered in optimized manner
     */
    public boolean isCustomized() {
        return customized;
    }

    public Number getLow() {
        return low;
    }

    public void setLow(Number low) {
        this.low = low;
    }

    public Number getHigh() {
        return high;
    }

    public void setHigh(Number high) {
        this.high = high;
    }
}
