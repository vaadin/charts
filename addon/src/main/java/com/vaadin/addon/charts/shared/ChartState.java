/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.shared;

import com.vaadin.shared.AbstractComponentState;

public class ChartState extends AbstractComponentState {

    public String confState;
    /**
     * Hacky helper field that can be used to force state change event. TODO
     * figure out a better method.
     */
    public int paintCount;
    public String jsonState;

    public boolean seriesVisibilityTogglingDisabled;
    public boolean timeline;
}
