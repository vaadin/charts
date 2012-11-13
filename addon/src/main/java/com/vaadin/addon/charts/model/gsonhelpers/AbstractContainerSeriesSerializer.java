package com.vaadin.addon.charts.model.gsonhelpers;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vaadin.addon.charts.model.AbstractContainerSeries;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

/**
 * Serializer for ContainerSeries TODO: optimization
 */
public class AbstractContainerSeriesSerializer implements
        JsonSerializer<AbstractContainerSeries> {

    @Override
    public JsonElement serialize(AbstractContainerSeries src, Type typeOfSrc,
            JsonSerializationContext context) {
        JsonArray data = new JsonArray();

        JsonObject series = new JsonObject();
        if (src.getType() != null) {
            series.add("type", new JsonPrimitive(src.getType().toString()));
        }
        if (src.getName() != null) {
            series.add("name", new JsonPrimitive(src.getName()));
        }
        series.add("data", data);

        Collection pids = src.getContainerPropertyIds();
        if (pids.size() < 2 && src.containsOnlyDefaultPropertyIds()) {
            serializeSimplest(src, data);
        } else if (!src.containsOnlyDefaultPropertyIds()) {
            for (int index = 0; index < src.size(); index++) {
                Item containerItem = src.getItem(src.getIdByIndex(index));
                JsonObject dataItem = new JsonObject();
                data.add(dataItem);
                for (Object pid : pids) {
                    serializeNormally(containerItem, dataItem, pid);
                }
            }
        } else {
            for (int index = 0; index < src.size(); index++) {
                Item containerItem = src.getItem(src.getIdByIndex(index));
                serializeWithoutIds(src, containerItem, data);
            }
        }
        return series;
    }

    private void serializeNormally(Item containerItem, JsonObject dataItem,
            Object pid) {
        if (containerItem != null && containerItem.getItemProperty(pid) != null
                && containerItem.getItemProperty(pid).getValue() != null) {

            Property p = containerItem.getItemProperty(pid);
            if (Number.class.isAssignableFrom(p.getType())) {
                Number pNum = (Number) containerItem.getItemProperty(pid)
                        .getValue();
                dataItem.add(pid.toString(), new JsonPrimitive(pNum));
            } else if (Boolean.class.isAssignableFrom(p.getType())) {
                Boolean pBool = (Boolean) containerItem.getItemProperty(pid)
                        .getValue();
                dataItem.add(pid.toString(), new JsonPrimitive(pBool));
            } else {
                String pStr = containerItem.getItemProperty(pid).getValue()
                        .toString();
                dataItem.add(pid.toString(), new JsonPrimitive(pStr));
            }
        }
    }

    private void serializeWithoutIds(AbstractContainerSeries src,
            Item containerItem, JsonArray series) {
        JsonArray entryArray = new JsonArray();
        for (Object pid : src.getContainerPropertyIds()) {
            if (containerItem != null
                    && containerItem.getItemProperty(pid) != null
                    && containerItem.getItemProperty(pid).getValue() != null) {

                Property p = containerItem.getItemProperty(pid);
                Object propertyValue = p.getValue();

                if (Number.class.isAssignableFrom(p.getType())) {
                    Number pNum = (Number) propertyValue;
                    entryArray.add(new JsonPrimitive(pNum));
                } else if (Boolean.class.isAssignableFrom(p.getType())) {
                    Boolean pBool = (Boolean) propertyValue;
                    entryArray.add(new JsonPrimitive(pBool));
                } else {
                    String pStr = propertyValue.toString();
                    entryArray.add(new JsonPrimitive(pStr));
                }

            }
        }
        series.add(entryArray);
    }

    private void serializeSimplest(AbstractContainerSeries src, JsonArray data) {
        Collection pids = src.getContainerPropertyIds();
        if (pids.size() == 0) {
            // no property's ids, use item's ids
            for (int index = 0; index < src.size(); index++) {
                Object itemId = src.getIdByIndex(index);
                if (itemId instanceof Number) {
                    data.add(new JsonPrimitive((Number) itemId));
                } else if (itemId instanceof Boolean) {
                    data.add(new JsonPrimitive((Boolean) itemId));
                } else {
                    data.add(new JsonPrimitive(itemId.toString()));
                }
            }
        } else {
            // one id, use that
            for (int index = 0; index < src.size(); index++) {
                Item item = src.getItem(src.getIdByIndex(index));
                Object propertyId = item.getItemPropertyIds().iterator().next();

                if (item != null && item.getItemProperty(propertyId) != null
                        && item.getItemProperty(propertyId).getValue() != null) {

                    Property p = item.getItemProperty(propertyId);
                    Object propertyValue = p.getValue();
                    if (Number.class.isAssignableFrom(p.getType())) {
                        Number pNum = (Number) propertyValue;
                        data.add(new JsonPrimitive(pNum));
                    } else if (Boolean.class.isAssignableFrom(p.getType())) {
                        Boolean pBool = (Boolean) propertyValue;
                        data.add(new JsonPrimitive(pBool));
                    } else {
                        String pStr = propertyValue.toString();
                        data.add(new JsonPrimitive(pStr));
                    }
                }
            }
        }

    }
}
