package com.vaadin.addon.charts.model;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaadin.addon.charts.model.gsonhelpers.AxisListSerializer;
import com.vaadin.addon.charts.model.gsonhelpers.ChartEnumSerializer;
import com.vaadin.addon.charts.model.gsonhelpers.ContainerSeriesSerializer;
import com.vaadin.addon.charts.model.gsonhelpers.PaneListSerializer;
import com.vaadin.addon.charts.model.gsonhelpers.SolidColorSerializer;
import com.vaadin.addon.charts.model.style.SolidColor;

/**
 * Abstract base class for model classes to be serialized to json. Mainly
 * com.vaadin.addon.charts.model.Configuration and stuff it uses.
 */
public abstract class AbstractConfigurationObject implements Serializable {

    private static Gson gson;
    static {
        // GSON is thread safe so we can use shared gson instance
        gson = createGsonBuilder().create();
    }

    /**
     * Returns default gson builder for configuration serializer.
     * 
     * @return
     */
    public static GsonBuilder createGsonBuilder() {
        GsonBuilder builder = new GsonBuilder();
        // TODO remove this before final release
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
                new ContainerSeriesSerializer());

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
