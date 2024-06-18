/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONParser;
import com.vaadin.addon.charts.client.HighchartsScriptLoader;
import com.vaadin.addon.charts.shared.ChartOptionsState;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

@SuppressWarnings("serial")
@Connect(com.vaadin.addon.charts.ChartOptions.class)
public class ChartOptionsConnector extends AbstractExtensionConnector {

    public ChartOptionsConnector() {
        HighchartsScriptLoader.ensureInjected();
    }

    @Override
    public void onStateChanged(StateChangeEvent event) {
        super.onStateChanged(event);

        if (getState().json != null) {
            JavaScriptObject options = JSONParser.parseLenient(getState().json)
                    .isObject().getJavaScriptObject();
            applyOptions(options);
        }
    }

    private native void applyOptions(JavaScriptObject obj)
    /*-{
        $wnd.Highcharts.setOptions(obj);
    }-*/;

    @Override
    public ChartOptionsState getState() {
        return (ChartOptionsState) super.getState();
    }

    @Override
    protected void extend(ServerConnector target) {
        // NOP
    }
}
