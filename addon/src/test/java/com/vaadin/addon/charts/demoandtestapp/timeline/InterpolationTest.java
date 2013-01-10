/*
@VaadinAddonLicenseForJavaFiles@
 */
package com.vaadin.addon.charts.demoandtestapp.timeline;

import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.data.Container;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class InterpolationTest extends AbstractVaadinChartExample {

    private Timeline timeline;

    public InterpolationTest() {
        setSizeFull();

        timeline = new Timeline("Sparse Data");
        timeline.setSizeFull();
        timeline.setVerticalAxisRange(0F, 5F);

        // Add some zoom levels
        timeline.addZoomLevel("Day", 86400000L);
        timeline.addZoomLevel("Week", 7 * 86400000L);
        timeline.addZoomLevel("Month", 2629743830L);

        Container.Indexed source = TestContainers.createSparseContainer();
        timeline.addGraphDataSource(source);

        addComponent(timeline);
    }

    @Override
    protected Component getChart() {
        return timeline;
    }
}
