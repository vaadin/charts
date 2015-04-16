package com.vaadin.addon.charts.client;

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

    @Source("highcharts.js")
    TextResource highcharts();

    @Source("vaadintheme.js")
    TextResource defaultTheme();

    /**
     * @return
     * @deprecated use {@link #standaloneframework()} instead
     */
    @Source("jquery.min.js")
    @Deprecated
    TextResource jquery();

    @Source("standalone-framework.js")
    TextResource standaloneframework();

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

}
