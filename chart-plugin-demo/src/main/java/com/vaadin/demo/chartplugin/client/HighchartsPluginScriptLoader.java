/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.demo.chartplugin.client;

import com.vaadin.addon.charts.client.HighchartResources;
import com.vaadin.addon.charts.client.HighchartsScriptLoader;

/**
 * Extends Vaadin Chart's {@link HighchartsScriptLoader} and overrides the
 * {@link #injectResources()} method to inject <code>map.src.js</code> and
 * <code>world-map-shapes.js</code> files in addition to the Vaadin Chart's
 * original resources. </br>This class should also replace the original
 * <code>HighchartsScriptLoader</code> with the following GWT deferred binding
 * in the module XML:</br></br>
 * 
 * <code>
 *       &lt;replace-with class="com.vaadin.demo.chartplugin.client.HighchartsPluginScriptLoader"&gt;</br>
          &nbsp; &nbsp; &nbsp;&lt;when-type-is class="com.vaadin.addon.charts.client.HighchartsScriptLoader"/&gt;</br>
         &lt;/replace-with&gt;
 * </code>
 * 
 */
public class HighchartsPluginScriptLoader extends HighchartsScriptLoader {

	@Override
	protected void injectResources() {

		// Inject highcharts only if not already injected
		if (!hasHighcharts()) {
			inject(HighchartResources.INSTANCE.highstock().getText());
			inject(HighchartResources.INSTANCE.noData().getText());
			inject(HighchartResources.INSTANCE.highchartsMore().getText());
			inject(HighchartResources.INSTANCE.funnel().getText());
			inject(HighchartResources.INSTANCE.exporting().getText());
			// Map plugin needs to be injected before defaultTheme and after highcharts
			inject(HighchartPluginResources.INSTANCE.worldMapShapes().getText());
			inject(HighchartPluginResources.INSTANCE.mapSrcJs().getText());
			inject(HighchartResources.INSTANCE.defaultTheme().getText());
			inject(HighchartResources.INSTANCE.highcharts3d().getText());
			inject(HighchartResources.INSTANCE.solidGauge().getText());
			inject(HighchartResources.INSTANCE.heatmap().getText());
			inject(HighchartResources.INSTANCE.treemap().getText());
			inject(HighchartResources.INSTANCE.drilldown().getText());
		}
	}
}
