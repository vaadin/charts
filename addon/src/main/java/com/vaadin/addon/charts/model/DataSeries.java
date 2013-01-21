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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.vaadin.addon.charts.model.style.Color;

/**
 * An array of data points to be displayed in a chart.
 * <p>
 * The class uses {@link DataSeriesItem} to represent individual data points.
 * The class also has various helper methods and constructors that allow passing
 * data as arrays or lists.
 * 
 * @see ListSeries
 * @see RangeSeries
 */
public class DataSeries extends AbstractSeries {

    private static final double TOLERANCE = 0.0001;
    private List<DataSeriesItem> data = new ArrayList<DataSeriesItem>();

    /**
     * Constructs an empty {@link DataSeries}. Developers should then populate
     * the series with various addData and setData methods.
     */
    public DataSeries() {
    }

    /**
     * Constructs a DataSeries instance containing the given category name, Y
     * value pairs.
     * 
     * @param categories
     * @param ys
     */
    public DataSeries(String[] categories, Number[] ys) {
        for (int i = 0; i < categories.length; i++) {
            add(new DataSeriesItem(categories[i], ys[i]));
        }
    }

    /**
     * Constructs a new DataSeries instance with the given items.
     * 
     * @param items
     *            items to be contained in the constructed DataSeries
     */
    public DataSeries(List<DataSeriesItem> items) {
        setData(items);
    }

    /**
     * Constructs a new DataSeries with the given items.
     * 
     * @param items
     *            items to be contained in the constructed DataSeries
     */
    public DataSeries(DataSeriesItem... items) {
        setData(Arrays.asList(items));
    }

    /**
     * Adds a list of (x,y) data pairs
     * 
     * e.g. <code>[[0, 15], [10, -50], [20, -56.5]...</code>
     * 
     * could be inserted as follows
     * 
     * <code>new Number[][] { { 0, 15 }, { 10, -50 }, { 20, -56.5 }</code>
     * 
     * @param entries
     *            An array of Numbers representing the (x,y) data pairs.
     */
    public void addData(Number[][] entries) {
        for (Number[] entry : entries) {
            data.add(new DataSeriesItem(entry[0], entry[1]));
        }
    }

    /**
     * Sets the data entries, first clearing the old ones. Uses the given
     * category names and numeric values.
     * 
     * The categoryNames and values arrays must be of equal length.
     * 
     * @param categoryNames
     *            An array of the category names.
     * @param values
     *            An array of the values for each category in the categoryNames
     *            parameter.
     */
    public void setData(String[] categoryNames, Number[] values) {
        assert (categoryNames.length == values.length);
        data.clear();
        for (int i = 0; i < categoryNames.length; i++) {
            data.add(new DataSeriesItem(categoryNames[i], values[i]));
        }
    }

    /**
     * Sets the data entries, first clearing the old ones. Uses the given
     * category names, numeric values, and colors.
     * 
     * The categoryNames, values and colors arrays must be of equal length.
     * 
     * @param categoryNames
     *            An array of the category names.
     * @param values
     *            An array of the values for each category in the categoryNames
     *            parameter.
     * @param colors
     *            An array of colors for each category name, value pair.
     */
    public void setData(String[] categoryNames, Number[] values, Color[] colors) {
        assert (categoryNames.length == values.length);
        assert (categoryNames.length == colors.length);
        data.clear();
        for (int i = 0; i < categoryNames.length; i++) {
            DataSeriesItem item = new DataSeriesItem(categoryNames[i],
                    values[i]);
            item.setColor(colors[i]);
            data.add(item);
        }
    }

    /**
     * Sets the data entries, first clearing the old ones. Uses the same numeric
     * value for names (value.toString) and Y-values.
     * 
     * @param values
     */
    public void setData(Number... values) {
        data.clear();
        for (int i = 0; i < values.length; i++) {
            data.add(new DataSeriesItem("" + values[i], values[i]));
        }
    }

    /**
     * Sets the data to the provided list of data items.
     * 
     * @param data
     */
    public void setData(List<DataSeriesItem> data) {
        this.data = data;
    }

    /**
     * @param name
     *            The name of the data item to find.
     * @return The first {@link DataSeriesItem} identified by the specified
     *         name. Returns null if no matching item is found.
     */
    public DataSeriesItem get(String name) {
        for (DataSeriesItem item : data) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Returns the first data series item for which x and y match the given
     * values within a tolerance of {@value #TOLERANCE}. Returns null if no
     * matching item is found.
     * 
     * @param x
     *            The X value of the item to find.
     * @param y
     *            The Y value of the item to find.
     * @return The first matching data series item.
     */
    public DataSeriesItem get(Number x, Number y) {
        for (DataSeriesItem item : data) {

            if (Math.abs(x.doubleValue() - item.getX().doubleValue()) < TOLERANCE
                    && Math.abs(y.doubleValue() - item.getY().doubleValue()) < TOLERANCE) {
                return item;
            }
        }
        return null;
    }

    /**
     * Adds a data item and immediately updates the chart if it already has been
     * drawn. If the chart has not yet been drawn, all items added will be added
     * to the chart when the chart is drawn.
     * 
     * @see #add(DataSeriesItem, boolean, boolean)
     * @param item
     *            the data item to be added
     */
    public void add(DataSeriesItem item) {
        add(item, true, false);
    }

    /**
     * Adds a data item and immediately sends an update to the chart if so
     * specified. Immediately updating the chart causes it to dynamically add
     * the data point.
     * <p>
     * This method is useful if you want to add many items without a
     * client/server round-trip for each item added. Do this by setting the
     * updateChartImmediately parameter to false.
     * 
     * @param item
     *            The item to add.
     * @param updateChartImmediately
     *            Updates the chart immediately if true.
     * @param shift
     *            If true, the first item from the series is removed. Handy if
     *            dynamically adjusting adding points and fixed amount of points
     *            should be kept visible.
     */
    public void add(DataSeriesItem item, boolean updateChartImmediately,
            boolean shift) {
        if(shift) {
            data.remove(0);
        }
        data.add(item);
        if (updateChartImmediately && getConfiguration() != null) {
            getConfiguration().fireDataAdded(this, item, shift);
        }
    }

    /**
     * Removes a given item and immediately removes it from the chart.
     * 
     * @param item
     *            The item to remove.
     */
    public void remove(DataSeriesItem item) {
        data.remove(item);
        if (getConfiguration() != null) {
            getConfiguration().fireDataRemoved(this, item);
        }
    }

    /**
     * Return an unmodifiable list of the data items in this series.
     */
    protected List<DataSeriesItem> getData() {
        return Collections.unmodifiableList(data);
    }

    /**
     * Triggers an update of the chart for the specified data item. Only the Y
     * value of the DataSeriesItem is updated.
     * 
     * @param item
     *            The item to update.
     */
    public void update(DataSeriesItem item) {
        if (getConfiguration() != null) {
            getConfiguration().fireDataUpdated(this, item.getY(),
                    data.indexOf(item));
        }
    }

    /**
     * Returns {@link DataSeriesItem} at given index
     * 
     * @param index
     * @return the Item
     * @throws IndexOutOfBoundsException
     *             if data series don't have item at given index
     */
    public DataSeriesItem get(int index) {
        return data.get(index);
    }

    /**
     * @return the number of data items in the series
     */
    public int size() {
        return data.size();
    }
}
