package com.vaadin.addon.charts.model.style;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;

/**
 * Styles of chart
 */
public class ChartStyle extends AbstractConfigurationObject {
    private Color backgroundColor;
    private Color plotBackgroundColor;
    private String plotBackgroundImage;
    private Boolean plotShadow;
    private Number plotBorderWidth;
    private Color plotBorderColor;
    private String className;
    private Number borderWidth;
    private Number borderRadius;
    private Style style;

    /**
     * @see #setBackgroundColor(Color)
     * 
     * @return Background color of component, null if not defined
     * @see getPlotBackgroundColor
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * The background color or gradient for the outer chart area. Defaults to
     * "#FFFFFF".
     * 
     * @param backgroundColor
     *            Background color
     * @see setPlotBackgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @see #setPlotBackgroundColor(Color)
     * @return Background color of plot, null if not defined
     * @see getBackgroundColor
     */
    public Color getPlotBackgroundColor() {
        return plotBackgroundColor;
    }

    /**
     * The background color or gradient for the plot area. Defaults to null.
     * 
     * @param plotBackgroundColor
     *            Background color of plot
     * @see setBackgroundColor
     */
    public void setPlotBackgroundColor(Color plotBackgroundColor) {
        this.plotBackgroundColor = plotBackgroundColor;
    }

    /**
     * @see #setPlotBackgroundImage(String)
     * 
     * @return Background image or plot, null if not defined
     */
    public String getPlotBackgroundImage() {
        return plotBackgroundImage;
    }

    /**
     * The URL for an image to use as the plot background. To set an image as
     * the background for the entire chart, set a CSS background image to the
     * container element. Defaults to null.
     * 
     * @param plotBackgroundImage
     *            Background image (url) of plot
     */
    public void setPlotBackgroundImage(String plotBackgroundImage) {
        this.plotBackgroundImage = plotBackgroundImage;
    }

    /**
     * @see #setPlotShadow(Boolean)
     * @return State of shadow, null if not defined
     */
    public Boolean isPlotShadow() {
        return plotShadow;
    }

    /**
     * Whether to apply a drop shadow to the plot area. Requires that
     * plotBackgroundColor be set.
     * 
     * @param plotShadow
     */
    public void setPlotShadow(Boolean plotShadow) {
        this.plotShadow = plotShadow;
    }

    /**
     * @see #setPlotBorderWidth(Number)
     * 
     * @return Border width, null if not defined
     */
    public Number getPlotBorderWidth() {
        return plotBorderWidth;
    }

    /**
     * The pixel width of the plot area border. Defaults to 0.
     * 
     * @param plotBorderWidth
     *            Width of border
     */
    public void setPlotBorderWidth(Number plotBorderWidth) {
        this.plotBorderWidth = plotBorderWidth;
    }

    /**
     * @see #setClassName(String)
     * 
     * @return
     */
    public String getClassName() {
        return className;
    }

    /**
     * A CSS class name to apply to the charts container div, allowing unique
     * CSS styling for each chart. Defaults to "".
     * 
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @see #setBorderWidth(Number)
     * 
     * @return Border width, null if not defined
     */
    public Number getBorderWidth() {
        return borderWidth;
    }

    /**
     * The pixel width of the outer chart border. The border is painted using
     * vector graphic techniques to allow rounded corners. Defaults to 0.
     * 
     * @param borderWidth
     *            Border width
     */
    public void setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * @see #setBorderRadius(Number)
     * 
     * @return Radius of border, null if not defined
     */
    public Number getBorderRadius() {
        return borderRadius;
    }

    /**
     * Set The corner radius of the outer chart border. Defaults to 5.
     * 
     * @param borderRadius
     *            Radius or border
     */
    public void setBorderRadius(Number borderRadius) {
        this.borderRadius = borderRadius;
    }

    /**
     * @see #setPlotBorderColor(Color)
     * @return Plot border color, null if not defined
     */
    public Color getPlotBorderColor() {
        return plotBorderColor;
    }

    /**
     * The color of the outer chart border. The border is painted using vector
     * graphic techniques to allow rounded corners. Defaults to "#4572A7".
     * 
     * @param plotBorderColor
     */
    public void setPlotBorderColor(Color plotBorderColor) {
        this.plotBorderColor = plotBorderColor;
    }

    /**
     * Gets various style defaults used. This can be used to for example define
     * default font family.
     * 
     * @see #setStyle(Style)
     * @return
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Additional CSS styles to apply inline to the container div. Note that
     * since the default font styles are applied in the renderer, it is ignorant
     * of the individual chart options and must be set globally. Defaults to:
     * 
     * style: { fontFamily: '"Lucida Grande", "Lucida Sans Unicode", Verdana,
     * Arial, Helvetica, sans-serif', // default font fontSize: '12px' }
     * 
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

}
