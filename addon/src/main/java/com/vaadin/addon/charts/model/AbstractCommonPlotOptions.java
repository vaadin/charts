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
 * This abstract class has some options that are relevant to many, but not all,
 * chart types.
 * 
 */
public abstract class AbstractCommonPlotOptions extends AbstractPlotOptions {

    private Boolean shadow;
    private Boolean allowPointSelect;
    private PointPlacement pointPlacement;

    /**
     * In a column chart, when pointPlacement is {@link PointPlacement#ON}, the
     * point will not create any padding of the X-axis.
     * 
     * In a polar column chart {@link PointPlacement#ON} means that the first
     * column points directly north.
     * 
     * If pointPlacement is {@link PointPlacement#BETWEEN}, the columns will be
     * laid out between ticks. This is useful for example for visualizing an
     * amount between two points in time or in a certain sector of a polar
     * chart.
     * 
     * Defaults to null in Cartesian charts, {@link PointPlacement#BETWEEN} in
     * polar charts.
     * 
     * @param pointPlacement
     */
    public void setPointPlacement(PointPlacement pointPlacement) {
        this.pointPlacement = pointPlacement;
    }

    /**
     * @see #setPointPlacement(PointPlacement)
     * @return the point placement or null if none.
     */
    public PointPlacement getPointPlacement() {
        return pointPlacement;
    }

    /**
     * @see #setAllowPointSelect(Boolean)
     */
    public boolean isAllowPointSelect() {
        return allowPointSelect == null ? false : allowPointSelect;
    }

    /**
     * Sets whether to allow points in this series to be selected by clicking on
     * the markers, bars or pie slices. Defaults to false.
     * 
     * @param allowPointSelect
     */
    public void setAllowPointSelect(Boolean allowPointSelect) {
        this.allowPointSelect = allowPointSelect;
    }

    /**
     * Sets whether to apply a drop shadow below the graph line. Defaults to
     * true.
     */
    public void setShadow(Boolean shadows) {
        shadow = shadows;
    }

    /**
     * @see #setShadow(boolean)
     */
    public boolean isShadow() {
        return shadow == null ? true : shadow;
    }

}
