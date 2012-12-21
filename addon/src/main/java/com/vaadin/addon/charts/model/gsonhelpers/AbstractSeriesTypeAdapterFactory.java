package com.vaadin.addon.charts.model.gsonhelpers;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.vaadin.addon.charts.model.AbstractSeries;

public class AbstractSeriesTypeAdapterFactory implements TypeAdapterFactory {

    public AbstractSeriesTypeAdapterFactory() {
    }

    @SuppressWarnings("unchecked")
    public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return AbstractSeries.class.isAssignableFrom(type.getRawType()) ? (TypeAdapter<T>) customizeMyClassAdapter(
                gson, (TypeToken<AbstractSeries>) type) : null;
    }

    private TypeAdapter<AbstractSeries> customizeMyClassAdapter(Gson gson,
            TypeToken<AbstractSeries> type) {
        final TypeAdapter<AbstractSeries> delegate = gson.getDelegateAdapter(
                this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson
                .getAdapter(JsonElement.class);
        return new TypeAdapter<AbstractSeries>() {
            @Override
            public void write(JsonWriter out, AbstractSeries value)
                    throws IOException {
                JsonObject tree = (JsonObject) delegate.toJsonTree(value);

                // "flatten" series specific plot options at series level. see
                // Higchart API for details
                if (value.getPlotOptions() != null) {
                    JsonObject plotOptionsJson = (JsonObject) tree
                            .remove("plotOptions");
                    Set<Entry<String, JsonElement>> entrySet = plotOptionsJson
                            .entrySet();
                    for (Entry<String, JsonElement> entry : entrySet) {
                        tree.add(entry.getKey(), entry.getValue());
                    }
                    tree.addProperty("type", value.getPlotOptions()
                            .getChartType().toString());
                }
                elementAdapter.write(out, tree);
            }

            // This is never used
            @Override
            public AbstractSeries read(JsonReader in) throws IOException {
                JsonElement tree = elementAdapter.read(in);
                return delegate.fromJsonTree(tree);
            }
        };
    }

}