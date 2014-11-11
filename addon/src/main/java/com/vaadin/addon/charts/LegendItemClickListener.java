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
 * Listener interface for legend item click events
 */
public interface LegendItemClickListener extends Serializable {

    /**
     * Called when the user click an item in the chart's legend
     * 
     * @param event
     *            the {@link LegendItemClickEvent} containing the name of the
     *            series which legend item was clicked
     */
    public void onClick(LegendItemClickEvent event);

}
