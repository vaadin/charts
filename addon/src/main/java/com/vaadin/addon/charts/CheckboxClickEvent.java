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
package com.vaadin.addon.charts;

import com.vaadin.addon.charts.model.Series;
import com.vaadin.ui.Component;

/**
 * CheckboxClickEvent triggered when a checkbox in a legend is clicked
 */
public class CheckboxClickEvent extends AbstractSeriesEvent {

    private final boolean checked;

    public CheckboxClickEvent(Component source, boolean isChecked,
        Series series, int seriesItemIndex) {
        super(source, series, seriesItemIndex);
        checked = isChecked;
    }

    public boolean isChecked() {
        return checked;
    }

}
