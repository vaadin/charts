/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vaadin.data.Container;

import java.util.HashMap;
import java.util.Map;

/**
 * A series that wraps a Vaadin Container. It is mutable only via the wrapped
 * container. <br>
 * Wrapped Container should always have:
 * <ul>
 * <li>a property for y values</li>
 * <li>or a property for both high and low values</li>
 * </ul>
 * This can be achieved by using a container:
 * <ul>
 * <li>with a {@value #SERIES_DEFAULT_ATTRIBUTE2} property</li>
 * <li>or with a custom property for y values and use
 * {@link #setYPropertyId(Object)} to set the custom property in the
 * ContainerDataSeries instance</li>
 * <li>or with a custom property for both high and low values and use
 * {@link #setHighPropertyId(Object)} and {@link #setLowPropertyId(Object)} to
 * set the custom properties in the ContainerDataSeries instance</li>
 * </ul>
 */
public class ContainerDataSeries extends AbstractSeries {

    @JsonIgnore
    private final Container vaadinContainer;

    // these need to be public for proper json serialization
    // see ContainerDataSeriesSerializer
    public static final String SERIES_DEFAULT_ATTRIBUTE1 = "x";
    public static final String SERIES_DEFAULT_ATTRIBUTE2 = "y";

    private static final String NAMEATTRIBUTE = "name";

    public static final String HIGH_PROPERTY = "high";
    public static final String LOW_PROPERTY = "low";
    public static final String OPEN_PROPERTY = "open";
    public static final String CLOSE_PROPERTY = "close";
    @JsonIgnore
    private final Map<String, Object> attributeToPropertyIdMap;

    /**
     * Constructs a ContainerDataSeries wrapping the given Container
     * 
     * @param container
     *            A container to wrap.
     */
    public ContainerDataSeries(Container container) {
        vaadinContainer = container;
        attributeToPropertyIdMap = new HashMap<String, Object>();
    }

    /**
     * Adds mapping that translates a property ID in the container into the
     * chart's X attribute
     * 
     * @param propertyId
     *            Id of a property.
     */
    public void setXPropertyId(Object propertyId) {
        attributeToPropertyIdMap.put(SERIES_DEFAULT_ATTRIBUTE1, propertyId);
    }

    /**
     * Adds mapping that translates a property ID in the container into the
     * chart's Y attribute
     * 
     * @param propertyId
     *            Id of a property.
     */
    public void setYPropertyId(Object propertyId) {
        attributeToPropertyIdMap.put(SERIES_DEFAULT_ATTRIBUTE2, propertyId);
    }

    /**
     * Adds mapping that translates a property ID in the container into the
     * chart's name attribute
     * 
     * @param propertyId
     *            Id of a property.
     */
    public void setNamePropertyId(Object propertyId) {
        attributeToPropertyIdMap.put(NAMEATTRIBUTE, propertyId);
    }

    /**
     * Adds mapping that translates a property ID in the container into the
     * chart's low attribute.
     *
     * @param propertyId
     *            Id of a property.
     */
    public void setLowPropertyId(Object propertyId) {
        attributeToPropertyIdMap.put(LOW_PROPERTY, propertyId);
    }

    /**
     * Adds mapping that translates a property ID in the container into the
     * chart's high attribute.
     *
     * @param propertyId
     *            Id of a property.
     */
    public void setHighPropertyId(Object propertyId) {
        attributeToPropertyIdMap.put(HIGH_PROPERTY, propertyId);
    }

    /**
     * Adds mapping that translates a property ID in the container into the
     * chart's open attribute.
     *
     * @param propertyId
     *            Id of a property.
     */
    public void setOpenPropertyId(Object propertyId) {
        attributeToPropertyIdMap.put(OPEN_PROPERTY, propertyId);
    }

    /**
     * Adds mapping that translates a property ID in the container into the
     * chart's close attribute.
     *
     * @param propertyId
     *            Id of a property.
     */
    public void setClosePropertyId(Object propertyId) {
        attributeToPropertyIdMap.put(CLOSE_PROPERTY, propertyId);
    }


    /**
     * Adds a mapping between a chart attribute (like x, y, name, id ...) and a
     * property id in the container.
     * 
     * @param chartAttribute
     *            Attribute of a chart.
     * @param propertyId
     *            Id of a property.
     */
    public void addAttributeToPropertyIdMapping(String chartAttribute,
            Object propertyId) {
        attributeToPropertyIdMap.put(chartAttribute, propertyId);
    }

    /**
     * Returns the underlying container.
     *
     * @return the wrapped Vaadin container.
     */
    public Container getVaadinContainer() {
        return vaadinContainer;
    }

    /**
     * Returns current mappings. The map may be modified and it will affect this
     * object.
     *
     * @return mappings between chart attributes (keys) and property IDs
     *         (values) in the container
     */
    public Map<String, Object> getAttributeToPropertyIdMap() {
        return attributeToPropertyIdMap;
    }

}
