package com.vaadin.addon.charts;

import java.io.Serializable;

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
 * Listener interface for unselect events on the data points of the chart
 */
public interface PointUnselectListener extends Serializable {

    /**
     * Called when a data point is selected
     *
     * @param event a {@link PointUnselectEvent} containing information on the unselected datapoint
     */
    public void onUnselect(PointUnselectEvent event);

}
