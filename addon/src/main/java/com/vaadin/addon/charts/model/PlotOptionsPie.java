package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

/**
 * Plot options that are specific for {@link ChartType#PIE} charts
 * 
 * @see AbstractPlotOptions
 */
public class PlotOptionsPie extends AbstractCommonPlotOptions {

    private Object size;
    private Object[] center;
    private Object innerSize;
    private Marker marker;

    /**
     * @see #setMarker(Marker)
     */
    public Marker getMarker() {
        return marker;
    }

    /**
     * Sets the marker used for the plot point items (points/bars/columns)
     * 
     * @param marker
     */
    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    @Override
    public ChartType getChartType() {
        return ChartType.PIE;
    }

    /**
     * @see #setInnerSize(Object)
     * @return The size of the inner diameter of the pie.
     */
    public Object getInnerSize() {
        return innerSize;
    }

    /**
     * Sets the size of the inner diameter of the pie. A size greater than 0
     * renders a donut chart. Can be a percentage or pixel value. Percentages
     * are relative to the size of the plot area. Pixel values are given as
     * integers. Defaults to 0.
     * 
     * <em>Note</em>: This relevant only for {@link ChartType#PIE}
     * 
     * @param innerSize
     *            The inner size in percent (String)
     */
    public void setInnerSize(String innerSize) {
        this.innerSize = innerSize;
    }

    /**
     * Sets the size of the inner diameter of the pie. A size greater than 0
     * renders a donut chart. Can be a percentage or pixel value. Percentages
     * are relative to the size of the plot area. Pixel values are given as
     * integers. Defaults to 0.
     * 
     * <em>Note</em>: This relevant only for {@link ChartType#PIE}
     * 
     * @param innerSize
     *            The inner size in pixels (Number)
     */
    public void setInnerSize(Number innerSize) {
        this.innerSize = innerSize;
    }

    /**
     * @see #setSize(Object)
     * @return The diameter of the pie.
     */
    public Object getSize() {
        return size;
    }

    /**
     * Sets the diameter of the pie relative to the plot area. Can be a
     * percentage (String) or pixel value (Number). Pixel values are given as
     * integers. Defaults to "75%".
     * 
     * @param size
     *            The size in percent (String)
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Sets the diameter of the pie relative to the plot area. Can be a
     * percentage (String) or pixel value (Number). Pixel values are given as
     * integers. Defaults to "75%".
     * 
     * @param size
     *            The size in pixels (Number)
     */
    public void setSize(Number size) {
        this.size = size;
    }

    /**
     * @see #setCenter(Object, Object)
     */
    public Object[] getCenter() {
        return center;
    }

    /**
     * Sets the center of the pie chart relative to the plot area. Can be
     * percentages or pixel values. Defaults to "50%", "50%".
     * 
     * <em>Note</em>: This relevant only for {@link ChartType#PIE}
     * 
     * @param left
     *            The left offset in percentages (String)
     * @param top
     *            The top offset in percentages (String)
     */
    public void setCenter(String left, String top) {
        Object[] array = null;
        if (left != null && top != null) {
            array = new Object[] { left, top };
        }
        center = array;
    }

    /**
     * Sets the center of the pie chart relative to the plot area. Can be
     * percentages or pixel values. Defaults to "50%", "50%".
     * 
     * <em>Note</em>: This relevant only for {@link ChartType#PIE}
     * 
     * @param left
     *            The left offset in pixels (Number)
     * @param top
     *            The top offset in pixels (Number)
     */
    public void setCenter(Number left, Number top) {
        Object[] array = null;
        if (left != null && top != null) {
            array = new Object[] { left, top };
        }
        center = array;
    }

}
