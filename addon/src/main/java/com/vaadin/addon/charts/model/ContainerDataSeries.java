package com.vaadin.addon.charts.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.data.Container;

/**
 * Series that wraps Vaadin Container, is mutable only via wrapped container
 */
public class ContainerDataSeries extends DataSeries {

    private String name;
    private ChartType type;

    private transient final Container container;

    public transient static final String SERIES_DEFAULT_ATTRIBUTE1 = "x";
    public transient static final String SERIES_DEFAULT_ATTRIBUTE2 = "y";

    private transient static final String XATTRIBUTE = "x";
    private transient static final String YATTRIBUTE = "y";
    private transient static final String NAMEATTRIBUTE = "name";

    private transient final Map<String, Object> attributeToPropertyIdMap;

    public ContainerDataSeries(Container container) {
        this.container = container;
        attributeToPropertyIdMap = new HashMap<String, Object>();
    }

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
     * Add mapping that translates container's property id into chart's x
     * attribute
     * 
     * @param containerPId
     */
    public void setXAttributesContainerId(Object containerPId) {
        attributeToPropertyIdMap.put(XATTRIBUTE, containerPId);
    }

    /**
     * Add mapping that translates container's property id into chart's y
     * attribute
     * 
     * @param containerPId
     */
    public void setYAttributesContainerId(Object containerPId) {
        attributeToPropertyIdMap.put(YATTRIBUTE, containerPId);
    }

    /**
     * Add mapping that translates container's property id into chart's name
     * attribute
     * 
     * @param containerPId
     */
    public void setNameAttributesContainerId(Object containerPId) {
        attributeToPropertyIdMap.put(NAMEATTRIBUTE, containerPId);
    }

    /**
     * Add a mapping between chart attribute (like x, y, name, id ...) and
     * container's property id
     * 
     * @param chartAttribute
     * @param containerId
     */
    public void addAttributeToPropertyIdMapping(String chartAttribute,
            Object containerPId) {
        attributeToPropertyIdMap.put(chartAttribute, containerPId);
    }

    /**
     * Returns wrapped Vaadin container
     * 
     * @return
     */
    public Container getVaadinContainer() {
        return container;
    }

    /**
     * Return mappings between chart attributes (keys) and Vaadin container's
     * property id's (alues)
     * 
     * @return
     */
    public Map<String, Object> getAttributeToPropertyIdMap() {
        return attributeToPropertyIdMap;
    }

    @Override
    public void addData(Number[][] entries) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setData(String[] mainCategories, Number[] entries) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setData(String[] mainCategories, Number[] entries,
            Color[] colors) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setData(Number... numericdata) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setData(Object[][] objects) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setData(List<DataSeriesItem> data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addData(DataSeriesItem item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addData(DataSeriesItem item, boolean updateChartImmediately) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateData(DataSeriesItem item) {
        throw new UnsupportedOperationException();
    }
}
