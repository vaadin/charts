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
        // As jquery is so popular, inject it conditionally
        if (!hasJQuery()) {
            inject(HighchartResources.INSTANCE.jquery().getText());
        }
        // This would be default, but we also demo using newer highcharts version
//        inject(HighchartResources.INSTANCE.highcharts().getText());

        inject(HighchartPluginResources.INSTANCE.highcharts3().getText());
        
        // Map plugin needs to be injected before defaultTheme and after jquery
        // and highcharts
        inject(HighchartPluginResources.INSTANCE.worldMapShapes().getText());
        inject(HighchartPluginResources.INSTANCE.mapSrcJs().getText());

        // This is new extra module in HC3
        inject(HighchartPluginResources.INSTANCE.funnel().getText());
        
        // inject(HighchartResources.INSTANCE.highchartsMore().getText());
        // inject(HighchartResources.INSTANCE.exporting().getText());
        inject(HighchartPluginResources.INSTANCE.more3().getText());
        inject(HighchartPluginResources.INSTANCE.exporting3().getText());
        inject(HighchartResources.INSTANCE.defaultTheme().getText());
    }
}
