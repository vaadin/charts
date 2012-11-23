package com.vaadin.addon.charts.model;

/**
 * Chart's title
 */
public class Title extends AbstractTitle {

    private Number margin;

    /**
     * Default constructor
     */
    public Title() {
        super();
    }

    /**
     * Create new title with given text
     * 
     * @param text
     *            Text of title
     */
    public Title(String text) {
        super(text);
    }

    /**
     * @see #setMargin(Number)
     * @return Margin of title or null if not defined.
     */
    public Number getMargin() {
        return margin;
    }

    /**
     * The margin between the title and the plot area, or if a subtitle is
     * present, the margin between the subtitle and the plot area. Defaults to
     * 15.
     * 
     * @param margin
     */
    public void setMargin(Number margin) {
        this.margin = margin;
    }

}
