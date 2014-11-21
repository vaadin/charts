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

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
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
    private Color plotBorderColor;
    private Number plotBorderWidth;
    private Boolean plotShadow;
    private ZoomType zoomType;
    private PinchType pinchType;
    private Style style;
    private Boolean reflow;
    private Number borderWidth;
    private Number borderRadius;
    private SolidColor borderColor;
    private Color backgroundColor;
    private Number[] margin;
    private Number marginBottom;
    private Number marginLeft;
    private Number marginRight;
    private Number marginTop;
    private Object animation;
    private String plotBackgroundImage;
    private Boolean alignTicks;
    private Boolean polar;
    private Options3d options3d;

    transient Configuration configuration;

    public ChartModel() {
    }

    /**
     * Create new chart of the given type
     * 
     * @param type
     *            The type of chart to create
     */
    public ChartModel(Configuration conf, ChartType type) {
        this.type = type;
    }

    /**
     * @see #setType(ChartType)
     * @return Type of chart or null if not defined
     */
    public ChartType getType() {
        return type;
    }

    /**
     * Sets the default chart type used to render series. Can be any one of the
     * members of {@link ChartType}. Defaults to {@link ChartType#LINE}. The
     * type can be be overridden per series by using
     * {@link AbstractSeries#setPlotOptions(AbstractPlotOptions)}.
     * 
     * @param type
     */
    public void setType(ChartType type) {
        this.type = type;
    }

    /**
     * @see #setSpacingTop(Number)
     * @return Spacing top or null if not defined
     */
    public Number getSpacingTop() {
        return spacingTop;
    }

    /**
     * Sets the space between the top edge of the chart and the content (plot
     * area, axis title and labels, title, subtitle or legend in top position).
     * 
     * @param spacingTop
     */
    public void setSpacingTop(Number spacingTop) {
        this.spacingTop = spacingTop;
    }

    /**
     * @see #setSpacingLeft(Number)
     * @return Spacing left or null if not defined
     */
    public Number getSpacingLeft() {
        return spacingLeft;
    }

    /**
     * Sets the space between the left edge of the chart and the content (plot
     * area, axis title and labels, title, subtitle or legend in top position).
     * 
     * @param spacingLeft
     */
    public void setSpacingLeft(Number spacingLeft) {
        this.spacingLeft = spacingLeft;
    }

    /**
     * @see #setSpacingBottom(Number)
     * @return Spacing bottom or null if not defined
     */
    public Number getSpacingBottom() {
        return spacingBottom;
    }

    /**
     * Sets the space between the bottom edge of the chart and the content (plot
     * area, axis title and labels, title, subtitle or legend in top position).
     * 
     * @param spacingBottom
     */
    public void setSpacingBottom(Number spacingBottom) {
        this.spacingBottom = spacingBottom;
    }

    /**
     * @see #setSpacingRight(Number)
     * @return Spacing right or null if not defined
     */
    public Number getSpacingRight() {
        return spacingRight;
    }

    /**
     * Sets the space between the right edge of the chart and the content (plot
     * area, axis title and labels, title, subtitle or legend in top position).
     * 
     * @param spacingRight
     */
    public void setSpacingRight(Number spacingRight) {
        this.spacingRight = spacingRight;
    }

    /**
     * @see #setInverted(Boolean)
     * @return Inverted state or null if not defined
     */
    public boolean isInverted() {
        return inverted == null ? false : inverted;
    }

    /**
     * Sets whether to invert the axes so that the X-axis is vertical and Y-axis
     * is horizontal. When true, the X-axis is reversed by default. If a bar
     * series is present in the chart, it will be inverted automatically.
     * Defaults to false.
     * 
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
     * @see #setZoomType(ZoomType)
     */
    public ZoomType getZoomType() {
        return zoomType;
    }

    /**
     * @see #setPinchType(PinchType)
     * @return
     */
    public PinchType getPinchType() {
        return pinchType;
    }

    /**
     * Equivalent to zoomType, but for multitouch gestures only. By default, the
     * pinchType behavior is the same as the zoomType setting. However, pinching can be
     * enabled separately in some cases. Defaults to null.
     * 
     * @param pinchType
     */
    public void setPinchType(PinchType pinchType) {
        this.pinchType = pinchType;
    }

    /**
     * @see #setPlotBackgroundColor(Color)
     * 
     * @return The background color or null if not defined
     */
    public Color getPlotBackgroundColor() {
        return plotBackgroundColor;
    }

    /**
     * Sets the background color or gradient for the plot area. Defaults to
     * null.
     * 
     * @param plotBackgroundColor
     */
    public void setPlotBackgroundColor(Color plotBackgroundColor) {
        this.plotBackgroundColor = plotBackgroundColor;
    }

    /**
     * @see #setPlotBorderColor(Color)
     * @return The color of the border or null if not defined
     */
    public Color getPlotBorderColor() {
        return plotBorderColor;
    }

    /**
     * Sets the color of the inner chart or plot area border. Defaults to
     * "#C0C0C0".
     * 
     * @param plotBorderColor
     */
    public void setPlotBorderColor(Color plotBorderColor) {
        this.plotBorderColor = plotBorderColor;
    }

    /**
     * @see #setPlotBorderWidth(Number)
     * @return The width of the border in pixels or null if not defined
     */
    public Number getPlotBorderWidth() {
        return plotBorderWidth;
    }

    /**
     * Sets the pixel width of the plot area border. Defaults to 0.
     * 
     * @param plotBorderWidth
     */
    public void setPlotBorderWidth(Number plotBorderWidth) {
        this.plotBorderWidth = plotBorderWidth;
    }

    /**
     * @see #setPlotShadow(Number)
     */
    public boolean isPlotShadow() {
        return plotShadow == null ? false : plotShadow;
    }

    /**
     * Sets whether to apply a drop shadow to the plot area. Requires that
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
     * Sets all margins to the same value
     * 
     * @param size
     */
    public void setMargin(Number size) {
        setMargin(size, size, size, size);
    }

    /**
     * Sets the horizontal margin for the right and left margin and the vertical
     * margin for top and bottom
     * 
     * @param horizontal
     * @param vertical
     */
    public void setMargin(Number horizontal, Number vertical) {
        setMargin(vertical, horizontal, vertical, horizontal);
    }

    /**
     * Set all margins in one call
     * 
     * @param margins
     */
    public void setMargin(Number top, Number right, Number bottom, Number left) {
        margin = new Number[] { top, right, bottom, left };
    }

    /**
     * @see #setMargin(Number)
     * @see #setMargin(Number, Number)
     * @see #setMargin(Number, Number, Number, Number)
     */
    public Number[] getMargin() {
        return margin;
    }

    /**
     * Sets whether to reflow the chart to fit the width of the container DIV
     * when resizing the window. Defaults to true.
     * 
     * @param reflow
     */
    public void setReflow(Boolean reflow) {
        this.reflow = reflow;
    }

    /**
     * @see #setReflow(Boolean)
     */
    public boolean isReflow() {
        return reflow == null ? true : reflow;
    }

    /**
     * Sets the pixel width of the outer chart border. The border is painted
     * using vector graphic techniques to allow rounded corners. Defaults to 0.
     * 
     * @param borderWidth
     */
    public void setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * @see #setBorderWidth(Number)
     */
    public Number getBorderWidth() {
        return borderWidth;
    }

    /**
     * Sets the background color or gradient for the outer chart area. Defaults
     * to "#FFFFFF".
     * 
     * @param backgroundColor
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @see #setBackgroundColor(Color)
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the margin between the left outer edge of the chart and the plot
     * area. Use this to set a fixed pixel value for the margin as opposed to
     * the default dynamic margin. Defaults to 80.
     * 
     * @see #setSpacingLeft(Number)
     * 
     * @param marginLeft
     */
    public void setMarginLeft(Number marginLeft) {
        this.marginLeft = marginLeft;
    }

    /**
     * @see #setMarginLeft(Number)
     */
    public Number getMarginLeft() {
        return marginLeft;
    }

    /**
     * Sets the margin between the right outer edge of the chart and the plot
     * area. Use this to set a fixed pixel value for the margin as opposed to
     * the default dynamic margin. Defaults to 50.
     * 
     * @see #setSpacingRight(Number)
     * 
     * @param marginRight
     */
    public void setMarginRight(Number marginRight) {
        this.marginRight = marginRight;
    }

    /**
     * @see #setMarginRight(Number)
     */
    public Number getMarginRight() {
        return marginRight;
    }

    /**
     * @see #setStyle(Style)
     */
    public Style getStyle() {
        if (style == null) {
            style = new Style();
        }
        return style;
    }

    /**
     * Sets additional CSS styles to apply inline in the container DIV. Note
     * that since the default font styles are applied in the renderer, it is
     * ignorant of the individual chart options and must be set globally.
     * Defaults to: <br>
     * style: { fontFamily: '"Lucida Grande", "Lucida Sans Unicode", Verdana,
     * Arial, Helvetica, sans-serif', // default font fontSize: '12px' }
     * 
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * Sets the margin between the bottom outer edge of the chart and the plot
     * area. Use this to set a fixed pixel value for the margin as opposed to
     * the default dynamic margin. Defaults to 70.
     * 
     * @see #setSpacingBottom(Number)
     * 
     * @param marginBottom
     */
    public void setMarginBottom(Number marginBottom) {
        this.marginBottom = marginBottom;
    }

    /**
     * @see #setMarginBottom(Number)
     */
    public Number getMarginBottom() {
        return marginBottom;
    }

    /**
     * Sets the margin between the top outer edge of the chart and the plot
     * area. Use this to set a fixed pixel value for the margin as opposed to
     * the default dynamic margin. Defaults to null.
     * 
     * @see #setSpacingTop(Number)
     * 
     * @param marginTop
     */
    public void setMarginTop(Number marginTop) {
        this.marginTop = marginTop;
    }

    /**
     * @see #setMarginTop(Number)
     */
    public Number getMarginTop() {
        return marginTop;
    }

    /**
     * Checks if animation is set as a Boolean and if so, returns that setting.
     * Otherwise returns <code>null</code>.
     * 
     * @see #setAnimation(Boolean)
     * @return <code>null</code> when the animation is not set as Boolean,
     *         otherwise a corresponding Boolean.
     */
    public Boolean isAnimation() {
        return animation instanceof Boolean ? (Boolean) animation : null;
    }

    /**
     * Returns current animation settings. Can be a boolean (
     * {@link #isAnimation()}), or an {@link Animation} instance.
     * 
     * @see #setAnimation(Boolean)
     * @see #setAnimation(Animation)
     * @return Current animation setting.
     */
    public Object getAnimation() {
        return animation;
    }

    /**
     * Some updates to existing charts can be animated. This method sets the
     * overall animation for all chart updates. Animation can be disabled
     * throughout the chart by setting it to false here. The default is true.
     * <p>
     * Note, that animation for the initial rendering (or redraw) is controlled
     * by the plot options.
     * 
     * @param animation
     */
    public void setAnimation(Boolean animation) {
        this.animation = animation;
        configuration.fireAnimationChanged(animation);
    }

    /**
     * Some updates to existing charts can be animated. This method sets the
     * overall animation for all chart updates. Animation can be disabled
     * throughout the chart by setting it to false here. The default is true.
     * <p>
     * Note, that animation for the initial rendering (or redraw) is controlled
     * by the plot options.
     * 
     * @param animation
     */
    public void setAnimation(Animation animation) {
        this.animation = animation;
        configuration.fireAnimationChanged(animation != null);
    }

    /**
     * Sets the URL for an image to use as the plot background. To set an image
     * as the background for the entire chart, set a CSS background image to the
     * container element. Defaults to null.
     * 
     * @param object
     */
    public void setPlotBackgroundImage(String plotBackgroundImage) {
        this.plotBackgroundImage = plotBackgroundImage;
    }

    /**
     * @see #setPlotBackgroundImage(String)
     */
    public String getPlotBackgroundImage() {
        return plotBackgroundImage;
    }

    /**
     * When using multiple axes, the ticks of two or more opposite axes will
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
     */
    public boolean isPolar() {
        return polar == null ? false : polar;
    }

    /**
     * @return The color of the outer chart border
     */
    public SolidColor getBorderColor() {
        return borderColor;
    }

    /**
     * Sets the color of the outer chart border.
     * 
     * @param borderColor
     *            The color of the outer chart border.
     */
    public void setBorderColor(SolidColor borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * @return the corner radius of the outer chart border
     */
    public Number getBorderRadius() {
        return borderRadius;
    }

    /**
     * The corner radius of the outer chart border. Defaults to 5.
     * 
     * @param borderRadius
     *            the border radius in pixels
     */
    public void setBorderRadius(Number borderRadius) {
        this.borderRadius = borderRadius;
    }

    public Options3d getOptions3d() {
        return options3d;
    }

    public void setOptions3d(Options3d options3d) {
        this.options3d = options3d;
    }

}
