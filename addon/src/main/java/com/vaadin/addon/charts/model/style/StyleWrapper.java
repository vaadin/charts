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
package com.vaadin.addon.charts.model.style;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;

/**
 * Helper class when JSON structure needs object with just style field
 */
public class StyleWrapper extends AbstractConfigurationObject {
    private Style style = new Style();

    /**
     * Return the style object
     * 
     * @return
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Set the style object
     * 
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }
}
