package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

/**
 * Enum representing different axis types. Can be one of LINEAR, LOGARITHMIC or
 * DATETIME. In a DATETIME axis, the numbers are given in milliseconds, and tick
 * marks are placed on appropriate values like full hours or days. The default
 * for new axes is LINEAR.
 */
public enum AxisType implements ChartEnum {
    LINEAR("linear"), LOGARITHMIC("logarithmic"), DATETIME("datetime");

    private String type;

    private AxisType(String type) {
        this.type = type;
    }

    public String toString() {
        return type;
    }

}
