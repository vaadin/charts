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
package com.vaadin.addon.charts.declarative;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.data.converter.AbstractStringToNumberConverter;
import com.vaadin.ui.declarative.DesignFormatter;

public class ChartDesignFormatter extends DesignFormatter {

    public ChartDesignFormatter() {
        super();

        addConverter(Number.class, new AbstractStringToNumberConverter<Number>(null, "") {
            @Override
            public Result<Number> convertToModel(String value,
                    ValueContext context) {
                return convertToNumber(value, context);
            }
        });

        addConverter(Color.class, new Converter<String, Color>() {
            @Override
            public Result<Color> convertToModel(String value,
                    ValueContext context) {
                Color color = new SolidColor(value);
                return Result.ok(color);
            }

            @Override
            public String convertToPresentation(Color value, ValueContext context) {
                if (value instanceof SolidColor) {
                    return value.toString();
                }
                return null;
            }
        });
    }

    @Override
    public boolean canConvert(Class<?> type) {
        return super.canConvert(type);
    }

    @Override
    public <T> Converter<String, T> findConverterFor(
            Class<? extends T> sourceType) {
        return super.findConverterFor(sourceType);
    }
}
