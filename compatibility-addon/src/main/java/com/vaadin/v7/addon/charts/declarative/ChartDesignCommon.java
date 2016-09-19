package com.vaadin.v7.addon.charts.declarative;

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

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

class ChartDesignCommon implements Serializable {
    // Prefix is used for tags which names are reserved in HTML
    // like style and title
    static final String CHART_PREFIX = "chart-";
    // Prefix is used for properties that conflict with polymer listeners
    static final String RESERVED_PROPERTY_PREFIX = "draw-";

    private static final List<String> reservedWords = Arrays.asList("title",
            "style", "area", "frame");
    private static final List<String> reservedPropertyNames = Arrays.asList(
            "on-series", "on-key");

    static boolean isReservedWord(String camelcase) {
        return reservedWords.contains(camelcase);
    }

    static boolean isReservedProperty(String camelcase) {
        return reservedPropertyNames.contains(camelcase);
    }
}
