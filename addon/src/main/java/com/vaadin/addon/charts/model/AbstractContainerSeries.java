package com.vaadin.addon.charts.model;

import java.util.HashSet;
import java.util.Set;

import com.vaadin.data.util.IndexedContainer;

/**
 * Abstract IndexedContainer based Series
 */
public abstract class AbstractContainerSeries extends IndexedContainer
        implements Series {

    private final Set<Object> defaultPropertyIds = new HashSet<Object>();
    private String name;
    private ChartType type;
    private transient Configuration configuration;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ChartType getType() {
        return type;
    }

    @Override
    public void setType(ChartType type) {
        this.type = type;
    }

    /**
     * Add a property ID for which it's name doesn't need to be serialized.<br/>
     * <br/>
     * Ex. Instead of serializing series like following<br/>
     * &nbsp;&nbsp;&nbsp;"data": [{"name": "MSIE","y": 55.11}, {"name":
     * "Firefox","y": 21.63},...<br/>
     * It is possible to mark the "name" and "y" properties as "default" and
     * thus the serializing is done without default identifiers, e.g.<br/>
     * &nbsp;&nbsp;&nbsp;"data": [{"MSIE", 55.11}, {"Firefox", 21.63},...
     * 
     * @param propertyId
     */
    public void addDefaultPropertyId(Object propertyId) {
        defaultPropertyIds.add(propertyId);
    }

    /**
     * @see #addDefaultPropertyId(Object)
     * @return
     */
    public Set<Object> getDefaultPropertyIds() {
        return defaultPropertyIds;
    }

    /**
     * Returns whether this container contains only IDs that are treated as
     * default IDs
     * 
     * @param containerPropertyIds
     * @return
     */
    public boolean containsOnlyDefaultPropertyIds() {
        for (Object pid : getContainerPropertyIds()) {
            if (!defaultPropertyIds.contains(pid)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes all the default property ids
     */
    public void clearDefaultPropertyIds() {
        defaultPropertyIds.clear();
    }

    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * @return the {@link Configuration} that this series is linked to.
     */
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Immediately adds a point to the chart without adding it to the container.
     * The point should be added to the container using {@link #addItem()}.
     * 
     * @param x
     * @param y
     */
    public void addPoint(Number x, Number y) {
        if (configuration != null) {
            configuration.fireDataAdded(this, new DataSeriesItem(x, y));
        }
    }

    /**
     * Immediately adds a point to the chart without adding it to the container.
     * The point should be added to the container using {@link #addItem()}.
     * 
     * @param name
     * @param y
     */
    public void addPoint(String name, Number y) {
        if (configuration != null) {
            configuration.fireDataAdded(this, new DataSeriesItem(name, y));
        }
    }
}
