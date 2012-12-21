package com.vaadin.addon.charts;

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
 * Server side event class for drag selection events on the chart's area
 */
public class ChartSelectionEvent extends com.vaadin.ui.Component.Event {

    private final Double selectionStart;
    private final Double selectionEnd;

    /**
     * Construct a ChartSelectionEvent
     * 
     * @param source
     * @param selectionStart
     * @param selectionEnd
     */
    public ChartSelectionEvent(Chart source, double selectionStart,
            double selectionEnd) {
        super(source);
        this.selectionStart = selectionStart;
        this.selectionEnd = selectionEnd;
    }

    /**
     * x-axis value where the drag selection started
     * 
     * @return
     */
    public Double getSelectionStart() {
        return selectionStart;
    }

    /**
     * x-axis value where the drag selection ended
     * 
     * @return
     */
    public Double getSelectionEnd() {
        return selectionEnd;
    }
}
