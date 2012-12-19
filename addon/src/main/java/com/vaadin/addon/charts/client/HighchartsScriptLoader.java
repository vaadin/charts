package com.vaadin.addon.charts.client;

/**
 * Helper to load javascripts required by Highcharts
 */
public class HighchartsScriptLoader {

    private static boolean injected;

    /**
     * Highchart related widgets and GWT overalays should ensure scripts are
     * injected to host page using this method.
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
