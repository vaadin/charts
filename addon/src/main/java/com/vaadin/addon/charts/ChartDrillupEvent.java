package com.vaadin.addon.charts;

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

import com.vaadin.ui.Component;

/**
 * ChartDrillupEvent triggered when the 'Back to previous series' button is
 * clicked
 */
public class ChartDrillupEvent extends com.vaadin.ui.Component.Event {

    private static final long serialVersionUID = -2539790491959770326L;

    public ChartDrillupEvent(Component source) {
        super(source);
    }

}
