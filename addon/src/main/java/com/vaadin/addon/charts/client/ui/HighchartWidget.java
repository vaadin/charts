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
package com.vaadin.addon.charts.client.ui;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.charts.client.HighchartsScriptLoader;
import com.vaadin.client.BrowserInfo;
import com.vaadin.client.StyleConstants;

public class HighchartWidget extends Widget implements HasEnabled {

    private HighchartJsOverlay jsOverlay;
    private boolean enabled;

    public HighchartWidget() {
        HighchartsScriptLoader.ensureInjected();
        setElement(Document.get().createDivElement());
        String loading = "<div class=v-loading-indicator style=\"position:relative\"></div>";
        getElement().setInnerHTML(loading);
    }

    public void init(HighchartConfig config, boolean timeline) {
        HighchartJsOverlay old = jsOverlay;
        if (old != null) {
            old.destroy();
        }
        jsOverlay = config.renderTo(getElement(), timeline);
        //Fix for problem with rendering in IE8
        if (BrowserInfo.get().isIE8()) {
            //Call update size
            updateSize();
            //Setting title will trigger title redraw with proper size
            updateTitle();
            //Need to redraw chart again, because some parts of chart are broken
            //after setting titleg, calling chart.redraw does nothing
            updateSize();
        }
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

    public void updateSeries(int seriesIndex, String seriesJson) {
        jsOverlay.updateSeries(seriesIndex, seriesJson);
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

    public void updatexAxis(int axisIndex, double minimum, double maximum,
                            boolean redraw, boolean animate) {
        JsArray<HighchartAxis> axes = jsOverlay.getxAxes();
        axes.get(axisIndex).setExtremes(minimum, maximum, redraw, animate);

    }

    public void resetZoom(boolean redraw, boolean animate) {
        JsArray<HighchartAxis> xAxes = jsOverlay.getxAxes();
        for (int i = 0; i < xAxes.length(); i++) {
            xAxes.get(i).resetZoom(redraw, animate);
        }
        JsArray<HighchartAxis> yAxes = jsOverlay.getyAxes();
        for (int i = 0; i < yAxes.length(); i++) {
            yAxes.get(i).resetZoom(redraw, animate);
        }
    }

    public void updateyAxis(int axisIndex, double minimum, double maximum,
                            boolean redraw, boolean animate) {
        JsArray<HighchartAxis> axes = jsOverlay.getyAxes();
        axes.get(axisIndex).setExtremes(minimum, maximum, redraw, animate);
    }

    public void updatezAxis(int axisIndex, double minimum, double maximum,
                            boolean redraw, boolean animate) {
        JsArray<HighchartAxis> axes = jsOverlay.getzAxes();
        axes.get(axisIndex).setExtremes(minimum, maximum, redraw, animate);
    }

    public void updateColorAxis(int axisIndex, double minimum, double maximum,
                                boolean redraw, boolean animate) {
        JsArray<HighchartAxis> axes = jsOverlay.getColorAxes();
        axes.get(axisIndex).setExtremes(minimum, maximum, redraw, animate);
    }

    public void setAnimation(boolean animation) {
        jsOverlay.setAnimation(animation);
    }

    public void slicePoint(int seriesIndex, int pointIndex, boolean sliced,
                           boolean redraw, boolean animation) {
        HighchartSeries highchartSeries = jsOverlay.getSeries()
                .get(seriesIndex);
        HighchartPoint highchartPoint = highchartSeries.getData().get(
                pointIndex);
        highchartPoint.slice(sliced, redraw, animation);
    }

    /**
     * This method is for workaround for IE8 bug, with wrong title width calculation.
     * Setting the title causes to recalculate the width of a title properly.
     */
    private void updateTitle() {
        if (BrowserInfo.get().isIE()) {
            String title = jsOverlay.getTitle();
            if (title == null) {
                title = "";
            }
            String subTitle = jsOverlay.getSubTitle();
            if (subTitle == null) {
                subTitle = "";
            }
            jsOverlay.setTitle(title, subTitle);

        }
    }

    public void updateSize() {
        jsOverlay.setSize(getOffsetWidth(), getOffsetHeight(), false, true);
    }

    public void destroy() {
        final HighchartJsOverlay jsOverlayToDestroy = jsOverlay;

        Scheduler.get().scheduleFinally(new ScheduledCommand() {
            @Override
            public void execute() {
                jsOverlayToDestroy.destroy();
            }
        });
    }

    public void addDrilldown(String series, int seriesIndex, int pointIndex) {
        jsOverlay.addDrilldown(series, seriesIndex, pointIndex);
    }

    public int getYAxisIndex(HighchartAxis axis) {
        JsArray<HighchartAxis> yAxes = jsOverlay.getyAxes();
        for (int i = 0; i < yAxes.length(); i++) {
            if (yAxes.get(i) == axis) {
                return i;
            }
        }
        return -1;
    }

    public int getXAxisIndex(HighchartAxis axis) {
        JsArray<HighchartAxis> xAxes = jsOverlay.getxAxes();
        for (int i = 0; i < xAxes.length(); i++) {
            if (xAxes.get(i) == axis) {
                return i;
            }
        }
        return -1;
    }

    public int getNumberOfSeries() {
        return jsOverlay.getSeries().length();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            setStyleName(StyleConstants.DISABLED, !enabled);
        }
    }
}
