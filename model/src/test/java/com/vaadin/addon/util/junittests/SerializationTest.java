package com.vaadin.addon.util.junittests;

import static org.junit.Assert.assertTrue
import com.vaadin.addon.charts.util.Util;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Test

import com.vaadin.addon.charts.util.Util

/**
 * Tests for Instant to Highcharts TS conversion.
 */
public class SerializationTest {

    @Test
    public void dateUtils_toAndFromHighchartsTS_equalValueObtained() {
        Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        long highchartsTS = Util.toHighchartsTS(instant);
        Instant serverInstant = Util.toServerInstant(highchartsTS);

        final String serverString = serverInstant.toString();
        final String strToCompare = serverString.substring(0, serverString.length() - 1);
        assertTrue(instant.toString().startsWith(strToCompare));
    }

}
