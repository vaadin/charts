package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.Style;

/**
 * Chart model class
 */
public class ChartModel extends AbstractConfigurationObject {
    private ChartType type;
    private Number spacingTop;
    private Number spacingLeft;
    private Number spacingBottom;
    private Number spacingRight;
    private Boolean inverted;
    private Color plotBackgroundColor;
    private String plotBorderColor;
    private Number plotBorderWidth;
    private Boolean plotShadow;
    private ZoomType zoomType;
    private Style style;
    private Boolean reflow;
    private Number borderWidth;
    private Color backgroundColor;
    private Number[] margin;
    private Number marginBottom;
    private Number marginLeft;
    private Number marginRight;
    private Number marginTop;
    private Boolean animation;
    private String plotBackgroundImage;
    private Boolean alignTicks;
    private Boolean polar;

    /**
     * Default constructor
     */
    public ChartModel() {
    }

    /**
     * Create new chart with given type
     * 
     * @param type
     *            Type of chart
     */
    public ChartModel(ChartType type) {
        this.type = type;
    }

    /**
     * The default series type for the chart. Can be one of line, spline, area,
     * areaspline, column, bar, pie and scatter. Defaults to "line".
     * 
     * @return Type of chart or null if not defined
     */
    public ChartType getType() {
        return type;
    }

    /**
     * @see #getType()
     * @param type
     */
    public void setType(ChartType type) {
        this.type = type;
    }

    /**
     * The space between the top edge of the chart and the content (plot area,
     * axis title and labels, title, subtitle or legend in top position).
     * 
     * @return Spacing top or null if not defined
     */
    public Number getSpacingTop() {
        return spacingTop;
    }

    /**
     * @see #getSpacingTop()
     * @param spacingTop
     */
    public void setSpacingTop(Number spacingTop) {
        this.spacingTop = spacingTop;
    }

    /**
     * The space between the left edge of the chart and the content (plot area,
     * axis title and labels, title, subtitle or legend in top position).
     * 
     * @return Spacing left or null if not defined
     */
    public Number getSpacingLeft() {
        return spacingLeft;
    }

    /**
     * @see #getSpacingLeft()
     * @param spacingLeft
     */
    public void setSpacingLeft(Number spacingLeft) {
        this.spacingLeft = spacingLeft;
    }

    /**
     * The space between the bottom edge of the chart and the content (plot
     * area, axis title and labels, title, subtitle or legend in top position).
     * 
     * @return Spacing bottom or null if not defined
     */
    public Number getSpacingBottom() {
        return spacingBottom;
    }

    /**
     * @see #getSpacingBottom()
     * @param spacingBottom
     */
    public void setSpacingBottom(Number spacingBottom) {
        this.spacingBottom = spacingBottom;
    }

    /**
     * The space between the right edge of the chart and the content (plot area,
     * axis title and labels, title, subtitle or legend in top position).
     * 
     * @return Spacing right or null if not defined
     */
    public Number getSpacingRight() {
        return spacingRight;
    }

    /**
     * @see #getSpacingRight()
     * @param spacingRight
     */
    public void setSpacingRight(Number spacingRight) {
        this.spacingRight = spacingRight;
    }

    /**
     * Whether to invert the axes so that the x axis is vertical and y axis is
     * horizontal. When true, the x axis is reversed by default. If a bar series
     * is present in the chart, it will be inverted automatically. Defaults to
     * false.
     * 
     * @return Inverted state or null if not defined
     */
    public boolean isInverted() {
    	return inverted == null ? false : inverted;
    }

    /**
     * @see #isInverted()
     * @param inverted
     */
    public void setInverted(Boolean inverted) {
        this.inverted = inverted;
    }

    /**
     * Decides in what dimensions the user can zoom by dragging the mouse. Can
     * be one of X, Y or XY. Defaults to null.
     * 
     * @param zoomType
     */
    public void setZoomType(ZoomType zoomType) {
        this.zoomType = zoomType;
    }

    /**
     * @see #getZoomType()
     * @return
     */
    public ZoomType getZoomType() {
        return zoomType;
    }

