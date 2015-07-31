package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2014 Vaadin Ltd
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

import com.vaadin.addon.charts.model.style.SolidColor;

public class PlotOptionsHeatMap extends AbstractCommonPlotOptions {

    private SolidColor borderColor;
    private Number borderWidth;
    private Number colsize;
    private DashStyle dashStyle;
    private SolidColor nullColor;
    private Number rowsize;
    private Boolean selected;
    private Boolean showCheckbox;
    private Boolean visible;
    private Boolean colorByPoint;
    private Integer turboThreshold;

    @Override
    public ChartType getChartType() {
        return ChartType.HEATMAP;
    }

    /**
     * @see #setBorderColor(SolidColor)
     * @return borderColor
     */
    public SolidColor getBorderColor() {
        return borderColor;
    }

    /**
     * The border color of the map areas. Defaults to silver.
     * 
     * @param borderColor
     */
    public void setBorderColor(SolidColor borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * @see #setBorderWidth(Number)
     * @return borderWidth
     */
    public Number getBorderWidth() {
        return borderWidth;
    }

    /**
     * The border width of each map area. Defaults to 1.
     * 
     * @param borderWidth
     */
    public void setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * @see #setColsize(Number)
     * @return colsize
     */
    public Number getColsize() {
        return colsize;
    }

    /**
     * The column size - how many X axis units each column in the heatmap should
     * span. Defaults to 1.
     * 
     * @param colsize
     */
    public void setColsize(Number colsize) {
        this.colsize = colsize;
    }

    /**
     * @see #setDashStyle(DashStyle)
     * @return dashStyle
     */
    public DashStyle getDashStyle() {
        return dashStyle;
    }

    /**
     * A {@link DashStyle} to use for the map area outline or the map line.
     * Defaults to {@link DashStyle#SOLID}.
     * 
     * @param dashStyle
     */
    public void setDashStyle(DashStyle dashStyle) {
        this.dashStyle = dashStyle;
    }

    /**
     * @see #setNullColor(SolidColor)
     * @return nullColor
     */
    public SolidColor getNullColor() {
        return nullColor;
    }

    /**
     * The color to apply to null points. Defaults to #F8F8F8
     * 
     * @param nullColor
     */
    public void setNullColor(SolidColor nullColor) {
        this.nullColor = nullColor;
    }

    /**
     * @see #setRowsize(Number)
     * @return rowsize
     */
    public Number getRowsize() {
        return rowsize;
    }

    /**
     * The row size - how many Y axis units each heatmap row should span.
     * Defaults to 1.
     * 
     * @param rowsize
     */
    public void setRowsize(Number rowsize) {
        this.rowsize = rowsize;
    }

    /**
     * @see #setSelected(Boolean)
     * @return selected
     */
    public Boolean getSelected() {
        return selected;
    }

    /**
     * Whether to select the series initially. If showCheckbox is true, the
     * checkbox next to the series name will be checked for a selected series.
     * Defaults to false.
     * 
     * @param selected
     */
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    /**
     * @see #setShowCheckbox(Boolean)
     * @return showCheckbox
     */
    public Boolean getShowCheckbox() {
        return showCheckbox;
    }

    /**
     * If true, and the series has a unique legend item, a checkbox is displayed
     * next to the legend item to allow selecting the series. The state of the
     * checkbox is determined by the selected option. Note that if a colorAxis
     * is defined, the color axis is represented in the legend, not the series.
     * Defaults to false.
     * 
     * @param showCheckbox
     */
    public void setShowCheckbox(Boolean showCheckbox) {
        this.showCheckbox = showCheckbox;
    }

    /**
     * @see #setVisible(Boolean)
     * @return visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * Set the initial visibility of the series. Defaults to true.
     * 
     * @param visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * @see PlotOptionsHeatMap#setColorByPoint(Boolean)
     * @return
     */
    public Boolean getColorByPoint() {
        return colorByPoint;
    }

    /**
     * When using automatic point colors, this option determines whether the
     * chart should receive one color per series or one color per point.
     * Defaults to false.
     * 
     * @param colorByPoint
     */
    public void setColorByPoint(Boolean colorByPoint) {
        this.colorByPoint = colorByPoint;
    }

    /**
     * @return the turbo threshold used for this chart type
     * @see #setTurboThreshold(Integer)
     */
    public Integer getTurboThreshold() {
        return turboThreshold;
    }

    /**
     * Sets the threshold (number of data points) after library will always try
     * to use optimized rendering. For optimized rendering to work, data points
     * can only contain numeric values - no special data item specific settings.
     * <p>
     * The default setting used by library is 1000
     * 
     * @param turboThreshold
     *            the number of data points after the optimized rendering is
     *            forced
     */
    public void setTurboThreshold(Integer turboThreshold) {
        this.turboThreshold = turboThreshold;
    }
}
