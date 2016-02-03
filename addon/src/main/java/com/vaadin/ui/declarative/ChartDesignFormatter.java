package com.vaadin.ui.declarative;

import java.util.Locale;

import com.vaadin.data.util.converter.AbstractStringToNumberConverter;

public class ChartDesignFormatter {

    private static boolean initialized;

    public static void init() {
        if(!initialized) {
            DesignAttributeHandler.getFormatter()
                .addConverter(new NumberToStringConverter());
            initialized = true;
        }
    }

    private static class NumberToStringConverter extends AbstractStringToNumberConverter<Number> {

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
}
