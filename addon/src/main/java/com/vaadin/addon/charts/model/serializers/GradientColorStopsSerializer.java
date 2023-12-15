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
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.util.Util;

/**
 * Serializes all {@link Date} objects as UTC long.
 *
 */
public class GradientColorStopsSerializer extends JsonSerializer<GradientColor.Stop> {

    public static Module getModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(GradientColor.Stop.class, new GradientColorStopsSerializer());

        return module;
    }

    @Override
    public void serialize(GradientColor.Stop value, JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException, JsonProcessingException {
        gen.writeStartArray();
        gen.writeNumber(value.getPosition());
        gen.writeString(value.getColor().toString());
        gen.writeEndArray();
    }
}
