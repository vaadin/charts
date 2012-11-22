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
 * Serializer for ContainerSeries
 */
public class ContainerDataSeriesSerializer implements
        JsonSerializer<ContainerDataSeries> {

    @Override
    public JsonElement serialize(ContainerDataSeries src, Type typeOfSrc,
            JsonSerializationContext context) {
        JsonObject series = new JsonObject();
        JsonArray data = new JsonArray();

        if (context != null) {
            series.add("size", context.serialize(src.getSize()));
            series.add("innerSize", context.serialize(src.getInnerSize()));
            series.add("center", context.serialize(src.getCenter()));
            series.add("color", context.serialize(src.getColor()));
            series.add("dataLabels", context.serialize(src.getDataLabels()));
            series.add("marker", context.serialize(src.getMarker()));
            series.add("name", context.serialize(src.getName()));
            series.add("pointPlacement",
                    context.serialize(src.getPointPlacement()));
            series.add("stack", context.serialize(src.getStack()));
            series.add("states", context.serialize(src.getStates()));
            series.add("tooltip", context.serialize(src.getTooltip()));
            series.add("type", context.serialize(src.getType()));
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
