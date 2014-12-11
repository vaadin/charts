package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
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

/**
 * Generic plot options that will be used for all chart types, unless a chart
 * type specific options are defined.
 * 
 * @see AbstractPlotOptions
 * @see AbstractLinePlotOptions
 */
public class PlotOptionsSeries extends AbstractLinePlotOptions {

    // TODO check if this is at correct place in type hierarchy
    private Number groupPadding;

    /**
     * Sets the padding between each value group, in X-axis units. Defaults to
     * 0.2.
     * 
     * @param groupPadding
     */
    public void setGroupPadding(Number groupPadding) {
        this.groupPadding = groupPadding;
    }

    /**
     * @see #setGroupPadding(Number)
     * @return The padding between each value group.
     */
    public Number getGroupPadding() {
        return groupPadding;
    }

    @Override
    public ChartType getChartType() {
        return ChartType.LINE;
    }

}
