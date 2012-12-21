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
import java.util.List;

import com.vaadin.addon.charts.model.style.Color;

/**
 * An array of data points for the series.
 * <p>
 * The class uses {@link DataSeriesItem} to represent individual data entry. The
 * class also has various helper methods and constructors that allow passing
 * data as arrays and lists.
 * 
 * @see ListSeries
 * @see RangeSeries
 */
public class DataSeries extends AbstractSeries {

    private static final double TOLERANCE = 0.0001;
    private List<DataSeriesItem> data = new ArrayList<DataSeriesItem>();

    private Number xAxis;
    private Number yAxis;

    /**
     * Constructs an empty {@link DataSeries}. Developers should then populate
     * the series with varios addData and setData methods.
     */
    public DataSeries() {

    }

    /**
     * Constructs DataSeries using category names and y-values as input
     * 
     * @param categories
     * @param ys
     */
    public DataSeries(String[] categories, Number[] ys) {
        for (int i = 0; i < categories.length; i++) {
            addData(new DataSeriesItem(categories[i], ys[i]));
        }
    }

    /**
     * Constructs a new DataSeries with given items.
     * 
     * @param dataSeries
     *            items to be contained in DataSeries
     */
    public DataSeries(List<DataSeriesItem> dataSeries) {
        setData(dataSeries);
    }

    /**
     * Constructs a new DataSeries with given items.
     * 
     * @param dataSeries
     *            items to be contained in DataSeries
     */
    public DataSeries(DataSeriesItem... dataSeries) {
        setData(Arrays.asList(dataSeries));
    }

    /**
     * Shortcut way to add list of (x,y) data pairs<br />
     * &nbsp;&nbsp;&nbsp;e.g. [[0, 15], [10, -50], [20, -56.5]... <br />
     * could be inserted as follows<br />
     * &nbsp;&nbsp;&nbsp;new Number[][] { { 0, 15 }, { 10, -50 }, { 20, -56.5 }
     * 
     * @param entries
     */
    public void addData(Number[][] entries) {
        for (Number[] entry : entries) {
            data.add(new DataSeriesItem(entry[0], entry[1]));
        }
    }

    /**
     * Set data entries, first clearing the old ones, uses given category names
     * and numeric entries
     * 
     * @param mainCategories
     * 
     * @param entries
     */
    public void setData(String[] mainCategories, Number[] entries) {
        assert (mainCategories.length == entries.length);
        data.clear();
        for (int i = 0; i < mainCategories.length; i++) {
            data.add(new DataSeriesItem(mainCategories[i], entries[i]));
        }
    }

    /**
     * Set data entries, first clearing the old ones, uses given category names
     * and numeric entries, and colors
     * 
     * @param mainCategories
     * @param entries
     * @param colors
     */
    public void setData(String[] mainCategories, Number[] entries,
            Color[] colors) {
        assert (mainCategories.length == entries.length);
        assert (mainCategories.length == colors.length);
        data.clear();
        for (int i = 0; i < mainCategories.length; i++) {
            DataSeriesItem item = new DataSeriesItem(mainCategories[i],
                    entries[i]);
            item.setColor(colors[i]);
            data.add(item);
        }
    }

    /**
     * Set data entries, first clearing the old ones, uses same numeric values
     * for names and y:s
     * 
     * @param entries
     */
    public void setData(Number... numericdata) {
        data.clear();
        for (int i = 0; i < numericdata.length; i++) {
            data.add(new DataSeriesItem("" + numericdata[i], numericdata[i]));
        }
    }

    /**
     * Sets whole array of data
     * 
     * @param data
     */
    public void setData(List<DataSeriesItem> data) {
        this.data = data;
    }

    /**
     * returns first {@link DataSeriesItem} with matching name returns null if
     * non is found
     * 
     * @param name
     */
    public DataSeriesItem getDataSeriesItem(String name) {
        for (DataSeriesItem item : data) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Return first data series item which x and y match given parameters,
     * returns null if non is found
     * 
     * @param x
     * @param y
     * @return
     */
    public DataSeriesItem getDataSeriesItem(Number x, Number y) {
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
     * @param item
     */
    public void addData(DataSeriesItem item) {
        addData(item, true);
    }

    /**
     * Adds a data item and optionally immediately sends an update to the chart,
     * which causes the chart to dynamically add the data point.
     * 
     * Calling this method with false for the second parameter is useful if you
     * want to add many items without a client/server round-trip for each item
     * added.
     * 
     * @param item
     * @param updateChartImmediately
     *            automatically updates the chart if true
     */
    public void addData(DataSeriesItem item, boolean updateChartImmediately) {
        data.add(item);
        if (updateChartImmediately && getConfiguration() != null) {
            getConfiguration().fireDataAdded(this, item);
        }
    }

    /**
     * Removes a given item and immediately removes it from the chart
     * 
     * @param item
     */
    public void removeData(DataSeriesItem item) {
        data.remove(item);
        if (getConfiguration() != null) {
            getConfiguration().fireDataRemoved(this, item);
        }
    }

    /**
     * @see #setxAxis(Number)
     * @return X Axis number or null if not defined.
     */
    public Number getxAxis() {
        return xAxis;
    }

    /**
     * When using dual or multiple x axes, this number defines which xAxis the
     * particular series is connected to. It refers to the index of the axis in
     * the xAxis array, with 0 being the first. Defaults to 0.
     * 
     * @param xAxis
     */
    public void setxAxis(Number xAxis) {
        this.xAxis = xAxis;
    }

    /**
     * @see #setyAxis(Number)
     * @return Y Axis number or null if not defined
     */
    public Number getyAxis() {
        return yAxis;
    }

    /**
     * When using dual or multiple y axes, this number defines which yAxis the
     * particular series is connected to. It refers to the index of the axis in
     * the yAxis array, with 0 being the first. Defaults to 0.
     * 
     * @param yAxis
     */
    public void setyAxis(Number yAxis) {
        this.yAxis = yAxis;
    }

    /**
     * For internal use only, returns internal data
     * 
     * @return
     */
    protected List<DataSeriesItem> getData() {
        return data;
    }

    /**
     * For internal use only, return y-values only
     * 
     * @return
     */
    protected Number[] getNumericData() {
        Number[] nums = new Number[data.size()];
        for (int i = 0; i < data.size(); i++) {
            nums[i] = data.get(i).getY();
        }
        return nums;
    }

    /**
     * Triggers an update of the chart for the specified data item. Only the Y
     * value of the DataSeriesItem is updated.
     * 
     * @param item
     */
    public void updateData(DataSeriesItem item) {
        if (getConfiguration() != null) {
            getConfiguration().fireDataUpdated(this, item.getY(),
                    getData().indexOf(item));
        }
    }
}
