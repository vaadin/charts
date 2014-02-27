/*
@VaadinAddonLicenseForJavaFiles@
 */
package com.vaadin.addon.charts.demoandtestapp.timeline;

import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.addon.timeline.Timeline.EventButtonClickEvent;
import com.vaadin.data.Container;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalSplitPanel;

@SkipFromDemo
@SuppressWarnings({ "serial", "deprecation" })
public class ResizeTest extends AbstractVaadinChartExample {

    private VerticalSplitPanel panel;

    public ResizeTest() {

        panel = new VerticalSplitPanel();
        panel.setHeight("800px");

        HorizontalSplitPanel panel2 = new HorizontalSplitPanel();
        panel2.setWidth("100%");

        Timeline timeline = new Timeline("Test");
        timeline.setWidth("100%");
        timeline.setHeight("100%");
        timeline.setVerticalAxisRange(-4F, 10F);

        Container.Indexed source = TestContainers.createContainer();
        timeline.addGraphDataSource(source);

        timeline.setMarkerDataSource(TestContainers.createMarkerContainer());
        timeline.setEventDataSource(TestContainers.createEventContainer());

        timeline.addListener(new Timeline.EventClickListener() {
            @Override
            public void eventClick(EventButtonClickEvent event) {
                Notification.show("Event clicked!");
            }
        });

        panel2.addComponent(timeline);
        panel.addComponent(panel2);
        addComponent(panel);
    }

    @Override
    protected Component getChart() {
        return null;
    }

    @Override
    protected void setup() {
    }

}
