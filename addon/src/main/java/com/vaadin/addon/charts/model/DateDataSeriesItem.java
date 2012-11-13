package com.vaadin.addon.charts.model;

import java.util.Date;

/**
 * DataSeries item where the values of the x-axis are Dates, (date is serialized
 * to Long value from epoch).
 * 
 */
public class DateDataSeriesItem extends DataSeriesItem {

    public DateDataSeriesItem(Date date, Number y) {
        setX(date.getTime());
        setY(y);
    }

}
