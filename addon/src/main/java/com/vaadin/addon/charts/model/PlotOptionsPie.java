package com.vaadin.addon.charts.model;

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
