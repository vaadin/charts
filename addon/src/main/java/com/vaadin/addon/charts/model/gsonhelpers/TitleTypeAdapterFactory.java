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
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.vaadin.addon.charts.model.Title;

public class TitleTypeAdapterFactory implements TypeAdapterFactory {

    public TitleTypeAdapterFactory() {
    }

    @SuppressWarnings("unchecked")
    public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return Title.class == type.getRawType() ? (TypeAdapter<T>) customizeMyClassAdapter(
                gson, (TypeToken<Title>) type) : null;
    }

    private TypeAdapter<Title> customizeMyClassAdapter(Gson gson,
            TypeToken<Title> type) {
        final TypeAdapter<Title> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson
                .getAdapter(JsonElement.class);
        return new TypeAdapter<Title>() {
            @Override
            public void write(JsonWriter out, Title value) throws IOException {
                // null for text is significant, else there will be
                // "Chart Title" as default
                if (value != null && value.getText() == null) {
                    boolean serializeNulls = out.getSerializeNulls();
                    out.setSerializeNulls(true);
                    out.beginObject();
                    out.name("text");
                    out.nullValue();
                    out.endObject();
                    out.setSerializeNulls(serializeNulls);
                } else {
                    elementAdapter.write(out, delegate.toJsonTree(value));
                }
            }

            // This is never used
            @Override
            public Title read(JsonReader in) throws IOException {
                JsonElement tree = elementAdapter.read(in);
                return delegate.fromJsonTree(tree);
            }
        };
    }

}