package com.vaadin.addon.charts.model.style;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;

/**
 * Style options for css styling
 */
public class Style extends AbstractConfigurationObject {
    private Color color;
    private FontWeight fontWeight;
    private String fontFamily;
    private String fontSize;
    private String left;
    private String top;
    private StylePosition position;
    private String lineHeight;

    /**
     * @see #setColor(Color)
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * Style's css color attribute
     * 
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @see #setFontWeight(FontWeight)
     * @return
     */
    public FontWeight getFontWeight() {
        return fontWeight;
    }

    /**
     * Style's css fontWeight attribute
     * 
     * @param fontWeight
     */
    public void setFontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
    }

    /**
     * @see #setFontFamily(String)
     * @return
     */
    public String getFontFamily() {
        return fontFamily;
    }

    /**
     * Style's css fontFamily attribute
     * 
     * @param fontFamily
     */
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    /**
     * @see #setFontSize(String)
     * @return
     */
    public String getFontSize() {
        return fontSize;
    }

    /**
     * Style's css fontSize attribute
     * 
     * @param fontSize
     */
    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * @see #setLeft(String)
     * @return
     */
    public String getLeft() {
        return left;
    }

    /**
     * Style's css left attribute
     * 
     * @param left
     */
    public void setLeft(String left) {
        this.left = left;
    }

    /**
     * @see #setTop(String)
     * @return
     */
    public String getTop() {
        return top;
    }

    /**
     * Style's css top attribute
     * 
     * @param top
     */
    public void setTop(String top) {
        this.top = top;
    }

    /**
     * Css position attribute
     * 
     * @param position
     */
    public void setPosition(StylePosition position) {
        this.position = position;
    }

    /**
     * @see #setPosition(StylePosition)
     * @return
     */
    public StylePosition getPosition() {
        return position;
    }

    /**
     * Css lineHeight attribute
     */
    public void setLineHeight(String lineHeight) {
        this.lineHeight = lineHeight;
    }

    /**
     * @see #setLineHeight(String)
     * @return
     */
    public String getLineHeight() {
        return lineHeight;
    }

}
