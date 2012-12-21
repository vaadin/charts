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
 * HTML labels that can be positioned anywhere in the chart area.
 * 
 */
public class HTMLLabels extends AbstractConfigurationObject {
    private HTMLLabelItem[] items;

    /**
     * Default constructor
     */
    public HTMLLabels() {

    }

    /**
     * Constructor with given label items
     * 
     * @param items
     */
    public HTMLLabels(HTMLLabelItem... items) {
        setItems(items);
    }

    /**
     * Return array of single label items
     * 
     * @return
     */
    public HTMLLabelItem[] getItems() {
        return items;
    }

    /**
     * Set label items
     * 
     * @param items
     */
    public void setItems(HTMLLabelItem... items) {
        this.items = items;
    }

}
