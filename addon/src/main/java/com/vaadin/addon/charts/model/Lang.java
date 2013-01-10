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
    private String[] months;
    private String[] weekdays;
    private String[] shortMonths;
    private String[] numericSymbols;
    private String resetZoom;
    private String resetZoomTitle;

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

    /**
     * An array containing the months names. Corresponds to the %B format in
     * Highcharts.dateFormat(). Defaults to {"January", "February", "March",
     * "April", "May", "June", "July", "August", "September", "October",
     * "November", "December"}
     * 
     * @param months
     */
    public void setMonths(String[] months) {
        this.months = months;
    }

    /**
     * @see #setMonths(String[])
     * @return
     */
    public String[] getMonths() {
        return months;
    }

    /**
     * An array containing the weekday names. Defaults to {"Sunday", "Monday",
     * "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.
     * 
     * @param weekdays
     */
    public void setWeekdays(String[] weekdays) {
        this.weekdays = weekdays;
    }

    /**
     * @see #setWeekdays(String[])
     * @return
     */
    public String[] getWeekdays() {
        return weekdays;
    }

    /**
     * An array containing the months' short names. Defaults {"Jan", "Feb",
     * "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}
     * 
     * @param shortMonths
     */
    public void setShortMonths(String[] shortMonths) {
        this.shortMonths = shortMonths;
    }

    /**
     * @see #setShortMonths(String[])
     * @return
     */
    public String[] getShortMonths() {
        return shortMonths;
    }

    /**
     * An array containing the numeric symbols. Defaults to {"k", "M", "G", "T",
     * "P", "E"}
     * 
     * @param shortMonths
     */
    public void setNumericSymbols(String[] numericSymbols) {
        this.numericSymbols = numericSymbols;
    }

    /**
     * @see #setNumericSymbols(String[])
     * @return
     */
    public String[] getNumericSymbols() {
        return numericSymbols;
    }

    /**
     * Caption of the zoom reseting button. Defaults to "Reset zoom"
     * 
     * @param resetZoom
     */
    public void setResetZoom(String resetZoom) {
        this.resetZoom = resetZoom;
    }

    /**
     * @see #setResetZoom(String)
     * @return
     */
    public String getResetZoom() {
        return resetZoom;
    }

    /**
     * Title of reset zoom button. Defaults to Reset zoom level 1:1.
     * 
     * @param resetZoomTitle
     */
    public void setResetZoomTitle(String resetZoomTitle) {
        this.resetZoomTitle = resetZoomTitle;
    }

    /**
     * @see #setResetZoomTitle(String)
     * @return
     */
    public String getResetZoomTitle() {
        return resetZoomTitle;
    }
}
