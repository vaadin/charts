package com.vaadin.addon.charts.model.style;


/**
 * Highchart by default puts a credits label in the lower right corner of the
 * chart. This can be changed using these options.
 * 
 */
public class Credits extends StyleWrapper {

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
     * Whether to show the credits text. Defaults to true.
     * 
     * @return enabled
     */
    public boolean isEnabled() {
    	return enabled == null ? true : enabled;
    }

    /**
     * @see #isEnabled()
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * The text for the credits label. Defaults to "Highcharts.com".
     * 
     * @return Text or null if not defined
     */
    public String getText() {
        return text;
    }

    /**
     * @see #getText()
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Position configuration for the credits label. Supported properties are
     * align, verticalAlign, x and y.
     * 
     * @return Position or null if not defined
     */
    public StylePosition getPosition() {
        return position;
    }

    /**
     * @see #getPosition()
     * @param position
     */
    public void setPosition(StylePosition position) {
        this.position = position;
    }

    /**
     * Gets the link where credits text point to
     * 
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
