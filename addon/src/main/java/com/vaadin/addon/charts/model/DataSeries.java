package com.vaadin.addon.charts.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.addon.charts.model.style.Color;

/**
 * An array of data points for the series. The points can be given in three
 * ways:
 * <ol>
 * <li>A list of numerical values (ListSeries). In this case, the numerical
 * values will be interpreted and y values, and x values will be automatically
 * calculated, either starting at 0 and incrementing by 1, or from pointStart
 * and pointInterval given in the plotOptions. If the axis is has categories,
 * these will be used. This option is not available for range series.
 * 
 * <li>
 * A list of arrays with two values. In this case, the first value is the x
 * value and the second is the y value. If the first value is a string, it is
 * applied as the name of the point, and the x value is incremented following
 * the above rules.
 * 
 * For range series, the arrays will be interpreted as {x, low, high}. In this
 * cases, the X value can be skipped altogether to make use of pointStart and
 * pointRange.
 * 
 * <li>
 * A list of object with named values. In this case the objects are point
 * configuration objects as seen below.
 * 
 * Range series values are given by low and high.
 * </ol>
 * 
 * 
 */
public class DataSeries extends AbstractSeries {

    private static final double TOLERANCE = 0.0001;
    private List<DataSeriesItem> data = new ArrayList<DataSeriesItem>();

    private Number xAxis;
    private Number yAxis;

    private Object[] center;
    private Boolean showInLegend;
    private Color color;
    private Boolean enableMouseTracking;
    private States states;

    /**
     * Default constructor
     */
    public DataSeries() {

    }

    /**
     * Constructs DataSeries with given ChartType
     * 
     * @param type
     */
    public DataSeries(ChartType type) {
        setType(type);
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
     * @see #setCenter(Object, Object)
     * 
     * @return
     */
    public Object[] getCenter() {
        return center;
    }

    /**
     * The center of the pie chart relative to the plot area. Can be percentages
     * or pixel values. Defaults to "50%", "50%". <br />
     * <br />
     * <b>This relevant only for ChartType.PIE</b>
     * 
     * @param left
     * @param top
     */
    public void setCenter(Object left, Object top) {
        Object[] array = null;
        if (left != null && top != null) {
            array = new Object[] { left, top };
        }
        center = array;
    }

    /**
     * @see #setShowInLegend(Boolean)
     * 
     * @return
     */
    public boolean isShowInLegend() {
        return showInLegend == null ? false : showInLegend;
    }

    /**
     * Whether to display this particular series or series type in the legend.
     * Defaults to false. <br />
     * <br />
     * <b>This relevant only for ChartType.PIE</b>
     * 
     * @param showInLegend
     */
    public void setShowInLegend(Boolean showInLegend) {
        this.showInLegend = showInLegend;
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
     * @see #setColor(Color)
     * 
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * The main color or the series. In line type series it applies to the line
     * and the point markers unless otherwise specified. In bar type series it
     * applies to the bars unless a color is specified per point. The default
     * value is pulled from the VaadinTheme's colors
     * 
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @see #setEnableMouseTracking(Boolean)
     * 
     * @return
     */
    public boolean isEnableMouseTracking() {
        return enableMouseTracking == null ? true : enableMouseTracking;
    }

    /**
     * Enable or disable the mouse tracking for a specific series. This includes
     * point tooltips and click events on graphs and points. For large datasets
     * it improves performance. Defaults to true.
     * 
     * @param enableMouseTracking
     */
    public void setEnableMouseTracking(Boolean enableMouseTracking) {
        this.enableMouseTracking = enableMouseTracking;
    }

    /**
     * @see #setStates(States)
     */
    public States getStates() {
        return states;
    }

    /**
     * A wrapper object for all the series options in specific states.
     * 
     * @param states
     */
    public void setStates(States states) {
        this.states = states;
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
