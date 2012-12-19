package com.vaadin.addon.charts.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle that contains java scripts etc required for Vaadin Charts.
 * <p>
 * Actual injection of these scripts are done by {@link HighchartsScriptLoader},
 * but to e.g. change script versions you can just replace bundle with GWT
 * deferred binding.
 */
public interface HighchartResources extends ClientBundle {
    public static final HighchartResources INSTANCE = GWT
            .create(HighchartResources.class);

    @Source("highcharts.src.js")
    TextResource highcharts();

    @Source("vaadintheme.js")
    TextResource defaultTheme();

    @Source("jquery.min.js")
    TextResource jquery();

    @Source("exporting.js")
    TextResource exporting();

    @Source("highcharts-more.js")
    TextResource highchartsMore();
}
