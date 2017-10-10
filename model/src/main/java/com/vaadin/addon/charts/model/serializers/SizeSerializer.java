package com.vaadin.addon.charts.model.serializers;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2016 Vaadin Ltd
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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vaadin.server.SizeWithUnit;
import com.vaadin.server.Sizeable.Unit;

import java.io.IOException;

public class SizeSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String tmpValue, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException,
            JsonProcessingException {
        SizeWithUnit sizeWithUnit = SizeWithUnit.parseStringSize(tmpValue);
        if (sizeWithUnit != null) {
            Unit unit = sizeWithUnit.getUnit();
            float size = sizeWithUnit.getSize();
            if (unit == Unit.PIXELS && size != -1.0f) {
                jsonGenerator.writeNumber(size);
            } else {
                jsonGenerator.writeObject(tmpValue);
            }
        }
    }
}
