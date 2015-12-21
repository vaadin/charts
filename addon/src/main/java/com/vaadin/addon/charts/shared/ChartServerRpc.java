package com.vaadin.addon.charts.shared;

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

import com.vaadin.shared.communication.ServerRpc;

public interface ChartServerRpc extends ServerRpc {

    void onChartClick(MouseEventDetails details);

    void onChartDrilldown(DrilldownEventDetails details);

    void onChartDrillup();

    void onPointClick(MouseEventDetails details, int seriesIndex,
            String category, int pointIndex);

    void onSelection(double selectionStart, double selectionEnd,
            double valueStart, double valueEnd);

    void onLegendItemClick(int seriesIndex, int seriesItemIndex);

    void onCheckboxClick(boolean isChecked, int seriesIndex, int seriesItemIndex);

    void onSeriesHide(int seriesIndex, int seriesItemIndex);

    void onSeriesShow(int seriesIndex, int seriesItemIndex);

    void onXAxesExtremesChange(int axisIndex, double minimum, double maximum);

    void onYAxesExtremesChange(int axisIndex, double minimum, double maximum);

    void onPointSelect(int seriesIndex, String category, int pointIndex);

    void onPointUnselect(int seriesIndex, String category, int pointIndex);
}
