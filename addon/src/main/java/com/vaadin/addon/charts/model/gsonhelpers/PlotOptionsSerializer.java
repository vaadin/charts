package com.vaadin.addon.charts.model.gsonhelpers;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.vaadin.addon.charts.model.AbstractPlotOptions;
import com.vaadin.addon.charts.model.AbstractPlotOptionsList;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.PlotOptionsAreaRange;
import com.vaadin.addon.charts.model.PlotOptionsAreaSpline;
import com.vaadin.addon.charts.model.PlotOptionsAreaSplineRange;
import com.vaadin.addon.charts.model.PlotOptionsBar;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsColumnRange;
import com.vaadin.addon.charts.model.PlotOptionsGauge;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.PlotOptionsScatter;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.addon.charts.model.PlotOptionsSpline;

/**
 * Serializes all the classes extending AbstractPlotOptions
 */
public class PlotOptionsSerializer implements
        JsonSerializer<AbstractPlotOptionsList> {

    @Override
    public JsonElement serialize(AbstractPlotOptionsList list, Type typeOfSrc,
            JsonSerializationContext context) {
        JsonObject plotoptions = new JsonObject();
        for (int i = 0; i < list.getNumberOfPlotOptions(); i++) {
            AbstractPlotOptions src = list.getPlotOptions(i);
            JsonObject opts = new JsonObject();
            if (src instanceof PlotOptionsArea) {
                opts.add("states",
                        context.serialize(((PlotOptionsArea) src).getStates()));
                plotoptions.add("area", opts);
            } else if (src instanceof PlotOptionsBar) {
                plotoptions.add("bar", opts);
            } else if (src instanceof PlotOptionsColumn) {
                opts.add("pointPadding", context
                        .serialize(((PlotOptionsColumn) src).getPointPadding()));
                opts.add("borderWidth", context
                        .serialize(((PlotOptionsColumn) src).getBorderWidth()));
                opts.add("groupPadding", context
                        .serialize(((PlotOptionsColumn) src).getGroupPadding()));
                plotoptions.add("column", opts);
            } else if (src instanceof PlotOptionsColumnRange) {
                plotoptions.add("columnrange", opts);
            } else if (src instanceof PlotOptionsGauge) {
                opts.add("dial",
                        context.serialize(((PlotOptionsGauge) src).getDial()));
                plotoptions.add("gauge", opts);
            } else if (src instanceof PlotOptionsLine) {
                plotoptions.add("line", opts);
            } else if (src instanceof PlotOptionsPie) {
                plotoptions.add("pie", opts);
            } else if (src instanceof PlotOptionsSeries) {
                opts.add("states", context.serialize(((PlotOptionsSeries) src)
                        .getStates()));
                opts.add("groupPadding", context
                        .serialize(((PlotOptionsSeries) src).getGroupPadding()));
                plotoptions.add("series", opts);
            } else if (src instanceof PlotOptionsSpline) {
                opts.add("states", context.serialize(((PlotOptionsSpline) src)
                        .getStates()));
                plotoptions.add("spline", opts);
            } else if (src instanceof PlotOptionsScatter) {
                plotoptions.add("scatter", opts);
            } else if (src instanceof PlotOptionsAreaRange) {
                plotoptions.add("arearange", opts);
            } else if (src instanceof PlotOptionsAreaSpline) {
                plotoptions.add("areaspline", opts);
            } else if (src instanceof PlotOptionsAreaSplineRange) {
                plotoptions.add("areasplinerange", opts);
            }
            // serialize rest of stuff

            opts.add("cursor", context.serialize(src.getCursor()));
            opts.add("dataLabels", context.serialize(src.getDataLabels()));
            opts.add("fillColor", context.serialize(src.getFillColor()));
            opts.add("fillOpacity", context.serialize(src.getFillOpacity()));
            opts.add("lineColor", context.serialize(src.getLineColor()));
            opts.add("lineWidth", context.serialize(src.getLineWidth()));
            opts.add("marker", context.serialize(src.getMarker()));
            opts.add("pointInterval", context.serialize(src.getPointInterval()));
            opts.add("pointPlacement",
                    context.serialize(src.getPointPlacement()));
            opts.add("pointStart", context.serialize(src.getPointStart()));
            opts.add("stacking", context.serialize(src.getStacking()));
            opts.add("zIndex", context.serialize(src.getzIndex()));
        }
        return plotoptions;
    }
}
