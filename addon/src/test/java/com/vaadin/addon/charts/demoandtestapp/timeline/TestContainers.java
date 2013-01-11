/*
@VaadinAddonLicenseForJavaFiles@
 */
package com.vaadin.addon.charts.demoandtestapp.timeline;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.vaadin.addon.timeline.Timeline;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

@SuppressWarnings("serial")
public class TestContainers implements Serializable {

    /**
     * Creates a graph container with random data points
     */
    public static Container.Indexed createMarkerContainer() {

        // Create the container
        IndexedContainer container = new IndexedContainer();

        // Add the timestamp property
        container.addContainerProperty(Timeline.PropertyId.TIMESTAMP,
                Date.class, null);

        // Add the caption property
        container.addContainerProperty(Timeline.PropertyId.CAPTION,
                String.class, "");

        // Add the marker specific value property.
        container.addContainerProperty(Timeline.PropertyId.VALUE, String.class,
                "");

        // Lets add a month of random data
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 10, 28, 14, 36, 0);
        Date today = cal.getTime();
        cal.add(Calendar.MONTH, -1);

        while (cal.getTime().before(today)) {
            Item item = container.addItem(cal.getTime());

            // Add timestamp
            item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                    cal.getTime());

            // Add the caption
            item.getItemProperty(Timeline.PropertyId.CAPTION).setValue("E");

            // Add the value
            item.getItemProperty(Timeline.PropertyId.VALUE).setValue(
                    "My marker<br>This is a marker popup!");

