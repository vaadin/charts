package com.vaadin.addon.charts.model.style;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;

/**
 * Helper class when JSON structure needs object with just style field
 */
public class StyleWrapper extends AbstractConfigurationObject {
    private Style style = new Style();

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
