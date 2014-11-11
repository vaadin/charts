package com.vaadin.addon.charts.model.gsonhelpers;

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

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vaadin.addon.charts.model.AxisList;

/**
 * A serializer for axis containers. Takes care of switching between single
 * items and arrays.
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