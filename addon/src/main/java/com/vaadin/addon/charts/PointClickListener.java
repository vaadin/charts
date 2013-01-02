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
 * Listener interface for click events on the data points of the chart
 */
public interface PointClickListener {
    /**
     * Called when a data point is clicked by the user.
     * 
     * @param event
     *            a {@link PointClickEvent} containing information on the click
     */
    public void onClick(PointClickEvent event);

}
