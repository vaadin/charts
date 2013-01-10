/*
@VaadinAddonLicenseForJavaFiles@
 */
package com.vaadin.addon.charts.demoandtestapp.timeline;

import java.util.Date;

import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.data.Container;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;

@SuppressWarnings("serial")
public class SanityCheck extends AbstractVaadinChartExample {

    private GridLayout grid;

    public SanityCheck() {

        grid = new GridLayout(3, 2);
        grid.setSpacing(true);

        addComponent(grid);

        Timeline timeline1 = new Timeline("Normal");
        Container.Indexed t1cont = TestContainers.createSanityCheckContainer(
                false, false);
        ((Container.Sortable) t1cont).sort(
                new Object[] { Timeline.PropertyId.TIMESTAMP },
                new boolean[] { true });
        timeline1.addGraphDataSource(t1cont);
        grid.addComponent(timeline1);

        Timeline timeline2 = new Timeline("One Point");
        Container.Indexed t2cont = TestContainers.createSanityCheckContainer(
                true, false);
        ((Container.Sortable) t2cont).sort(
                new Object[] { Timeline.PropertyId.TIMESTAMP },
                new boolean[] { true });
        timeline2.addGraphDataSource(t2cont);
        grid.addComponent(timeline2);

        Timeline timeline3 = new Timeline("No Point");
        Container.Indexed t3cont = new IndexedContainer();
        t3cont.addContainerProperty(Timeline.PropertyId.TIMESTAMP, Date.class,
                null);
        t3cont.addContainerProperty(Timeline.PropertyId.VALUE, Float.class,
                null);

        ((Container.Sortable) t1cont).sort(
                new Object[] { Timeline.PropertyId.TIMESTAMP },
                new boolean[] { true });
        timeline3.addGraphDataSource(t3cont);
        grid.addComponent(timeline3);

        Timeline timeline4 = new Timeline("Unsorted");
        timeline4.addGraphDataSource(TestContainers.createSanityCheckContainer(
                false, false));
        grid.addComponent(timeline4);

        Timeline timeline5 = new Timeline("Multiple points");
        // timeline1.setVerticalAxisRange(0f, 5f);
        Container.Indexed t5cont = TestContainers.createSanityCheckContainer(
                false, true);
        ((Container.Sortable) t5cont).sort(
                new Object[] { Timeline.PropertyId.TIMESTAMP },
                new boolean[] { true });
        timeline5.addGraphDataSource(t5cont);
        grid.addComponent(timeline5);

    }

    @Override
    protected Component getChart() {
        return grid;
    }

}
