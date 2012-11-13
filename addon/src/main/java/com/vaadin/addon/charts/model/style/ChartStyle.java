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
     * Get background color of Chart
     * 
     * @return Background color of component, null if not defined
     * @see getPlotBackgroundColor
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Set background color of Chart
     * 
     * @param backgroundColor
     *            Background color
     * @see setPlotBackgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Get background color of plot inside component
     * 
     * @return Background color of plot, null if not defined
     * @see getBackgroundColor
     */
    public Color getPlotBackgroundColor() {
        return plotBackgroundColor;
    }

    /**
     * Set background color of plot
     * 
     * @param plotBackgroundColor
     *            Background color of plot
     * @see setBackgroundColor
     */
    public void setPlotBackgroundColor(Color plotBackgroundColor) {
        this.plotBackgroundColor = plotBackgroundColor;
    }

    /**
     * Get background image of plot
     * 
     * @return Background image or plot, null if not defined
     */
    public String getPlotBackgroundImage() {
        return plotBackgroundImage;
    }

    /**
     * Set background image of plot
     * 
     * @param plotBackgroundImage
     *            Background image (url) of plot
     */
    public void setPlotBackgroundImage(String plotBackgroundImage) {
        this.plotBackgroundImage = plotBackgroundImage;
    }

    /**
     * 
     * @return State of shadow, null if not defined
     */
    public Boolean isPlotShadow() {
        return plotShadow;
    }

    public void setPlotShadow(Boolean plotShadow) {
        this.plotShadow = plotShadow;
    }

    /**
     * Get width of border around plot
     * 
     * @return Border width, null if not defined
     */
    public Number getPlotBorderWidth() {
        return plotBorderWidth;
    }

    /**
     * Set width of border around plot
     * 
     * @param plotBorderWidth
     *            Width of border
     */
    public void setPlotBorderWidth(Number plotBorderWidth) {
        this.plotBorderWidth = plotBorderWidth;
    }

    /**
     * TODO: ???
     * 
     * @return
     */
    public String getClassName() {
        return className;
    }

    /**
     * TODO: ???
     * 
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Get border width around the Chart
     * 
     * @return Border width, null if not defined
     */
    public Number getBorderWidth() {
        return borderWidth;
    }

    /**
     * Set border width around the Chart
     * 
     * @param borderWidth
     *            Border width
     */
    public void setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * Get radius of Chart border
     * 
     * @return Radius of border, null if not defined
     */
    public Number getBorderRadius() {
        return borderRadius;
    }

    /**
     * Set radius of Chart border
     * 
     * @param borderRadius
     *            Radius or border
     */
    public void setBorderRadius(Number borderRadius) {
        this.borderRadius = borderRadius;
    }

    /**
     * Get plot border color
     * 
     * @return Plot border color, null if not defined
     */
    public Color getPlotBorderColor() {
        return plotBorderColor;
    }

    public void setPlotBorderColor(Color plotBorderColor) {
        this.plotBorderColor = plotBorderColor;
    }

    /**
     * Gets various style defaults used. This can be used to for example define
     * default font family.
     * 
     * @return
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Sets the default style for e.g. fonts used in graphs.
     * 
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

}
