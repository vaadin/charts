package com.vaadin.addon.charts.demoandtestapp.timeline;

import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.addon.timeline.Timeline.DateFormatInfo;
import com.vaadin.data.Container;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class DateFormatTest extends AbstractVaadinChartExample {

    private Timeline timeline;

    public DateFormatTest() {
        setSizeFull();

        timeline = new Timeline("Custom date formats");
        timeline.setSizeFull();
        Container.Indexed source = TestContainers.createSparseContainer();
        timeline.addGraphDataSource(source);

        // Use some custom date format
        String format = "EEE, MMM d, ''yy";
        DateFormatInfo formats = timeline.getDateFormats();

        formats.setDateSelectDisplaySimpleDateFormat(format);
        formats.setDateSelectEditSimpleDateFormat(format);
        formats.setLongDayFormat(format);
        formats.setLongMonthFormat(format);
        formats.setLongTimeFormat(format);
        formats.setLongYearFormat(format);
        formats.setShortDayFormat(format);
        formats.setShortMonthFormat(format);
        formats.setShortTimeFormat(format);
        formats.setShortYearFormat(format);

        addComponent(timeline);
    }

    @Override
    protected Component getChart() {
        return timeline;
    }
}
