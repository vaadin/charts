package com.vaadin.addon.charts.model;

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
    public static final ChartType AREASPLINERANGE = new ChartType(
            "areasplinerange");
    public static final ChartType BOXPLOT = new ChartType("boxplot");
    public static final ChartType ERRORBAR = new ChartType("errorbar");
    public static final ChartType BUBBLE = new ChartType("bubble");
    public static final ChartType FUNNEL = new ChartType("funnel");
    public static final ChartType WATERFALL = new ChartType("waterfall");
    public static final ChartType PYRAMID = new ChartType("pyramid");
    public static final ChartType SOLIDGAUGE = new ChartType("solidgauge");
    public static final ChartType HEATMAP = new ChartType("heatmap");

    private final String type;

    /**
     * Constructs a new Chart type.
     * 
     * @param type
     *            the actual type string passed for client side
     */
    protected ChartType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
