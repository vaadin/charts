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
package com.vaadin.demo.chartplugin.model;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.charts.model.DataSeries;

/**
 * Adds 'type' and 'valueRanges' variables to the series. valueRanges is used to
 * specify color for a number value ranges.
 * 
 */
public class MapSeries extends DataSeries {

    private CustomChartTypes type;
    private final List<ValueRange> valueRanges = new ArrayList<ValueRange>();

    public void addValueRange(ValueRange valuerange) {
        valueRanges.add(valuerange);
    }

    public List<ValueRange> getValueRanges() {
        return valueRanges;
    }

    public CustomChartTypes getType() {
        return type;
    }

    public void setType(CustomChartTypes type) {
        this.type = type;
    }
}
