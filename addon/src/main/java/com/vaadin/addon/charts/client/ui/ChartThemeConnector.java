package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONParser;
import com.vaadin.addon.charts.client.HighchartsScriptLoader;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.VConsole;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

@SuppressWarnings("serial")
@Connect(com.vaadin.addon.charts.ChartTheme.class)
public class ChartThemeConnector extends AbstractExtensionConnector {
    
    public ChartThemeConnector() {
        HighchartsScriptLoader.ensureInjected();
    }

    @Override
    public void onStateChanged(StateChangeEvent event) {
        VConsole.log("ThemerConnector onStateChange");
        super.onStateChanged(event);

        if (getState().json != null) {
            JavaScriptObject theme = JSONParser.parseLenient(getState().json)
                    .isObject().getJavaScriptObject();
            applyTheme(theme);
        }
    }

    private native void applyTheme(JavaScriptObject obj)
    /*-{
        $wnd.Highcharts.setOptions(obj);
    }-*/;

    @Override
    public ChartThemeState getState() {
        return (ChartThemeState) super.getState();
    }

    @Override
    protected void extend(ServerConnector target) {
        // NOP
    }
}
