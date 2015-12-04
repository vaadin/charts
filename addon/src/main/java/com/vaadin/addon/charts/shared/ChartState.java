package com.vaadin.addon.charts.shared;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
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
}
