package com.vaadin.v7.addon.charts.model.serializers;

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
import static com.vaadin.v7.addon.charts.model.ContainerDataSeries.HIGH_PROPERTY;
import static com.vaadin.v7.addon.charts.model.ContainerDataSeries.LOW_PROPERTY;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.addon.charts.model.serializers.BeanSerializationDelegate;
import com.vaadin.addon.charts.model.serializers.BeanSerializerDelegator;
import com.vaadin.v7.addon.charts.model.ContainerDataSeries;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.Property;

/**
 * Custom bean serializer for {@link ContainerDataSeries}
 */
public class ContainerDataSeriesBeanSerializer extends
        BeanSerializationDelegate<ContainerDataSeries> {

    private enum Mode {
        ONLY_Y, XY, OBJECT
    }

    @Override
    public Class<ContainerDataSeries> getBeanClass() {
        return ContainerDataSeries.class;
    }

    @Override
    public void serialize(ContainerDataSeries bean,
            BeanSerializerDelegator<ContainerDataSeries> serializer,
            JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();

        if (bean.getPlotOptions() != null
                && !(bean.getPlotOptions() instanceof PlotOptionsSeries)) {
            jgen.writeObjectField("type", bean.getPlotOptions().getChartType());
        }

        // write other fields as per normal serialization rules
        serializer.serializeFields(bean, jgen, provider);

        ArrayNode data = createDataArray(bean.getVaadinContainer(),
                bean.getAttributeToPropertyIdMap());

        jgen.writeObjectField("data", data);

        jgen.writeEndObject();
    }

    private ArrayNode createDataArray(Container container,
            Map<String, Object> pidMap) {
        ArrayNode data = JsonNodeFactory.instance.arrayNode();

        Mode mode = null;
        for (Object o : pidMap.keySet()) {
            if (!(o.equals(ContainerDataSeries.SERIES_DEFAULT_ATTRIBUTE1) || o
                    .equals(ContainerDataSeries.SERIES_DEFAULT_ATTRIBUTE2))) {
                mode = Mode.OBJECT;
                break;
            }
        }
        Object xProperty = pidMap
                .get(ContainerDataSeries.SERIES_DEFAULT_ATTRIBUTE1);
        if (xProperty == null) {
            xProperty = ContainerDataSeries.SERIES_DEFAULT_ATTRIBUTE1;
        }
        Object yProperty = pidMap
                .get(ContainerDataSeries.SERIES_DEFAULT_ATTRIBUTE2);
        if (yProperty == null) {
            yProperty = ContainerDataSeries.SERIES_DEFAULT_ATTRIBUTE2;
        }

        checkRequiredProperties(container, pidMap, yProperty);

        if (mode != Mode.OBJECT) {
            if (container.getContainerPropertyIds().contains(xProperty)) {
                mode = Mode.XY;
            } else {
                mode = Mode.ONLY_Y;
            }
        }

        for (Object iid : container.getItemIds()) {
            Item item = container.getItem(iid);
            switch (mode) {
            case ONLY_Y:
                addValue(data, item.getItemProperty(yProperty));
                break;
            case XY:
                Property itemPropertyX = item.getItemProperty(xProperty);
                Property itemPropertyY = item.getItemProperty(yProperty);
                if (itemPropertyX.getValue() != null
                        && itemPropertyY.getValue() != null) {
                    ArrayNode entryArray = JsonNodeFactory.instance.arrayNode();
                    data.add(entryArray);
                    addValue(entryArray, itemPropertyX);
                    addValue(entryArray, itemPropertyY);
                } else {
                    data.addNull();
                }
                break;

            default:
                // render as json object
                ObjectNode entryObject = JsonNodeFactory.instance.objectNode();

                Property<?> x = item.getItemProperty(xProperty);
                if (x != null) {
                    addNamedValue(entryObject,
                            ContainerDataSeries.SERIES_DEFAULT_ATTRIBUTE1, x);
                }
                Property<?> y = item.getItemProperty(yProperty);
                if (y != null) {
                    addNamedValue(entryObject,
                            ContainerDataSeries.SERIES_DEFAULT_ATTRIBUTE2, y);
                }

                Iterator<String> iter = pidMap.keySet().iterator();
                while (iter.hasNext()) {
                    String name = iter.next();
                    Object id = pidMap.get(name);
                    if (!id.equals(xProperty) && !id.equals(yProperty)) {
                        addNamedValue(entryObject, name,
                                item.getItemProperty(id));
                    }
                }
                data.add(entryObject);

                break;
            }
        }

        return data;
    }

    private void checkRequiredProperties(Container container, Map<String, Object> pidMap, Object yProperty) {
        final Object highProperty = pidMap.get(HIGH_PROPERTY);
        final Object lowProperty = pidMap.get(LOW_PROPERTY);

        final Collection<?> containerPropertyIds = container.getContainerPropertyIds();

        boolean containsYProperty = containerPropertyIds.contains(yProperty);
        boolean containsHighProperty = containerPropertyIds.contains(highProperty);
        boolean containsLowProperty = containerPropertyIds.contains(lowProperty);

        if (!containsYProperty &&
              (   highProperty == null || ! containsHighProperty ||
                  lowProperty  == null || ! containsLowProperty)) {
            throw new IllegalStateException(
                    "ContainerDataSeries' container should always have a property for 'y' values or for "
                            + "both high and low values. Check ContainerDataSeries Javadoc");
        }
    }

    private void addValue(ArrayNode data, Property<?> itemProperty) {
        Objects.requireNonNull(itemProperty);
        Object value = itemProperty.getValue();
        if (value != null) {
            ValueNode node = JsonNodeFactory.instance.pojoNode(itemProperty.getValue());
            data.add(node);
        }
    }

    private void addNamedValue(ObjectNode data, String name, Property<?> itemProperty) {
        Objects.requireNonNull(itemProperty);
        Objects.requireNonNull(name);
        Objects.requireNonNull(data);
        Object value = itemProperty.getValue();
        if (value != null) {
            ValueNode node = JsonNodeFactory.instance.pojoNode(itemProperty.getValue());
            data.set(name, node);
        }
    }
}
