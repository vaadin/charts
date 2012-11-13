package com.vaadin.addon.charts.model.gsonhelpers;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vaadin.addon.charts.model.AxisList;

/**
 * Serializer for axis containers. Takes care of switching between single
 * item and array.
 */
public class AxisListSerializer implements JsonSerializer<AxisList> {

    @Override
    public JsonElement serialize(AxisList obj, Type type,
            JsonSerializationContext context) {

        if (obj.getNumberOfAxes() == 1) {
            return context.serialize(obj.getAxis(0));
        } else if (obj instanceof AxisList) {
            return context.serialize(obj.getAxes());
        } else {
            return null;
        }
    }
}