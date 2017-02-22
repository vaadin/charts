package com.vaadin.ui.declarative;

import java.util.Locale;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.data.converter.AbstractStringToNumberConverter;

public class ChartDesignFormatter {

    private static boolean initialized;

    public static void init() {

        if (!initialized) {
            DesignAttributeHandler.getFormatter().addConverter(Number.class,
                    new NumberToStringConverter());
            DesignAttributeHandler.getFormatter().addConverter(Color.class,
                    new StringToColorConverter());
            initialized = true;
        }
    }

    private static class NumberToStringConverter
            extends AbstractStringToNumberConverter<Number> {

        public NumberToStringConverter() {
            super(null, "");
        }

        @Override
        public Result<Number> convertToModel(String value,
                ValueContext context) {
            return convertToNumber(value, getLocaleFromContext(context));
        }

    }

    private static Locale getLocaleFromContext(ValueContext context) {
        return context.getLocale().isPresent() ? context.getLocale().get()
                : Locale.getDefault();
    }

    private static class StringToColorConverter
            implements Converter<String, Color> {

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
    }
}
