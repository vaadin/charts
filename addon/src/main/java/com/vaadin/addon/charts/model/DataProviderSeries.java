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
import com.vaadin.server.data.DataProvider;
import com.vaadin.server.data.Query;

/**
 * A series which is based on data from a DataProvider.
 * <p>
 * You must use {@link #setY(Function)} to define which part of the data bean to
 * use as <code>y</code> values.
 * <p>
 * Note that even if you use a lazy loading {@link DataProvider}, this series
 * will work in an eager fashion and load all the data from the provider at
 * once.
 */
public class DataProviderSeries<T> extends AbstractSeries {

    @JsonIgnore
    private final DataProvider<T> dataProvider;

    public static final String X_ATTRIBUTE = "x";
    public static final String Y_ATTRIBUTE = "y";
    private static final String NAME_ATTRIBUTE = "name";

    public static final String HIGH_PROPERTY = "high";
    public static final String LOW_PROPERTY = "low";
    public static final String OPEN_PROPERTY = "open";
    public static final String CLOSE_PROPERTY = "close";

    @JsonIgnore
    private final Map<String, Function<T, Object>> chartAttributeToCallback;

    /**
     * Creates a new series using data from the given data provider.
     * <p>
     * Use {@link #setY(Function)} to define a function for extracting the
     * <code>y</code> values from the bean in the provider.
     * 
     * @param dataProvider
     *            the data provider which contains the data
     */
    public DataProviderSeries(DataProvider<T> dataProvider) {
        this.dataProvider = dataProvider;
        chartAttributeToCallback = new HashMap<String, Function<T, Object>>();
    }

    /**
     * Sets the function used for retrieving the value for the given property
     * name from the given data provider.
     * 
     * @param propertyName
     *            the property name
     * @param callBack
     *            the function which retrieves the value for the property
     */
    public void setProperty(String propertyName, Function<T, Object> callBack) {
        chartAttributeToCallback.put(propertyName, callBack);
    }

    /**
     * Sets the function used for retrieving <code>x</code> values from the bean
     * provided by the data provider.
     * <p>
     * How exactly the values are used depends on the used chart type.
     * 
     * @param callBack
     *            the function which retrieves the values
     */
    public void setX(Function<T, Object> callBack) {
        setProperty(X_ATTRIBUTE, callBack);
    }

    /**
     * Sets the function used for retrieving <code>y</code> values from the bean
     * provided by the data provider.
     * <p>
     * How exactly the values are used depends on the used chart type.
     * 
     * @param callBack
     *            the function which retrieves the values
     */
    public void setY(Function<T, Object> callBack) {
        setProperty(Y_ATTRIBUTE, callBack);
    }

    /**
     * Sets the function used for retrieving <code>name</code> values from the
     * bean provided by the data provider.
     * <p>
     * How exactly the values are used depends on the used chart type.
     * 
     * @param callBack
     *            the function which retrieves the values
     */
    public void setPointName(Function<T, Object> callBack) {
        setProperty(NAME_ATTRIBUTE, callBack);
    }

    /**
     * Sets the function used for retrieving <code>low</code> values from the
     * bean provided by the data provider.
     * <p>
     * How exactly the values are used depends on the used chart type.
     * 
     * @param callBack
     *            the function which retrieves the values
     */
    public void setLow(Function<T, Object> callBack) {
        setProperty(LOW_PROPERTY, callBack);
    }

    /**
     * Sets the function used for retrieving <code>high</code> values from the
     * bean provided by the data provider.
     * <p>
     * How exactly the values are used depends on the used chart type.
     * 
     * @param callBack
     *            the function which retrieves the values
     */
    public void setHigh(Function<T, Object> callBack) {
        setProperty(HIGH_PROPERTY, callBack);
    }

    /**
     * Sets the function used for retrieving <code>open</code> values from the
     * bean provided by the data provider.
     * <p>
     * How exactly the values are used depends on the used chart type.
     * 
     * @param callBack
     *            the function which retrieves the values
     */
    public void setOpen(Function<T, Object> callBack) {
        setProperty(OPEN_PROPERTY, callBack);
    }

    /**
     * Sets the function used for retrieving <code>close</code> values from the
     * bean provided by the data provider.
     * <p>
     * How exactly the values are used depends on the used chart type.
     * 
     * @param callBack
     *            the function which retrieves the values
     */
    public void setClose(Function<T, Object> callBack) {
        setProperty(CLOSE_PROPERTY, callBack);
    }

    /**
     * Returns the underlying data provider.
     *
     * @return the underlying data provider.
     */
    public DataProvider<T> getDataProvider() {
        return dataProvider;
    }

    /**
     * Returns a list mappings between chart attributes(keys) and values. For
     * example: x->1, x->2, y->2, y->3 for linear chart
     * 
     * @return
     */
    public List<Map<String, Object>> getValues() {
        List<Map<String, Object>> list = dataProvider.fetch(new Query())
                .map((item) -> {
                    Map<String, Object> tmp = new HashMap<String, Object>();
                    for (Map.Entry<String, Function<T, Object>> entry : chartAttributeToCallback
                            .entrySet()) {
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
     * 
     * @return
     */
    public Set<String> getChartAttributes() {
        return chartAttributeToCallback.keySet();
    }
}
