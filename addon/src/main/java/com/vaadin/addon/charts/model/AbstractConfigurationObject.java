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

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaadin.addon.charts.model.gsonhelpers.AbstractSeriesTypeAdapterFactory;
import com.vaadin.addon.charts.model.gsonhelpers.AxisListSerializer;
import com.vaadin.addon.charts.model.gsonhelpers.ChartEnumSerializer;
import com.vaadin.addon.charts.model.gsonhelpers.ContainerDataSeriesSerializer;
import com.vaadin.addon.charts.model.gsonhelpers.DataSeriesItemTypeAdapterFactory;
import com.vaadin.addon.charts.model.gsonhelpers.PaneListSerializer;
import com.vaadin.addon.charts.model.gsonhelpers.SolidColorSerializer;
import com.vaadin.addon.charts.model.gsonhelpers.TitleTypeAdapterFactory;
import com.vaadin.addon.charts.model.style.SolidColor;

/**
 * Abstract base class for model classes to be serialized to JSON. Mainly
 * com.vaadin.addon.charts.model.Configuration and the stuff it uses.
 */
public abstract class AbstractConfigurationObject implements Serializable {

    private static Gson gson;
    static {
        // GSON is thread safe so we can use shared GSON instance
        gson = createGsonBuilder().create();
    }

    /**
     * Returns default GSON builder for configuration serializer.
     */
    public static GsonBuilder createGsonBuilder() {
        GsonBuilder builder = new GsonBuilder();
        // uncomment if you wish to debug generated json
        builder.setPrettyPrinting();
        builder.registerTypeHierarchyAdapter(ChartEnum.class,
                new ChartEnumSerializer());
        builder.registerTypeHierarchyAdapter(SolidColor.class,
                new SolidColorSerializer());
        builder.registerTypeHierarchyAdapter(AxisList.class,
                new AxisListSerializer());
        builder.registerTypeHierarchyAdapter(PaneList.class,
                new PaneListSerializer());
        builder.registerTypeAdapter(ContainerDataSeries.class,
                new ContainerDataSeriesSerializer());
        builder.registerTypeAdapterFactory(new DataSeriesItemTypeAdapterFactory());
        builder.registerTypeAdapterFactory(new AbstractSeriesTypeAdapterFactory());
        builder.registerTypeAdapterFactory(new TitleTypeAdapterFactory());
        return builder;
    }

    /**
     * This method can be used to configure the gson object used to serialize
     * configuration objects to client side. If users have made their extensions
     * to Highcharts library and wish to build a typed Java API for it, adding
     * custom serializers might be needed.
     * 
     * @param customGson
     * @see #createGsonBuilder()
     */
    public static void setGsonInstance(Gson customGson) {
        if (customGson == null) {
            throw new IllegalArgumentException("GSON serializer cannot be null");
        }
        gson = customGson;
    }

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
