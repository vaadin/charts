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
 * The ChartSelectionEvent class stores information on selection events on the
 * chart's area. Only selections along the X axis are supported.
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
     * @return the X coordinate where the selection started.
     */
    public Double getSelectionStart() {
        return selectionStart;
    }

    /**
     * @return the X coordinate where the selection ended.
     */
    public Double getSelectionEnd() {
        return selectionEnd;
    }
}
