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
package com.vaadin.demo.chartplugin.model;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.style.Color;

public class ValueRange extends AbstractConfigurationObject {

    protected Number from;
    protected Number to;
    protected Color color;

    public ValueRange() {
    }

    public ValueRange(Number from, Number to) {
        this.from = from;
        this.to = to;
    }

    public ValueRange(Number from, Number to, Color color) {
        this(from, to);
        this.color = color;
    }

    public Number getFrom() {
        return from;
    }

    public void setFrom(Number from) {
        this.from = from;
    }

    public Number getTo() {
        return to;
    }

    public void setTo(Number to) {
        this.to = to;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
