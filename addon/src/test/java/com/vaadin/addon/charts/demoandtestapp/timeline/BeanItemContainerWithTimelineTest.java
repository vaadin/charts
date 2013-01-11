package com.vaadin.addon.charts.demoandtestapp.timeline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class BeanItemContainerWithTimelineTest extends
        AbstractVaadinChartExample {

    private Timeline timeline;

    public static class TimelineBean {

        private Date RTimestamp;

        private Float RValue;

        public TimelineBean(Date timestamp, float value) {
            this.RTimestamp = timestamp;
            this.RValue = value;
        }

        /**
         * @return the timestamp
         */
        public Date getRTimestamp() {
            return RTimestamp;
        }

        /**
         * @return the value
         */
        public Float getRValue() {
            return RValue;
        }

        public void setRValue(Float f) {
            this.RValue = f;
        }
    }

    public BeanItemContainerWithTimelineTest() {
        setSizeFull();

        timeline = new Timeline("BeanItemContainer data source");
        timeline.setSizeFull();

        final BeanItemContainer<TimelineBean> source = createContainer();

        timeline.addGraphDataSource(source, "RTimestamp", "RValue");
        addComponent(timeline);
        setExpandRatio(timeline, 1);

        addComponent(new Button("Invert", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                invertValues(source);
            }
        }));
    }

    /**
     * Inverts all values in the container.
     */
    private void invertValues(BeanItemContainer<TimelineBean> container) {
        List<TimelineBean> beans = new ArrayList<BeanItemContainerWithTimelineTest.TimelineBean>();
        for (int i = 0; i < container.size(); i++) {

            // Get bean
            BeanItem<TimelineBean> item = container.getItem(container
                    .getIdByIndex(i));
            TimelineBean bean = item.getBean();

            // Invert value
            bean.setRValue(bean.getRValue() * (-1));

            // Store reference
            beans.add(bean);
        }

        // Refresh container (triggers change event for the timeline to repaint
        container.removeAllItems();
        container.addAll(beans);
    }

    /**
     * Creates a new Timeline data source. Uses a BeanItemContainer as data
     * source.
     * 
     * @return
     */
    private BeanItemContainer<TimelineBean> createContainer() {
        BeanItemContainer<TimelineBean> cont = new BeanItemContainer<TimelineBean>(
                TimelineBean.class);

        Calendar cal = Calendar.getInstance();
        Random r = new Random();
        for (int days = 0; days < 365; days++) {
            for (int minutes = 0; minutes < 60 * 12; minutes += 10) {
                cont.addBean(new TimelineBean(cal.getTime(), r.nextFloat()));
                cal.add(Calendar.MINUTE, 10);
            }
        }

        return cont;
    }

    @Override
    protected Component getChart() {
        return null;
    }

    @Override
    protected void setup() {
    }
}
