package com.vaadin.addon.charts.demoandtestapp.timeline;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;

import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.timeline.sources.VaadinForumDataSource;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.addon.timeline.Timeline.ChartMode;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Component;

@SuppressWarnings({"serial", "deprecation"})
public class ForumTrends extends AbstractVaadinChartExample {
    public static final float GRAPH_MAX_LIMIT = 130f;
    public static final float GRAPH_MIN_LIMIT = 0f;

    private Timeline timeline;

    private IndexedContainer posts;
    private IndexedContainer newThreads;
    private IndexedContainer vaadinPosts;
    private IndexedContainer vaadinThreads;

    public ForumTrends() {
        setSpacing(true);
        initializeContainers();
    }

    private void initializeContainers() {
        createContainers();
        // Reads actual forum data from a dynamically updated text file.
        VaadinForumDataSource datasource = new VaadinForumDataSource();
        // Fills each container with items consisting of a floating point value
        // and a timestamp.
        datasource.fillPostsContainer(posts);
        datasource.fillNewThreadsContainer(newThreads);
        datasource.fillVaadinPostsContainer(vaadinPosts);
        datasource.fillVaadinThreadsContainer(vaadinThreads);
    }

    private void createContainers() {
        posts = createIndexedContainer();
        newThreads = createIndexedContainer();
        vaadinPosts = createIndexedContainer();
        vaadinThreads = createIndexedContainer();
    }

    /**
     * Creates an indexed container with two properties: value and timestamp.
     * 
     * @return a container with "value, timestamp" items.
     */
    private IndexedContainer createIndexedContainer() {
        IndexedContainer container = new IndexedContainer();
        container.addContainerProperty(Timeline.PropertyId.VALUE, Float.class,
                new Float(0));
        container.addContainerProperty(Timeline.PropertyId.TIMESTAMP,
                java.util.Date.class, null);
        return container;
    }

    private Component createTimeline() {
        timeline = new Timeline("Forums");
        timeline.setSizeFull();
        timeline.setId("timeline");
        timeline.setUniformBarThicknessEnabled(true);

        timeline.setChartMode(ChartMode.BAR);
        timeline.setVerticalAxisRange(GRAPH_MIN_LIMIT, GRAPH_MAX_LIMIT);

        timeline.setVerticalGridLines(0f, 10f, 20f, 30f, 50f, 80f, 120f, 150f,
                200f);

        // Set the zoom levels
        timeline.addZoomLevel("1d", 86400000L);
        timeline.addZoomLevel("5d", 5 * 86400000L);
        timeline.addZoomLevel("1m", 2629743830L);
        timeline.addZoomLevel("3m", 3 * 2629743830L);
        timeline.addZoomLevel("6m", 6 * 2629743830L);
        timeline.addZoomLevel("1y", 31556926000L);
        timeline.addZoomLevel("5y", 5 * 31556926000L);
        timeline.addZoomLevel("10y", 10 * 31556926000L);
        // 100 years should suffice here ;=)
        timeline.addZoomLevel("Max", 100 * 31556926000L);

        // Add data sources
        timeline.addGraphDataSource(posts);
        timeline.setGraphLegend(posts, "Total Posts");
        timeline.setGraphOutlineColor(posts, new Color(0x00, 0xb4, 0xf0));
        timeline.setGraphFillColor(posts, null);
        timeline.setVerticalAxisLegendUnit(posts, "posts");

        timeline.addGraphDataSource(newThreads);
        timeline.setGraphLegend(newThreads, "New threads");
        timeline.setGraphOutlineColor(newThreads, new Color(0xee, 0x7c, 0x08));
        timeline.setGraphFillColor(newThreads, null);
        timeline.setVerticalAxisLegendUnit(newThreads, "threads");

        timeline.addGraphDataSource(vaadinPosts);
        timeline.setGraphLegend(vaadinPosts, "Vaadin Posts");
        timeline.setGraphOutlineColor(vaadinPosts, new Color(0xe6, 0x1e, 0x6e));
        timeline.setGraphFillColor(vaadinPosts, null);
        timeline.setVerticalAxisLegendUnit(vaadinPosts, "posts");

        timeline.addGraphDataSource(vaadinThreads);
        timeline.setGraphLegend(vaadinThreads, "Vaadin threads");
        timeline.setGraphOutlineColor(vaadinThreads,
                new Color(0x40, 0xb5, 0x27));
        timeline.setGraphFillColor(vaadinThreads, null);
        timeline.setVerticalAxisLegendUnit(vaadinThreads, "threads");

        // Set the date range
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        timeline.setVisibleDateRange(cal.getTime(), new Date());

        return timeline;
    }

    @Override
    protected Component getChart() {
        createTimeline();
        return timeline;
    }
}
