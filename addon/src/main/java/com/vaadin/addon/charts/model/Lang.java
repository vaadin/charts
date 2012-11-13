package com.vaadin.addon.charts.model;

/**
 * Language object. The language object is global and it can't be set on each
 * chart initiation.
 */
public class Lang extends AbstractConfigurationObject {
    private String loading;
    private String decimalPoint;
    private String thousandsSep;

    /**
     * Get loading text shown
     * 
     * @return Loading text or null if not defined
     */
    public String getLoading() {
        return loading;
    }

    /**
     * @see #getLoading()
     * @param loading
     */
    public void setLoading(String loading) {
        this.loading = loading;
    }

    /**
     * Decimal point used
     * 
     * @return Decimal point or null if not defined
     */
    public String getDecimalPoint() {
        return decimalPoint;
    }

    /**
     * @see #getDecimalPoint()
     * @param decimalPoint
     */
    public void setDecimalPoint(String decimalPoint) {
        this.decimalPoint = decimalPoint;
    }

    /**
     * Thousands separator used
     * 
     * @return Thousands separator point or null if not defined
     */
    public String getThousandsSep() {
        return thousandsSep;
    }

    /**
     * @see #getThousandsSep()
     * @param thousandsSep
     */
    public void setThousandsSep(String thousandsSep) {
        this.thousandsSep = thousandsSep;
    }
}
