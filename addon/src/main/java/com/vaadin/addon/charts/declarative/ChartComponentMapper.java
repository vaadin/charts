package com.vaadin.addon.charts.declarative;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.ui.Component;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.declarative.DesignContext;
import com.vaadin.ui.declarative.DesignException;


public class ChartComponentMapper extends Design.DefaultComponentMapper {

    @Override
    public Component tagToComponent(String tagName,
        Design.ComponentFactory componentFactory, DesignContext context) {
        boolean isChartTag = Pattern.matches("^vaadin-.*-chart$", tagName);
        if(isChartTag) {
            String[] split = tagName.split("-");
            if(split.length != 3) {
                throw new DesignException("Unknown tag: " + tagName);
            }
            String typeName = split[1];
            ChartType chartType = resolveChartTypeFor(typeName);
            if(chartType == null) {
                throw new DesignException("Unknown tag: " + tagName);
            }
            return new Chart(chartType);
        }

        return super.tagToComponent(tagName, componentFactory, context);
    }

    private ChartType resolveChartTypeFor(String type) {
        for (Field field : ChartType.class.getDeclaredFields()) {
            if(!ChartType.class.equals(field.getType())) {
                continue;
            }
            try {
                ChartType chartType = (ChartType) field.get(null);
                if(type.equals(chartType.toString())) {
                    return chartType;
                }
            } catch (IllegalAccessException e) {
                return null;
            }
        }

        return null;
    }

    @Override
    public String componentToTag(Component component,
        DesignContext context) {
        if(component instanceof Chart) {
            ChartType type = resolveType((Chart) component);
            return "vaadin-"+type+"-chart";
        }
        return super.componentToTag(component, context);
    }

    private ChartType resolveType(Chart chart) {
        ChartType defaultType = ChartType.LINE;
        if(chart.getConfiguration() == null || chart.getConfiguration().getChart() == null
            || chart.getConfiguration().getChart().getType() == null) {
            return defaultType;
        }
        return chart.getConfiguration().getChart().getType();
    }

}
