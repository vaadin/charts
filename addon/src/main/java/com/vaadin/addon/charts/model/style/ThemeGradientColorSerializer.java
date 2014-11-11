package com.vaadin.addon.charts.model.style;

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

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vaadin.addon.charts.model.style.GradientColor.LinearGradient;
import com.vaadin.addon.charts.model.style.GradientColor.RadialGradient;

/**
 * Themes use different type of gradient syntax than other API, override JSON
 * generation to make compatible with themes.
 */
public class ThemeGradientColorSerializer implements
        JsonSerializer<GradientColor> {
    @Override
    public JsonElement serialize(GradientColor src, Type typeOfSrc,
            JsonSerializationContext context) {

        // linearGradient: [0, 0, 250, 500],
        // stops: [
        // [0, 'rgb(48, 96, 48)'],
        // [1, 'rgb(0, 0, 0)']
        // ]

        JsonObject jsonObject = new JsonObject();

        LinearGradient linearGradient = src.getLinearGradient();

        if (linearGradient != null) {
            JsonArray value = new JsonArray();
            value.add(new JsonPrimitive(linearGradient.x1));
            value.add(new JsonPrimitive(linearGradient.y1));
            value.add(new JsonPrimitive(linearGradient.x2));
            value.add(new JsonPrimitive(linearGradient.y2));
            jsonObject.add("linearGradient", value);
        } else {
            RadialGradient radialGradient = src.getRadialGradient();
            JsonArray value = new JsonArray();
            value.add(new JsonPrimitive(radialGradient.cx));
            value.add(new JsonPrimitive(radialGradient.cy));
            value.add(new JsonPrimitive(radialGradient.r));
            jsonObject.add("radialGradient", value);
        }

        List<List<Object>> stops = src.getStops();
        jsonObject.add("stops", context.serialize(stops, List.class));

        return jsonObject;
    }
}