package com.vaadin.addon.charts.model;

import java.util.List;

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
 * Plot options that are specific for {@link ChartType#TREEMAP} charts
 * 
 * @see AbstractPlotOptions
 * @see PlotOptionsColumn
 */
public class PlotOptionsTreeMap extends AbstractCommonPlotOptions {

    private Boolean allowDrillToNode;
    private Boolean alternateStartingDirection;
    private TreeMapLayoutAlgorithm layoutAlgorithm;
    private TreeMapLayoutStartingDirection layoutStartingDirection;
    private Boolean levelIsConstant;
    private List<TreeMapLevel> levels;
    private Boolean colorByPoint;

    @Override
    public ChartType getChartType() {
        return ChartType.TREEMAP;
    }

    /**
     * @see #setAllowDrillToNode(Boolean)
     * @return
     */
    public Boolean isAllowDrillToNode() {
        return allowDrillToNode;
    }

    /**
     * When enabled the user can click on a point which is a parent and zoom in
     * on its children. Defaults to false.
     * 
     * @param allowDrillToNode
     */
    public void setAllowDrillToNode(Boolean allowDrillToNode) {
        this.allowDrillToNode = allowDrillToNode;
    }

    /**
     * @see #setAlternateStartingDirection(Boolean)
     * @return
     */
    public Boolean isAlternateStartingDirection() {
        return alternateStartingDirection;
    }

    /**
     * Enabling this option will make the treemap alternate the drawing
     * direction between vertical and horizontal. The next levels starting
     * direction will always be the opposite of the previous. Defaults to false.
     * 
     * @param alternateStartingDirection
     */
    public void setAlternateStartingDirection(Boolean alternateStartingDirection) {
        this.alternateStartingDirection = alternateStartingDirection;
    }

    /**
     * @see #setLayoutAlgorithm(TreeMapLayoutAlgorithm)
     * @return
     */
    public TreeMapLayoutAlgorithm getLayoutAlgorithm() {
        return layoutAlgorithm;
    }

    /**
     * This option decides which algorithm is used for setting position and
     * dimensions of the points. Can be one of
     * <ul>
     * <li>{@link TreeMapLayoutAlgorithm#SLICEANDDICE}</li>
     * <li>{@link TreeMapLayoutAlgorithm#STRIPES}</li>
     * <li>{@link TreeMapLayoutAlgorithm#SQUARIFIED}</li>
     * <li>{@link TreeMapLayoutAlgorithm#STRIP}</li>
     * </ul>
     *
     * Defaults to {@link TreeMapLayoutAlgorithm#SLICEANDDICE}.
     * 
     * @param layoutAlgorithm
     */
    public void setLayoutAlgorithm(TreeMapLayoutAlgorithm layoutAlgorithm) {
        this.layoutAlgorithm = layoutAlgorithm;
    }

    /**
     * @see #setLayoutStartingDirection(TreeMapLayoutStartingDirection)
     * @return
     */
    public TreeMapLayoutStartingDirection getLayoutStartingDirection() {
        return layoutStartingDirection;
    }

    /**
     * Defines which direction the layout algorithm will start drawing. Possible
     * values are {@link TreeMapLayoutStartingDirection#HORIZONTAL} and
     * {@link TreeMapLayoutStartingDirection#VERTICAL}. Defaults to
     * {@link TreeMapLayoutStartingDirection#VERTICAL}.
     * 
     * @param layoutStartingDirection
     */
    public void setLayoutStartingDirection(
            TreeMapLayoutStartingDirection layoutStartingDirection) {
        this.layoutStartingDirection = layoutStartingDirection;
    }

    /**
     * @see #setLevelIsConstant(Boolean)
     * @return
     */
    public Boolean isLevelIsConstant() {
        return levelIsConstant;
    }

    /**
     * Used together with the #setLevels and #setAllowDrillToNode options. When
     * set to false the first level visible when drilling is considered to be
     * level one. Otherwise the level will be the same as the tree structure.
     * Defaults to true.
     * 
     * @param levelIsConstant
     */
    public void setLevelIsConstant(Boolean levelIsConstant) {
        this.levelIsConstant = levelIsConstant;
    }

    /**
     * @see #setLevels(List)
     * @return
     */
    public List<TreeMapLevel> getLevels() {
        return levels;
    }

    /**
     * Set options on specific levels. Takes precedence over series options, but
     * not point options.
     *
     * @param levels
     */
    public void setLevels(List<TreeMapLevel> levels) {
        this.levels = levels;
    }

    /**
     * @see PlotOptionsTreeMap#setColorByPoint(Boolean)
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

}
