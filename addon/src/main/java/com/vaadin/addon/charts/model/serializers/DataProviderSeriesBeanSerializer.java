package com.vaadin.addon.charts.model.serializers;

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
import java.util.List;
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
        ONLY_Y, XY, OBJECT
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

    private ArrayNode createDataArray(DataProviderSeries chartDataProvider) {
        ArrayNode data = JsonNodeFactory.instance.arrayNode();
        checkRequiredProperties(chartDataProvider);
        Mode mode = null;

        Set<String> attributes = chartDataProvider.getChartAttributes();
        for (String attribute : attributes) {
            if (!attribute.equals(xAttribute)
                    && !attribute.equals(yAttribute)) {
                mode = Mode.OBJECT;
                break;
            }
        }

        if (mode != Mode.OBJECT) {
            if (chartDataProvider.getChartAttributes().contains(xAttribute)) {
                mode = Mode.XY;
            } else {
                mode = Mode.ONLY_Y;
            }
        }

        List<Map<String, Object>> chartAttributesToValues = chartDataProvider
                .getValues();

        for (Map<String, Object> chartAttributeToValue : chartAttributesToValues) {
            Object xValue = chartAttributeToValue.get(xAttribute);
            Object yValue = chartAttributeToValue.get(yAttribute);
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

    private void checkRequiredProperties(DataProviderSeries chartDataSeries) {

        Set<String> attributes = chartDataSeries.getChartAttributes();
        Boolean hasYProperty = attributes.contains(yAttribute);
        Boolean hasHighProperty = attributes
                .contains(DataProviderSeries.HIGH_PROPERTY);
        Boolean hasLowProperty = attributes
                .contains(DataProviderSeries.LOW_PROPERTY);

        if (!hasYProperty && (!hasHighProperty || !hasLowProperty)) {
            throw new IllegalStateException(
                    "ChartDataSeries' must have a property for 'y' values or for "
                            + "both high and low values. Check "
                            + DataProviderSeries.class.getName() + " Javadoc");
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
