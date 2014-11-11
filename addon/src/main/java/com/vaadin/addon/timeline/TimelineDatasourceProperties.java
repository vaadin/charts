package com.vaadin.addon.timeline;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
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

import java.awt.Color;
import java.io.Serializable;

import com.vaadin.data.Container;

/**
 * Class for storing graph related properties like color,thickness etc. Used
 * internally by the Timeline
 * 
 */
@SuppressWarnings("serial")
public class TimelineDatasourceProperties implements Serializable {

    private final Container.Indexed datasource;

    private Object timestampProperty = Timeline.PropertyId.TIMESTAMP;

    private Object valueProperty = Timeline.PropertyId.VALUE;

    private boolean visible = true;

    private Color color = Color.BLACK;

    private Color fillColor = new Color(0, 0, 0, 0);

    private String caption = "Unknown graph";

    private Color browserColor = new Color(0x00, 0x67, 0xDD);

    private Color browserFillColor = new Color(0xED, 0xF7, 0xFF);

    private String legendUnit = "";

    private double thickness = 2;

    private boolean capsVisible = false;

    public TimelineDatasourceProperties(Container.Indexed datasource) {
        this.datasource = datasource;
    }

    public Container.Indexed getDatasource() {
        return datasource;
    }

    public Object getTimestampProperty() {
        return timestampProperty;
    }

    public void setTimestampProperty(Object timestampProperty) {
        this.timestampProperty = timestampProperty;
    }

    public Object getValueProperty() {
        return valueProperty;
    }

    public void setValueProperty(Object valueProperty) {
        this.valueProperty = valueProperty;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Color getBrowserColor() {
        return browserColor;
    }

    public void setBrowserColor(Color browserColor) {
        this.browserColor = browserColor;
    }

    public Color getBrowserFillColor() {
        return browserFillColor;
    }

    public void setBrowserFillColor(Color browserFillColor) {
        this.browserFillColor = browserFillColor;
    }

    public String getLegendUnit() {
        return legendUnit;
    }

    public void setLegendUnit(String legendUnit) {
        this.legendUnit = legendUnit;
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public boolean isCapsVisible() {
        return capsVisible;
    }

    public void setCapsVisible(boolean capsVisible) {
        this.capsVisible = capsVisible;
    }

}
