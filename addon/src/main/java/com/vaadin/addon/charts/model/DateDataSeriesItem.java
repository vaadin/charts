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
