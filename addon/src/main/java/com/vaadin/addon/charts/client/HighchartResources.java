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
