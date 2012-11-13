package com.vaadin.addon.charts.model.gsonhelpers;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vaadin.addon.charts.model.PaneList;

/**
 * Serializer for Pane containers. Takes care of switching between single
 * item and array.
 */
public class PaneListSerializer implements JsonSerializer<PaneList> {

    @Override
    public JsonElement serialize(PaneList obj, Type type,
            JsonSerializationContext context) {

        if (obj.getNumberOfPanes() == 1) {
            return context.serialize(obj.getPane(0));
        } else if (obj instanceof PaneList) {
            return context.serialize(obj.getPanes());
        } else {
            return null;
        }
    }

}