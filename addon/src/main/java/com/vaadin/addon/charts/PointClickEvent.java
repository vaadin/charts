package com.vaadin.addon.charts;

/**
 * Server side event class for click events on the chart's points
 */
public class PointClickEvent extends com.vaadin.ui.Component.Event {

    private final Double x;
    private final Double y;
    private final String seriesName;
    private final String category;

    /**
     * Construct a PointClickEvent
     * 
     * @param source
     * @param x
     * @param y
     * @param seriesName
     * @param category
     */
    public PointClickEvent(Chart source, double x, double y, String seriesName,
            String category) {
        super(source);
        this.x = x;
        this.y = y;
        this.seriesName = seriesName;
        this.category = category;
    }

    /**
     * x-value of the clicked point
     * 
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * y-value of the clicked point
     * 
     * @return
     */
    public double getY() {
        return y;
    }

    /**
     * Name of the series containing the clicked point
     * 
     * @return
     */
    public String getSeriesName() {
        return seriesName;
    }

    /**
     * Name of the category of the clicked point
     * 
     * @return
     */
    public String getCategory() {
        return category;
    }

}
