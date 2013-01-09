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

/**
 * Abstract base class for series
 */
public abstract class AbstractSeries extends AbstractConfigurationObject
        implements Series {

    private String name;
    private String stack;

    private AbstractPlotOptions plotOptions;

    private transient Configuration configuration;

    public AbstractSeries() {
    }

    /**
     * Constructs a named series
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
     * Gets the plot options related to this specific series. This is needed
     * e.g. in combined charts.
     * 
     * @return
     */
    public AbstractPlotOptions getPlotOptions() {
        return plotOptions;
    }

    /**
     * Sets the plot options for this specific series. The type of the plot
     * options also explicitly sets the chart type used when rendering this
     * particular series. If plot options is null the component wide chart type
     * is used.
     * <p>
     * Options that are not defined at this level will be inherited from chart
     * and theme level.
     * 
     * @param plotOptions
     */
    public void setPlotOptions(AbstractPlotOptions plotOptions) {
        this.plotOptions = plotOptions;
    }
}
