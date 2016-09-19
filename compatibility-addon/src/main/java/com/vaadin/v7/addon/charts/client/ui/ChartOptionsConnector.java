package com.vaadin.v7.addon.charts.client.ui;

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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONParser;
import com.vaadin.v7.addon.charts.ChartOptions;
import com.vaadin.v7.addon.charts.client.HighchartsScriptLoader;
import com.vaadin.v7.addon.charts.shared.ChartOptionsState;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

@SuppressWarnings("serial")
@Connect(ChartOptions.class)
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
