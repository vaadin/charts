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
package com.vaadin.addon.charts;

import com.vaadin.addon.charts.model.ZoomType;

/**
 * The ChartSelectionEvent class stores information on selection events on the
 * chart's area.
 */
public class ChartSelectionEvent extends com.vaadin.ui.Component.Event {

    private final Double selectionStart;
    private final Double selectionEnd;
    private final Double valueStart;
    private final Double valueEnd;

    /**
     * Construct a ChartSelectionEvent
     * 
     * @param source
     * @param selectionStart
     * @param selectionEnd
     * @param valueStart
     * @param valueEnd
     */
    public ChartSelectionEvent(Chart source, double selectionStart,
            double selectionEnd, double valueStart, double valueEnd) {
        super(source);
        this.selectionStart = selectionStart;
        this.selectionEnd = selectionEnd;
        this.valueStart = valueStart;
        this.valueEnd = valueEnd;
    }

    /**
     * This value is undefined and shouldn't be considered if
     * {@link ChartModel#setZoomType(com.vaadin.addon.charts.model.ZoomType)}
     * was set to {@link ZoomType#Y}
     * 
     * @return the X coordinate where the selection started if ZoomType is
     *         {@link ZoomType#X} or {@link ZoomType#XY}.
     */
    public Double getSelectionStart() {
        return selectionStart;
    }

    /**
     * This value is undefined and shouldn't be considered if
     * {@link ChartModel#setZoomType(com.vaadin.addon.charts.model.ZoomType)}
     * was set to {@link ZoomType#Y}
     * 
     * @return the X coordinate where the selection endedif ZoomType is
     *         {@link ZoomType#X} or {@link ZoomType#XY}.
     */
    public Double getSelectionEnd() {
        return selectionEnd;
    }

    /**
     * This value is undefined and shouldn't be considered if
     * {@link ChartModel#setZoomType(com.vaadin.addon.charts.model.ZoomType)}
     * was set to {@link ZoomType#X}
     * 
     * @return the Y coordinate where the selection started if ZoomType is
     *         {@link ZoomType#Y} or {@link ZoomType#XY}.
     */
    public Double getValueStart() {
        return valueStart;
    }

    /**
     * This value is undefined and shouldn't be considered if
     * {@link ChartModel#setZoomType(com.vaadin.addon.charts.model.ZoomType)}
     * was set to {@link ZoomType#X}
     * 
     * @return the Y coordinate where the selection ended if ZoomType is
     *         {@link ZoomType#Y} or {@link ZoomType#XY}.
     */
    public Double getValueEnd() {
        return valueEnd;
    }
}
