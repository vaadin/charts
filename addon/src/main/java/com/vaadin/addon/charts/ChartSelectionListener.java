package com.vaadin.addon.charts;

/**
 * Listener interface for chart drag selections events
 */
public interface ChartSelectionListener {

    /**
     * Selection happened
     * 
     * @param event
     */
    public void onSelection(ChartSelectionEvent event);

}
