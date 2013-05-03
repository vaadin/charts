package com.vaadin.addon.charts.model;

/**
 * Settings to configure buttons used by the exporting module.
 * 
 * @see Exporting
 */
public class ExportButton extends AbstractConfigurationObject {

    /*
     * TODO add other options supported by Highcharts when needed
     */

    private Boolean enabled;

    public boolean isEnabled() {
        return enabled != null ? enabled : true;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
