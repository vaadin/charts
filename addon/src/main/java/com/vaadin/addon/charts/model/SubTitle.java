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

/**
 * The chart's subtitle
 */
@SuppressWarnings("serial")
public class SubTitle extends AbstractTitle {

    /**
     * Constructs an empty subtitle
     */
    public SubTitle() {
    }

    /**
     * Constructs a subtitle using the given text
     * 
     * @param text
     */
    public SubTitle(String text) {
        super(text);
    }
}
