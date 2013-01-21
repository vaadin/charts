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

public class HighchartWidget extends Widget {

    private HighchartJsOverlay jsOverlay;

    private boolean redraw = true;
    private boolean shift = false;
    private boolean animation = true;

    public HighchartWidget() {
        HighchartsScriptLoader.ensureInjected();
        setElement(Document.get().createDivElement());
        getElement().setInnerHTML("Loading chart...");
    }

    public void init(HighchartConfig config) {
        jsOverlay = config.renderTo(getElement());
    }

    public void addPoint(double x, double y, int seriesIndex) {
        jsOverlay.addPoint(x, y, seriesIndex, redraw, shift, animation);
    }

    public void removePoint(double x, double y) {
        jsOverlay.removePoint(x, y, 0.01);
    }

    public void updatePointValue(int seriesIndex, int pointIndex,
            double newValue) {
        jsOverlay.updatePointValue(seriesIndex, pointIndex, newValue, redraw,
                animation);
    }

    public void removePoint(double x, double y, int seriesIndex) {
        jsOverlay.removePoint(x, y, seriesIndex, 0.01);
    }

    public void setRedrawAfterUpdate(boolean redraw) {
        this.redraw = redraw;
    }

    public void setAnimationAfterUpdate(boolean animationAfterUpdate) {
        animation = animationAfterUpdate;
    }

    public void setShiftAfterUpdate(boolean shift) {
        this.shift = shift;
    }

    public void setSeriesEnabled(int seriesIndex, boolean enabled) {
        jsOverlay.setSeriesEnabled(seriesIndex, enabled);
    }

    public int getSeriesIndex(HighchartSeries series) {
        JsArray<HighchartSeries> serA = jsOverlay.getSeries();
        for(int i = 0; i< serA.length(); i++) {
            if(serA.get(i) == series) {
                return i;
            }
        }
        return -1;
    }
}
