package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;

/**
 * Single entry of DataSeries
 */
public class DataSeriesItem extends AbstractConfigurationObject {
    protected String name;
    protected Number y;
    private Number x;
    private Boolean sliced;
    private Boolean selected;
    private Color color;
    private Number legendIndex;
    private Marker marker;
    private String id;
    private Dial dial;

    /**
     * Default constructor
     */
    public DataSeriesItem() {

    }

    /**
     * Constructs an item with name and y value
     * 
     * @param name
     * @param y
     */
    public DataSeriesItem(String name, Number y) {
        this.name = name;
        this.y = y;
    }

    /**
     * Constructs an item with a name and a value on the Y-axis using the
     * specified color.
     * 
     * @param name
     * @param y
     * @param color
     */
    public DataSeriesItem(String name, Number y, Color color) {
        this.name = name;
        this.y = y;
        setColor(color);
    }

    /**
     * Constructs an item with x and y values
     * 
     * @param x
     * @param y
     */
    public DataSeriesItem(Number x, Number y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs an item with numerical values for the X- and Y-axis using the
     * specified color.
     * 
     * @param x
     * @param y
     * @param color
     */
    public DataSeriesItem(Number x, Number y, Color color) {
        this.x = x;
        this.y = y;
        setColor(color);
    }

    /**
     * @see #setName(String)
     * @return Name of point or null if not defined
     */
    public String getName() {
        return name;
    }

    /**
     * The name of the point as shown in the legend, tooltip, dataLabel etc.
     * Defaults to "".
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @see #setY(Number)
     * @return
     */
    public Number getY() {
        return y;
    }

    /**
     * The y value of the point. Defaults to null.
     * 
     * @param y
     */
    public void setY(Number y) {
        this.y = y;
    }

    /**
     * @see #setX(Number)
     * @return
     */
    public Number getX() {
        return x;
    }

    /**
     * The x value of the point. Defaults to null.
     * 
     * @param x
     */
    public void setX(Number x) {
        this.x = x;
    }

    /**
     * @see #setSliced(Boolean)
     * @return Slided value or null if not defined
     */
    public boolean getSliced() {
        return sliced == null ? false : sliced;
    }

    /**
     * Pie series only. Whether to display a slice offset from the center.
     * Defaults to false.
     * 
     * @param sliced
     */
    public void setSliced(Boolean sliced) {
        this.sliced = sliced;
    }

    /**
     * @see #setSelected(Boolean)
     * @return
     */
    public boolean isSelected() {
        return selected == null ? false : selected;
    }

    /**
     * Whether the item (point) is selected or not.
     * 
     * @param selected
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    /**
     * @see #setColor(Color)
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * Individual color for the point. Defaults to null.
     * 
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @see #setLegendIndex(Number)
     * @return Legend index or null if not defined
     */
    public Number getLegendIndex() {
        return legendIndex;
    }

    /**
     * Pies only. The sequential index of the pie slice in the legend. Defaults
     * to undefined.
     * 
     * @param legendIndex
     */
    public void setLegendIndex(Number legendIndex) {
        this.legendIndex = legendIndex;
    }

    /**
     * The marker of single data series item
     * 
     * @param marker
     */
    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    /**
     * @see #setMarker(Marker)
     * @return
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
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * An id for the point. This can be used after render time to get a pointer
     * to the point object. Defaults to null.
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Dial or arrow pointer of the gauge.
     * 
     * @param dial
     */
    public void setDial(Dial dial) {
        this.dial = dial;
    }

    /**
     * @see #setDial(Dial)
     * @return
     */
    public Dial getDial() {
        return dial;
    }

}
