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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.addon.charts.model.DataSeriesEventListener.DataAddedEvent;
import com.vaadin.addon.charts.model.DataSeriesEventListener.DataRemovedEvent;
import com.vaadin.addon.charts.model.DataSeriesEventListener.DataUpdatedEvent;
import com.vaadin.addon.charts.model.DataSeriesEventListener.SeriesEnablationEvent;

/**
 * Chart's configuration root object containing all the child objects that are
 * used to configure chart, axes, legend, titles etc.
 */
public class Configuration extends AbstractConfigurationObject {

    private ChartModel chart;
    private Title title;
    private SubTitle subtitle;
    private AxisList xAxis;
    private AxisList yAxis;
    private Tooltip tooltip;
    private Legend legend;
    private Credits credits;
    private Map<String, AbstractPlotOptions> plotOptions;
    private HTMLLabels labels;

    private List<Series> series = new ArrayList<Series>();

    private PaneList pane;
    private Exporting exporting = new Exporting(false);
    private transient DataSeriesEventListener dataSeriesEventListener;

    /**
     * @see #setChart(ChartModel)
     */
    public ChartModel getChart() {
        if (chart == null) {
            chart = new ChartModel();
        }
        return chart;
    }

    /**
     * Sets options regarding the chart and plot areas as well as general chart
     * options.
     * 
     * @param chart
     */
    public void setChart(ChartModel chart) {
        this.chart = chart;
    }

    /**
     * @see #setSeries(List)
     */
    public List<Series> getSeries() {
        return Collections.unmodifiableList(series);
    }

    /**
     * Adds a single series to the list of series in this configuration.
     * 
     * @param series
     */
    public void addSeries(Series series) {
        this.series.add(series);
        series.setConfiguration(this);
    }

    /**
     * Sets the actual series to append to the chart. Series objects contains
     * the data content (individual points/columns etc.) of the plot. The series
     * object could have data (the content), name, labels, tooltips, markers
     * etc.
     * 
     * <br />
     * <br />
     * In addition to the attributes listed above, any member of the plotOptions
     * for that specific type of plot can be added to a series individually. For
     * example, even though a general lineWidth is specified in
     * AbstractPlotOptions, an individual lineWidth can be specified for each
     * series (e.g. to enable each series have different lineWidth).
     * 
     * @param series
     */
    public void setSeries(List<Series> series) {
        this.series = series;
        for (Series s : series) {
            s.setConfiguration(this);
        }
    }

    /**
     * @see #setSeries(List)
     * @param series
     */
    public void setSeries(Series... series) {
        setSeries(Arrays.asList(series));
    }

    /**
     * @see #setTitle(Title)
     */
    public Title getTitle() {
        if (title == null) {
            title = new Title();
        }
        return title;
    }

    /**
     * The main title of the chart.
     * 
     * @param title
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * Sets the new chart's main title to the given string
     * 
     * @param text
     *            Text of title
     */
    public void setTitle(String text) {
        title = new Title(text);
    }

    /**
     * @return The chart's subtitle
     */
    public SubTitle getSubTitle() {
        if (subtitle == null) {
            subtitle = new SubTitle();
        }
        return subtitle;
    }

    /**
     * Sets the subtitle to the given text
     * 
     * @param text
     *            Text of subtitle
     */
    public void setSubTitle(String text) {
        subtitle = new SubTitle(text);
    }

    /**
     * Sets the chart's subtitle
     * 
     * @param subTitle
     */
    public void setSubTitle(SubTitle subTitle) {
        subtitle = subTitle;
    }

    /**
     * Returns the X-axis or category axis. Normally this is the horizontal
     * axis, though if the chart is inverted this is the vertical axis. In case
     * of multiple axes defined, the first axis is returned. An axis will be
     * created if no axis is defined.
     * 
     * @return the X-axis or category axis.
     */
    public XAxis getxAxis() {

        if (xAxis == null) {
            xAxis = new AxisList();
        }

        if (xAxis.getNumberOfAxes() == 0) {
            xAxis.addAxis(new XAxis());
        }

        return (XAxis) xAxis.getAxis(0);
    }

    /**
     * @return the number of X-axes defined
     */
    public int getNumberOfxAxes() {
        if (xAxis == null) {
            return 0;
        } else {
            return xAxis.getNumberOfAxes();
        }
    }

    /**
     * @return The X-axis with the given index or null if the index is not valid
     */
    public XAxis getxAxis(int index) {
        if (index > 0 && xAxis != null && getNumberOfxAxes() > index) {
            return (XAxis) xAxis.getAxis(index);
        } else {
            return null;
        }
    }

    /**
     * Removes all defined X-axes
     */
    public void removexAxes() {
        xAxis = null;
    }

    /**
     * Adds an X-axis to the configuration
     * 
     * @param axis
     *            The X-Axis to add.
     * @see #getxAxis()
     * @see #getxAxes()
     */
    public void addxAxis(XAxis axis) {
        if (xAxis == null) {
            xAxis = new AxisList();
        }
        xAxis.addAxis(axis);
    }

