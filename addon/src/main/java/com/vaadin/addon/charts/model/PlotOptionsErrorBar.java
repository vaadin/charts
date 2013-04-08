package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;

public abstract class PlotOptionsErrorBar extends AbstractLinePlotOptions {

    @Override
    public ChartType getChartType() {
        // TODO type
        return null;
    }

    /**
     * @see #setColor(Color)
     */
    public Color getStemColor() {
        return stemColor;
    }

    /**
     * Sets the color of the stem, the vertical line extending from the box to
     * the whiskers. If null, the series color is used. Defaults to null.
     * 
     * @param stemColor
     *            the color of the stem
     */
    public void setStemColor(Color stemColor) {
        this.stemColor = stemColor;
    }

    /**
     * @see #setStemDashStyle(DashStyle)
     */
    public DashStyle getStemDashStyle() {
        return stemDashStyle;
    }

    /**
     * Sets the dash style of the stem, the vertical line extending from the box
     * to the whiskers. Defaults to Solid.
     * 
     * @param stemDashStyle
     *            the dash style for stem
     */
    public void setStemDashStyle(DashStyle stemDashStyle) {
        this.stemDashStyle = stemDashStyle;
    }

    /**
     * @see #setStemWidth(Number)
     * @return the stemWidth
     */
    public Number getStemWidth() {
        return stemWidth;
    }

    /**
     * Sets the width of the stem, the vertical line extending from the box to
     * the whiskers. If null, the width is inherited from the lineWidth option.
     * 
     * @param stemWidth
     *            the stemWidth to set
     */
    public void setStemWidth(Number stemWidth) {
        this.stemWidth = stemWidth;
    }

    /**
     * @see #setWhiskerColor(Color)
     * @return the whisker color
     */
    public Color getWhiskerColor() {
        return whiskerColor;
    }

    /**
     * Sets the color of the whiskers, the horizontal lines marking low and high
     * values. When null, the general series color is used. Defaults to null.
     * 
     * @param whiskerColor
     *            the whisker color to set
     */
    public void setWhiskerColor(Color whiskerColor) {
        this.whiskerColor = whiskerColor;
    }

    /**
     * @see #setWhiskerLength(Number)
     * @return the whiskerLength
     */
    public Number getWhiskerLength() {
        return whiskerLength;
    }

    /**
     * The length of the whiskers, the horizontal lines marking low and high
     * values. It can be a numerical pixel value, or a percentage value of the
     * box width. Set 0 to disable whiskers. Defaults to 50%.
     * 
     * @param whiskerLength
     *            the whisker length to set
     */
    public void setWhiskerLength(Number whiskerLength) {
        this.whiskerLength = whiskerLength;
    }

    /**
     * @see #setWhiskerWidth(Number)
     * 
     * @return the whisker width
     */
    public Number getWhiskerWidth() {
        return whiskerWidth;
    }

    /**
     * Sets the line width of the whiskers, the horizontal lines marking low and
     * high values. When null, the general lineWidth applies. 
     * 
     * Defaults to 2.
     * 
     * @param whiskerWidth
     *            the whisker width to set in pixels
     */
    public void setWhiskerWidth(Number whiskerWidth) {
        this.whiskerWidth = whiskerWidth;
    }

    private Color stemColor;

    private DashStyle stemDashStyle;

    private Number stemWidth;

    private Color whiskerColor;

    private Number whiskerLength;

    private Number whiskerWidth;

}