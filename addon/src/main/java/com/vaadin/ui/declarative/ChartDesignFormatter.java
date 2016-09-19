package com.vaadin.ui.declarative;

import java.util.Locale;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.data.Result;
import com.vaadin.data.util.converter.AbstractStringToNumberConverter;
import com.vaadin.data.util.converter.Converter;

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

    private static class NumberToStringConverter extends AbstractStringToNumberConverter<Number> {

        public NumberToStringConverter() {
            super("");
        }
        public NumberToStringConverter(String error){
            super(error);
        }
        @Override
        public Result<Number> convertToModel(String value, Locale locale) {
             return convertToNumber(value,  locale);
        }


    }

    private static class StringToColorConverter implements Converter<String, Color> {


        @Override
        public Result<Color> convertToModel(String value, Locale locale) {
            Color color = new SolidColor(value);
            return Result.ok(color);
        }

        @Override
        public String convertToPresentation(Color value, Locale locale) {
            if (value instanceof SolidColor) {
                return value.toString();
            }
            return null;
        }
    }
}