    /**
     * Returns the Y-axis or value axis. Normally this is the vertical axis,
     * though if the chart is inverted this is the horizontal axis. In case
     * there are multiple axes defined (a list), only the first axis is
     * returned. If none exist, a Y-axis will be created.
     * 
     * @return The first Y-axis
     * @see #getyAxes()
     */
    public YAxis getyAxis() {
        if (yAxis == null) {
            yAxis = new AxisList();
        }

        if (yAxis.getNumberOfAxes() == 0) {
            yAxis.addAxis(new YAxis());
        }

        return (YAxis) yAxis.getAxis(0);
    }

    /**
     * @return The number of Y-axes defined
     */
    public int getNumberOfyAxes() {
        if (yAxis == null) {
            return 0;
        } else {
            return yAxis.getNumberOfAxes();
        }
    }

    /**
     * @return The X-axis with the given index or null if the index is not valid
     */
    public YAxis getyAxis(int index) {
        if (index > 0 && yAxis != null && getNumberOfyAxes() > index) {
            return (YAxis) yAxis.getAxis(index);
        } else {
            return null;
        }
    }

    /**
     * Removes all defined Y-axes
     */
    public void removeyAxes() {
        yAxis = null;
    }

    /**
     * Adds a Y-axis.
     * 
     * @param yAxis
     *            The Y-axis to add.
     * @see #getyAxes()
     * @see #getyAxis()
     */
    public void addyAxis(YAxis axis) {
        if (yAxis == null) {
            yAxis = new AxisList();
        }
        yAxis.addAxis(axis);
    }

    /**
     * @return A list of all defined Y-axes, null if none are found.
     */
    public AxisList getyAxes() {
        if (yAxis != null) {
            return yAxis;
        } else {
            return null;
        }
    }

    /**
     * @see #setTooltip(Tooltip)
     * @return the tooltip options for this chart. If none have been set, a new
     *         Tooltip object with the default options is created.
     */
    public Tooltip getTooltip() {
        if (tooltip == null) {
            tooltip = new Tooltip();
        }
        return tooltip;
    }

    /**
     * Sets the options for the tooltip that appears when the user hovers over a
     * series or point.
     * 
     * @param tooltip
     */
    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * @see #setCredits(Credits)
     */
    public Credits getCredits() {
        if (credits == null) {
            credits = new Credits();
        }
        return credits;
    }

    /**
     * Sets/changes the credits label that is added in the lower right corner of
     * the chart.
     * 
     * @param credits
     */
    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    /**
     * Disables the credits by setting a Credits object with the enabled
     * property set to false.
     */
    public void disableCredits() {
        Credits disabled = new Credits();
        disabled.setEnabled(false);
        setCredits(disabled);
    }

    /**
     * @see #setLegend(Legend)
     */
    public Legend getLegend() {
        if (legend == null) {
            legend = new Legend();
        }
        return legend;
    }

    /**
     * Sets the legend. The legend is a box containing a symbol and name for
     * each series item or point item in the chart.
     * 
     * @param legend
     */
    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    /**
     * Returns all plot options used by this configuration.
     * 
     * @see #setPlotOptions(AbstractPlotOptions)
     */
    public AbstractPlotOptions[] getAllPlotOptions() {
        return plotOptions.values().toArray(
                new AbstractPlotOptions[plotOptions.size()]);
    }

    /**
     * Sets component-wide default plot options.
     * <p>
     * If the component contains several chart types, this method can be called
     * with several plot option types. Subsequent calls with the same plot type
     * will replace previous options for that specific chart type i.e only the
     * latest options for each chart type is honored.
     * <p>
     * {@link PlotOptionsSeries} is a special plot options type that can be used
     * to define rules for all chart types.
     * 
     * @see AbstractPlotOptions
     * 
     * @param plotOptions
     */
    public void setPlotOptions(AbstractPlotOptions plotOptions) {
        addPlotOptions(plotOptions);
    }

    /**
     * Adds plot options
     * 
     * @see #setPlotOptions(AbstractPlotOptions)
     * 
     * @param plotOptions
     */
    private void addPlotOptions(AbstractPlotOptions plotOptions) {
        if (this.plotOptions == null) {
            this.plotOptions = new HashMap<String, AbstractPlotOptions>();
        }
        if (plotOptions instanceof PlotOptionsSeries) {
            this.plotOptions.put("series", plotOptions);
        } else {
            this.plotOptions.put(plotOptions.getChartType().toString(),
                    plotOptions);
        }
    }

    /**
     * @see #setLabels(HTMLLabels)
     * @return Labels or null if not defined
     */
    public HTMLLabels getLabels() {
        return labels;
    }

    /**
     * Sets HTML labels that can be positioned anywhere in the chart area.
     * 
     * @param labels
     */
    public void setLabels(HTMLLabels labels) {
        this.labels = labels;
    }

    /**
     * Sets whether to enable exporting
     * 
     * @param exporting
     * @see Exporting
     * @see #setExporting(Exporting)
     */
    public void setExporting(Boolean exporting) {
        this.exporting.setEnabled(exporting);
    }

