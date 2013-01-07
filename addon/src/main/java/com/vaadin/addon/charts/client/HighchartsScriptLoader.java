package com.vaadin.addon.charts.client;

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

/**
 * Helper to load the JavaScripts required by Highcharts
 */
public class HighchartsScriptLoader {

    private static boolean injected;

    /**
     * Highchart related widgets and GWT overlays should ensure scripts are
     * injected in the host page using this method.
     */
    public static void ensureInjected() {
        if (!injected) {
            // As jquery is so popular, inject it conditionally
            if (!hasJQuery()) {
                inject(HighchartResources.INSTANCE.jquery().getText());
            }
            inject(HighchartResources.INSTANCE.highcharts().getText());
            inject(HighchartResources.INSTANCE.highchartsMore().getText());
            inject(HighchartResources.INSTANCE.exporting().getText());
            inject(HighchartResources.INSTANCE.defaultTheme().getText());
            injected = true;
        }
    }

    private native static boolean hasJQuery()
    /*-{
        if($wnd.jQuery)
            return true;
        return false;
    }-*/;

    private static native void inject(String script)
    /*-{
        $wnd.eval(script);
    }-*/;

}
