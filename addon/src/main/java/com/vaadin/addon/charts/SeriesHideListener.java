package com.vaadin.addon.charts;

/*
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
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

import java.io.Serializable;

/**
 * Listener interface for series hide events
 */
public interface SeriesHideListener extends Serializable {

    /**
     * Called when a series is hidden
     *
     * @param event
     *            the {@link SeriesHideEvent} containing information on the
     *            event.
     */
    public void onHide(SeriesHideEvent event);

}
