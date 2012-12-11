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

    private String name;
    private String stack;

    private AbstractPlotOptions plotOptions;

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

    /**
     * Gets plot options related to this specific series. This is needed in
     * eg.g. "combinded charts".
     * 
     * @return
     */
    public AbstractPlotOptions getPlotOptions() {
        return plotOptions;
    }

    /**
     * Sets plot options related to this specific series. This is needed in e.g.
     * "combined charts". The type of plot options fixes the type used rendering
     * this series.
     * 
     * @param plotOptions
     */
    public void setPlotOptions(AbstractPlotOptions plotOptions) {
        this.plotOptions = plotOptions;
    }
}
