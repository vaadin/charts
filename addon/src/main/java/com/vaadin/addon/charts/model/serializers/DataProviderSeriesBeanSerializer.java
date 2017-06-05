package com.vaadin.addon.charts.model.serializers;

import static com.vaadin.addon.charts.model.DataProviderSeries.CLOSE_PROPERTY;
import static com.vaadin.addon.charts.model.DataProviderSeries.HIGH_PROPERTY;
import static com.vaadin.addon.charts.model.DataProviderSeries.LOW_PROPERTY;
import static com.vaadin.addon.charts.model.DataProviderSeries.OPEN_PROPERTY;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
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

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.vaadin.addon.charts.model.DataProviderSeries;
import com.vaadin.addon.charts.model.PlotOptionsSeries;

/**
 * Custom bean serializer for {@link DataProviderSeries}
 */
public class DataProviderSeriesBeanSerializer
        extends BeanSerializationDelegate<DataProviderSeries> {

    public static final String xAttribute = DataProviderSeries.X_ATTRIBUTE;
    public static final String yAttribute = DataProviderSeries.Y_ATTRIBUTE;

    private enum Mode {
        ONLY_Y, XY, XLH, XOHLC, OBJECT
    }

    @Override
    public Class<DataProviderSeries> getBeanClass() {
        return DataProviderSeries.class;
    }

    @Override
    public void serialize(DataProviderSeries bean,
            BeanSerializerDelegator<DataProviderSeries> serializer,
            JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        jgen.writeStartObject();

        if (bean.getPlotOptions() != null
                && !(bean.getPlotOptions() instanceof PlotOptionsSeries)) {
            jgen.writeObjectField("type", bean.getPlotOptions().getChartType());
        }

        // write other fields as per normal serialization rules
        serializer.serializeFields(bean, jgen, provider);

        ArrayNode data = createDataArray(bean);

        jgen.writeObjectField("data", data);

        jgen.writeEndObject();
    }
    private ArrayNode createDataArray(DataProviderSeries<?> chartDataProvider) {
        ArrayNode data = JsonNodeFactory.instance.arrayNode();
        Set<String> attributes = chartDataProvider.getChartAttributes();
        checkRequiredProperties(attributes);
        Mode mode = inferSerializationMode(attributes);

        for (Map<String, Object> chartAttributeToValue : chartDataProvider
                .getValues()) {
            Object xValue = chartAttributeToValue.get(xAttribute);
            Object yValue = chartAttributeToValue.get(yAttribute);
            Object oValue = chartAttributeToValue
                    .get(OPEN_PROPERTY);
            Object lValue = chartAttributeToValue
                    .get(LOW_PROPERTY);
            Object hValue = chartAttributeToValue
                    .get(HIGH_PROPERTY);
            Object cValue = chartAttributeToValue
                    .get(CLOSE_PROPERTY);

            switch (mode) {
            case ONLY_Y:
                Object value = chartAttributeToValue.get(yAttribute);
                addValue(data, value);
                break;
            case XY:
                if (xValue != null && yValue != null) {
                    ArrayNode entryArray = JsonNodeFactory.instance.arrayNode();
                    data.add(entryArray);
                    addValue(entryArray, xValue);
                    addValue(entryArray, yValue);
                } else {
                    data.addNull();
                }
                break;
            case XLH:

                if (xValue != null && lValue != null && hValue != null) {
                    ArrayNode entryArray = JsonNodeFactory.instance.arrayNode();
                    data.add(entryArray);
                    addValue(entryArray, xValue);
                    addValue(entryArray, lValue);
                    addValue(entryArray, hValue);
                } else {
                    data.addNull();
                }
                break;
            case XOHLC:

                if (xValue != null && oValue != null && hValue != null
                        && lValue != null && cValue != null) {
                    ArrayNode entryArray = JsonNodeFactory.instance.arrayNode();
                    data.add(entryArray);
                    addValue(entryArray, xValue);
                    addValue(entryArray, oValue);
                    addValue(entryArray, hValue);
                    addValue(entryArray, lValue);
                    addValue(entryArray, cValue);
                } else {
                    data.addNull();
                }
                break;

            default:
                // render as json object
                ObjectNode entryObject = JsonNodeFactory.instance.objectNode();
                if (xValue != null) {
                    addNamedValue(entryObject, xAttribute, xValue);
                }

                if (yValue != null) {
                    addNamedValue(entryObject, yAttribute, yValue);
                }

                for (Map.Entry<String, Object> object : chartAttributeToValue
                        .entrySet()) {
                    if (!object.getKey().equals(xAttribute)
                            && !object.getKey().equals(yAttribute)) {
                        addNamedValue(entryObject, object.getKey(),
                                object.getValue());
                    }
                }
                data.add(entryObject);

                break;
            }
        }

        return data;
    }

    private void checkRequiredProperties(Set<String> attributes) {

        Boolean hasYProperty = attributes.contains(yAttribute);
        Boolean hasHighProperty = attributes
                .contains(HIGH_PROPERTY);
        Boolean hasLowProperty = attributes
                .contains(LOW_PROPERTY);

        if (!hasYProperty && (!hasHighProperty || !hasLowProperty)) {
            throw new IllegalStateException(
                    "ChartDataSeries' must have a property for 'y' values or for "
                            + "both high and low values. Check "
                            + DataProviderSeries.class.getName() + " Javadoc");
        }
    }

    private Mode inferSerializationMode(Set<String> attributes) {
        switch (attributes.size()) {
        case 1:
            if (attributes.contains(yAttribute)) {
                return Mode.ONLY_Y;
            }
        case 2:
            if (attributes.contains(yAttribute)
                    && attributes.contains(xAttribute)) {
                return Mode.XY;
            }
        case 3:
            if (attributes.contains(xAttribute)
                    && attributes.contains(LOW_PROPERTY)
                    && attributes.contains(HIGH_PROPERTY)) {
                return Mode.XLH;
            }
        case 5:
            if (attributes.contains(xAttribute)
                    && attributes.contains(OPEN_PROPERTY)
                    && attributes.contains(HIGH_PROPERTY)
                    && attributes.contains(LOW_PROPERTY)
                    && attributes.contains(CLOSE_PROPERTY)) {
                return Mode.XOHLC;
            }
        default:
            return Mode.OBJECT;
        }
    }

    private void addValue(ArrayNode data, Object value) {
        if (value != null) {
            ValueNode node = JsonNodeFactory.instance.pojoNode(value);
            data.add(node);
        }
    }

    private void addNamedValue(ObjectNode data, String name, Object value) {
        if (value != null) {
            ValueNode node = JsonNodeFactory.instance.pojoNode(value);
            data.set(name, node);

        }
    }
}
