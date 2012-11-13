package com.vaadin.addon.charts.model;

/**
 * For a datetime axis, the scale will automatically adjust to the appropriate
 * unit. This member gives the default string representations used for each
 * unit. For an overview of the replacement codes, see dateFormat. Defaults to:
 * 
 * { second: '%H:%M:%S', minute: '%H:%M', hour: '%H:%M', day: '%e. %b', week:
 * '%e. %b', month: '%b \'%y', year: '%Y' }
 */
public class DateTimeLabelFormats extends AbstractConfigurationObject {

    private String second;
    private String minute;
    private String hour;
    private String day;
    private String week;
    private String month;
    private String year;

    /**
     * Default constructor
     */
    public DateTimeLabelFormats() {

    }

    /**
     * Constructor with given format strings for month and year
     * 
     * @param month
     * @param year
     */
    public DateTimeLabelFormats(String month, String year) {
        this.month = month;
        this.year = year;
    }

    /**
     * @see #setSecond(String)
     * @return
     */
    public String getSecond() {
        return second;
    }

    /**
     * Set format String for second
     * 
     * @param second
     */
    public void setSecond(String second) {
        this.second = second;
    }

    /**
     * @see #setMinute(String)
     * @return
     */
    public String getMinute() {
        return minute;
    }

    /**
     * Set format String for minute
     * 
     * @param minute
     */
    public void setMinute(String minute) {
        this.minute = minute;
    }

    /**
     * @see #setHour(String)
     * @return
     */
    public String getHour() {
        return hour;
    }

    /**
     * Set format String for hour
     * 
     * @param hour
     */
    public void setHour(String hour) {
        this.hour = hour;
    }

    /**
     * @see #setDay(String)
     * @return
     */
    public String getDay() {
        return day;
    }

    /**
     * Set format String for day
     * 
     * @param day
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * @see #setWeek(String)
     * @return
     */
    public String getWeek() {
        return week;
    }

    /**
     * Set format String for week
     * 
     * @param week
     */
    public void setWeek(String week) {
        this.week = week;
    }

    /**
     * @see #setMonth(String)
     * @return
     */
    public String getMonth() {
        return month;
    }

    /**
     * Set format String for month
     * 
     * @param month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @see #setYear(String)
     * @return
     */
    public String getYear() {
        return year;
    }

    /**
     * Set format String for year
     * 
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }

}
