/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.util.junittests;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Test;

import com.vaadin.addon.charts.util.Util;

/**
 * Tests for Instant to Highcharts TS conversion.
 *
 */
public class SerializationTest {

    @Test
    public void dateUtils_toAndFromHighchartsTS_equalValueObtained() {
        Instant instant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        long highchartsTS = Util.toHighchartsTS(instant);
        Instant serverInstant = Util.toServerInstant(highchartsTS);
        assertEquals(instant.toEpochMilli(), serverInstant.toEpochMilli());
    }

}
