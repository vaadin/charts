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

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.vaadin.addon.charts.model.AbstractSeries;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.LegendTitle;
import com.vaadin.addon.charts.model.Title;

/**
 * Logic for altering the bean serialization process. Mainly used when
 * serialization needs to be customized with a bean serializer.
 */
public class DefaultBeanSerializerModifier extends BeanSerializerModifier {

    @Override
    public JsonSerializer<?> modifySerializer(SerializationConfig config,
            BeanDescription beanDesc, JsonSerializer<?> serializer) {
        if (ContainerDataSeries.class.isAssignableFrom(beanDesc.getBeanClass())) {
            return new BeanSerializerDelegator<ContainerDataSeries>(
                    (BeanSerializerBase) serializer,
                    new ContainerDataSeriesBeanSerializer());
        } else if (DataSeriesItem.class.isAssignableFrom(beanDesc
                .getBeanClass())) {
            return new BeanSerializerDelegator<DataSeriesItem>(
                    (BeanSerializerBase) serializer,
                    new DataSeriesItemBeanSerializer());
        } else if (Title.class.isAssignableFrom(beanDesc.getBeanClass())) {
            return new BeanSerializerDelegator<Title>(
                    (BeanSerializerBase) serializer, new TitleBeanSerializer());
        } else if (AxisTitle.class.isAssignableFrom(beanDesc.getBeanClass())) {
            return new BeanSerializerDelegator<AxisTitle>(
                    (BeanSerializerBase) serializer, new AxisTitleBeanSerializer());
        }
        else if (LegendTitle.class.isAssignableFrom(beanDesc.getBeanClass())) {
            return new BeanSerializerDelegator<LegendTitle>(
                    (BeanSerializerBase) serializer, new LegendTitleBeanSerializer());
        }
        else if (AbstractSeries.class.isAssignableFrom(beanDesc
                .getBeanClass())) {
            return new BeanSerializerDelegator<AbstractSeries>(
                    (BeanSerializerBase) serializer,
                    new AbstractSeriesBeanSerializer());
        } else {
            return super.modifySerializer(config, beanDesc, serializer);
        }
    }
}
