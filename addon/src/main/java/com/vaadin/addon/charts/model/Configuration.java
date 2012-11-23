package com.vaadin.addon.charts.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.vaadin.addon.charts.model.DataSeriesEventListener.DataAddedEvent;
import com.vaadin.addon.charts.model.DataSeriesEventListener.DataRemovedEvent;
import com.vaadin.addon.charts.model.DataSeriesEventListener.DataUpdatedEvent;

/**
 * Chart's configuration root object containing all the child objects that are
 * used to configure chart, axes, legend, titles etc.
 */
public class Configuration extends AbstractConfigurationObject {

    private Lang lang;
    private ChartModel chart;
    private Title title;
    private SubTitle subtitle;
    private AxisList xAxis;
    private AxisList yAxis;
    private Tooltip tooltip;
    private Legend legend;
    private Credits credits;
    private AbstractPlotOptionsList plotOptions;
    private HTMLLabels labels;

    private List<Series> series = new ArrayList<Series>();

    private Boolean exporting;
    private PaneList pane;

    private transient DataSeriesEventListener dataSeriesEventListener;

    /**
     * @see #setChart(ChartModel)
     * @return
     */
    public ChartModel getChart() {
        if (chart == null) {
            chart = new ChartModel();
        }
        return chart;
    }

    /**
     * Options regarding the chart area and plot area as well as general chart
     * options.
     * 
     * @param chart
     */
    public void setChart(ChartModel chart) {
        this.chart = chart;
    }

    /**
     * @see #setSeries(List)
     * @return
     */
    public List<Series> getSeries() {
        return Collections.unmodifiableList(series);
    }

    /**
     * Add a single series to the list of series in this configuration.
     * 
     * @param series
     */
    public void addSeries(Series series) {
        this.series.add(series);
        series.setConfiguration(this);
    }

    /**
     * The actual series to append to the chart. In addition to the members
     * listed below, any member of the plotOptions for that specific type of
     * plot can be added to a series individually. For example, even though a
     * general lineWidth is specified in plotOptions.series, an individual
     * lineWidth can be specified for each series.
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
     * @return
     */
    public Title getTitle() {
        if (title == null) {
            title = new Title();
        }
        return title;
    }

    /**
     * The chart's main title.
     * 
     * @param title
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * Set the new chart's main title to the given string
     * 
     * @param text
     *            Text of title
     */
    public void setTitle(String text) {
        title = new Title(text);
    }

    /**
     * The chart's subtitle
     * 
     * @return
     */
    public SubTitle getSubTitle() {
        if (subtitle == null) {
            subtitle = new SubTitle();
        }
        return subtitle;
    }

    /**
     * Set new subtitle with given text
     * 
     * @param text
     *            Text of subtitle
     */
    public void setSubTitle(String text) {
        subtitle = new SubTitle(text);
    }

    /**
     * Set the chart's subtitle
     * 
     * @param subTitle
     */
    public void setSubTitle(SubTitle subTitle) {
        subtitle = subTitle;
    }

    /**
     * The X axis or category axis. Normally this is the horizontal axis, though
     * if the chart is inverted this is the vertical axis. In case of multiple
     * axes defined, the first axis is returned. Will create Axis if no axis
     * defined.
     * 
     * @return
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
     * Get number of X Axes defined
     * 
     * @return
     */
    public int getNumberOfxAxes() {
        if (xAxis == null) {
            return 0;
        } else {
            return xAxis.getNumberOfAxes();
        }
    }

    /**
     * Get axis with given index
     * 
     * @return X axis with given index or null if index is not valid
     */
    public XAxis getxAxis(int index) {
        if (index > 0 && xAxis != null && getNumberOfxAxes() > index) {
            return (XAxis) xAxis.getAxis(index);
        } else {
            return null;
        }
    }

    /**
     * Remove all X axes defined
     */
    public void removexAxes() {
        xAxis = null;
    }

    /**
     * Add axis to configuration
     * 
     * @param axis
     *            X Axis set as single X axis of configuration.
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
     * The Y axis or value axis. Normally this is the vertical axis, though if
     * the chart is inverted this is the horizontal axis. In case of multiple
     * axes (list) the first axis is returned.
     * 
     * @return first y axis
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
     * Get number of Y Axes defined
     * 
     * @return
     */
    public int getNumberOfyAxes() {
        if (yAxis == null) {
            return 0;
        } else {
            return yAxis.getNumberOfAxes();
        }
    }

    /**
     * Get axis with given index
     * 
     * @return X axis with given index or null if index is not valid
     */
    public YAxis getyAxis(int index) {
        if (index > 0 && yAxis != null && getNumberOfyAxes() > index) {
            return (YAxis) yAxis.getAxis(index);
        } else {
            return null;
        }
    }

    /**
     * Remove all Y axes defined
     */
    public void removeyAxes() {
        yAxis = null;
    }

