/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vaadin.addon.charts.model.AxisTitle;

import java.io.IOException;

/**
 * Serializer for {@link com.vaadin.addon.charts.model.AxisTitle}.
 *
 */
public class AxisTitleBeanSerializer extends BeanSerializationDelegate<AxisTitle> {



    @Override
    public Class<AxisTitle> getBeanClass() {
        return AxisTitle.class;
    }

    public void serialize(AxisTitle bean,
                          BeanSerializerDelegator<AxisTitle> serializer, JsonGenerator jgen,
                          SerializerProvider provider) throws IOException {
        jgen.writeStartObject();

        if (bean != null && bean.getText() == null) {
            jgen.writeNullField("text");
        } else {
            // write fields as per normal serialization rules
            serializer.serializeFields(bean, jgen, provider);
        }

        jgen.writeEndObject();
    }
}
