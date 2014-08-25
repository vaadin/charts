package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;

/**
 * The legend is a box containing a symbol and name for each series item or
 * point item in the chart.
 */
public class Legend extends AbstractConfigurationObject {

    private LayoutDirection layout;
    private HorizontalAlign align;
    private VerticalAlign verticalAlign;
    private Number x;
    private Number y;
    private Boolean floating;
    private SolidColor borderColor;
    private Number borderWidth;
    private Number borderRadius;
    private Color backgroundColor;
    private Boolean shadow;
    private Boolean reversed;
    private Boolean enabled;
    private Style itemStyle;
    private Style style;
    private Style itemHoverStyle;
    private Style itemHiddenStyle;
    private Number itemMarginTop;
    private Number itemMarginBottom;

    /**
     * @see #setItemHoverStyle(Style)
     * 
     * @return The item style when hovering
     */
    public Style getItemHoverStyle() {
        return itemHoverStyle;
    }

    /**
     * Sets the CSS styles for each legend item when hovering over it.
     * Properties are inherited from style unless overridden here. Defaults to:
     * 
     * <code>
     * itemHoverStyle: { color: '#000' }
     * </code>
     * 
     * @param itemHoverStyle
     */
    public void setItemHoverStyle(Style itemHoverStyle) {
        this.itemHoverStyle = itemHoverStyle;
    }

    /**
     * @see #setItemHiddenStyle(Style)
     * 
     * @return The item style when the corresponding series or point is hidden
     */
    public Style getItemHiddenStyle() {
        return itemHiddenStyle;
    }

    /**
     * Sets the CSS styles for each legend item when the corresponding series or
     * point is hidden. Properties are inherited from style unless overridden
     * here. Defaults to:
     * 
     * <code>
     * itemHiddenStyle: { color: '#CCC' }
     * </code>
     * 
     * @param itemHiddenStyle
     *            Item style
     */
    public void setItemHiddenStyle(Style itemHiddenStyle) {
        this.itemHiddenStyle = itemHiddenStyle;
    }

    /**
     * @see #setLayout(LayoutDirection)
     */
    public LayoutDirection getLayout() {
        return layout;
    }

    /**
     * Sets the layout of the legend items. Defaults to
     * {@link LayoutDirection#HORIZONTAL}.
     */
    public void setLayout(LayoutDirection layout) {
        this.layout = layout;
    }

    /**
     * @see #setHorizontalAlign(HorizontalAlign)
     */
    public HorizontalAlign getHorizontalAlign() {
        return align;
    }

    /**
     * Sets the horizontal alignment of the legend box within the chart area.
     * Defaults to {@link HorizontalAlign#CENTER}.
     */
    public void setHorizontalAlign(HorizontalAlign horizontalAlign) {
        align = horizontalAlign;
    }

    /**
     * @see #setVerticalAlign(VerticalAlign)
     */
    public VerticalAlign getVerticalAlign() {
        return verticalAlign;
    }

    /**
     * Sets the vertical alignment of the legend box. The vertical position can
     * be further determined by the y option. Defaults to
     * {@link VerticalAlign#BOTTOM}.
     */
    public void setVerticalAlign(VerticalAlign verticalAlign) {
        this.verticalAlign = verticalAlign;
    }

    /**
     * @see #setX(Number)
     */
    public Number getX() {
        return x;
    }

    /**
     * Sets the X offset of the legend relative to it's horizontal alignment
     * within chart.spacingLeft and chart.spacingRight. A negative value for X
     * moves it to the left, positive value moves it to the right. The default
     * value of 15 together with {@link HorizontalAlign#CENTER} puts it in the
     * center of the plot area. Defaults to 0.
     * 
     * @param x
     */
    public void setX(Number x) {
        this.x = x;
    }

    /**
     * @see #setY(Number)
     */
    public Number getY() {
        return y;
    }

    /**
     * Sets the vertical offset of the legend relative to it's vertical
     * alignment within chart.spacingTop and chart.spacingBottom. A negative
     * value for Y moves it up, while a positive value moves it down. Defaults
     * to 0.
     * 
     * @param y
     */
    public void setY(Number y) {
        this.y = y;
    }

    /**
     * @see #setFloating(Boolean)
     */
    public boolean isFloating() {
        return floating == null ? false : floating;
    }

