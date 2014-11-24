package com.vaadin.addon.timeline.gwt.client;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.timeline.gwt.canvas.client.Canvas;
import com.vaadin.addon.timeline.gwt.client.VCanvasPlotter.Graph;
import com.vaadin.addon.timeline.gwt.client.VCanvasPlotter.PlottingListener;
import com.vaadin.addon.timeline.gwt.client.VCanvasPlotter.Point;
import com.vaadin.addon.timeline.gwt.client.VTimelineDisplay.PlotMode;
import com.vaadin.client.VConsole;

/**
 * Abstract base class for Timeline components which utilizes the canvas for
 * plotting
 * 
 * @since 1.2.4
 * 
 */
public abstract class VTimelineCanvasComponent extends Widget {

    protected abstract Canvas getCanvas();

    protected abstract Date getSelectionStartDate();

    protected abstract Date getSelectionEndDate();

    protected abstract float[] getVerticalScaleMinAndMax();

    protected abstract float calculateVerticalZero(float yUnit,
            float canvasHeight);

    protected abstract PlotMode getPlotMode();

    protected abstract void setCurrentGraphs(List<Graph> graphs);

    protected abstract String getFillColors(int graphIndex);

    protected abstract String getColors(int graphIndex);

    private float currentZeroCoordinate = 0f;

    private boolean currentlyRendering = false;

    private VTimelineWidget widget;

    protected List<HandlerRegistration> handlers = new LinkedList<HandlerRegistration>();

