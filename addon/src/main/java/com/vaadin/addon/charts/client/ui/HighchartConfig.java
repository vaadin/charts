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
import com.google.gwt.user.client.Element;

public class HighchartConfig extends JavaScriptObject {

    protected HighchartConfig() {
    };

    public static HighchartConfig createFromServerSideString(String confStr,
            String jsonState) {
        HighchartConfig conf;
        if (confStr != null) {
            conf = (HighchartConfig) JSONParser.parseLenient(confStr)
                    .isObject().getJavaScriptObject();
            conf.prepare();
        } else {
            conf = JavaScriptObject.createObject().cast();
        }
        if (jsonState != null) {
            merge(conf, jsonState);
        }

        return conf;
    }

    private static native void merge(HighchartConfig conf, String jsonState)
    /*-{
        var cfg = $wnd.eval('('+jsonState+')');
        $wnd.jQuery.extend(true, conf, cfg);
    }-*/;

    /**
     * Searches all fields with _fn_ prefix and generates new field without
     * prefix with eval around the string code.
     */
    private final native void searchAndEvalFields()
    /*-{
        var re = /( |\+)(Highcharts\.)/g;
        var re_fn_prop = /^_fn_/;
        (function recursiveFunction(obj) {
            for (var prop in obj) {
                if (obj.hasOwnProperty(prop)) {
                    var childobj = obj[prop];
                    if (prop.indexOf("_fn_") == 0) {
                        try {
                            var script = childobj;
                            var actualPropName = prop.replace(re_fn_prop, '');
                            script = script.replace(re, "$1\$wnd.$2");
                            if(script.indexOf("function()") != 0) {
                                if(script.indexOf("return") != 0) {
                                    script = "return " + script;
                                }
                                script = "function() {" + script + "}";
                            }
                            obj[actualPropName] = eval('(' + script + ')');
                            obj[prop] = null;
                        } catch (e) {
                            // TODO report on VConsole before release
                            alert("Failed to evaluate formatter");
                            alert(e);
                        }
                    } else {
                        if(typeof childobj == 'object' && childobj != null) {
                            recursiveFunction(childobj);
                        }
                    }
                }
            }
        })(this);
    }-*/;

    private void prepare() {
        searchAndEvalFields();
    }

    public final native void setClickHandler(ChartClickHandler handler)
    /*-{
        if(!this.chart) this.chart = {};
        if(!this.chart.events) this.chart.events = {};
        this.chart.events.click = function(e) {
            $entry(handler.@com.vaadin.addon.charts.client.ui.ChartClickHandler::onClick(Lcom/vaadin/addon/charts/client/ui/ChartClickEvent;)(e));
        };
    }-*/;

    public final native void setSeriesPointClickHandler(
            PointClickHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.series.point.events");
        this.plotOptions.series.point.events.click = function(e) {
            $entry(handler.@com.vaadin.addon.charts.client.ui.PointClickHandler::onClick(Lcom/vaadin/addon/charts/client/ui/PointClickEvent;)(e));
        };
    }-*/;

    public final native void setColumnClickHandler(PointClickHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.column.point.events");
        this.plotOptions.series.point.events.click = function(e) {
            $entry(handler.@com.vaadin.addon.charts.client.ui.PointClickHandler::onClick(Lcom/vaadin/addon/charts/client/ui/PointClickEvent;)(e));
        };
    }-*/;

    public final native void setChartSelectionHandler(
            ChartSelectionHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"chart.events");
        this.chart.events.selection = function(e) {
            $entry(handler.@com.vaadin.addon.charts.client.ui.ChartSelectionHandler::onSelection(Lcom/vaadin/addon/charts/client/ui/ChartSelectionEvent;)(e));
        };
    }-*/;

    public final native void setLegendItemClickHandler(
            LegendItemClickHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.series.events");
               
        this.plotOptions.series.events.legendItemClick = function(e) {
            $entry(handler.@com.vaadin.addon.charts.client.ui.LegendItemClickHandler::onClick(Lcom/vaadin/addon/charts/client/ui/LegendItemClickEvent;)(e));
        };
    }-*/;

    public static final native void ensureObjectStructure(JavaScriptObject obj,
            String path)
    /*-{
     var parts = path.split(".");
        for (var i=0; i<parts.length; i++) {
            if (!obj[parts[i]]) obj[parts[i]] = {};
            obj = obj[parts[i]];
        }
    }-*/;

    public native final HighchartJsOverlay renderTo(Element element)
    /*-{
        if(!this.chart) this.chart = {};
        this.chart.renderTo = element;
        return new $wnd.Highcharts.Chart(this);
    }-*/;

}
