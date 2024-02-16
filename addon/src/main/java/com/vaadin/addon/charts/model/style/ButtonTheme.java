/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model.style;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.serializers.ButtonThemeWidthFilter;

public class ButtonTheme extends AbstractConfigurationObject {

    public final static Number DEFAULT_WIDTH = 32;

    private Color fill;
    private Color stroke;
    @JsonProperty("stroke-width")
    private Number strokeWidth;
    private Style style;
    @JsonInclude(value = Include.CUSTOM, valueFilter = ButtonThemeWidthFilter.class)
    private Number width = DEFAULT_WIDTH;

    public Color getFill() {
        return fill;
    }

    public void setFill(Color fill) {
        this.fill = fill;
    }

    public Color getStroke() {
        return stroke;
    }

    public void setStroke(Color stroke) {
        this.stroke = stroke;
    }

    public Number getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(Number strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Number getWidth() {
        return width;
    }

    public void setWidth(Number width) {
        this.width = width;
    }
}
