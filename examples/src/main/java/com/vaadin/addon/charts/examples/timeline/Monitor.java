/*
@VaadinAddonLicenseForJavaFiles@
 */
package com.vaadin.addon.charts.examples.timeline;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

@SkipFromDemo
@SuppressWarnings({ "serial", "deprecation" })
public class Monitor extends AbstractVaadinChartExample {

    private IndexedContainer ds;

    private Timeline timeline;

    private Timer updateTimer = new Timer();

    public Monitor() {

        // Create the container
        ds = new IndexedContainer();

        // Add the required property ids, we use the default ones here
        ds.addContainerProperty(Timeline.PropertyId.TIMESTAMP, Date.class, null);
        ds.addContainerProperty(Timeline.PropertyId.VALUE, Float.class, 0f);

        // Create a timeline to display data
        timeline = new Timeline("Monitor");
        timeline.setImmediate(true);
        timeline.addGraphDataSource(ds);
        timeline.setSizeFull();
        timeline.setBrowserSelectionLock(false);
        timeline.setVerticalAxisRange(0f, 110f);
        timeline.setZoomLevelsVisible(false);
        timeline.setDateSelectVisible(false);
        timeline.setGraphOutlineThickness(4);
        timeline.setGraphFillColor(ds, new SolidColor(0, 30, 220, 0.128));
        timeline.setGraphOutlineColor(ds, SolidColor.RED);
        addComponent(timeline);

        HorizontalLayout controls = new HorizontalLayout();
        controls.setSpacing(true);
        addComponent(controls);

        ProgressIndicator pi = new ProgressIndicator();
        pi.setIndeterminate(true);
        pi.setPollingInterval(1000);
        pi.setHeight("0px");
        pi.setWidth("0px");
        controls.addComponent(pi);

        final Button start = new Button("Start updates",
                new Button.ClickListener() {
                    public void buttonClick(ClickEvent event) {
                        try {
                            updateTimer.cancel();
                        } catch (Exception e) {
                        }
                        updateTimer = new Timer();
                        updateTimer.scheduleAtFixedRate(new TimerTask() {
                            public void run() {
                                updateDataContainer();
                            }
                        }, new Date(), 1000L);
                    }
                });
        controls.addComponent(start);

        Button stop = new Button("Stop updates", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                updateTimer.cancel();
            }
        });
        controls.addComponent(stop);

        CheckBox lock = new CheckBox("Selection lock", false);
        lock.setImmediate(true);
        lock.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                timeline.setBrowserSelectionLock((Boolean) event.getProperty()
                        .getValue());
            }
        });
        controls.addComponent(lock);

        setExpandRatio(timeline, 1);
    }

    @SuppressWarnings("unchecked")
    private void updateDataContainer() {
        UI.getCurrent().access(new Runnable() {
            @Override
            public void run() {
                Date date = Calendar.getInstance().getTime();

                Random r = new Random();

                Item item = ds.addItem(date.getTime());
                item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                        date);
                item.getItemProperty(Timeline.PropertyId.VALUE).setValue(
                        r.nextFloat() * 100.0f);

                timeline.setCaption("Monitor (" + ds.size() + " points)");
            }
        });
    }

    @Override
    protected Component getChart() {
        return null;
    }

    protected void setup() {
    }
}
