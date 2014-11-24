/*
@VaadinAddonLicenseForJavaFiles@
 */
package com.vaadin.addon.charts.demoandtestapp.timeline;

import java.awt.Color;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.addon.timeline.Timeline.EventButtonClickEvent;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

@SkipFromDemo
@SuppressWarnings({ "serial", "deprecation", "unchecked" })
public class MyTimelineDemo extends AbstractVaadinChartExample {

    Container.Indexed firstDataSource;
    List<Container.Indexed> datasourcesList = new ArrayList<Container.Indexed>();

    private Timeline timeline;

    public MyTimelineDemo() {

        timeline = new Timeline("My graph");
        timeline.setSizeFull();
        timeline.setVerticalAxisRange(-1f, 2f);
        timeline.setZoomLevelsVisible(false);
        timeline.setDateSelectVisible(false);

        // Create the data sources
        firstDataSource = createGraphDataSource();
        datasourcesList.add(firstDataSource);
        final Container.Indexed markerDataSource = createMarkerDataSource();
        final Container.Indexed eventDataSource = createEventDataSource();

        // Add our data sources
        timeline.addGraphDataSource(firstDataSource,
                Timeline.PropertyId.TIMESTAMP, Timeline.PropertyId.VALUE);

        // Markers and events
        timeline.setMarkerDataSource(markerDataSource,
                Timeline.PropertyId.TIMESTAMP, Timeline.PropertyId.CAPTION,
                Timeline.PropertyId.VALUE);
        timeline.setEventDataSource(eventDataSource,
                Timeline.PropertyId.TIMESTAMP, Timeline.PropertyId.CAPTION);

        // Set the caption of the graph
        timeline.setGraphLegend(firstDataSource, "Our cool graph");

        // Set the color of the graph
        timeline.setGraphOutlineColor(firstDataSource, Color.RED);

        // Set the fill color of the graph
        timeline.setGraphFillColor(firstDataSource, new Color(255, 0, 0, 128));

        // Set the width of the graph
        timeline.setGraphOutlineThickness(1);

        // Set the color of the browser graph
        timeline.setBrowserOutlineColor(firstDataSource, Color.BLACK);

        // Set the fill color of the graph
        timeline.setBrowserFillColor(firstDataSource, new Color(0, 0, 0, 128));

        // Add some zoom levels
        timeline.addZoomLevel("Day", 86400000L);
        timeline.addZoomLevel("Week", 7 * 86400000L);
        timeline.addZoomLevel("Month", 2629743830L);

        // Listen to click events from events
        timeline.addListener(new Timeline.EventClickListener() {
            @Override
            public void eventClick(EventButtonClickEvent event) {
                Item item = eventDataSource.getItem(event.getItemIds()
                        .iterator().next());
                Date sunday = (Date) item.getItemProperty(
                        Timeline.PropertyId.TIMESTAMP).getValue();
                SimpleDateFormat formatter = new SimpleDateFormat(
                        "EEE, MMM d, ''yy");

                Notification.show(formatter.format(sunday));
            }
        });

        addComponent(timeline);

        HorizontalLayout addDateForm = new HorizontalLayout();
        final DateField dateField = new DateField();
        dateField.setImmediate(true);
        addDateForm.addComponent(dateField);
        final TextField valueField = new TextField();
        valueField.setImmediate(true);
        addDateForm.addComponent(valueField);
        Button addBtn = new Button("Add", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {

                java.util.Date d = dateField.getValue();
                Date date = new Date(d.getTime());
                float value = Float.valueOf(valueField.getValue().toString());

                // Create a point in time
                Item item = firstDataSource.addItem(date.getTime());

                if (item == null) {
                    item = firstDataSource.getItem(date.getTime());
                }

                // Set the timestamp property
                item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                        date);

                // Set the value property
                item.getItemProperty(Timeline.PropertyId.VALUE).setValue(value);
            }
        });
        addDateForm.addComponent(addBtn);
        addComponent(addDateForm);

        Button addGraphDataSource = new Button("Add graph data source",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        Container.Indexed ds = createGraphDataSource();
                        datasourcesList.add(ds);
                        timeline.addGraphDataSource(ds);
                        timeline.setGraphFillColor(ds, Color.BLACK);
                    }
                });
        addComponent(addGraphDataSource);

        Button removeGraphDataSource = new Button("Remove graph data source",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        if (datasourcesList.size() > 1) {
                            Container.Indexed ds = datasourcesList
                                    .get(datasourcesList.size() - 1);
                            timeline.removeGraphDataSource(ds);
                            datasourcesList.remove(ds);
                        }
                    }
                });
        addComponent(removeGraphDataSource);

        CheckBox stacked = new CheckBox("Stacked graphs", false);
        stacked.setImmediate(true);
        stacked.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                timeline.setGraphStacking((Boolean) event.getProperty()
                        .getValue());
            }
        });
        addComponent(stacked);

        CheckBox lock = new CheckBox("Selection lock", true);
        lock.setImmediate(true);
        lock.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                timeline.setBrowserSelectionLock((Boolean) event.getProperty()
                        .getValue());
            }
        });
        addComponent(lock);

        setExpandRatio(timeline, 1);
    }

    /**
     * Creates a graph container with a month of random data
     */
    public Container.Indexed createGraphDataSource() {

        // Create the container
        Container.Indexed container = new IndexedContainer();

        // Add the required property ids, we use the default ones here
        container.addContainerProperty(Timeline.PropertyId.TIMESTAMP,
                Date.class, null);
        container.addContainerProperty(Timeline.PropertyId.VALUE, Float.class,
                0f);

        // Add some random data to the container
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date today = new Date((new java.util.Date().getTime()));
        Random generator = new Random();

        while (cal.getTime().before(today)) {
            // Create a point in time
            Item item = container.addItem(cal.getTime());

            // Set the timestamp property
            item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                    new Date(cal.getTime().getTime()));

            // Set the value property
            float value = generator.nextFloat();
            item.getItemProperty(Timeline.PropertyId.VALUE).setValue(value);

            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        return container;
    }

    /**
     * Creates a marker container with a marker for each seven days
     */
    public Container.Indexed createMarkerDataSource() {

        // Create the container
        Container.Indexed container = new IndexedContainer();

        // Add the required property ids, we use the default ones here
        container.addContainerProperty(Timeline.PropertyId.TIMESTAMP,
                Date.class, null);
        container.addContainerProperty(Timeline.PropertyId.CAPTION,
                String.class, "Our marker symbol");
        container.addContainerProperty(Timeline.PropertyId.VALUE, String.class,
                "Our description");

        // Add a marker for every seven days
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date today = new Date((new java.util.Date().getTime()));
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d, ''yy");
        while (cal.getTime().before(today)) {
            // Create a point in time
            Item item = container.addItem(cal.getTime());

            // Set the timestamp property
            item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                    new Date(cal.getTime().getTime()));

            // Set the caption property
            item.getItemProperty(Timeline.PropertyId.CAPTION).setValue("M");

            // Set the value property
            item.getItemProperty(Timeline.PropertyId.VALUE).setValue(
                    "Today is " + formatter.format(cal.getTime()));

            cal.add(Calendar.DAY_OF_MONTH, 7);
        }

        return container;
    }

    /**
     * Creates a event container with a marker for each sunday
     */
    public Container.Indexed createEventDataSource() {

        // Create the container
        Container.Indexed container = new IndexedContainer();

        // Add the required property ids, we use the default ones here
        container.addContainerProperty(Timeline.PropertyId.TIMESTAMP,
                Date.class, null);
        container.addContainerProperty(Timeline.PropertyId.CAPTION,
                String.class, "Our marker symbol");

        // Add a marker for every seven days
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date today = new Date((new java.util.Date().getTime()));
        while (cal.getTime().before(today)) {
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                // Create a point in time
                Item item = container.addItem(cal.getTime());

                // Set the timestamp property
                item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                        new Date(cal.getTime().getTime()));

                // Set the caption property
                item.getItemProperty(Timeline.PropertyId.CAPTION).setValue(
                        "Sunday");
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        return container;
    }

    @Override
    protected Component getChart() {
        return null;
    }

    @Override
    protected void setup() {
    }
}
