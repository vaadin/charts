package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;

/**
 * The legend is a box containing a symbol and name for each series item or
 * point item in the chart.
 * 
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

    /**
     * Get hover style of item
     * 
     * @return Item style
     */
    public Style getItemHoverStyle() {
        return itemHoverStyle;
    }

    /**
     * Set hover style of item
     * 
     * @param itemHoverStyle
     *            Item style
     */
    public void setItemHoverStyle(Style itemHoverStyle) {
        this.itemHoverStyle = itemHoverStyle;
    }

    /**
     * Get hidden style of item
     * 
     * @return Item style
     */
    public Style getItemHiddenStyle() {
        return itemHiddenStyle;
    }

    /**
     * Set hidden style of item
     * 
     * @param itemHiddenStyle
     *            Item style
     */
    public void setItemHiddenStyle(Style itemHiddenStyle) {
        this.itemHiddenStyle = itemHiddenStyle;
    }

    /**
     * The layout of the legend items. Can be one of HORIZONTAL("horizontal") or
     * VERTICAL("vertical"). Defaults to HORIZONTAL.
     * 
     * @return the layout
     */
    public LayoutDirection getLayout() {
        return layout;
    }

    /**
     * @see #getLayout()
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
     * The horizontal alignment of the legend box within the chart area.
     * Defaults to CENTER.
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
     * The vertical alignment of the legend box. Can be one of TOP, MIDDLE or
     * BOTTOM. Vertical position can be further determined by the y option.
     * Defaults to BOTTOM.
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
     * The x offset of the legend relative to it's horizontal alignment align
     * within chart.spacingLeft and chart.spacingRight. Negative x moves it to
     * the left, positive x moves it to the right. The default value of 15
     * together with align: "center" puts it in the center of the plot area.
     * Defaults to 0.
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
     * The vertical offset of the legend relative to it's vertical alignment
     * verticalAlign within chart.spacingTop and chart.spacingBottom. Negative y
     * moves it up, positive y moves it down. Defaults to 0.
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
     * When the legend is floating, the plot area ignores it and is allowed to
     * be placed below it. Defaults to false.
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
     * The width of the drawn border around the legend. Defaults to 1.
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
     * The background color of the legend, filling the rounded corner border.
     * Defaults to null.
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * The background color of the legend, filling the rounded corner border.
     * Defaults to null.
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
     * Whether to apply a drop shadow to the legend. A backgroundColor also
     * needs to be applied for this to take effect. Since 2.3 the shadow can be
     * an object configuration containing color, offsetX, offsetY, opacity and
     * width. Defaults to false.
     */
    public void setShadow(Boolean shadow) {
        this.shadow = shadow;
    }

    /**
     * The color of the drawn border around the legend.
     * 
     * @return Border color or null if not defined
     */
    public SolidColor getBorderColor() {
        return borderColor;
    }

    /**
     * The color of the drawn border around the legend.
     * 
     * @param borderColor
     */
    public void setBorderColor(SolidColor borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * The color of the drawn border around the legend.
     * 
     * @param borderColor
     */
    public void setBorderColor(String borderColor) {
        this.borderColor = new SolidColor(borderColor);
    }

    /**
     * The border corner radius of the legend. Defaults to 5.
     * 
     * @return Border radius or null if not defined
     */
    public Number getBorderRadius() {
        return borderRadius;
    }

    /**
     * @see #getBorderRadius()
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
     * @return
     */
    public boolean isEnabled() {
        return enabled == null ? true : enabled;
    }

    /**
     * Whether to reverse the order of the legend items compared to the order of
     * the series or points as defined in the configuration object. Defaults to
     * false.
     * 
     * @return Reversed value or null if not defined
     */
    public boolean isReversed() {
        return reversed == null ? false : reversed;
    }

    /**
     * @see #getReversed()
     * @param reversed
     */
    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
    }

    /**
     * CSS styles for each legend item. Defaults to:
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
     * @return
     */
    public Style getItemStyle() {
        if (itemStyle == null) {
            itemStyle = new Style();
        }
        return itemStyle;
    }

    /**
     * CSS styles for the legend area. In the 1.x versions the position of the
     * legend area was determined by CSS. In 2.x, the position is determined by
     * properties like align, verticalAlign, x and y, but the styles are still
     * parsed for backwards compatibility.
     * 
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
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

}
