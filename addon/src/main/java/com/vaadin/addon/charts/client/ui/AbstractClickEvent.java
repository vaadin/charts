package com.vaadin.addon.charts.client.ui;

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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Abstract class with common attributes for click events
 *
 */
public class AbstractClickEvent extends JavaScriptObject {
    protected AbstractClickEvent() {
    }

    public native final boolean isAltKey()
    /*-{
        return this.altKey;
    }-*/;

    public native final boolean isCtrlKey()
    /*-{
        return this.ctrlKey;
    }-*/;

    public native final boolean isShiftKey()
    /*-{
        return this.shiftKey;
    }-*/;

    public native final boolean isMetaKey()
    /*-{
        return this.metaKey;
    }-*/;

    public native final int getButton()
    /*-{
        return this.button;
    }-*/;

}
