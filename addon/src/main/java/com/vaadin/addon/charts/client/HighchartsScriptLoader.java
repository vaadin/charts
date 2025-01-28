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
package com.vaadin.addon.charts.client;

import com.google.gwt.core.client.GWT;

/**
 * Helper to load the JavaScripts required by Highcharts
 */
public class HighchartsScriptLoader {

    private static final HighchartsScriptLoader INSTANCE = GWT
            .create(HighchartsScriptLoader.class);

    private static boolean injected;

    /**
     * Highchart related widgets and GWT overlays should ensure scripts are
     * injected in the host page using this method.
     */
    public static void ensureInjected() {
        if (!injected) {
            INSTANCE.injectResources();
            injected = true;
        }
    }

    protected void injectResources() {
        // Inject highcharts only if not already injected
        if (!hasHighcharts()) {
            inject(HighchartResources.INSTANCE.highstock().getText());
            inject(HighchartResources.INSTANCE.highchartsWorkarounds().getText());
        }
        // Load heatmap before treemap
        if (!hasHeatmap()) {
            inject(HighchartResources.INSTANCE.heatmap().getText());
        }
        // Inject highchartsmore only if not already injected
        if (!hasHighchartsMore()) {
            inject(HighchartResources.INSTANCE.highchartsMore().getText());
        }
        // Inject other resources only if not already injected
        if (!hasExtraImports()) {
            inject(HighchartResources.INSTANCE.noData().getText());
            inject(HighchartResources.INSTANCE.funnel().getText());
            inject(HighchartResources.INSTANCE.exporting().getText());
            inject(HighchartResources.INSTANCE.defaultTheme().getText());
            inject(HighchartResources.INSTANCE.highcharts3d().getText());
            inject(HighchartResources.INSTANCE.solidGauge().getText());
            inject(HighchartResources.INSTANCE.treemap().getText());
            inject(HighchartResources.INSTANCE.drilldown().getText());
            inject(HighchartResources.INSTANCE.highchartsDrilldownWorkarounds().getText());
        }
    }

    protected native static boolean hasHighcharts()
    /*-{
        if($wnd.Highcharts)
            return true;
        return false;
    }-*/;

    protected native static boolean hasHeatmap()
    /*-{
        if($wnd.Highcharts.seriesTypes.heatmap)
            return true;
        return false;
    }-*/;

    /**
     * Funnel should be enough to check that extra modules are missing
     *
     * @return true if other imports were loaded
     */
    protected native static boolean hasExtraImports()
    /*-{
        if($wnd.Highcharts.seriesTypes.funnel)
            return true;
        return false;
    }-*/;

    protected native static boolean hasHighchartsMore()
    /*-{
        if($wnd.Highcharts.seriesTypes.bubble)
            return true;
        return false;
    }-*/;

    protected static native void inject(String script)
    /*-{
        $wnd.eval(script);
    }-*/;
}