    /**
     * Sets the exporting module settings.
     * 
     * @param exporting
     * @see Exporting
     */
    public void setExporting(Exporting exporting) {
        this.exporting = exporting;
    }

    /**
     * @see #setExporting(Exporting)
     */
    public Exporting getExporting() {
        return exporting;
    }

    /**
     * @see #setExporting(Boolean)
     */
    public Boolean isExporting() {
        return exporting.isEnabled();
    }

    /**
     * Auxiliary search method for finding point/DataSeriesItem with given
     * series name and X,Y coordinates
     * 
     * @param serieName
     * @param x
     * @param y
     * @return the data series item identified by the parameters.
     */
    public DataSeriesItem getDataSeriesItem(String serieName, double x, double y) {
        DataSeriesItem item = null;

        // go trough all series
        for (Series serie : series) {
            if (serieName == null || serieName.equals(serie.getName())) {
                item = ((DataSeries) serie).getData(x, y);
                if (item != null) {
                    return item;
                }
            }
        }

        return null;
    }

    /**
     * @see #addPane(Pane)
     */
    public Pane getPane() {
        if (pane == null) {
            pane = new PaneList();
        }

        if (pane.getNumberOfPanes() == 0) {
            pane.addPane(new Pane());
        }

        return pane.getPane(0);
    }

    /**
     * Adds a pane. This applies only to polar charts and angular gauges. This
     * configuration object holds general options for the combined X and Y -axes
     * set. Each XAxis or YAxis can reference the pane by index.
     * 
     * @param pane
     */
    public void addPane(Pane pane) {
        if (this.pane == null) {
            this.pane = new PaneList();
        }
        this.pane.addPane(pane);
    }

    /**
     * Reverses the ListSeries (transposes it such that categories would be
     * series names and vice versa) to help stacking
     * 
     * throws {@link IllegalStateException} if series are not ListSeries type
     */
    public void reverseListSeries() {
        List<Series> newSeries = new ArrayList<Series>();
        String[] newCategories = new String[series.size()];

        for (int j = 0; j < getxAxis().getCategories().length; j++) {

            String name = getxAxis().getCategories()[j];
            List<Number> numbers = new ArrayList<Number>();
            for (int i = 0; i < series.size(); i++) {
                if (series.get(i) instanceof ListSeries) {
                    numbers.add(((ListSeries) series.get(i)).getData()[j]);
                    newCategories[i] = series.get(i).getName();
                } else {
                    throw new IllegalStateException();
                }
            }
            newSeries.add(new ListSeries(name, numbers.toArray(new Number[1])));
        }
        series = newSeries;
        getxAxis().setCategories(newCategories);
    }

    /** Notifies listeners that a data point has been added */
    void fireDataAdded(Series series, Number value) {
        if (dataSeriesEventListener == null) {
            return;
        }
        dataSeriesEventListener.dataAdded(new DataAddedEvent(series, value));
    }

    /** Notifies listeners that a data point has been added 
     * @param shift */
    void fireDataAdded(Series series, DataSeriesItem item, boolean shift) {
        if (dataSeriesEventListener == null) {
            return;
        }
        dataSeriesEventListener.dataAdded(new DataAddedEvent(series, item, shift));
    }

    /** Notifies listeners that a data point has been removed */
    void fireDataRemoved(Series series, Number value) {
        if (dataSeriesEventListener == null) {
            return;
        }
        dataSeriesEventListener
                .dataRemoved(new DataRemovedEvent(series, value));
    }

    /** Notifies listeners that a data point has been removed */
    void fireDataRemoved(Series series, DataSeriesItem item) {
        if (dataSeriesEventListener == null) {
            return;
        }
        dataSeriesEventListener.dataRemoved(new DataRemovedEvent(series, item));
    }

    /** Notifies listeners that a data point has been updated */
    void fireDataUpdated(Series series, Number value, int pointIndex) {
        if (dataSeriesEventListener == null) {
            return;
        }
        dataSeriesEventListener.dataUpdated(new DataUpdatedEvent(series, value,
                pointIndex));
    }

    /** Notifies listeners that a data point has been updated */
    void fireDataUpdated(Series series, DataSeriesItem item, int pointIndex) {
        if (dataSeriesEventListener == null) {
            return;
        }
        dataSeriesEventListener.dataUpdated(new DataUpdatedEvent(series, item,
                pointIndex));
    }

    /** Notifies listeners that a series is enabled or disabled */
    void fireSeriesEnabled(Series series, boolean enabled) {
        if (dataSeriesEventListener == null) {
            return;
        }
        dataSeriesEventListener.seriesEnablation(new SeriesEnablationEvent(
                series, enabled));
    }

    /**
     * Sets the data series event listener.
     * <p>
     * This method is used internally by the library. Usage by the end user will
     * cause unexpected behavior.
     * 
     * @param listener
     * @deprecated This method is reserved for internal use only
     */
    public void setDataSeriesEventListener(DataSeriesEventListener listener) {
        dataSeriesEventListener = listener;
    }
}
