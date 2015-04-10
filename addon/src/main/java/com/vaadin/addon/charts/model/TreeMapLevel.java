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

import com.vaadin.addon.charts.model.style.SolidColor;

/**
 * Settings for specific levels in a {@link ChartType#TREEMAP}.
 */
public class TreeMapLevel extends AbstractConfigurationObject {

    private SolidColor borderColor;
    private DashStyle borderDashStyle;
    private Number borderWidth;
    private SolidColor color;
    private Labels dataLabels;
    private TreeMapLayoutAlgorithm layoutAlgorithm;
    private TreeMapLayoutStartingDirection layoutStartingDirection;
    private Number level;

    /**
     * @return used border color or null
     * @see #setBorderColor(SolidColor)
     */
    public SolidColor getBorderColor() {
        return borderColor;
    }

    /**
     * Set borderColor on all points which lies on the same level
     *
     * @param borderColor
     *            the {@link SolidColor} to use for the border
     */
    public void setBorderColor(SolidColor borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * @return the used dash style or null
     * @see #setBorderDashStyle(DashStyle)
     */
    public DashStyle getBorderDashStyle() {
        return borderDashStyle;
    }

    /**
     * Set the dash style of the border of all the points which lie on the
     * level.
     *
     * @param borderDashStyle
     *            the {@link DashStyle} to use for the border
     */
    public void setBorderDashStyle(DashStyle borderDashStyle) {
        this.borderDashStyle = borderDashStyle;
    }

    /**
     * @return the border width used, or null
     * @see #setBorderWidth(Number)
     */
    public Number getBorderWidth() {
        return borderWidth;
    }

    /**
     * Set the border width on all points which lie on the same level
     *
     * @param borderWidth
     *            the width of the border
     */
    public void setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * @return the {@link SolidColor} used, or null
     * @see #setColor(SolidColor)
     */
    public SolidColor getColor() {
        return color;
    }

    /**
     * Set a color on all points which lie on the same level
     *
     * @param color
     *            the {@link SolidColor} to use
     */
    public void setColor(SolidColor color) {
        this.color = color;
    }

    /**
     * @return the data label settings, or null
     * @see #setDataLabels(Labels)
     */
    public Labels getDataLabels() {
        return dataLabels;
    }

    /**
     * Set the options of dataLabels on each point which lies on the level.
     *
     * @param dataLabels
     *            the {@link Labels} to use
     */
    public void setDataLabels(Labels dataLabels) {
        this.dataLabels = dataLabels;
    }

    /**
     * @return the {@link TreeMapLayoutAlgorithm} used, or null
     * @see #setLayoutAlgorithm(TreeMapLayoutAlgorithm)
     */
    public TreeMapLayoutAlgorithm getLayoutAlgorithm() {
        return layoutAlgorithm;
    }

    /**
     * Set the layout algorithm option on a specific level
     *
     * @param layoutAlgorithm
     *            the {@link TreeMapLayoutAlgorithm} to use
     */
    public void setLayoutAlgorithm(TreeMapLayoutAlgorithm layoutAlgorithm) {
        this.layoutAlgorithm = layoutAlgorithm;
    }

    /**
     * @return the {@link TreeMapLayoutStartingDirection} used, or null
     * @see #setLayoutStartingDirection(TreeMapLayoutStartingDirection)
     */
    public TreeMapLayoutStartingDirection getLayoutStartingDirection() {
        return layoutStartingDirection;
    }

    /**
     * Set the layout starting direction on a specific level.
     *
     * @param layoutStartingDirection
     *            the {@link TreeMapLayoutStartingDirection} to use.
     */
    public void setLayoutStartingDirection(
            TreeMapLayoutStartingDirection layoutStartingDirection) {
        this.layoutStartingDirection = layoutStartingDirection;
    }

    /**
     * @return the level that is affected
     * @see #setLevel(Number)
     */
    public Number getLevel() {
        return level;
    }

    /**
     * Decides which level takes effect from the options set in this object.
     *
     * @param level
     *            the affected level
     */
    public void setLevel(Number level) {
        this.level = level;
    }
}
