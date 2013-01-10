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
 * Listener interface for events of DataSeries like data add/remove/update. This
 * listener is used internally by the library.
 */
public interface DataSeriesEventListener extends Serializable {
    /** A data point has been added */
    void dataAdded(DataAddedEvent event);

    /** A data point has been removed */
    void dataRemoved(DataRemovedEvent event);

    /** A data point has been updated */
    void dataUpdated(DataUpdatedEvent event);

    public static class DataAddedEvent {
        /** The affected series */
        private final Series series;
        /** The value added. May be null if item != null */
        private Number value;
        /** The item added. May be null if value != null */
        private DataSeriesItem item;

        DataAddedEvent(Series series, Number value) {
            if (series == null || value == null) {
                throw new IllegalArgumentException(
                        "Series or value may not be null");
            }
            this.series = series;
            this.value = value;
        }

        DataAddedEvent(Series series, DataSeriesItem item) {
            if (series == null || item == null) {
                throw new IllegalArgumentException(
                        "Series or item may not be null");
            }
            this.series = series;
            this.item = item;
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
    }

    public static class DataRemovedEvent extends DataAddedEvent {
        DataRemovedEvent(Series series, Number value) {
            super(series, value);
        }

        DataRemovedEvent(Series series, DataSeriesItem item) {
            super(series, item);
        }
    }

    public static class DataUpdatedEvent extends DataAddedEvent {
        int pointIndex;

        DataUpdatedEvent(Series series, Number value, int pointIndex) {
            super(series, value);
            this.pointIndex = pointIndex;
        }

        DataUpdatedEvent(Series series, DataSeriesItem item, int pointIndex) {
            super(series, item);
            this.pointIndex = pointIndex;
        }

        public int getPointIndex() {
            return pointIndex;
        }
    }

}
