package com.vaadin.addon.charts.model.style;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;

/**
 * Styles of Axis
 */
public class AxisStyle extends AbstractConfigurationObject {

    private TickIntervalStyle minorTickInterval;
    private Color lineColor;
    private Number lineWidth;
    private Number tickWidth;
    private Color tickColor;
    private Color gridLineColor;
    private Number gridLineWidth;
    private Color alternateGridColor;

    private StyleWrapper title = new StyleWrapper();
    private StyleWrapper subtitle = new StyleWrapper();
    private StyleWrapper labels = new StyleWrapper();

    public TickIntervalStyle getMinorTickInterval() {
        return minorTickInterval;
    }

    public void setMinorTickInterval(TickIntervalStyle minorTickInterval) {
        this.minorTickInterval = minorTickInterval;
    }

    /**
     * TODO: ???
     * 
     * @return
     */
    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public Number getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(Number lineWidth) {
        this.lineWidth = lineWidth;
    }

    public Number getTickWidth() {
        return tickWidth;
    }

    public void setTickWidth(Number tickWidth) {
        this.tickWidth = tickWidth;
    }

    public Color getTickColor() {
        return tickColor;
    }

    public void setTickColor(Color tickColor) {
        this.tickColor = tickColor;
    }

    /**
     * Get title style
     * 
     * @return Title style
     */
    public Style getTitle() {
        return title.getStyle();
    }

    /**
     * Set title style
     * 
     * @param style
     *            Title style
     */
    public void setTitle(Style style) {
        title.setStyle(style);
    }

    /**
     * Get subtitle style
     * 
     * @return Subtitle style
     */
    public Style getSubtitle() {
        return subtitle.getStyle();
    }

    /**
     * Set subtitle style
     * 
     * @param style
     *            Subtitle style
     */
    public void setSubtitle(Style style) {
        subtitle.setStyle(style);
    }

    /**
     * Get labels style
     * 
     * @return Labels style
     */
    public Style getLabels() {
        return labels.getStyle();
    }

    /**
     * Set labels style
     * 
     * @param style
     *            Labels style
     */
    public void setLabels(Style style) {
        labels.setStyle(style);
    }

    /**
     * Get grid line width
     * 
     * @return Grid line width or null if not defined
     */
    public Number getGridLineWidth() {
        return gridLineWidth;
    }

    /**
     * Set grid line width
     * 
     * @param gridLineWidth
     *            Grid line width
     */
    public void setGridLineWidth(Number gridLineWidth) {
        this.gridLineWidth = gridLineWidth;
    }

    /**
     * Get alternate grid color
     * 
     * @return Alternate grid color, null if not defined
     */
    public Color getAlternateGridColor() {
        return alternateGridColor;
    }

    /**
     * Set alternate grid color
     * 
     * @param alternateGridColor
     *            Alternate grid color
     */
    public void setAlternateGridColor(Color alternateGridColor) {
        this.alternateGridColor = alternateGridColor;
    }

    /**
     * Get color of grid lines
     * 
     * @return Color of grid lines, null if not defined
     */
    public Color getGridLineColor() {
        return gridLineColor;
    }

    /**
     * Set color of grid lines
     * 
     * @param gridLineColor
     *            Color of grid lines
     */
    public void setGridLineColor(Color gridLineColor) {
        this.gridLineColor = gridLineColor;
    }

}
