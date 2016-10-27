package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vaadin.server.data.DataSource;
import com.vaadin.server.data.Query;

public class ChartDataSeries<T> extends AbstractSeries {


    @JsonIgnore
    private final DataSource<T> vaadinDS;

    public static final String SERIES_DEFAULT_ATTRIBUTE1 = "x";
    public static final String SERIES_DEFAULT_ATTRIBUTE2 = "y";
    private static final String NAME_ATTRIBUTE = "name";

    public static final String HIGH_PROPERTY = "high";
    public static final String LOW_PROPERTY = "low";
    public static final String OPEN_PROPERTY = "open";
    public static final String CLOSE_PROPERTY = "close";

    @JsonIgnore
    private final Map<String, Function<T, Object>> chartAttriubteToCallback;


    public ChartDataSeries(DataSource<T> ds) {
        vaadinDS = ds;
        chartAttriubteToCallback = new HashMap<String, Function<T, Object>>();
    }

    /**
     * Sets mapping for chart property to a callback how this value
     * will be populated in the chart.
     * @param callBack
     */
    public void addDataProvider(String field, Function<T, Object> callBack) {
        chartAttriubteToCallback.put(field, callBack);
    }

    /**
     * Sets mapping for X chart property to value.
     * @param callBack
     */
    public void setXValueProvider(Function<T, Object> callBack) {
        chartAttriubteToCallback.put(SERIES_DEFAULT_ATTRIBUTE1, callBack);
    }

    /**
     * Sets mapping for Y chart property to value.
     * @param callBack
     */
    public void setYValueProvider(Function<T, Object> callBack) {
        chartAttriubteToCallback.put(SERIES_DEFAULT_ATTRIBUTE2, callBack);
    }

    /**
     * Sets mapping for name chart property to value.
     * @param callBack
     */
    public void setNameProvider(Function<T, Object> callBack) {
        chartAttriubteToCallback.put(NAME_ATTRIBUTE, callBack);
    }

    /**
     * Sets mapping for low chart property to value.
     * @param callBack
     */
    public void setLowDataProvider(Function<T, Object> callBack) {
        chartAttriubteToCallback.put(LOW_PROPERTY, callBack);
    }
    /**
     * Sets mapping for high chart property to value.
     * @param callBack
     */
    public void setHighDataProvider(Function<T, Object> callBack) {
        chartAttriubteToCallback.put(HIGH_PROPERTY, callBack);
    }
    /**
     * Sets mapping for open chart property to value.
     * @param callBack
     */
    public void setOpenDataProvider(Function<T, Object> callBack) {
        chartAttriubteToCallback.put(OPEN_PROPERTY, callBack);
    }
    /**
     * Sets mapping for high chart property to value.
     * @param callBack
     */
    public void setCloseDataProvider(Function<T, Object> callBack) {
        chartAttriubteToCallback.put(CLOSE_PROPERTY, callBack);
    }
    /**
     * Returns the underlying Vaadin data source.
     *
     * @return the wrapped Vaadin data source.
     */
    public DataSource getVaadinDataSource() {
        return vaadinDS;
    }

    /**
     * Returns current mappings. The map may be modified and it will affect this
     * object.
     *
     * @return mappings between chart attributes (keys) and property IDs
     * (values) in the container
     */
    public Map<String, Function<T, Object>> getAttributeToPropertyIdMap() {
        return chartAttriubteToCallback;
    }

    /**
     * Returns a list mappings between chart attributes(keys) and values.
     * For example:
     *  x->1, x->2, y->2, y->3 for linear chart
     * @return
     */
    public List<Map<String, Object>> getValues() {
        List<Map<String, Object>> list = vaadinDS.fetch(new Query()).map((item) -> {
            Map<String, Object> tmp = new HashMap<String, Object>();
            for (Map.Entry<String, Function<T, Object>> entry : chartAttriubteToCallback.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue().apply(item);
                tmp.put(key, value);
            }
            return tmp;
        }).collect(Collectors.toList());

        return list;
    }

    /**
     * Returns a set of chart attributes(keys).
     * @return
     */
    public Set<String> getChartAttributes() {
        return chartAttriubteToCallback.keySet();
    }
}
