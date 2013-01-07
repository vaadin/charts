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
 * Language object. The language object is global and can only be set once. It
 * cannot be set on each chart initiation.
 */
public class Lang extends AbstractConfigurationObject {
    private String loading;
    private String decimalPoint;
    private String thousandsSep;

    /**
     * @see #setLoading(String)
     * 
     * @return The loading text or null if not defined
     */
    public String getLoading() {
        return loading;
    }

    /**
     * Sets the loading text that is shown while loading charts.
     * 
     * @param loading
     */
    public void setLoading(String loading) {
        this.loading = loading;
    }

    /**
     * @see #setDecimalPoint(String)
     * @return The decimal point or null if not defined
     */
    public String getDecimalPoint() {
        return decimalPoint;
    }

    /**
     * Sets the decimal point to use
     * 
     * @param decimalPoint
     */
    public void setDecimalPoint(String decimalPoint) {
        this.decimalPoint = decimalPoint;
    }

    /**
     * @see #setThousandsSep()
     * @return The thousands separator point or null if not defined
     */
    public String getThousandsSep() {
        return thousandsSep;
    }

    /**
     * Sets the thousands separator to use
     * 
     * @param thousandsSep
     */
    public void setThousandsSep(String thousandsSep) {
        this.thousandsSep = thousandsSep;
    }
}
