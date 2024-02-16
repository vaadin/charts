/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.shared;

import com.vaadin.shared.communication.ServerRpc;

public interface ChartServerRpc extends ServerRpc {

    void onChartClick(MouseEventDetails details);

    void onChartDrilldown(DrilldownEventDetails details);

    void onChartDrillup();

    void onPointClick(MouseEventDetails details, int seriesIndex,
            String category, int pointIndex);

    void onSelection(double selectionStart, double selectionEnd,
            double valueStart, double valueEnd);

    void onLegendItemClick(int seriesIndex, int seriesItemIndex, MouseEventDetails mouseEventDetails);

    void onCheckboxClick(boolean isChecked, int seriesIndex, int seriesItemIndex);

    void onSeriesHide(int seriesIndex, int seriesItemIndex);

    void onSeriesShow(int seriesIndex, int seriesItemIndex);

    void onXAxesExtremesChange(int axisIndex, double minimum, double maximum);

    void onYAxesExtremesChange(int axisIndex, double minimum, double maximum);

    void onPointSelect(int seriesIndex, String category, int pointIndex);

    void onPointUnselect(int seriesIndex, String category, int pointIndex);
}
