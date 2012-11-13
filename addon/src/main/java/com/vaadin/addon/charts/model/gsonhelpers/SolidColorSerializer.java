package com.vaadin.addon.charts.model.gsonhelpers;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vaadin.addon.charts.model.style.SolidColor;

/**
 * Serializer for SolidColors
 */
public class SolidColorSerializer implements
        JsonSerializer<SolidColor> {
    @Override
    public JsonElement serialize(SolidColor src, Type typeOfSrc,
            JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

}