package com.vaadin.addon.charts.model;

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
