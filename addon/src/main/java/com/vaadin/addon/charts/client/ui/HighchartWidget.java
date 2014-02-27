package com.vaadin.addon.charts.client.ui;

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

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.charts.client.HighchartsScriptLoader;
import com.vaadin.client.VConsole;

public class HighchartWidget extends Widget {

    private HighchartJsOverlay jsOverlay;

    public HighchartWidget() {
        HighchartsScriptLoader.ensureInjected();
        setElement(Document.get().createDivElement());
        getElement().setInnerHTML("Loading chart...");
    }

    public void init(HighchartConfig config) {
        HighchartJsOverlay old = jsOverlay;
        if (old != null) {
            old.destroy();
        }
        jsOverlay = config.renderTo(getElement());
    }

    public void addPoint(String pointJson, int seriesIndex, boolean shift) {
        jsOverlay.addPoint(pointJson, seriesIndex, true, shift);
    }

    public void updatePointValue(int seriesIndex, int pointIndex,
            double newValue) {
        HighchartSeries highchartSeries = jsOverlay.getSeries()
                .get(seriesIndex);
        HighchartPoint highchartPoint = highchartSeries.getData().get(
                pointIndex);
        highchartPoint.update(newValue);
    }

    public void updatePointValue(int seriesIndex, int pointIndex, String json) {
        HighchartSeries highchartSeries = jsOverlay.getSeries()
                .get(seriesIndex);
        HighchartPoint highchartPoint = highchartSeries.getData().get(
                pointIndex);
        highchartPoint.update(json);
    }

    public void removePoint(int pointIndex, int seriesIndex) {
        jsOverlay.removePoint(pointIndex, seriesIndex);
    }

    public void setSeriesEnabled(int seriesIndex, boolean enabled) {
        jsOverlay.setSeriesEnabled(seriesIndex, enabled);
    }

    public int getSeriesIndex(HighchartSeries series) {
        JsArray<HighchartSeries> serA = jsOverlay.getSeries();
        for (int i = 0; i < serA.length(); i++) {
            if (serA.get(i) == series) {
                return i;
            }
        }
        return -1;
    }

    public void setAnimation(boolean animation) {
        jsOverlay.setAnimation(animation);
    }

    public void updateSize() {
        jsOverlay.setSize(getOffsetWidth(), getOffsetHeight(), false, true);
    }

    public void destroy() {
        jsOverlay.destroy();
    }
}
