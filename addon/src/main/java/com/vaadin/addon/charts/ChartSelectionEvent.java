package com.vaadin.addon.charts;

public class ChartSelectionEvent extends com.vaadin.ui.Component.Event {

    private final Double selectionStart;
    private final Double selectionEnd;

    public ChartSelectionEvent(Chart source, double selectionStart,
            double selectionEnd) {
        super(source);
        this.selectionStart = selectionStart;
        this.selectionEnd = selectionEnd;
    }

    public Double getSelectionStart() {
        return selectionStart;
    }

    public Double getSelectionEnd() {
        return selectionEnd;
    }
}
