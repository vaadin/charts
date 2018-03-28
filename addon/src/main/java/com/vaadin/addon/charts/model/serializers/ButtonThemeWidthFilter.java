package com.vaadin.addon.charts.model.serializers;

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