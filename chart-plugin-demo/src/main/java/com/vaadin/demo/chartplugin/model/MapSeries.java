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
