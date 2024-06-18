/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Util {

    /**
     * Returns an epoch timestamp adjusted by timezone offset. All Date objects
     * passed to Highcharts should be routed via this method as we want to
     * maintain the Timezone used on the server (HC uses UTC time stamps
     * internally)
     * 
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long toHighchartsTS(Date date) {
        return date.getTime() - date.getTimezoneOffset() * 60000;
    }

    /**
     * Converts UTC based raw date value from the client side rendering library
     * to a Date value in JVM's default time zone.
     * 
     * @param rawClientSideValue
     *            the raw value from the client side
     * @return a Date value in JVM's default time zone
     */
    public static Date toServerDate(double rawClientSideValue) {
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        instance.setTimeInMillis((long) rawClientSideValue);
        // fix one field to force calendar re-adjust the value
        instance.set(Calendar.MINUTE, instance.get(Calendar.MINUTE));
        instance.setTimeZone(TimeZone.getDefault());
        return instance.getTime();
    }

}
