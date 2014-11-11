package com.vaadin.addon.charts;

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

    /**
     * @return the Y coordinate where the selection started.
     */
    public Double getValueStart() {
        return valueStart;
    }

    /**
     * @return the Y coordinate where the selection ended.
     */
    public Double getValueEnd() {
        return valueEnd;
    }
}
