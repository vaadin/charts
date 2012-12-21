package com.vaadin.addon.charts.model.style;

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