    /**
     * Sets whether the legend is floating or not. When the legend is floating,
     * the plot area ignores it and is allowed to be placed below it. Defaults
     * to false.
     * 
     * @param floating
     */
    public void setFloating(Boolean floating) {
        this.floating = floating;
    }

    /**
     * @see #setBorderWidth(Number)
     */
    public Number getBorderWidth() {
        return borderWidth;
    }

    /**
     * Sets the width of the border drawn around the legend. Defaults to 1.
     */
    public void setBorderWidth(Number borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * @see #setBackgroundColor(String)
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the background color of the legend, filling the rounded corner
     * border. Defaults to null.
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Sets the background color of the legend, filling the rounded corner
     * border. Defaults to null.
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = new SolidColor(backgroundColor);
    }

    /**
     * @see #setShadow(Boolean)
     */
    public boolean isShadow() {
        return shadow == null ? false : shadow;
    }

    /**
     * Sets whether to apply a drop shadow to the legend. A backgroundColor also
     * needs to be applied for this to take effect. Defaults to false.
     */
    public void setShadow(Boolean shadow) {
        this.shadow = shadow;
    }

    /**
     * @see #setBorderColor(SolidColor)
     * 
     * @return The color of the border or null if not defined
     */
    public SolidColor getBorderColor() {
        return borderColor;
    }

    /**
     * Sets the color of the border drawn around the legend.
     * 
     * @param borderColor
     */
    public void setBorderColor(SolidColor borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * Sets the color of the border drawn around the legend.
     * 
     * @param borderColor
     */
    public void setBorderColor(String borderColor) {
        this.borderColor = new SolidColor(borderColor);
    }

    /**
     * @see #setBorderRadius(Number)
     * @return The border radius or null if not defined
     */
    public Number getBorderRadius() {
        return borderRadius;
    }

    /**
     * Sets the corner radius of border of the legend. Defaults to 5.
     * 
     * @param borderRadius
     */
    public void setBorderRadius(Number borderRadius) {
        this.borderRadius = borderRadius;
    }

    /**
     * Enable or disable the legend. Defaults to true.
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @see #setEnabled(boolean)
     */
    public boolean isEnabled() {
        return enabled == null ? true : enabled;
    }

    /**
     * @see #setReversed()
     * @return true if the order of items is reversed or null if undefined.
     */
    public boolean isReversed() {
        return reversed == null ? false : reversed;
    }

    /**
     * Sets whether to reverse the order of the legend items compared to the
     * order of the series or points as defined in the configuration object.
     * Defaults to false.
     * 
     * @param reversed
     */
    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }

    /**
     * Sets the CSS styles for all legend items. Defaults to:
     * 
     * itemStyle: { cursor: 'pointer', color: '#3E576F' }
     * 
     * @param itemStyle
     */
    public void setItemStyle(Style itemStyle) {
        this.itemStyle = itemStyle;
    }

    /**
     * @see #setItemStyle(Style)
     */
    public Style getItemStyle() {
        if (itemStyle == null) {
            itemStyle = new Style();
        }
        return itemStyle;
    }

    /**
     * @see #setItemMarginTop(Number)
     * @return the itemMarginTop
     */
    public Number getItemMarginTop() {
        return itemMarginTop;
    }

    /**
     * Sets the top itemMargin for legend items. Defaults to 0.
     * 
     * @param itemMarginTop
     *            the itemMarginTop to set
     */
    public void setItemMarginTop(Number itemMarginTop) {
        this.itemMarginTop = itemMarginTop;
    }

    /**
     * @see #setItemMarginBottom(Number)
     * @return the itemMarginBottom
     */
    public Number getItemMarginBottom() {
        return itemMarginBottom;
    }

    /**
     * Sets the bottom itemMargin for legend items. Defaults to 0.
     * 
     * @param itemMarginBottom
     *            the itemMarginBottom to set
     */
    public void setItemMarginBottom(Number itemMarginBottom) {
        this.itemMarginBottom = itemMarginBottom;
    }

    /**
     * Sets the CSS styles for the legend area. In Highcharts 1.x versions the
     * position of the legend area was determined by CSS. In 2.x, the position
     * is determined by properties like align, verticalAlign, x and y, but the
     * styles are still parsed for backwards compatibility.
     * 
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
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
}
