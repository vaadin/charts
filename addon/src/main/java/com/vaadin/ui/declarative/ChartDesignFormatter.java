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
package com.vaadin.ui.declarative;

import java.util.Locale;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.data.util.converter.AbstractStringToNumberConverter;
import com.vaadin.data.util.converter.Converter;

public class ChartDesignFormatter {

    private static boolean initialized;

    public static void init() {
        if (!initialized) {
            DesignAttributeHandler.getFormatter().addConverter(
                    new NumberToStringConverter());
            DesignAttributeHandler.getFormatter().addConverter(
                    new StringToColorConverter());
            initialized = true;
        }
    }

    private static class NumberToStringConverter extends
            AbstractStringToNumberConverter<Number> {

        @Override
        public Number convertToModel(String value,
                Class<? extends Number> targetType, Locale locale)
                throws ConversionException {
            return convertToNumber(value, targetType, locale);
        }

        @Override
        public Class<Number> getModelType() {
            return Number.class;
        }
    }

    private static class StringToColorConverter implements
            Converter<String, Color> {

        @Override
        public Color convertToModel(String value,
                Class<? extends Color> targetType, Locale locale)
                throws com.vaadin.data.util.converter.Converter.ConversionException {
            return new SolidColor(value);
        }

        @Override
        public String convertToPresentation(Color value,
                Class<? extends String> targetType, Locale locale)
                throws com.vaadin.data.util.converter.Converter.ConversionException {
            if (value instanceof SolidColor) {
                return value.toString();
            }
            return null;
        }

        @Override
        public Class<Color> getModelType() {
            return Color.class;
        }

        @Override
        public Class<String> getPresentationType() {
            return String.class;
        }

    }
}
