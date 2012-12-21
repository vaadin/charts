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
 * Plot options that are specific for ChartType.PIE charts
 */
public class PlotOptionsPie extends AbstractPlotOptions {
    
    private Object size;
    private Object[] center;
    private Object innerSize;
    
    @Override
    public ChartType getChartType() {
        return ChartType.PIE;
    }

    /**
     * @see #setInnerSize(Object)
     * 
     * @return
     */
    public Object getInnerSize() {
        return innerSize;
    }

    /**
     * The size of the inner diameter for the pie. A size greater than 0 renders
     * a donut chart. Can be a percentage or pixel value. Percentages are
     * relative to the size of the plot area. Pixel values are given as
     * integers. Defaults to 0. <br />
     * <br />
     * <b>This relevant only for ChartType.PIE</b>
     * 
     * @param innerSize
     */
    public void setInnerSize(Object innerSize) {
        this.innerSize = innerSize;
    }
    
    /**
     * @see #setSize(Object)
     * 
     * @return
     */
    public Object getSize() {
        return size;
    }

    /**
     * The diameter of the pie relative to the plot area. Can be a percentage (String) or
     * pixel value (Number). Pixel values are given as integers. Defaults to "75%".
     * 
     * @param size
     */
    public void setSize(Object size) {
        this.size = size;
    }
    
    /**
     * @see #setCenter(Object, Object)
     * 
     * @return
     */
    public Object[] getCenter() {
        return center;
    }

    /**
     * The center of the pie chart relative to the plot area. Can be percentages
     * or pixel values. Defaults to "50%", "50%". <br />
     * <br />
     * <b>This relevant only for ChartType.PIE</b>
     * 
     * @param left
     * @param top
     */
    public void setCenter(Object left, Object top) {
        Object[] array = null;
        if (left != null && top != null) {
            array = new Object[] { left, top };
        }
        center = array;
    }

}
