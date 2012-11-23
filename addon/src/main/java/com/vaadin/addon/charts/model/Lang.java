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
     * @see #setLoading(String)
     * 
     * @return Loading text or null if not defined
     */
    public String getLoading() {
        return loading;
    }

    /**
     * set loading text shown
     * 
     * @param loading
     */
    public void setLoading(String loading) {
        this.loading = loading;
    }

    /**
     * Decimal point used
     * 
     * @see #setDecimalPoint(String)
     * @return Decimal point or null if not defined
     */
    public String getDecimalPoint() {
        return decimalPoint;
    }

    /**
     * Decimal point used
     * 
     * @param decimalPoint
     */
    public void setDecimalPoint(String decimalPoint) {
        this.decimalPoint = decimalPoint;
    }

    /**
     * @see #setThousandsSep()
     * @return Thousands separator point or null if not defined
     */
    public String getThousandsSep() {
        return thousandsSep;
    }

    /**
     * Thousands separator used
     * 
     * @param thousandsSep
     */
    public void setThousandsSep(String thousandsSep) {
        this.thousandsSep = thousandsSep;
    }
}