    /**
     * Set Y axis.
     * 
     * @param yAxis
     *            Y Axis or multiple Y Axis. Only YAxes accepted!
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
     * Get Y axes if list is defined. Will return null else.
     * 
     * @return Y axes list if defined, null if not. Also single axis will return
     *         null.
     */
    public AxisList getyAxes() {
        if (yAxis != null && yAxis instanceof AxisList) {
            return yAxis;
        } else {
            return null;
        }
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
     * Options for the tooltip that appears when the user hovers over a series
     * or point.
     * 
     * @param tooltip
     */
    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * @see #setCredits(Credits)
     * @return
     */
    public Credits getCredits() {
        if (credits == null) {
            credits = new Credits();
        }
        return credits;
    }

    /**
     * Highchart by default puts a credits label in the lower right corner of
     * the chart. This can be changed using these options.
     * 
     * @param credits
     */
    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    /**
     * Disables the credits by adding disabled credit to configure.
     */
    public void disableCredits() {
        Credits disabled = new Credits();
        disabled.setEnabled(false);
        setCredits(disabled);
    }

    /**
     * @see #setLegend(Legend)
     * @return
     */
    public Legend getLegend() {
        if (legend == null) {
            legend = new Legend();
        }
        return legend;
    }

    /**
     * The legend is a box containing a symbol and name for each series item or
     * point item in the chart.
     * 
     * @param legend
     */
    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    /**
     * @see #setPlotOptions(AbstractPlotOptions)
     * @return
     */
    public AbstractPlotOptions getPlotOptions() {
        if (plotOptions == null) {
            plotOptions = new AbstractPlotOptionsList();
        }
        if (plotOptions.getNumberOfPlotOptions() == 0) {
            return null;
        }
        return plotOptions.getPlotOptions(0);
    }

    /**
     * Configuration options for the series are given in three levels. Options
     * for all series in a chart are given in the PlotOptionsSeries object. Then
     * options for all series of a specific type are given in the PlotOptions of
     * that type, for example PlotOptionsLine. Next, options for one single
     * series are given in the series array.
     * 
     * @param plotOptions
     */
    public void setPlotOptions(AbstractPlotOptions plotOptions) {
        if (this.plotOptions == null) {
            this.plotOptions = new AbstractPlotOptionsList();
        } else {
            this.plotOptions.clear();
        }
        this.plotOptions.addPlotOptions(plotOptions);
    }

    /**
     * Adds plot options
     * 
     * @see #setPlotOptions(AbstractPlotOptions)
     * 
     * @param plotOptions
     */
    public void addPlotOptions(AbstractPlotOptions plotOptions) {
        if (this.plotOptions == null) {
            this.plotOptions = new AbstractPlotOptionsList();
        }
        this.plotOptions.addPlotOptions(plotOptions);
    }

    /**
     * Returns plot options from given index
     * 
     * @return
     */
    public AbstractPlotOptions getPlotOptions(int index) {
        return plotOptions.getPlotOptions(index);
    }

    /**
     * @see #setLabels(HTMLLabels)
     * @return Labels or null if not defined
     */
    public HTMLLabels getLabels() {
        return labels;
    }

    /**
     * HTML labels that can be positioned anywhere in the chart area.
     * 
     * @param labels
     */
    public void setLabels(HTMLLabels labels) {
        this.labels = labels;
    }

    /**
     * Whether to enable the exporting module. Defaults to true.
     * 
     * @param exporting
     */
    public void setExporting(Boolean exporting) {
        this.exporting = exporting;
    }

    /**
     * @see #setExporting(Boolean)
     * @return
     */
    public boolean isExporting() {
        return exporting == null ? true : exporting;
    }

    /**
     * Auxiliary search method for finding point/DataSeriesItem with given
     * series name and xy coordinates
     * 
     * @param serieName
     * @param x
     * @param y
     * @return
     */
    public DataSeriesItem getDataSeriesItem(String serieName, double x, double y) {
        DataSeriesItem item = null;

        // go trough all series
        for (Series serie : series) {
            if (serieName == null || serieName.equals(serie.getName())) {
                item = ((DataSeries) serie).getDataSeriesItem(x, y);
                if (item != null) {
                    return item;
                }
            }
        }

        return null;
    }

    /**
     * @see #setLang(Lang)
     * @return
     */
    public Lang getLang() {
        return lang;
    }

    /**
     * Language object. The language object is global and it can't be set on
     * each chart initiation. Instead, use Highcharts.setOptions to set it
     * before any chart is initiated.
     * 
     * @param lang
     */
    public void setLang(Lang lang) {
        this.lang = lang;
    }

    /**
     * @see #addPane(Pane)
     * @return
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
     * Applies only to polar charts and angular gauges. This configuration
     * object holds general options for the combined X and Y axes set. Each
     * xAxis or yAxis can reference the pane by index.
     * 
     * @param pane
     */
    public void addPane(Pane pan) {
        if (pane == null) {
            pane = new PaneList();
        }
        pane.addPane(pan);
    }

    /**
     * Reverses ListSeries (makes a transpose such that categories would be
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

    /** Notifies listeners that a data point has been added */
    void fireDataAdded(Series series, DataSeriesItem item) {
        if (dataSeriesEventListener == null) {
            return;
        }
        dataSeriesEventListener.dataAdded(new DataAddedEvent(series, item));
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

    /**
     * Add data series event listener
     * 
     * @param listener
     */
    public void setDataSeriesEventListener(DataSeriesEventListener listener) {
        dataSeriesEventListener = listener;
    }
}
