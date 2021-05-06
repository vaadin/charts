package com.vaadin.addon.charts;

/*-
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2019 Vaadin Ltd
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

import com.vaadin.event.SerializableEventListener;

/**
 * Listener interface for checkbox click events
 */
@FunctionalInterface
public interface CheckboxClickListener extends SerializableEventListener {

    /**
     * Called when the user has clicked a checkbox in the legend
     * 
     * @param event
     *            the {@link CheckboxClickEvent} containing information on the
     *            event.
     */
    public void onClick(CheckboxClickEvent event);

}