    /**
     * Constructor
     * 
     * @param widget
     *            The root timeline composite
     */
    public VTimelineCanvasComponent(VTimelineWidget widget) {
        this.widget = widget;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.Widget#onLoad()
     */
    @Override
    protected void onLoad() {
        super.onLoad();

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.Widget#onUnload()
     */
    @Override
    protected void onUnload() {
        super.onUnload();
        for (HandlerRegistration handler : handlers) {
            handler.removeHandler();
        }
        handlers.clear();
    }

    /**
     * Returns the y-coordinate in pixels where the zero line should be drawn
     * 
     * @return
     */
    protected float getCurrentZeroCoordinate() {
        return currentZeroCoordinate;
    }

    /**
     * The horizontal pixel to value ratio
     */
    protected float getCanvasXUnit() {
        Long startTime = getSelectionStartDate().getTime();
        Long endTime = getSelectionEndDate().getTime();
        Long timeDiff = endTime - startTime;
        Float canvasWidth = new Float(getCanvas().getWidth());
        float xUnit = canvasWidth / timeDiff.floatValue();
        return xUnit;
    }

    /**
     * The vertical pixel to value ratio
     */
    protected float getCanvasYUnit() {
        Float canvasHeight = new Float(getCanvas().getHeight());
        float[] startAndEnd = getVerticalScaleMinAndMax();
        float scaleStart = startAndEnd[0];
        float scaleEnd = startAndEnd[1];
        Float diff = Math.abs(scaleEnd - scaleStart);
        float yUnit = canvasHeight / diff;
        return yUnit;
    }

    /**
     * Is the plotter currently plotting a graph
     */
    protected boolean isCurrentlyRendering() {
        return currentlyRendering;
    }

    /**
     * Plots graphs onto the canvas using a plotter
     * 
     * @param plotter
     *            The plotter to use to plot the graph with
     * @param pointValues
     *            The values (y-axis) to plot onto the canvas
     * @param pointDates
     *            The dates (x-axis) to plot onto the canvas
     */
    protected void plotData(final VCanvasPlotter plotter,
            final Map<Integer, List<Float>> pointValues,
            final Map<Integer, List<Date>> pointDates) {

        currentlyRendering = true;

        // Wrap current plot listener
        final PlottingListener listener = plotter.getListener();
        plotter.setListener(new PlottingListener() {
            @Override
            public void plottingStarts() {
                listener.plottingStarts();
            }

            @Override
            public void plottingEnds() {
                listener.plottingEnds();
                currentlyRendering = false;
            }
        });

        // Calculate some needed values
        Float canvasHeight = new Float(getCanvas().getHeight());
        Long startTime = getSelectionStartDate().getTime();
        Long endTime = getSelectionEndDate().getTime();

        Long timeDiff = endTime - startTime;

        // Ensure we have something to plot
        if (timeDiff <= 0) {
            currentlyRendering = false;
            VConsole.error("Could not plot time range "
                    + getSelectionStartDate() + " - " + getSelectionEndDate());
            return;
        }

        // Get scales
        Float xUnit = getCanvasXUnit();
        Float yUnit = getCanvasYUnit();

        // Plot the vertical scale
        currentZeroCoordinate = calculateVerticalZero(yUnit, canvasHeight);

        // Set the zero coordinate
        plotter.setZeroLevel(currentZeroCoordinate);

        // Create the rendering graphs
        List<Graph> graphs = new ArrayList<Graph>();
        for (int g = 0; g < widget.getNumGraphs(); g++) {
            Graph graph = new Graph();

            // Set Fill color
            String fillcolors = getFillColors(g);
            if (fillcolors != null) {
                graph.setFillColor(fillcolors);
            } else {
                VConsole.error("Could not plot graph " + g
                        + " since fill color is missing");
                continue;
            }

            // Set color
            String colors = getColors(g);
            if (colors != null) {
                graph.setColor(colors);
            } else {
                VConsole.error("Could not plot graph " + g
                        + " since color is missing");
                continue;
            }

            // Set line thickness
            graph.setLineThickness(getLineThickness(g));

            // Set line caps
            graph.setLinecaps(getLineCaps(g));

            // Normalize values
            List<Float> normalizedValues = pointValues.get(g);

            // Add points to graph
            List<Date> dates = pointDates.get(g);
            if (dates != null && dates.size() > 0) {
                Date startDate = dates.get(0);
                Long timeFromStart = startDate.getTime() - startTime;
                float lastX = 0;
                int lastWidth = 0;
                for (int i = 0; i < normalizedValues.size(); i++) {
                    Float value = normalizedValues.get(i);
                    Date date = dates.get(i);
                    timeFromStart = date.getTime() - startTime;

                    float y = currentZeroCoordinate - value * yUnit;
                    float x = timeFromStart * xUnit;

                    Point p;
                    if (normalizedValues.size() == 1) {
                        /*
                         * Special behaviour for a graph with one point since we
                         * cannot calculate the width for it
                         */
                        p = new Point(Math.round(x), Math.round(y), graph, 1,
                                value);
                        lastWidth = 1;

                    } else if (i == 0) {
                        /*
                         * Setting width for the first point to zero
                         */
                        Date d = dates.get(1);
                        timeFromStart = d.getTime() - startTime;
                        lastWidth = 0;
                        p = new Point(Math.round(x), Math.round(y), graph,
                                lastWidth, value);

                    } else {
                        /*
                         * Other points calculate backwards
                         */
                        if (getPlotMode() == PlotMode.BAR) {
                            // MT 22.3.2013: I have no idea why some points are
                            // ignored. Without this bars get totally messed,
                            // but without essential points may be dropped in
                            // lines (no 1 priority)
                            int diff = Math.round(x - lastX);
                            if (diff > 2) {
                                lastWidth = diff;
                                p = new Point(Math.round(x), Math.round(y),
                                        graph, lastWidth, value);
                            } else {
                                p = null;
                            }
                        } else {
                            p = new Point(Math.round(x), Math.round(y), graph,
                                    lastWidth, value);
                        }

                    }
                    if (p != null) {
                        graph.addPoint(p);
                        lastX = x;
                    }
                }
            }

            graphs.add(graph);
        }

        setCurrentGraphs(graphs);

        // Plot the graphs
        if (getPlotMode() == PlotMode.BAR) {
            if (widget.isBarChartUniform()) {
                /*
                 * Make the bars uniform by using the minimum of the bar widths
                 */
                VConsole.log("Bar chart should be uniform, finding minimum bar width");

                // Find minimum
                int minWidth = Integer.MAX_VALUE;
                for (Graph g : graphs) {
                    for (Point p : g.getPoints()) {
                        if (p.width < minWidth && p.width > 0) {
                            minWidth = p.width;
                        }
                    }
                }

                VConsole.log("Minimum found, bar chart will be " + minWidth
                        + "px wide");

                // set minimum for all points
                for (Graph g : graphs) {
                    for (Point p : g.getPoints()) {
                        p.width = minWidth;
                    }
                }
            }

            if (widget.isGraphsStacked()) {
                plotter.plotStackedBarGraph(graphs);
            } else {
                plotter.plotBarGraph(graphs);
            }
        } else if (getPlotMode() == PlotMode.LINE) {
            if (widget.isGraphsStacked()) {
                plotter.plotStackedLineGraph(graphs);
            } else {
                plotter.plotLineGraph(graphs);
            }
        } else if (getPlotMode() == PlotMode.SCATTER) {
            if (widget.isGraphsStacked()) {
                plotter.plotStackedScatterGraph(graphs);
            } else {
                plotter.plotScatterGraph(graphs);
            }
        }
    }

    /**
     * Returns the line thickness for a graph
     * 
     * @param graphIndex
     *            The graph index rendered
     * @return
     */
    protected double getLineThickness(int graphIndex) {
        if (widget.getThicknessMap().containsKey(graphIndex)) {
            return widget.getThicknessMap().get(graphIndex);
        } else {
            return 2;
        }
    }

    /**
     * Should line caps be turned on for a graph
     * 
     * @param graphIndex
     *            The graph index
     * @return
     */
    protected boolean getLineCaps(int graphIndex) {
        return widget.getCapsMap().get(graphIndex);
    }
}
