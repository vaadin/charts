package com.vaadin.addon.charts.model;

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

    /**
     * Creates an empty item, without values, colors, etc.
     */
    public DataSeriesItem() {
    }

    /**
     * Constructs an item with a name and a Y value
     * 
     * @param name
     *            Name of the item.
     * @param y
     *            Y-value of the item.
     */
    public DataSeriesItem(String name, Number y) {
        setName(name);
        setY(y);
        makeCustomized();
    }

    /**
     * Constructs an item with a name and a value on the Y-axis and assigns the
     * specified color to the item.
     * 
     * @param name
     *            Name of the item.
     * @param y
     *            Y-value of the item.
     * @param color
     *            Color of the item.
     */
    public DataSeriesItem(String name, Number y, Color color) {
        setName(name);
        setY(y);
        setColor(color);
        makeCustomized();
    }

    /**
     * Constructs an item with X and Y values
     * 
     * @param x
     *            X-value of the item.
     * @param y
     *            Y-value of the item.
     */
    public DataSeriesItem(Number x, Number y) {
        setX(x);
        setY(y);
    }

    /**
     * Constructs an item with numerical values for the X and Y axes and assigns
     * the specified color to the item.
     * 
     * @param x
     *            X-value of the item.
     * @param y
     *            Y-value of the item.
     * @param color
     *            Color of the item.
     */
    public DataSeriesItem(Number x, Number y, Color color) {
        setX(x);
        setY(y);
        setColor(color);
        makeCustomized();
    }

    /**
     * Constructs a DataSeriesItem with the given date as X value and Y value.
     * 
     * @param date
     *            Date of the item, as its X-value.
     * @param y
     *            Y-value of the item.
     */
    public DataSeriesItem(Date date, Number y) {
        setX(date);
        setY(y);
    }

    /**
     * Constructs a DataSeriesItem with the given date as X value with min and
     * max values for use in range visualizations.
     * 
     * @param date
     *            Date of the item, as its X-value.
     * @param low
     *            Lower value for range visualization.
     * @param high
     *            Upper value for range visualization.
     */
    public DataSeriesItem(Date date, Number low, Number high) {
        setX(date);
        setLow(low);
        setHigh(high);
    }

    /**
     * Constructs a DataSeriesItem with the given X, min and max values for use
     * in range visualizations.
     * 
     * @param x
     *            X-value of the item.
     * @param low
     *            Lower value for range visualization.
     * @param high
     *            Upper value for range visualization.
     */
    public DataSeriesItem(Number x, Number low, Number high) {
        setX(x);
        setLow(low);
        setHigh(high);
    }

    /**
     * Sets the given date as the x value.
     * <p>
     * Note, that internally the Date value is stored as an "epoch timestamp",
     * adjusted by timezone offset. So when calling {@link #getX()} you might
     * get a bit odd result.
     * 
     * @param date
     *            Date to set.
     */
    public void setX(Date date) {
        setX(Util.toHighchartsTS(date));
    }

    /**
     * Returns the name of the item.
     * 
     * @see #setName(String)
     * @return The name of the data item or null if not defined.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the data item as shown in the legend, tooltip, dataLabel
     * etc. Defaults to "".
     * 
     * @param name
     *            Name of the item.
     */
    public void setName(String name) {
        this.name = name;
        makeCustomized();
    }

    /**
     * Returns the Y-value of the item.
     * 
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
     *            Y-value of the item.
     */
    public void setY(Number y) {
        this.y = y;
    }

    /**
     * Returns the X-value of the item.
     * 
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
     *            X-value of the item.
     */
    public void setX(Number x) {
        this.x = x;
    }

    /**
     * Checks whether or not the item is sliced. Makes sense only in pie charts.
     * 
     * @see #setSliced(boolean)
     * @return <b>true</b> when this data item is displayed offset from the
     *         center in a pie chart, <b>false</b> otherwise.
     */
    public boolean getSliced() {
        return sliced;
    }

    /**
     * Sets whether to display a slice offset from the center. Defaults to
     * false.
     * 
     * <em>Note</em>: This applies to pie charts only.
     * 
     * @param sliced
     *            When <b>true</b>, this item should be displayed with a small
     *            offset from the centre of the pie chart; when <b>false</b>,
     *            this item will be rendered normally.
     */
    public void setSliced(boolean sliced) {
        this.sliced = sliced;
        makeCustomized();
    }

    /**
     * Checks whether or not the item is selected.
     * 
     * @see #setSelected(Boolean)
     * @return <b>true</b> if the item is selected, <b>false</b> otherwise.
     */
    public boolean isSelected() {
        return selected == null ? false : selected;
    }

    /**
     * Sets whether the data item is selected or not.
     * 
     * @param selected
     *            Whether or not the item should be selected.
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
        makeCustomized();
    }

    /**
     * Returns the color of the item.
     * 
     * @see #setColor(Color)
     * @return The color of the item.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the individual color for the point. Defaults to null. This might not
     * work for all chart types.
     * 
     * @param color
     *            Color of the item.
     */
    public void setColor(Color color) {
        this.color = color;
        makeCustomized();
    }

    /**
     * Returns the index of the legend. Applicable only to pie charts.
     * 
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
     *            Index in the legend.
     */
    public void setLegendIndex(Number legendIndex) {
        this.legendIndex = legendIndex;
        makeCustomized();
    }

    /**
     * Sets the marker of this data series item
     * 
     * @param marker
     *            Marker of the item.
     */
    public void setMarker(Marker marker) {
        this.marker = marker;
        makeCustomized();
    }

    /**
     * Returns the marker of the item.
     * 
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
     * Returns the id of the item.
     * 
     * @see #setId(String)
     * @return The ID of the item.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID for the point. This can be used after rendering to get a
     * reference to the point object. Defaults to null.
     * 
     * @param id
     *            New id.
     */
    public void setId(String id) {
        this.id = id;
        makeCustomized();
    }

    /**
     * Sets the dial or arrow pointer of the gauge.
     * 
     * <em>Note</em> This is only applicable for gauge charts.
     * 
     * @param dial
     *            Dial to use.
     */
    public void setDial(Dial dial) {
        this.dial = dial;
        makeCustomized();
    }

    /**
     * Returns the current dial. This is only applicable for gauge charts.
     * 
     * @see #setDial(Dial)
     * @return The dial or arrow pointer of a gauge chart. Only applicable for
     *         gauge charts.
     */
    public Dial getDial() {
        return dial;
    }

    /**
     * Checks if the data can be rendered in an optimized manner.
     * 
     * @return <b>true</b> if the data series item can be rendered in optimized
     *         manner, <b>false</b> otherwise.
     */
    public boolean isCustomized() {
        return customized;
    }

    /**
     * Marks the item as customized, so that it can be rendered in a more
     * optimal way.
     */
    protected void makeCustomized() {
        customized = true;
    }

    /**
     * Returns the lower range for visualizations.
     * 
     * @return The lower range.
     */
    public Number getLow() {
        return low;
    }

    /**
     * Sets the lower range for visualizations.
     * 
     * @param low
     *            New lower range.
     */
    public void setLow(Number low) {
        this.low = low;
    }

    /**
     * Returns the upper range for visualizations.
     * 
     * @return The upper range.
     */
    public Number getHigh() {
        return high;
    }

    /**
     * Sets the upper range for visualizations.
     * 
     * @param high
     *            New upper range.
     */
    public void setHigh(Number high) {
        this.high = high;
    }
}
