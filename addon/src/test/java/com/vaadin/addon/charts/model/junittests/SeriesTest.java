package com.vaadin.addon.charts.model.junittests;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.addon.charts.model.AbstractSeries;

public class SeriesTest {

    @Test
    public void seriesVisibility_defaultVisibility_trueByDefault() {
        @SuppressWarnings("serial")
        AbstractSeries dataSeries = new AbstractSeries() {
        };

        Assert.assertTrue(dataSeries.isVisible());
    }
}
