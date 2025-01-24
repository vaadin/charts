/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts;

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
