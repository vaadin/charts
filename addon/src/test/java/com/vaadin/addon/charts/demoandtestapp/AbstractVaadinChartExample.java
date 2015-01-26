package com.vaadin.addon.charts.demoandtestapp;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.addon.charts.model.style.Theme;
import com.vaadin.addon.charts.themes.ValoLightTheme;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.demo.sampler.sample.Sample;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
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
            };
        };
        thread.start();
    }

    private final VerticalLayout content;

    private final boolean skippingDefaultCredits;

    public AbstractVaadinChartExample() {
        this(false);
    }

    /**
     * Constructs the class optionally ignoring default credits added to each
     * chart.
     * 
     * @param skipDefaultCredits
     *            When <b>true</b>, no chart will be given default credits.
     */
    protected AbstractVaadinChartExample(boolean skipDefaultCredits) {
        content = this;
        content.setSizeFull();
        content.setMargin(true);
        skippingDefaultCredits = skipDefaultCredits;
    }

    protected abstract Component getChart();

    /**
     * Adds default credits - "Vaadin Charts" that links to Vaadin Charts page
     * in the directory.
     *
     * @param chart
     *            Chart to add credits to. This will overwrite whatever credits
     *            are in that chart already.
     */
    private static final void addCreditsTo(Chart chart) {
        chart.getConfiguration().getCredits().setStyle(new Style());
        chart.getConfiguration().getCredits().getStyle().setFontSize("14px");
        chart.getConfiguration().getCredits().getStyle()
                .setColor(new SolidColor(128, 128, 128));
        chart.getConfiguration().getCredits().setText("Vaadin Charts");
        chart.getConfiguration().getCredits()
                .setHref("https://vaadin.com/add-ons/charts");
    }

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

    /**
     * Adds credits to all charts in the component.
     * 
     * @param c
     *            Component.
     */
    private static final void addCreditsTo(Component c) {
        if (c instanceof Chart) {
            addCreditsTo((Chart) c);
        } else if (c instanceof ComponentContainer) {
            for (Component c2 : (ComponentContainer) c) {
                addCreditsTo(c2);
            }
        }
    }

    protected void setup() {
        if (content.getComponentCount() == 0) {
            final Component map = getChart();
            // add credits when not skipping them
            if (!isSkippingDefaultCredits()) {
                addCreditsTo(map);
            }
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

    protected boolean isSkippingDefaultCredits() {
        return skippingDefaultCredits;
    }
}
