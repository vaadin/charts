package com.vaadin.addon.charts.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Pane Container to allow multiple Panes
 */
public class PaneList extends AbstractConfigurationObject {
    private final List<Pane> paneList = new ArrayList<Pane>();

    /**
     * Get number or panes in list
     * 
     * @return
     */
    public int getNumberOfPanes() {
        return paneList.size();
    }

    /**
     * Get Pane with given index
     * 
     * @param index
     *            Index of pane
     * @return Pane with given index
     */
    public Pane getPane(int index) {
        return paneList.get(index);
    }

    /**
     * Get pane list. Use this only for serialization.
     * 
     * @return
     */
    public List<Pane> getPanes() {
        return paneList;
    }

    /**
     * Add new pane to list
     * 
     * @param pane
     *            Pane added
     */
    public void addPane(Pane pane) {
        paneList.add(pane);
    }

    /**
     * Add new pane to list
     * 
     * @param pane
     */
    public void removePane(Pane pane) {
        paneList.remove(pane);
    }
}
