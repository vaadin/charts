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

import java.io.Serializable;

/**
 * Listener interface for events triggered in DataSeries, like data
 * add/remove/update. This listener is used internally by the library.
 */
public interface DataSeriesEventListener extends Serializable {
    /** A data point has been added */
    void dataAdded(DataAddedEvent event);

    /** A data point has been removed */
    void dataRemoved(DataRemovedEvent event);

    /** A data point has been updated */
    void dataUpdated(DataUpdatedEvent event);

    /** The series is enabled or disabled */
    void seriesEnablation(SeriesEnablationEvent event);

    public static class DataAddedEvent {
        /** The affected series */
        private final Series series;
        /** The value added. May be null if item != null */
        private Number value;
        /** The item added. May be null if value != null */
        private DataSeriesItem item;
        
        /** true if the data addition was a shift and first item was removed */
        private boolean shift = false;

        DataAddedEvent(Series series, Number value) {
            if (series == null || value == null) {
                throw new IllegalArgumentException(
                        "Series or value may not be null");
            }
            this.series = series;
            this.value = value;
        }

        DataAddedEvent(Series series, DataSeriesItem item, boolean shift) {
            if (series == null || item == null) {
                throw new IllegalArgumentException(
                        "Series or item may not be null");
            }
            this.series = series;
            this.item = item;
            this.shift = shift;
        }

        /** The value added. May be null if item != null */
        public Number getValue() {
            return value;
        }

        /** The item added. May be null if value != null */
        public DataSeriesItem getItem() {
            return item;
        }

        /** The affected series */
        public Series getSeries() {
            return series;
        }
        
        public boolean isShift() {
            return shift;
        }
    }

    public static class DataRemovedEvent extends DataAddedEvent {
        DataRemovedEvent(Series series, Number value) {
            super(series, value);
        }

        DataRemovedEvent(Series series, DataSeriesItem item) {
            super(series, item, false);
        }
    }

    public static class DataUpdatedEvent extends DataAddedEvent {
        int pointIndex;

        DataUpdatedEvent(Series series, Number value, int pointIndex) {
            super(series, value);
            this.pointIndex = pointIndex;
        }

        DataUpdatedEvent(Series series, DataSeriesItem item, int pointIndex) {
            super(series, item, false);
            this.pointIndex = pointIndex;
        }

        public int getPointIndex() {
            return pointIndex;
        }
    }

    /**
     * Listener class for Series enabling and disabling events. This listener is
     * used internally by the library.
     */
    public static class SeriesEnablationEvent {

        /** Series was enabled */
        private final boolean enabled;
        /** Series which was enabled or disabled */
        private final Series series;

        SeriesEnablationEvent(Series series, boolean enabled) {
            if (series == null || series.getName() == null) {
                throw new IllegalArgumentException(
                        "Series or its name may not be null");
            }
            this.series = series;
            this.enabled = enabled;
        }

        /**
         * @return is given series now enabled
         */
        public boolean isEnabled() {
            return enabled;
        }

        /**
         * @return the altered series
         */
        public Series getSeries() {
            return series;
        }
    }
}
