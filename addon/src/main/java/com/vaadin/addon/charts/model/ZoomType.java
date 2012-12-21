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
 * Decides in what dimensions the user can zoom by dragging the mouse. Can be
 * one of X("x"), Y("y"), XY("xy"). Defaults to "".
 * 
 */
public enum ZoomType implements ChartEnum {
    X("x"), Y("y"), XY("xy");

    private final String zoomType;

    private ZoomType(String zoomType) {
        this.zoomType = zoomType;
    }

    @Override
    public String toString() {
        return zoomType;
    }
}
