package com.vaadin.addon.charts.model;

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
public abstract class AbstractSeries extends AbstractConfigurationObject
        implements Series {

    private ChartType type;
    private String name;
    private String stack;
    private Labels dataLabels;
    private Tooltip tooltip;
    private PointPlacement pointPlacement;
    private Marker marker;
    private Object size;
    private Object innerSize;

    private transient Configuration configuration;

    /**
     * Default constructor
     */
    public AbstractSeries() {

    }

    /**
     * Constructor with given series name
     */
    public AbstractSeries(String name) {
        setName(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ChartType getType() {
        if (type == null) {
            return ChartType.LINE;
        } else {
            return type;
        }
    }

    @Override
    public void setType(ChartType type) {
        this.type = type;
    }

    /**
     * @see #setStack(String)
     * @return
     */
    public String getStack() {
        return stack;
    }

    /**
     * This option allows grouping series in a stacked chart. The stack option
     * can be a string or a number or anything else, as long as the grouped
     * series' stack options match each other. Defaults to null.
     * 
     * @param stack
     */
    public void setStack(String stack) {
        this.stack = stack;
    }

    /**
     * @see #setDataLabels(Labels)
     * @return DataLabels of null if not defined
     */
    public Labels getDataLabels() {
        return dataLabels;
    }

    /**
     * Individual data label for each point. The options are the same as the
     * ones for plotOptions.series.dataLabels
     * 
     * @param dataLabels
     */
    public void setDataLabels(Labels dataLabels) {
        this.dataLabels = dataLabels;
    }

    /**
     * @see #setTooltip(Tooltip)
     * @return
     */
    public Tooltip getTooltip() {
        if (tooltip == null) {
            tooltip = new Tooltip();
        }
        return tooltip;
    }

    /**
     * A configuration object for the tooltip rendering of each single series.
     * Properties are inherited from tooltip. Overridable properties are
     * headerFormat, pointFormat, valueDecimals, xDateFormat, valuePrefix and
     * valueSuffix. . Defaults to {}.
     * 
     * @param tooltip
     */
    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * Possible values: null, "on", "between".
     * 
     * In a column chart, when pointPlacement is "on", the point will not create
     * any padding of the X axis. In a polar column chart this means that the
     * first column points directly north. If the pointPlacement is "between",
     * the columns will be laid out between ticks. This is useful for example
     * for visualizing an amount between two points in time or in a certain
     * sector of a polar chart.
     * 
     * Defaults to null in Cartesian charts, "between" in polar charts.
     * 
     * @param pointPlacement
     */
    public void setPointPlacement(PointPlacement pointPlacement) {
        this.pointPlacement = pointPlacement;
    }

    /**
     * @see #setPointPlacement(PointPlacement)
     * @return
     */
    public PointPlacement getPointPlacement() {
        return pointPlacement;
    }

    /**
     * Series marker for whole series
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
        }
        return marker;
    }

    /**
     * @see #setSize(Object)
     * 
     * @return
     */
    public Object getSize() {
        return size;
    }

    /**
     * The diameter of the pie relative to the plot area. Can be a percentage or
     * pixel value. Pixel values are given as integers. Defaults to "75%". <br />
     * <br />
     * <b>This relevant only for ChartType.PIE</b>
     * 
     * @param size
     */
    public void setSize(Object size) {
        this.size = size;
    }

    /**
     * @see #setInnerSize(Object)
     * 
     * @return
     */
    public Object getInnerSize() {
        return innerSize;
    }

    /**
     * The size of the inner diameter for the pie. A size greater than 0 renders
     * a donut chart. Can be a percentage or pixel value. Percentages are
     * relative to the size of the plot area. Pixel values are given as
     * integers. Defaults to 0. <br />
     * <br />
     * <b>This relevant only for ChartType.PIE</b>
     * 
     * @param innerSize
     */
    public void setInnerSize(Object innerSize) {
        this.innerSize = innerSize;
    }

    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * @return the {@link Configuration} that this series is linked to.
     */
    public Configuration getConfiguration() {
        return configuration;
    }
}
