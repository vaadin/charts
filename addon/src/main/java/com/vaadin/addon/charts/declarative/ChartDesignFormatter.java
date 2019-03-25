package com.vaadin.addon.charts.declarative;

/*-
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2019 Vaadin Ltd
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
