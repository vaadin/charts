package com.vaadin.addon.charts.model;

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

import java.util.HashMap;
import java.util.Map;

import com.vaadin.data.Container;

/**
 * A series that wraps a Vaadin Container. It is mutable only via the wrapped
 * container.
 */
public class ContainerDataSeries extends AbstractSeries {

    private final Container vaadinContainer;

    public static final String SERIES_DEFAULT_ATTRIBUTE1 = "x";
    public static final String SERIES_DEFAULT_ATTRIBUTE2 = "y";

    private static final String NAMEATTRIBUTE = "name";

    private final Map<String, Object> attributeToPropertyIdMap;

    /**
     * Constructs a ContainerDataSeries wrapping the given Container
     * 
     * @param container
     */
    public ContainerDataSeries(Container container) {
        vaadinContainer = container;
        attributeToPropertyIdMap = new HashMap<String, Object>();
    }

    /**
     * Adds mapping that translates a property ID in the container into the
     * chart's X attribute
     * 
     * @param containerPId
     */
    public void setXPropertyId(Object containerPId) {
        attributeToPropertyIdMap.put(SERIES_DEFAULT_ATTRIBUTE1, containerPId);
    }

    /**
     * Adds mapping that translates a property ID in the container into the
     * chart's Y attribute
     * 
     * @param containerPId
     */
    public void setYPropertyId(Object containerPId) {
        attributeToPropertyIdMap.put(SERIES_DEFAULT_ATTRIBUTE2, containerPId);
    }

    /**
     * Adds mapping that translates a property ID in the container into the
     * chart's name attribute
     * 
     * @param propertyId
     */
    public void setNamePropertyId(Object propertyId) {
        attributeToPropertyIdMap.put(NAMEATTRIBUTE, propertyId);
    }

    /**
     * Adds a mapping between a chart attribute (like x, y, name, id ...) and a
     * property id in the container.
     * 
     * @param chartAttribute
     * @param containerId
     */
    public void addAttributeToPropertyIdMapping(String chartAttribute,
            Object containerPId) {
        attributeToPropertyIdMap.put(chartAttribute, containerPId);
    }

    /**
     * @return the wrapped Vaadin container.
     */
    public Container getVaadinContainer() {
        return vaadinContainer;
    }

    /**
     * @return mappings between chart attributes (keys) and property IDs
     *         (values) in the container
     */
    public Map<String, Object> getAttributeToPropertyIdMap() {
        return attributeToPropertyIdMap;
    }

}
