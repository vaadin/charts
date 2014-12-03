package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Style;

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

/**
 * A credits label is put in the lower right corner of the chart by default.
 * This label can be changed using these options.
 */
public class Credits extends AbstractConfigurationObject {

    private Boolean enabled;
    private String text;
    private String href;
    private Style style;
    private CreditPosition position;

    public Credits() {
    }

    /**
     * Constructs a Credits instance that is either enabled or disabled.
     * 
     * @param enabled
     *            True to enable, false to disable.
     */
    public Credits(boolean enabled) {
        setEnabled(enabled);
    }

    /**
     * Constructs a Credits instance with the given text. Credits are
     * automatically enabled.
     * 
     * @param text
     *            Text in credit
     */
    public Credits(String text) {
        this(true);
        setText(text);
    }

    /**
     * @see #setEnabled(Boolean)
     * @return true if enabled, false if disabled.
     */
    public boolean isEnabled() {
        return enabled == null ? true : enabled;
    }

    /**
     * Sets whether to show the credits text or not. Defaults to true.
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns current text of the credits. Defaults to an empty string.
     * 
     * @see #setText(String)
     * @return The text of the credits label or null if not defined
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text of the credits label.
     * 
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @see #setPosition(CreditPosition)
     * @return The position or null if not defined
     */
    public CreditPosition getPosition() {
        return position;
    }

    /**
     * Sets the position configuration for the credits label. Supported
     * properties are align, verticalAlign, x and y.
     * 
     * @param position
     */
    public void setPosition(CreditPosition position) {
        this.position = position;
    }

    /**
     * @see #setHref(String)
     * @return the link that the credit text points to.
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the link where the credits text is to point.
     * 
     * @param href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * @return The styles for the credits
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Sets the styles for the credits
     * 
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

}
