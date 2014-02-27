package com.vaadin.addon.charts;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.vaadin.addon.charts.client.ui.ChartOptionsWidget;
import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.Lang;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.Theme;
import com.vaadin.addon.charts.model.style.ThemeGradientColorSerializer;
import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.ClientWidget;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

/**
 * The ChartOptions extension configures a page local theme and other global
 * options like localized texts for charts. With this invisible component it is
 * possible to configure e.g. default colors used by all Chart objects displayed
 * in the Window. Developers should add only one of these components to window
 * and add it before any {@link Chart} components.
 */
@ClientWidget(ChartOptionsWidget.class)
public class ChartOptions extends AbstractComponent {

    private Theme theme;
    private Lang lang;

    public ChartOptions() {
    }

    private void notifyListeners() {
        requestRepaint();
        Window w = getWindow();
        if (w == null) {
            return;
        }
        if (w.getParent() != null) {
            w = w.getParent();
        }

        searchAndNotifyListeners(w);
    }

    private void searchAndNotifyListeners(Component component) {
        if (component instanceof ComponentContainer) {
            ComponentContainer container = (ComponentContainer) component;
            Iterator<Component> iter = container.getComponentIterator();
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
        notifyListeners();
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
        notifyListeners();
    }

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);
        JsonObject json;
        if (theme != null) {
            json = (JsonObject) gson.toJsonTree(theme);
        } else {
            json = new JsonObject();
        }
        if (lang != null) {
            json.add("lang", gson.toJsonTree(lang));
        }
        target.addAttribute("json", json.toString());
    }

}
