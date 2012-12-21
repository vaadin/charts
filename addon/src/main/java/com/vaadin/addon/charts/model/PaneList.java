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
