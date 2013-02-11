package com.vaadin.addon.charts.util;

import java.util.Date;

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
        return date.getTime() + date.getTimezoneOffset() * 60000;
    }

}