    /**
     * The background color or gradient for the plot area. Defaults to null.
     * 
     * @return Background color or null if not defined
     */
    public Color getPlotBackgroundColor() {
        return plotBackgroundColor;
    }

    /**
     * @see #getPlotBackgroundColor()
     * @param plotBackgroundColor
     */
    public void setPlotBackgroundColor(Color plotBackgroundColor) {
        this.plotBackgroundColor = plotBackgroundColor;
    }

    /**
     * The color of the inner chart or plot area border. Defaults to "#C0C0C0".
     * 
     * @return Border color or null if not defined
     */
    public String getPlotBorderColor() {
        return plotBorderColor;
    }

    /**
     * @see #getPlotBorderColor()
     * @param plotBorderColor
     */
    public void setPlotBorderColor(String plotBorderColor) {
        this.plotBorderColor = plotBorderColor;
    }

    /**
     * The pixel width of the plot area border. Defaults to 0.
     * 
     * @return Border width or null if not defined
     */
    public Number getPlotBorderWidth() {
        return plotBorderWidth;
    }

    /**
     * @see #getPlotBorderWidth()
     * @param plotBorderWidth
     */
    public void setPlotBorderWidth(Number plotBorderWidth) {
        this.plotBorderWidth = plotBorderWidth;
    }

    /**
     * @see #setPlotShadow(Number)
     * @return
     */
    public boolean isPlotShadow() {
    	return plotShadow == null ? false : plotShadow;
    }

    /**
     * Whether to apply a drop shadow to the plot area. Requires that
     * plotBackgroundColor be set. Since 2.3 the shadow can be an object
     * configuration containing color, offsetX, offsetY, opacity and width.
     * Defaults to false.
     * 
     * @param plotShadow
     */
    public void setPlotShadow(Boolean plotShadow) {
        this.plotShadow = plotShadow;
    }

    /**
     * Set all margin parameters like {@link #setMarginTop(Number)} in same call
     * 
     * @param margins
     */
    public void setMargin(Number[] margins) {
        if (margins == null || margins.length == 4) {
            margin = margins;
        } else if (margins.length == 1) {
            setMargin(margins[0]);
        } else if (margin.length == 2) {
            setMargin(margins[0], margins[1]);
        } else {
            throw new IllegalArgumentException("Illegal margins");
        }
    }

    /**
     * Sets all margins to same value
     * 
     * @param size
     */
    public void setMargin(Number size) {
        setMargin(size, size, size, size);
    }

    /**
     * Sets horizontal margin for top and right margin and vertical for bottom
     * and left
     * 
     * @param horizontal
     * @param vertical
     */
    public void setMargin(Number horizontal, Number vertical) {
        setMargin(vertical, horizontal, vertical, horizontal);
    }

    /**
     * Set all margin parameters like {@link #setMarginTop(Number)} in same call
     * 
     * @param margins
     */
    public void setMargin(Number left, Number top, Number bottom, Number right) {
        margin = new Number[] { left, top, bottom, right };
    }

    /**
     * @see #setMargin(Number[])
     * @return
     */
    public Number[] getMargin() {
        return margin;
    }

    /**
     * Whether to reflow the chart to fit the width of the container div on
     * resizing the window. Defaults to true.
     * 
     * @param reflow
     */
    public void setReflow(Boolean reflow) {
        this.reflow = reflow;
    }

    /**
     * @see #setReflow(Boolean)
     * @return
     */
    public boolean isReflow() {
    	return reflow == null ? true : reflow;
    }

    /**
     * The pixel width of the outer chart border. The border is painted using
     * vector graphic techniques to allow rounded corners. Defaults to 0.
     * 
     * @param borderWidth
     */
    public void setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * @see #setBorderWidth(Number)
     * @return
     */
    public Number getBorderWidth() {
        return borderWidth;
    }

    /**
     * The background color or gradient for the outer chart area. Defaults to
     * "#FFFFFF".
     * 
     * @param backgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @see #setBackgroundColor(Color)
     * @return
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * The margin between the left outer edge of the chart and the plot area.
     * Use this to set a fixed pixel value for the margin as opposed to the
     * default dynamic margin. See also spacingLeft. Defaults to 80.
     * 
     * @param marginLeft
     */
    public void setMarginLeft(Number marginLeft) {
        this.marginLeft = marginLeft;
    }

