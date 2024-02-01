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
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.BeanAsArraySerializer;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;

/**
 * Delegates serialization calls to the given instance of
 * {@link BeanSerializationDelegate}. This class can be used instead of
 * implementing {@link BeanSerializerBase}.
 *
 * @param <T>
 */
public class BeanSerializerDelegator<T> extends BeanSerializerBase {

    private BeanSerializationDelegate<T> delegate;

    public BeanSerializerDelegator(BeanSerializerBase source) {
        super(source);
    }

    public BeanSerializerDelegator(BeanSerializerBase source,
            ObjectIdWriter objectIdWriter) {
        super(source, objectIdWriter);
    }

    @Deprecated
    public BeanSerializerDelegator(BeanSerializerBase source,
            String[] toIgnore) {
        super(source, toIgnore);
    }

    @Deprecated
    protected BeanSerializerDelegator(BeanSerializerBase source,
            Set<String> toIgnore) {
        super(source, toIgnore);
    }

    protected BeanSerializerDelegator(BeanSerializerBase source,
            Set<String> toIgnore, Set<String> toInclude) {
        super(source, toIgnore, toInclude);
    }

    protected BeanSerializerDelegator(BeanSerializerBase source,
            BeanPropertyWriter[] properties,
            BeanPropertyWriter[] filteredProperties) {
        super(source, properties, filteredProperties);
    }

    public BeanSerializerDelegator(BeanSerializerBase source, Object filterId) {
        super(source, null, filterId);
    }

    public BeanSerializerDelegator(BeanSerializerBase source,
            BeanSerializationDelegate<T> delegate) {
        super(source);

        this.delegate = delegate;
    }

    @Override
    public BeanSerializerBase withObjectIdWriter(
            ObjectIdWriter objectIdWriter) {
        return new BeanSerializerDelegator<>(this, objectIdWriter);
    }

    @Override
    @Deprecated
    protected BeanSerializerBase withIgnorals(String[] toIgnore) {
        return new BeanSerializerDelegator<>(this, toIgnore);
    }

    @Override
    @Deprecated
    protected BeanSerializerBase withIgnorals(Set<String> toIgnore) {
        return new BeanSerializerDelegator<>(this, toIgnore);
    }

    @Override
    public BeanSerializerBase withFilterId(Object filterId) {
        return new BeanSerializerDelegator<>(this, filterId);
    }

    @Override
    protected BeanSerializerBase withByNameInclusion(Set<String> toIgnore,
            Set<String> toInclude) {
        return new BeanSerializerDelegator<>(this, toIgnore, toInclude);
    }

    @Override
    protected BeanSerializerBase withProperties(BeanPropertyWriter[] properties,
            BeanPropertyWriter[] filteredProperties) {
        return new BeanSerializerDelegator<>(this, properties,
                filteredProperties);
    }

    @Override
    protected BeanSerializerBase asArraySerializer() {
        // copied from BeanSerializer
        if ((_objectIdWriter == null) && (_anyGetterWriter == null)
                && (_propertyFilterId == null)) {
            return new BeanAsArraySerializer(this);
        }
        return this;
    }

    @Override
    public void serialize(Object bean, JsonGenerator jgen,
            SerializerProvider provider) throws IOException {
        delegate.serialize(delegate.getBeanClass().cast(bean), this, jgen,
                provider);
    }

    @Override
    public void serializeFields(Object bean, JsonGenerator jgen,
            SerializerProvider provider) throws IOException,
            JsonGenerationException {
        super.serializeFields(bean, jgen, provider);
    }
}
