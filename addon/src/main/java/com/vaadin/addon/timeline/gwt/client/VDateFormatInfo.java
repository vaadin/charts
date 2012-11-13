package com.vaadin.addon.timeline.gwt.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.vaadin.client.UIDL;

public class VDateFormatInfo {

    public static final Long SECOND = 1000L;
    public static final Long MINUTE = 60000L;
    public static final Long HOUR = 3600000L;
    public static final Long DAY = 86400000L;
    public static final Long WEEK = 604800000L;
    public static final Long MONTH = 2629743830L;
    public static final Long YEAR = 31556926000L;

    private DateTimeFormat yearFormatShort = DateTimeFormat.getFormat("''yy");
    private DateTimeFormat yearFormatLong = DateTimeFormat
            .getFormat(PredefinedFormat.YEAR);

    private DateTimeFormat monthFormatShort = DateTimeFormat
            .getFormat(PredefinedFormat.YEAR_MONTH_ABBR);
    private DateTimeFormat monthFormatLong = DateTimeFormat
            .getFormat(PredefinedFormat.YEAR_MONTH);

    private DateTimeFormat dayFormatShort = DateTimeFormat
            .getFormat(PredefinedFormat.YEAR_MONTH_ABBR_DAY);
    private DateTimeFormat dayFormatLong = DateTimeFormat
            .getFormat(PredefinedFormat.YEAR_MONTH_DAY);

    private DateTimeFormat timeFormatShort = DateTimeFormat
            .getFormat(PredefinedFormat.HOUR_MINUTE);
    private DateTimeFormat timeFormatLong = DateTimeFormat
            .getFormat(PredefinedFormat.HOUR_MINUTE_SECOND);

    private DateTimeFormat displayFormat = DateTimeFormat.getFormat("MMM d, y");
    private DateTimeFormat editFormat = DateTimeFormat.getFormat("dd/MM/yyyy");

    public DateTimeFormat getLongDateFormatter(long unitTime) {
        if (unitTime < DAY) {
            return timeFormatLong;
        } else if (unitTime < MONTH) {
            return dayFormatLong;
        } else if (unitTime < YEAR) {
            return monthFormatLong;
        } else {
            return yearFormatLong;
        }
    }

    public DateTimeFormat getShortDateFormatter(long unitTime) {
        if (unitTime < DAY) {
            return timeFormatShort;
        } else if (unitTime < MONTH) {
            return dayFormatShort;
        } else if (unitTime < YEAR) {
            return monthFormatShort;
        } else {
            return yearFormatShort;
        }
    }

    public DateTimeFormat getDisplayFormat() {
        return displayFormat;
    }

    public DateTimeFormat getEditFormat() {
        return editFormat;
    }

    public void setDateFormatInfo(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_DATE_FORMATS)) {
            String[] formats = uidl.getStringAttribute(
                    TimelineConstants.ATTRIBUTE_DATE_FORMATS).split(
                            "\\|");
            displayFormat = DateTimeFormat.getFormat(formats[0]);
            editFormat = DateTimeFormat.getFormat(formats[1]);
            yearFormatShort = DateTimeFormat.getFormat(formats[2]);
            yearFormatLong = DateTimeFormat.getFormat(formats[3]);
            monthFormatShort = DateTimeFormat.getFormat(formats[4]);
            monthFormatLong = DateTimeFormat.getFormat(formats[5]);
            dayFormatShort = DateTimeFormat.getFormat(formats[6]);
            dayFormatLong = DateTimeFormat.getFormat(formats[7]);
            timeFormatShort = DateTimeFormat.getFormat(formats[8]);
            timeFormatLong = DateTimeFormat.getFormat(formats[9]);
        }
    }

}
