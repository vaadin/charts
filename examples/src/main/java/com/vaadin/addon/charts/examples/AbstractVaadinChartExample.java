/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.examples;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.Theme;
import com.vaadin.addon.charts.themes.ValoLightTheme;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class AbstractVaadinChartExample extends VerticalLayout
        implements ChartExample {

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
                    while (component.getUI() != null) {
                        Future<Void> future = component.getUI().access(task);
                        future.get();
                        Thread.sleep(interval);
                    }
                } catch (InterruptedException e) {
                } catch (ExecutionException e) {
                    Logger.getLogger(this.getClass().getName())
                            .log(Level.WARNING,
                                    "Stopping repeating command due to an exception",
                                    e);
                } catch (com.vaadin.ui.UIDetachedException e) {
                } catch (Exception e) {
                    Logger.getLogger(this.getClass().getName())
                            .log(Level.WARNING,
                                    "Unexpected exception while running scheduled update",
                                    e);
                }
                Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                        "Thread stopped");
            }

            ;
        };
        thread.start();
    }

    private final VerticalLayout content;

    public AbstractVaadinChartExample() {
        content = this;
        content.setSizeFull();
        content.setMargin(true);
        content.setSpacing(false);
    }

    protected abstract Component getChart();

    @Override
    public void attach() {
        super.attach();
        setup();
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

    protected Color[] getThemeColors() {
        Theme theme = ChartOptions.get().getTheme();
        return (theme != null) ? theme.getColors() : new ValoLightTheme()
                .getColors();
    }

    protected Theme getCurrentTheme() {
        Theme theme = ChartOptions.get().getTheme();
        return (theme != null) ? theme : new ValoLightTheme();
    }
}
