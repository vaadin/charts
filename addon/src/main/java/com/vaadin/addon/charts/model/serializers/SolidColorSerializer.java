/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
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
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vaadin.addon.charts.model.style.SolidColor;

/**
 * Serializer for {@link SolidColor}
 *
 */
public class SolidColorSerializer extends JsonSerializer<SolidColor> {

    public static Module getModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(SolidColor.class, new SolidColorSerializer());
        return module;
    }

    @Override
    public void serialize(SolidColor value, JsonGenerator gen,
            SerializerProvider serializers) throws IOException,
            JsonProcessingException {
        gen.writeString(value.toString());
    }
}
