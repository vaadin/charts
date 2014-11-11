package com.vaadin.addon.charts;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.vaadin.addon.charts.client.ui.ChartOptionsState;
import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.Lang;
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
 * The ChartOptions extension configures a page local theme and other global
 * options like localized texts for charts. With this extension it is possible
 * to configure e.g. default colors used by all Chart objects displayed in the
 * UI.
 */
public class ChartOptions extends AbstractExtension {

    private Theme theme;
    private Lang lang;

    protected ChartOptions() {
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
     * Sets the theme to use.
     * <p>
     * Note that if the view is already drawn, all existing {@link Chart}s will
     * be redrawn.
     * 
     * @param theme
     */
    public void setTheme(Theme theme) {
        this.theme = theme;
        buildOptionsJson();
        notifyListeners();
    }

    private void buildOptionsJson() {
        JsonObject json;
        if (theme != null) {
            json = gson.toJsonTree(theme).getAsJsonObject();
        } else {
            json = new JsonObject();
        }

        if (lang != null) {
            json.add("lang", Lang.createGsonBuilder().create().toJsonTree(lang));
        }

        getState().json = json.toString();
    }

    /**
     * Returns the {@link Theme} in use or {@code null} if no theme has been
     * set.
     * 
     * @return the {@link Theme} in use or {@code null}.
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * Changes the language of all charts.
     * 
     * @param lang
     */
    public void setLang(Lang lang) {
        this.lang = lang;
        buildOptionsJson();
        notifyListeners();
    }

    @Override
    protected ChartOptionsState getState() {
        return (ChartOptionsState) super.getState();
    }

    void extendConnector(AbstractClientConnector connector) {
        super.extend(connector);
    }

    /**
     * Returns a ChartOptions extension for the given UI. If a ChartOptions
     * extension has not yet been added, a new one is created and added.
     * 
     * @param ui
     *            the UI for which the ChartOptions should be returned
     * @return the ChartOptions instance connected to the given UI
     */
    public static ChartOptions get(UI ui) {
        ChartOptions optioner = null;

        // Search singleton optioner
        for (Extension extension : ui.getExtensions()) {
            if (extension instanceof ChartOptions) {
                optioner = (ChartOptions) extension;
                break;
            }
        }

        // Create new optioner if not found
        if (optioner == null) {
            optioner = new ChartOptions();
            optioner.extendConnector(ui);
        }

        return optioner;

    }

    /**
     * Returns a ChartOptions extension for the current UI. If a ChartOptions
     * extension has not yet been added, a new one is created and added.
     * 
     * @return a ChartOptions instance connected to the currently active UI
     */
    public static ChartOptions get() {
        UI ui = UI.getCurrent();

        if (ui == null) {
            throw new IllegalStateException(
                    "This method must be used from UI thread");
        }
        return get(ui);
    }

}
