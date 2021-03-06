package com.vaadin.addon.charts.model.style;

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

import com.vaadin.addon.charts.model.ChartEnum;

/**
 * CSS position attribute, ABSOLUTE or RELATIVE
 */
public enum StylePosition implements ChartEnum {
    ABSOLUTE("absolute"), RELATIVE("relative");

    private String position;

    private StylePosition(String position) {
        this.position = position;
    }

    public String toString() {
        return position;
    }

}
