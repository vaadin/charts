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

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vaadin.server.SizeWithUnit;
import com.vaadin.server.Sizeable.Unit;

public class SizeSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String tmpValue, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException,
            JsonProcessingException {
        SizeWithUnit sizeWithUnit = SizeWithUnit.parseStringSize(tmpValue);
        if (sizeWithUnit != null) {
            Unit unit = sizeWithUnit.getUnit();
            float size = sizeWithUnit.getSize();
            if (unit == Unit.PIXELS && size != -1.0f) {
                jsonGenerator.writeNumber(size);
            } else {
                jsonGenerator.writeObject(tmpValue);
            }
        }
    }
}
