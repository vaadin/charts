package com.vaadin.addon.charts.model;

public interface DataSeriesEventListener {
    /** A data point has been added */
    void dataAdded(DataAddedEvent event);

    /** A data point has been removed */
    void dataRemoved(DataRemovedEvent event);

    /** A data point has been updated */
    void dataUpdated(DataUpdatedEvent event);

    public static class DataAddedEvent {
        /** The affected series */
        private Series series;
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

        public Number getValue() {
            return value;
        }

        public DataSeriesItem getItem() {
            return item;
        }

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
