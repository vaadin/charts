/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model.serializers;

import static com.vaadin.addon.charts.model.style.ButtonTheme.DEFAULT_WIDTH;

import com.vaadin.addon.charts.model.style.ButtonTheme;

/**
 * Value filter helper for {@link ButtonTheme} serialization. <br>
 * Workaround for
 * <a href="https://github.com/FasterXML/jackson-databind/issues/1663">
 * jackson-databind issue with include non default</a>
 */
public class ButtonThemeWidthFilter {

    @Override
    public boolean equals(Object other) {
        if (other != null) {
            return other.equals(DEFAULT_WIDTH);
        } else {
            return false;
        }
    }
}
