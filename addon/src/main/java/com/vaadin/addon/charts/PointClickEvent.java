package com.vaadin.addon.charts;

public class PointClickEvent extends com.vaadin.ui.Component.Event {

    private final Double x;
    private final Double y;
    private final String seriesName;
    private final String category;

    public PointClickEvent(Chart source, double x, double y, String seriesName,
            String category) {
        super(source);
        this.x = x;
        this.y = y;
        this.seriesName = seriesName;
        this.category = category;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Name of the series containing the point
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
