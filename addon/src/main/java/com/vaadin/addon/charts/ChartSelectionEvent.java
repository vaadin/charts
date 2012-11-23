package com.vaadin.addon.charts;

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
