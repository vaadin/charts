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
     * Sets the name of the months in the current language. Corresponds to the
     * <code>%B</code> format in <code>Highcharts.dateFormat()</code>. Defaults
     * to <code>{"January", "February", "March", "April", "May", "June", "July",
     * "August", "September", "October", "November", "December"}</code>
     * 
     * @param months
     *            An array containing the names of the months.
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
     * Sets the name of the days in the current language. Defaults to
     * <code>{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
     * "Friday", "Saturday"}</code>.
     * 
     * @param weekdays
     *            An array containing the weekday names.
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
     * Sets the short name for the months in the current language. Defaults
     * <code>{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
     * "Oct", "Nov", "Dec"}</code>.
     * 
     * @param shortMonths
     *            An array containing the abbreviated names of the months.
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
     * Sets numeric symbols for the current language. Defaults to
     * <code>{"k", "M", "G", "T", "P", "E"}</code>.
     * 
     * @param numericSymbols
     *            An array containing the numeric symbols.
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
     * Sets the title of the zoom resetting button. Defaults to "Reset zoom".
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
     * Title of the zoom resetting button. Defaults to "Reset zoom level 1:1".
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
