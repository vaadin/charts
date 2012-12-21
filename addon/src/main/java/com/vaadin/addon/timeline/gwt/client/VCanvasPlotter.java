package com.vaadin.addon.timeline.gwt.client;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.vaadin.addon.timeline.gwt.canvas.client.Canvas;
import com.vaadin.addon.timeline.gwt.client.VTimelineDisplay.PlotMode;
import com.vaadin.client.BrowserInfo;
import com.vaadin.client.VConsole;

/**
 * Plotting algorithms
 * 
 * @author John Ahlroos / Vaadin Ltd
 * 
 */
public class VCanvasPlotter {

    /**
     * Represents a Point in the Cache
     */
    public static class Point implements Comparable<Point> {
        public int x, y;
        public Graph g;
        public int width;

        /**
         * Constructor
         * 
         * @param x
         *            The x-coordinate of the point
         * @param y
         *            The y-coordinate of the point
         * @param g
         *            The graph the point belongs to
         * @param width
         *            The width of the point (used by bar charts to measure the
         *            width of the bar)
         */
        public Point(int x, int y, Graph g, int width) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.width = width;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        @Override
        public int compareTo(Point o) {
            return o.x - this.x;
        }
    }

    /**
     * A comparator for comparing to points in a graph
     */
    public static class GraphPointComparator implements
    Comparator<Map.Entry<Graph, Point>> {
        @Override
        public int compare(Entry<Graph, Point> o1, Entry<Graph, Point> o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    }

    /**
     * Represents a graph in the Timeline
     */
    public static class Graph {
        private List<Point> points = new ArrayList<VCanvasPlotter.Point>();
        private boolean lineCaps = false;
        private double lineThickness = 1;
        private String color;
        private String fillColor;

        /**
         * Adds a point to the graph
         * 
         * @param p
         *            The point to add
         */
        public void addPoint(Point p) {
            points.add(p);
        }

        /**
         * Should the graph have line caps
         * 
         * @param enabled
         *            Are linecaps enabled
         */
        public void setLinecaps(boolean enabled) {
            lineCaps = enabled;
        }

        /**
         * Are line caps enabled
         * 
         * @return
         */
        public boolean isLineCaps() {
            return lineCaps;
        }

        /**
         * Set the thickness of the line of the graph. (Only applies to line
         * charts)
         * 
         * @param thickness
         *            The thickness in pixels
         */
        public void setLineThickness(double thickness) {
            lineThickness = thickness;
        }

        /**
         * Get the thickness of the lines in the line chart
         * 
         * @return
         */
        public double getLineThickness() {
            return lineThickness;
        }

        /**
         * Set the color of the graph.
         * 
         * @param color
         *            The color of the graph using CSS colors
         */
        public void setColor(String color) {
            this.color = color;
        }

        /**
         * Get the color of the graph. Returns a CSS color.
         * 
         * @return
         */
        public String getColor() {
            return color;
        }

        /**
         * Set the fill color of the graph (Applies to line and bar charts)
         * 
         * @param fillColor
         *            The fill color in CSS format
         */
        public void setFillColor(String fillColor) {
            this.fillColor = fillColor;
        }

        /**
         * Get the fill color for a graph. Returns a CSS color
         * 
         * @return
         */
        public String getFillColor() {
            return fillColor;
        }

        /**
         * Get the points for the graph
         * 
         * @return
         */
        public List<Point> getPoints() {
            return points;
        }
    }

    /**
     * A listener for listening to plotting events
     */
    public interface PlottingListener {

        /**
         * Called just before the plotter starts plotting. Good place for init
         * stuff
         */
        public void plottingStarts();

        /**
         * Called after the plotting has ended. Good place for cleanup.
         */
        public void plottingEnds();
    }

    private PlottingListener listener;
    private final Canvas canvas;
    private Float zero;
    private String color;
    private String fillColor;
    private boolean rendering = false;
    private boolean useShadows = true;

    /**
     * Constructor
     * 
     * @param canvas
     *            The canvas to plot on
     */
    public VCanvasPlotter(Canvas canvas) {
        this.canvas = canvas;
        zero = (float) canvas.getHeight();
    }

    /**
     * The y-coordinate where the zero value is located
     * 
     * @param zero
     *            A pixel value where the zero-coordinate is located
     */
    public void setZeroLevel(Float zero) {
        rendering = false;
        this.zero = zero;
    }

    /**
     * Set the listener to listen for plotting events
     * 
     * @param listener
     *            The listener to use
     */
    public void setListener(PlottingListener listener) {
        rendering = false;
        this.listener = listener;
    }

    /**
     * Get the listener for plotting events
     * 
     * @return
     */
    public PlottingListener getListener() {
        return listener;
    }

    /**
     * Plots a line graph
     * 
     * @param xCoordinates
     *            The x-coordinates
     * @param yCoordinates
     *            The y-coordinates
     */
    private void plotLineGraph(List<Float> xCoordinates,
            List<Float> yCoordinates, boolean lineCaps, double lineThickness) {

        canvas.setLineWidth(lineThickness);
        canvas.setLineJoin("round");

        // First draw the filling
        canvas.setStrokeStyle("rgba(0,0,0,255)");
        canvas.setFillStyle(fillColor);
        canvas.beginPath();

        float x = xCoordinates.get(0);
        float y = yCoordinates.get(0);

        canvas.moveTo(x, zero);
        canvas.lineTo(x, y);

        for (int i = 1; i < xCoordinates.size(); i++) {
            x = xCoordinates.get(i);
            y = yCoordinates.get(i);
            canvas.lineTo(x, y);
        }

        canvas.lineTo(x, zero);

        x = xCoordinates.get(0);
        canvas.moveTo(x, zero);

        canvas.closePath();
        canvas.fill();

        if (useShadows) {
            enableShadows(lineThickness, PlotMode.LINE);
        }

        // Then draw the outline
        if (lineCaps) {
            canvas.setLineJoin(Canvas.BEVEL);
        }

        canvas.setStrokeStyle(color);
        canvas.beginPath();

        x = xCoordinates.get(0);
        y = yCoordinates.get(0);

        canvas.moveTo(x, zero);
        canvas.moveTo(x, y);

        for (int i = 1; i < xCoordinates.size(); i++) {
            x = xCoordinates.get(i);
            y = yCoordinates.get(i);
            canvas.lineTo(x, y);
        }

        if (BrowserInfo.get().isIE8() || BrowserInfo.get().isChrome()) {
            /*
             * In IE we have to draw a line down to zero so the path can get
             * closed. If we don't then then there will be a line through the
             * whole graph due to the VML implementation. After we move we also
             * have to draw a 1px line, else IE will not draw anything when
             * stroke width > 1.5px. IE 9-> uses canvas implementation where
             * this problem does not exist
             * 
             * Apparently Chrome 18-> joined the club :(
             */
            canvas.moveTo(xCoordinates.get(0), yCoordinates.get(0));
            canvas.lineTo(xCoordinates.get(0) + 1, yCoordinates.get(0));

        } else {
            canvas.moveTo(xCoordinates.get(0), zero);
        }

        canvas.closePath();
        canvas.stroke();

        // Finally, if we have any linecaps then draw them
        if (lineCaps) {
            plotLineCaps(xCoordinates, yCoordinates, lineThickness);
        }
    }

    /**
     * Plots the line caps on the graph
     * 
     * @param xCoordinates
     *            The x-coordinates of the caps
     * @param yCoordinates
     *            The y-coordinates of the caps
     */
    private void plotLineCaps(List<Float> xCoordinates,
            List<Float> yCoordinates, double lineThickness) {
        canvas.setStrokeStyle(canvas.getBackgroundColor());

        canvas.setFillStyle(color);
        canvas.beginPath();
        Float x, y;
        for (int i = 1; i < xCoordinates.size(); i++) {
            x = xCoordinates.get(i);
            y = yCoordinates.get(i);
            canvas.moveTo(x, y);
            canvas.arc(x, y, lineThickness, 0, 2 * Math.PI, false);
        }

        canvas.closePath();
        canvas.stroke();
        canvas.fill();
    }

    private void enableShadows(double thickness, PlotMode mode) {
        if (mode == PlotMode.LINE) {
            canvas.setShadow("#666", (int) thickness, 0,
                    (int) Math.ceil(thickness / 2.0));

        } else if (mode == PlotMode.BAR) {
            canvas.setShadow("#666", 2, 2, 2);
        }
    }

    private void disableShadows() {
        canvas.setShadow(null, 0, 0, 0);
    }

    /**
     * Plots a line graph out of several graphs
     * 
     * @param graphs
     *            The graphs to plot as line graphs
     */
    public void plotLineGraph(List<Graph> graphs) {
        final long start = new Date().getTime();
        final ArrayList<Graph> renderGraphs = new ArrayList<VCanvasPlotter.Graph>(
                graphs);
        RepeatingCommand rc = new Scheduler.RepeatingCommand() {
            int counter = 0;
            @Override
            public boolean execute() {
                Graph g = renderGraphs.get(counter);
                if (counter == 0 && listener != null) {
                    listener.plottingStarts();
                    rendering = true;
                }


                counter++;

                if (g.getPoints().size() > 0) {
                    List<Float> x = new LinkedList<Float>();
                    List<Float> y = new LinkedList<Float>();
                    for (Point p : g.getPoints()) {
                        x.add((float) p.x);
                        y.add((float) p.y);
                    }

                    color = g.getColor();
                    fillColor = g.getFillColor();

                    if (g.getPoints().size() == 1) {
                        plotScatterGraph(renderGraphs);
                    } else {
                        plotLineGraph(x, y, g.isLineCaps(),
                                g.getLineThickness());
                    }
                }

                if (counter == renderGraphs.size() && listener != null) {
                    long end = new Date().getTime();
                    VConsole.log("VCanvasPlotter: Plotting line graphs took "
                            + (end - start) + "ms");
                    if (useShadows) {
                        disableShadows();
                    }
                    listener.plottingEnds();
                    rendering = false;
                }

                return counter < renderGraphs.size();
            }
        };

        iRender(rc, graphs.size());
    }

    /**
     * Plots a line graph where the lines position depends on each other.
     * 
     * @param graphs
     *            The list of graphs to plot. First graph is plotted bottom most
     *            and sequential graphs on top of the previous one.
     */
    public void plotStackedLineGraph(final List<Graph> graphs) {

        // Make all graphs equal sized by adding zeros
        int maxSize = 0;
        for (Graph g : graphs) {
            maxSize = maxSize < g.getPoints().size() ? g.getPoints().size()
                    : maxSize;
        }

        final int max = maxSize;
        RepeatingCommand rc = new Scheduler.RepeatingCommand() {
            private int counter = 0;
            private List<Float> lastCoords = null;

            @Override
            public boolean execute() {
                Graph g = graphs.get(counter);
                if (counter == 0 && listener != null) {
                    listener.plottingStarts();
                    canvas.setGlobalCompositeOperation(Canvas.DESTINATION_OVER);
                    if (useShadows) {
                        enableShadows(g.getLineThickness(), PlotMode.LINE);
                    }
                    rendering = true;
                }

                counter++;

                if (g.getPoints().size() > 0) {
                    float lastX = -1;
                    List<Float> yCoords = new LinkedList<Float>();
                    List<Float> xCoords = new LinkedList<Float>();
                    for (int i = 0; i < max; i++) {
                        if (g.getPoints().size() > i) {
                            Point p = g.getPoints().get(i);

                            if (lastCoords == null) {
                                canvas.lineTo(p.x, p.y);
                                yCoords.add((float) p.y);
                            } else {
                                float diff = lastCoords.get(i) - zero;
                                canvas.lineTo(p.x, p.y + diff);
                                yCoords.add(p.y + diff);
                            }
                            lastX = p.x;
                            xCoords.add(lastX);
                        }
                    }

                    color = g.getColor();
                    fillColor = g.getFillColor();
                    if (g.getPoints().size() == 1) {
                        plotScatterGraph(xCoords, yCoords);
                    } else {
                        plotLineGraph(xCoords, yCoords, g.isLineCaps(),
                                g.getLineThickness());
                    }

                    lastCoords = yCoords;
                }

                if (counter == graphs.size() && listener != null) {
                    canvas.setGlobalCompositeOperation(Canvas.SOURCE_OVER);
                    if (useShadows) {
                        disableShadows();
                    }

                    listener.plottingEnds();
                }

                return counter < graphs.size();
            }
        };

        iRender(rc, graphs.size());
    }

    private void iRender(RepeatingCommand rc, int graphs) {
        if (graphs > 1) {
            if (BrowserInfo.get().isIE8()) {
                /*
                 * VML is so slow it needs to be done incrementally
                 */
                Scheduler.get().scheduleIncremental(rc);
            } else {
                while (rc.execute()) {
                    ;
                }
            }
        } else if (graphs > 0) {
            rc.execute();
        }
    }

    /**
     * Plots a bar graph with overlapping graphs. Orders bars so the bar with
     * the least value is topmost.
     * 
     * @param graphs
     */
    public void plotBarGraph(List<Graph> graphs) {

        final long start = new Date().getTime();

        // Make all graphs equal sized by adding zeros
        int maxSize = 0;
        for (Graph g : graphs) {
            maxSize = maxSize < g.getPoints().size() ? g.getPoints().size()
                    : maxSize;
        }

        // Render graphs (Iterates through every point)
        final int max = maxSize;
        for (int i = 0; i < maxSize; i++) {

            // Create rendering order map
            final List<Point> points = new LinkedList<VCanvasPlotter.Point>();
            for (Graph g : graphs) {
                if (g.getPoints().size() > i) {
                    points.add(g.getPoints().get(i));
                }
            }

            // Order points so they always are visible
            Collections.sort(points, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    Float val1 = zero - o1.y;
                    Float val2 = zero - o2.y;

                    if (val1 >= zero && val2 >= 0) {
                        // Both are positive
                        return val1.compareTo(val2);
                    } else if (val1 <= zero && val2 <= zero) {
                        // Both are negative
                        return val2.compareTo(val1);
                    } else {
                        // One is negative, one is positive
                        return val1.compareTo(val2);
                    }
                }
            });

            final int index = i;
            RepeatingCommand rc = new RepeatingCommand() {
                private int counter = 0;
                private float x = points.get(0).x;

                @Override
                public boolean execute() {
                    if (counter == 0 && index == 0 && listener != null) {
                        listener.plottingStarts();
                    }
                    Point p = points.get(counter);
                    counter++;

                    if (useShadows) {
                        enableShadows(0, PlotMode.BAR);
                    }

                    canvas.setStrokeStyle("rgba(0,0,0,0)");

                    Graph g = p.g;
                    canvas.setFillStyle(g.getColor());

                    float height = p.y - zero;
                    float width = p.width;
                    float y = zero;

                    if (p.x >= x && p.x < x + p.width) {
                        /*
                         * If the bar x-coordinate is between the first bar
                         * x-coordinate and its width the stack them on top of
                         * eachother
                         */
                        if (p.width > 2) {
                            // Ensure space between the next bar and this one
                            canvas.fillRect(x - width, y, width - 2, height);
                        } else if (p.width > 0) {
                            canvas.fillRect(x - width, y, width, height);
                        }
                    } else {
                        /*
                         * Else stack them beside each other
                         */
                        if (p.width > 2) {
                            // Ensure space between the next bar and this one
                            canvas.fillRect(p.x - width, y, width - 2, height);
                        } else if (p.width > 0) {
                            canvas.fillRect(p.x - width, y, width, height);
                        }
                    }

                    if (counter == points.size() && index == max - 1
                            && listener != null) {
                        long end = new Date().getTime();
                        VConsole.log("VCanvasPlotter: Plotting " + index
                                + " bar graphs took " + (end - start) + "ms");
                        if (useShadows) {
                            disableShadows();
                        }
                        listener.plottingEnds();
                    }

                    return counter < points.size();
                }
            };

            iRender(rc, graphs.size());
        }
    }

    /**
     * Plots a graph with stacked graphs
     * 
     * @param graphs
     */
    public void plotStackedBarGraph(List<Graph> graphs) {

        // Make all graphs equal sized by adding zeros
        int maxSize = 0;
        for (Graph g : graphs) {
            maxSize = maxSize < g.getPoints().size() ? g.getPoints().size()
                    : maxSize;
        }

        // Render graphs (Iterates through every point)
        final int max = maxSize;
        for (int i = 0; i < maxSize; i++) {

            // Create rendering order map
            final List<Point> points = new LinkedList<VCanvasPlotter.Point>();
            for (Graph g : graphs) {
                if (g.getPoints().size() > i) {
                    points.add(g.getPoints().get(i));
                }
            }

            final int index = i;
            RepeatingCommand rc = new RepeatingCommand() {
                private int counter;
                private float x = points.get(0).x;
                float localZero = zero;

                @Override
                public boolean execute() {

                    if (counter == 0 && index == 0 && listener != null) {
                        listener.plottingStarts();
                    }

                    canvas.setStrokeStyle("rgba(0,0,0,0)");

                    Point p = points.get(counter);
                    counter++;

                    if (useShadows) {
                        enableShadows(0, PlotMode.BAR);
                    }

                    Graph g = p.g;
                    canvas.setFillStyle(g.getColor());

                    float height = p.y - zero;
                    float width = p.width;
                    // float x = p.x;
                    float y = localZero;

                    if (p.x >= x && p.x < x + p.width) {
                        /*
                         * If the bar x-coordinate is between the first bar
                         * x-coordinate and its width the stack them on top of
                         * eachother
                         */
                        if (p.width > 2) {
                            // Ensure space between the next bar and this one
                            canvas.fillRect(x, y, width - 2, height);
                        } else if (p.width > 0) {
                            canvas.fillRect(x, y, width, height);
                        }

                        localZero += height;
                    } else {
                        /*
                         * Else stack them beside each other
                         */
                        if (p.width > 2) {
                            // Ensure space between the next bar and this one
                            canvas.fillRect(p.x, y, width - 2, height);
                        } else if (p.width > 0) {
                            canvas.fillRect(p.x, y, width, height);
                        }
                    }

                    if (counter == points.size() && index == max - 1
                            && listener != null) {
                        if (useShadows) {
                            disableShadows();
                        }
                        listener.plottingEnds();
                    }

                    return counter < points.size();
                }
            };

            iRender(rc, graphs.size());
        }
    }

    /**
     * Plots a scatter graph
     * 
     * @param xCoordinates
     *            The x-coordinates
     * @param yCoordinates
     *            THe y-coordinates
     */
    private void plotScatterGraph(List<Float> xCoordinates,
            List<Float> yCoordinates) {

        canvas.setStrokeStyle(color);
        canvas.setFillStyle(fillColor);
        canvas.beginPath();

        float x = xCoordinates.get(0);
        float y = yCoordinates.get(0);

        canvas.moveTo(x, y);
        canvas.strokeRect(x, y, 2, 2);
        canvas.moveTo(x, y);

        for (int i = 1; i < xCoordinates.size(); i++) {
            x = xCoordinates.get(i);
            y = yCoordinates.get(i);

            canvas.moveTo(x, y);
            canvas.strokeRect(x, y, 2, 2);
            canvas.moveTo(x, y);
        }

        canvas.moveTo(x, zero);

        x = xCoordinates.get(0);
        canvas.moveTo(x, zero);

        canvas.closePath();
        canvas.stroke();
        canvas.fill();
    }

    /**
     * Plots a scatter graph of several graphs
     * 
     * @param graphs
     *            The graphs to plot
     */
    public void plotScatterGraph(final List<Graph> graphs) {
        final long start = new Date().getTime();
        RepeatingCommand rc = new RepeatingCommand() {
            private int counter = 0;
            @Override
            public boolean execute() {
                if (counter == 0 && listener != null) {
                    listener.plottingStarts();
                }

                Graph g = graphs.get(counter);
                counter++;

                if (g.getPoints().size() > 0) {
                    List<Float> x = new LinkedList<Float>();
                    List<Float> y = new LinkedList<Float>();
                    for (Point p : g.getPoints()) {
                        x.add((float) p.x);
                        y.add((float) p.y);
                    }
                    color = g.getColor();
                    fillColor = g.getFillColor();
                    plotScatterGraph(x, y);
                }

                if (counter == graphs.size() && listener != null) {
                    long end = new Date().getTime();
                    VConsole.log("VCanvasPlotter: Plotting scatter graph took "
                            + (end - start)
                            + "ms");
                    listener.plottingEnds();
                }

                return counter < graphs.size();
            }
        };

        iRender(rc, graphs.size());
    }

    /**
     * Plots a scatter graph of several graphs. Stacks the graphs on top of each
     * other
     * 
     * @param graphs
     *            The graphs to plot
     */
    public void plotStackedScatterGraph(final List<Graph> graphs) {

        // Make all graphs equal sized by adding zeros
        int maxSize = 0;
        for (Graph g : graphs) {
            maxSize = maxSize < g.getPoints().size() ? g.getPoints().size()
                    : maxSize;
        }
        for (Graph g : graphs) {
            while (g.getPoints().size() < maxSize) {
                g.addPoint(new Point(0, 0, g, 0));
            }
        }

        final int max = maxSize;
        RepeatingCommand rc = new RepeatingCommand() {
            private int counter;
            private List<Float> lastCoords = null;

            @Override
            public boolean execute() {

                if (counter == 0 && listener != null) {
                    listener.plottingStarts();
                }

                canvas.setGlobalCompositeOperation(Canvas.DESTINATION_OVER);

                Graph g = graphs.get(counter);
                counter++;

                if (g.isLineCaps()) {
                    canvas.setLineJoin(Canvas.BEVEL);
                }

                canvas.setStrokeStyle(g.getColor());
                canvas.setFillStyle(g.getFillColor());

                canvas.beginPath();
                canvas.moveTo(-1, zero);

                float lastX = -1;
                List<Float> theseCoords = new LinkedList<Float>();
                for (int i = 0; i < max; i++) {
                    Point p = g.getPoints().get(i);

                    if (lastCoords == null) {
                        canvas.moveTo(p.x, p.y);
                        canvas.strokeRect(p.x, p.y, 2, 2);
                        canvas.moveTo(p.x, p.y);
                        theseCoords.add((float) p.y);
                    } else {
                        float diff = lastCoords.get(i) - zero;
                        canvas.moveTo(p.x, p.y + diff);
                        canvas.strokeRect(p.x, p.y + diff, 2, 2);
                        canvas.moveTo(p.x, p.y + diff);
                        theseCoords.add(p.y + diff);
                    }
                    lastX = p.x;
                }

                canvas.moveTo(lastX, zero);

                canvas.closePath();
                canvas.stroke();
                canvas.fill();

                lastCoords = theseCoords;

                canvas.setGlobalCompositeOperation(Canvas.SOURCE_OVER);

                if (counter == graphs.size() && listener != null) {
                    listener.plottingEnds();
                }

                return counter < graphs.size();
            }
        };

        iRender(rc, graphs.size());
    }

    public void setUseShadows(boolean useShadows) {
        this.useShadows = useShadows;
    }

    public boolean isRendering() {
        return rendering;
    }
}