    /**
     * @see #setMarginLeft(Number)
     * @return
     */
    public Number getMarginLeft() {
        return marginLeft;
    }

    /**
     * The margin between the right outer edge of the chart and the plot area.
     * Use this to set a fixed pixel value for the margin as opposed to the
     * default dynamic margin. See also spacingRight. Defaults to 50.
     * 
     * @param marginRight
     */
    public void setMarginRight(Number marginRight) {
        this.marginRight = marginRight;
    }

    /**
     * @see #setMarginRight(Number)
     * @return
     */
    public Number getMarginRight() {
        return marginRight;
    }

    /**
     * @see #setStyle(Style)
     * @return
     */
    public Style getStyle() {
        if (style == null) {
            style = new Style();
        }
        return style;
    }

    /**
     * Additional CSS styles to apply inline to the container div. Note that
     * since the default font styles are applied in the renderer, it is ignorant
     * of the individual chart options and must be set globally. Defaults to: <br>
     * style: { fontFamily: '"Lucida Grande", "Lucida Sans Unicode", Verdana,
     * Arial, Helvetica, sans-serif', // default font fontSize: '12px' }
     * 
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * The margin between the bottom outer edge of the chart and the plot area.
     * Use this to set a fixed pixel value for the margin as opposed to the
     * default dynamic margin. See also spacingBottom. Defaults to 70.
     * 
     * @param marginBottom
     */
    public void setMarginBottom(Number marginBottom) {
        this.marginBottom = marginBottom;
    }

    /**
     * @see #setMarginBottom(Number)
     * @return
     */
    public Number getMarginBottom() {
        return marginBottom;
    }

    /**
     * The margin between the top outer edge of the chart and the plot area. Use
     * this to set a fixed pixel value for the margin as opposed to the default
     * dynamic margin. See also spacingTop. Defaults to null.
     * 
     * @param marginTop
     */
    public void setMarginTop(Number marginTop) {
        this.marginTop = marginTop;
    }

    /**
     * @see #setMarginTop(Number)
     * @return
     */
    public Number getMarginTop() {
        return marginTop;
    }

    /**
     * Set the overall animation for all chart updating. Animation can be
     * disabled throughout the chart by setting it to false here.
     * <p>
     * Note, that animation for initial (or redraw) is controlled by plot
     * options.
     * 
     * @param animation
     */
    public void setAnimation(Boolean animation) {
        this.animation = animation;
    }

    /**
     * @return true is animation are enabled for this chart
     */
    public boolean isAnimation() {
        return animation == null ? true : animation;
    }

    /**
     * The URL for an image to use as the plot background. To set an image as
     * the background for the entire chart, set a CSS background image to the
     * container element. Defaults to null.
     * 
     * @param object
     */
    public void setPlotBackgroundImage(String plotBackgroundImage) {
        this.plotBackgroundImage = plotBackgroundImage;
    }

    /**
     * @see #setPlotBackgroundImage(String)
     * @return
     */
    public String getPlotBackgroundImage() {
        return plotBackgroundImage;
    }

    /**
     * When using multiple axis, the ticks of two or more opposite axes will
     * automatically be aligned by adding ticks to the axis or axes with the
     * least ticks. This can be prevented by setting alignTicks to false. If the
     * grid lines look messy, it's a good idea to hide them for the secondary
     * axis by setting gridLineWidth to 0. Defaults to true.
     * 
     * @param alignTicks
     */
    public void setAlignTicks(Boolean alignTicks) {
        this.alignTicks = alignTicks;
    }

    /**
     * @see #setAlignTicks(Boolean)
     * @return
     */
    public boolean isAlignTicks() {
    	return alignTicks == null ? true : alignTicks;
    }

    /**
     * When true, cartesian charts like line, spline, area and column are
     * transformed into the polar coordinate system. Requires
     * highcharts-more.js.
     * 
     * @param polar
     */
    public void setPolar(Boolean polar) {
        this.polar = polar;
    }

    /**
     * @see #setPolar(Boolean)
     * @return
     */
    public boolean isPolar() {
    	return polar == null ? false : polar;
    }
}
