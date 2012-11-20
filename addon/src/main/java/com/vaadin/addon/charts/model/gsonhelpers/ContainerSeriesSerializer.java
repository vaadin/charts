package com.vaadin.addon.charts.model.gsonhelpers;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

/**
 * Serializer for ContainerSeries TODO: optimization
 */
public class ContainerSeriesSerializer implements
        JsonSerializer<ContainerDataSeries> {

    @Override
    public JsonElement serialize(ContainerDataSeries src, Type typeOfSrc,
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

        Map<String, Object> pidMap = src.getAttributeToPropertyIdMap();

        Object defaultId1 = pidMap
                .get(ContainerDataSeries.SERIES_DEFAULT_ATTRIBUTE1);
        Object defaultId2 = pidMap
                .get(ContainerDataSeries.SERIES_DEFAULT_ATTRIBUTE2);

        Container container = src.getVaadinContainer();
        for (Object iid : container.getItemIds()) {
            Item item = container.getItem(iid);
            boolean hasFirstDefault = defaultId1 != null
                    && item.getItemProperty(defaultId1) != null;
            boolean hasSecondDefault = defaultId2 != null
                    && item.getItemProperty(defaultId2) != null;

            if (((hasFirstDefault && !hasSecondDefault || !hasFirstDefault
                    && hasSecondDefault) && pidMap.keySet().size() == 1)
                    || (hasFirstDefault && hasSecondDefault && pidMap.keySet()
                            .size() == 2)
                    || (nonDefaultIdsAreNull(item, container, pidMap))) {
                // render simplified since there is only default ids

                if (pidMap.keySet().size() == 1) {
                    addAnonymousTypedValue(
                            data,
                            item.getItemProperty(pidMap.keySet().iterator()
                                    .next()));
                } else {
                    JsonArray entryArray = new JsonArray();
                    data.add(entryArray);

                    Iterator<String> iter = pidMap.keySet().iterator();
                    while (iter.hasNext()) {
                        addAnonymousTypedValue(entryArray,
                                item.getItemProperty(iter.next()));
                    }
                }

            } else {
                // render as json object
                JsonObject entryObject = new JsonObject();
                if (hasFirstDefault) {
                    addNamedAndTypedValue(entryObject,
                            ContainerDataSeries.SERIES_DEFAULT_ATTRIBUTE1,
                            item.getItemProperty(defaultId1));
                }
                if (hasSecondDefault) {
                    addNamedAndTypedValue(entryObject,
                            ContainerDataSeries.SERIES_DEFAULT_ATTRIBUTE2,
                            item.getItemProperty(defaultId2));
                }

                Iterator<String> iter = pidMap.keySet().iterator();
                while (iter.hasNext()) {
                    String name = iter.next();
                    Object id = pidMap.get(name);
                    if (!id.equals(defaultId1) && !id.equals(defaultId2)) {
                        addNamedAndTypedValue(entryObject, name,
                                item.getItemProperty(id));
                    }
                }
                data.add(entryObject);

            }

        }
        System.out.println(series.toString());
        return series;
    }

    private boolean nonDefaultIdsAreNull(Item item, Container container,
            Map<String, Object> pidMap) {
        Object defaultId1 = pidMap
                .get(ContainerDataSeries.SERIES_DEFAULT_ATTRIBUTE1);
        Object defaultId2 = pidMap
                .get(ContainerDataSeries.SERIES_DEFAULT_ATTRIBUTE2);
        for (Object propertyId : container.getContainerPropertyIds()) {
            if (!propertyId.equals(defaultId1)
                    && !propertyId.equals(defaultId2)) {
                if (item.getItemProperty(propertyId) != null
                        && item.getItemProperty(propertyId).getValue() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private void addAnonymousTypedValue(JsonArray data, Property itemProperty) {
        if (itemProperty != null && itemProperty.getValue() != null) {
            if (Number.class.isAssignableFrom(itemProperty.getType())) {
                Number pNum = (Number) itemProperty.getValue();
                data.add(new JsonPrimitive(pNum));
            } else if (Boolean.class.isAssignableFrom(itemProperty.getType())) {
                Boolean pBool = (Boolean) itemProperty.getValue();
                data.add(new JsonPrimitive(pBool));
            } else {
                String pStr = itemProperty.getValue().toString();
                data.add(new JsonPrimitive(pStr));
            }
        }
    }

    private void addNamedAndTypedValue(JsonObject entryObject, String name,
            Property itemProperty) {
        if (itemProperty != null && itemProperty.getValue() != null) {
            if (Number.class.isAssignableFrom(itemProperty.getType())) {
                Number pNum = (Number) itemProperty.getValue();
                entryObject.add(name, new JsonPrimitive(pNum));
            } else if (Boolean.class.isAssignableFrom(itemProperty.getType())) {
                Boolean pBool = (Boolean) itemProperty.getValue();
                entryObject.add(name, new JsonPrimitive(pBool));
            } else {
                String pStr = itemProperty.getValue().toString();
                entryObject.add(name, new JsonPrimitive(pStr));
            }
        }
    }
}
