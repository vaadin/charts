package com.vaadin.addon.charts.model;

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

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vaadin.addon.charts.model.serializers.AxisListSerializer;
import com.vaadin.addon.charts.model.serializers.ChartEnumSerializer;
import com.vaadin.addon.charts.model.serializers.DateSerializer;
import com.vaadin.addon.charts.model.serializers.DefaultBeanSerializerModifier;
import com.vaadin.addon.charts.model.serializers.PaneListSerializer;
import com.vaadin.addon.charts.model.serializers.SolidColorSerializer;

/**
 * Abstract base class for model classes to be serialized to JSON. Mainly
 * com.vaadin.addon.charts.model.Configuration and the stuff it uses.
 */
public abstract class AbstractConfigurationObject implements Serializable {

    private static ObjectWriter jsonWriter;

    static {
        // writer is thread safe so we can use a shared instance
        jsonWriter = createObjectMapper().writer();
    }

    /**
     * Create the default {@link ObjectMapper} used for serialization.
     */
    public static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setVisibility(PropertyAccessor.ALL, Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)

                .registerModule(ChartEnumSerializer.getModule())
                .registerModule(SolidColorSerializer.getModule())
                .registerModule(AxisListSerializer.getModule())
                .registerModule(PaneListSerializer.getModule())
                .registerModule(DateSerializer.getModule());

        // serializer modifier used when basic serializer isn't enough
        return mapper.setSerializerFactory(mapper.getSerializerFactory()
                .withSerializerModifier(new DefaultBeanSerializerModifier()));
    }

    /**
     * This method can be used to configure the {@link ObjectMapper} object used
     * to serialize configuration objects to client side. If users have made
     * their extensions to Highcharts library and wish to build a typed Java API
     * for it, adding custom serializers might be needed.
     *
     * @param newObjectWriter
     * @see #createObjectMapper()
     */
    public static void setObjectMapperInstance(ObjectWriter newObjectWriter) {
        jsonWriter = newObjectWriter;
    }

    @Override
    public String toString() {
        try {
            return jsonWriter.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while serializing "
                    + this.getClass().getSimpleName(), e);
        }
    }
}
