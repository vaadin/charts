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
 * Alignment of the title relative to the axis values and more generically
 * vertical alignment. Possible values are BOTTOM("bottom"), LOW("low"),
 * MIDDLE("middle"), HIGH("high"), TOP("top")
 */
public enum VerticalAlign implements ChartEnum {
    BOTTOM("bottom"), LOW("low"), MIDDLE("middle"), HIGH("high"), TOP("top");

    private final String align;

    private VerticalAlign(String align) {
        this.align = align;
    }

    @Override
    public String toString() {
        return align;
    }
}
