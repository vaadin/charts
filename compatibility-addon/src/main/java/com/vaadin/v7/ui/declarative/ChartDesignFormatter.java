package com.vaadin.v7.ui.declarative;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
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

import java.util.Locale;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.v7.data.util.converter.AbstractStringToNumberConverter;
import com.vaadin.v7.data.util.converter.Converter;

public class ChartDesignFormatter {
    private static boolean initialized;

    public static void init() {
        if (!initialized) {
//            DesignAttributeHandler.getFormatter().addConverter(
//                    new NumberToStringConverter());
//            DesignAttributeHandler.getFormatter().addConverter(
//                    new StringToColorConverter());
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
                throws com.vaadin.v7.data.util.converter.Converter.ConversionException {
            return new SolidColor(value);
        }

        @Override
        public String convertToPresentation(Color value, Class<? extends String> targetType, Locale locale) throws
                com.vaadin.v7.data.util.converter.Converter.ConversionException {
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