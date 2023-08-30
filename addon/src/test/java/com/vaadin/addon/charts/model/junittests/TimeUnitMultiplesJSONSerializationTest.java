/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model.junittests;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vaadin.addon.charts.model.TimeUnit;
import com.vaadin.addon.charts.model.TimeUnitMultiples;
import com.vaadin.addon.charts.model.YAxis;

public class TimeUnitMultiplesJSONSerializationTest {

    @Test
    public void toJSON_axisWithUnits_TimeUnitMultiplesCorrectlySerialized() {
        YAxis yaxis = new YAxis();

        TimeUnitMultiples unitMillisecond = new TimeUnitMultiples(
                TimeUnit.MILLISECOND, 1, 2, 5, 10, 20, 25, 50, 100, 200, 500);
        TimeUnitMultiples unitSecond = new TimeUnitMultiples(TimeUnit.SECOND,
                1, 2, 5, 10, 15, 30);
        TimeUnitMultiples unitMinute = new TimeUnitMultiples(TimeUnit.MINUTE,
                1, 2, 5, 10, 15, 30);
        TimeUnitMultiples unitHour = new TimeUnitMultiples(TimeUnit.HOUR, 1, 2,
                3, 4, 6, 8, 12);
        TimeUnitMultiples unitDay = new TimeUnitMultiples(TimeUnit.DAY, 1);
        TimeUnitMultiples unitWeek = new TimeUnitMultiples(TimeUnit.WEEK, 1);
        TimeUnitMultiples unitMonth = new TimeUnitMultiples(TimeUnit.MONTH, 1,
                3, 6);
        TimeUnitMultiples unitYear = new TimeUnitMultiples(TimeUnit.YEAR, null);

        yaxis.setUnits(unitMillisecond, unitSecond, unitMinute, unitHour,
                unitDay, unitWeek, unitMonth, unitYear);

        // units array should look like this
        // units: [
        // ['millisecond', [1, 2, 5, 10, 20, 25, 50, 100, 200, 500]],
        // ['second', [1, 2, 5, 10, 15, 30]],
        // ['minute',[1, 2, 5, 10, 15, 30]],
        // ['hour',[1, 2, 3, 4, 6, 8, 12]],
        // ['day',[1]],
        // ['week',[1]],
        // ['month',[1, 3, 6]],
        // ['year',null]
        // ]

        String expected = "{\"units\":[[\"millisecond\",[1,2,5,10,20,25,50,100,200,500]],[\"second\",[1,2,5,10,15,30]],[\"minute\",[1,2,5,10,15,30]],[\"hour\",[1,2,3,4,6,8,12]],[\"day\",[1]],[\"week\",[1]],[\"month\",[1,3,6]],[\"year\",null]]}";

        assertEquals(expected, toJSON(yaxis));
    }
}
