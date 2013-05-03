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

/**
 * The Title class represents the title of a chart
 */
@SuppressWarnings("serial")
public class Title extends AbstractTitle {

    private Number margin;

    /**
     * Constructs a blank title.
     */
    public Title() {
        super();
    }

    /**
     * Constructs a new title using the given text
     * 
     * @param text
     *            Text of title
     */
    public Title(String text) {
        super(text);
    }

    /**
     * @see #setMargin(Number)
     * @return The margin between the title and the plot area or null if not
     *         defined.
     */
    public Number getMargin() {
        return margin;
    }

    /**
     * Sets the margin between the title and the plot area, or if a subtitle is
     * present, the margin between the subtitle and the plot area. Defaults to
     * 15.
     * 
     * @param margin
     */
    public void setMargin(Number margin) {
        this.margin = margin;
    }

}
