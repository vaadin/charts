package com.vaadin.addon.charts.demoandtestapp.timeline;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Component;

@SkipFromDemo
@SuppressWarnings({"serial", "deprecation"})
public class SparseDataTest extends AbstractVaadinChartExample {

    private Timeline timeline;

    public SparseDataTest() {
        setSizeFull();

        timeline = new Timeline();
        // t.setClientCacheEnabled(false);
        timeline.setSizeFull();
        try {
            IndexedContainer ds = getDatasource();
            timeline.addGraphDataSource(ds);
            timeline.setGraphOutlineColor(ds, new Color(0, 0, 0, 0));
            timeline.setGraphFillColor(ds, new Color(254, 0, 0, 100));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        timeline.setVisibleDateRange(new Date(110, 8, 14), new Date(110, 10, 8));

        addComponent(timeline);
    }

    @SuppressWarnings("unchecked")
    private IndexedContainer getDatasource() throws IOException {
        IndexedContainer ds = new IndexedContainer();
        ds.addContainerProperty(Timeline.PropertyId.TIMESTAMP, Date.class, null);
        ds.addContainerProperty(Timeline.PropertyId.VALUE, Float.class, 0f);

        InputStream stream = getClass().getResourceAsStream(
                "points2.txt");
        BufferedReader r = new BufferedReader(new InputStreamReader(stream));
        String line;
        while ((line = r.readLine()) != null) {
            if (line.equals("")) {
                break;
            }

            Date date = new Date(Long.parseLong(line));
            line = r.readLine();
            Float value = Float.parseFloat(line);

            Item item = ds.addItem(date);
            item.getItemProperty(Timeline.PropertyId.TIMESTAMP).setValue(date);
            item.getItemProperty(Timeline.PropertyId.VALUE).setValue(value);
        }

        return ds;
    }

    @Override
    protected Component getChart() {
        return null;
    }

    @Override
    protected void setup() {
    }
}
