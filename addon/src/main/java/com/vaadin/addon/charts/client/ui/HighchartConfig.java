package com.vaadin.addon.charts.client.ui;

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
        conf.initMouseOverHandler();

        return conf;
    }

    private static native void merge(HighchartConfig conf, String jsonState)
    /*-{
        var cfg = $wnd.eval('('+jsonState+')');
        var merge = function(dst, src) {
          for (var property in src) {
            if (src[property] && typeof src[property] === "object") {
              if(!dst[property]) {
                  dst[property] = src[property];
              } else {
                  arguments.callee(dst[property], src[property]);
              }
            } else {
              dst[property] = src[property];
            }
          }
        };
        merge(conf, cfg);
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
                            // "0 ||" to bypass IE8 bug
                            obj[actualPropName] = eval('(0 || ' + script + ')');
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
        this.chart.events.click = $entry(function(e) {
            return handler.@com.vaadin.addon.charts.client.ui.ChartClickHandler::onClick(Lcom/vaadin/addon/charts/client/ui/ChartClickEvent;)(e);
        });
    }-*/;

    public final native void setDrilldownHandler(ChartDrilldownHandler handler)
    /*-{
        if(!this.chart) this.chart = {};
        if(!this.chart.events) this.chart.events = {};
        this.chart.events.drilldown = $entry(function(e) {
            return handler.@com.vaadin.addon.charts.client.ui.ChartDrilldownHandler::onDrilldown(Lcom/vaadin/addon/charts/client/ui/ChartDrilldownEvent;)(e);
        });
        this.chart.events.drillup = $entry(function(e) {
            return handler.@com.vaadin.addon.charts.client.ui.ChartDrilldownHandler::onDrillup()();
        });
    }-*/;

    public final native void setCheckboxClickHandler(CheckboxClickHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.series.events");
        this.plotOptions.series.events.checkboxClick = $entry(function(e) {
            return handler.@com.vaadin.addon.charts.client.ui.CheckboxClickHandler::onClick(Lcom/vaadin/addon/charts/client/ui/CheckboxClickEvent;)(e);
        });
    }-*/;

    public final native void setSeriesHideHandler(SeriesHideHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.series.events");
        this.plotOptions.series.events.hide = $entry(function(e) {
            return handler.@com.vaadin.addon.charts.client.ui.SeriesHideHandler::onHide(Lcom/vaadin/addon/charts/client/ui/SeriesEvent;)(e);
        });
    }-*/;

    public final native void setSeriesShowHandler(SeriesShowHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.series.events");
        this.plotOptions.series.events.show = $entry(function(e) {
            return handler.@com.vaadin.addon.charts.client.ui.SeriesShowHandler::onShow(Lcom/vaadin/addon/charts/client/ui/SeriesEvent;)(e);
        });
    }-*/;

    public final native void setXAxesAfterSetExtremeHandler(
        AfterSetExtremeHandler handler)
    /*-{
        if(!this.xAxis) this.xAxis = {};
        var axes = [].concat(this.xAxis);
        @com.vaadin.addon.charts.client.ui.HighchartConfig::setAfterExtremesHandlerTo(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/vaadin/addon/charts/client/ui/AfterSetExtremeHandler;)(axes, handler);
    }-*/;

    public final native void setYAxesAfterSetExtremeHandler(
        AfterSetExtremeHandler handler)
    /*-{
        if(!this.yAxis) this.yAxis = {};
        var axes = [].concat(this.yAxis);
        @com.vaadin.addon.charts.client.ui.HighchartConfig::setAfterExtremesHandlerTo(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/vaadin/addon/charts/client/ui/AfterSetExtremeHandler;)(axes, handler);
    }-*/;

    public static final native void setAfterExtremesHandlerTo(JavaScriptObject axes, AfterSetExtremeHandler handler)
    /*-{
        axes.map(function(axis) {
            if(!axis.events) axis.events = {};
            axis.events.afterSetExtremes = $entry(function(e) {
                return handler.@com.vaadin.addon.charts.client.ui.AfterSetExtremeHandler::afterSetExtreme(Lcom/vaadin/addon/charts/client/ui/SetExtremesEvent;)(e);
            });
        });
    }-*/;

    public final native void setSeriesPointClickHandler(
            PointClickHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.series.point.events");
        this.plotOptions.series.point.events.click = $entry(function(e) {
            //only handle event if point is not drilldown
            //and point properties are not null
            if(!this.drilldown && this.series){
                return handler.@com.vaadin.addon.charts.client.ui.PointClickHandler::onClick(Lcom/vaadin/addon/charts/client/ui/PointClickEvent;)(e);
            }
        });
    }-*/;

    public final native void setColumnClickHandler(PointClickHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.column.point.events");
        this.plotOptions.series.point.events.click = $entry(function(e) {
            return handler.@com.vaadin.addon.charts.client.ui.PointClickHandler::onClick(Lcom/vaadin/addon/charts/client/ui/PointClickEvent;)(e);
        });
    }-*/;

    public final native void setChartSelectionHandler(
            ChartSelectionHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"chart.events");
        this.chart.events.selection = $entry(function(e) {
            return handler.@com.vaadin.addon.charts.client.ui.ChartSelectionHandler::onSelection(Lcom/vaadin/addon/charts/client/ui/ChartSelectionEvent;)(e);
        });
    }-*/;

    public final native void setLegendItemClickHandler(
            LegendItemClickHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.series.events");
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.pie.point.events");
        
        this.plotOptions.series.events.legendItemClick = this.plotOptions.pie.point.events.legendItemClick = $entry(function(e) {
            return handler.@com.vaadin.addon.charts.client.ui.LegendItemClickHandler::onClick(Lcom/vaadin/addon/charts/client/ui/SeriesEvent;)(e);
        });
    }-*/;


    public final native void setPointSelectHandler(PointSelectHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.series.point.events");

        this.plotOptions.series.point.events.select = $entry(function(e) {
            return handler.@com.vaadin.addon.charts.client.ui.PointSelectHandler::onSelect(Lcom/vaadin/addon/charts/client/ui/PointEvent;)(e);
        });
    }-*/;

    public final native void setPointUnselectHandler(PointUnselectHandler handler)
    /*-{
        @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.series.point.events");

        this.plotOptions.series.point.events.unselect = $entry(function(e) {
            return handler.@com.vaadin.addon.charts.client.ui.PointUnselectHandler::onUnselect(Lcom/vaadin/addon/charts/client/ui/PointEvent;)(e);
        });
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

    /**
     * If data series item has a defined cursor it will be set when mouse is
     * over the item
     */
    private final native void initMouseOverHandler()
    /*-{
       @com.vaadin.addon.charts.client.ui.HighchartConfig::ensureObjectStructure(Lcom/google/gwt/core/client/JavaScriptObject;Ljava/lang/String;)(this,"plotOptions.series.point.events");
       this.plotOptions.series.point.events.mouseOver = function(){
           if(this.cursor){
               this.graphic.element.style.cursor = this.cursor;
           }
       };
    }-*/;

    public native final HighchartJsOverlay renderTo(Element element)
    /*-{
        if(!this.chart) this.chart = {};
        this.chart.renderTo = element;
        return new $wnd.Highcharts.Chart(this);
    }-*/;

}
