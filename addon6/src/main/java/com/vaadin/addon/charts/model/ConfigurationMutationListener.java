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
 * Listener interface for events triggered in Configuration. E.g. in DataSeries,
 * events like data add/remove/update. This listener is used internally by the
 * library.
 * 
 * @deprecated usage of this interface is currently reserved for internal use of
 *             the library
 */
public interface ConfigurationMutationListener extends Serializable {
    /** A data point has been added */
    void dataAdded(DataAddedEvent event);

    /** A data point has been removed */
    void dataRemoved(DataRemovedEvent event);

    /** A data point has been updated */
    void dataUpdated(DataUpdatedEvent event);

    /** The series is enabled or disabled */
    void seriesEnablation(SeriesEnablationEvent event);
    
    void animationChanged(boolean animation);

    public static abstract class SeriesEvent implements Serializable {
        /** The affected series */
        protected Series series;

        /** The affected series */
        public Series getSeries() {
            return series;
        }

    }

    public static abstract class SeriesItemEvent extends SeriesEvent {
        /** The item added. May be null if value != null */
        protected DataSeriesItem item;

        /** The item added. May be null if value != null */
        public DataSeriesItem getItem() {
            return item;
        }

        /** The value added. May be null if item != null */
        protected Number value;

        /** The value added. May be null if item != null */
        public Number getValue() {
            return value;
        }

    }

    public static class DataAddedEvent extends SeriesItemEvent {

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

        public boolean isShift() {
            return shift;
        }
    }

    public static class DataRemovedEvent extends SeriesItemEvent {
        private int index;

        DataRemovedEvent(Series series, int index) {
            this.series = series;
            this.index = index;
        }
        
        /**
         * @return index of the removed data point
         */
        public int getIndex() {
            return index;
        }

    }

    public static class DataUpdatedEvent extends SeriesItemEvent {
        int pointIndex;

        DataUpdatedEvent(Series series, Number value, int pointIndex) {
            this.series = series;
            this.value = value;
            this.pointIndex = pointIndex;
        }

        DataUpdatedEvent(Series series, DataSeriesItem item, int pointIndex) {
            this.series = series;
            this.item = item;
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
    public static class SeriesEnablationEvent extends SeriesEvent {

        /** Series was enabled */
        private final boolean enabled;

        SeriesEnablationEvent(Series series, boolean enabled) {
            if (series == null) {
                throw new IllegalArgumentException(
                        "Series may not be null");
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

    }

}
