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
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Serializes all {@link java.lang.String} objects that are not functions as
 * sanitized Strings.
 */
public class StringSerializer extends JsonSerializer<String> {

    public static Module getModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(String.class, new StringSerializer());

        return module;
    }

    @Override
    public void serialize(String value, JsonGenerator gen,
            SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        value = shouldSanitize(value, gen) ? sanitize(value) : value;
        gen.writeString(value);
    }

    /**
     * Functions (_fn_ prefixed properties) and null values should not be
     * sanitized
     */
    private boolean shouldSanitize(String value, JsonGenerator gen) {
        return value != null && gen.getOutputContext().inObject()
                && !gen.getOutputContext().getCurrentName().startsWith("_fn_")
                && isHtml(value);
    }

    private boolean isHtml(String html) {
        Pattern htmlPattern = Pattern.compile(".*\\<[^>]+>.*", Pattern.DOTALL);
        return htmlPattern.matcher(html).matches();        
    }

    /*
     * Helper function for content sanitization, this preserves common
     * formatting, but strips scripts. Workaround for:
     * https://github.com/highcharts/highcharts/issues/13559 See also:
     * SNYK-JS-HIGHCHARTS-571995Ï
     */
    private String sanitize(String html) {
        Safelist safelist = Safelist.relaxed()
                .addAttributes(":all", "style")
                .addEnforcedAttribute("a", "rel", "nofollow");
        String sanitized = Jsoup.clean(html, "", safelist,
                           new Document.OutputSettings().prettyPrint(false));
        return sanitized;
    }
}
