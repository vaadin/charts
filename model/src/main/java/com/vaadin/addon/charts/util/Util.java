package com.vaadin.addon.charts.util;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2014 Vaadin Ltd
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
