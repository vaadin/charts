package com.vaadin.addon.charts.client.ui;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONParser;
import com.vaadin.addon.charts.client.HighchartsScriptLoader;
import com.vaadin.terminal.gwt.client.VConsole;

@SuppressWarnings("serial")
public class ChartOptionsConnector {
    
    public ChartOptionsConnector() {
        HighchartsScriptLoader.ensureInjected();
    }

    public void onStateChanged() {
        VConsole.log("OptionsConnector onStateChange");

        if (getState() != null) {
            JavaScriptObject options = JSONParser.parseLenient(getState())
                    .isObject().getJavaScriptObject();
            applyOptions(options);
        }
    }

    private native void applyOptions(JavaScriptObject obj)
    /*-{
        $wnd.Highcharts.setOptions(obj);
    }-*/;

    public String getState() {
    	return null;
    }

}
