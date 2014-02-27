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

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vaadin.addon.charts.model.PaneList;

/**
 * Serializer for Pane containers. Takes care of switching between single item
 * and array.
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