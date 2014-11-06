package com.vaadin.addon.charts.demoandtestapp;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.demo.sampler.sample.Sample;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class AbstractVaadinChartExample extends VerticalLayout
        implements Sample {

    /**
     * Runs given task repeatedly until the reference component is attached
     * 
     * @param component
     * @param task
     * @param interval
     * @param initialPause
     *            a timeout after tas is started
     */
    public static void runWhileAttached(final Component component,
            final Runnable task, final int interval, final int initialPause) {
        // Until reliable push available in our demo servers
        UI.getCurrent().setPollInterval(interval);

        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(initialPause);
                    while (true) {
                        Future<Void> future = component.getUI().access(task);
                        future.get();
                        Thread.sleep(interval);
                    }
                } catch (InterruptedException e) {
                } catch (ExecutionException e) {
                    Logger.getLogger(getClass().getName())
                            .log(Level.WARNING,
                                    "Stopping repeating command due to an exception",
                                    e);
                } catch (com.vaadin.ui.UIDetachedException e) {
                } catch (Exception e) {
                    Logger.getLogger(getClass().getName())
                            .log(Level.WARNING,
                                    "Unexpected exception while running scheduled update",
                                    e);
                }
                Logger.getLogger(getClass().getName()).log(Level.INFO,
                        "Thread stopped");
            };
        };
        thread.start();
    }

    private final VerticalLayout content;

    public AbstractVaadinChartExample() {
        content = this;
        content.setSizeFull();
    }

    protected abstract Component getChart();

    @Override
    public void attach() {
        super.attach();
        setup();
    }

    @Override
    public Component getComponentUsedByPropertyEditor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List getPropertyEditorExcludedList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List getExtraComponentsForPropertyEditor() {
        return null;
    };

    private Item sampleItem;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Item getSampleItem() {
        if (sampleItem == null) {
            sampleItem = new BeanItem(getComponentUsedByPropertyEditor());
        }
        return sampleItem;
    }

    @Override
    public void sampleOpened() {
        // TODO Auto-generated method stub

    }

    @Override
    public Component getWrappedComponent() {
        setup();
        content.getComponent(0).setSizeFull();
        return content;
    }

    protected void setup() {
        if (content.getComponentCount() == 0) {
            final Component map = getChart();
            content.addComponent(map);
            content.setExpandRatio(map, 1);
        }
    }

}
