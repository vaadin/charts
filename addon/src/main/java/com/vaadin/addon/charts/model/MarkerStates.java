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
 * States for markers (like symbols in plot line)
 */
public class MarkerStates extends States {
    
    /**
     * Creates new States object with given hoverState
     * 
     * @param hoverState
     */
    public MarkerStates(State hoverState) {
        super(hoverState);
    }
}
