package com.vaadin.addon.charts.model;

/**
 * The default series type for the chart. Can be one of LINE, SPLINE, AREA,
 * AREASPLINE, COLUMN, BAR, PIE, SCATTER, AREARANGE, AREASPLINERANGE, and
 * COLUMNRANGE. Defaults to LINE.
 * <p>
 * Own custom extensions can be made by extending this class.
 */
public class ChartType implements ChartEnum {
    
    public static final ChartType AREA = new ChartType("area");
    public static final ChartType LINE = new ChartType("line");
    public static final ChartType SPLINE = new ChartType("spline");
    public static final ChartType AREASPLINE = new ChartType("areaspline");
    public static final ChartType COLUMN = new ChartType("column");
    public static final ChartType BAR = new ChartType("bar");
    public static final ChartType PIE = new ChartType("pie");
    public static final ChartType SCATTER = new ChartType("scatter");
    public static final ChartType GAUGE = new ChartType("gauge");
    public static final ChartType AREARANGE = new ChartType("arearange");
    public static final ChartType COLUMNRANGE = new ChartType("columnrange");
    public static final ChartType AREASPLINERANGE = new ChartType("areasplinerange");
    
    private final String type;

    /**
     * Constructs a new Chart type.
     * 
     * @param type the actual type string passed for client side
     */
    protected ChartType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
