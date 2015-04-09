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
 * Configuration for ZAxis. Currently only min and configuration is supported.
 */
public class ZAxis extends AbstractConfigurationObject {

    private Number min;
    private Number max;

    /**
     * @see #setMin(Number)
     * @return the minimum value of the z-axis or null
     */
    public Number getMin() {
        return min;
    }

    /**
     * The minimum value of the axis. If null the min value is automatically
     * calculated. Defaults to null.
     *
     * @param min
     */
    public void setMin(Number min) {
        this.min = min;
    }

    /**
     * @see #setMax(Number)
     * @return the maximum value of the z-axis or null
     */
    public Number getMax() {
        return max;
    }

    /**
     * The maximum value of the axis. If null the max value is automatically
     * calculated. Defaults to null.
     *
     * @param max
     */
    public void setMax(Number max) {
        this.max = max;
    }
}
