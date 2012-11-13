package com.vaadin.addon.charts.model.style;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;

/**
 * Style options
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public FontWeight getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getTop() {
        return top;
    }

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
