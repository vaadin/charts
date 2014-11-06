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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.vaadin.addon.charts.model.DataSeriesItem;

public class DataSeriesItemTypeAdapterFactory implements TypeAdapterFactory {

    public DataSeriesItemTypeAdapterFactory() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return DataSeriesItem.class.isAssignableFrom(type.getRawType()) ? (TypeAdapter<T>) customizeMyClassAdapter(
                gson, (TypeToken<DataSeriesItem>) type) : null;
    }

    private TypeAdapter<DataSeriesItem> customizeMyClassAdapter(Gson gson,
            TypeToken<DataSeriesItem> type) {
        final TypeAdapter<DataSeriesItem> delegate = gson.getDelegateAdapter(
                this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson
                .getAdapter(JsonElement.class);
        return new TypeAdapter<DataSeriesItem>() {
            @Override
            public void write(JsonWriter out, DataSeriesItem value)
                    throws IOException {
                if (value.isCustomized()) {
                    elementAdapter.write(out, delegate.toJsonTree(value));
                } else {
                    Number x = value.getX();
                    Number y = value.getY();
                    Number z = value.getZ();
                    if (x != null) {
                        JsonArray jsonArray = new JsonArray();
                        jsonArray.add(new JsonPrimitive(x));
                        if (y != null) {
                            jsonArray.add(new JsonPrimitive(y));
                            if (z != null) {
                                jsonArray.add(new JsonPrimitive(z));
                            }
                        } else if (value.getLow() != null) {
                            jsonArray.add(new JsonPrimitive(value.getLow()));
                            jsonArray.add(new JsonPrimitive(value.getHigh()));
                        } else {
                            jsonArray.add(JsonNull.INSTANCE);
                            jsonArray.add(JsonNull.INSTANCE);
                        }
                        elementAdapter.write(out, jsonArray);
                    } else {
                        // If no x set, make it like list series, just number or
                        // min-max pairs
                        if (y != null) {
                            elementAdapter.write(out, new JsonPrimitive(y));
                        } else {
                            JsonArray jsonArray = new JsonArray();
                            jsonArray.add(new JsonPrimitive(value.getLow()));
                            jsonArray.add(new JsonPrimitive(value.getHigh()));
                            elementAdapter.write(out, jsonArray);
                        }
                    }
                }
            }

            // This is never used
            @Override
            public DataSeriesItem read(JsonReader in) throws IOException {
                JsonElement tree = elementAdapter.read(in);
                return delegate.fromJsonTree(tree);
            }
        };
    }

}