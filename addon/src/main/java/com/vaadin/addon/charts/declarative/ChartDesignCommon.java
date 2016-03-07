package com.vaadin.addon.charts.declarative;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

class ChartDesignCommon implements Serializable {
    // Prefix is used for tags which names are reserved in HTML
    // like style and title
    static final String CHART_PREXIX = "chart-";

    private static final List<String> reservedWords = Arrays.asList("title", "style", "area", "frame");

    static boolean isReservedWord(String camelcase) {
        return reservedWords.contains(camelcase);
    }
}
