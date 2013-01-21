package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.Chart;

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

    private boolean visible = true;

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
     * particular series. If plot options is null, the component wide chart type
     * is used.
     * <p>
     * Options that are not defined at this level will be inherited from the
     * chart and theme levels.
     * 
     * @param plotOptions
     */
    public void setPlotOptions(AbstractPlotOptions plotOptions) {
        this.plotOptions = plotOptions;
    }

    /**
     * Control the visibility of the series. Although the series is invisible in
     * the client it is still "cached" there and thus setting it visible happens
     * quickly.
     * 
     * @see #setVisible(boolean, boolean)
     * 
     * @param visible
     *            true if the series should be displayed, false if not
     */
    public void setVisible(boolean visible) {
        setVisible(visible, true);
    }

    /**
     * Control the visibility of the series.
     * <p>
     * With this version of the method developer can disable immediate chart
     * update for already rendered chart, if e.g. multiple changes to the chart
     * configuration are wished to be applied at once.
     * 
     * @see #setVisible(boolean)
     * @see Chart#drawChart()
     * 
     * @param visible
     *            true if the series should be displayed, false if not
     * @param updateChartImmediately
     *            Updates the chart immediately if true.
     */
    public void setVisible(boolean visible, boolean updateChartImmediately) {
        boolean doDynamicChange = updateChartImmediately
                && getConfiguration() != null && this.visible != visible;
        this.visible = visible;
        if (doDynamicChange) {
            getConfiguration().fireSeriesEnabled(this, visible);
        }

    }
    
    /**
     * @return true if the series is displayed on the client
     */
    public boolean isVisible() {
        return visible;
    }

}
