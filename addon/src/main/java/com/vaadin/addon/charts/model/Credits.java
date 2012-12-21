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

import com.vaadin.addon.charts.model.style.StylePosition;

/**
 * Highchart by default puts a credits label in the lower right corner of the
 * chart. This can be changed using these options.
 * 
 */
public class Credits {

    private Boolean enabled;
    private String text;
    private String href;
    private StylePosition position;

    /**
     * Default constructor
     */
    public Credits() {

    }

    /**
     * Create credits with enabled state
     * 
     * @param enabled
     *            Enabled state of credit made
     */
    public Credits(boolean enabled) {
        setEnabled(enabled);
    }

    /**
     * Create enabled credit with given text
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
     * @return enabled
     */
    public boolean isEnabled() {
        return enabled == null ? true : enabled;
    }

    /**
     * Whether to show the credits text. Defaults to true.
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @see #setText(String)
     * @return Text or null if not defined
     */
    public String getText() {
        return text;
    }

    /**
     * The text for the credits label. Defaults to "Vaadin Charts".
     * 
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @see #setPosition(StylePosition)
     * @return Position or null if not defined
     */
    public StylePosition getPosition() {
        return position;
    }

    /**
     * Position configuration for the credits label. Supported properties are
     * align, verticalAlign, x and y.
     * 
     * @param position
     */
    public void setPosition(StylePosition position) {
        this.position = position;
    }

    /**
     * @see #setHref(String)
     * @return
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the link where credits text point to
     * 
     * @param href
     */
    public void setHref(String href) {
        this.href = href;
    }

}
