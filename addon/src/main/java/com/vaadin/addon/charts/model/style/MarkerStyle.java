package com.vaadin.addon.charts.model.style;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;

/**
 * Marker style
 */
public class MarkerStyle extends AbstractConfigurationObject {
    private Color lineColor;

    /**
     * @see #setLineColor(Color)
     * @return
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * The color of the point marker's outline. When null, the series' or
     * point's color is used. Defaults to "#FFFFFF".
     * 
     * @param lineColor
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

}
