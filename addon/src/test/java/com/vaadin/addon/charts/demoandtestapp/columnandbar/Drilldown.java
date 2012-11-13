package com.vaadin.addon.charts.demoandtestapp.columnandbar;

import com.vaadin.addon.charts.model.style.Color;

public class Drilldown {
    private String name;
    private String[] categories;
    private Number[] data;
    private Color color;

    public Drilldown() {

    }

    public Drilldown(String name) {
        this.name = name;
    }

    public Drilldown(String name, String[] categories, Number[] data,
            Color color) {
        super();
        this.name = name;
        this.categories = categories;
        this.data = data;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String... categories) {
        this.categories = categories;
    }

    public Number[] getData() {
        return data;
    }

    public void setData(Number... data) {
        this.data = data;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
