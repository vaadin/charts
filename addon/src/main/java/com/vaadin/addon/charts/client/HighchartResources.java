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
package com.vaadin.addon.charts.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle that contains the JavaScripts, etc required for Vaadin Charts.
 * <p>
 * Actual injection of these scripts is done by {@link HighchartsScriptLoader},
 * but to e.g. change script versions you can just replace this bundle with GWT
 * deferred binding.
 */
public interface HighchartResources extends ClientBundle {
    public static final HighchartResources INSTANCE = GWT
            .create(HighchartResources.class);

    @Source("highstock.js")
    TextResource highstock();

    @Source("no-data-to-display.js")
    TextResource noData();

    @Source("vaadintheme.js")
    TextResource defaultTheme();

    @Source("exporting.js")
    TextResource exporting();

    @Source("highcharts-more.js")
    TextResource highchartsMore();

    @Source("funnel.js")
    TextResource funnel();

    @Source("highcharts-3d.js")
    TextResource highcharts3d();

    @Source("solid-gauge.js")
    TextResource solidGauge();

    @Source("heatmap.js")
    TextResource heatmap();

    @Source("treemap.js")
    TextResource treemap();

    @Source("drilldown.js")
    TextResource drilldown();

    @Source("highcharts-workarounds.js")
    TextResource highchartsWorkarounds();

    @Source("highcharts-drilldown-workarounds.js")
    TextResource highchartsDrilldownWorkarounds();

}
