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
package com.vaadin.v7.addon.charts.model.serializers;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.vaadin.addon.charts.model.serializers.BeanSerializerDelegator;
import com.vaadin.addon.charts.model.serializers.DefaultBeanSerializerModifier;
import com.vaadin.v7.addon.charts.model.ContainerDataSeries;

/**
 * Logic for altering the bean serialization process. Mainly used when
 * serialization needs to be customized with a bean serializer.
 */
public class CompatibilityBeanSerializerModifier extends
        DefaultBeanSerializerModifier {

    @Override
    public JsonSerializer<?> modifySerializer(SerializationConfig config,
            BeanDescription beanDesc, JsonSerializer<?> serializer) {
        if (ContainerDataSeries.class.isAssignableFrom(beanDesc.getBeanClass())) {
            return new BeanSerializerDelegator<ContainerDataSeries>(
                    (BeanSerializerBase) serializer,
                    new ContainerDataSeriesBeanSerializer());
        } else {
            return super.modifySerializer(config, beanDesc, serializer);
        }
    }
}
