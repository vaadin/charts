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

    private static final long serialVersionUID = 20141120;

    private String contextButtonTitle;
    private String decimalPoint;
    private String downloadJPEG;
    private String downloadPDF;
    private String downloadPNG;
    private String downloadSVG;
    private String drillUpText;
    private String loading;
    private String[] months;
    private String noData;
    private String[] numericSymbols;
    private String printChart;
    private String resetZoom;
    private String resetZoomTitle;
    private String[] shortMonths;
    private String thousandsSep;
    private String[] weekdays;

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
     *            New loading text.
     */
    public void setLoading(String loading) {
        this.loading = loading;
    }

    /**
     * Returns the decimal point.
     * 
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
     *            Decimal point symbol.
     */
    public void setDecimalPoint(String decimalPoint) {
        this.decimalPoint = decimalPoint;
    }

    /**
     * Returns the thousands separator.
     * 
     * @see #setThousandsSep(String)
     * @return The thousands separator point or null if not defined
     */
    public String getThousandsSep() {
        return thousandsSep;
    }

    /**
     * Sets the thousands separator to use
     * 
     * @param thousandsSep
     *            New thousand separator.
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
     * Returns current month names.
     * 
     * @see #setMonths(String[])
     * @return Current month names.
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
     * Returns current weekday names.
     * 
     * @see #setWeekdays(String[])
     * @return Weekday names.
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
     * Returns the array with short month names.
     * 
     * @see #setShortMonths(String[])
     * @return Array with short month names.
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
     * Returns numeric symbols.
     * 
     * @see #setNumericSymbols(String[])
     * @return An array with numeric symbols.
     */
    public String[] getNumericSymbols() {
        return numericSymbols;
    }

    /**
     * Sets the title of the zoom resetting button. Defaults to "Reset zoom".
     * 
     * @param resetZoom
     *            Tile for the zoom-resetting button.
     */
    public void setResetZoom(String resetZoom) {
        this.resetZoom = resetZoom;
    }

    /**
     * Returns current title for the zoom-resetting button.
     * 
     * @see #setResetZoom(String)
     * @return Title for zoom-resetting button.
     */
    public String getResetZoom() {
        return resetZoom;
    }

    /**
     * Title of the zoom resetting button. Defaults to "Reset zoom level 1:1".
     * 
     * @param resetZoomTitle
     *            New title.
     */
    public void setResetZoomTitle(String resetZoomTitle) {
        this.resetZoomTitle = resetZoomTitle;
    }

    /**
     * Returns the title for zoom reset button.
     * 
     * @see #setResetZoomTitle(String)
     * @return The title for zoom reset button.
     */
    public String getResetZoomTitle() {
        return resetZoomTitle;
    }

    /**
     * Returns the tooltip title for the context menu.
     * 
     * @return The tooltip title for the context menu.
     */
    public String getContextButtonTitle() {
        return contextButtonTitle;
    }

    /**
     * Sets the tooltip title for the context menu.
     * 
     * @param contextButtonTitle
     *            New tooltip.
     */
    public void setContextButtonTitle(String contextButtonTitle) {
        this.contextButtonTitle = contextButtonTitle;
    }

    /**
     * The text for the JPEG download menu item.
     * 
     * @return The text for the JPEG download menu item.
     */
    public String getDownloadJPEG() {
        return downloadJPEG;
    }

    /**
     * Sets the text for download JPEG.
     * 
     * @param downloadJPEG
     *            New text.
     */
    public void setDownloadJPEG(String downloadJPEG) {
        this.downloadJPEG = downloadJPEG;
    }

    /**
     * The text for the PDF download menu item.
     * 
     * @return The text for the PDF download menu item.
     */
    public String getDownloadPDF() {
        return downloadPDF;
    }

    /**
     * Sets the text for download PDF.
     * 
     * @param downloadPDF
     *            New text.
     */
    public void setDownloadPDF(String downloadPDF) {
        this.downloadPDF = downloadPDF;
    }

    /**
     * Returns the text for downloading PNG.
     * 
     * @return Text for downloading PNG.
     */
    public String getDownloadPNG() {
        return downloadPNG;
    }

    /**
     * Sets the text for downloading PNG.
     * 
     * @param downloadPNG
     *            New text.
     */
    public void setDownloadPNG(String downloadPNG) {
        this.downloadPNG = downloadPNG;
    }

    /**
     * Returns the text for downloading SVG.
     * 
     * @return Text for downloading SVG.
     */
    public String getDownloadSVG() {
        return downloadSVG;
    }

    /**
     * Sets new text for downloading SVG.
     * 
     * @param downloadSVG
     *            New text.
     */
    public void setDownloadSVG(String downloadSVG) {
        this.downloadSVG = downloadSVG;
    }

    /**
     * Gets the text for the button that appears when drilling down.
     * 
     * @return Text for the button for drilling up.
     */
    public String getDrillUpText() {
        return drillUpText;
    }

    /**
     * Sets the text for the button that appears when drilling down, linking
     * back to the parent series. The parent series' name is inserted for
     * <tt>{series.name}</tt>. Defaults to <tt>Back to {series.name}</tt>.
     * 
     * @param drillUpText
     *            New text.
     */
    public void setDrillUpText(String drillUpText) {
        this.drillUpText = drillUpText;
    }

    /**
     * Returns text to show when there is no data.
     * 
     * @return Text to show when there is no data.
     */
    public String getNoData() {
        return noData;
    }

    /**
     * Sets the text to show when there is no data.
     * 
     * @param noData
     *            New text.
     */
    public void setNoData(String noData) {
        this.noData = noData;
    }

    /**
     * Returns the menu item text for printing the chart.
     * 
     * @return The text for printing the chart.
     */
    public String getPrintChart() {
        return printChart;
    }

    /**
     * Sets the text of a menu item to print the chart.
     * 
     * @param printChart
     *            New text.
     */
    public void setPrintChart(String printChart) {
        this.printChart = printChart;
    }
}
