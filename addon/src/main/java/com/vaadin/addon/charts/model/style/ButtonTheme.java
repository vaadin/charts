package com.vaadin.addon.charts.model.style;

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