            cal.add(Calendar.DAY_OF_MONTH, 7);
        }

        return container;
    }

    /**
     * Creates a graph container with random data points
     */
    public static Container.Indexed createEventContainer() {

        // Create the container
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty(Timeline.PropertyId.TIMESTAMP,
                Date.class, null);

        container.addContainerProperty(Timeline.PropertyId.CAPTION,
                String.class, "");

        // Lets add a month of random data
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 10, 28, 14, 36, 0);
        Date today = cal.getTime();
        cal.add(Calendar.MONTH, -1);

        while (cal.getTime().before(today)) {
            Item item = container.addItem(cal.getTime());

            // Add timestamp
            item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                    cal.getTime());

            // Add the caption
            item.getItemProperty(Timeline.PropertyId.CAPTION).setValue("E");

            cal.add(Calendar.DAY_OF_MONTH, 3);
        }

        return container;
    }

    /**
     * Creates a graph container with random data points
     */
    public static Container.Indexed createContainer() {

        // Create the container
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty(Timeline.PropertyId.TIMESTAMP,
                Date.class, null);
        container.addContainerProperty(Timeline.PropertyId.VALUE,
                Integer.class, 0);

        // Lets add a month of random data
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 10, 28, 14, 36, 0);
        Date today = cal.getTime();
        cal.add(Calendar.MONTH, -1);

        Random generator = new Random();

        while (cal.getTime().before(today)) {
            Item item = container.addItem(cal.getTime());
            item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                    cal.getTime());

            float val = generator.nextFloat();
            if (generator.nextBoolean()) {
                val *= -1;
            }

            item.getItemProperty(Timeline.PropertyId.VALUE).setValue(
                    Math.round(val));
            cal.add(Calendar.HOUR_OF_DAY, 1);
        }

        return container;
    }

    /**
     * Creates a container with a month of data with a data point each other day
     * 
     * @return
     */
    public static Container.Indexed createSparseContainer() {
        // Create the container
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty(Timeline.PropertyId.TIMESTAMP,
                Date.class, null);
        container.addContainerProperty(Timeline.PropertyId.VALUE,
                Integer.class, 0);

        // Lets add a month of random data
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 10, 28, 14, 36, 0);
        Date today = cal.getTime();
        cal.add(Calendar.MONTH, -1);
        Random generator = new Random();

        while (cal.getTime().before(today)) {
            Item item = container.addItem(cal.getTime());
            item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                    cal.getTime());

            float val = generator.nextFloat();

            item.getItemProperty(Timeline.PropertyId.VALUE).setValue(
                    Math.round(val));
            cal.add(Calendar.DAY_OF_MONTH, 2);
        }

        return container;
    }

    /**
     * Sanity check containers
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Container.Indexed createSanityCheckContainer(
            boolean onePoint, boolean multiple) {

        // Create the container
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty(Timeline.PropertyId.TIMESTAMP,
                Date.class, null);
        container.addContainerProperty(Timeline.PropertyId.VALUE,
                Integer.class, 0);

        // Add some unsorted dates
        Item item = container.addItem("item2");
        item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                new Date(110, 3, 27));
        item.getItemProperty(Timeline.PropertyId.VALUE).setValue(2);

        if (!onePoint) {
            item = container.addItem("item1");
            item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                    new Date(110, 3, 19));
            item.getItemProperty(Timeline.PropertyId.VALUE).setValue(1);

            if (multiple) {
                item = container.addItem("item1copy");
                item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                        new Date(110, 3, 19));
                item.getItemProperty(Timeline.PropertyId.VALUE).setValue(1);
            }

            item = container.addItem("item4");
            item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                    new Date(110, 3, 29));
            item.getItemProperty(Timeline.PropertyId.VALUE).setValue(4);

            if (multiple) {
                item = container.addItem("item4copy");
                item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                        new Date(110, 3, 29));
                item.getItemProperty(Timeline.PropertyId.VALUE).setValue(4);
            }

            item = container.addItem("item3");
            item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                    new Date(110, 3, 28));
            item.getItemProperty(Timeline.PropertyId.VALUE).setValue(3);
        }

        return container;
    }

    public static IndexedContainer createEmptyDefaultContainer() {
        // Create the container
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty(Timeline.PropertyId.TIMESTAMP,
                Date.class, null);
        container.addContainerProperty(Timeline.PropertyId.VALUE,
                Integer.class, 0);
        return container;
    }

    public static Indexed createSmoothContainer(long seed) {

        // Create the container
        IndexedContainer container = createEmptyDefaultContainer();

        // Lets add a month of random data
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 10, 28, 14, 36, 0);
        Date today = cal.getTime();
        cal.add(Calendar.MONTH, -1);

        Random generator = new Random(seed);

        float previous = 50;
        while (cal.getTime().before(today)) {
            Item item = container.addItem(cal.getTime());
            item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                    cal.getTime());

            float val = generator.nextFloat() * 100f;
            float diff = previous - val;
            val = previous + diff * 0.005f;

            if (val < 0 || val > 100) {
                val = 50;
            }

            item.getItemProperty(Timeline.PropertyId.VALUE).setValue(
                    Math.round(val));
            cal.add(Calendar.HOUR_OF_DAY, 1);
            previous = val;
        }

        return container;
    }

    public static Container.Indexed createCSVContainer(String csvFile) {
        SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty(Timeline.PropertyId.TIMESTAMP,
                Date.class, null);
        container.addContainerProperty(Timeline.PropertyId.VALUE,
                Integer.class, 0);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(
                    csvFile));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] dateValue = line.split(",");
                Date date = SIMPLE_DATE_FORMAT.parse(dateValue[1]);
                Item item = container.addItem(date);
                item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                        date);
                item.getItemProperty(Timeline.PropertyId.VALUE).setValue(
                        Integer.valueOf(dateValue[0]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException - " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException - " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("ParseException - " + e.getMessage());
        }

        return container;
    }

    public static Container.Indexed createPreEpochDatasource(long seed) {

        // Create the container
        IndexedContainer container = createEmptyDefaultContainer();

        // Lets add a month of random data
        Calendar cal = Calendar.getInstance();
        cal.set(2010, 10, 28, 14, 36, 0);
        Date today = cal.getTime();
        cal.add(Calendar.YEAR, -100);

        Random generator = new Random(seed);

        float previous = 50;
        while (cal.getTime().before(today)) {
            Item item = container.addItem(cal.getTime());
            item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(
                    cal.getTime());

            float val = generator.nextFloat() * 100f;
            float diff = previous - val;
            val = previous + diff * 0.005f;

            if (val < 0 || val > 100) {
                val = 50;
            }

            item.getItemProperty(Timeline.PropertyId.VALUE).setValue(
                    Math.round(val));
            cal.add(Calendar.DAY_OF_MONTH, 1);
            previous = val;
        }

        return container;
    }
}
