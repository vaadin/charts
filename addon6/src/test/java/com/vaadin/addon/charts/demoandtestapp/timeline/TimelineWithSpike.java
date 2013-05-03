package com.vaadin.addon.charts.demoandtestapp.timeline;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.addon.timeline.Timeline.ReducingAlgorithm;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;

@SkipFromDemo
@SuppressWarnings({ "serial", "deprecation" })
public class TimelineWithSpike extends AbstractVaadinChartExample {

    private Timeline timeline;

    public TimelineWithSpike() {

        boolean initiallySmart = true;

        final CheckBox checkBox = new CheckBox("Use smart reducing algorithm");
        checkBox.setValue(initiallySmart);
        checkBox.addListener(new ValueChangeListener() {

            @Override
            public void valueChange(ValueChangeEvent event) {
                Boolean smart = (Boolean) checkBox.getValue();
                createTimeline(smart);
            }
        });
        checkBox.setImmediate(true);
        addComponent(checkBox);

        createTimeline(initiallySmart);

    }

    private void createTimeline(boolean smart) {
        if (timeline != null) {
            removeComponent(timeline);
        }

        timeline = new Timeline("My Timeline");
        timeline.setReducingAlgorithm(smart ? ReducingAlgorithm.MODIFIED_RAMER_DOUGLAS_PEUCKER
                : ReducingAlgorithm.SIMPLE);
        timeline.setSizeFull();

        Calendar cal = new GregorianCalendar();
        cal.set(2010, 1, 1);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Random random = new Random(1234567L);

        Indexed graph1 = createTimelineGraphContainer();
        for (int days = 0; days < 8000; days++) {
            if (days == 4000 && days < 4020) {
                addPoint(graph1, cal.getTime(), 2382776f);
            } else {
                addPoint(graph1, cal.getTime(),
                        random.nextFloat() * 100f + 100f);
            }
            cal.add(Calendar.DAY_OF_YEAR, 1);
        }

        timeline.addGraphDataSource(graph1);

        // To test #11176
        timeline.setVerticalAxisNumberFormat("000.00");

        addComponent(timeline);
        setExpandRatio(timeline, 1);
    }

    private Indexed createTimelineGraphContainer() {
        IndexedContainer ic = new IndexedContainer();
        ic.addContainerProperty(Timeline.PropertyId.TIMESTAMP, Date.class, null);
        ic.addContainerProperty(Timeline.PropertyId.VALUE, Float.class, 0);
        return ic;
    }

    @SuppressWarnings("unchecked")
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
