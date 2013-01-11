package com.vaadin.addon.charts.demoandtestapp.timeline;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Component;

@SkipFromDemo
@SuppressWarnings("serial")
public class TimelineHelloWorld extends AbstractVaadinChartExample {

    private Timeline timeline;

    public TimelineHelloWorld() {

        timeline = new Timeline("My Timeline");
        timeline.setSizeFull();

        Calendar cal = new GregorianCalendar();
        cal.set(2010, 1, 1);
        Random random = new Random(1234567L);

        Indexed graph1 = createTimelineGraphContainer();
        for (int days = 0; days < 356; days++) {
            addPoint(graph1, cal.getTime(), random.nextFloat() * 50f);
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }

        timeline.addGraphDataSource(graph1);

        addComponent(timeline);
    }

    private Indexed createTimelineGraphContainer() {
        IndexedContainer ic = new IndexedContainer();
        ic.addContainerProperty(Timeline.PropertyId.TIMESTAMP, Date.class, null);
        ic.addContainerProperty(Timeline.PropertyId.VALUE, Float.class, 0);
        return ic;
    }

    private void addPoint(Indexed graph, Date date, Float value) {
        Item item = graph.getItem(graph.addItem());
        item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(date);
        item.getItemProperty(Timeline.PropertyId.VALUE).setValue(value);
    }

    @Override
    protected Component getChart() {
        return null;
    }

    @Override
    protected void setup() {
    }

}
