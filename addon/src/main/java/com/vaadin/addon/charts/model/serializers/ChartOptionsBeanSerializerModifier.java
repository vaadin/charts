/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model.serializers;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.style.GradientColor;

/**
 * Modifier that adds special handling for {@link ChartOptions} and
 * {@link GradientColor} inside {@link ChartOptions}
 */
public class ChartOptionsBeanSerializerModifier extends
        DefaultBeanSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(
            SerializationConfig config, BeanDescription beanDesc,
            List<BeanPropertyWriter> beanProperties) {

        if (ChartOptions.class.isAssignableFrom(beanDesc.getBeanClass())) {
            // make sure we only serialize the lang, theme and global fields
            // from
            // ChartOptions, otherwise everything from parent would be
            // serialized
            List<BeanPropertyWriter> newProperties = new ArrayList<BeanPropertyWriter>();

            for (BeanPropertyWriter writer : beanProperties) {
                if ("lang" == writer.getName() || "theme" == writer.getName()
                        || "global" == writer.getName()) {
                    newProperties.add(writer);
                }
            }
            return newProperties;
        } else if (GradientColor.class
                .isAssignableFrom(beanDesc.getBeanClass())) {
            // don't serialize gradients normally, instead use the custom
            // serializer
            List<BeanPropertyWriter> newProperties = new ArrayList<BeanPropertyWriter>();

            for (BeanPropertyWriter writer : beanProperties) {
                if ("radialGradient" != writer.getName()
                        && "linearGradient" != writer.getName()) {
                    newProperties.add(writer);
                }
            }
            return newProperties;
        }

        return super.changeProperties(config, beanDesc, beanProperties);
    }

    @Override
    public JsonSerializer<?> modifySerializer(SerializationConfig config,
            BeanDescription beanDesc, JsonSerializer<?> serializer) {
        if (GradientColor.class.isAssignableFrom(beanDesc.getBeanClass())) {
            return new ThemeGradientColorBeanSerializer(
                    (BeanSerializerBase) serializer);
        } else {
            return super.modifySerializer(config, beanDesc, serializer);
        }
    }
}
