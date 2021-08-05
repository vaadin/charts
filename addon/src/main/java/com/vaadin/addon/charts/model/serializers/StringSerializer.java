package com.vaadin.addon.charts.model.serializers;

/*-
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2019 Vaadin Ltd
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

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

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
                && !gen.getOutputContext().getCurrentName().startsWith("_fn_");
    }

    /*
     * Helper function for content sanitization, this preserves common
     * formatting, but strips scripts. Workaround for:
     * https://github.com/highcharts/highcharts/issues/13559 See also:
     * SNYK-JS-HIGHCHARTS-571995Ï
     */
    private String sanitize(String html) {
        return Jsoup.clean(html,
                Whitelist.basic().addTags("img", "h1", "h2", "h3", "s")
                        .addAttributes("img", "align", "alt", "height", "src",
                                "title", "width")
                        .addAttributes(":all", "style")
                        .addProtocols("img", "src", "data"));
    }
}
