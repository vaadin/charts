package com.vaadin.addon.charts.model.gsonhelpers;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vaadin.addon.charts.model.ChartEnum;

/**
 * Serializer for enums
 */
public class ChartEnumSerializer implements
        JsonSerializer<ChartEnum> {

    @Override
    public JsonElement serialize(ChartEnum src, Type typeOfSrc,
            JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }

}