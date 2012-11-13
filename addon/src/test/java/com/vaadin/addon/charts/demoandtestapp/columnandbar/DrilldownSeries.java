package com.vaadin.addon.charts.demoandtestapp.columnandbar;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.style.Color;

/**
 * Works like normal DataSeries but stores also the drilldown data and contains
 * functionality for dill down and back to up
 */
public class DrilldownSeries extends DataSeries {
    private transient final Map<String, Drilldown> drilldowns = new HashMap<String, Drilldown>();
    private transient final Configuration configuration;
    private transient Number[] mainData = null;
    private transient String[] mainCategories;
    private transient String mainName;
    private transient Color mainColor;
    private transient boolean isDrilldown;

    /**
     * Constructs the DrilldownSeries with Chart's configuration
     * 
     * @param configuration
     */
    public DrilldownSeries(Configuration configuration) {
        super();
        this.configuration = configuration;
    }

    /**
     * Constructs the DrilldownSeries with Chart's configuration and numeric
     * data for main categories (that are fetched from the chart configuration)
     * 
     * @param configuration
     */
    public DrilldownSeries(Configuration configuration, Number... data) {
        super(configuration.getxAxis().getCategories(), data);
        mainCategories = configuration.getxAxis().getCategories();
        this.configuration = configuration;
    }

    /**
     * Adds a new Drilldown to be drilldown serie of the given categoryName
     * 
     * @param categoryName
     * @param numbers
     */
    public void addDrilldown(String categoryName, Drilldown drilldown) {
        drilldowns.put(categoryName, drilldown);
        extractColors();
    }

    /**
     * Extract series colors for series when all series are given
     */
    private void extractColors() {
        if (mainCategories.length == drilldowns.size()) {
            for (DataSeriesItem item : getData()) {
                Drilldown dd = drilldowns.get(item.getName());
                ((DataSeriesItem) item).setColor(dd.getColor());
            }
        }
    }

    /**
     * Drills down into given category
     * 
     * @param category
     */
    public void drillDown(String category) {
        if (mainData == null) {
            mainData = getNumericData().clone();
            mainCategories = configuration.getxAxis().getCategories().clone();
            mainName = getName();
            mainColor = getColor();
        }

        Drilldown dd = drilldowns.get(category);
        configuration.getxAxis().setCategories(dd.getCategories());
        setName(dd.getName());
        setData(dd.getData());
        setDrillDownColors(category);
        isDrilldown = true;
    }

    /**
     * Set colors of the current drilldown series
     * 
     * @param category
     */
    private void setDrillDownColors(String category) {
        Drilldown dd = drilldowns.get(category);
        for (DataSeriesItem item : getData()) {
            ((DataSeriesItem) item).setColor(dd.getColor());
        }
    }

    /**
     * Goes back to up
     */
    public void backToUp() {
        configuration.getxAxis().setCategories(mainCategories);
        setName(mainName);
        setData(mainCategories, mainData);
        configuration.getLegend().getStyle().setColor(mainColor);
        extractColors();

        isDrilldown = false;
    }

    /**
     * Are we drilled down now
     * 
     * @return
     */
    public boolean isDrilldown() {
        return isDrilldown;
    }
}
