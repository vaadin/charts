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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;

/**
 * Cache implementation for caching points on the client side
 * 
 */
public class VClientCache {

    /**
     * A data point in time
     */
    public static class DataPoint implements Comparable<DataPoint> {
        private final Float value;
        private final Long date;

        /**
         * Constructor
         * 
         * @param date
         *            The date of the point
         * @param value
         *            The value of the point
         */
        public DataPoint(Date date, Float value) {
            this.value = value;
            this.date = date.getTime();
        }

        /**
         * The value of the point
         * 
         * @return The value
         */
        public Float getValue() {
            return value;
        }

        /**
         * The date of the point
         * 
         * @return The date
         */
        public Date getDate() {
            return new Date(date);
        }

        public Long getTime() {
            return date;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        public int compareTo(DataPoint o) {
            return date.compareTo(o.getTime());
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object o) {
            if (o instanceof DataPoint) {
                DataPoint dp = (DataPoint) o;
                return date.equals(dp.getTime());
            } else {
                return false;
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return date.hashCode();
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return Long.toString(date);
        }
    }

    /**
     * A range of points sorted by time
     */
    public static class DataRange implements Comparable<DataRange> {
        private final Long from;
        private final Long to;
        private final List<DataPoint> points;
        private final Set<String> markers;
        private final Set<String> events;
        private DataPoint minimum;
        private DataPoint maximum;

        /**
         * Constructor
         * 
         * @param from
         *            Date that range starts
         * @param to
         *            Date the range ends
         * @param values
         *            The values in the range (must have the same size as the
         *            dates)
         * @param dates
         *            The dates in the range (must have the same size as the
         *            values)
         * @param markers
         *            The markers in the range
         * @param events
         *            The events in the range
         */
        public DataRange(Date from, Date to, List<Float> values,
                List<Date> dates, Set<String> markers, Set<String> events) {
            this.from = from.getTime();
            this.to = to.getTime();

            points = new ArrayList<DataPoint>();
            for (int i = 0; i < dates.size(); i++) {

                // Create data point
                DataPoint dp = new DataPoint(dates.get(i), values.get(i));

                // Calculate minimum
                if (minimum == null || minimum.getValue() > dp.getValue()) {
                    minimum = dp;
                }

                // Calculate maximum
                if (maximum == null || maximum.getValue() < dp.getValue()) {
                    maximum = dp;
                }

                // Add the point
                points.add(dp);
            }

            // Add markers and events
            this.markers = markers;
            this.events = events;
        }

        /**
         * Constructor
         * 
         * @param from
         *            Date that range starts
         * @param to
         *            Date the range ends
         * @param points
         *            The data points in the range
         * @param markers
         *            The markers in the range
         * @param events
         *            The events in the range
         * @param min
         *            The minimum value in the range
         * @param max
         *            The maximum value in the range
         */
        public DataRange(Date from, Date to, List<DataPoint> points,
                Set<String> markers, Set<String> events, DataPoint min,
                DataPoint max) {
            this.from = from.getTime();
            this.to = to.getTime();
            this.points = points;
            this.markers = markers;
            this.events = events;
            minimum = min;
            maximum = max;
        }

        /**
         * Checks if a date is in the range
         * 
         * @param date
         *            The date to check
         * @return Returns true if date is in range, else false
         */
        public boolean inRange(Date date) {
            Long time = date.getTime();
            return time >= from && time <= to;
        }

        /**
         * Gets the points from the range
         * 
         * @return Returns the points as a list of datapoints
         */
        public List<DataPoint> getPoints() {
            return points;
        }

        /**
         * Gets the markers in the range
         * 
         * @return Returns the markers as a set of strings
         */
        public Set<String> getMarkers() {
            return markers;
        }

        /**
         * Gets the events in the range
         * 
         * @return Returns the events as a set of strings
         */
        public Set<String> getEvents() {
            return events;
        }

        /**
         * Gets the starting time of the range
         * 
         * @return The starting time as Long
         */
        public Long getStartTime() {
            return from;
        }

        /**
         * Gets the ending time of the range
         * 
         * @return The ending time
         */
        public Long getEndTime() {
            return to;
        }

        /**
         * Gets the minimum value of the range
         * 
         * @return The minimum data point
         */
        public DataPoint getMinimum() {
            return minimum;
        }

        /**
         * Gets the maximum value of the range
         * 
         * @return Them maximum data point
         */
        public DataPoint getMaximum() {
            return maximum;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        public int compareTo(DataRange o) {
            Date start = new Date(o.getStartTime());
            Date end = new Date(o.getEndTime());

            if (inRange(start) || inRange(end)) {
                return 0;
            }

            if (o.getStartTime() > to) {
                return -1;
            }

            return 1;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o instanceof DataRange) {
                DataRange r = (DataRange) o;
                Date start = new Date(r.getStartTime());
                Date end = new Date(r.getEndTime());
                if (inRange(start) || inRange(end)) {
                    return true;
                }
            }

            return false;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            return from.hashCode() + to.hashCode();
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            DateTimeFormat formatter = DateTimeFormat
                    .getFormat(PredefinedFormat.DATE_TIME_SHORT);
            Date f = new Date(from);
            Date t = new Date(to);
            return "Datarange: " + formatter.format(f) + " - "
                    + formatter.format(t) + ", points=" + points.size()
                    + ",max=" + maximum.getValue() + ", min="
                    + minimum.getValue();
        }
    }

    // Graph - Data range map
    private final Map<Integer, List<DataRange>> dataCache;

    // The main widget
    private final VTimelineWidget widget;

    public VClientCache(VTimelineWidget widget) {
        dataCache = new HashMap<Integer, List<DataRange>>();
        this.widget = widget;
    }

    /**
     * Get a date range by start and end dates. Note: A range with different
     * start and end dates might be returned!
     * 
     * @param graph
     *            The graph of the range
     * @param from
     *            The start date of the range
     * @param to
     *            The end date of the range
     * @return A date range
     */
    private DataRange getRange(Integer graph, Date from, Date to) {
        List<DataRange> ranges = dataCache.get(graph);

        if (ranges == null) {
            return null;
        }

        for (DataRange r : ranges) {
            if (r.inRange(from) || r.inRange(to)) {
                return r;
            }
        }

        return null;
    }

    /**
     * Merge two overlapping date ranges into one
     * 
     * @param graph
     *            The graph of the ranges
     * @param r1
     *            The first date range
     * @param r2
     *            The second date range
     */
    private void merge(Integer graph, DataRange r1, DataRange r2) {
        List<DataRange> ranges = dataCache.get(graph);
        if (ranges == null) {
            return;
        }

        // Remove both ranges from the cache
        if (ranges.contains(r1)) {
            ranges.remove(r1);
        }

        if (ranges.contains(r2)) {
            ranges.remove(r2);
        }

        // Calculate new from and to times
        Date from = r1.getStartTime() > r2.getStartTime() ? new Date(
                r2.getStartTime()) : new Date(r1.getStartTime());
        Date to = r1.getEndTime() > r2.getEndTime() ? new Date(r1.getEndTime())
                : new Date(r2.getEndTime());

        // Add markers
        Set<String> markers = new HashSet<String>();
        if (r1.getMarkers() != null) {
            markers.addAll(r1.getMarkers());
        }
        if (r2.getMarkers() != null) {
            markers.addAll(r2.getMarkers());
        }

        // Add events
        Set<String> events = new HashSet<String>();
        if (r1.getEvents() != null) {
            events.addAll(r1.getEvents());
        }
        if (r2.getEvents() != null) {
            events.addAll(r2.getEvents());
        }

        // Add all points, skip duplicates
        Set<DataPoint> uniquePoints = new HashSet<DataPoint>();
        if (r1.getPoints() != null) {
            uniquePoints.addAll(r1.getPoints());
        }
        if (r2.getPoints() != null) {
            uniquePoints.addAll(r2.getPoints());
        }

        // Sort the points
        List<DataPoint> points = new ArrayList<DataPoint>(uniquePoints);
        Collections.sort(points);

        // Get minimum and maximum
        DataPoint min = null, max = null;
        for (DataPoint p : points) {
            if (min == null || min.getValue() > p.getValue()) {
                min = p;
            }
            if (max == null || max.getValue() < p.getValue()) {
                max = p;
            }
        }

        // Create a new Data range
        DataRange range = new DataRange(from, to, points, markers, events, min,
                max);
        ranges.add(range);
    }

    /**
     * Add data points to cache
     * 
     * @param graph
     *            The graph index
     * @param from
     *            THe from date
     * @param to
     *            The to date
     * @param values
     *            The values
     * @param dates
     *            The dates
     * @param markers
     *            The markers
     * @param events
     *            THe events
     */
    public void addToCache(Integer graph, Date from, Date to,
            List<Float> values, List<Date> dates, Set<String> markers,
            Set<String> events) {

        List<DataRange> ranges = dataCache.get(graph);
        if (ranges == null) {
            ranges = new LinkedList<DataRange>();
            dataCache.put(graph, ranges);
        }

        // Check if we have an mergable data range
        DataRange range = getRange(graph, from, to);

        // No existing range was found, so creating a new one
        if (range == null) {
            range = new DataRange(from, to, values, dates, markers, events);
            ranges.add(range);
        }

        // Else merge the ranges
        else {
            merge(graph, range, new DataRange(from, to, values, dates, markers,
                    events));
        }
    }

    /**
     * Get graph data points from cache
     * 
     * @param graph
     *            The graph index number
     * @param from
     *            The start date
     * @param to
     *            The end date
     * @return Returns an object array with 4 values, the dates, the values, the
     *         markers and the events. If the range was not found in cache null
     *         is returned.
     */
    public Object[] getFromCache(Integer graph, Date from, Date to) {
        if (graph == null || from == null || to == null) {
            return null;
        }

        List<DataRange> ranges = dataCache.get(graph);
        if (ranges == null) {
            return null;
        }

        for (DataRange dr : ranges) {
            if ((dr.inRange(from) && dr.inRange(to))
                    || (dr.inRange(from)
                            && to.getTime() > widget.getEndDate().getTime() && dr
                            .getEndTime() == widget.getEndDate().getTime())
                    || (dr.inRange(to)
                            && from.getTime() < widget.getStartDate().getTime() && dr
                            .getStartTime() == widget.getStartDate().getTime())) {
                List<Float> values = new LinkedList<Float>();
                List<Date> dates = new LinkedList<Date>();
                Set<String> markers = new HashSet<String>();
                Set<String> events = new HashSet<String>();

                // Approximate the first index
                Long totalDiffTime = dr.getEndTime() - dr.getStartTime();
                Float pointSize = new Float(dr.getPoints().size());
                Float sizeRatio = pointSize / totalDiffTime.floatValue();
                Long startDiffTime = from.getTime() - dr.getStartTime();

                // Since we are approximating reduce the start point with 1/3 so
                // we definitly start
                // searching for the points outside the range
                Float startPoint = (startDiffTime.floatValue() * sizeRatio) / 3F;

                // Ensure start point is positive
                if (startPoint < 0) {
                    startPoint = 0f;
                }

                // Find the real first point index
                int firstPointIndex = startPoint.intValue();
                for (int d = startPoint.intValue(); d < dr.getPoints().size(); d++) {
                    Date date = dr.getPoints().get(d).getDate();
                    if (date.after(from) && date.before(to)) {
                        firstPointIndex = d
                                - widget.getOffscreenRenderingPoints();
                        if (firstPointIndex < 0) {
                            firstPointIndex = 0;
                        }
                        break;
                    }
                }

                // Approximate the last index
                Long endDiffTime = dr.getEndTime() - to.getTime();

                // Since we are approximating increase the end point so we
                // definitly start
                // searching for the points outsize the range
                Float endPoint = pointSize - endDiffTime.floatValue()
                        * sizeRatio;
                endPoint += (endPoint - startPoint);

                // Boundary checl
                if (endPoint >= pointSize) {
                    endPoint = pointSize - 1F;
                }

                // Find the real last point index
                int lastPointIndex = endPoint.intValue();

                if (to.getTime() <= widget.getEndDate().getTime()) {
                    for (int d = endPoint.intValue(); d > startPoint.intValue(); d--) {
                        Date date = dr.getPoints().get(d).getDate();
                        if (date.after(from) && date.before(to)) {
                            lastPointIndex = d
                                    + widget.getOffscreenRenderingPoints();
                            if (lastPointIndex > dr.getPoints().size() - 1) {
                                lastPointIndex = dr.getPoints().size() - 1;
                            }
                            break;
                        }
                    }
                }

                // Add the subset
                for (int i = firstPointIndex; i <= lastPointIndex; i++) {
                    if (i >= 0 && i < dr.getPoints().size()) {
                        DataPoint dp = dr.getPoints().get(i);
                        dates.add(dp.getDate());
                        values.add(dp.getValue());
                    }
                }

                // Get the markers
                if (dr.getMarkers() != null) {
                    for (String markerStr : dr.getMarkers()) {
                        String[] params = markerStr.split("_");
                        Long time = Long.parseLong(params[0]);
                        if (time >= dr.getStartTime()
                                && time <= dr.getEndTime()) {
                            markers.add(markerStr);
                        }
                    }
                }

                // Get the events
                if (dr.getEvents() != null) {
                    for (String eventStr : dr.getEvents()) {
                        String[] params = eventStr.split("_");
                        Long time = Long.parseLong(params[0]);
                        if (time >= dr.getStartTime()
                                && time <= dr.getEndTime()) {
                            events.add(eventStr);
                        }
                    }
                }

                // Get the minumum and maximum indexes
                int minIndex = dr.getPoints().indexOf(dr.getMinimum());
                int maxIndex = dr.getPoints().indexOf(dr.getMaximum());

                // Set minimum value
                Float min = null;
                if (minIndex >= firstPointIndex && minIndex <= lastPointIndex) {
                    min = dr.getMinimum().getValue();
                } else {
                    min = new Float(VTimelineWidget.getMinValue(values));
                }

                // Set maximum value
                Float max = null;
                if (maxIndex >= firstPointIndex && maxIndex <= lastPointIndex) {
                    max = dr.getMaximum().getValue();
                } else {
                    max = new Float(VTimelineWidget.getMaxValue(values));
                }

                return new Object[] { values, dates, markers, events, min, max };
            }
        }

        return null;
    }
}
