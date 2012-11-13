package com.vaadin.addon.charts;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaadin.addon.charts.client.ui.ThemerState;
import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.Theme;
import com.vaadin.addon.charts.model.style.ThemeGradientColorSerializer;
import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.AbstractExtension;
import com.vaadin.server.Extension;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

/**
 * ChartTheme extension configures page global theme for charts. 
 * With this extension it is possible to configure e.g. default 
 * colors used by all Chart objects displayed in the UI.
 */
public class ChartTheme extends AbstractExtension {

    private boolean painted = false;

    protected ChartTheme() {
    }

    private void notifyListeners() {
        UI ui = getUI();

        if (ui == null) {
            return;
        }

        searchAndNotifyListeners(ui);
    }

    private void searchAndNotifyListeners(Component component) {
        if (component instanceof HasComponents) {
            HasComponents container = (HasComponents) component;
            Iterator<Component> iter = container.iterator();
            while (iter.hasNext()) {
                searchAndNotifyListeners(iter.next());
            }
        } else if (component instanceof Chart) {
            Chart listener = (Chart) component;
            listener.drawChart();
        }
    }

    final static Gson gson;
    static {
        // .serializeNulls()
        GsonBuilder builder = AbstractConfigurationObject.createGsonBuilder();
        builder.registerTypeHierarchyAdapter(GradientColor.class,
                new ThemeGradientColorSerializer());
        gson = builder.create();
    }

    /**
     * Sets the currently used Vaadin Charts theme.
     * <p>
     * Note, that if view is already drawn, all existing {@link Chart}s will be
     * redrawn.
     * 
     * @param theme
     */
    public void setTheme(Theme theme) {
        getState().json = gson.toJson(theme);
        if (painted) {
            notifyListeners();
        }
    }

    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial);
        painted = true;
    }

    @Override
    protected ThemerState getState() {
        return (ThemerState) super.getState();
    }

    void extendConnector(AbstractClientConnector connector) {
        super.extend(connector);
    }

    /**
     * Returns a ChartTheme extension for given UI. If ChartTheme extension is
     * not yet added, a new one is created.
     * 
     * @param ui
     *            the UI which ChartTheme should be returned
     * @return the ChartTheme instance connected to given UI
     */
    public static ChartTheme get(UI ui) {
        ChartTheme themer = null;

        // Search singleton themer
        for (Extension extension : ui.getExtensions()) {
            if (extension instanceof ChartTheme) {
                themer = (ChartTheme) extension;
                break;
            }
        }

        // Create new themer if not found
        if (themer == null) {
            themer = new ChartTheme();
            themer.extendConnector(ui);
        }

        return themer;

    }

    /**
     * Returns a ChartTheme extension for current UI. If ChartTheme extension is
     * not yet added, a new one is created.
     * 
     * @return a ChartTheme instance connected to currently active UI
     */
    public static ChartTheme get() {
        UI ui = UI.getCurrent();

        if (ui == null) {
            throw new IllegalStateException(
                    "This method must be used from UI thread");
        }
        return get(ui);
    }

}
